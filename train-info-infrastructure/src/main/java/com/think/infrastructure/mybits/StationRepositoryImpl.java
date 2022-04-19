package com.think.infrastructure.mybits;

import com.think.domain.assembler.DomainAssembler;
import com.think.domain.station.entity.StationEntity;
import com.think.domain.station.repository.StationRepository;
import com.think.infrastructure.assembler.StationAssembler;
import com.think.infrastructure.mybits.mapper.IStationInfoMapper;
import com.think.infrastructure.mybits.po.StationInfoPO;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
     * 批量保存
     *
     * @param list
     */
    @Override
    public void saveList(List<StationEntity> list) {
        if(list ==null || list.isEmpty()){
            return;
        }
        List<StationInfoPO> stationInfoPOS = Lists.newArrayList();
        list.forEach(stationEntity -> {stationInfoPOS.add(StationAssembler.createStationInfoPO(stationEntity));});
        iStationInfoMapper.insertBatchSomeColumn(stationInfoPOS);
    }

    /**
     * 删除全部
     */
    @Override
    public void deleteAll() {
        iStationInfoMapper.deleteAll();
    }
}
