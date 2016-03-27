package ninja.onewaysidewalks.distcad.data.job.core;

import java.util.List;

public interface JobReader {
    Job getById(String id);
    List<Job> listByAgent(String agentId);
}
