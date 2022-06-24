package com.think.acl.port.pl;

import com.think.infrastructure.http.IHttpClient;

import java.util.List;
import java.util.Map;

/**
 * @author hg
 * @date 2022-04-12日 14:58
 */
public interface SpiderService <T>{

    /**
     * http请求
     * @param iHttpClient
     * @param params
     * @return
     * @throws Exception
     */
    public List<T> execute(IHttpClient iHttpClient, Map<String, Object> params) throws Exception;
}
