package com.think.acl.port.gateway;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.think.acl.port.pl.GetStationInfoSpider12306;
import com.think.domain.common.TrainDataSource;
import com.think.domain.station.model.CreateStationCommand;
import com.think.domain.station.port.gateway.GetStationGateway;
import com.think.infrastructure.http.IHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 *  获取外部数据 port 南向网关实现
 * @author hg
 * @date 2022-06-24日 11:24
 */
@Service
public class GetStationGatewayImpl implements GetStationGateway {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    IHttpClient iHttpClient;

    /**
     * @param trainDataSource
     * @return
     */
    @Override
    public List<CreateStationCommand> getAllStationByDataSource(TrainDataSource trainDataSource) {
        List<CreateStationCommand> list = Lists.newArrayList();
        try {
            if(trainDataSource == TrainDataSource.D12306){
                GetStationInfoSpider12306 getStationInfoSpider12306 = new GetStationInfoSpider12306();
                list = getStationInfoSpider12306.execute(iHttpClient, Maps.newHashMap());
            }
        }catch (Exception ex){
            logger.error("GetStationInfoSpider12306 execute error :"+ex.getMessage(),ex);
        }
        return list;
    }

}
