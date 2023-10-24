package seedu.address.model.person.doctor;

import java.util.HashSet;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.Listable;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;
import seedu.address.model.remark.Remark;
import seedu.address.model.tag.Tag;

/**
 * Represents a Doctor in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Doctor extends Person {
    /**
     * Every field must be present and not null.
     */
    public Doctor(Name name, Nric nric) {
        super(name, nric);
    }

    /**
     * Constructor to set remark and tags.
     * Every field must be present and not null.
     * @param name name of doctor.
     * @param nric nric of doctor.
     * @param remark remark for doctor.
     * @param tags tags for doctor.
     */
    public Doctor(Name name, Nric nric, Remark remark, HashSet<Tag> tags) {
        super(name, nric, remark, tags);
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
        return super.equals(otherDoctor);
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", getName())
                .add("nric", getNric())
                .toString();
    }
}
