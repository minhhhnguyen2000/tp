package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ASSIGNMENT;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Optional;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.task.Time;

/**
 * Lists assignments in ProductiveNUS to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = "Format: " + COMMAND_WORD
            + " [INDEX] (must be a positive integer from 1 to 50)";

    public static final String MESSAGE_INDEX_NOT_IN_RANGE = "Index should only be from 1 to 50.";

    private final Optional<Index> numberOfDays;

    /**
     * Constructor of List Command which takes in an Index.
     * @param numberOfDays Number of days as Index.
     */
    public ListCommand(Index numberOfDays) {
        assert numberOfDays != null;
        this.numberOfDays = Optional.of(numberOfDays);
    }

    public ListCommand() {
        numberOfDays = Optional.empty();
    }

    private Predicate<Assignment> showLimitedAssignments() {
        return assignment -> {
            DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern(Time.TIME_DATE_TIME_FORMAT)
                    .withResolverStyle(ResolverStyle.STRICT);
            String dateAndTimeToParse = assignment.getDeadline().value;
            LocalDateTime currentDateAndTime = LocalDateTime.now();
            assert numberOfDays.isPresent();
            int zeroBaseValue = numberOfDays.get().getZeroBased();
            long inputNumberOfDays = Optional.of(zeroBaseValue).get();
            LocalDateTime lastDateAndTime = currentDateAndTime.plusDays(inputNumberOfDays);
            LocalDateTime parsedDateAndTime = LocalDateTime.parse(dateAndTimeToParse, inputFormat);

            boolean isAfterCurrentDateAndTime = parsedDateAndTime.isAfter(currentDateAndTime);
            boolean isBeforeLastDateAndTime = parsedDateAndTime.isBefore(lastDateAndTime);

            return isAfterCurrentDateAndTime && isBeforeLastDateAndTime;
        };
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (numberOfDays.isEmpty()) {
            model.updateFilteredAssignmentList(PREDICATE_SHOW_ALL_ASSIGNMENT);
        } else {
            model.updateFilteredAssignmentList((showLimitedAssignments()));
        }
        return new CommandResult(String.format(
                Messages.MESSAGE_ASSIGNMENTS_LISTED_OVERVIEW, model.getFilteredAssignmentList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListCommand // instanceof handles nulls
                && numberOfDays.equals(((ListCommand) other).numberOfDays)); // state check
    }
}
