package com.think.application.service.task;

import com.think.infrastructure.common.domain.SpiderTaskContext;
import com.think.infrastructure.common.exception.TException;
import com.think.infrastructure.mybits.po.StationInfoPO;
import com.think.infrastructure.common.domain.TaskInfo;
import com.think.infrastructure.mybits.mapper.IStationInfoMapper;
import com.think.application.service.spider.SpiderService;
import com.think.spider.station.StationNameProcessor;
import com.think.spider.station.StationNamePipeline;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author hg
 * @date 2021-08-09日 17:59
 */
@Service
public class StationTaskService implements TaskService{

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    SpiderService spiderService;
    @Autowired
    IStationInfoMapper stationInfoMapper;

    ExecutorService cachedThreadPool = Executors.newFixedThreadPool(10);

    @Transactional(rollbackFor = Exception.class)
    public void test() {
        int a = 10;
        cachedThreadPool.submit(new Runnable() {
            @Override
            public void run() {
                stationInfoMapper.deleteAll();
            }
        });
        if(a >1){
            throw new TException("rollback for test !!!!!");
        }
    }

    @Override
    public void runTask() {
        SpiderTaskContext spiderTaskContext = new SpiderTaskContext(this);
        initTaskInfo(spiderTaskContext);
        executeTask(spiderTaskContext);
    }

    @Override
    public void initTaskInfo(SpiderTaskContext spiderTaskContext) {
        TaskInfo taskInfo = spiderTaskContext.getTaskInfo();
        taskInfo.setUrl("https://kyfw.12306.cn/otn/resources/js/framework/station_name.js");
        taskInfo.setPageProcessor(new StationNameProcessor());
        taskInfo.setPipeline(new StationNamePipeline(spiderTaskContext));
    }

    @Override
    public void executeTask(SpiderTaskContext spiderTaskContext) {
        spiderService.doTask(spiderTaskContext.getTaskInfo());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void afterTask(SpiderTaskContext spiderTaskContext) {
        List<StationInfoPO> list =  (List<StationInfoPO>)spiderTaskContext.getData().get("list");
        if(list == null || list.isEmpty()){
            return;
        }
        stationInfoMapper.deleteAll();
        for (int i = 0; i < list.size(); i++) {
            stationInfoMapper.insert(list.get(i));
        }
    }


}
