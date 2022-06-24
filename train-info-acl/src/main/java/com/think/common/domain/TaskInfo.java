package com.think.common.domain;

import com.google.common.collect.Maps;
import org.assertj.core.util.Lists;
import us.codecraft.webmagic.SpiderListener;
import us.codecraft.webmagic.downloader.Downloader;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.Scheduler;
import us.codecraft.webmagic.utils.HttpConstant;

import java.util.List;
import java.util.Map;

/**
 *
 * @author hg
 * @date 2021-08-10日 11:42
 */
public class TaskInfo {

    private String url;

    private PageProcessor pageProcessor;

    private Pipeline pipeline;

    private Downloader downloader;

    private List<SpiderListener> listenerList = Lists.newArrayList();

    private HttpConstant.Method method;

    private Map<String,String> headers = Maps.newHashMap();

    private Object object;

    private boolean isAsync = true;

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public void setSpiderListener(SpiderListener listener){
        listenerList.add(listener);
    }

    public List<SpiderListener> getListenerList(){
        return listenerList;
    }

    public Downloader getDownloader() {
        return downloader;
    }

    public void setDownloader(Downloader downloader) {
        this.downloader = downloader;
    }

    public Scheduler getScheduler() {
        return scheduler;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    private Scheduler scheduler;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public PageProcessor getPageProcessor() {
        return pageProcessor;
    }

    public void setPageProcessor(PageProcessor pageProcessor) {
        this.pageProcessor = pageProcessor;
    }

    public Pipeline getPipeline() {
        return pipeline;
    }

    public void setPipeline(Pipeline pipeline) {
        this.pipeline = pipeline;
    }

    public HttpConstant.Method getMethod() {
        return method;
    }

    public void setMethod(HttpConstant.Method method) {
        this.method = method;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeader(String key,String value){
        this.headers.put(key,value);
    }

    public boolean isAsync() {
        return isAsync;
    }

    public void setAsync(boolean async) {
        isAsync = async;
    }
}
