package ninja.onewaysidewalks.distcad.agent.models.api;

import lombok.Data;
import ninja.onewaysidewalks.distcad.data.job.core.Job;

import java.util.List;

@Data
public class ListJobResponse {
    private List<Job> jobs;
}
