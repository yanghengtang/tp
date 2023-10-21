package seedu.address.testutil;

import seedu.address.logic.commands.EditAppointmentCommand.EditAppointmentDescriptor;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentEndTime;
import seedu.address.model.appointment.AppointmentStartTime;
import seedu.address.model.person.Nric;

/**
 * A utility class to help with building EditAppointmentDescriptor objects.
 */
public class EditAppointmentDescriptorBuilder {

    private EditAppointmentDescriptor descriptor;

    public EditAppointmentDescriptorBuilder() {
        descriptor = new EditAppointmentDescriptor();
    }

    public EditAppointmentDescriptorBuilder(EditAppointmentDescriptor descriptor) {
        this.descriptor = new EditAppointmentDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditAppointmentDescriptor} with fields containing {@code Appointment}'s details
     */
    public EditAppointmentDescriptorBuilder(Appointment appointment) {
        descriptor = new EditAppointmentDescriptor();
        descriptor.setPatientNric(appointment.getPatientNric());
        descriptor.setDoctorNric(appointment.getDoctorNric());
        descriptor.setAppointmentStartTime(appointment.getStartTime());
        descriptor.setAppointmentEndTime(appointment.getEndTime());
    }

    /**
     * Sets the patientNric of the {@code EditAppointmentDescriptor} that we are building.
     */
    public EditAppointmentDescriptorBuilder withPatientNric(String nric) {
        descriptor.setPatientNric(new Nric(nric));
        return this;
    }

    /**
     * Sets the e doctorNric of the {@code EditPatientDescriptor} that we are building.
     */
    public EditAppointmentDescriptorBuilder withDoctorNric(String nric) {
        descriptor.setDoctorNric(new Nric(nric));
        return this;
    }

    /**
     * Sets the {@code AppointmentStartTIme} of the {@code EditAppointmentDescriptor} that we are building.
     */
    public EditAppointmentDescriptorBuilder withStartTime(String startTime) {
        descriptor.setAppointmentStartTime(new AppointmentStartTime(startTime));
        return this;
    }

    /**
     * Sets the {@code AppointmentEndTIme} of the {@code EditAppointmentDescriptor} that we are building.
     */
    public EditAppointmentDescriptorBuilder withEndTime(String endTime) {
        descriptor.setAppointmentEndTime(new AppointmentEndTime(endTime));
        return this;
    }

    public EditAppointmentDescriptor build() {
        return descriptor;
    }
}
