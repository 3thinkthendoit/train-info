package com.think.domain.gateway.impl;

import com.think.application.bo.StationBO;
import com.think.domain.assembler.DomainAssembler;
import com.think.domain.gateway.StationGateway;
import com.think.domain.station.entity.StationEntity;
import com.think.domain.station.service.StationDomainService;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hg
 * @date 2022-04-15日 17:14
 */
@Service
public class StationGatewayImpl implements StationGateway {

    @Autowired
    StationDomainService stationDomainService;


    @Override
    public void updateStationList(List<StationBO> list) {
        if(list == null || list.isEmpty()){
            return;
        }
        List<StationEntity> entities = Lists.newArrayList();
        list.forEach(stationBO -> {entities.add(DomainAssembler.createStationEntity(stationBO));});
        stationDomainService.updateStationList(entities);
    }
}
