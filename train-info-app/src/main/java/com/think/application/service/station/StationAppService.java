package com.think.application.service.station;

import com.think.domain.common.TrainDataSource;
import com.think.domain.station.model.CreateStationCommand;
import com.think.domain.station.model.StationAggregate;
import com.think.domain.station.port.repository.StationRepository;
import com.think.domain.station.service.GetStationDomainService;
import org.assertj.core.util.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.List;


/**
 *  车站应用服务
 * @author hg
 * @date 2022-04-11日 14:24
 */
@Service
public class StationAppService {

    protected Logger logger = LoggerFactory.getLogger(getClass());


    @Autowired
    GetStationDomainService getStationDomainService;
    @Autowired
    StationRepository stationRepository;

    /**
     * 根据数据源获取车站信息(通用用例)
     * @param trainDataSource
     */
    @Async
    public void getAllStation(TrainDataSource trainDataSource){
       try {
           List<CreateStationCommand>  createStationCommands = getStationDomainService.getAllStationByDataSource(trainDataSource);
           List<StationAggregate> stationAggregates = Lists.newArrayList();
           for (int i = 0; i < createStationCommands.size(); i++) {
               StationAggregate stationAggregate = StationAggregate.create(createStationCommands.get(i));
               //城市地域归类
               stationAggregate.setCity();
               //设置经纬度
               stationAggregate.setLongitudeAndLatitude();
               stationAggregates.add(stationAggregate);
           }
           stationRepository.batchSave(stationAggregates);
           logger.info("成功处理车站size = {} :",stationAggregates.size());
       }catch (Exception e){
           logger.error(e.getMessage(),e);
       }
    }


}

