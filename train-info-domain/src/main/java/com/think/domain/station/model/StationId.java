package com.think.domain.station.model;


/**
 *
 * @author hg
 * @date 2022-06-23日 15:16
 */
public class StationId {

    private String stationCode;

    private Integer sid;

    public StationId(Integer sid,String stationCode) throws Exception{
        //领域校验
        if(stationCode == null && sid ==null){
            throw new Exception("stationCode and sid both null !");
        }
        this.stationCode = stationCode;
        this.sid = sid;
    }

    public String getStationCode() {
        return stationCode;
    }

    public Integer getSid() {
        return sid;
    }
}
