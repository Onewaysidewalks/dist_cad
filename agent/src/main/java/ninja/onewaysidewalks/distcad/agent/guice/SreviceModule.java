package ninja.onewaysidewalks.distcad.agent.guice;

import com.google.inject.Binder;
import com.google.inject.Module;
import ninja.onewaysidewalks.distcad.agent.models.AgentState;
import ninja.onewaysidewalks.distcad.core.DateTimeProvider;

/**\
 * Registers all bits necessary to do actual work
 */
public class SreviceModule implements Module {

    @Override
    public void configure(Binder binder) {
        binder.bind(AgentState.class).toInstance(new AgentState()); //Effectively a singleton
        binder.bind(DateTimeProvider.class).toInstance(new DateTimeProvider());
    }
}
