package com.think.acl.port.gateway;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.think.acl.port.pl.GetTrainDetailSpider12306;
import com.think.acl.port.pl.GetTrainInfoSpider12306;
import com.think.domain.common.TrainDataSource;
import com.think.domain.train.model.CreateTrainDetailCommand;
import com.think.domain.train.model.CreateTrainInfoCommand;
import com.think.domain.train.port.gateway.GetTrainInfoGateway;
import com.think.infrastructure.http.IHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author hg
 * @date 2022-06-24日 16:07
 */
@Service
public class GetTrainInfoGatewayImpl implements GetTrainInfoGateway {

    private Logger logger = LoggerFactory.getLogger(getClass());

    List<String> keywords = Stream.of("1","2","3","5","6","7","8","9","C","D","S","G","K","T","Y","Z").collect(Collectors.toList());

    @Autowired
    IHttpClient iHttpClient;

    /**
     * @param trainDataSource
     * @return
     */
    @Override
    public List<CreateTrainInfoCommand> getAllTrainListByDataSource(TrainDataSource trainDataSource) {
        List<CreateTrainInfoCommand> list = Lists.newArrayList();
        try {
            if(trainDataSource == TrainDataSource.D12306){
                GetTrainInfoSpider12306 getTrainInfoSpider12306 = new GetTrainInfoSpider12306();
                for (int i = 0; i < keywords.size(); i++) {
                     getTrainInfoSpider12306.execute(iHttpClient, Maps.newHashMap());
                }
            }
        }catch (Exception ex){
            logger.error("GetStationInfoSpider12306 execute error :"+ex.getMessage(),ex);
        }
        return list;
    }

    /**
     * @param createTrainInfoCommand
     * @param trainDataSource
     * @return
     */
    @Override
    public List<CreateTrainDetailCommand> getTrainDetail(CreateTrainInfoCommand createTrainInfoCommand, TrainDataSource trainDataSource) {
        List<CreateTrainDetailCommand> createTrainDetailCommands = Lists.newArrayList();
        try {
            if(trainDataSource == TrainDataSource.D12306){
                GetTrainDetailSpider12306 getTrainDetailSpider12306 = new GetTrainDetailSpider12306();
                createTrainDetailCommands = getTrainDetailSpider12306.execute(iHttpClient, Maps.newHashMap());
            }
        }catch (Exception ex){
            logger.error("GetStationInfoSpider12306 execute error :"+ex.getMessage(),ex);
        }
        return createTrainDetailCommands;
    }
}
