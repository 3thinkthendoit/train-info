package com.think.service.train;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.base.Stopwatch;
import com.think.common.domain.TaskInfo;
import com.think.common.exception.TException;
import com.think.db.entity.TrainInfoEntity;
import com.think.db.mapper.ITrainInfoMapper;
import com.think.service.spider.SpiderService;
import com.think.spider.train.TrainListListener;
import com.think.spider.train.TrainListPipeline;
import com.think.spider.train.TrainListProcessor;
import com.think.util.SpiderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author hg
 * @date 2021-08-11日 11:29
 */
@Service
public class TrainInfoService extends ServiceImpl<ITrainInfoMapper,TrainInfoEntity> {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    SpiderService spiderService;
    @Autowired
    ITrainInfoMapper trainInfoMapper;

    private boolean IS_RUNNING = false;

    List<String> keywords = Stream.of("1","2","3","5","6","7","8","9","C","D","S","G","K","T","Y","Z").collect(Collectors.toList());

    private void deleteAllTrainInfo(){
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

    /**
     *
     */
    public void getTrainInfo(){
        checkIsRunning();
        try {
            deleteAllTrainInfo();
            TaskInfo taskInfo = null;
            CountDownLatch cd  = new CountDownLatch(keywords.size());
            Stopwatch sw = Stopwatch.createStarted();
            for (int i = 0; i < keywords.size(); i++) {
                taskInfo = new TaskInfo();
                taskInfo.setUrl("https://search.12306.cn/search/v1/train/search?keyword="+keywords.get(i)+"&date="+ SpiderUtil.getToday());
                taskInfo.setPageProcessor(new TrainListProcessor());
                taskInfo.setSpiderListener(new TrainListListener(cd));
                taskInfo.setPipeline(new TrainListPipeline(cd));
                spiderService.doTask(taskInfo);
            }
            cd.await();
            sw.stop();
            logger.info("getTrainInfo 耗时:{}",sw.toString());
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }finally {
            setIsRunning(false);
        }

    }

    /**
     * 批量保存
     * @param list
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveTrainInfoList(List<TrainInfoEntity> list){
        saveBatch(list);
        logger.info("total train list size:{}",list.size());
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
}
