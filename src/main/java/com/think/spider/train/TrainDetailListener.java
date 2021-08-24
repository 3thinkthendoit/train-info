package com.think.spider.train;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.SpiderListener;
import java.util.concurrent.CountDownLatch;

/**
 * @author hg
 * @date 2021-08-17日 14:25
 */
public class TrainDetailListener implements SpiderListener {

    Logger logger = LoggerFactory.getLogger(getClass());

    private CountDownLatch countDownLatch;

    public TrainDetailListener(CountDownLatch countDownLatch){
        this.countDownLatch = countDownLatch;
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
        logger.error("error:{}",e.getMessage());
        countDownLatch.countDown();
    }
}
