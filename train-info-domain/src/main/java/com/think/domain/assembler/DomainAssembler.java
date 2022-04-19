package com.think.domain.assembler;

import com.think.application.bo.StationBO;
import com.think.domain.station.entity.StationEntity;
import com.think.domain.train.entity.TrainInfoEntity;
import com.think.infrastructure.common.util.CommonUtil;
import com.think.infrastructure.mybits.po.TrainInfoPO;

/**
 * @author hg
 * @date 2022-04-13日 14:18
 */
public class DomainAssembler {

    public static StationEntity createStationEntity(StationBO stationBO){
        StationEntity stationEntity = new StationEntity();
        stationEntity.setStationCode(stationBO.getStationCode());
        stationEntity.setStationId(Integer.valueOf(stationBO.getStationId()));
        stationEntity.setStationName(stationBO.getStationName());
        stationEntity.setStationPf(stationBO.getStationPf());
        stationEntity.setStationPj(stationBO.getStationPj());
        stationEntity.setStationNo(stationBO.getStationNo());
        return  stationEntity;
    }

    public static TrainInfoPO createTrainInfoPO(TrainInfoEntity train){
        TrainInfoPO trainInfoPO = new TrainInfoPO();
//        trainInfoPO.setDepart(train.getDepart());
//        trainInfoPO.setDestination(train.getDestination());
//        trainInfoPO.setTrainNo(train.getTrainNo());
//        trainInfoPO.setTrainCode(train.getTrainCode());
//        trainInfoPO.setState((byte)0);
//        trainInfoPO.setTrainDate(CommonUtil.parseDate(train.getDate()));
        return trainInfoPO;
    }

}
