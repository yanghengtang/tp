package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_END_TIME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_START_TIME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DOCTOR_NRIC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PATIENT_NRIC;
import static seedu.address.testutil.TypicalDoctor.getTypicalDoctor;
import static seedu.address.testutil.TypicalPatient.ALICE_NRIC;
import static seedu.address.testutil.TypicalPatient.BENSON_NRIC;
import static seedu.address.testutil.TypicalPatient.CARL_NRIC;
import static seedu.address.testutil.TypicalPatient.DANIEL_NRIC;
import static seedu.address.testutil.TypicalPatient.ELLE_NRIC;
import static seedu.address.testutil.TypicalPatient.FIONA_NRIC;
import static seedu.address.testutil.TypicalPatient.getTypicalPatient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.Database;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.doctor.Doctor;
import seedu.address.model.person.patient.Patient;

/**
 * A utility class containing a list of {@code Appointment} objects to be used in tests.
 */
public class TypicalAppointment {


    public static final Appointment APPOINTMENT_1 = new AppointmentBuilder().withDoctorNric(BENSON_NRIC)
            .withPatientNric(ALICE_NRIC)
            .build();

    public static final Appointment APPOINTMENT_2 = new AppointmentBuilder().withDoctorNric(CARL_NRIC)
            .withPatientNric(BENSON_NRIC)
            .build();

    public static final Appointment APPOINTMENT_3 = new AppointmentBuilder().withDoctorNric(DANIEL_NRIC)
            .withPatientNric(CARL_NRIC)
            .build();

    public static final Appointment APPOINTMENT_4 = new AppointmentBuilder().withDoctorNric(ELLE_NRIC)
            .withPatientNric(DANIEL_NRIC)
            .build();

    public static final Appointment APPOINTMENT_5 = new AppointmentBuilder().withDoctorNric(FIONA_NRIC)
            .withPatientNric(ELLE_NRIC)
            .build();

    public static final Appointment APPOINTMENT_6 = new AppointmentBuilder().withDoctorNric(ALICE_NRIC)
            .withPatientNric(FIONA_NRIC)
            .build();

    // Manually added - Doctor's and Patient's details found in {@code CommandTestUtil}
    public static final Appointment APPOINTMENT_7 = new AppointmentBuilder()
            .withDoctorNric(VALID_DOCTOR_NRIC)
            .withPatientNric(VALID_PATIENT_NRIC)
            .withStartTime(VALID_APPOINTMENT_START_TIME)
            .withEndTime(VALID_APPOINTMENT_END_TIME)
            .build();
    public TypicalAppointment() {} // prevents instantiation

    public static List<Appointment> getTypicalAppointment() {
        return new ArrayList<>(Arrays.asList(APPOINTMENT_1, APPOINTMENT_2, APPOINTMENT_3, APPOINTMENT_4,
                APPOINTMENT_5, APPOINTMENT_6));
    }
}
