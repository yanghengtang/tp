package seedu.address.ui;

import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.appointment.Appointment;

/**
 * An UI component that displays information of a {@code Appointment}.
 */
public class AppointmentCard extends UiPart<Region> {
    private static final String FXML = "AppointmentListCard.fxml";
    private static final String DATE_TIME_FORMAT = "MMM d yyyy HH:mm";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Appointment appointment;

    @FXML
    private HBox cardPane;
    @FXML
    private Label startDateTime;
    @FXML
    private Label endDateTime;
    @FXML
    private Label id;
    @FXML
    private Label patientNric;
    @FXML
    private Label doctorNric;

    /**
     * Creates a {@code AppointmentCard} with the given {@code Appointment} and index to display.
     */
    public AppointmentCard(Appointment appointment, int displayedIndex) {
        super(FXML);
        this.appointment = appointment;
        id.setText(String.format("%d", displayedIndex));
        startDateTime.setText(appointment.getStartTime().getTime().format(DateTimeFormatter
                .ofPattern(DATE_TIME_FORMAT)));
        endDateTime.setText(appointment.getEndTime().getTime().format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)));
        patientNric.setText(appointment.getPatientNric().nric);
        doctorNric.setText(appointment.getDoctorNric().nric);
    }
}
