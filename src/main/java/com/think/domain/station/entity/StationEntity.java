package com.think.domain.station.entity;


import com.think.domain.city.entity.CityEntity;
import com.think.domain.train.entity.TrainInfoEntity;

import java.util.List;

/**
 * 车站信息
 * @author hg
 * @date 2022-03-31日 11:42
 */
public class StationEntity {

    /**
     * 车站代码
     */
    private String stationCode;

    private Integer stationId;

    private String stationNo;

    private String stationName;

    private String stationPj;

    private String stationPf;

    /**
     * 归属城市
     */
    private CityEntity city;

    /**
     * 车次过站信息
     */
    private List<TrainInfoEntity> list;


    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

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

    public CityEntity getCity() {
        return city;
    }

    public void setCity(CityEntity city) {
        this.city = city;
    }


    public List<TrainInfoEntity> getList() {
        return list;
    }

    public void setList(List<TrainInfoEntity> list) {
        this.list = list;
    }
}
