package com.think.infrastructure.mybatis.mapper;

import com.think.infrastructure.mybatis.po.StationInfoPO;
import org.apache.ibatis.annotations.Mapper;

/**
 *  Station数据接口
 * @author hg
 * @since 2021-08-06
 */
@Mapper
public interface IStationInfoMapper extends BatchMapper<StationInfoPO> {

    /**
     * 失效所有数据
     */
    public void deleteAll();

    /**
     * 根据车站代码失效车站
     * @param stationCode
     */
    public void deleteByStationCode(String stationCode);
}
