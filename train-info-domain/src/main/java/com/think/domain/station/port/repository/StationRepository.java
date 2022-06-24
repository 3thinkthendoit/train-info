package com.think.domain.station.port.repository;

import com.think.domain.station.model.StationAggregate;

import java.util.List;

/**
 * @author hg
 * @date 2022-04-08日 15:45
 */

public interface StationRepository {


    /**
     * 持久化聚合根
     *
     * @param stationAggregate
     */
    public void save(StationAggregate stationAggregate);

    /**
     * 批量持久化
     * @param aggregateList
     */
    public void batchSave(List<StationAggregate> aggregateList);

}
