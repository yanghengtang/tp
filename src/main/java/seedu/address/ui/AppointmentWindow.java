package seedu.address.ui;

import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.appointment.Appointment;

/**
 * Controller for an appointment page
 */
public class AppointmentWindow extends UiPart<Stage> {

    private static final Logger logger = LogsCenter.getLogger(AppointmentWindow.class);
    private static final String FXML = "AppointmentWindow.fxml";

    private Appointment appointment;

    @FXML
    private Label startDateTime;
    @FXML
    private Label endDateTime;
    @FXML
    private Label patientNric;
    @FXML
    private Label doctorNric;
    @FXML
    private Label remark;
    @FXML
    private FlowPane tags;

    /**
     * Creates a new AppointmentWindow.
     *
     * @param root Stage to use as the root of the AppointmentWindow.
     */
    public AppointmentWindow(Stage root) {
        super(FXML, root);
    }

    /**
     * Creates a new AppointmentWindow.
     */
    public AppointmentWindow() {
        this(new Stage());
    }

    /**
     * Updates the displayed appointment in the window.
     * @param appointment The given appointment to be displayed
     */
    public void updateSelectedAppointment(Appointment appointment) {
        this.appointment = appointment;
        startDateTime.setText(appointment.getStartTime().getTime().format(DateTimeFormatter
                .ofPattern("MMM d yyyy HH:mm")));
        endDateTime.setText(appointment.getEndTime().getTime().format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")));
        patientNric.setText(appointment.getPatientNric().nric);
        doctorNric.setText(appointment.getDoctorNric().nric);
        remark.setText(appointment.getRemark().remark);
        tags.getChildren().clear();
        appointment.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    /**
     * Shows the appointment window.
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
        logger.fine("Showing appointment page about the application.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the appointment window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the appointment window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the appointment window.
     */
    public void focus() {
        getRoot().requestFocus();
    }
}
