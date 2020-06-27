package com.xiaofeiyang.activiti.demo;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;

/**
 * @author: yangchun
 * @description:
 * @date: Created in 2020-06-03 15:12
 */
public class SuspendProcessInstance {
    public static void main(String[] args) {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processDefinitionKey("myProcess_1").singleResult();
        if(processInstance.isSuspended()){
            runtimeService.activateProcessInstanceById(processInstance.getId());
        }else {
            runtimeService.suspendProcessInstanceById(processInstance.getId());
        }
    }
}
