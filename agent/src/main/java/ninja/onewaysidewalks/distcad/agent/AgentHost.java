package ninja.onewaysidewalks.distcad.agent;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import io.dropwizard.setup.Bootstrap;
import ninja.onewaysidewalks.distcad.core.Host;
import ninja.onewaysidewalks.distcad.data.job.core.JobReader;
import ninja.onewaysidewalks.distcad.data.job.inmemory.DataModule;
import ninja.onewaysidewalks.distcad.data.job.inmemory.InMemoryJobRepository;

import java.util.Collections;
import java.util.List;

public class AgentHost extends Host<ServiceConfig> {

    public static void main(String[] args) throws Exception {
        new AgentHost().run(args);
    }

    @Override
    public void initialize(Bootstrap<ServiceConfig> bootstrap) {

        withModule(new AbstractModule() {
            @Override
            protected void configure() {
//                bind(JobReader.class).toInstance(new InMemoryJobRepository.InMemoryJobReader());
            }2

            @Provides
            public JobReader jobReader() {
                return new InMemoryJobRepository.InMemoryJobReader();
            }
        });

        super.initialize(bootstrap);
    }

    @Override
    public List<String> packagesForAutoConfig() {
        return Collections.singletonList("ninja.onewaysidewalks.distcad.agent");
    }
}