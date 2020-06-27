package com.xiaofeiyang.activiti.demo;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;

import java.util.List;

/**
 * @author: yangchun
 * @description:
 * @date: Created in 2020-06-01 17:24
 */
public class ActivityTask {
    public static void main(String[] args) {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        List<Task> list = taskService.createTaskQuery().processDefinitionKey("assigned").taskAssignee("xiafeng").list();
        for(Task task:list){
            System.out.println(task.getId());
            taskService.complete(task.getId());
        }
    }
}
