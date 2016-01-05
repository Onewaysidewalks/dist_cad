package ninja.onewaysidewalks.distcad.agent;

import io.dropwizard.Configuration;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ServiceConfig extends Configuration {
    private int agentWorkerPoolSize;
}
