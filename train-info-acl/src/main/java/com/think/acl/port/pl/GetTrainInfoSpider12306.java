package com.think.acl.port.pl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.think.common.SpiderService;
import com.think.domain.train.model.CreateTrainInfoCommand;
import com.think.infrastructure.http.IHttpClient;
import com.think.infrastructure.http.IResponse;
import org.assertj.core.util.Lists;
import java.util.List;
import java.util.Map;

/**
 * @author hg
 * @date 2022-04-14日 15:50
 */
public class GetTrainInfoSpider12306 extends SpiderService<CreateTrainInfoCommand> {

    String url = "https://search.12306.cn/search/v1/train/search?keyword=";

    @Override
    public List<CreateTrainInfoCommand> execute(IHttpClient iHttpClient, Map<String, Object> params) throws Exception {
        IResponse response = iHttpClient.get(url,null);
        String result = response.getData().toString();
        result = result.replace(" ","");
        result = result.replace("\n","");
        result = result.replace("<html><head></head><body>","");
        result = result.replace("</body></html>","");
        JSONObject json = JSONObject.parseObject(result);
        CreateTrainInfoCommand trainInfoCommand = null;
        List<CreateTrainInfoCommand> list = Lists.newArrayList();
        JSONArray arr = json.getJSONArray("data");
        for (int i = 0; i < arr.size() ; i++) {
            trainInfoCommand = new CreateTrainInfoCommand();
            JSONObject train = arr.getJSONObject(i);
            trainInfoCommand.setDate(train.getString("date"));
            trainInfoCommand.setDestination(train.getString("to_station"));
            trainInfoCommand.setDepart(train.getString("from_station"));
            trainInfoCommand.setNum(train.getString("total_num"));
            trainInfoCommand.setTrainCode(train.getString("station_train_code"));
            trainInfoCommand.setTrainNo(train.getString("train_no"));
            list.add(trainInfoCommand);
        }
        return list;
    }
}
