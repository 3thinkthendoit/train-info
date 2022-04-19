package com.think.application.service.task;

import com.think.infrastructure.common.domain.SpiderTaskContext;
import com.think.infrastructure.common.domain.TaskInfo;
import com.think.infrastructure.common.exception.TException;
import com.think.infrastructure.mybits.po.TrainInfoPO;
import com.think.infrastructure.mybits.mapper.ITrainInfoMapper;
import com.think.application.service.spider.SpiderService;
import com.think.train.TrainListListener;
import com.think.train.TrainListPipeline;
import com.think.train.TrainListProcessor;
import com.think.infrastructure.common.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author hg
 * @date 2021-08-11日 11:29
 */
@Service
public class TrainInfoTaskService implements TaskService{

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    SpiderService spiderService;
    @Autowired
    ITrainInfoMapper trainInfoMapper;

    private boolean IS_RUNNING = false;

    List<String> keywords = Stream.of("1","2","3","5","6","7","8","9","C","D","S","G","K","T","Y","Z").collect(Collectors.toList());

    @Transactional(rollbackFor = Exception.class)
    public void deleteAllTrainInfo(){
        trainInfoMapper.deleteAllTrainInfo();
    }

    private void setIsRunning(boolean isRunning){
        IS_RUNNING = isRunning;
    }

    private void checkIsRunning(){
        if(IS_RUNNING){
            throw new TException("getTrainInfo-任务已经在执行!");
        }
        setIsRunning(true);
    }

    private void getTrainInfo(String date) throws InterruptedException {
        for (int i = 0; i < keywords.size(); i++) {
            SpiderTaskContext spiderTaskContext = SpiderTaskContext.create(this);
            spiderTaskContext.setParam("date",date);
            spiderTaskContext.setParam("keyword",keywords.get(i));
            initTaskInfo(spiderTaskContext);
            executeTask(spiderTaskContext);
        }
        //清洗重复数据
        cleanDuplicateTrainInfo();
    }

    /**
     * 删除重复数据
     */
    @Transactional(rollbackFor = Exception.class)
    public void cleanDuplicateTrainInfo(){
        List<Long> tList = trainInfoMapper.selectDuplicateTrainInfo();
        if(!tList.isEmpty()) {
            trainInfoMapper.deleteBatchIds(tList);
        }
    }


    @Override
    public void runTask() {
        checkIsRunning();
        deleteAllTrainInfo();
        List<String> dateList = Stream.of(CommonUtil.getDateString(-1),
                CommonUtil.getDateString(0), CommonUtil.getDateString(1)).collect(Collectors.toList());
        for (int i = 0; i < dateList.size(); i++) {
            try {
                getTrainInfo(dateList.get(i));
            }catch (Exception e){
                logger.error(e.getMessage(),e);
            }
        }
        setIsRunning(false);
    }

    @Override
    public void initTaskInfo(SpiderTaskContext spiderTaskContext) {
        TaskInfo taskInfo = spiderTaskContext.getTaskInfo();
        taskInfo.setUrl("https://search.12306.cn/search/v1/train/search?keyword="+ spiderTaskContext.getParam("keyword")+"&date="+  spiderTaskContext.getParam("date"));
        taskInfo.setPageProcessor(new TrainListProcessor(spiderTaskContext));
        taskInfo.setSpiderListener(new TrainListListener(spiderTaskContext));
        taskInfo.setPipeline(new TrainListPipeline(spiderTaskContext));

    }

    @Override
    public void executeTask(SpiderTaskContext spiderTaskContext) {
        spiderService.doTask(spiderTaskContext.getTaskInfo());
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void afterTask(SpiderTaskContext spiderTaskContext) {
        List<TrainInfoPO> list = (List<TrainInfoPO>)spiderTaskContext.getData().get("list");
        if(list!=null && !list.isEmpty()){
            for (int i = 0; i < list.size(); i++) {
                trainInfoMapper.insert(list.get(i));
            }
            logger.info("total train list size:{}",list.size());
        }
    }

}
