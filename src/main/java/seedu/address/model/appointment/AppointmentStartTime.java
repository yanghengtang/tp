package seedu.address.model.appointment;

import java.time.LocalDateTime;

/**
 * Represents a Appointment's start time in the address book.
 * Guarantees: immutable; is valid as declared in {@link AppointmentTime#isValidAppointmmentTime(String)}
 */
public class AppointmentStartTime extends AppointmentTime {

    public static final String MESSAGE_CONSTRAINTS = String.format(AppointmentTime.MESSAGE_CONSTRAINTS, "start");

    /**
     * Constructs an {@code AppointmentStartTime}.
     *
     * @param time A valid LocalDateTime time.
     */
    public AppointmentStartTime(String time) {
        super(AppointmentTime.parse(time, MESSAGE_CONSTRAINTS));
    }
}
