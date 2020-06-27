import com.xiaofeiyang.activiti.util.SecurityUtil;
import org.activiti.api.process.model.ProcessInstance;
import org.activiti.api.process.model.builders.ProcessPayloadBuilder;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.api.runtime.shared.query.Page;
import org.activiti.api.runtime.shared.query.Pageable;
import org.activiti.api.task.model.Task;
import org.activiti.api.task.model.builders.CompleteTaskPayloadBuilder;
import org.activiti.api.task.model.builders.TaskPayloadBuilder;
import org.activiti.api.task.model.payloads.CompleteTaskPayload;
import org.activiti.api.task.runtime.TaskRuntime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: yangchun
 * @description:
 * @date: Created in 2020-06-12 9:09
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivitiTest {
    @Autowired(required = false)
    private ProcessRuntime processRuntime;
    @Autowired(required = false)
    private TaskRuntime runtime;
    @Autowired(required = false)
    private SecurityUtil securityUtil;
    // activiti与springboot整合后会自动部署流程，必须放到resource/processes下面
    @Test
    private void runProcessDefinition(){
        securityUtil.logInAs("bob");
        Page processDefinitionPage = processRuntime.processDefinitions(Pageable.of(0,10));
    }
    @Scheduled(initialDelay = 1000,fixedDelay = 1000)
    public void processText(){
        securityUtil.logInAs("bob");
        ProcessInstance processInstance = processRuntime.start(ProcessPayloadBuilder.start()
        .withProcessDefinitionKey("abc").withName("abc").withVariable("content","content").build());
    }
    @Test
    public void testTask(){
        securityUtil.logInAs("bob");
        Page<Task> taskPage =runtime.tasks(Pageable.of(0,10));
        if(taskPage.getTotalItems()>0) {
            for(Task task:taskPage.getContent()){
                System.out.println(task);
                runtime.claim(TaskPayloadBuilder.claim().withTaskId(task.getId()).build());
                runtime.complete(TaskPayloadBuilder.complete().withTaskId(task.getId()).build());
            }
        }
    }
}
