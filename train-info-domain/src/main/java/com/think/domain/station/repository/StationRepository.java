package com.think.domain.station.repository;

import com.think.domain.station.entity.StationEntity;
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
    public void saveList(List<StationEntity> list);

    /**
     * 删除全部
     */
    public void deleteAll();
}
