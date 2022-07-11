package com.think.domain.screen.model;


public class CreateScreenInfoAggregate {

    private ScreenInfoId screenInfoId;
    
    private String trainDataSource;

    private ArriveInfo arriveInfo;
    
    private DepartInfo departInfo;


    /**
     * 工厂创建聚合根
     * @param command
     * @return
     */
    public static CreateScreenInfoAggregate create(CreateScreenInfoCommand command){

        CreateScreenInfoAggregate createScreenInfoAggregate = new CreateScreenInfoAggregate();
        createScreenInfoAggregate.arriveInfo = new ArriveInfo();
        createScreenInfoAggregate.departInfo = new DepartInfo();
        //    
        return createScreenInfoAggregate;
    }


    public ScreenInfoId getScreenInfoId() {
        return screenInfoId;
    }


    public String getTrainDataSource() {
        return trainDataSource;
    }


    public ArriveInfo getArriveInfo() {
        return arriveInfo;
    }


    public DepartInfo getDepartInfo() {
        return departInfo;
    }
    
    

}
