package ninja.onewaysidewalks.distcad.coordinator.models;

import lombok.Data;

@Data
public class CoordinatorRequest {
    /**
     * Identifier of the job that should be cadenced.
     */
    private String id;

    /**
     * a serialized string of data to include with the cadenced job. should be immutable properties that dont change
     * (Contains things like user id, etc.)
     */
    private String data;
}
