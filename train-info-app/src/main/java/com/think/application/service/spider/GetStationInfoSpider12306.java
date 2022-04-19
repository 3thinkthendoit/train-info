package com.think.application.service.spider;

import com.google.common.collect.Maps;
import com.think.application.bo.StationBO;
import com.think.domain.station.entity.StationEntity;
import com.think.infrastructure.http.IHttpClient;
import com.think.infrastructure.http.IResponse;
import org.assertj.core.util.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Map;

/**
 *
 * @author hg
 * @date 2022-04-12日 17:10
 */
public class GetStationInfoSpider12306 implements SpiderService<StationBO> {


    Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * @return
     */
    @Override
    public List<StationBO> execute(IHttpClient iHttpClient, Map<String,Object> params) throws Exception {
        String url = "";
        IResponse response = iHttpClient.get(url, Maps.newHashMap());
        return handResult(response.getData().toString());
    }

    private List<StationBO> handResult(String result){
        logger.info("原始数据:{}",result);
        result = result.replace(" ","");
        result = result.replace("\n","");
        //去掉多余字符
        result = result.replace("<html><head></head><body>varstation_names='","");
        result = result.replace("';</body></html>","");
        String [] stationInfoArr = result.split("@");
        List<StationBO> stationList = Lists.newArrayList();
        StationBO station = null;
        for (int i = 1; i < stationInfoArr.length; i++) {
            station = new StationBO();
            String [] info = stationInfoArr[i].split("\\|");
            station.setStationPf(info[0]);
            station.setStationName(info[1]);
            station.setStationCode(info[2]);
            station.setStationPj(info[3]);
            station.setStationNo(info[4]);
            station.setStationId(Integer.valueOf(info[5]));
            stationList.add(station);
        }
        return stationList;
    }
}
