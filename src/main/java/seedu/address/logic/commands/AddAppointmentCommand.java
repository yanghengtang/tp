package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_START_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DOCTOR_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PATIENT_NRIC;

import java.util.Objects;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.NewModel;
import seedu.address.model.appointment.Appointment;


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
    public static final String MESSAGE_SAME_PIC_DIC = "Patient's NRIC cannot be the same as Doctor's NRIC";
    public static final String MESSAGE_INVALID_APPOINTMENT_TIME = "Appointment's end time cannot be before start time";
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
    public CommandResult execute(NewModel model) throws CommandException {
        requireNonNull(model);

        if (!toAdd.getEndTime().getTime().isAfter(toAdd.getStartTime().getTime())) {
            throw new CommandException(MESSAGE_INVALID_APPOINTMENT_TIME);
        }

        if (!model.hasDoctorWithNric(toAdd.getDoctorNric())) {
            throw new CommandException(MESSAGE_INVALID_DOCTOR);
        }

        if (!model.hasPatientWithNric(toAdd.getPatientNric())) {
            throw new CommandException(MESSAGE_INVALID_PATIENT);
        }

        if (toAdd.getPatientNric().equals(toAdd.getDoctorNric())) {
            throw new CommandException(MESSAGE_SAME_PIC_DIC);
        }

        if (model.hasAppointment(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_APPOINTMENT);
        }

        ObservableList<Appointment> appointments = model.getFilteredAppointmentList();

        for (Appointment a : appointments) {
            if (toAdd.getPatientNric().equals(a.getPatientNric())
                    && (toAdd.overlaps(a))) {
                throw new CommandException(MESSAGE_OVERLAPPING_PATIENT_APPOINTMENTS);
            }
            if (toAdd.getDoctorNric().equals(a.getDoctorNric())
                    && (toAdd.overlaps(a))) {
                throw new CommandException(MESSAGE_OVERLAPPING_DOCTOR_APPOINTMENTS);
            }
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
