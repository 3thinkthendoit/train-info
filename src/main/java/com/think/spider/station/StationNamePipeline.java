package com.think.spider.station;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.think.infrastructure.common.domain.SpiderTaskContext;
import com.think.infrastructure.mybits.po.StationInfoPO;
import com.think.infrastructure.common.domain.StationNameInfo;
import org.assertj.core.util.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import java.util.List;
import java.util.Map;



/**
 * @author hg
 * @date 2021-08-05日 18:07
 */
public class StationNamePipeline implements Pipeline {

    Logger logger = LoggerFactory.getLogger(getClass());

    private SpiderTaskContext spiderTaskContext;

    public StationNamePipeline(SpiderTaskContext spiderTaskContext){
        this.spiderTaskContext = spiderTaskContext;
    }

    @Override
    public void process(ResultItems resultItems, Task task) {
        List<StationNameInfo> stationInfoList = resultItems.get("data");
        logger.info("stationPipeline：{}", JSONObject.toJSON(stationInfoList));
        List<StationInfoPO> stationList = Lists.newArrayList();
        StationInfoPO station = null;
        StationNameInfo stationInfoBO = null;
        for (int i = 0; i < stationInfoList.size(); i++) {
            station = new StationInfoPO();
            stationInfoBO = stationInfoList.get(i);
            station.setStationCode(stationInfoBO.getStationCode());
            station.setStationId(Integer.valueOf(stationInfoBO.getStationId()));
            station.setStationName(stationInfoBO.getStationName());
            station.setStationPf(stationInfoBO.getStationPf());
            station.setStationPj(stationInfoBO.getStationPj());
            station.setStationNo(stationInfoBO.getStationNo());
            station.setState((byte)0);
            stationList.add(station);
        }
        try {
            Map<String,Object> params = Maps.newHashMap();
            spiderTaskContext.getData().put("list",stationList);
            spiderTaskContext.getTaskService().afterTask(spiderTaskContext);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
    }

}
