package com.think.domain.train.model;

import com.think.domain.station.model.StationAggregate;

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

    public static TrainInfoAggregate create(CreateTrainInfoCommand createTrainInfoCommand){

        TrainInfoAggregate trainInfoAggregate = new TrainInfoAggregate();

        return  trainInfoAggregate;
    }


}
