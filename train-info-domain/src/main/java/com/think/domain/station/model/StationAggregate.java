package com.think.domain.station.model;

import com.think.domain.city.model.CityEntity;

/**
 * 车站信息聚合根
 * @author hg
 * @date 2022-03-31日 11:42
 */
public class StationAggregate {

    /**
     * 车站ID
     */
    private StationId stationId;

    private String stationNo;

    private String stationName;

    private String stationPj;

    private String stationPf;

    private Double longitude;

    private Double latitude;

    /**
     * 归属城市
     */
    private CityEntity city;


    public StationId getStationId() {
        return stationId;
    }

    public String getStationNo() {
        return stationNo;
    }

    public String getStationName() {
        return stationName;
    }

    public String getStationPj() {
        return stationPj;
    }

    public String getStationPf() {
        return stationPf;
    }

    public CityEntity getCity() {
        return city;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    /**
     *  领域能力-设置经纬度
     */
    public void setLongitudeAndLatitude(){
        this.longitude = 0d;
        this.latitude = 0d;
    }

    /**
     *  领域能力-设置城市归属
     */
    public void setCity(){
        this.city = null;
    }

    /**
     * 工厂创建聚合根
     * @param cmd
     * @return
     */
    public static StationAggregate create(CreateStationCommand cmd) throws Exception {
        //领域校验
        StationAggregate stationAggregate = new StationAggregate();
        stationAggregate.stationId = new StationId(null,cmd.getStationCode());
        stationAggregate.stationNo = cmd.getStationNo();
        stationAggregate.stationPf = cmd.getStationPf();
        stationAggregate.stationPj = cmd.getStationPj();
        stationAggregate.stationName = cmd.getStationName();
        return  stationAggregate;
    }

}
