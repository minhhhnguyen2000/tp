package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ListCommandParser implements Parser<ListCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ListCommand
     * and returns a ListCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListCommand parse(String args) throws ParseException {
        boolean hasArgumentIndex = args.matches("(.+|[$&+,:;=?@#|'<>.^*()%!-])$"); //Has argument
        if (!hasArgumentIndex) {
            return new ListCommand();
        } else {
            boolean isWithinArgumentRange = args.trim().matches("^([1-9]|[1-4][0-9]|50)$"); //Argument range is 1 to 50
            if (isWithinArgumentRange) {
                Index index = ParserUtil.parseListIndex(args);
                return new ListCommand(index);
            } else {
                throw new ParseException(ListCommand.MESSAGE_INDEX_NOT_IN_RANGE);
            }
        }
    }
}
