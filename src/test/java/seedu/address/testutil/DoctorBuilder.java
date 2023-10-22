package seedu.address.testutil;

import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.model.person.doctor.Doctor;
import seedu.address.model.remark.Remark;
import seedu.address.model.tag.Tag;

import javax.print.Doc;
import java.util.Arrays;
import java.util.HashSet;

/**
 * A utility class to help with building Doctor objects.
 */
public class DoctorBuilder {
    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_NRIC = "T0123456A";

    private Name name;
    private Nric nric;
    private Remark remark;
    private HashSet<Tag> tags;

    /**
     * Creates a {@code DoctorBuilder} with the default details.
     */
    public DoctorBuilder() {
        name = new Name(DEFAULT_NAME);
        nric = new Nric(DEFAULT_NRIC);
    }

    /**
     * Initializes the DoctorBuilder with the data of {@code doctorToCopy}.
     */
    public DoctorBuilder(Doctor doctorToCopy) {
        name = doctorToCopy.getName();
        nric = doctorToCopy.getNric();
        remark = doctorToCopy.getRemark();
        tags = doctorToCopy.getTags();
    }

    /**
     * Sets the {@code Name} of the {@code Doctor} that we are building.
     */
    public DoctorBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Nric} of the {@code Doctor} that we are building.
     */
    public DoctorBuilder withNric(String nric) {
        this.nric = new Nric(nric);
        return this;
    }

    /**
     * Sets the {@code remark} of the {@code Doctor} that we are building.
     */
    public DoctorBuilder withRemark(String remark) {
        this.remark = new Remark(remark);
        return this;
    }

    /**
     * Sets the {@code tags} of the {@code Doctor} that we are building.
     */
    public DoctorBuilder withTags(Tag... tags) {
        HashSet<Tag> tagSet = new HashSet<>(Arrays.asList(tags));
        this.tags = tagSet;
        return this;
    }

    public Doctor build() {
        if (this.remark == null || this.tags == null) {
            return new Doctor(name, nric);
        }
        return new Doctor(name, nric, remark, tags);
    }
}
