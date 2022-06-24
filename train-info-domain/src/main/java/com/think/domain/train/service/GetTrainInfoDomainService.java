package com.think.domain.train.service;

import com.think.domain.common.TrainDataSource;
import com.think.domain.train.model.CreateTrainDetailCommand;
import com.think.domain.train.model.CreateTrainInfoCommand;
import com.think.domain.train.port.gateway.GetTrainInfoGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 获取车次详细领域服务
 *
 * @author hg
 * @date 2022-04-01日 15:30
 */
@Service
public class GetTrainInfoDomainService {

    @Autowired
    GetTrainInfoGateway getTrainInfoGateway;

    public List<CreateTrainInfoCommand> getAllStationByDataSource(TrainDataSource trainDataSource) {
        List<CreateTrainInfoCommand> createTrainInfoCommands = getTrainInfoGateway.getAllTrainListByDataSource(trainDataSource);
        //通用处理数据
        return createTrainInfoCommands;
    }

    public CreateTrainInfoCommand getTrainDetail(CreateTrainInfoCommand createTrainInfoCommand, TrainDataSource trainDataSource){
        List<CreateTrainDetailCommand> list = getTrainInfoGateway.getTrainDetail(createTrainInfoCommand,trainDataSource);
        createTrainInfoCommand.setCreateTrainDetailCommandList(list);
        return createTrainInfoCommand;
    }

}
