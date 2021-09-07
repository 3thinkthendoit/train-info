package com.think.web;

import com.think.util.R;
import com.think.service.task.StationTaskService;
import com.think.service.task.TrainDetailTaskService;
import com.think.service.task.TrainInfoTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author hg
 * @date 2021-08-04日 18:03
 */
@RestController
@RequestMapping("/train")
public class TrainController {

    private final String PASSWORD = "888888";

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    StationTaskService stationTaskService;
    @Autowired
    TrainInfoTaskService trainService;
    @Autowired
    TrainDetailTaskService trainDetailTaskService;

    @RequestMapping("/getStationInfo")
    public R getAllStationInfo(){
        stationTaskService.runTask();
        return R.ok("getStationTask is running......");
    }

    @RequestMapping("/getAllTrainInfo")
    public R getAllTrainInfo(){
        trainService.runTask();
        return R.ok("getTrainInfoTask is running......");
    }

    @RequestMapping("/getAllTrainDetailInfo")
    public R getAllTrainDetailInfo(){
        trainDetailTaskService.getAllTrainDetailInfo();
        return R.ok("getTrainDetailInfoTask is running......");
    }

    @RequestMapping("/test")
    public R test(HttpServletRequest request){
        return R.ok("test");
    }
}
