package com.think.infrastructure.redis;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author NINGMEI
 */
@Component
public class RedissonUtil implements InitializingBean, DisposableBean {


    @Override
    public void destroy() throws Exception {

    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}