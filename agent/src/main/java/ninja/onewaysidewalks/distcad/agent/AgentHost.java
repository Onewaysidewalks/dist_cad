package ninja.onewaysidewalks.distcad.agent;

import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import ninja.onewaysidewalks.distcad.agent.models.AgentState;
import ninja.onewaysidewalks.distcad.core.Host;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class AgentHost extends Host<ServiceConfig> {

    public static void main(String[] args) throws Exception {
        new AgentHost().run(args);
    }

    @Override
    public void initialize(Bootstrap<ServiceConfig> bootstrap) {
        super.initialize(bootstrap);
    }

    @Override
    public void run(ServiceConfig configuration, Environment environment) throws Exception {
        injectorProvider.get().getInstance(AgentState.class).setId(UUID.randomUUID().toString());

        super.run(configuration, environment);
    }

    @Override
    public List<String> packagesForAutoConfig() {
        return Collections.singletonList("ninja.onewaysidewalks.distcad");
    }
}
