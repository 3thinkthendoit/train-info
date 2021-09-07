package com.think.spider.train;

import com.google.common.base.Stopwatch;
import com.google.common.collect.Maps;
import com.think.common.domain.SpiderTaskContext;
import com.think.common.domain.TaskContext;
import com.think.common.domain.TrainListInfo;
import com.think.db.entity.TrainInfoEntity;
import com.think.service.task.TaskService;
import com.think.service.task.TrainInfoTaskService;
import com.think.util.SpringContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * @author hg
 * @date 2021-08-11日 17:37
 */
public class TrainListPipeline implements Pipeline {

    Logger logger = LoggerFactory.getLogger(getClass());

    private SpiderTaskContext spiderTaskContext;

    public TrainListPipeline(SpiderTaskContext spiderTaskContext){
        this.spiderTaskContext = spiderTaskContext;
    }

    private Date parseDate(String dateTxt){
       try {
           SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
           return  formatter.parse(dateTxt);
       }catch (Exception e){
           return null;
       }
    }

    @Override
    public void process(ResultItems resultItems, Task task) {
        Stopwatch sw = Stopwatch.createStarted();
        try {
            List<TrainListInfo> list = resultItems.get("data");
            List<TrainInfoEntity> trainList = new ArrayList<>(list.size());
            TrainInfoEntity trainInfoEntity = null;
            TrainListInfo trainListInfo = null;
            for (int i = 0; i < list.size(); i++) {
                trainListInfo = list.get(i);
                trainInfoEntity = new TrainInfoEntity();
                trainInfoEntity.setDepart(trainListInfo.getDepart());
                trainInfoEntity.setDestination(trainListInfo.getDestination());
                trainInfoEntity.setTrainNo(trainListInfo.getTrainNo());
                trainInfoEntity.setTrainCode(trainListInfo.getTrainCode());
                trainInfoEntity.setState((byte)0);
                trainInfoEntity.setTrainDate(parseDate(trainListInfo.getDate()));
                trainList.add(trainInfoEntity);
            }
            Map<String,Object> params = Maps.newHashMap();
            spiderTaskContext.getData().put("list",trainList);
            spiderTaskContext.getTaskService().afterTask(spiderTaskContext);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }finally {
        }
        sw.stop();
        logger.info("save train_info list spend {}",sw.toString());
    }


}
