package seedu.address.model.appointment;

import org.junit.jupiter.api.Test;
import seedu.address.model.person.Name;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

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
    public void isValidAppointmentStartTime() {
        // null name
        assertThrows(NullPointerException.class, () -> AppointmentStartTime.isValidAppointmmentTime(null));

        // invalid name
        assertFalse(AppointmentStartTime.isValidAppointmmentTime("")); // empty string
        assertFalse(AppointmentStartTime.isValidAppointmmentTime(" ")); // spaces only
        assertFalse(AppointmentStartTime.isValidAppointmmentTime("^*$")); // not numeric characters
        assertFalse(AppointmentStartTime.isValidAppointmmentTime("12-01-2023 07:30")); // date in wrong format

        // valid name
        assertTrue(AppointmentStartTime.isValidAppointmmentTime("2023-09-11 08:00")); // in yyyy-dd-mm HH:mm format
        assertTrue(AppointmentStartTime.isValidAppointmmentTime("2023-09-11 16:00")); // in yyyy-dd-mm HH:mm 24h format
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
    }
}

