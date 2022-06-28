package com.think.domain.train.port.gateway;

import com.think.domain.common.TrainDataSource;
import com.think.domain.train.model.CreateTrainDetailCommand;
import com.think.domain.train.model.CreateTrainInfoCommand;
import java.util.List;

/**
 * @author hg
 * @date 2022-06-24日 14:51
 */
public interface GetTrainInfoGateway {

    /**
     *
     * @param trainDataSource
     * @return
     */
    public List<CreateTrainInfoCommand> getAllTrainListByDataSource(TrainDataSource trainDataSource);


    /**
     *
     * @param createTrainInfoCommand
     * @param trainDataSource
     * @return
     */
    public List<CreateTrainDetailCommand> getTrainDetail(CreateTrainInfoCommand createTrainInfoCommand, TrainDataSource trainDataSource);

}
