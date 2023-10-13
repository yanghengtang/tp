package seedu.address.model.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Appointment's start time in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAppointmmentTime(String)}
 */
public class AppointmentStartTime {

    public static final String MESSAGE_CONSTRAINTS =
            "Appointment start date and time should be of the format YYYY-MM-DD HHmm ";
    public static final String DATETIME_INPUT_FORMAT = "yyyy-MM-dd HH:mm";
    public static final DateTimeFormatter
            DATE_TIME_INPUT_FORMATTER = DateTimeFormatter.ofPattern(DATETIME_INPUT_FORMAT);
    public final LocalDateTime time;

    /**
     * Constructs an {@code AppointmentStartTime}.
     *
     * @param time A valid LocalDateTime time.
     */
    public AppointmentStartTime(String time) {
        requireNonNull(time);
        String trimmedTime = time.trim();
        checkArgument(isValidAppointmmentTime((trimmedTime)), MESSAGE_CONSTRAINTS);
        this.time = LocalDateTime.parse(trimmedTime, DATE_TIME_INPUT_FORMATTER);
    }

    /**
     * Returns if a given string is a valid dateTime.
     */
    public static boolean isValidAppointmmentTime(String test) {
        try {
            LocalDateTime.parse(test, DATE_TIME_INPUT_FORMATTER);
            return true; // Parsing successful, so it matches the specified format.
        } catch (DateTimeParseException e) {
            return false; // Parsing failed, indicating it doesn't match the format.
        }
    }

    @Override
    public String toString() {
        return time.format(DateTimeFormatter.ofPattern(DATETIME_INPUT_FORMAT));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AppointmentStartTime)) {
            return false;
        }

        AppointmentStartTime otherAppointmentStartTime = (AppointmentStartTime) other;
        return time.equals(otherAppointmentStartTime.time);
    }

    @Override
    public int hashCode() {
        return time.hashCode();
    }
}
