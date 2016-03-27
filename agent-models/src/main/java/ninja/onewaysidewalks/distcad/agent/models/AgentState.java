package ninja.onewaysidewalks.distcad.agent.models;

import lombok.Data;

@Data
public class AgentState {

    /**
     * Agent id
     */
    private String id;

    /**
     * Whether or not the agent is currently processing work.
     * This object is a singleton, and shared with the rest of the Agent code
     */
    private boolean isRunning;

    /**
     * Whether or not the agent is allowed to work.
     * Setting this to fault via code/RESAPI will result in the agent no longer doing work
     * However, this does not cancel the current job of work. That will finish, and no
     * FUTURE work will be done
     */
    private boolean allowedToWork;
}
