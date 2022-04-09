package com.think.infrastructure.mybits.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author hg
 * @date 2021-08-12日 17:50
 */
@TableName("train_detail")
public class TrainDetailPO {

    @TableId(value = "t_id", type = IdType.AUTO)
    private Long tId;

    @TableField("train_code")
    private String trainCode;

    @TableField("station_no")
    private Byte stationNo;

    @TableField("station_name")
    private String stationName;

    @TableField("arrive_time")
    private String arriveTime;

    @TableField("start_time")
    private String startTime;

    @TableField("stopover_time")
    private String stopoverTime;

    @TableField("is_enabled")
    private Byte isEnabled;

    @TableField("arrive_day_str")
    private String arriveDayStr;

    @TableField("running_time")
    private String runningTime;

    @TableField("arrive_day_diff")
    private Byte arriveDayDiff;

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

    public Byte getArriveDayDiff() {
        return arriveDayDiff;
    }

    public void setArriveDayDiff(Byte arriveDayDiff) {
        this.arriveDayDiff = arriveDayDiff;
    }

    public Long gettId() {
        return tId;
    }

    public void settId(Long tId) {
        this.tId = tId;
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

    public Byte getStationNo() {
        return stationNo;
    }

    public void setStationNo(Byte stationNo) {
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

    public Byte getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(Byte isEnabled) {
        this.isEnabled = isEnabled;
    }
}
