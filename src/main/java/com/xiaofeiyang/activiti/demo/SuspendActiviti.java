package com.xiaofeiyang.activiti.demo;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;

import java.util.Date;
import java.util.List;

/**
 * @author: yangchun
 * @description:
 * @date: Created in 2020-06-03 15:12
 */
public class SuspendActiviti {
    public static void main(String[] args) {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionKey("myProcess_1")
                .singleResult();
        if(processDefinition.isSuspended()){
            repositoryService.activateProcessDefinitionById(processDefinition.getId(),true,null);
        }else {
            repositoryService.suspendProcessDefinitionById(processDefinition.getId(),true,null);
        }
    }
}
