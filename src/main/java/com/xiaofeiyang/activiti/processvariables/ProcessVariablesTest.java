package com.xiaofeiyang.activiti.processvariables;

import org.activiti.engine.*;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import java.util.*;

/**
 * @author: yangchun
 * @description:
 * @date: Created in 2020-06-09 10:58
 */
public class ProcessVariablesTest {
    public static void main(String[] args) {
        //deploy();
        //startProcess();
        //completeTask("dingji");
        //completeTaskVariables("dingji");
        //deleteDeploy();
    }
    public static void deploy(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        repositoryService.createDeployment().addClasspathResource("processvariables.bpmn")
                .addClasspathResource("processvariables.png").name("processvariables")
                .deploy();
    }
    // 流程变量存在act_ru_variables,act_get_bytearray
    public static void startProcess(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        Map<String,Object> variables = new HashMap<>();
        Holiday holiday = new Holiday();

        holiday.setBegin(new Date());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_YEAR,3);
        holiday.setEnd(calendar.getTime());

        holiday.setHolidayName("请假回家");
        holiday.setId(1);
        holiday.setNum(2.5f);
        holiday.setReason("请假回家玩");
        holiday.setType("调休假");

        //variables.put("holiday",holiday);
        variables.put("assign0","yangchun");
        variables.put("assign1","dingji");
        variables.put("assign2","sparky");
        variables.put("assign3","shengqin");
        runtimeService.startProcessInstanceByKey("processvariable","1",variables);
    }

    public static void completeTask(String name){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        Task task = taskService.createTaskQuery().processDefinitionKey("processvariable").taskAssignee(name).singleResult();
        if(task!=null){
            taskService.complete(task.getId());
        }
    }
    public static void completeTaskVariables(String name){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        Task task = taskService.createTaskQuery().processDefinitionKey("processvariable").taskAssignee(name).singleResult();
        if(task!=null){
            Map<String,Object> variables = new HashMap<>();
            Holiday holiday = new Holiday();

            holiday.setBegin(new Date());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DAY_OF_YEAR,3);
            holiday.setEnd(calendar.getTime());

            holiday.setHolidayName("请假回家");
            holiday.setId(1);
            holiday.setNum(2.5f);
            holiday.setReason("请假回家玩");
            holiday.setType("调休假");
            variables.put("holiday",holiday);
            taskService.complete(task.getId(),variables);
        }
    }

    public static void deleteDeploy(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        List<ProcessDefinition> processDefinitions = repositoryService.createProcessDefinitionQuery().processDefinitionKey("processvariable").
                orderByProcessDefinitionVersion().desc().list();
        for(ProcessDefinition processDefinition:processDefinitions){
            System.out.println(processDefinition.getDeploymentId());
            System.out.println(processDefinition.getId());
            System.out.println(processDefinition.getName());
            System.out.println(processDefinition.getKey());
            //第二个参数强制删除未完成的流程
            repositoryService.deleteDeployment(processDefinition.getDeploymentId(),true);
        }
    }
    public static void processInstanceVariables(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        List<ProcessInstance> processInstances = runtimeService.createProcessInstanceQuery().processDefinitionKey("processvariable")
                .list();
        Holiday holiday = new Holiday();
        holiday.setBegin(new Date());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_YEAR,3);
        holiday.setEnd(calendar.getTime());
        holiday.setHolidayName("请假回家");
        holiday.setId(1);
        holiday.setNum(2.5f);
        holiday.setReason("请假回家玩");
        holiday.setType("调休假");
        for(ProcessInstance processInstance:processInstances){
            if(processInstance.isEnded()){
                continue;
            }
            runtimeService.setVariable(processInstance.getId(),"holiday",holiday);
            // Map<String,Object> variables = new HashMap<>();
           // variables.put("holiday",holiday);
           // runtimeService.setVariables(processInstance.getId(),variables);
        }
    }
    public static void taskVariables(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        List<Task> taskList = taskService.createTaskQuery().processDefinitionKey("processvariable")
                .list();
        Holiday holiday = new Holiday();
        holiday.setBegin(new Date());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_YEAR,3);
        holiday.setEnd(calendar.getTime());
        holiday.setHolidayName("请假回家");
        holiday.setId(1);
        holiday.setNum(2.5f);
        holiday.setReason("请假回家玩");
        holiday.setType("调休假");
        for(Task task:taskList){
            taskService.setVariable(task.getId(),"holiday",holiday);
            // Map<String,Object> variables = new HashMap<>();
            // variables.put("holiday",holiday);
            // taskService.setVariables(task.getId(),variables);
        }
    }
}
