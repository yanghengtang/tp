package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AppointmentStartTimeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AppointmentStartTime(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new AppointmentStartTime(invalidName));
    }

    @Test
    public void equals() {
        AppointmentStartTime appointmentStartTime = new AppointmentStartTime("2023-09-11 16:00");

        // same values -> returns true
        assertTrue(appointmentStartTime.equals(new AppointmentStartTime("2023-09-11 16:00")));

        // same object -> returns true
        assertTrue(appointmentStartTime.equals(appointmentStartTime));

        // null -> returns false
        assertFalse(appointmentStartTime.equals(null));

        // different types -> returns false
        assertFalse(appointmentStartTime.equals(5.0f));

        // different values -> returns false
        assertFalse(appointmentStartTime.equals(new AppointmentStartTime("2023-09-11 04:00")));

        AppointmentEndTime appointmentEndTime = new AppointmentEndTime("2023-09-11 16:00");
        assertFalse(appointmentStartTime.equals(appointmentEndTime));
    }
}

