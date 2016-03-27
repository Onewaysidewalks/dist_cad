package ninja.onewaysidewalks.distcad.agent.resources;

import lombok.extern.slf4j.Slf4j;
import ninja.onewaysidewalks.distcad.agent.models.AgentState;
import javax.inject.Inject;
import javax.ws.rs.*;

@Path("/agent")
@Produces("application/json")
@Consumes("application/json")
@Slf4j
public class AdminResource {

    private final AgentState agentState;

    @Inject
    public AdminResource(AgentState agentState) {
        this.agentState = agentState;
    }

    @Path("/state")
    @GET
    public AgentState getState() {
        return agentState;
    }

    @Path("/stop_work")
    @POST
    public AgentState stopWork() {

        log.info("Disallowing agent to work....");

        agentState.setAllowedToWork(false);

        return agentState;
    }

    @Path("/start_work")
    @POST
    public AgentState startWork() {

        log.info("Allowing agent to work....");

        agentState.setAllowedToWork(true);

        return agentState;
    }
}
