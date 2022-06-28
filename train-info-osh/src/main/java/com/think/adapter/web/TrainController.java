package com.think.adapter.web;

import com.think.application.service.station.StationAppService;
import com.think.application.service.train.TrainAppService;
import com.think.common.util.R;
import com.think.domain.common.TrainDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author hg
 * @date 2021-08-04日 18:03
 */
@RestController
@RequestMapping("/train")
public class TrainController {


    protected Logger logger = LoggerFactory.getLogger(getClass());


    @Autowired
    StationAppService stationAppService;
    @Autowired
    TrainAppService trainAppService;

    @RequestMapping("/getStationInfo/12306")
    public R getAllStationInfo(){
        stationAppService.getAllStation(TrainDataSource.D12306);
        return R.ok("getStationTask is running......");
    }

    @RequestMapping("/getAllTrainInfo/12306")
    public R getAllTrainInfo(){
        trainAppService.executeGetAllStation(TrainDataSource.D12306);
        return R.ok("getTrainInfoTask is running......");
    }
}
