package seedu.address.model.assignment;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.model.task.Time;

public class Schedule {
    // message constraints occurs if saved data is modified wrongly
    public static final String MESSAGE_CONSTRAINTS = "Suggested start time should be"
            + "before suggested end time";
    public static final String START_TIME_MESSAGE_CONSTRAINS = "Suggested start time "
            + "should only be in the format 'dd-MM-uuuu HHmm', and contain a valid date and time";
    public static final String END_TIME_MESSAGE_CONSTRAINS = "Suggested end time "
            + "should only be in the format 'dd-MM-uuuu HHmm', and contain a valid date and time";
    public static final String NOT_SCHEDULED_CONSTRAINS = "Assignment is not scheduled but "
            + "suggest start time or end time is present";
    private final boolean schedule;
    private final Time suggestedStartTime;
    private final Time suggestedEndTime;

    /**
     * Constructs a false schedule
     */
    public Schedule() {
        schedule = false;
        suggestedStartTime = null;
        suggestedEndTime = null;
    }

    /**
     * Constructs a suggested schedule
     */
    public Schedule(Time suggestedStartTime, Time suggestedEndTime) {
        requireAllNonNull(suggestedStartTime, suggestedEndTime);
        checkArgument(isValidSchedule(true, suggestedStartTime, suggestedEndTime), MESSAGE_CONSTRAINTS);
        schedule = true;
        this.suggestedStartTime = suggestedStartTime;
        this.suggestedEndTime = suggestedEndTime;
    }

    /**
     * Returns true if a given schedule is a valid schedule
     */
    public static boolean isValidSchedule(boolean schedule, Time suggestedStartTime, Time suggestedEndTime) {
        if (!schedule) {
            return (suggestedStartTime == null && suggestedEndTime == null);
        } else {
            requireAllNonNull(suggestedStartTime, suggestedEndTime);
            return !suggestedEndTime.isBefore(suggestedStartTime);
        }
    }

    public boolean isScheduled() {
        return schedule;
    }

    public Time getSuggestedStartTime() {
        return suggestedStartTime;
    }

    public Time getSuggestedEndTime() {
        return suggestedEndTime;
    }
}
