package com.xiaofeiyang.activiti.demo;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.List;

/**
 * @author: yangchun
 * @description:
 * @date: Created in 2020-06-03 15:12
 */
public class QueryBpmFile {
    public static void main(String[] args) throws IOException {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        processDefinitionQuery.processDefinitionKey("myProcess_1");
        ProcessDefinition processDefinition = processDefinitionQuery.singleResult();
        String deploymentId = processDefinition.getDeploymentId();
        InputStream inputStream = repositoryService.getResourceAsStream(deploymentId,processDefinition.getDiagramResourceName());
        InputStream inputStream2 = repositoryService.getResourceAsStream(deploymentId,processDefinition.getResourceName());
        OutputStream outputStream = new FileOutputStream("E:\\spark\\"+processDefinition.getDiagramResourceName());
        OutputStream outputStream2 = new FileOutputStream("E:\\spark\\"+processDefinition.getResourceName());
        IOUtils.copy(inputStream,outputStream);
        IOUtils.copy(inputStream2,outputStream2);
        outputStream.close();
        outputStream2.close();
        inputStream.close();
        inputStream2.close();
    }
}
