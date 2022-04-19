package com.think.application.service.task;

import com.google.common.collect.Maps;
import com.think.infrastructure.common.domain.SpiderTaskContext;
import com.think.infrastructure.common.domain.TaskInfo;
import com.think.infrastructure.mybits.po.TrainDetailPO;
import com.think.infrastructure.mybits.po.TrainInfoPO;
import com.think.infrastructure.mybits.mapper.ITrainDetailMapper;
import com.think.infrastructure.mybits.mapper.ITrainInfoMapper;
import com.think.application.service.spider.SpiderService;
import com.think.train.TrainDetailListener;
import com.think.train.TrainDetailPipeline;
import com.think.train.TrainDetailProcessor;
import com.think.infrastructure.common.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author hg
 * @date 2021-08-13日 18:05
 */
@Service
public class TrainDetailTaskService implements TaskService{

    @Autowired
    SpiderService spiderService;
    @Autowired
    TrainInfoTaskService trainInfoTaskService;
    @Autowired
    ITrainDetailMapper trainDetailMapper;
    @Autowired
    ITrainInfoMapper trainInfoMapper;

    protected Logger logger = LoggerFactory.getLogger(getClass());

    private AtomicBoolean IS_RUNNING = new AtomicBoolean(false);

    private Map<String,Object> trainMap = Maps.newConcurrentMap();

    private Map<String,CountDownLatch> monitorMap = Maps.newConcurrentMap();

    private void setIsRunning(boolean isRunning){
        IS_RUNNING.set(isRunning);
    }

    public void getAllTrainDetailInfo(){
        trainMap.clear();
        trainInfoTaskService.cleanDuplicateTrainInfo();
        if(IS_RUNNING.get()){
            logger.info("getTrainDetail task 正在运行...");
        }
        Thread t = new Thread(){
            @Override
            public void run() {
                logger.info("getTrainDetail task 开始运行");
                runTask();
            }
        };
        t.start();
        setIsRunning(true);
    }

    /**
     *
     */
    @Override
    public void runTask() {
        List<String> dateList = Stream.of(CommonUtil.getTodayString(0), CommonUtil.getTodayString(1),
                CommonUtil.getTodayString(-1)).collect(Collectors.toList());

        for (int i = 0; i < dateList.size(); i++) {
            try{
                getTrainDetailByDate(dateList.get(i));
                Thread.sleep(6000);
                logger.info("暂停6秒后继续运行.....");
            }catch (Exception e){
                logger.error(e.getMessage(),e);
            }
        }
        setIsRunning(false);
    }

    private void getTrainDetailByDate(String date){
        int counter = 0;
        int batchSize = 30;
        List<TrainInfoPO> list = trainInfoMapper.getTrainList();
        SpiderTaskContext spiderTaskContext = null;
        for (int i = 0; i < list.size(); i++) {
            try {
                spiderTaskContext = new SpiderTaskContext(this);
                spiderTaskContext.setParam("date", date);
                spiderTaskContext.setParam("trainInfo",list.get(i));
                initTaskInfo(spiderTaskContext);
                executeTask(spiderTaskContext);
                if(counter%batchSize ==0){
                    Thread.sleep(3000);
                }
                counter++;
            }catch (Exception e){
                logger.error(e.getMessage(),e);
            }
        }
    }

    /**
     *
     * @throws Exception
     */
    @Override
    public void initTaskInfo(SpiderTaskContext spiderTaskContext)  {
        TaskInfo taskInfo = spiderTaskContext.getTaskInfo();
        TrainInfoPO trainInfo = (TrainInfoPO)spiderTaskContext.getData().get("trainInfo");
        String url = "https://kyfw.12306.cn/otn/queryTrainInfo/query?leftTicketDTO.train_no=$trainNo&leftTicketDTO.train_date=$date&rand_code="
                +System.currentTimeMillis();
        url = url.replace("$date",(String)spiderTaskContext.getData().get("date"));
        url = url.replace("$trainNo", trainInfo.getTrainNo());
        taskInfo.setUrl(url);
        taskInfo.setObject(trainInfo);
        taskInfo.setPageProcessor(new TrainDetailProcessor(spiderTaskContext));
        taskInfo.setPipeline( new TrainDetailPipeline(spiderTaskContext));
        taskInfo.setSpiderListener(new TrainDetailListener());
    }



    @Override
    public void executeTask(SpiderTaskContext spiderTaskContext) {
            spiderService.doTask(spiderTaskContext.getTaskInfo());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void afterTask(SpiderTaskContext spiderTaskContext) {
        List<TrainDetailPO> list = (List<TrainDetailPO>)spiderTaskContext.getData().get("list");
        if(list == null || list.isEmpty()){
            return;
        }
        //删除已经完成数据
        trainInfoMapper.updateTrainInfoByTrainCode(spiderTaskContext.getData());
        trainDetailMapper.deleteTrainDetailByTrainCode(spiderTaskContext.getData());
        for (int i = 0; i < list.size(); i++) {
            trainDetailMapper.insert(list.get(i));
            trainMap.remove(list.get(i).getTrainCode());
        }
    }



}
