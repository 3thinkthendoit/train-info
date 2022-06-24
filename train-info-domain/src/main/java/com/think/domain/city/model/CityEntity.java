package com.think.domain.city.model;

/**
 * @author hg
 * @date 2022-04-08日 16:43
 */
public class CityEntity {

    private String cityName;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getCityCode() {
        return cityCode;
    }

    public void setCityCode(int cityCode) {
        this.cityCode = cityCode;
    }

    private int cityCode;


}
