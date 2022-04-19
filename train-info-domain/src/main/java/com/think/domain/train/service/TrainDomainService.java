package com.think.domain.train.service;

import com.think.domain.assembler.DomainAssembler;
import com.think.domain.train.entity.TrainInfoEntity;
import com.think.domain.train.repository.TrainRepository;
import com.think.infrastructure.mybits.po.TrainInfoPO;
import org.assertj.core.util.Lists;
import org.assertj.core.util.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;

/**
 * @author hg
 * @date 2022-04-01日 15:30
 */
@Service
public class TrainDomainService {


    @Autowired
    TrainRepository trainRepository;

    /**
     * 更新车次基本信息
     * @param list
     */
    public void updateTrainInfoList(List<TrainInfoEntity> list){
        if(list == null || list.isEmpty()){
            return;
        }
        //trainRepository.deleteTrainInfoList();
        List<TrainInfoPO> trainInfoPOList = Lists.newArrayList();
        list.forEach(trainInfo->{trainInfoPOList.add(DomainAssembler.createTrainInfoPO(trainInfo));});
        trainRepository.saveTrainInfoList(trainInfoPOList);
    }


    /**
     * 更新车次运行信息
     * @param list
     */
    public void updateTrainDetailList(List<TrainInfoEntity> list){
        if(list == null || list.isEmpty()){
            return;
        }
        List<TrainInfoPO> trainInfoPOList = Lists.newArrayList();

    }

}
