package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_START_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DOCTOR_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PATIENT_NRIC;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;


/**
 * Adds a person to the address book.
 */
public class AddAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "add_a";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an appointment to the database. "
            + "Parameters: "
            + PREFIX_PATIENT_NRIC + "PATIENT_NRIC "
            + PREFIX_DOCTOR_NRIC + "DOCTOR_NRIC \n"
            + PREFIX_APPOINTMENT_START_TIME + "START_TIME "
            + PREFIX_APPOINTMENT_END_TIME + "END_TIME \n"
            + "Example: "
            + COMMAND_WORD + " "
            + PREFIX_PATIENT_NRIC + "S9912343G "
            + PREFIX_DOCTOR_NRIC + "T0212385J "
            + PREFIX_APPOINTMENT_START_TIME + "2023-09-11 07:30 "
            + PREFIX_APPOINTMENT_END_TIME + "2023-09-11 08:00 ";

    public static final String MESSAGE_SUCCESS = "New appointment added: %1$s";
    public static final String MESSAGE_DUPLICATE_APPOINTMENT =
            "This Appointment already exists in the database";
    public static final String MESSAGE_INVALID_DOCTOR = "This Doctor does not exist in the database";
    public static final String MESSAGE_INVALID_PATIENT = "This Patient does not exist in the database";
    public static final String MESSAGE_OVERLAPPING_PATIENT_APPOINTMENTS =
            "Appointment overlaps with an existing appointment of Patient";
    public static final String MESSAGE_OVERLAPPING_DOCTOR_APPOINTMENTS =
            "Appointment overlaps with an existing appointment of Doctor";

    private final Appointment toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddAppointmentCommand(Appointment appointment) {
        requireNonNull(appointment);
        toAdd = appointment;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasDoctorWithNric(toAdd.getDoctorNric())) {
            throw new CommandException(MESSAGE_INVALID_DOCTOR);
        }

        if (!model.hasPatientWithNric(toAdd.getPatientNric())) {
            throw new CommandException(MESSAGE_INVALID_PATIENT);
        }

        if (model.hasAppointment(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_APPOINTMENT);
        }

        model.addAppointment(toAdd);

        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddAppointmentCommand)) {
            return false;
        }

        AddAppointmentCommand otherAddCommand = (AddAppointmentCommand) other;
        return toAdd.equals(otherAddCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(toAdd);
    }
}
