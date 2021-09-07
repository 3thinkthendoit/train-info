package com.think.db.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.think.db.entity.TrainDetailEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * @author hg
 * @date 2021-08-13日 18:06
 */
@Mapper
public interface ITrainDetailMapper extends BaseMapper<TrainDetailEntity> {

    /**
     *
     * @param params
     */
    public void deleteTrainDetailByTrainCode(Map<String,Object> params);
}
