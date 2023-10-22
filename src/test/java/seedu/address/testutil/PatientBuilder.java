package seedu.address.testutil;

import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Phone;
import seedu.address.model.person.doctor.Doctor;
import seedu.address.model.person.patient.Patient;
import seedu.address.model.remark.Remark;
import seedu.address.model.tag.Tag;

import java.util.Arrays;
import java.util.HashSet;

/**
 * A utility class to help with building Patient objects.
 */
public class PatientBuilder {
    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_NRIC = "T0123456A";

    private Name name;
    private Phone phone;
    private Nric nric;
    private Remark remark;
    private HashSet<Tag> tags;

    /**
     * Creates a {@code PatientBuilder} with the default details.
     */
    public PatientBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        nric = new Nric(DEFAULT_NRIC);
    }

    /**
     * Initializes the PatientBuilder with the data of {@code patientToCopy}.
     */
    public PatientBuilder(Patient patientToCopy) {
        name = patientToCopy.getName();
        phone = patientToCopy.getPhone();
        nric = patientToCopy.getNric();
        remark = patientToCopy.getRemark();
        tags = patientToCopy.getTags();
    }

    /**
     * Sets the {@code Name} of the {@code Patient} that we are building.
     */
    public PatientBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Patient} that we are building.
     */
    public PatientBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Nric} of the {@code Patient} that we are building.
     */
    public PatientBuilder withNric(String nric) {
        this.nric = new Nric(nric);
        return this;
    }

    /**
     * Sets the {@code remark} of the {@code Patient} that we are building.
     */
    public PatientBuilder withRemark(String remark) {
        this.remark = new Remark(remark);
        return this;
    }

    /**
     * Sets the {@code tags} of the {@code Patient} that we are building.
     */
    public PatientBuilder withTags(Tag... tags) {
        HashSet<Tag> tagSet = new HashSet<>(Arrays.asList(tags));
        this.tags = tagSet;
        return this;
    }

    public Patient build() {
        if (this.remark == null || this.tags == null) {
            return new Patient(name, phone, nric);
        }
        return new Patient(name, phone, nric, remark, tags);
    }
}
