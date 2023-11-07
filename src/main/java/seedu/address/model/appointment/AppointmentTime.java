package seedu.address.model.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * Represents a time to be used in Appointment.
 * Guarantees: immutable; is valid as declared in {@link #isValidAppointmentTime(String)}
 */
public abstract class AppointmentTime implements Comparable<AppointmentTime> {
    public static final String MESSAGE_CONSTRAINTS =
            "Appointment %s date and time should be of the format YYYY-MM-DD HH:mm "
                    + "and must be a valid date ";
    public static final String DATETIME_INPUT_FORMAT = "uuuu-MM-dd HH:mm";
    public static final DateTimeFormatter
            DATE_TIME_INPUT_FORMATTER = DateTimeFormatter.ofPattern(DATETIME_INPUT_FORMAT)
            .withResolverStyle(ResolverStyle.STRICT);
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
    public static boolean isValidAppointmentTime(String test) {
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

    @Override
    public int compareTo(AppointmentTime time) {
        if (this.time.isBefore(time.time)) {
            return -1;
        } else if (this.time.isAfter(time.time)) {
            return 1;
        }
        return 0;
    }

    /**
     * Parses a given string using the date_time_formatter.
     * @param time string representation of the time
     * @param msg message to return if the appointment time is invalid
     * @return LocalDateTime object of the given time
     */
    public static LocalDateTime parse(String time, String msg) {
        requireNonNull(time);
        String trimmedTime = time.trim();
        checkArgument(isValidAppointmentTime((trimmedTime)), msg);
        return LocalDateTime.parse(trimmedTime, DATE_TIME_INPUT_FORMATTER);
    }
}
