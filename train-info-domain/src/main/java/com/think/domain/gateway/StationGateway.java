package com.think.domain.gateway;

import com.think.application.bo.StationBO;

import java.util.List;

/**
 * @author hg
 * @date 2022-04-15日 17:13
 */
public interface StationGateway {

    public void updateStationList(List<StationBO> list);
}
