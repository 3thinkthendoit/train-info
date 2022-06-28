package com.think.domain.train.model;


import org.assertj.core.util.Lists;

import java.util.List;

/**
 * @author hg
 * @date 2022-04-08日 16:57
 */
public class TrainInfoAggregate {

    private TrainId trainId;

    private String trainNo;

    private String depart;

    private String destination;

    private String num;

    private Byte state;

    private List<TrainDetailEntity> trainDetailList = Lists.newArrayList();

    public static TrainInfoAggregate create(CreateTrainInfoCommand createTrainInfoCommand) throws Exception {
        TrainInfoAggregate trainInfoAggregate = new TrainInfoAggregate();
        trainInfoAggregate.trainNo = createTrainInfoCommand.getTrainNo();
        trainInfoAggregate.depart = createTrainInfoCommand.getDepart();
        trainInfoAggregate.destination = createTrainInfoCommand.getDestination();
        trainInfoAggregate.num = createTrainInfoCommand.getNum();
        trainInfoAggregate.trainId = new TrainId(null,createTrainInfoCommand.getDate(),
                createTrainInfoCommand.getTrainCode());
        List<CreateTrainDetailCommand> list = createTrainInfoCommand.getCreateTrainDetailCommandList();
        if(list.size() ==0){
            throw new Exception("CreateTrainDetailCommand list size is 0!");
        }
        list.forEach(createTrainDetailCommand -> {
            trainInfoAggregate.trainDetailList.add(TrainDetailEntity.create(createTrainDetailCommand));
        });
        return  trainInfoAggregate;
    }


}
