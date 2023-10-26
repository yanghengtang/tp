package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Objects;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;

/**
 * Views an appointment identified using it's displayed index from the database.
 */
public class ViewAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "view_a";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Views the details of the appointment identified by the "
            + "index number used in the displayed appointment list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_VIEW_APPOINTMENT_SUCCESS = "View Appointment: %1$s";

    private final Index targetIndex;

    public ViewAppointmentCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Appointment> lastShownList = model.getFilteredAppointmentList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
        }

        Appointment appointmentToView = lastShownList.get(targetIndex.getZeroBased());
        model.updateSelectedAppointment(appointmentToView);
        return new CommandResult(String.format(MESSAGE_VIEW_APPOINTMENT_SUCCESS,
                Messages.format(appointmentToView)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ViewAppointmentCommand)) {
            return false;
        }

        ViewAppointmentCommand otherViewAppointmentCommand = (ViewAppointmentCommand) other;
        return targetIndex.equals(otherViewAppointmentCommand.targetIndex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
