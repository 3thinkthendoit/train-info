package com.think.acl.port.repository;

import com.think.domain.train.port.repository.TrainRepository;
import com.think.infrastructure.mybatis.mapper.ITrainInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author hg
 * @date 2022-04-01日 15:42
 */
@Repository
public class TrainRepositoryImpl implements TrainRepository {

    @Autowired
    ITrainInfoMapper iTrainInfoMapper;

}
