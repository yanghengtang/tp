package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import seedu.address.logic.commands.AddPatientCommand;
import seedu.address.model.person.patient.Patient;

/**
 * A utility class for Patient.
 */
public class PatientUtil {

    /**
     * Returns an add patient command string for adding the {@code patient}.
     */
    public static String getAddPatientCommand(Patient patient) {
        return AddPatientCommand.COMMAND_WORD + " " + getPatientDetails(patient);
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

}
