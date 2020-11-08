package seedu.address.model.assignment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTime.EARLY_TIME;
import static seedu.address.testutil.TypicalTime.LATE_TIME;

import org.junit.jupiter.api.Test;

import seedu.address.model.task.Time;

public class ScheduleTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Schedule(null, null));
    }

    @Test
    public void constructor_invalidDeadline_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Schedule(LATE_TIME, EARLY_TIME));
    }

    @Test
    public void isValidSchedule() {
        // null schedule
        assertThrows(NullPointerException.class, () -> Schedule.isValidSchedule(true, null, null));
        // invalid schedules
        assertFalse(Schedule.isValidSchedule(false, new Time("30-04-2020 1800"),
                null)); // no schedule but start time is present
        assertFalse(Schedule.isValidSchedule(false, null,
                new Time("30-04-2020 1800"))); // no schedule but end time is present
        assertFalse(Schedule.isValidSchedule(false, new Time("30-04-2020 1800"),
                new Time("30-04-2020 1800"))); // no schedule but start time and end time are present
        assertFalse(Schedule.isValidSchedule(true, new Time("30-04-2020 1800"),
                new Time("30-04-2020 1759"))); // end time is before start time

        // valid schedules
        assertTrue(Schedule.isValidSchedule(false, null, null)); // no schedule
        assertTrue(Schedule.isValidSchedule(true, new Time("29-04-2020 1800"),
                new Time("30-04-2020 1800"))); // valid schedule with start time before end time
        assertTrue(Schedule.isValidSchedule(true, new Time("30-04-2020 1800"),
                new Time("30-04-2020 1800"))); // valid schedule with start time equals end time
        assertTrue(Schedule.isValidSchedule(true, new Time("20-01-2001 0000"), new Time("20-01-2030 2359")));
    }
}
