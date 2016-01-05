package ninja.onewaysidewalks.distcad.agent.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;


public interface AdminResource {

    Object getState();
}
