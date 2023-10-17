package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;


public class AppointmentTimeTest {
    public static final LocalDateTime VALID_TIME =
            LocalDateTime.of(2023, 10, 16, 20, 30);
    public static final LocalDateTime VALID_TIME_2 =
            LocalDateTime.of(2023, 10, 17, 20, 30);
    public static final String VALID_TIME_STRING = "2023-10-16 20:30";
    public static final String VALID_TIME_STRING_2 = "2023-10-17 20:30";
    public static final String INVALID_TIME_STRING = "2023-10-16 2030";
    public static final String MESSAGE = "Test Message";

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AppointmentTimeStub(null));
    }

    @Test
    public void getTime() {
        AppointmentTime appointmentTime = new AppointmentTimeStub(VALID_TIME);
        assertEquals(VALID_TIME, appointmentTime.getTime());
        assertNotEquals(LocalDateTime.now(), appointmentTime.getTime());
    }

    @Test
    public void isValidAppointmentTime() {
        // null name
        assertThrows(NullPointerException.class, () -> AppointmentTime.isValidAppointmentTime(null));

        // invalid name
        assertFalse(AppointmentTime.isValidAppointmentTime("")); // empty string
        assertFalse(AppointmentTime.isValidAppointmentTime(" ")); // spaces only
        assertFalse(AppointmentTime.isValidAppointmentTime("^*$")); // not numeric characters
        assertFalse(AppointmentTime.isValidAppointmentTime("12-01-2023 07:30")); // date in wrong format

        // valid name
        assertTrue(AppointmentStartTime.isValidAppointmentTime("2023-09-11 08:00")); // in yyyy-dd-mm HH:mm format
        assertTrue(AppointmentStartTime.isValidAppointmentTime("2023-09-11 16:00")); // in yyyy-dd-mm HH:mm 24h format
    }

    @Test
    public void toString_test() {
        AppointmentTime appointmentTime = new AppointmentTimeStub(VALID_TIME);
        assertEquals(VALID_TIME_STRING, appointmentTime.toString());
        assertNotEquals(INVALID_TIME_STRING, appointmentTime.toString());
    }

    @Test
    public void equals() {
        AppointmentTime appointmentTime = new AppointmentTimeStub(VALID_TIME);

        // same values -> returns true
        assertTrue(appointmentTime.equals(new AppointmentTimeStub(VALID_TIME)));

        // same object -> returns true
        assertTrue(appointmentTime.equals(appointmentTime));

        // null -> returns false
        assertFalse(appointmentTime.equals(null));

        // different types -> returns false
        assertFalse(appointmentTime.equals(5.0f));

        // different values -> returns false
        assertFalse(appointmentTime.equals(new AppointmentTimeStub(VALID_TIME_2)));
    }

    @Test
    public void parse() {
        // null time string -> throws NullPointerException
        assertThrows(NullPointerException.class, () -> AppointmentTime.parse(null, MESSAGE));

        // invalid time string -> throws IllegalArgumentException with message
        assertThrows(IllegalArgumentException.class, MESSAGE, () ->
                AppointmentTime.parse(INVALID_TIME_STRING, MESSAGE));

        // valid time string -> returns LocalDateTime object
        assertEquals(VALID_TIME_2, AppointmentTime.parse(VALID_TIME_STRING_2, MESSAGE));
    }
}

class AppointmentTimeStub extends AppointmentTime {
    /**
     * Constructs an {@code AppointmentTime}.
     *
     * @param time A valid LocalDateTime time.
     */
    public AppointmentTimeStub(LocalDateTime time) {
        super(time);
    }
}
