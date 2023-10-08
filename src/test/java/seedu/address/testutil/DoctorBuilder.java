package seedu.address.testutil;

import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.model.person.doctor.Doctor;

/**
 * A utility class to help with building Doctor objects.
 */
public class DoctorBuilder {
    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_NRIC = "T0123456A";

    private Name name;
    private Nric nric;

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

    public Doctor build() {
        return new Doctor(name, nric);
    }
}
