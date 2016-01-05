package ninja.onewaysidewalks.distcad.data.job.core;

import lombok.Data;
import org.joda.time.DateTime;

@Data
public class Job {
    private String id;
    private String ownerId;
    private String agentId;
    private DateTime created;
    private DateTime lastRan;
}
