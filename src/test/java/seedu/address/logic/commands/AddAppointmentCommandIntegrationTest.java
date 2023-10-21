package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalDatabase.getTypicalDatabase;
import static seedu.address.testutil.TypicalPatient.ALICE_NRIC;
import static seedu.address.testutil.TypicalPatient.ELLE_NRIC;
import static seedu.address.testutil.TypicalPatient.HOON_NRIC;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.appointment.Appointment;
import seedu.address.testutil.AppointmentBuilder;

/**
 * Contains integration tests (interaction with the Model) for the AddAppointmentCommand class.
 */
public class AddAppointmentCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalDatabase(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Appointment validAppointment = new AppointmentBuilder().withPatientNric(ALICE_NRIC)
                .withDoctorNric(ELLE_NRIC)
                .withStartTime("2023-09-12 07:45")
                .withEndTime("2023-09-12 08:45")
                .build();

        Model expectedModel = new ModelManager(model.getDatabase(), new UserPrefs());
        expectedModel.addAppointment(validAppointment);
        assertCommandSuccess(new AddAppointmentCommand(validAppointment), model,
                String.format(AddAppointmentCommand.MESSAGE_SUCCESS, Messages.format(validAppointment)),
                expectedModel);
    }

    @Test
    public void execute_duplicateAppointment_throwsCommandException() {
        Appointment appointmentInList = model.getDatabase().getAppointmentList().get(0);
        assertCommandFailure(new AddAppointmentCommand(appointmentInList), model,
                AddAppointmentCommand.MESSAGE_DUPLICATE_APPOINTMENT);
    }

    @Test
    public void execute_invalidPatient_throwsCommandException() {
        Appointment appointmentWithInvalidPatient = new AppointmentBuilder().withPatientNric(HOON_NRIC).build();
        assertCommandFailure(new AddAppointmentCommand(appointmentWithInvalidPatient), model,
                AddAppointmentCommand.MESSAGE_INVALID_PATIENT);
    }

    @Test
    public void execute_invalidDoctor_throwsCommandException() {
        Appointment appointmentWithInvalidDoctor = new AppointmentBuilder().withDoctorNric(HOON_NRIC).build();
        assertCommandFailure(new AddAppointmentCommand(appointmentWithInvalidDoctor), model,
                AddAppointmentCommand.MESSAGE_INVALID_DOCTOR);
    }

    @Test
    public void execute_sameDoctorPatientAppointment_throwsCommandException() {
        Appointment appointmentWithDuplicateNric = new AppointmentBuilder()
                .withDoctorNric(ALICE_NRIC)
                .withPatientNric(ALICE_NRIC)
                .build();
        assertCommandFailure(new AddAppointmentCommand(appointmentWithDuplicateNric), model,
                AddAppointmentCommand.MESSAGE_SAME_PIC_DIC);
    }

    @Test
    public void execute_invalidAppointmentEndAndStartDate_throwsCommandException() {
        Appointment appointmentWithInvalidStartAndEndDate = new AppointmentBuilder()
                .withStartTime("2023-09-11 07:45")
                .withEndTime("2023-09-11 06:45")
                .build();
        assertCommandFailure(new AddAppointmentCommand(appointmentWithInvalidStartAndEndDate), model,
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
        assertCommandFailure(new AddAppointmentCommand(appointmentWithInvalidStartAndEndDate), model,
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
        assertCommandFailure(new AddAppointmentCommand(appointmentWithInvalidStartAndEndDate), model,
                AddAppointmentCommand.MESSAGE_OVERLAPPING_DOCTOR_APPOINTMENTS);
    }

}
