package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.showAssignmentAtIndex;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAssignments.getTypicalProductiveNus;
import static seedu.address.testutil.TypicalExpectedHours.EXPECTED_HOUR_MAX;
import static seedu.address.testutil.TypicalExpectedHours.EXPECTED_HOUR_MIN;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ASSIGNMENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ASSIGNMENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_ASSIGNMENT;
import static seedu.address.testutil.TypicalTime.EARLY_TIME;
import static seedu.address.testutil.TypicalTime.LATE_TIME;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.assignment.Assignment;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code ScheduleCommand}.
 */
public class ScheduleCommandTest {

    private Model model = new ModelManager(getTypicalProductiveNus(), new UserPrefs(), null);

    public static void assertExecuteSuccess(Command command, Model actualModel, String expectedMessage) {
        try {
            CommandResult expectedCommandResult = new CommandResult(expectedMessage);
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    @Test
    public void constructor_nullAssignment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ScheduleCommand(null, 0, null, null));
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Assignment assignmentToSchedule = model.getFilteredAssignmentList().get(INDEX_THIRD_ASSIGNMENT.getZeroBased());

        ScheduleCommand scheduleCommand = new ScheduleCommand(INDEX_THIRD_ASSIGNMENT, EXPECTED_HOUR_MIN,
                EARLY_TIME, LATE_TIME);

        String expectedMessage = String.format(ScheduleCommand.MESSAGE_SCHEDULE_ASSIGNMENT_SUCCESS,
                assignmentToSchedule);
        assertExecuteSuccess(scheduleCommand, model, expectedMessage);
        assert(assignmentToSchedule.getSchedule().isScheduled());
    }

    @Test
    public void execute_validIndexUnfilteredList_overdueFail() {
        ScheduleCommand scheduleCommand = new ScheduleCommand(INDEX_FIRST_ASSIGNMENT, EXPECTED_HOUR_MIN,
                EARLY_TIME, LATE_TIME);

        String expectedMessage = ScheduleCommand.MESSAGE_ASSIGNMENT_DUE;

        assertCommandFailure(scheduleCommand, model, expectedMessage);
    }

    @Test
    public void execute_validIndexUnfilteredList_noScheduleFail() {
        ScheduleCommand scheduleCommand = new ScheduleCommand(INDEX_THIRD_ASSIGNMENT, EXPECTED_HOUR_MIN,
                LATE_TIME, EARLY_TIME);

        String expectedMessage = ScheduleCommand.MESSAGE_SCHEDULE_ASSIGNMENT_FAIL;

        assertCommandFailure(scheduleCommand, model, expectedMessage);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredAssignmentList().size() + 1);
        ScheduleCommand scheduleCommand = new ScheduleCommand(outOfBoundIndex, EXPECTED_HOUR_MIN,
                EARLY_TIME, LATE_TIME);
        assertCommandFailure(scheduleCommand, model, Messages.MESSAGE_INVALID_ASSIGNMENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showAssignmentAtIndex(model, INDEX_THIRD_ASSIGNMENT);

        Assignment assignmentToSchedule = model.getFilteredAssignmentList().get(INDEX_FIRST_ASSIGNMENT.getZeroBased());
        ScheduleCommand scheduleCommand = new ScheduleCommand(INDEX_FIRST_ASSIGNMENT, EXPECTED_HOUR_MIN,
                EARLY_TIME, LATE_TIME);

        String expectedMessage = String.format(ScheduleCommand.MESSAGE_SCHEDULE_ASSIGNMENT_SUCCESS,
                assignmentToSchedule);

        assertExecuteSuccess(scheduleCommand, model, expectedMessage);
        assert(assignmentToSchedule.getSchedule().isScheduled());
    }

    @Test
    public void execute_validIndexFilteredList_overdueFail() {
        showAssignmentAtIndex(model, INDEX_FIRST_ASSIGNMENT);

        ScheduleCommand scheduleCommand = new ScheduleCommand(INDEX_FIRST_ASSIGNMENT, EXPECTED_HOUR_MIN,
                EARLY_TIME, LATE_TIME);

        String expectedMessage = ScheduleCommand.MESSAGE_ASSIGNMENT_DUE;

        assertCommandFailure(scheduleCommand, model, expectedMessage);
    }

    @Test
    public void execute_validIndexFilteredList_noScheduleFail() {
        showAssignmentAtIndex(model, INDEX_THIRD_ASSIGNMENT);

        ScheduleCommand scheduleCommand = new ScheduleCommand(INDEX_FIRST_ASSIGNMENT, EXPECTED_HOUR_MIN,
                LATE_TIME, EARLY_TIME);

        String expectedMessage = ScheduleCommand.MESSAGE_SCHEDULE_ASSIGNMENT_FAIL;

        assertCommandFailure(scheduleCommand, model, expectedMessage);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showAssignmentAtIndex(model, INDEX_FIRST_ASSIGNMENT);

        Index outOfBoundIndex = INDEX_SECOND_ASSIGNMENT;

        // ensures that outOfBoundIndex is still in bounds of ProductiveNus list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getProductiveNus().getAssignmentList().size());

        ScheduleCommand scheduleCommand = new ScheduleCommand(outOfBoundIndex, EXPECTED_HOUR_MIN,
                EARLY_TIME, LATE_TIME);
        assertCommandFailure(scheduleCommand, model, Messages.MESSAGE_INVALID_ASSIGNMENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        ScheduleCommand scheduleFirstCommand = new ScheduleCommand(INDEX_FIRST_ASSIGNMENT, EXPECTED_HOUR_MIN,
                EARLY_TIME, LATE_TIME);
        ScheduleCommand scheduleSecondCommand = new ScheduleCommand(INDEX_SECOND_ASSIGNMENT, EXPECTED_HOUR_MAX,
                EARLY_TIME, LATE_TIME);

        // same object -> returns true
        assertTrue(scheduleFirstCommand.equals(scheduleFirstCommand));

        // same values -> returns true
        ScheduleCommand scheduleFirstCommandCopy = new ScheduleCommand(INDEX_FIRST_ASSIGNMENT, EXPECTED_HOUR_MIN,
                EARLY_TIME, LATE_TIME);
        assertTrue(scheduleFirstCommand.equals(scheduleFirstCommandCopy));

        // different types -> returns false
        assertFalse(scheduleFirstCommand.equals(1));

        // null -> returns false
        assertFalse(scheduleFirstCommand.equals(null));

        // different assignment -> returns false
        assertFalse(scheduleFirstCommand.equals(scheduleSecondCommand));
    }
}
