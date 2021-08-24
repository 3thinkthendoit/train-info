package com.think.service.spider;

import com.alibaba.fastjson.JSONObject;
import com.think.common.domain.TaskInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.utils.HttpConstant;

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
        //内容处理
        Spider spider =  Spider.create(taskInfo.getPageProcessor());
        if(taskInfo.getPipeline()!=null){
            spider.addPipeline(taskInfo.getPipeline());
        }
        //下载器配置
        if(taskInfo.getDownloader()!=null){
            spider.setDownloader(taskInfo.getDownloader());
        }
        if(taskInfo.getScheduler() !=null){
            spider.setScheduler(taskInfo.getScheduler());
        }
        //爬虫监听
        if(taskInfo.getListenerList()!=null) {
            spider.setSpiderListeners(taskInfo.getListenerList());
        }
        Request request = new Request(taskInfo.getUrl());
        //默认GET请求
        request.setMethod(taskInfo.getMethod()!=null?taskInfo.getMethod().toString():HttpConstant.Method.GET);
        //新增headers
        if(!taskInfo.getHeaders().isEmpty()){
            taskInfo.getHeaders().forEach((key,value)->
                    request.addHeader(key,value)
            );
        }
        spider.addRequest(request);
        //默认async启动
        if(taskInfo.isAsync()) {
            spider.start();
        }else{
            spider.run();
        }
    }

}
