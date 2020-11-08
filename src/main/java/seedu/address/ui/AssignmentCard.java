package seedu.address.ui;

import static seedu.address.model.task.Time.TIME_DATE_TIME_FORMAT;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.Done;
import seedu.address.model.assignment.Priority;
import seedu.address.model.assignment.Schedule;
import seedu.address.model.task.Time;

/**
 * An UI component that displays information of a {@code Assignment}.
 */
public class AssignmentCard extends UiPart<Region> {
    private static final long MIN_PER_HOUR = 60;
    private static final long HOUR_PER_DAY = 24;
    private static final long DAY_PER_WEEK = 7;
    private static final long MIN_DAY_PER_MONTH = 28;
    private static final String FXML = "AssignmentListCard.fxml";
    private static final String OVERDUE_STYLE_CLASS = "overdue";
    private static final String DUE_SOON_STYLE_CLASS = "due-soon";
    private static final String DUE_IN_A_WEEK_STYLE_CLASS = "due-in-a-week";
    private static final String HIGH_PRIORITY_STYLE_CLASS = "high-priority";
    private static final String MEDIUM_PRIORITY_STYLE_CLASS = "medium-priority";
    private static final String LOW_PRIORITY_STYLE_CLASS = "low-priority";
    private static final String NONE_PRIORITY_STYLE_CLASS = "none-priority";
    private static final DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern(TIME_DATE_TIME_FORMAT)
            .withResolverStyle(ResolverStyle.STRICT);

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Assignment assignment;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label deadline;
    @FXML
    private Label moduleCode;
    @FXML
    private Label dueDate;
    @FXML
    private Label displaySchedule;
    @FXML
    private Label suggestedStartTime;
    @FXML
    private Label suggestedEndTime;
    @FXML
    private Label priority;
    @FXML
    private Label done;

    /**
     * Creates a {@code AssignmentCode} with the given {@code Assignment} and index to display.
     */
    public AssignmentCard(Assignment assignment, int displayedIndex) {
        super(FXML);
        this.assignment = assignment;
        id.setText(displayedIndex + ". ");
        name.setText(assignment.getName().fullName);
        deadline.setText("Deadline: " + assignment.getDeadline().value);
        moduleCode.setText("Module: " + assignment.getModuleCode().moduleCode);
        getDueDate(dueDate, assignment.getDeadline());
        getSchedule(displaySchedule, suggestedStartTime, suggestedEndTime, assignment.getSchedule());
        getPriority(priority, assignment.getPriority());
        done.setText(assignment.getDone().toString());
        getDone(done, assignment.getDone());
    }

    public void getDone(Label label, Done done) {
        if (!done.isMarkedDone()) {
            label.setText("");
            return;
        }
        label.setText(done.toString());
    }

    public void getSchedule(Label displaySchedule, Label suggestedStartTime, Label suggestedEndTime,
                            Schedule schedule) {
        if (!schedule.isScheduled()) {
            displaySchedule.setText("");
            suggestedStartTime.setText("");
            suggestedEndTime.setText("");
            return;
        }
        displaySchedule.setText("Suggested:");
        suggestedStartTime.setText("Start time: " + schedule.getSuggestedStartTime());
        suggestedEndTime.setText("End time: " + schedule.getSuggestedEndTime());
    }

    public void getPriority(Label label, Priority priority) {
        label.setText("priority." + priority.toString());

        switch (priority.toString()) {
        case Priority.HIGH_PRIORITY:
            setStyleToIndicateHighPriority(label);
            break;

        case Priority.LOW_PRIORITY:
            setStyleToIndicateLowPriority(label);
            break;

        case Priority.MEDIUM_PRIORITY:
            setStyleToIndicateMediumPriority(label);
            break;

        case Priority.NONE_PRIORITY:
            setStyleToIndicateNonePriority(label);
            break;

        default:
            break;
        }
    }

    public void setStyleToIndicateHighPriority(Label label) {
        ObservableList<String> styleClass = label.getStyleClass();

        if (styleClass.contains(HIGH_PRIORITY_STYLE_CLASS)) {
            return;
        }

        styleClass.add(HIGH_PRIORITY_STYLE_CLASS);
    }

    public void setStyleToIndicateMediumPriority(Label label) {
        ObservableList<String> styleClass = label.getStyleClass();

        if (styleClass.contains(MEDIUM_PRIORITY_STYLE_CLASS)) {
            return;
        }

        styleClass.add(MEDIUM_PRIORITY_STYLE_CLASS);
    }

    public void setStyleToIndicateLowPriority(Label label) {
        ObservableList<String> styleClass = label.getStyleClass();

        if (styleClass.contains(LOW_PRIORITY_STYLE_CLASS)) {
            return;
        }

        styleClass.add(LOW_PRIORITY_STYLE_CLASS);
    }

    public void setStyleToIndicateNonePriority(Label label) {
        ObservableList<String> styleClass = label.getStyleClass();

        if (styleClass.contains(NONE_PRIORITY_STYLE_CLASS)) {
            return;
        }

        styleClass.add(NONE_PRIORITY_STYLE_CLASS);
    }

    public void getDueDate(Label label, Time deadline) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime due = LocalDateTime.parse(deadline.value, inputFormat);
        Duration duration = Duration.between(now, due);

        if (duration.toMinutes() < 0) {
            setStyleToIndicateOverdue(label);
            label.setText("Overdue!");
        } else if (duration.toMinutes() < MIN_PER_HOUR) {
            setStyleToIndicateDueSoon(label);
            label.setText("Due in " + duration.toMinutes() + " minutes");
        } else if (duration.toHours() < HOUR_PER_DAY) {
            setStyleToIndicateDueSoon(label);
            label.setText("Due in " + duration.toHours() + " hours");
        } else if (duration.toDays() < DAY_PER_WEEK) {
            setStyleToIndicateDueInAWeek(label);
            label.setText("Due in " + duration.toDays() + " days");
        } else if (duration.toDays() < MIN_DAY_PER_MONTH) {
            label.setText("Due in " + duration.toDays() + " days");
        } else {
            label.setText("");
        }
    }

    public void setStyleToIndicateOverdue(Label label) {
        ObservableList<String> styleClass = label.getStyleClass();

        if (styleClass.contains(OVERDUE_STYLE_CLASS)) {
            return;
        }

        styleClass.add(OVERDUE_STYLE_CLASS);
    }

    public void setStyleToIndicateDueSoon(Label label) {
        ObservableList<String> styleClass = label.getStyleClass();

        if (styleClass.contains(DUE_SOON_STYLE_CLASS)) {
            return;
        }

        styleClass.add(DUE_SOON_STYLE_CLASS);
    }

    public void setStyleToIndicateDueInAWeek(Label label) {
        ObservableList<String> styleClass = label.getStyleClass();

        if (styleClass.contains(DUE_IN_A_WEEK_STYLE_CLASS)) {
            return;
        }

        styleClass.add(DUE_IN_A_WEEK_STYLE_CLASS);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AssignmentCard)) {
            return false;
        }

        // state check
        AssignmentCard card = (AssignmentCard) other;
        return id.getText().equals(card.id.getText())
                && assignment.equals(card.assignment);
    }
}
