package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.doctor.Doctor;

/**
 * An UI component that displays information of a {@code Doctor}.
 */
public class DoctorCard extends UiPart<Region> {
    private static final String FXML = "DoctorListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Doctor doctor;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label nric;

    /**
     * Creates a {@code DoctorCard} with the given {@code Doctor} and index to display.
     */
    public DoctorCard(Doctor doctor, int displayedIndex) {
        super(FXML);
        this.doctor = doctor;
        id.setText(String.format("%d", displayedIndex));
        name.setText(doctor.getName().fullName);
        nric.setText(doctor.getNric().nric);
    }
}
