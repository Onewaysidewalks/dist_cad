package ninja.onewaysidewalks.distcad.agent.models.api;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class JobData {

    private String id;

    @NotNull
    private String ownerId;
}
