package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AppointmentEndTimeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AppointmentEndTime(null));
    }

    @Test
    public void constructor_invalidAppointmentTime_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new AppointmentEndTime(invalidName));
    }

    @Test
    public void equals() {
        AppointmentEndTime appointmentEndTime = new AppointmentEndTime("2023-09-11 16:00");

        // same values -> returns true
        assertTrue(appointmentEndTime.equals(new AppointmentEndTime("2023-09-11 16:00")));

        // same object -> returns true
        assertTrue(appointmentEndTime.equals(appointmentEndTime));

        // null -> returns false
        assertFalse(appointmentEndTime.equals(null));

        // different types -> returns false
        assertFalse(appointmentEndTime.equals(5.0f));

        // different values -> returns false
        assertFalse(appointmentEndTime.equals(new AppointmentEndTime("2023-09-11 04:00")));

        AppointmentStartTime appointmentStartTime = new AppointmentStartTime("2023-09-11 16:00");
        assertFalse(appointmentEndTime.equals(appointmentStartTime));
    }
}

