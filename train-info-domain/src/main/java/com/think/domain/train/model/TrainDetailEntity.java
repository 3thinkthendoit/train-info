package com.think.domain.train.model;

/**
 * @author hg
 * @date 2022-06-28日 14:35
 */
public class TrainDetailEntity {

    private String trainCode;

    private Integer stationNo;

    private String stationName;

    private String arriveTime;

    private String startTime;

    private String stopoverTime;

    private Integer isEnabled;

    private String arriveDayStr;

    private String runningTime;

    private Integer arriveDayDiff;

    public static TrainDetailEntity create(CreateTrainDetailCommand createTrainDetailCommand){
        TrainDetailEntity trainDetail = new TrainDetailEntity();
        trainDetail.arriveDayDiff = createTrainDetailCommand.getArriveDayDiff();
        trainDetail.arriveDayStr  = createTrainDetailCommand.getArriveDayStr();
        trainDetail.arriveTime = createTrainDetailCommand.getArriveTime();
        trainDetail.isEnabled = 1;
        trainDetail.runningTime = createTrainDetailCommand.getRunningTime();
        trainDetail.startTime = createTrainDetailCommand.getStartTime();
        trainDetail.stationName = createTrainDetailCommand.getStationName();
        trainDetail.stationNo = createTrainDetailCommand.getStationNo();
        trainDetail.stopoverTime = createTrainDetailCommand.getStopoverTime();
        return  trainDetail;
    }
}
