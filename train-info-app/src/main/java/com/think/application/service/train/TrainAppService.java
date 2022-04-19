package com.think.application.service.train;

import com.think.adapter.dto.cmd.GetAllStationCmd;
import com.think.adapter.dto.cmd.GetAllTrainInfoCmd;
import com.think.application.service.adapter.ApplicationAdapter;
import com.think.infrastructure.http.IHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author hg
 * @date 2022-04-14日 15:41
 */
@Service
public class TrainAppService {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    IHttpClient iHttpClient;
    @Autowired
    ApplicationAdapter applicationAdapter;


    @Async
    public void executeGetAllStation(GetAllTrainInfoCmd cmd){

    }
}
