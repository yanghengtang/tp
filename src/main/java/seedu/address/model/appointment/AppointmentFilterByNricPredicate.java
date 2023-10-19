package seedu.address.model.appointment;

import java.util.Objects;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that an {@code Appointment}'s {@code Doctor}'s {@code Nric}
 * and {@code Patient}'s {@code Nric} matches the predicates given.
 */
public class AppointmentFilterByNricPredicate implements Predicate<Appointment> {
    private final Predicate<Appointment> patientPredicate;
    private final Predicate<Appointment> doctorPredicate;

    /**
     * Constructor for a {@code AppointmentFilterByNricPredicate} object
     * @param patientPredicate
     * @param doctorPredicate
     */
    public AppointmentFilterByNricPredicate(Predicate<Appointment> patientPredicate,
                                               Predicate<Appointment> doctorPredicate) {
        this.patientPredicate = patientPredicate;
        this.doctorPredicate = doctorPredicate;
    }

    @Override
    public boolean test(Appointment appointment) {
        return patientPredicate.test(appointment) && doctorPredicate.test(appointment);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AppointmentFilterByNricPredicate)) {
            return false;
        }

        AppointmentFilterByNricPredicate otherAppointmentFilterByNricPredicate =
                (AppointmentFilterByNricPredicate) other;
        return patientPredicate.equals(otherAppointmentFilterByNricPredicate.patientPredicate)
                && doctorPredicate.equals(otherAppointmentFilterByNricPredicate.doctorPredicate);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.patientPredicate);
    }
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("patientPredicate", patientPredicate)
                .add("doctorPredicate", doctorPredicate)
                .toString();
    }
}

