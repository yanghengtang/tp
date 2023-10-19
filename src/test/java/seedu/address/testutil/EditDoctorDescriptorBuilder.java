package seedu.address.testutil;

import seedu.address.logic.commands.EditDoctorCommand.EditDoctorDescriptor;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.model.person.doctor.Doctor;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditDoctorDescriptorBuilder {

    private EditDoctorDescriptor descriptor;

    public EditDoctorDescriptorBuilder() {
        descriptor = new EditDoctorDescriptor();
    }

    public EditDoctorDescriptorBuilder(EditDoctorDescriptor descriptor) {
        this.descriptor = new EditDoctorDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public EditDoctorDescriptorBuilder(Doctor doctor) {
        descriptor = new EditDoctorDescriptor();
        descriptor.setName(doctor.getName());
        descriptor.setNric(doctor.getNric());
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditDoctorDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditDoctorDescriptorBuilder withNric(String nric) {
        descriptor.setNric(new Nric(nric));
        return this;
    }

    public EditDoctorDescriptor build() {
        return descriptor;
    }
}
