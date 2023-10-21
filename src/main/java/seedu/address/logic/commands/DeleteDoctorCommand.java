package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Objects;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.NewModel;
import seedu.address.model.person.doctor.Doctor;

/**
 * Deletes a doctor identified using it's displayed index from the database.
 */
public class DeleteDoctorCommand extends NewCommand {

    public static final String COMMAND_WORD = "delete_d";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the doctor identified by the index number used in the displayed doctor list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_DOCTOR_SUCCESS = "Deleted Doctor: %1$s";

    private final Index targetIndex;

    public DeleteDoctorCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(NewModel model) throws CommandException {
        requireNonNull(model);
        List<Doctor> lastShownList = model.getFilteredDoctorList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_DOCTOR_DISPLAYED_INDEX);
        }

        Doctor doctorToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteDoctor(doctorToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_DOCTOR_SUCCESS, Messages.format(doctorToDelete)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteDoctorCommand)) {
            return false;
        }

        DeleteDoctorCommand otherDeleteCommand = (DeleteDoctorCommand) other;
        return targetIndex.equals(otherDeleteCommand.targetIndex);
    }
    @Override
    public int hashCode() {
        return Objects.hash(targetIndex);
    }
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
