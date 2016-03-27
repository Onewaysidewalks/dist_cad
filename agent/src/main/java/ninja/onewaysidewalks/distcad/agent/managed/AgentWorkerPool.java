package ninja.onewaysidewalks.distcad.agent.managed;

import io.dropwizard.lifecycle.Managed;
import lombok.extern.slf4j.Slf4j;
import ninja.onewaysidewalks.distcad.agent.ServiceConfig;
import ninja.onewaysidewalks.distcad.agent.models.AgentState;

import javax.inject.Inject;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class AgentWorkerPool implements Managed {

    private final ScheduledExecutorService executor;
    private final AgentState agentState;

    @Inject
    public AgentWorkerPool(ServiceConfig serviceConfig, AgentState agentState) {
        this.agentState = agentState;
        this.executor = Executors.newScheduledThreadPool(serviceConfig.getAgentWorkerPoolSize());
    }

    @Override
    public void start() throws Exception {
        executor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                if (agentState.isAllowedToWork()) {
                    agentState.setRunning(true);
                    log.info("somethings going on!");
                } else {
                    agentState.setRunning(false);
                }
            }
        }, 0, 1, TimeUnit.SECONDS);
    }

    @Override
    public void stop() throws Exception {
        executor.shutdown();
    }
}
