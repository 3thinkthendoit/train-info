package com.think.infrastructure.common.domain;


/**
 * @author hg
 * @date 2021-08-12日 18:02
 */
public class TrainDetailInfo {

    private String trainCode;

    private String stationName;

    private String stationNo;

    private String arriveTime;

    private String startTime;

    private String stopoverTime;

    private Boolean isEnabled;

    private String arriveDayStr;

    private String runningTime;

    private String arriveDayDiff;


    public String getArriveDayStr() {
        return arriveDayStr;
    }

    public void setArriveDayStr(String arriveDayStr) {
        this.arriveDayStr = arriveDayStr;
    }

    public String getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(String runningTime) {
        this.runningTime = runningTime;
    }

    public String getArriveDayDiff() {
        return arriveDayDiff;
    }

    public void setArriveDayDiff(String arriveDayDiff) {
        this.arriveDayDiff = arriveDayDiff;
    }

    public String getTrainCode() {
        return trainCode;
    }

    public void setTrainCode(String trainCode) {
        this.trainCode = trainCode;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getStationNo() {
        return stationNo;
    }

    public void setStationNo(String stationNo) {
        this.stationNo = stationNo;
    }

    public String getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(String arriveTime) {
        this.arriveTime = arriveTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStopoverTime() {
        return stopoverTime;
    }

    public void setStopoverTime(String stopoverTime) {
        this.stopoverTime = stopoverTime;
    }

    public Boolean getEnabled() {
        return isEnabled;
    }

    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }
}
