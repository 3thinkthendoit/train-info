package com.think.domain.station.port.gateway;

import com.think.domain.common.TrainDataSource;
import com.think.domain.station.model.CreateStationCommand;
import java.util.List;


/**
 * 获取领域外部信息-南向网关
 * @author hg
 * @date 2022-06-24日 11:22
 */
public interface GetStationGateway {

    /**
     *
     * @param trainDataSource
     * @return
     */
    public List<CreateStationCommand> getAllStationByDataSource(TrainDataSource trainDataSource);

}
