package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_START_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DOCTOR_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PATIENT_NRIC;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.NewModel;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.exceptions.DuplicateItemException;

/**
 * Adds a person to the address book.
 */
public class AddAppointmentCommand extends NewCommand {

    public static final String COMMAND_WORD = "add_a";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an appointment to the address book. "
            + "Parameters: "
            + PREFIX_PATIENT_NRIC + "PATIENT_NRIC"
            + PREFIX_DOCTOR_NRIC + "DOCTOR_NRIC"
            + PREFIX_APPOINTMENT_START_TIME + "START_TIME "
            + PREFIX_APPOINTMENT_END_TIME + "END_TIME ";

    public static final String MESSAGE_SUCCESS = "New appointment added: %1$s";
    public static final String MESSAGE_DUPLICATE_APPOINTMENT =
            "This Appointment already exists in the list of appointments";
    public static final String MESSAGE_INVALID_DOCTOR = "This Doctor does not exist in the list of doctors";
    public static final String MESSAGE_INVALID_PATIENT = "This Patient does not exist in the list of patients";

    private final Appointment toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddAppointmentCommand(Appointment appointment) {
        requireNonNull(appointment);
        toAdd = appointment;
    }

    @Override
    public CommandResult execute(NewModel model) throws CommandException {
        requireNonNull(model);

        if (!model.hasDoctorWithNric(toAdd.getDoctorNric())) {
            throw new CommandException(MESSAGE_INVALID_DOCTOR);
        }

        if (!model.hasPatientWithNric(toAdd.getPatientNric())) {
            throw new CommandException(MESSAGE_INVALID_PATIENT);
        }

        try {
            model.addAppointment(toAdd);
        } catch (DuplicateItemException e) {
            throw new CommandException(MESSAGE_DUPLICATE_APPOINTMENT);
        }

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
}
