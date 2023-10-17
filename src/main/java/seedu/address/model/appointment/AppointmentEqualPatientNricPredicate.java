package seedu.address.model.appointment;

import java.util.Objects;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Nric;

/**
 * Tests that a {@code Doctor}'s {@code Name} matches any of the keywords given.
 */
public class AppointmentEqualPatientNricPredicate implements Predicate<Appointment> {
    private final Nric nric;

    public AppointmentEqualPatientNricPredicate(Nric nric) {
        this.nric = nric;
    }

    @Override
    public boolean test(Appointment appointment) {
        return appointment.getPatientNric().equals(this.nric);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AppointmentEqualPatientNricPredicate)) {
            return false;
        }

        AppointmentEqualPatientNricPredicate otherAppointmentEqualPatientNricPredicate =
                (AppointmentEqualPatientNricPredicate) other;
        return nric.equals(otherAppointmentEqualPatientNricPredicate.nric);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.nric);
    }
    @Override
    public String toString() {
        return new ToStringBuilder(this).add("nric", nric).toString();
    }
}
