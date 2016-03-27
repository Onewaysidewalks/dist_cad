package ninja.onewaysidewalks.distcad.core;

import ch.qos.logback.classic.Level;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.Provider;
import com.hubspot.dropwizard.guice.GuiceBundle;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import ninja.onewaysidewalks.distcad.core.guice.AutoConfigModules;

import java.util.ArrayList;
import java.util.List;

public abstract class Host<T extends Configuration> extends Application<T> {

    protected Provider<Injector> injectorProvider;

    private List<Module> specifiedModules = new ArrayList<>();

    public void withModule(Module module) {
        specifiedModules.add(module);
    }

    @Override
    public void initialize(Bootstrap<T> bootstrap) {

        io.dropwizard.logging.LoggingFactory.bootstrap(Level.INFO);

        List<String> packagesForAutoConfig = packagesForAutoConfig();

        final GuiceBundle.Builder<T> guiceBundleBuilder = GuiceBundle.<T>newBuilder()
                .enableAutoConfig(packagesForAutoConfig.toArray(new String[packagesForAutoConfig.size()]))
                .setConfigClass(this.getConfigurationClass());

        AutoConfigModules.withPackages(packagesForAutoConfig).list().forEach(guiceBundleBuilder::addModule);

        specifiedModules.forEach(guiceBundleBuilder::addModule);

        GuiceBundle<T> guiceBundle = guiceBundleBuilder.build();

        injectorProvider = guiceBundle::getInjector;

        bootstrap.addBundle(guiceBundle);
    }

    public abstract List<String> packagesForAutoConfig();

    @Override
    public void  run(T configuration, Environment environment) throws Exception {

    }
}
