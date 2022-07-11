package com.think.domain.station.service;

import com.think.domain.common.TrainDataSource;
import com.think.domain.station.model.CreateStationCommand;
import com.think.domain.station.port.gateway.GetStationGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 获取车站信息领域服务（由产品定制层抽取共性下沉为领域能力）
 *
 * @author hg
 * @date 2022-04-01日 15:28
 */
@Service
public class GetStationDomainService {

    @Autowired
    GetStationGateway getStationGateway;



    public List<CreateStationCommand> getAllStationByDataSource(TrainDataSource trainDataSource) {
        List<CreateStationCommand> createStationCommands = getStationGateway.getAllStationByDataSource(trainDataSource);
        //通用处理数据
        return createStationCommands;
    }
}
