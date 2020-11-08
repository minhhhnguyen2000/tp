package seedu.address.ui;

import static seedu.address.model.task.Time.TIME_DATE_TIME_FORMAT;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.task.Task;
import seedu.address.model.task.Time;

/**
 * Panel containing the list of tasks.
 */
public class UpcomingTaskListPanel extends UiPart<Region> {
    private static final long MIN_PER_HOUR = 60;
    private static final long HOUR_PER_DAY = 24;
    private static final long DAY_PER_WEEK = 7;
    private static final long MIN_DAY_PER_MONTH = 28;
    private static final String FXML = "UpcomingTaskListPanel.fxml";
    private static final DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern(TIME_DATE_TIME_FORMAT)
            .withResolverStyle(ResolverStyle.STRICT);
    private static final String DUE_SOON_STYLE_CLASS = "due-soon";
    private static final String OVERDUE_STYLE_CLASS = "overdue";
    private static final String DUE_IN_A_WEEK_STYLE_CLASS = "due-in-a-week";
    private static final String DISPLAY_DATE_AND_TIME_FORMAT = "dd MMM yyyy HH:mm";
    private final Logger logger = LogsCenter.getLogger(UpcomingTaskListPanel.class);

    @FXML
    private ListView<Task> upcomingTaskListView;

    /**
     * Constructs a {@code UpcomingTaskListPanel} with the given {@code ObservableList}.
     */
    public UpcomingTaskListPanel(ObservableList<Task> taskList) {
        super(FXML);
        upcomingTaskListView.setItems(taskList);
        upcomingTaskListView.setCellFactory(listView -> new UpcomingTaskListViewCell());
    }

    public static void getDueDate(Label label, Time deadline) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime due = LocalDateTime.parse(deadline.value, inputFormat);
        String formattedDue = due.format(DateTimeFormatter.ofPattern(DISPLAY_DATE_AND_TIME_FORMAT));
        Duration duration = Duration.between(now, due);
        if (duration.toMinutes() < 0) {
            setStyleToIndicateOverdue(label);
            label.setText("Overdue, on " + formattedDue);
        } else if (duration.toMinutes() < MIN_PER_HOUR) {
            setStyleToIndicateDueSoon(label);
            label.setText("Due in " + duration.toMinutes() + " minutes, on " + formattedDue);
        } else if (duration.toHours() < HOUR_PER_DAY) {
            setStyleToIndicateDueSoon(label);
            label.setText("Due in " + duration.toHours() + " hours, on " + formattedDue);
        } else if (duration.toDays() < DAY_PER_WEEK) {
            setStyleToIndicateDueInAWeek(label);
            label.setText("Due in " + duration.toDays() + " days, on " + formattedDue);
        } else if (duration.toDays() < MIN_DAY_PER_MONTH) {
            label.setText("Due in " + duration.toDays() + " days, on " + formattedDue);
        } else {
            label.setText("Due on " + formattedDue);
        }
    }

    public static void setStyleToIndicateOverdue(Label label) {
        ObservableList<String> styleClass = label.getStyleClass();

        if (styleClass.contains(OVERDUE_STYLE_CLASS)) {
            return;
        }

        styleClass.add(OVERDUE_STYLE_CLASS);
    }

    public static void setStyleToIndicateDueSoon(Label label) {
        ObservableList<String> styleClass = label.getStyleClass();

        if (styleClass.contains(DUE_SOON_STYLE_CLASS)) {
            return;
        }

        styleClass.add(DUE_SOON_STYLE_CLASS);
    }

    public static void setStyleToIndicateDueInAWeek(Label label) {
        ObservableList<String> styleClass = label.getStyleClass();

        if (styleClass.contains(DUE_IN_A_WEEK_STYLE_CLASS)) {
            return;
        }

        styleClass.add(DUE_IN_A_WEEK_STYLE_CLASS);
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Task} using a {@code TaskCard}.
     */
    class UpcomingTaskListViewCell extends ListCell<Task> {

        protected void updateItem(Task task, boolean empty) {
            super.updateItem(task, empty);

            if (empty || task == null) {
                setGraphic(null);
                setText(null);
            } else {
                if (task instanceof Assignment) {
                    setGraphic(new UpcomingAssignmentCard((Assignment) task, getIndex() + 1).getRoot());
                } else if (task instanceof Lesson) {
                    setGraphic(new UpcomingLessonCard((Lesson) task, getIndex() + 1).getRoot());
                }
            }
        }
    }

}

