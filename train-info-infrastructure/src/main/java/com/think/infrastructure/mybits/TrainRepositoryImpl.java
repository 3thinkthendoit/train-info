package com.think.infrastructure.mybits;

import com.think.domain.train.entity.TrainInfoEntity;
import com.think.domain.train.repository.TrainRepository;
import com.think.infrastructure.mybits.mapper.ITrainInfoMapper;
import com.think.infrastructure.mybits.po.TrainInfoPO;
import org.assertj.core.util.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * @author hg
 * @date 2022-04-01日 15:42
 */
@Component
public class TrainRepositoryImpl implements TrainRepository {

    @Autowired
    ITrainInfoMapper iTrainInfoMapper;

    @Override
    public void saveTrainInfoList(List<TrainInfoPO> list){
        iTrainInfoMapper.insertBatchSomeColumn(list);
    }

    @Override
    public void deleteTrainInfoByTrainCode(List<TrainInfoPO> list){

        //iTrainInfoMapper.updateTrainInfoByTrainCode(ids);
    }

}
