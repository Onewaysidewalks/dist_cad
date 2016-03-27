package ninja.onewaysidewalks.distcad.agent.resources;

import ninja.onewaysidewalks.distcad.agent.models.AgentState;
import ninja.onewaysidewalks.distcad.agent.models.api.JobData;
import ninja.onewaysidewalks.distcad.agent.models.api.ListJobResponse;
import ninja.onewaysidewalks.distcad.core.DateTimeProvider;
import ninja.onewaysidewalks.distcad.data.job.core.Job;
import ninja.onewaysidewalks.distcad.data.job.core.JobReader;
import ninja.onewaysidewalks.distcad.data.job.core.JobWriter;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.ws.rs.*;
import java.util.Collections;
import java.util.UUID;

@Path("/agent/jobs")
@Produces("application/json")
@Consumes("application/json")
public class JobResource {

    private final JobReader jobReader;
    private final AgentState agentState;
    private final JobWriter jobWriter;
    private final DateTimeProvider dateTimeProvider;

    @Inject
    public JobResource(
            JobReader jobReader,
            JobWriter jobWriter,
            DateTimeProvider dateTimeProvider,
            AgentState agentState) {
        this.jobReader = jobReader;
        this.agentState = agentState;
        this.jobWriter = jobWriter;
        this.dateTimeProvider = dateTimeProvider;
    }

    @Path("/")
    @GET
    public ListJobResponse list() {
        ListJobResponse response = new ListJobResponse();

        response.setJobs(jobReader.listByAgent(agentState.getId()));

        return response;
    }

    @Path("/")
    @POST
    public Job createJob(@Valid JobData jobData) {
        Job job = new Job();

        job.setAgentId(agentState.getId());
        job.setCreated(dateTimeProvider.utcnow());
        job.setId(jobData.getId() != null ? jobData.getId() : UUID.randomUUID().toString());
        job.setOwnerId(jobData.getOwnerId());

        jobWriter.acceptJob(job);

        return job;
    }
}
