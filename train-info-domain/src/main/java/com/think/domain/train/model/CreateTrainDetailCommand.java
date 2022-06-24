package com.think.domain.train.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * @author hg
 * @date 2022-06-24日 16:57
 */
public class CreateTrainDetailCommand {

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

    public String getTrainCode() {
        return trainCode;
    }

    public void setTrainCode(String trainCode) {
        this.trainCode = trainCode;
    }

    public Integer getStationNo() {
        return stationNo;
    }

    public void setStationNo(Integer stationNo) {
        this.stationNo = stationNo;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
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

    public Integer getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(Integer isEnabled) {
        this.isEnabled = isEnabled;
    }

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

    public Integer getArriveDayDiff() {
        return arriveDayDiff;
    }

    public void setArriveDayDiff(Integer arriveDayDiff) {
        this.arriveDayDiff = arriveDayDiff;
    }
}
