package com.think.domain.train.entity;

import com.think.domain.station.entity.StationEntity;

/**
 * @author hg
 * @date 2022-04-08日 16:57
 */
public class TrainInfoEntity {

    private String date;

    private String trainCode;

    private String trainNo;

    private StationEntity depart;

    private StationEntity destination;

    

    private String num;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTrainCode() {
        return trainCode;
    }

    public void setTrainCode(String trainCode) {
        this.trainCode = trainCode;
    }

    public String getTrainNo() {
        return trainNo;
    }

    public void setTrainNo(String trainNo) {
        this.trainNo = trainNo;
    }

    public StationEntity getDepart() {
        return depart;
    }

    public void setDepart(StationEntity depart) {
        this.depart = depart;
    }

    public StationEntity getDestination() {
        return destination;
    }

    public void setDestination(StationEntity destination) {
        this.destination = destination;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

}
