package ninja.onewaysidewalks.distcad.agent.guice;

import com.google.inject.Binder;
import com.google.inject.Module;
import ninja.onewaysidewalks.distcad.agent.resources.AdminResource;
import ninja.onewaysidewalks.distcad.agent.resources.AdminResourceImpl;


public class ApiModule implements Module {
    @Override
    public void configure(Binder binder) {
        binder.bind(AdminResource.class).to(AdminResourceImpl.class);
    }
}
