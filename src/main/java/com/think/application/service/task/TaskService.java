package com.think.application.service.task;

import com.think.infrastructure.common.domain.SpiderTaskContext;

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
