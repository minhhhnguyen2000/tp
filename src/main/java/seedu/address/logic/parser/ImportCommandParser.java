package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_LESSON;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_URL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMETABLE_URL;

import java.util.stream.Stream;

import seedu.address.logic.commands.ImportCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.timetable.TimetableData;
import seedu.address.timetable.TimetableUrlParser;

public class ImportCommandParser implements Parser<ImportCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ImportCommand
     * and returns an ImportCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ImportCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TIMETABLE_URL);

        if (!arePrefixesPresent(argMultimap, PREFIX_TIMETABLE_URL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ImportCommand.MESSAGE_USAGE));
        }

        String url = argMultimap.getValue(PREFIX_TIMETABLE_URL).get();

        if (!TimetableUrlParser.isValidUrl(url)) {
            throw new ParseException(MESSAGE_INVALID_URL);
        }

        try {
            TimetableData data = TimetableUrlParser.parseTimetableUrl(url);
            return new ImportCommand(data);
        } catch (Exception e) {
            throw new ParseException(MESSAGE_INVALID_LESSON);
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
