package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PRIORITY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_HW;
import static seedu.address.logic.commands.CommandTestUtil.PRIORITY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRIORITY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.model.assignment.Priority.MESSAGE_CONSTRAINTS;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_ASSIGNMENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.PrioritizeCommand;
import seedu.address.model.assignment.Priority;

public class PrioritizeCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, PrioritizeCommand.MESSAGE_USAGE);
    private PrioritizeCommandParser parser = new PrioritizeCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, PRIORITY_DESC, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + PRIORITY_DESC, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + PRIORITY_DESC, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_PRIORITY_DESC, MESSAGE_CONSTRAINTS); // invalid priority
        assertParseFailure(parser, "1" + NAME_DESC_HW, MESSAGE_INVALID_FORMAT); // not a priority
    }

    @Test
    public void parse_success() {
        Index targetIndex = INDEX_THIRD_ASSIGNMENT;
        String userInput = targetIndex.getOneBased() + PRIORITY_DESC;
        PrioritizeCommand expectedCommand = new PrioritizeCommand(targetIndex, new Priority(VALID_PRIORITY));
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
