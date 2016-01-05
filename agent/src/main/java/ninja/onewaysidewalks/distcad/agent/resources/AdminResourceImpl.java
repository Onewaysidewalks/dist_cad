package ninja.onewaysidewalks.distcad.agent.resources;

import ninja.onewaysidewalks.distcad.data.job.core.JobReader;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/agent")
@Produces("application/json")
@Consumes("application/json")
public class AdminResourceImpl implements AdminResource {

    @Inject
    @com.google.inject.Inject
    public AdminResourceImpl(JobReader jobReader) {

    }


    @Path("/state")
    @GET
    @Override
    public Object getState() {
        return new Object();
    }
}
