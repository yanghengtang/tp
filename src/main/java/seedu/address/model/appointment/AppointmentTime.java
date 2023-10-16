package seedu.address.model.appointment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a time to be used in Appointment
 * Guarantees: immutable; is valid as declared in {@link #isValidAppointmmentTime(String)}
 */
public abstract class AppointmentTime {
    public static final String MESSAGE_CONSTRAINTS =
            "Appointment %s date and time should be of the format YYYY-MM-DD HH:mm ";
    public static final String DATETIME_INPUT_FORMAT = "yyyy-MM-dd HH:mm";
    public static final DateTimeFormatter
            DATE_TIME_INPUT_FORMATTER = DateTimeFormatter.ofPattern(DATETIME_INPUT_FORMAT);
    private final LocalDateTime time;

    /**
     * Constructs an {@code AppointmentTime}.
     *
     * @param time A valid LocalDateTime time.
     */
    public AppointmentTime(LocalDateTime time) {
        requireNonNull(time);
        this.time = time;
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

    public LocalDateTime getTime() {
        return this.time;
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
        if (!(other instanceof AppointmentTime)) {
            return false;
        }

        AppointmentTime otherAppointmentTime = (AppointmentTime) other;
        return time.equals(otherAppointmentTime.time);
    }

    @Override
    public int hashCode() {
        return time.hashCode();
    }

    public static LocalDateTime parse(String time, String msg) {
        requireNonNull(time);
        String trimmedTime = time.trim();
        checkArgument(isValidAppointmmentTime((trimmedTime)), msg);
        return LocalDateTime.parse(trimmedTime, DATE_TIME_INPUT_FORMATTER);
    }
}
