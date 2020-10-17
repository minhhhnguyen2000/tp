package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DO_BEFORE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXPECTED_TIME;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.Deadline;
import seedu.address.model.assignment.ModuleCode;
import seedu.address.model.assignment.Name;
import seedu.address.model.assignment.Remind;
import seedu.address.model.assignment.Schedule;
import seedu.address.model.assignment.Task;
import seedu.address.model.lesson.Lesson;

/**
 * Sets reminders for an assignment identified using it's displayed index from the address book.
 */
public class ScheduleCommand extends Command {

    public static final String COMMAND_WORD = "schedule";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Schedule the assignment identified by the index number used in the displayed assignment list.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_EXPECTED_TIME + "EXPECTED HOURS "
            + PREFIX_DO_BEFORE + "BEFORE "
            + "Example: " + COMMAND_WORD + " 1 " + PREFIX_EXPECTED_TIME + "2 " + PREFIX_DO_BEFORE + "23-12-2020 2359";

    public static final String MESSAGE_SCHEDULE_ASSIGNMENT_SUCCESS = "Schedule Assignment: %1$s";
    private static final LocalTime WORKING_START_TIME = LocalTime.parse("06:00", DateTimeFormatter.ISO_TIME);
    private static final LocalTime WORKING_END_TIME = LocalTime.parse("23:59", DateTimeFormatter.ISO_TIME);

    private final Index targetIndex;
    private final Deadline doBefore;
    private final int expectedTime;

    /**
     * Constructs a RemindCommand to set reminders to the specified assignment.
     * @param targetIndex index of the assignment in the filtered assignment list to edit
     */
    public ScheduleCommand(Index targetIndex, int expectedTime, Deadline doBefore) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
        this.doBefore = doBefore;
        this.expectedTime = expectedTime;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Assignment> lastShownList = model.getFilteredAssignmentList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ASSIGNMENT_DISPLAYED_INDEX);
        }

        Assignment assignmentToSchedule = lastShownList.get(targetIndex.getZeroBased());

        Schedule schedule = createValidSchedule(assignmentToSchedule, model.getFilteredTaskList());

        Assignment scheduledAssignment = createScheduledAssignment(assignmentToSchedule, schedule);

        model.setAssignment(assignmentToSchedule, scheduledAssignment);
        return new CommandResult(String.format(MESSAGE_SCHEDULE_ASSIGNMENT_SUCCESS, scheduledAssignment));
    }

    private Schedule createValidSchedule(Assignment assignmentToSchedule, List<Task> taskList) throws CommandException {
        LocalDateTime newDeadline = doBefore.toLocalDateTime();
        if (assignmentToSchedule.getDeadline().isBefore(doBefore)) {
            newDeadline = assignmentToSchedule.getDeadline().toLocalDateTime();
        }

        LocalDateTime nearestTime = roundToHour(LocalDateTime.now().plusHours(1));

        List<LocalDateTime> possibleTime = generateAllPossibleTime(nearestTime,
                newDeadline, taskList);

        if (possibleTime.isEmpty()) {
            throw new CommandException("No possible schedule");
        }
        return getRandom(possibleTime);
    }

    private LocalDateTime roundToHour(LocalDateTime time) {
        int min = time.getMinute();
        time = time.minusMinutes(min % 60);
        return time;
    }

    private Schedule getRandom(List<LocalDateTime> list) {
        int rnd = new Random().nextInt(list.size());
        Deadline suggestedStartTime = new Deadline(list.get(rnd));
        Deadline suggestedEndTime = new Deadline(list.get(rnd).plusHours(expectedTime));
        return new Schedule(suggestedStartTime, suggestedEndTime);
    }

    private List<LocalDateTime> generateAllPossibleTime(LocalDateTime start, LocalDateTime end, List<Task> taskList) {
        List<LocalDateTime> possibleTime = new ArrayList<>();
        for (LocalDateTime i = start; i.isBefore(end); i = i.plusHours(1)) {
            boolean can = true;
            // working hours
            if (!isWorkingHour(i, i.plusHours(expectedTime))) {
                can = false;
            }
            // no overlap
            for (Task j: taskList) {
                if (overlap(i, i.plusHours(expectedTime), j)) {
                    can = false;
                }
            }

            if (can) {
                possibleTime.add(i);
            }
        }
        return possibleTime;
    }

    private boolean isWorkingHour(LocalDateTime start, LocalDateTime end) {
        return !start.toLocalTime().isBefore(WORKING_START_TIME) && !end.toLocalTime().isAfter(WORKING_END_TIME);
    }

    private boolean overlap(LocalDateTime start, LocalDateTime end, Task task) {
        if (task instanceof Assignment) {
            if (!((Assignment) task).getSchedule().isScheduled()) {
                return false;
            }
            return (!start.isBefore(((Assignment) task).getSchedule().getSuggestedStartTime().toLocalDateTime())
                    && !end.isAfter(((Assignment) task).getSchedule().getSuggestedEndTime().toLocalDateTime()));
        }
        return (!start.isBefore(task.getTime().toLocalDateTime())
                && !end.isAfter(((Lesson) task).getEndTime().toLocalDateTime()));
    }

    /**
     * Creates and returns a {@code Assignment} with the details of {@code assignmentToSchedule}.
     */
    private Assignment createScheduledAssignment(Assignment assignmentToSchedule, Schedule schedule) {
        assert assignmentToSchedule != null;

        Name updatedName = assignmentToSchedule.getName();
        Deadline updatedDeadline = assignmentToSchedule.getDeadline();
        ModuleCode updatedModuleCode = assignmentToSchedule.getModuleCode();
        Remind updatedRemind = assignmentToSchedule.getRemind();

        return new Assignment(updatedName, updatedDeadline, updatedModuleCode, updatedRemind, schedule);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ScheduleCommand // instanceof handles nulls
                && targetIndex.equals(((ScheduleCommand) other).targetIndex)); // state check
    }
}
