package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PATIENTS;

import java.util.List;
import java.util.Objects;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.patient.Patient;
import seedu.address.model.remark.Remark;

/**
 * Changes the remark of an existing patient in the database.
 */
public class PatientRemarkCommand extends Command {
    public static final String COMMAND_WORD = "remark_p";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the remark of the patient identified "
            + "by the index number used in the last patient listing. "
            + "Existing remark will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "r\\ [REMARK]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "r/ Patient to follow up in 1 month.";

    public static final String MESSAGE_ADD_REMARK_SUCCESS = "Added remark to Patient at index %1$s";
    public static final String MESSAGE_DELETE_REMARK_SUCCESS = "Removed remark from Patient at index %1$s";

    private final Index index;
    private final Remark remark;

    /**
     * @param index of the patient in the filtered patient list to edit the remark
     * @param remark of the patient to be updated to
     */
    public PatientRemarkCommand(Index index, Remark remark) {
        requireAllNonNull(index, remark);

        this.index = index;
        this.remark = remark;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.index, this.remark);
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Patient> lastShownList = model.getFilteredPatientList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
        }

        Patient patientToEdit = lastShownList.get(index.getZeroBased());
        Patient editedPatient = new Patient(
                patientToEdit.getName(), patientToEdit.getPhone(),
                patientToEdit.getNric(), remark, patientToEdit.getTags());

        model.setPatient(patientToEdit, editedPatient);
        model.updateFilteredPatientList(PREDICATE_SHOW_ALL_PATIENTS);

        return new CommandResult(generateSuccessMessage(index));
    }

    /**
     * Generates a command execution success message based on whether
     * the remark is added to or removed from
     * {@code patientToEdit}.
     */
    private String generateSuccessMessage(Index index) {
        String message = !remark.remark.isEmpty() ? MESSAGE_ADD_REMARK_SUCCESS : MESSAGE_DELETE_REMARK_SUCCESS;
        return String.format(message, index.getZeroBased());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PatientRemarkCommand)) {
            return false;
        }

        PatientRemarkCommand command = (PatientRemarkCommand) other;
        return index.equals(command.index)
                && remark.equals(command.remark);
    }
}
