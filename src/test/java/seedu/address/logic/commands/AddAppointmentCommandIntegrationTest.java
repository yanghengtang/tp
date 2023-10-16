package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertNewCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertNewCommandSuccess;
import static seedu.address.testutil.TypicalDatabase.getTypicalDatabase;
import static seedu.address.testutil.TypicalPatient.ALICE_NRIC;
import static seedu.address.testutil.TypicalPatient.ELLE_NRIC;
import static seedu.address.testutil.TypicalPatient.HOON_NRIC;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.NewModel;
import seedu.address.model.NewModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.appointment.Appointment;
import seedu.address.testutil.AppointmentBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddAppointmentCommandIntegrationTest {

    private NewModel newModel;

    @BeforeEach
    public void setUp() {
        newModel = new NewModelManager(getTypicalDatabase(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Appointment validAppointment = new AppointmentBuilder().withPatientNric(ALICE_NRIC)
                .withDoctorNric(ELLE_NRIC).build();

        NewModel expectedModel = new NewModelManager(newModel.getDatabase(), new UserPrefs());
        expectedModel.addAppointment(validAppointment);
        assertNewCommandSuccess(new AddAppointmentCommand(validAppointment), newModel,
                String.format(AddAppointmentCommand.MESSAGE_SUCCESS, Messages.format(validAppointment)),
                expectedModel);
    }

    @Test
    public void execute_duplicateAppointment_throwsCommandException() {
        Appointment appointmentInList = newModel.getDatabase().getAppointmentList().get(0);
        assertNewCommandFailure(new AddAppointmentCommand(appointmentInList), newModel,
                AddAppointmentCommand.MESSAGE_DUPLICATE_APPOINTMENT);
    }

    @Test
    public void execute_invalidPatient_throwsCommandException() {
        Appointment appointmentWithInvalidPatient = new AppointmentBuilder().withPatientNric(HOON_NRIC).build();
        assertNewCommandFailure(new AddAppointmentCommand(appointmentWithInvalidPatient), newModel,
                AddAppointmentCommand.MESSAGE_INVALID_PATIENT);
    }

    @Test
    public void execute_invalidDoctor_throwsCommandException() {
        Appointment appointmentWithInvalidDoctor = new AppointmentBuilder().withDoctorNric(HOON_NRIC).build();
        assertNewCommandFailure(new AddAppointmentCommand(appointmentWithInvalidDoctor), newModel,
                AddAppointmentCommand.MESSAGE_INVALID_DOCTOR);
    }
}
