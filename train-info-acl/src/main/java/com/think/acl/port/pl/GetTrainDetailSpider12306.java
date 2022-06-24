package com.think.acl.port.pl;

import com.think.domain.train.model.CreateTrainDetailCommand;
import com.think.infrastructure.http.IHttpClient;

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
        return null;
    }
}
