package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_HW;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_LAB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_HW;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_LAB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_HW;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_LAB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.ProductiveNus;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.Priority;

/**
 * A utility class containing a list of {@code Assignment} objects to be used in tests.
 */
public class TypicalAssignments {

    public static final Assignment CS1231S_HW = new AssignmentBuilder().withName("CS1231S Homework")
            .withModuleCode("CS1231S").withDeadline("01-01-2020 1800").build();
    public static final Assignment CS2103T_TUT = new AssignmentBuilder().withName("CS2103T Tutorial")
            .withModuleCode("CS2103T").withDeadline("02-03-2020 2359").build();
    public static final Assignment CS2106_LAB = new AssignmentBuilder().withName("CS2106 Lab")
            .withDeadline("12-12-2020 1200").withModuleCode("CS2106").build();
    public static final Assignment CS2106_TUTORIAL_QUIZ = new AssignmentBuilder().withName("CS2102 Tutorial Quiz")
            .withDeadline("03-03-2020 0300").withModuleCode("CS2102").build();
    public static final Assignment IS1103_MISSION = new AssignmentBuilder().withName("IS1103 Mission")
            .withDeadline("12-10-2020 1900").withModuleCode("IS1103").build();
    public static final Assignment PEER_REVIEW = new AssignmentBuilder().withName("Peer review")
            .withDeadline("10-10-2020 1700").withModuleCode("CS2101").build();
    public static final Assignment ORAL_PRESENTATION = new AssignmentBuilder().withName("Oral presentation")
            .withDeadline("03-12-2020 0400").withModuleCode("CS2101").build();
    public static final Assignment MA1101R_TUTORIAL_ONE = new AssignmentBuilder().withName("MA1101R Tut 1")
            .withDeadline("03-12-2020 0400").withModuleCode("MA1101R").withPriority("HIGH").build();
    public static final Assignment MA1101R_TUTORIAL_TWO = new AssignmentBuilder().withName("MA1101R Tut 2")
            .withDeadline("03-12-2020 0400").withModuleCode("MA1101R").withPriority("low").build();

    // Manually added
    public static final Assignment READ = new AssignmentBuilder().withName("Read notes").withDeadline("01-01-2020 0100")
            .withModuleCode("CS3244").build();
    public static final Assignment SLIDE = new AssignmentBuilder().withName("Prepare slide")
            .withDeadline("02-02-2020 0220").withModuleCode("GER1000").build();

    // Manually added - Assignment with reminders set
    public static final Assignment QUIZ = new AssignmentBuilder().withName("Prepare for quiz")
            .withDeadline("10-10-2020 2000").withModuleCode("ST2334").withRemindersSet().build();

    // Manually added - Assignment's details found in {@code CommandTestUtil}
    public static final Assignment HW = new AssignmentBuilder().withName(VALID_NAME_HW).withDeadline(VALID_DEADLINE_HW)
            .withModuleCode(VALID_MODULE_CODE_HW).build();
    public static final Assignment LAB = new AssignmentBuilder().withName(VALID_NAME_LAB)
            .withDeadline(VALID_DEADLINE_LAB).withModuleCode(VALID_MODULE_CODE_LAB).build();
    public static final Assignment LAB_REMIND = new AssignmentBuilder().withName(VALID_NAME_LAB)
            .withDeadline(VALID_DEADLINE_LAB).withModuleCode(VALID_MODULE_CODE_LAB).withRemindersSet().build();
    public static final Assignment LAB_PRIORITY = new AssignmentBuilder().withName(VALID_NAME_LAB)
            .withDeadline(VALID_DEADLINE_LAB).withModuleCode(VALID_MODULE_CODE_LAB)
            .withPriority(Priority.HIGH_PRIORITY).build();
    public static final Assignment LAB_PRIORITY_REMIND = new AssignmentBuilder().withName(VALID_NAME_LAB)
            .withDeadline(VALID_DEADLINE_LAB).withModuleCode(VALID_MODULE_CODE_LAB).withPriority(Priority.HIGH_PRIORITY)
            .withRemindersSet().build();

    private TypicalAssignments() {} // prevents instantiation

    /**
     * Returns an {@code ProductiveNus} with all the typical assignments.
     */
    public static ProductiveNus getTypicalProductiveNus() {
        ProductiveNus pn = new ProductiveNus();
        for (Assignment assignment : getTypicalAssignments()) {
            pn.addAssignment(assignment);
        }
        return pn;
    }

    public static List<Assignment> getTypicalAssignments() {
        return new ArrayList<>(Arrays.asList(CS1231S_HW, CS2103T_TUT, CS2106_LAB, CS2106_TUTORIAL_QUIZ,
                IS1103_MISSION, PEER_REVIEW, ORAL_PRESENTATION, MA1101R_TUTORIAL_ONE, MA1101R_TUTORIAL_TWO));
    }
}
