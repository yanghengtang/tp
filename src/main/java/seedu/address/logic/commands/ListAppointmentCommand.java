package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DOCTOR_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PATIENT_NRIC;
import static seedu.address.model.NewModel.PREDICATE_SHOW_ALL_APPOINTMENTS;

import java.util.Objects;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.NewModel;
import seedu.address.model.appointment.Appointment;

/**
 * Lists all Appoiintments in the address book to the user.
 */
public class ListAppointmentCommand extends NewCommand {

    public static final String COMMAND_WORD = "list_a";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists appointments in the address book. "
            + "Parameters: "
            + PREFIX_PATIENT_NRIC + "PATIENT_NRIC"
            + PREFIX_DOCTOR_NRIC + "DOCTOR_NRIC";

    public static final String MESSAGE_SUCCESS = "Listed all appointments";

    public final Predicate<Appointment> predicate;
    public ListAppointmentCommand(Predicate<Appointment> predicate) {
        this.predicate = predicate;
    }

    public ListAppointmentCommand() {
        this.predicate = PREDICATE_SHOW_ALL_APPOINTMENTS;
    }

    @Override
    public CommandResult execute(NewModel model) {
        requireNonNull(model);
        model.updateFilteredAppointmentList(predicate);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ListAppointmentCommand)) {
            return false;
        }

        ListAppointmentCommand otherListAppointmentCommand = (ListAppointmentCommand) other;
        return predicate.equals(otherListAppointmentCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(predicate);
    }
}
