package com.think.application.service.spider;

import com.think.domain.station.entity.StationEntity;
import com.think.infrastructure.http.IHttpClient;

import java.util.List;
import java.util.Map;

/**
 * @author hg
 * @date 2022-04-12日 14:58
 */
public interface SpiderService <T>{

    public List<T> execute(IHttpClient iHttpClient, Map<String,Object> params) throws Exception;
}
