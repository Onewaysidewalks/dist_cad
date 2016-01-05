package ninja.onewaysidewalks.distcad.data.job.core;

public interface JobReader {
    Job getById(String id);
    Job[] listByAgent(String agentId);
}
