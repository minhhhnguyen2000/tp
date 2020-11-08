package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showAssignmentAtIndex;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ASSIGNMENT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAssignments.getTypicalProductiveNus;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ASSIGNMENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ASSIGNMENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.assignment.Assignment;
import seedu.address.testutil.AssignmentBuilder;

public class UnprioritizeCommandTest {
    private Model model = new ModelManager(getTypicalProductiveNus(), new UserPrefs(), null);

    @Test
    public void constructor_nullAssignment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new UnprioritizeCommand(null));
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        markFirstAssignmentAsHighPriority();
        Assignment assignmentToUnprioritize = model.getFilteredAssignmentList()
                .get(INDEX_FIRST_ASSIGNMENT.getZeroBased());
        UnprioritizeCommand unprioritizeCommand = new UnprioritizeCommand(INDEX_FIRST_ASSIGNMENT);

        String expectedMessage = String.format(
                UnprioritizeCommand.MESSAGE_UNPRIORITIZE_ASSIGNMENT_SUCCESS, assignmentToUnprioritize);

        ModelManager expectedModel = new ModelManager(model.getProductiveNus(), new UserPrefs(),
                model.getPreviousModel());
        expectedModel.setAssignment(model.getFilteredAssignmentList().get(0), assignmentToUnprioritize);

        assertCommandSuccess(unprioritizeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredAssignmentList().size() + 1);
        UnprioritizeCommand unprioritizeCommand = new UnprioritizeCommand(outOfBoundIndex);

        assertCommandFailure(unprioritizeCommand, model, Messages.MESSAGE_INVALID_ASSIGNMENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showAssignmentAtIndex(model, INDEX_FIRST_ASSIGNMENT);

        markFirstAssignmentAsHighPriority();
        model.updateFilteredAssignmentList(PREDICATE_SHOW_ALL_ASSIGNMENT);

        Assignment assignmentToUnprioritize = model.getFilteredAssignmentList()
                .get(INDEX_FIRST_ASSIGNMENT.getZeroBased());
        UnprioritizeCommand unprioritizeCommand = new UnprioritizeCommand(INDEX_FIRST_ASSIGNMENT);

        String expectedMessage = String.format(
                UnprioritizeCommand.MESSAGE_UNPRIORITIZE_ASSIGNMENT_SUCCESS, assignmentToUnprioritize);

        Model expectedModel = new ModelManager(model.getProductiveNus(), new UserPrefs(), model.getPreviousModel());
        expectedModel.setAssignment(model.getFilteredAssignmentList().get(0), assignmentToUnprioritize);

        assertCommandSuccess(unprioritizeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showAssignmentAtIndex(model, INDEX_FIRST_ASSIGNMENT);

        Index outOfBoundIndex = INDEX_SECOND_ASSIGNMENT;
        // ensures that outOfBoundIndex is still in bounds of ProductiveNus list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getProductiveNus().getAssignmentList().size());

        UnprioritizeCommand unprioritizeCommand = new UnprioritizeCommand(outOfBoundIndex);

        assertCommandFailure(unprioritizeCommand, model, Messages.MESSAGE_INVALID_ASSIGNMENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_noPriorityAssignmentUnfilteredList_failure() {
        UnprioritizeCommand unprioritizeCommand = new UnprioritizeCommand(INDEX_FIRST_ASSIGNMENT);

        assertCommandFailure(unprioritizeCommand, model, UnprioritizeCommand.MESSAGE_UNPRIORITIZE_ASSIGNMENT);
    }

    @Test
    public void execute_noPriorityAssignmentFilteredList_failure() {
        showAssignmentAtIndex(model, INDEX_FIRST_ASSIGNMENT);

        UnprioritizeCommand unprioritizeCommand = new UnprioritizeCommand(INDEX_FIRST_ASSIGNMENT);

        assertCommandFailure(unprioritizeCommand, model, UnprioritizeCommand.MESSAGE_UNPRIORITIZE_ASSIGNMENT);
    }

    @Test
    public void equals() {
        markFirstAssignmentAsHighPriority();
        markSecondAssignmentAsHighPriority();

        UnprioritizeCommand unprioritizeFirstCommand = new UnprioritizeCommand(INDEX_FIRST_ASSIGNMENT);
        UnprioritizeCommand unprioritizeSecondCommand = new UnprioritizeCommand(INDEX_SECOND_ASSIGNMENT);

        // same object -> returns true
        assertTrue(unprioritizeFirstCommand.equals(unprioritizeFirstCommand));

        // same values -> returns true
        UnprioritizeCommand unprioritizeFirstCommandCopy = new UnprioritizeCommand(INDEX_FIRST_ASSIGNMENT);
        assertTrue(unprioritizeFirstCommand.equals(unprioritizeFirstCommandCopy));

        // different types -> returns false
        assertFalse(unprioritizeFirstCommand.equals(1));

        // null -> returns false
        assertFalse(unprioritizeFirstCommand.equals(null));

        // different assignment -> returns false
        assertFalse(unprioritizeFirstCommand.equals(unprioritizeSecondCommand));
    }

    // Prioritize first assignment in filtered list as high priority
    private void markFirstAssignmentAsHighPriority() {
        Assignment assignmentInList = model.getProductiveNus().getAssignmentList()
                .get(INDEX_FIRST_ASSIGNMENT.getZeroBased());
        Assignment assignmentInListMarkAsHighPriority = new AssignmentBuilder(assignmentInList).withPriority("HIGH")
                .build();
        model.setAssignment(assignmentInList, assignmentInListMarkAsHighPriority);
    }

    // Prioritize second assignment in filtered list as high priority
    private void markSecondAssignmentAsHighPriority() {
        Assignment assignmentInList = model.getProductiveNus().getAssignmentList()
                .get(INDEX_SECOND_ASSIGNMENT.getZeroBased());
        Assignment assignmentInListMarkAsHighPriority = new AssignmentBuilder(assignmentInList).withPriority("HIGH")
                .build();
        model.setAssignment(assignmentInList, assignmentInListMarkAsHighPriority);
    }
}
