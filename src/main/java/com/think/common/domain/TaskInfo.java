package com.think.common.domain;

import org.assertj.core.util.Lists;
import us.codecraft.webmagic.SpiderListener;
import us.codecraft.webmagic.downloader.Downloader;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.Scheduler;

import java.util.List;

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
}