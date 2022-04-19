package com.think.infrastructure.mybits.mapper;

import com.think.infrastructure.mybits.po.StationInfoPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 *  Station数据接口
 * @author hg
 * @since 2021-08-06
 */
@Mapper
public interface IStationInfoMapper extends BatchMapper<StationInfoPO> {

    /**
     * 删除所有数据
     */
    public void deleteAll();
}
