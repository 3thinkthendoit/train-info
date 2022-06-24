package com.think.domain.train.model;

/**
 * @author hg
 * @date 2022-06-24日 14:32
 */
public class TrainId {

    private String date;

    private String trainCode;

    private Long trainInfoId;

    public TrainId(Long trainInfoId,String date,String trainCode) throws Exception {
        if(trainInfoId == null && date==null && trainCode==null){
            throw new Exception("trainInfoId and trainCode_date  both null!!!");
        }
        this.date = date;
        this.trainCode = trainCode;
        this.trainInfoId = trainInfoId;
    }

    public String getDate() {
        return date;
    }

    public String getTrainCode() {
        return trainCode;
    }

    public Long getTrainInfoId() {
        return trainInfoId;
    }
}
