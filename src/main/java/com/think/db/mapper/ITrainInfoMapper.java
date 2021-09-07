package com.think.db.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.think.db.entity.TrainInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author hg
 * @date 2021-08-11日 17:44
 */
@Mapper
public interface ITrainInfoMapper extends BaseMapper<TrainInfoEntity> {

    public void deleteAllTrainInfo();

    public List<TrainInfoEntity> getTrainList();

    public void updateTrainInfoByTrainCode(Map<String,Object> params);

    public List<Long> selectDuplicateTrainInfo();
}
