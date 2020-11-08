package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAssignments.getTypicalProductiveNus;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ASSIGNMENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ASSIGNMENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.assignment.Assignment;
import seedu.address.testutil.AssignmentBuilder;

public class UnremindCommandTest {

    private Model model = new ModelManager(getTypicalProductiveNus(), new UserPrefs(), null);
    private final Index indexLastAssignment = Index.fromOneBased(
            model.getProductiveNus().getAssignmentList().size());

    @Test
    public void constructor_nullAssignment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new UnremindCommand(null));
    }

    @Test
    public void execute_validIndex_success() {
        // Remove first assignment from reminded assignment list
        remindFirstAssignment();
        Assignment assignmentToUnremind = model.getRemindedAssignmentsList().get(INDEX_FIRST_ASSIGNMENT.getZeroBased());
        UnremindCommand unremindCommand = new UnremindCommand(INDEX_FIRST_ASSIGNMENT);

        String expectedMessage = String.format(
                UnremindCommand.MESSAGE_UNREMIND_ASSIGNMENT_SUCCESS, assignmentToUnremind);

        ModelManager expectedModel = new ModelManager(model.getProductiveNus(), new UserPrefs(),
                model.getPreviousModel());
        expectedModel.setAssignment(model.getRemindedAssignmentsList().get(0), assignmentToUnremind);

        assertCommandSuccess(unremindCommand, model, expectedMessage, expectedModel);

        // Remove last assignment from reminded assignment list
        remindFirstAssignment();
        remindSecondAssignment();
        remindLastAssignment();
        Index indexLastRemindedAssignment = Index.fromOneBased(
                model.getRemindedAssignmentsList().size());

        Assignment assignmentToUnremindLast = model.getRemindedAssignmentsList()
                .get(indexLastRemindedAssignment.getZeroBased());
        UnremindCommand unremindCommandLast = new UnremindCommand(indexLastRemindedAssignment);

        String expectedMessageLast = String.format(
                UnremindCommand.MESSAGE_UNREMIND_ASSIGNMENT_SUCCESS, assignmentToUnremindLast);

        ModelManager expectedModelLast = new ModelManager(model.getProductiveNus(), new UserPrefs(),
                model.getPreviousModel());
        expectedModelLast.setAssignment(model.getRemindedAssignmentsList()
                .get(indexLastRemindedAssignment.getZeroBased()), assignmentToUnremindLast);

        assertCommandSuccess(unremindCommandLast, model, expectedMessageLast, expectedModelLast);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        // Reminded assignment list size + 1 (boundary value)
        Index outOfBoundIndex = Index.fromOneBased(model.getRemindedAssignmentsList().size() + 1);
        UnremindCommand unremindCommand = new UnremindCommand(outOfBoundIndex);

        assertCommandFailure(unremindCommand, model, UnremindCommand.MESSAGE_INVALID_DISPLAYED_REMINDERS_INDEX);

        // Index 0 (boundary value)
        Index zeroIndex = Index.fromZeroBased(0);
        UnremindCommand unremindCommand1 = new UnremindCommand(zeroIndex);

        assertCommandFailure(unremindCommand1, model, UnremindCommand.MESSAGE_INVALID_DISPLAYED_REMINDERS_INDEX);
    }

    @Test
    public void equals() {
        remindFirstAssignment();
        remindSecondAssignment();

        UnremindCommand unremindFirstCommand = new UnremindCommand(INDEX_FIRST_ASSIGNMENT);
        UnremindCommand unremindSecondCommand = new UnremindCommand(INDEX_SECOND_ASSIGNMENT);

        // same object -> returns true
        assertTrue(unremindFirstCommand.equals(unremindFirstCommand));

        // same values -> returns true
        UnremindCommand unremindFirstCommandCopy = new UnremindCommand(INDEX_FIRST_ASSIGNMENT);
        assertTrue(unremindFirstCommand.equals(unremindFirstCommandCopy));

        // different types -> returns false
        assertFalse(unremindFirstCommand.equals(1));

        // null -> returns false
        assertFalse(unremindFirstCommand.equals(null));

        // different assignment -> returns false
        assertFalse(unremindFirstCommand.equals(unremindSecondCommand));
    }

    // Set reminders for first assignment in filtered list
    private void remindFirstAssignment() {
        // Set reminders for assignment in filtered list in ProductiveNus
        Assignment assignmentInList = model.getProductiveNus().getAssignmentList()
                .get(INDEX_FIRST_ASSIGNMENT.getZeroBased());
        Assignment assignmentInListReminded = new AssignmentBuilder(assignmentInList).withRemindersSet().build();
        model.setAssignment(assignmentInList, assignmentInListReminded);
    }

    // Set reminders for second assignment in filtered list
    private void remindSecondAssignment() {
        // Set reminders for assignment in filtered list in ProductiveNus
        Assignment assignmentInList = model.getProductiveNus().getAssignmentList()
                .get(INDEX_SECOND_ASSIGNMENT.getZeroBased());
        Assignment assignmentInListReminded = new AssignmentBuilder(assignmentInList).withRemindersSet().build();
        model.setAssignment(assignmentInList, assignmentInListReminded);
    }

    // Set reminders for last assignment in filtered list
    private void remindLastAssignment() {
        // Set reminders for assignment in filtered list in ProductiveNus
        Assignment assignmentInList = model.getProductiveNus().getAssignmentList()
                .get(indexLastAssignment.getZeroBased());
        Assignment assignmentInListReminded = new AssignmentBuilder(assignmentInList).withRemindersSet().build();
        model.setAssignment(assignmentInList, assignmentInListReminded);
    }
}
