package com.think.domain.station.repository;

import com.think.infrastructure.mybits.po.StationInfoPO;

import java.util.List;

/**
 * @author hg
 * @date 2022-04-08日 15:45
 */
public interface StationRepository {


    /**
     * 批量保存
     * @param list
     */
    public void saveList(List<StationInfoPO> list);

    /**
     * 删除全部
     */
    public void deleteAll();
}
