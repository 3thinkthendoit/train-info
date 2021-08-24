package com.think.web;

import com.think.common.exception.TException;
import com.think.common.domain.R;
import com.think.service.station.StationInfoService;
import com.think.service.train.TrainDetailService;
import com.think.service.train.TrainInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

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
    StationInfoService stationInfoService;
    @Autowired
    TrainInfoService trainService;
    @Autowired
    TrainDetailService trainDetailService;

    @RequestMapping("/getStationInfo")
    public R getStationInfo(@RequestParam String k){
        if(!PASSWORD.equalsIgnoreCase(k)){
            throw new TException("k is error!");
        }
        stationInfoService.getStationInfo();
        return R.ok("getStationTask is running......");
    }

    @RequestMapping("/getTrainInfo")
    public R getTrainInfo(@RequestParam String k){
        if(!PASSWORD.equalsIgnoreCase(k)){
            throw new TException("k is error!");
        }
        trainService.getTrainInfo();
        return R.ok("getTrainInfoTask is running......");
    }

    @RequestMapping("/getTrainDetailInfo")
    public R getTrainDetailInfo(@RequestParam String k){
        if(!PASSWORD.equalsIgnoreCase(k)){
            throw new TException("k is error!");
        }
        trainDetailService.getTrainDetail();
        return R.ok("getTrainDetailInfoTask is running......");
    }

    @RequestMapping("/test")
    public R test(HttpServletRequest request){
        Enumeration<String> names =  request.getHeaderNames();
       while (names.hasMoreElements()){
           String h = names.nextElement();
           logger.info("name:{},value:{}",h,request.getHeader(h));
       }
        return R.ok("test");
    }
}
