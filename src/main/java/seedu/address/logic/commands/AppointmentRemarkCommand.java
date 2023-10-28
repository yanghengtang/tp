package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_APPOINTMENTS;

import java.util.List;
import java.util.Objects;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.remark.Remark;

/**
 * Changes the remark of an existing appointment in the database.
 */
public class AppointmentRemarkCommand extends Command {
    public static final String COMMAND_WORD = "remark_a";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the remark of the appointment identified "
            + "by the index number used in the last appointment listing. "
            + "Existing remark will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "r\\ [REMARK]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "r/ Patient to follow up in 1 month.";

    public static final String MESSAGE_ADD_REMARK_SUCCESS = "Added remark to Appointment: %1$s";
    public static final String MESSAGE_DELETE_REMARK_SUCCESS = "Removed remark from Appointment: %1$s";

    private final Index index;
    private final Remark remark;

    /**
     * @param index of the person in the filtered appointment list to edit the remark
     * @param remark of the person to be updated to
     */
    public AppointmentRemarkCommand(Index index, Remark remark) {
        requireAllNonNull(index, remark);

        this.index = index;
        this.remark = remark;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Appointment> lastShownList = model.getFilteredAppointmentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
        }

        Appointment appointmentToEdit = lastShownList.get(index.getZeroBased());
        Appointment editedAppointment = new Appointment(
                appointmentToEdit.getDoctorNric(), appointmentToEdit.getPatientNric(),
                appointmentToEdit.getStartTime(),
                appointmentToEdit.getEndTime(), remark, appointmentToEdit.getTags());

        model.setAppointment(appointmentToEdit, editedAppointment);
        model.updateFilteredAppointmentList(PREDICATE_SHOW_ALL_APPOINTMENTS);

        return new CommandResult(generateSuccessMessage(editedAppointment));
    }

    /**
     * Generates a command execution success message based on whether
     * the remark is added to or removed from
     * {@code appointmentToEdit}.
     */
    private String generateSuccessMessage(Appointment appointmentToEdit) {
        String message = !remark.remark.isEmpty() ? MESSAGE_ADD_REMARK_SUCCESS : MESSAGE_DELETE_REMARK_SUCCESS;
        return String.format(message, appointmentToEdit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.index, this.remark);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AppointmentRemarkCommand)) {
            return false;
        }

        AppointmentRemarkCommand e = (AppointmentRemarkCommand) other;
        return index.equals(e.index)
                && remark.equals(e.remark);
    }
}
