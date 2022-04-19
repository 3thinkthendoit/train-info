package com.think.infrastructure.mybits.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.think.infrastructure.mybits.po.TrainDetailPO;
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
