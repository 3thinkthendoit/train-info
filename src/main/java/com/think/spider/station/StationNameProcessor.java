package com.think.spider.station;

import com.think.common.domain.StationNameInfo;
import com.think.spider.BaseProcessor;
import org.assertj.core.util.Lists;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;

import java.util.List;


/**
 * @author hg
 * @date 2021-08-04日 18:01
 */
public class StationNameProcessor extends BaseProcessor {

    /**
     * 车站信息提取
     * @param page
     */
    @Override
    public void process(Page page) {
        String result = page.getHtml().get();
        logger.info("原始数据:{}",result);
        result = result.replace(" ","");
        result = result.replace("\n","");
        //去掉多余字符
        result = result.replace("<html><head></head><body>varstation_names='","");
        result = result.replace("';</body></html>","");
        String [] stationInfoArr = result.split("@");
        StationNameInfo stationInfoBO = null;
        List<StationNameInfo> stationInfoList = Lists.newArrayList();
        for (int i = 1; i < stationInfoArr.length; i++) {
            stationInfoBO = new StationNameInfo();
            String [] info = stationInfoArr[i].split("\\|");
            stationInfoBO.setStationPf(info[0]);
            stationInfoBO.setStationName(info[1]);
            stationInfoBO.setStationCode(info[2]);
            stationInfoBO.setStationPj(info[3]);
            stationInfoBO.setStationNo(info[4]);
            stationInfoBO.setStationId(info[5]);
            stationInfoList.add(stationInfoBO);
        }
        page.putField("data",stationInfoList);
    }

    /**
     * site定制
     * @return
     */
    @Override
    public Site getSite() {
        return site;
    }
}
