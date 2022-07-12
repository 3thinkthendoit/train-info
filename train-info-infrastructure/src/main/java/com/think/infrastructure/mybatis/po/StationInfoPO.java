package com.think.infrastructure.mybatis.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * @author hg
 * @since 2021-08-06
 */
@TableName("station_info")
public class StationInfoPO extends Model<StationInfoPO> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("station_code")
    private String stationCode;

    @TableField("station_id")
    private Integer stationId;

    @TableField("station_no")
    private String stationNo;

    @TableField("station_name")
    private String stationName;

    @TableField("station_pj")
    private String stationPj;

    @TableField("station_pf")
    private String stationPf;

    @TableField("city_code_id")
    private Integer cityCodeId;

    @TableField("city_code_id")
    private Byte state;

    @Override
    public Serializable pkVal() {
        return this.id;
    }
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

    public Integer getCityCodeId() {
        return cityCodeId;
    }

    public void setCityCodeId(Integer cityCodeId) {
        this.cityCodeId = cityCodeId;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }
}
