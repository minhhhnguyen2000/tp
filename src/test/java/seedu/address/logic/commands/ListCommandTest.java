package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAssignments.getTypicalProductiveNus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Optional;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.task.Time;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;
    private Optional<Index> index;

    private Predicate<Assignment> showLimitedAssignments() {
        return assignment -> {
            DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern(Time.TIME_DATE_TIME_FORMAT)
                    .withResolverStyle(ResolverStyle.STRICT);
            String dateAndTimeToParse = assignment.getDeadline().value;
            LocalDateTime currentDateAndTime = LocalDateTime.now();
            assert index.isPresent();
            int zeroBaseValue = index.get().getZeroBased();
            long inputNumberOfDays = Optional.of(zeroBaseValue).get();
            LocalDateTime lastDateAndTime = currentDateAndTime.plusDays(inputNumberOfDays);
            LocalDateTime parsedDateAndTime = LocalDateTime.parse(dateAndTimeToParse, inputFormat);

            boolean isAfterCurrentDateAndTime = parsedDateAndTime.isAfter(currentDateAndTime);
            boolean isBeforeLastDateAndTime = parsedDateAndTime.isBefore(lastDateAndTime);

            return isAfterCurrentDateAndTime && isBeforeLastDateAndTime;
        };
    }

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalProductiveNus(), new UserPrefs(), null);
        expectedModel = new ModelManager(model.getProductiveNus(), new UserPrefs(), null);
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, String.format(
                Messages.MESSAGE_ASSIGNMENTS_LISTED_OVERVIEW, model.getFilteredAssignmentList().size()), expectedModel);
    }

    @Test
    public void execute_oneDayFromCurrentDate_showAssignments() {
        index = Optional.of(Index.fromZeroBased(1));
        model.updateFilteredAssignmentList(showLimitedAssignments());
        String expectedMessage = String.format(
                Messages.MESSAGE_ASSIGNMENTS_LISTED_OVERVIEW, model.getFilteredAssignmentList().size());
        ListCommand listForOneDay = new ListCommand(index.get());
        expectedModel.updateFilteredAssignmentList(showLimitedAssignments());
        assertCommandSuccess(listForOneDay, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_tenDaysFromCurrentDate_showAssignments() {
        index = Optional.of(Index.fromZeroBased(10));
        model.updateFilteredAssignmentList(showLimitedAssignments());
        String expectedMessage = String.format(
                Messages.MESSAGE_ASSIGNMENTS_LISTED_OVERVIEW, model.getFilteredAssignmentList().size());
        ListCommand listForTenDays = new ListCommand(index.get());
        expectedModel.updateFilteredAssignmentList(showLimitedAssignments());
        assertCommandSuccess(listForTenDays, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_fiftyDaysFromCurrentDate_showAssignments() {
        index = Optional.of(Index.fromZeroBased(50));
        model.updateFilteredAssignmentList(showLimitedAssignments());
        String expectedMessage = String.format(
                Messages.MESSAGE_ASSIGNMENTS_LISTED_OVERVIEW, model.getFilteredAssignmentList().size());
        ListCommand listForFiftyDays = new ListCommand(index.get());
        expectedModel.updateFilteredAssignmentList(showLimitedAssignments());
        assertCommandSuccess(listForFiftyDays, model, expectedMessage, expectedModel);
    }
}
