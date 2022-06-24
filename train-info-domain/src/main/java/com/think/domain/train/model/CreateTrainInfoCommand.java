package com.think.domain.train.model;

import java.util.List;

/**
 * @author hg
 * @date 2022-06-24日 14:27
 */
public class CreateTrainInfoCommand {

    private String trainNo;

    private String depart;

    private String destination;

    private String num;

    private String date;

    private String trainCode;

    private List<CreateTrainDetailCommand> createTrainDetailCommandList;

    public List<CreateTrainDetailCommand> getCreateTrainDetailCommandList() {
        return createTrainDetailCommandList;
    }

    public void setCreateTrainDetailCommandList(List<CreateTrainDetailCommand> createTrainDetailCommandList) {
        this.createTrainDetailCommandList = createTrainDetailCommandList;
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

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

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


}
