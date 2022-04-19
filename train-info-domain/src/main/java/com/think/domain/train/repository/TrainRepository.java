package com.think.domain.train.repository;

import com.think.infrastructure.mybits.po.TrainInfoPO;

import java.util.List;

/**
 * @author hg
 * @date 2022-04-01日 15:30
 */
public interface TrainRepository {

    public void saveTrainInfoList(List<TrainInfoPO> list);

    public void deleteTrainInfoByTrainCode(List<TrainInfoPO> list);

}
