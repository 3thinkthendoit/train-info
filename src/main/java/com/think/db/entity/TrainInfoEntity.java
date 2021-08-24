package com.think.db.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.util.Date;

/**
 * @author hg
 * @date 2021-08-11日 17:39
 */
@TableName("train_info")
public class TrainInfoEntity extends Model<TrainInfoEntity> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "train_id", type = IdType.AUTO)
    private Long trainId;


    @TableField("train_code")
    private String trainCode;

    @TableField("train_no")
    private String trainNo;

    @TableField("depart")
    private String depart;

    @TableField("destination")
    private String destination;

    @TableField("state")
    private Byte state;

    @TableField("train_date")
    private Date trainDate;

    public Long getTrainId() {
        return trainId;
    }

    public void setTrainId(Long trainId) {
        this.trainId = trainId;
    }


    public Date getTrainDate() {
        return trainDate;
    }

    public void setTrainDate(Date trainDate) {
        this.trainDate = trainDate;
    }

    public String getTrainCode() {
        return trainCode;
    }

    public void setTrainCode(String trainSn) {
        this.trainCode = trainSn;
    }

    public String getTrainNo() {
        return trainNo;
    }

    public void setTrainNo(String trainNo) {
        this.trainNo = trainNo;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }
}
