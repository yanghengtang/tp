package seedu.address.model.person.patient;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.Listable;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Phone;

/**
 * Represents a Patient in the system.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Patient implements Listable {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Nric nric;

    /**
     * Every field must be present and not null.
     */
    public Patient(Name name, Phone phone, Nric nric) {
        requireAllNonNull(name, phone, nric);
        this.name = name;
        this.phone = phone;
        this.nric = nric;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Nric getNric() {
        return nric;
    }

    /**
     * Returns true if both patients have the same NRIC.
     * This defines a weaker notion of equality between two patients.
     */
    public boolean isSame(Listable otherListable) {
        if (!(otherListable instanceof Patient)) {
            return false;
        }

        Patient otherPatient = (Patient) otherListable;

        if (otherPatient == this) {
            return true;
        }

        return otherPatient.getNric().equals(getNric());
    }

    /**
     * Returns true if both patients have the same identity fields.
     * This defines a stronger notion of equality between two patients.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Patient)) {
            return false;
        }

        Patient otherPatient = (Patient) other;
        return name.equals(otherPatient.name)
                && phone.equals(otherPatient.phone)
                && nric.equals(otherPatient.nric);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, nric);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("nric", nric)
                .toString();
    }

}
