package com.think.infrastructure.common.domain;

import com.think.application.service.task.TaskService;

import java.util.concurrent.CountDownLatch;

/**
 * @author hg
 * @date 2021-08-30日 14:18
 */
public class TaskContext {

    private CountDownLatch countDownLatch;

    private TaskService taskService;

    public TaskContext(CountDownLatch countDownLatch,TaskService taskService){
        this.countDownLatch = countDownLatch;
        this.taskService = taskService;
    }

    public CountDownLatch getCountDownLatch() {
        return countDownLatch;
    }

    public void setCountDownLatch(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    public TaskService getTaskService() {
        return taskService;
    }

    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }
}
