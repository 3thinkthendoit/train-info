package com.think.domain.station.model;

/**
 * @author hg
 * @date 2022-06-23日 15:25
 */
public class CreateStationCommand {

    private String stationCode;

    //private StationId stationId;

    private String stationNo;

    private String stationName;

    private String stationPj;

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

//    public StationId getStationId() {
//        return stationId;
//    }
//
//    public void setStationId(StationId stationId) {
//        this.stationId = stationId;
//    }

    public String getStationNo() {
        return stationNo;
    }

    public void setStationNo(String stationNo) {
        this.stationNo = stationNo;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getStationPj() {
        return stationPj;
    }

    public void setStationPj(String stationPj) {
        this.stationPj = stationPj;
    }

    public String getStationPf() {
        return stationPf;
    }

    public void setStationPf(String stationPf) {
        this.stationPf = stationPf;
    }

    private String stationPf;


}
