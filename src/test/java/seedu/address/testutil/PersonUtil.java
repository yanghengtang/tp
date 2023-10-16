package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_START_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DOCTOR_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PATIENT_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.address.logic.commands.AddAppointmentCommand;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddDoctorCommand;
import seedu.address.logic.commands.AddPatientCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Person;
import seedu.address.model.person.doctor.Doctor;
import seedu.address.model.person.patient.Patient;
import seedu.address.model.tag.Tag;

/**
 * A utility class for Person.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(Person person) {
        return AddCommand.COMMAND_WORD + " " + getPersonDetails(person);
    }

    /**
     * Returns an add appointment command string for adding the {@code appointment}.
     */
    public static String getAddAppointmentCommand(Appointment appointment) {
        return AddAppointmentCommand.COMMAND_WORD + " " + getAppointmentDetails(appointment);
    }

    /**
     * Returns an add doctor command string for adding the {@code doctor}.
     */
    public static String getAddDoctorCommand(Doctor doctor) {
        return AddDoctorCommand.COMMAND_WORD + " " + getDoctorDetails(doctor);
    }

    /**
     * Returns an add patient command string for adding the {@code patient}.
     */
    public static String getAddPatientCommand(Patient patient) {
        return AddPatientCommand.COMMAND_WORD + " " + getPatientDetails(patient);
    }
    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPersonDetails(Person person) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + person.getName().fullName + " ");
        sb.append(PREFIX_PHONE + person.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + person.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + person.getAddress().value + " ");
        person.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code appointment}'s details.
     */
    public static String getAppointmentDetails(Appointment appointment) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_PATIENT_NRIC + appointment.getPatientNric().toString() + " ");
        sb.append(PREFIX_DOCTOR_NRIC + appointment.getDoctorNric().toString() + " ");
        sb.append(PREFIX_APPOINTMENT_START_TIME + appointment.getStartTime().toString() + " ");
        sb.append(PREFIX_APPOINTMENT_END_TIME + appointment.getEndTime().toString() + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code doctor}'s details.
     */
    public static String getDoctorDetails(Doctor doctor) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NRIC + doctor.getNric().toString() + " ");
        sb.append(PREFIX_NAME + doctor.getName().toString() + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code patient}'s details.
     */
    public static String getPatientDetails(Patient patient) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NRIC + patient.getNric().toString() + " ");
        sb.append(PREFIX_NAME + patient.getName().toString() + " ");
        sb.append(PREFIX_PHONE + patient.getPhone().toString() + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
