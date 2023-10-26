package seedu.address.ui;

import java.util.Comparator;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.doctor.Doctor;

/**
 * Controller for a doctor page
 */
public class DoctorWindow extends UiPart<Stage> {

    private static final Logger logger = LogsCenter.getLogger(DoctorWindow.class);
    private static final String FXML = "DoctorWindow.fxml";

    private Doctor doctor;

    @FXML
    private Label name;
    @FXML
    private Label nric;
    @FXML
    private Label remark;
    @FXML
    private FlowPane tags;

    /**
     * Creates a new DoctorWindow.
     *
     * @param root Stage to use as the root of the DoctorWindow.
     */
    public DoctorWindow(Stage root) {
        super(FXML, root);
    }

    /**
     * Creates a new DoctorWindow.
     */
    public DoctorWindow() {
        this(new Stage());
    }

    /**
     * Updates the displayed doctor in the window.
     * @param doctor The given doctor to be displayed
     */
    public void updateSelectedDoctor(Doctor doctor) {
        this.doctor = doctor;
        name.setText(doctor.getName().fullName);
        nric.setText(doctor.getNric().nric);
        remark.setText(doctor.getRemark().remark);
        doctor.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    /**
     * Shows the doctor window.
     * @throws IllegalStateException
     *     <ul>
     *         <li>
     *             if this method is called on a thread other than the JavaFX Application Thread.
     *         </li>
     *         <li>
     *             if this method is called during animation or layout processing.
     *         </li>
     *         <li>
     *             if this method is called on the primary stage.
     *         </li>
     *         <li>
     *             if {@code dialogStage} is already showing.
     *         </li>
     *     </ul>
     */
    public void show() {
        logger.fine("Showing doctor page about the application.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the doctor window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the doctor window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the doctor window.
     */
    public void focus() {
        getRoot().requestFocus();
    }
}
