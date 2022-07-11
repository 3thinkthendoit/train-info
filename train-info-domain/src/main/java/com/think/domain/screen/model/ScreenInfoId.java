package com.think.domain.screen.model;

import java.util.Date;

public class ScreenInfoId {

    private String trainCode;

    private String stationCode;

    private Date trainDate;

    public ScreenInfoId(String trainCode,String stationCode,Date trainDate){
        this.trainCode = trainCode;
        this.stationCode = stationCode;
        this.trainDate = trainDate;
    }

    public String getTrainCode() {
        return trainCode;
    }

    public String getStationCode() {
        return stationCode;
    }

    public Date getTrainDate() {
        return trainDate;
    }

 
    
    
}
