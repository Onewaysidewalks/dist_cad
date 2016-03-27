package ninja.onewaysidewalks.distcad.data.job.inmemory;

import lombok.extern.slf4j.Slf4j;
import ninja.onewaysidewalks.distcad.data.job.core.Job;
import ninja.onewaysidewalks.distcad.data.job.core.JobReader;
import ninja.onewaysidewalks.distcad.data.job.core.JobWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;

public class InMemoryJobRepository {

    static ConcurrentHashMap<String, Job> JOBS = new ConcurrentHashMap<String, Job>();

    @Slf4j
    public static class InMemoryJobReader implements JobReader {
        @Override
        public Job getById(String id) {
            log.info("looking for job with id: " + id);

            return JOBS.get(id);
        }

        @Override
        public List<Job> listByAgent(final String agentId) {

            log.info("looking for jobs with agent id: " + agentId);

            final List<Job> jobList = new ArrayList<>();

            JOBS.forEach((s, job) -> {
                if (job.getAgentId().equalsIgnoreCase(agentId)) {
                    jobList.add(job);
                }
            });

            return jobList;
        }
    }

    @Slf4j
    public static class InMemoryJobWriter implements JobWriter {
        @Override
        public void acceptJob(Job job) {
            log.info("accepting job: " + job.toString());
            JOBS.put(job.getId(), job);
        }
    }
}
