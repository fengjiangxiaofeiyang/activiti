package com.xiaofeiyang.activiti.demo;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

/**
 * @author: yangchun
 * @description:
 * @date: Created in 2020-06-06 17:19
 */
public class ActivitiTaskListener implements TaskListener {
    @Override
    public void notify(DelegateTask delegateTask) {
        delegateTask.setAssignee("yangchun");
    }
}
