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
                .withDoctorNric(ELLE_NRIC)
                .withStartTime("2023-09-12 07:45")
                .withEndTime("2023-09-12 08:45")
                .build();

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

    @Test
    public void execute_sameDoctorPatientAppointment_throwsCommandException() {
        Appointment appointmentWithDuplicateNric = new AppointmentBuilder()
                .withDoctorNric(ALICE_NRIC)
                .withPatientNric(ALICE_NRIC)
                .build();
        assertNewCommandFailure(new AddAppointmentCommand(appointmentWithDuplicateNric), newModel,
                AddAppointmentCommand.MESSAGE_SAME_PIC_DIC);
    }

    @Test
    public void execute_invalidAppointmentEndAndStartDate_throwsCommandException() {
        Appointment appointmentWithInvalidStartAndEndDate = new AppointmentBuilder()
                .withStartTime("2023-09-11 07:45")
                .withEndTime("2023-09-11 06:45")
                .build();
        assertNewCommandFailure(new AddAppointmentCommand(appointmentWithInvalidStartAndEndDate), newModel,
                AddAppointmentCommand.MESSAGE_INVALID_APPOINTMENT_TIME);
    }

    @Test
    public void execute_overlappingPatientAppointments_throwsCommandException() {
        Appointment appointmentWithInvalidStartAndEndDate = new AppointmentBuilder()
                .withPatientNric(ALICE_NRIC)
                .withDoctorNric(ELLE_NRIC)
                .withStartTime("2023-09-11 07:30")
                .withEndTime("2023-09-11 08:00")
                .build();
        assertNewCommandFailure(new AddAppointmentCommand(appointmentWithInvalidStartAndEndDate), newModel,
                AddAppointmentCommand.MESSAGE_OVERLAPPING_PATIENT_APPOINTMENTS);
    }

    @Test
    public void execute_overlappingDoctorAppointments_throwsCommandException() {
        Appointment appointmentWithInvalidStartAndEndDate = new AppointmentBuilder()
                .withPatientNric(ALICE_NRIC)
                .withDoctorNric(ELLE_NRIC)
                .withStartTime("2023-09-11 17:45")
                .withEndTime("2023-09-11 18:00")
                .build();
        assertNewCommandFailure(new AddAppointmentCommand(appointmentWithInvalidStartAndEndDate), newModel,
                AddAppointmentCommand.MESSAGE_OVERLAPPING_DOCTOR_APPOINTMENTS);
    }

}
