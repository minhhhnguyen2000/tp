package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.VALID_PRIORITY;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAssignments.getTypicalProductiveNus;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ASSIGNMENT;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ProductiveNus;
import seedu.address.model.UserPrefs;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.Priority;
import seedu.address.testutil.AssignmentBuilder;

public class PrioritizeCommandTest {
    private Model model = new ModelManager(getTypicalProductiveNus(), new UserPrefs(), null);

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Assignment prioritizedAssignment = new AssignmentBuilder(model.getFilteredAssignmentList().get(0))
                .withPriority(VALID_PRIORITY).build();
        PrioritizeCommand prioritizeCommand = new PrioritizeCommand(INDEX_FIRST_ASSIGNMENT,
                new Priority(VALID_PRIORITY));

        String expectedMessage = String.format(PrioritizeCommand.MESSAGE_PRIORITIZE_ASSIGNMENT_SUCCESS,
                prioritizedAssignment);

        Model expectedModel = new ModelManager(new ProductiveNus(model.getProductiveNus()), new UserPrefs(), null);
        expectedModel.setAssignment(model.getFilteredAssignmentList().get(0), prioritizedAssignment);

        assertCommandSuccess(prioritizeCommand, model, expectedMessage, expectedModel);
    }
}
