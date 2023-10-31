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
 * Adds a prescription to an existing appointment in the database.
 */
public class AddPrescriptionCommand extends Command {
    public static final String COMMAND_WORD = "add_tag_a";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds the prescription to the appointment identified "
            + "by the index number used in the last appointment listing. "
            + "Existing tags will not be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_TAG + " [PRESCRIPTION]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_TAG + "CoughSyrup";

    public static final String MESSAGE_ADD_PRESCRIPTION_SUCCESS = "Added prescription to Appointment: %1$s";
    public static final String MESSAGE_ADD_PRESCRIPTION_FAILURE = "The prescription "
            + "already exist in Appointment: %1$s";

    private final Index index;
    private final Tag prescription;

    /**
     * @param index of the appointment in the filtered appointment list to add prescription to
     * @param prescription of the appointment to be updated
     */
    public AddPrescriptionCommand(Index index, Tag prescription) {
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

        if (currentPrescriptions.contains(prescription)) {
            throw new CommandException(String.format(MESSAGE_ADD_PRESCRIPTION_FAILURE, prescription));
        }

        currentPrescriptions.add(prescription);
        Appointment editedAppointment = new Appointment(
                appointmentToEdit.getDoctorNric(), appointmentToEdit.getPatientNric(),
                appointmentToEdit.getStartTime(),
                appointmentToEdit.getEndTime(), appointmentToEdit.getRemark(), currentPrescriptions);

        model.setAppointment(appointmentToEdit, editedAppointment);
        model.updateFilteredAppointmentList(PREDICATE_SHOW_ALL_APPOINTMENTS);

        return new CommandResult(String.format(MESSAGE_ADD_PRESCRIPTION_SUCCESS, editedAppointment));
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
        if (!(other instanceof AddPrescriptionCommand)) {
            return false;
        }

        AddPrescriptionCommand c = (AddPrescriptionCommand) other;
        return index.equals(c.index)
                && prescription.equals(c.prescription);
    }
}
