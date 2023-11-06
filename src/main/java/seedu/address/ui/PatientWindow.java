package seedu.address.ui;

import java.util.Comparator;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.patient.Patient;

/**
 * Controller for a patient page
 */
public class PatientWindow extends UiPart<Stage> {

    private static final Logger logger = LogsCenter.getLogger(PatientWindow.class);
    private static final String FXML = "PatientWindow.fxml";

    private Patient patient;

    @FXML
    private Label name;
    @FXML
    private Label phone;
    @FXML
    private Label nric;
    @FXML
    private Label remark;
    @FXML
    private FlowPane tags;

    /**
     * Creates a new PatientWindow.
     *
     * @param root Stage to use as the root of the PatientWindow.
     */
    public PatientWindow(Stage root) {
        super(FXML, root);
    }

    /**
     * Creates a new PatientWindow.
     */
    public PatientWindow() {
        this(new Stage());
    }

    /**
     * Updates the displayed patient in the window.
     * @param patient The given patient to be displayed
     */
    public void updateSelectedPatient(Patient patient) {
        this.patient = patient;
        name.setText(patient.getName().fullName);
        nric.setText(patient.getNric().nric);
        phone.setText(patient.getPhone().value);
        remark.setText(patient.getRemark().remark);
        tags.getChildren().clear();
        patient.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    /**
     * Shows the patient window.
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
        logger.fine("Showing patient window.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the patient window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the patient window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the patient window.
     */
    public void focus() {
        if (getRoot().isIconified()) {
            getRoot().setIconified(false);
        }

        getRoot().requestFocus();
    }
}
