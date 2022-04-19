package com.think.application.service.adapter;

import com.google.common.collect.Maps;
import com.think.application.service.spider.GetStationInfoSpider12306;
import com.think.infrastructure.common.constant.CMDConstant;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author hg
 * @date 2022-04-12日 17:38
 */
@Component
public class ApplicationAdapter {

    private Map<String, Object> serviceMap = Maps.newHashMap();
    {
        serviceMap.put(CMDConstant.STATION_CMD_12306,new GetStationInfoSpider12306());
    }

    public Object get(String key){
        return  serviceMap.get(key);
    }
}
