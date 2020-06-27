package com.xiaofeiyang.activiti.demo;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricActivityInstanceQuery;
import org.activiti.engine.repository.Deployment;

import java.util.List;


/**
 * @author: yangchun
 * @description:
 * @date: Created in 2020-06-01 15:47
 */
public class ActivitiHistory {
    public static void main(String[] args) {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        HistoryService historyService = processEngine.getHistoryService();
        HistoricActivityInstanceQuery querInstance =  historyService.createHistoricActivityInstanceQuery();
        querInstance.processInstanceId("2501");
        List<HistoricActivityInstance> list = querInstance.list();
        for(HistoricActivityInstance historicActivityInstance:list){
            System.out.println(historicActivityInstance.getActivityId());
            System.out.println(historicActivityInstance.getActivityName());
            System.out.println(historicActivityInstance.getProcessDefinitionId());
            System.out.println(historicActivityInstance.getProcessInstanceId());
        }
    }
}
