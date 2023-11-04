package seedu.address.model.person.patient;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashSet;
import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.remark.Remark;
import seedu.address.model.tag.Tag;

/**
 * Represents a Patient in the system.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Patient extends Person {

    // Identity fields
    private final Phone phone;

    /**
     * Constructor for Patient.
     * Every field must be present and not null.
     * @param name name of patient.
     * @param phone phone number of patient.
     * @param nric nric of patient.
     */
    public Patient(Name name, Phone phone, Nric nric) {
        super(name, nric);
        requireAllNonNull(phone);
        this.phone = phone;
    }

    /**
     * Constructor to set remark and tags.
     * Every field must be present and not null.
     * @param name name of patient.
     * @param phone phone number of patient.
     * @param nric nric of patient.
     * @param remark remark for patient.
     * @param tags tags for patient.
     */
    public Patient(Name name, Phone phone, Nric nric, Remark remark, HashSet<Tag> tags) {
        super(name, nric, remark, tags);
        requireAllNonNull(phone);
        this.phone = phone;
    }

    public Phone getPhone() {
        return phone;
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
        return super.equals(otherPatient) && phone.equals(otherPatient.phone);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(getName(), phone, getNric());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", getName())
                .add("phone", phone)
                .add("nric", getNric())
                .add("remark", getRemark())
                .add("conditions", getTags())
                .toString();
    }

}
