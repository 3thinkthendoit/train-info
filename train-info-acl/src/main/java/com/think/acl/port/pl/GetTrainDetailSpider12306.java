package com.think.acl.port.pl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.think.domain.train.model.CreateTrainDetailCommand;
import com.think.infrastructure.http.IHttpClient;
import org.assertj.core.util.Lists;
import java.util.List;
import java.util.Map;

/**
 * @author hg
 * @date 2022-06-24日 16:50
 */
public class GetTrainDetailSpider12306 implements SpiderService<CreateTrainDetailCommand>{

    String url = "https://kyfw.12306.cn/otn/queryTrainInfo/query?leftTicketDTO.train_no=$trainNo&leftTicketDTO.train_date=$date&rand_code=";


    /**
     * http请求
     *
     * @param iHttpClient
     * @param params
     * @return
     * @throws Exception
     */
    @Override
    public List<CreateTrainDetailCommand> execute(IHttpClient iHttpClient, Map<String, Object> params) throws Exception {
        List<CreateTrainDetailCommand> list = Lists.newArrayList();
        String result = iHttpClient.get(url, Maps.newHashMap()).getData().toString();
        result = result.replace(" ","");
        result = result.replace("\n","");
        JSONObject json = null;
        json = JSONObject.parseObject(result);
        if(!json.getBoolean("status")){
            return list;
        }
        JSONArray arr = json.getJSONObject("data").getJSONArray("data");
        CreateTrainDetailCommand detailInfo = null;
        JSONObject trainJson = null;
        for (int i = 0; i < arr.size(); i++) {
            detailInfo = new CreateTrainDetailCommand();
            trainJson = arr.getJSONObject(i);
            detailInfo.setArriveTime(trainJson.getString("arrive_time"));
            detailInfo.setStartTime(trainJson.getString("start_time"));
            detailInfo.setStationName(trainJson.getString("station_name"));
            detailInfo.setStationNo(Integer.valueOf(trainJson.getString("station_no")));
            detailInfo.setArriveDayDiff(Integer.valueOf(trainJson.getString("arrive_day_diff")));
            detailInfo.setArriveDayStr(trainJson.getString("arrive_day_str"));
            detailInfo.setRunningTime(trainJson.getString("running_time"));
            detailInfo.setTrainCode(arr.getJSONObject(0).getString("station_train_code"));
            list.add(detailInfo);
        }
        return list;
    }
}
