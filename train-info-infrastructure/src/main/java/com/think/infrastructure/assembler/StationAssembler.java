package com.think.infrastructure.assembler;

import com.think.application.bo.StationBO;
import com.think.domain.station.entity.StationEntity;
import com.think.infrastructure.mybits.po.StationInfoPO;

/**
 * @author hg
 * @date 2022-04-15日 17:43
 */
public class StationAssembler {

    public static StationInfoPO createStationInfoPO(StationEntity stationEntity){
        StationInfoPO stationInfoPO = new StationInfoPO();
        stationInfoPO.setStationCode(stationEntity.getStationCode());
        stationInfoPO.setStationId(Integer.valueOf(stationEntity.getStationId()));
        stationInfoPO.setStationName(stationEntity.getStationName());
        stationInfoPO.setStationPf(stationEntity.getStationPf());
        stationInfoPO.setStationPj(stationEntity.getStationPj());
        stationInfoPO.setStationNo(stationEntity.getStationNo());
        stationInfoPO.setState((byte)0);
        return  stationInfoPO;
    }
}
