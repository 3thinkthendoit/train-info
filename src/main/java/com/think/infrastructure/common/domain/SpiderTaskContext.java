package com.think.infrastructure.common.domain;

import com.google.common.collect.Maps;
import com.think.application.service.task.TaskService;

import java.util.Map;

/**
 * @author hg
 * @date 2021-09-01日 10:40
 */
public class SpiderTaskContext {

    private TaskInfo taskInfo = new TaskInfo();

    private Map<String,Object> data = Maps.newConcurrentMap();

    private TaskService taskService;

    public void setParam(String key,Object value){
        data.put(key,value);
    }

    public Object getParam(String key){
        return  data.get(key);
    }

    public  SpiderTaskContext(TaskService taskService){
        this.taskService = taskService;
    }

    public TaskInfo getTaskInfo() {
        return taskInfo;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public TaskService getTaskService() {
        return taskService;
    }

    public static SpiderTaskContext create(TaskService taskService){
        return new SpiderTaskContext(taskService);
    }
}
