package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.ImportCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTimetableData.DATA;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class ImportCommandTest {
    @Test
    public void constructor_nullAssignment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ImportCommand(null));
    }

    @Test
    public void execute_import_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();
        expectedModel.importTimetable(DATA);
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_SUCCESS);
        assertCommandSuccess(new ImportCommand(DATA), model, expectedCommandResult, expectedModel);
    }
}
