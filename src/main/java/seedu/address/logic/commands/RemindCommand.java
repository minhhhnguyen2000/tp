package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ASSIGNMENT;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.Done;
import seedu.address.model.assignment.Priority;
import seedu.address.model.assignment.Remind;
import seedu.address.model.assignment.Schedule;
import seedu.address.model.task.ModuleCode;
import seedu.address.model.task.Name;
import seedu.address.model.task.Time;

/**
 * Sets reminders for an assignment identified using it's displayed index from ProductiveNus.
 */
public class RemindCommand extends Command {

    public static final String COMMAND_WORD = "remind";

    public static final String MESSAGE_USAGE = "Format: " + COMMAND_WORD
            + " INDEX [MORE_INDEXES] (must be positive integers)";

    public static final String MESSAGE_REMIND_ASSIGNMENT_SUCCESS = "Set reminder for Assignment: %1$s";
    public static final String MESSAGE_REMIND_ASSIGNMENTS_SUCCESS = "Mark assignments as done: %1$s";

    // for single index
    public static final String MESSAGE_REMINDED_ASSIGNMENT = "This assignment already has reminders set.";

    // for multiple indexes
    public static final String MESSAGE_MULTIPLE_REMINDED_ASSIGNMENT = "This assignment already has reminders set: %1$s";
    public static final String MESSAGE_MULTIPLE_REMINDED_ASSIGNMENTS =
            "These assignments already have reminders set: %1$s";



    private final List<Index> targetIndexes;

    /**
     * Constructs a RemindCommand to set reminders to the specified assignment(s).
     * @param targetIndexes indexes of assignments in the filtered assignment list to remind
     */
    public RemindCommand(List<Index> targetIndexes) {
        requireNonNull(targetIndexes);
        this.targetIndexes = targetIndexes;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Assignment> lastShownList = model.getFilteredAssignmentList();
        List<Assignment> assignmentsToRemind = new ArrayList<>();
        List<Integer> assignmentsAlreadyReminded = new ArrayList<>();

        CommandLogic.checkForDuplicatedIndexes(targetIndexes);
        CommandLogic.checkForInvalidIndexes(targetIndexes, model, RemindCommand.MESSAGE_USAGE);

        boolean isMultipleIndexes = targetIndexes.size() > 1;
        boolean hasException = false;

        for (Index targetIndex : targetIndexes) {
            Assignment assignmentToRemind = lastShownList.get(targetIndex.getZeroBased());

            if (assignmentToRemind.isReminded() && model.hasAssignment(assignmentToRemind)) {
                hasException = true;
                assignmentsAlreadyReminded.add(targetIndex.getOneBased());
                continue;
            }

            assert(!assignmentToRemind.isReminded());
            Assignment remindedAssignment = createRemindedAssignment(assignmentToRemind);

            model.setAssignment(assignmentToRemind, remindedAssignment);
            model.updateFilteredAssignmentList(PREDICATE_SHOW_ALL_ASSIGNMENT);
            assignmentsToRemind.add(assignmentToRemind);
        }

        if (hasException) {
            if (isMultipleIndexes && assignmentsAlreadyReminded.size() > 1) {
                throw new CommandException(String.format(MESSAGE_MULTIPLE_REMINDED_ASSIGNMENTS,
                        assignmentsAlreadyReminded));
            } else if (isMultipleIndexes) {
                throw new CommandException(String.format(MESSAGE_MULTIPLE_REMINDED_ASSIGNMENT,
                        assignmentsAlreadyReminded));
            } else {
                throw new CommandException(MESSAGE_REMINDED_ASSIGNMENT);
            }
        }

        return isMultipleIndexes
                ? new CommandResult(String.format(MESSAGE_REMIND_ASSIGNMENTS_SUCCESS, assignmentsToRemind))
                : new CommandResult(String.format(MESSAGE_REMIND_ASSIGNMENT_SUCCESS, assignmentsToRemind));
    }

    /**
     * Creates and returns a {@code Assignment} with the details of {@code assignmentToRemind}.
     */
    private static Assignment createRemindedAssignment(Assignment assignmentToRemind) {
        assert assignmentToRemind != null;

        Name updatedName = assignmentToRemind.getName();
        Time updatedDeadline = assignmentToRemind.getDeadline();
        ModuleCode updatedModuleCode = assignmentToRemind.getModuleCode();
        Remind updatedRemind = assignmentToRemind.getRemind().setReminder();
        Schedule updatedSchedule = assignmentToRemind.getSchedule();
        Priority priority = assignmentToRemind.getPriority();
        Done updatedDone = assignmentToRemind.getDone();

        return new Assignment(updatedName, updatedDeadline, updatedModuleCode, updatedRemind, updatedSchedule,
                priority, updatedDone);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RemindCommand // instanceof handles nulls
                && targetIndexes.equals(((RemindCommand) other).targetIndexes)); // state check
    }
}
