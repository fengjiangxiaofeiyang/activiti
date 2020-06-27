import org.activiti.api.runtime.shared.identity.UserGroupManager;
import org.activiti.engine.*;
import org.activiti.engine.impl.db.DbSchemaCreate;
import org.activiti.engine.impl.persistence.entity.integration.IntegrationContextManager;
import org.activiti.engine.integration.IntegrationContextService;

/**
 * @author: yangchun
 * @description:
 * @date: Created in 2020-05-26 17:04
 */
public class CreateActivitiTables {
    public static void main(String[] args) {
        DbSchemaCreate.main(args);
    }
    public static ProcessEngine getProcessEngine(){
        ProcessEngineConfiguration processEngineConfiguration = ProcessEngineConfiguration.
                createProcessEngineConfigurationFromResource("activiti.cfg.xml","processEngineConfiguration");
        ProcessEngine processEngine = processEngineConfiguration.buildProcessEngine();
        processEngine.getHistoryService();
        processEngine.getRuntimeService();
        return processEngine;
    }
}
