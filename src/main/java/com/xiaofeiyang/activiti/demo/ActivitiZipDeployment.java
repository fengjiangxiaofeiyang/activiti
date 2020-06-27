package com.xiaofeiyang.activiti.demo;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;

import java.io.InputStream;
import java.util.zip.ZipInputStream;


/**
 * @author: yangchun
 * @description:
 * @date: Created in 2020-06-01 15:47
 */
public class ActivitiZipDeployment {
    public static void main(String[] args) {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        InputStream path = ActivitiZipDeployment.class.getClassLoader().getResourceAsStream("askForleave.zip");
        ZipInputStream zipInputStream=new ZipInputStream(path);
        Deployment deployment = repositoryService.createDeployment().addZipInputStream(zipInputStream)
                .name("请假流程")
                .deploy();
    }
}
