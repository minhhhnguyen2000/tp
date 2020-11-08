package seedu.address.logic.commands;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.assignment.Assignment;

public class CommandLogic {

    static final Comparator<Index> INDEX_COMPARATOR = (firstIndex, secondIndex) -> {
        int firstIndexValue = firstIndex.getZeroBased();
        int secondIndexValue = secondIndex.getZeroBased();
        return secondIndexValue - firstIndexValue; // sort by descending order
    };

    static void checkForDuplicatedIndexes(List<Index> targetIndexes) throws CommandException {
        List<Integer> zeroBasedIndexes = new ArrayList<>();
        for (Index targetIndex : targetIndexes) {
            int zeroBasedIndex = targetIndex.getZeroBased();
            zeroBasedIndexes.add(zeroBasedIndex);
        }

        long distinctIndexes = zeroBasedIndexes.stream().distinct().count();
        long lengthOfIndexesList = zeroBasedIndexes.size();
        boolean containsDuplicates = distinctIndexes < lengthOfIndexesList;

        if (containsDuplicates) {
            throw new CommandException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, Messages.MESSAGE_DUPLICATE_INDEXES));
        }
    }

    static void checkForInvalidIndexes(
            List<Index> targetIndexes, Model model, String messageUsage) throws CommandException {
        List<Assignment> lastShownList = model.getFilteredAssignmentList();
        if (targetIndexes.isEmpty()) {
            throw new CommandException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, messageUsage));
        }
        for (Index targetIndex : targetIndexes) {
            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_ASSIGNMENT_DISPLAYED_INDEX);
            }
        }
    }

}
