package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMETABLE_URL;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.timetable.TimetableData;

public class ImportCommand extends Command {

    public static final String COMMAND_WORD = "import";

    public static final String MESSAGE_USAGE = "Format: " + COMMAND_WORD + " "
            + PREFIX_TIMETABLE_URL + "TIMETABLE URL ";
    public static final String MESSAGE_SUCCESS = "Timetable imported.";

    private final TimetableData data;

    /**
     * Creates an ImportCommand to add the specified {@code Assignment}
     */
    public ImportCommand(TimetableData data) {
        requireNonNull(data);
        this.data = data;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.importTimetable(data);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
