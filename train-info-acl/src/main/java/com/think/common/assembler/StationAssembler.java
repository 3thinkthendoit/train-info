package com.think.common.assembler;

import com.think.domain.station.model.StationAggregate;
import com.think.infrastructure.mybatis.po.StationInfoPO;

/**
 * @author hg
 * @date 2022-04-15日 17:43
 */
public class StationAssembler {

    public static StationInfoPO createStationInfoPO(StationAggregate stationAggregate){
        StationInfoPO stationInfoPO = new StationInfoPO();
        stationInfoPO.setStationCode(stationAggregate.getStationId().getStationCode());
        stationInfoPO.setStationId(Integer.valueOf(stationAggregate.getStationId().getSid()));
        stationInfoPO.setStationName(stationAggregate.getStationName());
        stationInfoPO.setStationPf(stationAggregate.getStationPf());
        stationInfoPO.setStationPj(stationAggregate.getStationPj());
        stationInfoPO.setStationNo(stationAggregate.getStationNo());
        stationInfoPO.setState((byte)0);
        return  stationInfoPO;
    }
}
