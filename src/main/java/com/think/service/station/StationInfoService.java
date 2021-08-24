package com.think.service.station;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.think.db.entity.StationInfoEntity;
import com.think.db.mapper.IStationInfoMapper;
import com.think.common.domain.TaskInfo;
import com.think.service.spider.SpiderService;
import com.think.spider.station.StationNameProcessor;
import com.think.spider.station.StationNamePipeline;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 * @author hg
 * @date 2021-08-09日 17:59
 */
@Service
public class StationInfoService extends ServiceImpl<IStationInfoMapper,StationInfoEntity> {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    IStationInfoMapper iStationInfoMapper;
    @Autowired
    SpiderService spiderService;
    /**
     * 更新所有车站
     * @param stationInfoEntityList
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveStationList(List<StationInfoEntity> stationInfoEntityList) throws Exception {
        deleteAll();
        saveBatch(stationInfoEntityList);
    }

    /**
     * 删除所有数据
     */
    public void deleteAll(){
        iStationInfoMapper.deleteAll();
    }

    public void getStationInfo(){
        TaskInfo taskInfo = new TaskInfo();
        taskInfo.setUrl("https://kyfw.12306.cn/otn/resources/js/framework/station_name.js");
        //taskInfo.setUrl("https://kyfw.12306.cn/otn/queryTrainInfo/query?leftTicketDTO.train_no=5500000Z4000&leftTicketDTO.train_date=2021-08-24&rand_code=1629793873245");
        taskInfo.setPageProcessor(new StationNameProcessor());
        taskInfo.setPipeline(new StationNamePipeline());
        spiderService.doTask(taskInfo);
    }

}
