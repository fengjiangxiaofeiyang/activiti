package com.xiaofeiyang.activiti.demo;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.springframework.util.ResourceUtils;


/**
 * @author: yangchun
 * @description:
 * @date: Created in 2020-06-01 15:47
 */
public class ActivitiDeployment {
    public static void main(String[] args) {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deployment = repositoryService.createDeployment().addClasspathResource("assigned.bpmn")
                .addClasspathResource("assigned.png")
                .name("请假流程")
                .deploy();
    }
}
