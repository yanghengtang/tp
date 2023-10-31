package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_APPOINTMENTS;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.tag.Tag;

/**
 * Deletes a prescription from an existing appointment in the database.
 */
public class DeletePrescriptionCommand extends Command {
    public static final String COMMAND_WORD = "delete_pres";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the prescription from the appointment identified "
            + "by the index number used in the last appointment listing. "
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_TAG + " [REMARK]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_TAG + "CoughSyrup";

    public static final String MESSAGE_DELETE_PRESCRIPTION_SUCCESS = "Deleted prescription from Appointment: %1$s";
    public static final String MESSAGE_DELETE_PRESCRIPTION_FAILURE = "The prescription "
            + "does not exist in Appointment: %1$s";

    private final Index index;
    private final Tag prescription;

    /**
     * @param index of the appointment in the filtered appointment list to delete prescription from
     * @param prescription of the appointment to be updated
     */
    public DeletePrescriptionCommand(Index index, Tag prescription) {
        requireAllNonNull(index, prescription);

        this.index = index;
        this.prescription = prescription;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Appointment> lastShownList = model.getFilteredAppointmentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
        }

        Appointment appointmentToEdit = lastShownList.get(index.getZeroBased());
        HashSet<Tag> currentPrescriptions = appointmentToEdit.getTags();

        if (!currentPrescriptions.contains(prescription)) {
            throw new CommandException(String.format(MESSAGE_DELETE_PRESCRIPTION_FAILURE, prescription));
        }

        currentPrescriptions.remove(prescription);
        Appointment editedAppointment = new Appointment(
                appointmentToEdit.getDoctorNric(), appointmentToEdit.getPatientNric(),
                appointmentToEdit.getStartTime(),
                appointmentToEdit.getEndTime(), appointmentToEdit.getRemark(), currentPrescriptions);

        model.setAppointment(appointmentToEdit, editedAppointment);
        model.updateFilteredAppointmentList(PREDICATE_SHOW_ALL_APPOINTMENTS);

        return new CommandResult(String.format(MESSAGE_DELETE_PRESCRIPTION_SUCCESS, editedAppointment));
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.index, this.prescription);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeletePrescriptionCommand)) {
            return false;
        }

        DeletePrescriptionCommand c = (DeletePrescriptionCommand) other;
        return index.equals(c.index)
                && prescription.equals(c.prescription);
    }
}
