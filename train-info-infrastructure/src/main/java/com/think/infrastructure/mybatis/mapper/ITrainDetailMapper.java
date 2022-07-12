package com.think.infrastructure.mybatis.mapper;

import com.think.infrastructure.mybatis.po.TrainDetailPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * @author hg
 * @date 2021-08-13日 18:06
 */
@Mapper
public interface ITrainDetailMapper extends BatchMapper<TrainDetailPO> {

    /**
     *
     * @param params
     */
    public void deleteTrainDetailByTrainCode(Map<String,Object> params);
}
