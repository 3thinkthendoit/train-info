package com.think.application.service.screen;

import com.think.domain.screen.model.CreateScreenInfoAggregate;
import com.think.domain.screen.model.CreateScreenInfoCommand;
import com.think.domain.screen.port.repository.ScreenInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author hg
 * @date 2022-06-30日 15:38
 */
@Service
public class ScreenAppService {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    ScreenInfoRepository screenInfoRepository;

    public void saveScreenInfo(CreateScreenInfoCommand screenInfoCommand){
        CreateScreenInfoAggregate screenInfoAggregate = CreateScreenInfoAggregate.create(screenInfoCommand);
        //invoke domain method
        screenInfoRepository.save(screenInfoAggregate);
    }

}
