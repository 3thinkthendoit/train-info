package com.think.application.service.train;

import com.think.domain.common.TrainDataSource;
import com.think.domain.train.model.CreateTrainInfoCommand;
import com.think.domain.train.model.TrainInfoAggregate;
import com.think.domain.train.service.GetTrainInfoDomainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 应用服务
 * @author hg
 * @date 2022-04-14日 15:41
 */
@Service
public class TrainAppService {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    GetTrainInfoDomainService getTrainInfoDomainService;

    ExecutorService cachedThreadPool = Executors.newFixedThreadPool(20);

    /**
     * 通用用例-批量保存车次信息
     * @param trainDataSource
     */
    @Async
    public void executeGetAllStation(TrainDataSource trainDataSource){
        List<CreateTrainInfoCommand> createTrainInfoCommands = getTrainInfoDomainService.getAllTrainInfoByDataSource(trainDataSource);
        createTrainInfoCommands.forEach(cmd->{
            cachedThreadPool.submit(new Runnable() {
                @Override
                public void run() {
                    getTrainInfo(trainDataSource,cmd);
                }
            });
        });
    }

    private void getTrainInfo(TrainDataSource trainDataSource,CreateTrainInfoCommand createTrainInfoCommand){
        try{
            CreateTrainInfoCommand command = getTrainInfoDomainService.getTrainDetail(createTrainInfoCommand,trainDataSource);
            TrainInfoAggregate trainInfoAggregate = TrainInfoAggregate.create(command);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
    }
}
