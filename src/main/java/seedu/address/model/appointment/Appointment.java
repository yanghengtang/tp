package seedu.address.model.appointment;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.Listable;
import seedu.address.model.person.doctor.Doctor;
import seedu.address.model.person.patient.Patient;

/**
 * Represents a Appointment in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Appointment implements Listable {
    private final Doctor doctor;
    private final Patient patient;
    private final LocalDateTime dateTime;

    /**
     * Every field must be present and not null.
     */
    public Appointment(Doctor doctor, Patient patient, LocalDateTime dateTime) {
        requireAllNonNull(doctor, patient, dateTime);
        this.doctor = doctor;
        this.patient = patient;
        this.dateTime = dateTime;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public boolean isSame(Listable otherListable) {
        if (!(otherListable instanceof Appointment)) {
            return false;
        }

        Appointment otherAppointment = (Appointment) otherListable;

        if (otherAppointment == this) {
            return true;
        }

        return otherAppointment.getDoctor().isSame(getDoctor())
                && otherAppointment.getPatient().isSame(getPatient())
                && otherAppointment.getDateTime().equals(getDateTime());
    }

    /**
     * Returns true if both appointments have the same identity fields.
     * This defines a stronger notion of equality between two patients.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Appointment)) {
            return false;
        }

        Appointment otherAppointment = (Appointment) other;
        return doctor.equals(otherAppointment.doctor)
                && patient.equals(otherAppointment.patient)
                && dateTime.equals(otherAppointment.dateTime);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(doctor, patient, dateTime);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("doctor", doctor)
                .add("patient", patient)
                .add("dateTime", dateTime)
                .toString();
    }
}
