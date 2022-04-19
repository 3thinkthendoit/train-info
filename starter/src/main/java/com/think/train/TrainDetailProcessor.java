package com.think.train;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.think.infrastructure.common.domain.SpiderTaskContext;
import com.think.infrastructure.common.domain.TrainDetailInfo;
import com.think.spider.BaseProcessor;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hg
 * @date 2021-08-12日 11:53
 */
public class TrainDetailProcessor extends BaseProcessor {

    private SpiderTaskContext spiderTaskContext;

    public TrainDetailProcessor(SpiderTaskContext context){
        this.spiderTaskContext = context;
    }

    @Override
    public void process(Page page) {
       try{
           String result = page.getRawText();
           result = result.replace(" ","");
           result = result.replace("\n","");
           JSONObject json = null;
           json = JSONObject.parseObject(result);
           if(!json.getBoolean("status")){
               return;
           }
           JSONArray arr = json.getJSONObject("data").getJSONArray("data");
           TrainDetailInfo detailInfo = null;
           List<TrainDetailInfo> list = new ArrayList<TrainDetailInfo>(arr.size());
           JSONObject trainJson = null;
           for (int i = 0; i < arr.size(); i++) {
               detailInfo = new TrainDetailInfo();
               trainJson = arr.getJSONObject(i);
               detailInfo.setArriveTime(trainJson.getString("arrive_time"));
               detailInfo.setStartTime(trainJson.getString("start_time"));
               detailInfo.setStationName(trainJson.getString("station_name"));
               detailInfo.setStationNo(trainJson.getString("station_no"));
               detailInfo.setArriveDayDiff(trainJson.getString("arrive_day_diff"));
               detailInfo.setArriveDayStr(trainJson.getString("arrive_day_str"));
               detailInfo.setRunningTime(trainJson.getString("running_time"));
               detailInfo.setTrainCode(arr.getJSONObject(0).getString("station_train_code"));
               list.add(detailInfo);
           }
           page.putField("data",list);
           if(arr.isEmpty()) {return; }
           page.putField("train_class_name",arr.getJSONObject(0).getString("train_class_name"));
           page.putField("service_type",arr.getJSONObject(0).getString("service_type"));
           page.putField("station_train_code",arr.getJSONObject(0).getString("station_train_code"));
       }catch (Exception e){
            logger.error(e.getMessage(),e);
            //spiderTaskContext.getTaskService().makeup(spiderTaskContext.getTaskInfo().getObject());
       }
    }

    @Override
    public Site getSite() {
        return site;
    }
}
