package ninja.onewaysidewalks.distcad.core;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

public class DateTimeProvider {

    public DateTime utcnow() {
        return DateTime.now(DateTimeZone.UTC);
    }
}
