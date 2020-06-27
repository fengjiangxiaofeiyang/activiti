package com.xiaofeiyang.activiti.cadidate;

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
        //queryCandidateTask("yangchun");
       // assignCandidateTask("yangchun");
        //returnCandidateTask("yangchun");
        transCandidateTask("yangchun","lidan");
        //completeTask("dingji");
        //completeTaskVariables("dingji");
        //deleteDeploy();
    }
    public static void deploy(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        repositoryService.createDeployment().addClasspathResource("candidate.bpmn")
                .addClasspathResource("candidate.png").name("candidate")
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

        variables.put("holiday",holiday);
        List<String> user = Arrays.asList(new String[]{"yangchun","lidan"});
        List<String> user1 = Arrays.asList(new String[]{"yuyuping","yangzhongming"});
        List<String> user2 = Arrays.asList(new String[]{"xiakexiu","likefu"});
        List<String> user3 = Arrays.asList(new String[]{"yanghuan","zhangwei"});
        variables.put("users0",user);
        variables.put("users1",user1);
        variables.put("users2",user2);
        variables.put("users3",user3);
        runtimeService.startProcessInstanceByKey("cadidate","1",variables);
    }

    public static void queryCandidateTask(String name){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        Task task = taskService.createTaskQuery().processDefinitionKey("cadidate").taskCandidateUser(name).singleResult();
        System.out.println(task.getId());
        System.out.println(task.getAssignee());
    }
    public static void assignCandidateTask(String name){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        Task task = taskService.createTaskQuery().processDefinitionKey("cadidate").taskCandidateUser(name).singleResult();
        taskService.setAssignee(task.getId(),"yangchun");
        System.out.println(task.getId());
        System.out.println(task.getAssignee());
    }
    public static void returnCandidateTask(String name){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        Task task = taskService.createTaskQuery().processDefinitionKey("cadidate").taskAssignee(name).singleResult();
        taskService.setAssignee(task.getId(), null);
        System.out.println(task.getId());
        System.out.println(task.getAssignee());

    }
    public static void transCandidateTask(String name1,String name){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        Task task = taskService.createTaskQuery().processDefinitionKey("cadidate").taskAssignee(name1).singleResult();
        taskService.setAssignee(task.getId(), name);
        task = taskService.createTaskQuery().processDefinitionKey("cadidate").taskAssignee(name).singleResult();
        System.out.println(task.getId());
        System.out.println(task.getAssignee());
    }
}
