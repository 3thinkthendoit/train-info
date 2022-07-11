package com.think.domain.screen.port.repository;

import com.think.domain.screen.model.CreateScreenInfoAggregate;


/**
 * 
 * 
 */
public interface ScreenInfoRepository {

    public void save(CreateScreenInfoAggregate createScreenInfoAggregate);
    
}
