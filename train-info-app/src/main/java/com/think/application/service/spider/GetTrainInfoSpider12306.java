package com.think.application.service.spider;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.think.domain.station.entity.StationEntity;
import com.think.domain.train.entity.TrainInfoEntity;
import com.think.infrastructure.common.domain.TrainListInfo;
import com.think.infrastructure.http.IHttpClient;
import com.think.infrastructure.http.IResponse;
import org.assertj.core.util.Lists;

import java.util.List;
import java.util.Map;

/**
 * @author hg
 * @date 2022-04-14日 15:50
 */
public class GetTrainInfoSpider12306 implements SpiderService<TrainInfoEntity> {



    @Override
    public List<TrainInfoEntity> execute(IHttpClient iHttpClient, Map<String, Object> params) throws Exception {
        String url = "";
        IResponse response = iHttpClient.get(url,null);
        String result = response.getData().toString();
        result = result.replace(" ","");
        result = result.replace("\n","");
        result = result.replace("<html><head></head><body>","");
        result = result.replace("</body></html>","");
        JSONObject json = JSONObject.parseObject(result);
        TrainInfoEntity trainListInfo = null;
        List<TrainInfoEntity> list = Lists.newArrayList();
        JSONArray arr = json.getJSONArray("data");
        for (int i = 0; i < arr.size() ; i++) {
            trainListInfo = new TrainInfoEntity();
            JSONObject train = arr.getJSONObject(i);
            trainListInfo.setDate(train.getString("date"));
            trainListInfo.setDestination(new StationEntity(train.getString("to_station")));
            trainListInfo.setDepart(new StationEntity(train.getString("from_station")));
            trainListInfo.setNum(train.getString("total_num"));
            trainListInfo.setTrainCode(train.getString("station_train_code"));
            trainListInfo.setTrainNo(train.getString("train_no"));
            list.add(trainListInfo);
        }
        return list;
    }
}
