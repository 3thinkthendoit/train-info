package com.think.application.service.station;

import com.think.adapter.dto.cmd.GetAllStationCmd;
import com.think.application.bo.StationBO;
import com.think.application.service.adapter.ApplicationAdapter;
import com.think.application.service.spider.SpiderService;
import com.think.domain.gateway.StationGateway;
import com.think.domain.station.entity.StationEntity;
import com.think.domain.station.service.StationDomainService;
import com.think.infrastructure.http.IHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author hg
 * @date 2022-04-11日 14:24
 */
@Service
public class StationAppService {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    IHttpClient iHttpClient;
    @Autowired
    ApplicationAdapter applicationAdapter;
    @Autowired
    StationGateway stationGateway;

    /**
     * 根据数据源获取车站信息
     * @param cmd
     */
    @Async
    public void executeGetAllStation(GetAllStationCmd cmd){
       try {
           String source = cmd.getSource();
           SpiderService spiderService =  (SpiderService) applicationAdapter.get(source);
           List<StationBO> list =  spiderService.execute(iHttpClient,null);
           stationGateway.updateStationList(list);
       }catch (Exception e){
           logger.error(e.getMessage(),e);
       }
    }
}

