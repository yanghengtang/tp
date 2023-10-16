package seedu.address.logic.commands;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.NewModel;
import seedu.address.model.person.patient.Patient;

import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * Deletes a patient identified using it's displayed index from the database.
 */
public class DeletePatientCommand extends NewCommand {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the patient identified by the index number used in the displayed patient list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PATIENT_SUCCESS = "Deleted patient: %1$s";

    private final Index targetIndex;

    public DeletePatientCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(NewModel model) throws CommandException {
        requireNonNull(model);
        List<Patient> lastShownList = model.getFilteredPatientList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
        }

        Patient personToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deletePatient(personToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PATIENT_SUCCESS, Messages.format(personToDelete)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeletePatientCommand)) {
            return false;
        }

        DeletePatientCommand otherDeletePatientCommand = (DeletePatientCommand) other;
        return targetIndex.equals(otherDeletePatientCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
