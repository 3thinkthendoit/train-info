package com.think.service.spider;

import com.alibaba.fastjson.JSONObject;
import com.think.common.domain.TaskInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Spider;

/**
 * @author hg
 * @date 2021-08-04日 17:42
 *
 */
@Service
public class SpiderService {

    Logger logger = LoggerFactory.getLogger(getClass());

    /**
     *
     * @param taskInfo
     */
    public void doTask(TaskInfo taskInfo){
        logger.info(JSONObject.toJSONString(taskInfo));
        Spider spider =  Spider.create(taskInfo.getPageProcessor())
                .addUrl(taskInfo.getUrl())
                .thread(1);
        if(taskInfo.getPipeline()!=null){
            spider.addPipeline(taskInfo.getPipeline());
        }
        if(taskInfo.getDownloader()!=null){
            spider.setDownloader(taskInfo.getDownloader());
        }
        if(taskInfo.getScheduler() !=null){
            spider.setScheduler(taskInfo.getScheduler());
        }
        if(taskInfo.getListenerList()!=null) {
            spider.setSpiderListeners(taskInfo.getListenerList());
        }
        spider.start();
    }

}
