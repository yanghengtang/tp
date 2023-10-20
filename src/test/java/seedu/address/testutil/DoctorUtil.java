package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddDoctorCommand;
import seedu.address.logic.commands.DeleteDoctorCommand;
import seedu.address.logic.commands.EditDoctorCommand.EditDoctorDescriptor;
import seedu.address.model.person.doctor.Doctor;

/**
 * A utility class for Person.
 */
public class DoctorUtil {

    /**
     * Returns an add doctor command string for adding the {@code doctor}.
     */
    public static String getAddDoctorCommand(Doctor doctor) {
        return AddDoctorCommand.COMMAND_WORD + " " + getDoctorDetails(doctor);
    }

    /**
     * Returns an add patient command string for adding the {@code patient}.
     */
    public static String getDeleteDoctorCommand(Index index) {
        return DeleteDoctorCommand.COMMAND_WORD + " " + index.toString();
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
     * Returns the part of command string for the given {@code EditDoctorDescriptor}'s details.
     */
    public static String getEditDoctorDescriptorDetails(EditDoctorDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getNric().ifPresent(nric -> sb.append(PREFIX_NRIC).append(nric.nric).append(" "));
        return sb.toString();
    }
}
