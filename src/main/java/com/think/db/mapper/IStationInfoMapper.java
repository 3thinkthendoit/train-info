package com.think.db.mapper;

import com.think.db.entity.StationInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 *  Station数据接口
 * @author hg
 * @since 2021-08-06
 */
public interface IStationInfoMapper extends BaseMapper<StationInfoEntity> {

    /**
     * 删除所有数据
     */
    public void deleteAll();
}
