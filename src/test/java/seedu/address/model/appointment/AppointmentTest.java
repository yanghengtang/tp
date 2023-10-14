package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_BOB;
import static seedu.address.testutil.TypicalAppointment.APPOINTMENT_1;
import static seedu.address.testutil.TypicalAppointment.APPOINTMENT_2;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.AppointmentBuilder;

public class AppointmentTest {

    @Test
    public void isSame() {
        // same object -> returns true
        assertTrue(APPOINTMENT_1.isSame(APPOINTMENT_1));

        // null -> returns false
        assertFalse(APPOINTMENT_1.isSame(null));

        // different Doctor NRIC -> returns false
        assertFalse(APPOINTMENT_1.isSame(new AppointmentBuilder(APPOINTMENT_1)
                .withDoctorNric(VALID_NRIC_AMY).build()));

        // different Patient NRIC -> returns false
        assertFalse(APPOINTMENT_1.isSame(new AppointmentBuilder(APPOINTMENT_1)
                .withPatientNric(VALID_NRIC_BOB).build()));

        // different Date Time -> returns false
        assertFalse(APPOINTMENT_1.isSame(new AppointmentBuilder(APPOINTMENT_1)
                .withStartTime("2023-12-01 10:30").build()));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Appointment appointmentCopy = new AppointmentBuilder(APPOINTMENT_1).build();
        assertTrue(APPOINTMENT_1.equals(appointmentCopy));

        // same object -> returns true
        assertTrue(APPOINTMENT_1.equals(APPOINTMENT_1));

        // null -> returns false
        assertFalse(APPOINTMENT_1.equals(null));

        // different type -> returns false
        assertFalse(APPOINTMENT_1.equals(5.0));

        // different person -> returns false
        assertFalse(APPOINTMENT_1.equals(APPOINTMENT_2));

        // different Doctor NRIC -> returns false
        Appointment editedAppointment = new AppointmentBuilder(APPOINTMENT_1).withDoctorNric(VALID_NRIC_AMY).build();
        assertFalse(APPOINTMENT_1.equals(editedAppointment));

        // different Patient NRIC -> returns false
        editedAppointment = new AppointmentBuilder(APPOINTMENT_1).withPatientNric(VALID_NRIC_BOB).build();
        assertFalse(APPOINTMENT_1.equals(editedAppointment));

        // different startTime -> returns false
        editedAppointment = new AppointmentBuilder(APPOINTMENT_1).withStartTime("2023-12-01 12:00").build();
        assertFalse(APPOINTMENT_1.equals(editedAppointment));

        // different endTime -> returns false
        editedAppointment = new AppointmentBuilder(APPOINTMENT_1).withEndTime("2023-12-01 12:00").build();
        assertFalse(APPOINTMENT_1.equals(editedAppointment));

    }

    @Test
    public void toStringMethod() {
        String expected = Appointment.class.getCanonicalName() + "{doctorNric=" + APPOINTMENT_1.getDoctorNric()
                + ", patientNric=" + APPOINTMENT_1.getPatientNric()
                + ", startTime=" + APPOINTMENT_1.getStartTime()
                + ", endTime=" + APPOINTMENT_1.getEndTime()
                + "}";
        assertEquals(expected, APPOINTMENT_1.toString());
    }
}
