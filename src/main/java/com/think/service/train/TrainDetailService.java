package com.think.service.train;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.base.Stopwatch;
import com.think.common.domain.TaskInfo;
import com.think.db.entity.TrainDetailEntity;
import com.think.db.entity.TrainInfoEntity;
import com.think.db.mapper.ITrainDetailMapper;
import com.think.db.mapper.ITrainInfoMapper;
import com.think.service.spider.SpiderService;
import com.think.spider.train.TrainDetailListener;
import com.think.spider.train.TrainDetailPipeline;
import com.think.spider.train.TrainDetailProcessor;
import com.think.util.SpiderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * @author hg
 * @date 2021-08-13日 18:05
 */
@Service
public class TrainDetailService extends ServiceImpl<ITrainDetailMapper, TrainDetailEntity> {

    @Autowired
    SpiderService spiderService;
    @Autowired
    TrainInfoService trainInfoService;
    @Autowired
    ITrainInfoMapper trainInfoMapper;
    @Autowired
    ITrainDetailMapper trainDetailMapper;

    protected Logger logger = LoggerFactory.getLogger(getClass());

    private boolean IS_RUNNING = false;

    public void getTrainDetail(){
        trainInfoService.cleanDuplicateTrainInfo();
        if(IS_RUNNING){
            logger.info("getTrainDetail task 正在运行...");
        }
        Thread t = new Thread(){
            @Override
            public void run() {
                runGetTrainDetailTask();
            }
        };
        t.start();
        setIsRunning(true);
    }

    private void setIsRunning(boolean isRunning){
        IS_RUNNING = isRunning;
    }

    private void runGetTrainDetailTask() {
        String checkTag = "";
        int counter = 0;
        boolean isOK = true;
        Stopwatch stopwatch = Stopwatch.createStarted();
        while (isOK){
           try {
               List<TrainInfoEntity> list = trainInfoMapper.getTrainList();
               if(list.size() == 0){
                   isOK = false;
                   setIsRunning(false);
                   return;
               }
               //重复数据6次停止
               if(checkTag.equalsIgnoreCase(list.get(0).getTrainCode())){
                   counter++;
                   logger.info("查询数据重复6次将停止任务,当前计数:{}.",counter);
               }
               checkTag = list.get(0).getTrainCode();
               if(counter > 5){
                   isOK = false;
                   setIsRunning(false);
                   return;
               }
               TaskInfo taskInfo = null;
               TrainInfoEntity trainInfo = null;
               CountDownLatch countDownLatch = new CountDownLatch(list.size());
               //String url = "http://mobile.12306.cn/weixin/czxx/queryByTrainNo?train_no=$trainNo&from_station_telecode=BBB&to_station_telecode=BBB&depart_date=$date";
               String url = "https://kyfw.12306.cn/otn/queryTrainInfo/query?leftTicketDTO.train_no=$trainNo&leftTicketDTO.train_date=$date&rand_code="+System.currentTimeMillis();
               url = url.replace("$date", SpiderUtil.getTodayString());
               for (int i = 0; i < list.size(); i++) {
                   trainInfo = list.get(i);
                   taskInfo = new TaskInfo();
                   url = url.replace("$trainNo", trainInfo.getTrainNo());
                   taskInfo.setUrl(url);
                   taskInfo.setPageProcessor(new TrainDetailProcessor());
                   taskInfo.setPipeline( new TrainDetailPipeline(countDownLatch));
                   taskInfo.setSpiderListener(new TrainDetailListener(countDownLatch));
                   spiderService.doTask(taskInfo);
                   url = url.replace(trainInfo.getTrainNo(), "$trainNo");
               }
               countDownLatch.await();
           }catch (Exception e){
               logger.error(e.getMessage(),e);
           }
        }
        stopwatch.stop();
        logger.info("getTrainDerail spends:{}",stopwatch.toString());
        setIsRunning(false);
    }

    @Transactional(rollbackFor = Exception.class)
    public void handleTrainDetail(List<TrainDetailEntity> list, Map<String,Object> params){
        logger.info("params:{}",params);
        trainInfoMapper.updateTrainInfoByTrainCode(params);
        trainDetailMapper.deleteTrainDetailByTrainCode(params);
        saveBatch(list);
    }
}
