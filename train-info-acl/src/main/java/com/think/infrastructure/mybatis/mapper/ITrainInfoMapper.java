package com.think.infrastructure.mybatis.mapper;

import com.think.infrastructure.mybatis.po.TrainInfoPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author hg
 * @date 2021-08-11日 17:44
 */
@Mapper
public interface ITrainInfoMapper extends BatchMapper<TrainInfoPO> {

    public void deleteAllTrainInfo();

    public List<TrainInfoPO> getTrainList();

    public void updateTrainInfoByTrainCode(Map<String,Object> params);

    public List<Long> selectDuplicateTrainInfo();
}
