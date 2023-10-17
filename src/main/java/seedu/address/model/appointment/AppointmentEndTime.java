package seedu.address.model.appointment;

/**
 * Represents a Appointment's end time in the database.
 * Guarantees: immutable; is valid as declared in {@link AppointmentTime#isValidAppointmentTime(String)}
 */
public class AppointmentEndTime extends AppointmentTime {

    public static final String MESSAGE_CONSTRAINTS = String.format(AppointmentTime.MESSAGE_CONSTRAINTS, "end");

    /**
     * Constructs an {@code AppointmentEndTime}.
     *
     * @param time A valid LocalDateTime time.
     */
    public AppointmentEndTime(String time) {
        super(AppointmentTime.parse(time, MESSAGE_CONSTRAINTS));
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof AppointmentEndTime)) {
            return false;
        }

        AppointmentEndTime otherAppointmentEndTime = (AppointmentEndTime) other;
        return super.equals(otherAppointmentEndTime);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
