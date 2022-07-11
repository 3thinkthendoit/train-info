package com.think.acl.port.repository;

import com.think.infrastructure.redis.RedissonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.think.domain.screen.model.CreateScreenInfoAggregate;
import com.think.domain.screen.port.repository.ScreenInfoRepository;

/**
 * @author NINGMEI
 */
@Repository
public class ScreenInfoRepositoryImpl implements ScreenInfoRepository{


    @Autowired
    RedissonUtil redissonUtil;

    @Override
    public void save(CreateScreenInfoAggregate createScreenInfoAggregate) {
        //redis 持久化
        //redissonUtil
    }
    
}
