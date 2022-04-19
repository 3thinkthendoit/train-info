package com.think.domain.station.service;

import com.think.domain.assembler.DomainAssembler;
import com.think.domain.station.entity.StationEntity;
import com.think.domain.station.repository.StationRepository;
import com.think.infrastructure.mybits.po.StationInfoPO;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author hg
 * @date 2022-04-01日 15:28
 */
@Service
public class StationDomainService {


    @Autowired
    StationRepository stationRepository;

    /**
     * 更新所有车站信息
     */
    public void updateStationList(List<StationEntity> list){
        stationRepository.deleteAll();
//        List<StationInfoPO> stationList = Lists.newArrayList();
//        list.forEach(stationEntity->{ stationList.add(DomainAssembler.createStationInfoPO(stationEntity));});
        stationRepository.saveList(list);
    }

}
