package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Objects;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.patient.Patient;

/**
 * Views a patient identified using it's displayed index from the database.
 */
public class ViewPatientCommand extends Command {

    public static final String COMMAND_WORD = "view_p";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Views the details of the patient identified by the "
            + "index number used in the displayed patient list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_VIEW_PATIENT_SUCCESS = "View Patient: %1$s";

    private final Index targetIndex;

    public ViewPatientCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Patient> lastShownList = model.getFilteredPatientList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
        }

        Patient patientToView = lastShownList.get(targetIndex.getZeroBased());
        model.updateSelectedPatient(patientToView);
        return new CommandResult(String.format(MESSAGE_VIEW_PATIENT_SUCCESS,
                Messages.format(patientToView)), false, false, true);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ViewPatientCommand)) {
            return false;
        }

        ViewPatientCommand otherViewPatientCommand = (ViewPatientCommand) other;
        return targetIndex.equals(otherViewPatientCommand.targetIndex);
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
