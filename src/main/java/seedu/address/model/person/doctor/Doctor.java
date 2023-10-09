package seedu.address.model.person.doctor;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.Listable;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;

/**
 * Represents a Doctor in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Doctor implements Listable {
    // Identity fields
    private final Name name;
    private final Nric nric;

    /**
     * Every field must be present and not null.
     */
    public Doctor(Name name, Nric nric) {
        requireAllNonNull(name, nric);
        this.name = name;
        this.nric = nric;
    }

    public Name getName() {
        return name;
    }

    public Nric getNric() {
        return nric;
    }

    /**
     * Returns true if both doctors have the same NRIC.
     * This defines a weaker notion of equality between two doctors.
     */
    public boolean isSame(Listable otherListable) {
        if (!(otherListable instanceof Doctor)) {
            return false;
        }

        Doctor otherDoctor = (Doctor) otherListable;

        if (otherDoctor == this) {
            return true;
        }

        return otherDoctor.getNric().equals(getNric());
    }

    /**
     * Returns true if both doctors have the same identity fields.
     * This defines a stronger notion of equality between two doctors.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Doctor)) {
            return false;
        }

        Doctor otherDoctor = (Doctor) other;
        return name.equals(otherDoctor.name)
                && nric.equals(otherDoctor.nric);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, nric);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("nric", nric)
                .toString();
    }
}
