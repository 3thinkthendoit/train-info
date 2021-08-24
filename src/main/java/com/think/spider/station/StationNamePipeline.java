package com.think.spider.station;

import com.alibaba.fastjson.JSONObject;
import com.think.db.entity.StationInfoEntity;
import com.think.common.domain.StationNameInfo;
import com.think.service.station.StationInfoService;
import com.think.util.SpringContextUtil;
import org.assertj.core.util.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import java.util.List;


/**
 * @author hg
 * @date 2021-08-05日 18:07
 */
public class StationNamePipeline implements Pipeline {

    Logger logger = LoggerFactory.getLogger(getClass());

    private StationInfoService stationInfoService = SpringContextUtil.getBean(StationInfoService.class);

    @Override
    public void process(ResultItems resultItems, Task task) {
        List<StationNameInfo> stationInfoList = resultItems.get("data");
        logger.info("stationPipeline：{}", JSONObject.toJSON(stationInfoList));
        List<StationInfoEntity> stationList = Lists.newArrayList();
        StationInfoEntity station = null;
        StationNameInfo stationInfoBO = null;
        for (int i = 0; i < stationInfoList.size(); i++) {
            station = new StationInfoEntity();
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
            stationInfoService.saveStationList(stationList);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
    }

}
