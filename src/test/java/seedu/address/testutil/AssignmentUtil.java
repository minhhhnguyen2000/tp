package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditAssignmentDescriptor;
import seedu.address.model.person.Assignment;
import seedu.address.model.tag.Tag;

/**
 * A utility class for Assignment.
 */
public class AssignmentUtil {

    /**
     * Returns an add command string for adding the {@code assignment}.
     */
    public static String getAddCommand(Assignment assignment) {
        return AddCommand.COMMAND_WORD + " " + getAssignmentDetails(assignment);
    }

    /**
     * Returns the part of command string for the given {@code assignment}'s details.
     */
    public static String getAssignmentDetails(Assignment assignment) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + assignment.getName().fullName + " ");
        sb.append(PREFIX_DEADLINE + assignment.getDeadline().value + " ");
        sb.append(PREFIX_MODULE_CODE + assignment.getModuleCode().moduleCode + " ");
        assignment.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditAssignmentDescriptor}'s details.
     */
    public static String getEditAssignmentDescriptorDetails(EditAssignmentDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getDeadline().ifPresent(deadline -> sb.append(PREFIX_DEADLINE).append(deadline.value).append(" "));
        descriptor.getModuleCode().ifPresent(moduleCode -> sb.append(PREFIX_MODULE_CODE)
                .append(moduleCode.moduleCode).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
