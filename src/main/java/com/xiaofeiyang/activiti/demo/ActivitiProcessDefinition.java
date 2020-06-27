package com.xiaofeiyang.activiti.demo;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;

import java.util.List;

/**
 * @author: yangchun
 * @description:
 * @date: Created in 2020-06-03 15:12
 */
public class ActivitiProcessDefinition {
    public static void main(String[] args) {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        List<ProcessDefinition> processDefinitions = repositoryService.createProcessDefinitionQuery().processDefinitionKey("myProcess_1").
                orderByProcessDefinitionVersion().desc().list();
        for(ProcessDefinition processDefinition:processDefinitions){
            System.out.println(processDefinition.getDeploymentId());
            System.out.println(processDefinition.getId());
            System.out.println(processDefinition.getName());
            System.out.println(processDefinition.getKey());
            //第二个参数强制删除未完成的流程
            repositoryService.deleteDeployment(processDefinition.getDeploymentId());
        }
    }
}
