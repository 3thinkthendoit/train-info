package com.think.service.task;

import com.think.common.domain.SpiderTaskContext;

import java.util.Map;

/**
 * @author hg
 * @date 2021-08-26日 11:45
 */
public interface TaskService {

    public void runTask();

    public void initTaskInfo(SpiderTaskContext spiderTaskContext);

    public void executeTask(SpiderTaskContext spiderTaskContext);

    public void afterTask(SpiderTaskContext spiderTaskContext);


}
