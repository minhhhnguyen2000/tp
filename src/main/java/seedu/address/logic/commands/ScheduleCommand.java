package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DO_AFTER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DO_BEFORE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXPECTED_HOURS;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.Done;
import seedu.address.model.assignment.Priority;
import seedu.address.model.assignment.Remind;
import seedu.address.model.assignment.Schedule;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.task.ModuleCode;
import seedu.address.model.task.Name;
import seedu.address.model.task.Task;
import seedu.address.model.task.Time;

/**
 * Schedules an assignment identified using it's displayed index from ProductiveNus.
 */
public class ScheduleCommand extends Command {

    public static final String START_TIME = "06:00";
    public static final String END_TIME = "23:59";
    public static final int MAX_HOURS = 5;
    public static final int MIN_HOURS = 1;
    public static final String COMMAND_WORD = "schedule";
    public static final String MESSAGE_SCHEDULE_ASSIGNMENT_SUCCESS = "Schedule Assignment: %1$s";
    public static final String MESSAGE_SCHEDULE_ASSIGNMENT_FAIL = "No possible schedule";
    public static final String MESSAGE_ASSIGNMENT_DUE = "The deadline of this assignment is over";
    public static final String MESSAGE_USAGE = "Format: " + COMMAND_WORD + " INDEX (must be a positive integer) "
            + PREFIX_EXPECTED_HOURS + "EXPECTED HOURS (must be between " + MIN_HOURS + " and " + MAX_HOURS + " hours) "
            + PREFIX_DO_AFTER + "AFTER " + PREFIX_DO_BEFORE + "BEFORE";

    private static final LocalTime WORKING_START_TIME = LocalTime.parse(START_TIME, DateTimeFormatter.ISO_TIME);
    private static final LocalTime WORKING_END_TIME = LocalTime.parse(END_TIME, DateTimeFormatter.ISO_TIME);

    private final Index targetIndex;
    private final Time doBefore;
    private final Time doAfter;
    private final int expectedHours;

    /**
     * Constructs a ScheduleCommand to set reminders to the specified assignment.
     * @param targetIndex index of the assignment in the filtered assignment list to edit
     */
    public ScheduleCommand(Index targetIndex, int expectedHours, Time doAfter, Time doBefore) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
        this.doAfter = doAfter;
        this.doBefore = doBefore;
        this.expectedHours = expectedHours;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Assignment> lastShownList = model.getFilteredAssignmentList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ASSIGNMENT_DISPLAYED_INDEX);
        }

        Assignment assignmentToSchedule = lastShownList.get(targetIndex.getZeroBased());

        // assignment is already due
        if (assignmentToSchedule.getDeadline().toLocalDateTime().isBefore(LocalDateTime.now())) {
            throw new CommandException(MESSAGE_ASSIGNMENT_DUE);
        }

        List<Task> taskList = new ArrayList<>(model.getFilteredTaskList());
        taskList.remove(assignmentToSchedule);
        Schedule schedule = createValidSchedule(assignmentToSchedule, taskList);

        Assignment scheduledAssignment = createScheduledAssignment(assignmentToSchedule, schedule);

        model.setAssignment(assignmentToSchedule, scheduledAssignment);
        return new CommandResult(String.format(MESSAGE_SCHEDULE_ASSIGNMENT_SUCCESS, scheduledAssignment));
    }

    private Schedule createValidSchedule(Assignment assignmentToSchedule, List<Task> taskList) throws CommandException {
        LocalDateTime after = doAfter.toLocalDateTime();
        LocalDateTime before = doBefore.toLocalDateTime();
        if (assignmentToSchedule.getDeadline().isBefore(doBefore)) {
            before = assignmentToSchedule.getDeadline().toLocalDateTime();
        }
        if (after.isBefore(LocalDateTime.now())) {
            after = LocalDateTime.now();
        }

        after = roundToHour(after.plusMinutes(59));

        List<LocalDateTime> possibleTime = generateAllPossibleTime(after, before, taskList);

        if (possibleTime.isEmpty()) {
            throw new CommandException(MESSAGE_SCHEDULE_ASSIGNMENT_FAIL);
        }
        return getRandom(possibleTime);
    }

    private LocalDateTime roundToHour(LocalDateTime time) {
        return time.truncatedTo(ChronoUnit.HOURS);
    }

    private Schedule getRandom(List<LocalDateTime> list) {
        int rnd = new Random().nextInt(list.size());
        Time suggestedStartTime = new Time(list.get(rnd));
        Time suggestedEndTime = new Time(list.get(rnd).plusHours(expectedHours));
        return new Schedule(suggestedStartTime, suggestedEndTime);
    }

    private List<LocalDateTime> generateAllPossibleTime(LocalDateTime start, LocalDateTime end, List<Task> taskList) {
        List<LocalDateTime> possibleTime = new ArrayList<>();
        for (LocalDateTime i = start; !i.plusHours(expectedHours).isAfter(end); i = i.plusHours(1)) {
            boolean canSchedule = true;
            // working hours
            if (!isWorkingHour(i, i.plusHours(expectedHours))) {
                canSchedule = false;
            }
            // no overlap
            for (Task j: taskList) {
                if (!haveNoOverlap(i, i.plusHours(expectedHours), j)) {
                    if (canSchedule) {
                        System.out.println(i + " " + j.getTime());
                    }
                    canSchedule = false;
                }
            }
            if (canSchedule) {
                possibleTime.add(i);
            }
        }
        return possibleTime;
    }

    private boolean isWorkingHour(LocalDateTime start, LocalDateTime end) {
        return !start.toLocalTime().isBefore(WORKING_START_TIME) && !end.toLocalTime().isAfter(WORKING_END_TIME)
                && !end.toLocalTime().isBefore(WORKING_START_TIME);
    }

    private boolean haveNoOverlap(LocalDateTime start, LocalDateTime end, Task task) {
        if (task instanceof Assignment) {
            if (!((Assignment) task).getSchedule().isScheduled()) {
                return true;
            }
            return (!end.isAfter(((Assignment) task).getSchedule().getSuggestedStartTime().toLocalDateTime())
                    || (!start.isBefore(((Assignment) task).getSchedule().getSuggestedEndTime().toLocalDateTime())));
        }
        return (!end.isAfter(task.getTime().toLocalDateTime())
                || (!start.isBefore(((Lesson) task).getEndTime().toLocalDateTime())));
    }

    /**
     * Creates and returns a {@code Assignment} with the details of {@code assignmentToSchedule}.
     */
    private Assignment createScheduledAssignment(Assignment assignmentToSchedule, Schedule schedule) {
        assert assignmentToSchedule != null;

        Name updatedName = assignmentToSchedule.getName();
        Time updatedDeadline = assignmentToSchedule.getDeadline();
        ModuleCode updatedModuleCode = assignmentToSchedule.getModuleCode();
        Remind updatedRemind = assignmentToSchedule.getRemind();
        Priority priority = assignmentToSchedule.getPriority();
        Done updatedDone = assignmentToSchedule.getDone();

        return new Assignment(updatedName, updatedDeadline, updatedModuleCode, updatedRemind, schedule,
                priority, updatedDone);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ScheduleCommand // instanceof handles nulls
                && targetIndex.equals(((ScheduleCommand) other).targetIndex)
                && doAfter.equals(((ScheduleCommand) other).doAfter)
                && doBefore.equals(((ScheduleCommand) other).doBefore)
                && expectedHours == ((ScheduleCommand) other).expectedHours); // state check
    }
}
