package com.think.application.local.spider;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.think.common.SpiderService;
import com.think.infrastructure.http.IHttpClient;

/**
 * 腾讯出行-车站大屏爬虫
 * "WaitScreen": [
            {
                "ActualLeaveTime": "1900-01-01 00:00:00",
                "CheckState": "",
                "CheckingPort": "",
                "EndStationName": "昆明",
                "PlanLeaveTime": "1900-01-01 00:00:00",
                "TrainCode": "K1208"
            },
   ]    

 * 
 */

public class GetScreenInfoSpiderTencent extends SpiderService<JSONArray>{

    
    @Override
    public List<JSONArray> execute(IHttpClient iHttpClient, Map<String, Object> params) throws Exception {
       
        return null;
    }
    
}
