package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_END_TIME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_START_TIME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DOCTOR_NRIC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PATIENT_NRIC;
import static seedu.address.model.DataTest.PARACETAMOL_TAG;
import static seedu.address.model.DataTest.PEDIATRICIAN_TAG;
import static seedu.address.model.DataTest.VALID_REMARK_STRING;
import static seedu.address.testutil.PersonUtil.ALICE_NRIC;
import static seedu.address.testutil.PersonUtil.BENSON_NRIC;
import static seedu.address.testutil.PersonUtil.CARL_NRIC;
import static seedu.address.testutil.PersonUtil.DANIEL_NRIC;
import static seedu.address.testutil.PersonUtil.ELLE_NRIC;
import static seedu.address.testutil.PersonUtil.FIONA_NRIC;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.appointment.Appointment;

/**
 * A utility class containing a list of {@code Appointment} objects to be used in tests.
 */
public class TypicalAppointment {


    public static final Appointment APPOINTMENT_1 = new AppointmentBuilder().withDoctorNric(BENSON_NRIC)
            .withPatientNric(ALICE_NRIC)
            .withStartTime("2023-09-11 07:30")
            .withEndTime("2023-09-11 08:00")
            .build();

    public static final Appointment APPOINTMENT_1_WITH_REMARKS = new AppointmentBuilder().withDoctorNric(BENSON_NRIC)
            .withPatientNric(ALICE_NRIC)
            .withStartTime("2023-09-11 07:30")
            .withEndTime("2023-09-11 08:00")
            .withRemark(VALID_REMARK_STRING)
            .withTags(PEDIATRICIAN_TAG, PARACETAMOL_TAG)
            .build();

    public static final Appointment APPOINTMENT_2 = new AppointmentBuilder().withDoctorNric(CARL_NRIC)
            .withPatientNric(BENSON_NRIC)
            .withStartTime("2023-09-12 07:30")
            .withEndTime("2023-09-12 08:00")
            .build();

    public static final Appointment APPOINTMENT_3 = new AppointmentBuilder().withDoctorNric(DANIEL_NRIC)
            .withPatientNric(CARL_NRIC)
            .withStartTime("2023-09-11 10:30")
            .withEndTime("2023-09-11 11:00")
            .build();

    public static final Appointment APPOINTMENT_4 = new AppointmentBuilder().withDoctorNric(ELLE_NRIC)
            .withPatientNric(DANIEL_NRIC)
            .withStartTime("2023-09-11 17:30")
            .withEndTime("2023-09-11 18:00")
            .build();

    public static final Appointment APPOINTMENT_5 = new AppointmentBuilder().withDoctorNric(FIONA_NRIC)
            .withPatientNric(ELLE_NRIC)
            .withStartTime("2023-09-11 09:30")
            .withEndTime("2023-09-11 10:00")
            .build();

    public static final Appointment APPOINTMENT_6 = new AppointmentBuilder().withDoctorNric(ALICE_NRIC)
            .withPatientNric(FIONA_NRIC)
            .withStartTime("2023-09-11 13:30")
            .withEndTime("2023-09-11 14:00")
            .build();

    public static final Appointment APPOINTMENT_6_DIFFERENT_TIME = new AppointmentBuilder()
            .withDoctorNric(ALICE_NRIC)
            .withPatientNric(FIONA_NRIC)
            .withStartTime("2023-09-11 14:00")
            .withEndTime("2023-09-11 14:30")
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
        return new ArrayList<>(Arrays.asList(APPOINTMENT_1_WITH_REMARKS, APPOINTMENT_2, APPOINTMENT_3, APPOINTMENT_4,
                APPOINTMENT_5, APPOINTMENT_6));
    }
}
