package com.think.spider;

import com.think.util.SpiderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * @author hg
 * @date 2021-08-10日 15:17
 */
public abstract class BaseProcessor implements PageProcessor {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 爬虫配置
     */
    protected Site site = Site.me()
            //重试次数
            .setRetryTimes(3)
            //UA
            .setUserAgent(SpiderUtil.getUserAgent())
            //间隔时间
            .setSleepTime(1000)
            //超时时间
            .setTimeOut(20000);
            //设置代理
           // .setHttpProxy();

}
