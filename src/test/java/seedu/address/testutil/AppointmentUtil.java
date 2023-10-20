package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_START_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DOCTOR_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PATIENT_NRIC;

import seedu.address.logic.commands.AddAppointmentCommand;
import seedu.address.model.appointment.Appointment;

/**
 * A utility class for Appointment.
 */
public class AppointmentUtil {

    /**
     * Returns an add appointment command string for adding the {@code appointment}.
     */
    public static String getAddAppointmentCommand(Appointment appointment) {
        return AddAppointmentCommand.COMMAND_WORD + " " + getAppointmentDetails(appointment);
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

}
