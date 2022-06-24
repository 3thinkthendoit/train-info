package com.think.train;

import com.think.common.domain.SpiderTaskContext;
import com.think.common.domain.TrainDetailInfo;
import com.think.infrastructure.mybatis.po.TrainDetailPO;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hg
 * @date 2021-08-12日 11:53
 */
public class TrainDetailPipeline implements Pipeline {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    private SpiderTaskContext spiderTaskContext;

    public TrainDetailPipeline(SpiderTaskContext context){
        this.spiderTaskContext = context;
    }

    @Override
    public void process(ResultItems resultItems, Task task) {
       try {
           List<TrainDetailInfo> list = resultItems.get("data");
           if(CollectionUtils.isEmpty(list)){
               return;
           }
           String trainCode = resultItems.get("station_train_code");
           String trainClass= resultItems.get("train_class_name");
           String serviceType = resultItems.get("service_type");
           List<TrainDetailPO> trainDetailList = new ArrayList<TrainDetailPO>(list.size());
           TrainDetailPO trainDetailEntity= null;
           TrainDetailInfo tInfo = null;
           for (int i = 0; i < list.size(); i++) {
               trainDetailEntity = new TrainDetailPO();
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
           spiderTaskContext.getData().put("trainClass",trainClass);
           spiderTaskContext.getData().put("serviceType",serviceType);
           spiderTaskContext.getData().put("trainCode",trainCode);
           spiderTaskContext.getData().put("list",trainDetailList);
           spiderTaskContext.getTaskService().afterTask(spiderTaskContext);
       }catch (Exception e){
           logger.error(e.getMessage(),e);
       }
    }

}
