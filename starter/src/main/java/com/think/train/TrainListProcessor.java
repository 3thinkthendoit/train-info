package com.think.train;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.think.infrastructure.common.domain.SpiderTaskContext;
import com.think.infrastructure.common.domain.TrainListInfo;
import com.think.spider.BaseProcessor;
import org.assertj.core.util.Lists;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import java.util.List;

/**
 * @author hg
 * @date 2021-08-11日 11:50
 */
public class TrainListProcessor extends BaseProcessor {

    SpiderTaskContext spiderTaskContext;

    public TrainListProcessor(SpiderTaskContext spiderTaskContext){
        this.spiderTaskContext = spiderTaskContext;
    }

    @Override
    public void process(Page page) {
        String result = page.getHtml().get();
        result = result.replace(" ","");
        result = result.replace("\n","");
        result = result.replace("<html><head></head><body>","");
        result = result.replace("</body></html>","");
        JSONObject json = JSONObject.parseObject(result);
        TrainListInfo trainListInfo = null;
        List<TrainListInfo> list = Lists.newArrayList();
        JSONArray arr = json.getJSONArray("data");
        for (int i = 0; i < arr.size() ; i++) {
            trainListInfo = new TrainListInfo();
            JSONObject train = arr.getJSONObject(i);
            trainListInfo.setDate(train.getString("date"));
            trainListInfo.setDestination(train.getString("to_station"));
            trainListInfo.setDepart(train.getString("from_station"));
            trainListInfo.setNum(train.getString("total_num"));
            trainListInfo.setTrainCode(train.getString("station_train_code"));
            trainListInfo.setTrainNo(train.getString("train_no"));
            list.add(trainListInfo);
        }
        page.putField("data",list);
    }

    @Override
    public Site getSite() {
        return site;
    }
}
