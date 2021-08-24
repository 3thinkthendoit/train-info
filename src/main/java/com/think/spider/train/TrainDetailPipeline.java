package com.think.spider.train;

import com.google.common.collect.Maps;
import com.think.common.domain.TrainDetailInfo;
import com.think.db.entity.TrainDetailEntity;
import com.think.service.train.TrainDetailService;
import com.think.util.SpringContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * @author hg
 * @date 2021-08-12日 11:53
 */
public class TrainDetailPipeline implements Pipeline {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    private TrainDetailService trainDetailService = SpringContextUtil.getBean(TrainDetailService.class);

    private CountDownLatch countDownLatch;

    public TrainDetailPipeline(CountDownLatch countDownLatch){
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void process(ResultItems resultItems, Task task) {
       try {
           List<TrainDetailInfo> list = resultItems.get("data");
           if(list ==null || list.isEmpty()){
               return;
           }
           String trainCode = resultItems.get("station_train_code");
           String trainClass= resultItems.get("train_class_name");
           String serviceType = resultItems.get("service_type");
           List<TrainDetailEntity> trainDetailList = new ArrayList<TrainDetailEntity>(list.size());
           TrainDetailEntity trainDetailEntity= null;
           TrainDetailInfo tInfo = null;
           for (int i = 0; i < list.size(); i++) {
               trainDetailEntity = new TrainDetailEntity();
               tInfo = list.get(i);
               trainDetailEntity.setArriveTime(tInfo.getArriveTime());
               trainDetailEntity.setIsEnabled((byte)0);
               trainDetailEntity.setStartTime(tInfo.getStartTime());
               trainDetailEntity.setStationName(tInfo.getStationName());
               trainDetailEntity.setStationNo(Integer.valueOf(tInfo.getStationNo()).byteValue());
               trainDetailEntity.setStopoverTime(tInfo.getStopoverTime());
               trainDetailEntity.setTrainCode(trainCode);
               trainDetailEntity.setRunningTime(tInfo.getRunningTime());
               trainDetailEntity.setArriveDayStr(tInfo.getArriveDayStr());
               trainDetailEntity.setArriveDayDiff(Byte.valueOf(tInfo.getArriveDayDiff()));
               trainDetailList.add(trainDetailEntity);
           }
           Map<String,Object> map = Maps.newHashMap();
           map.put("trainClass",trainClass);
           map.put("serviceType",serviceType);
           map.put("trainCode",trainCode);
           trainDetailService.handleTrainDetail(trainDetailList,map);
       }catch (Exception e){
           logger.error(e.getMessage(),e);
       }finally {
           countDownLatch.countDown();
       }
    }

}
