package com.xiaofeiyang.activiti.demo;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: yangchun
 * @description:
 * @date: Created in 2020-06-01 16:57
 */
public class AssigneeUEL {
    public static void main(String[] args) {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        Map<String,Object> assigned = new HashMap<>();
        assigned.put("assigned0","yangchun");
        assigned.put("assigned1","liujiawei");
        assigned.put("assigned2","xiafeng");
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("assigned","1",assigned);
        System.out.println(processInstance.getBusinessKey());
    }
}
