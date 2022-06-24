package com.think.acl.port.repository;

import com.think.domain.station.model.StationAggregate;
import com.think.domain.station.port.repository.StationRepository;
import com.think.common.assembler.StationAssembler;
import com.think.infrastructure.mybatis.mapper.IStationInfoMapper;
import com.think.infrastructure.mybatis.po.StationInfoPO;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author hg
 * @date 2022-04-08日 15:47
 */
@Component
public class StationRepositoryImpl implements StationRepository {

    @Autowired
    IStationInfoMapper iStationInfoMapper;


    /**
     * 持久化聚合根
     *
     * @param stationAggregate
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(StationAggregate stationAggregate) {
        //失效数据
        iStationInfoMapper.deleteByStationCode(stationAggregate.getStationPf());
        StationInfoPO stationInfoPO = StationAssembler.createStationInfoPO(stationAggregate);
        iStationInfoMapper.insert(stationInfoPO);
    }

    /**
     * 批量持久化
     *
     * @param list
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchSave(List<StationAggregate> list) {
        //失效所有数据
        iStationInfoMapper.deleteAll();
        List<StationInfoPO> stationInfoPOS = Lists.newArrayList();
        list.forEach( station->{
            StationInfoPO stationInfoPO = StationAssembler.createStationInfoPO(station);
            stationInfoPOS.add(stationInfoPO);
        });
        iStationInfoMapper.insertBatchSomeColumn(stationInfoPOS);
    }

}
