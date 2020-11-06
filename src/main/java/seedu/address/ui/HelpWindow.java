package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL = "https://ay2021s1-cs2103t-f11-3.github.io/tp/UserGuide.html";
    public static final String HELP_MESSAGE = "Welcome to ProductiveNUS!\n\nHere are some of the commands that you can"
            + " try:\n\n"
            + "-----------------------------------------Basic Features---------------------------------------------\n"
            + "- To add an assignment: add n/Lab report 3 d/23-04-2020 1230 mod/CS2100\n"
            + "    *Tip: You can include `remind` when adding an assignment for it to appear in Your reminders\n"
            + "- To delete an assignment shown in the list: delete 3\n"
            + "    *Tip: You can delete multiple assignments by including multiple indexes: delete 3 4 7\n"
            + "- To list all assignments: list\n"
            + "    *Tip: Use `list 2` to list assignments with deadlines 2 days from current date\n\n"
            + "----------------------------------------Advance Features-------------------------------------------\n"
            + "- To import your timetable: import url/YOUR_NUSMODS_URL\n"
            + "- To undo your most recent command: undo\n"
            + "- To find assignments by name: find n/Lab\n"
            + "    *Tip: You can also find assignments by module code, deadline or priority using\n"
            + "    `mod/`, `d/` and `p/` prefixes respectively.\n"
            + "- To edit the details of an assignment: edit 3 n/Lab 5\n"
            + "    *Tip: You can edit multiple fields, such as name, deadline and module code, "
            + "     of an assignment at the same using `n/`, `d/` and `mod/` prefixes respectively.\n"
            + "- To schedule an assignment: schedule 3 hrs/2 af/05-11-2020 1000 by/10-11-2020 2359\n"
            + "- To unschedule an assignment: unschedule 3\n"
            + "- To set reminders for an assignment shown in the list: remind 3\n"
            + "    *Tip: You can remind multiple assignments by including multiple indexes: remind 3 4 7\n"
            + "    *Tip: Assignments with reminders set will be displayed in Your reminders permanently\n"
            + "- To remove reminders for an assignment: unremind 3\n"
            + "    *Tip: The index refers to the index of the assignment in Your reminders\n"
            + "- To set a priority tag for an assignment: prioritize 3 p/HIGH\n"
            + "    *Tip: There are 3 priority tags to choose from: LOW, MEDIUM, HIGH\n"
            + "- To remove the priority tag for an assignment: unprioritize 3\n"
            + "- To mark an assignment shown in the list as done: done 3\n"
            + "    *Tip: You can mark multiple assignments as done: done 3 4 7\n"
            + "- To mark an assignment shown in the list as not done: undone 3\n\n"
            + "For more information, kindly refer to our user guide:\n"
            + USERGUIDE_URL;

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private Button copyButton;

    @FXML
    private Label helpMessage;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        helpMessage.setText(HELP_MESSAGE);
    }

    /**
     * Creates a new HelpWindow.
     */
    public HelpWindow() {
        this(new Stage());
    }

    /**
     * Shows the help window.
     * @throws IllegalStateException
     * <ul>
     *     <li>
     *         if this method is called on a thread other than the JavaFX Application Thread.
     *     </li>
     *     <li>
     *         if this method is called during animation or layout processing.
     *     </li>
     *     <li>
     *         if this method is called on the primary stage.
     *     </li>
     *     <li>
     *         if {@code dialogStage} is already showing.
     *     </li>
     * </ul>
     */
    public void show() {
        logger.fine("Showing help page about the application.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Copies the URL to the user guide to the clipboard.
     */
    @FXML
    private void copyUrl() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(USERGUIDE_URL);
        clipboard.setContent(url);
    }
}
