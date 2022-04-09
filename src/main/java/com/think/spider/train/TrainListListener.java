package com.think.spider.train;

import com.think.infrastructure.common.domain.SpiderTaskContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.SpiderListener;

/**
 * @author hg
 * @date 2021-08-24日 11:31
 */
public class TrainListListener implements SpiderListener {

    Logger logger = LoggerFactory.getLogger(getClass());

    private SpiderTaskContext spiderTaskContext;

    public TrainListListener(SpiderTaskContext spiderTaskContext){
        this.spiderTaskContext = spiderTaskContext;
    }


    @Override
    public void onSuccess(Request request) {

    }

    /**
     * @param request
     * @deprecated
     */
    @Override
    public void onError(Request request) {

    }

    @Override
    public void onError(Request request, Exception e) {
        logger.error(e.getMessage(),e);
    }
}
