package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_DOCTORS;

import java.util.List;
import java.util.Objects;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.doctor.Doctor;
import seedu.address.model.remark.Remark;

/**
 * Changes the remark of an existing doctor in the database.
 */
public class DoctorRemarkCommand extends Command {

    public static final String COMMAND_WORD = "remark_d";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the remark of the doctor identified "
            + "by the index number used in the last doctor listing. "
            + "Existing remark will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer less than 2147483648) "
            + "[" + PREFIX_REMARK + "REMARK]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_REMARK + "Doctor will not be in clinic until  30/12/2023.";

    public static final String MESSAGE_ADD_REMARK_SUCCESS = "Added remark to Doctor at index %1$s";
    public static final String MESSAGE_DELETE_REMARK_SUCCESS = "Removed remark from Doctor at index %1$s";

    private final Index index;
    private final Remark remark;

    /**
     * @param index of the doctor in the filtered doctor list to edit the remark
     * @param remark of the doctor to be updated to
     */
    public DoctorRemarkCommand(Index index, Remark remark) {
        requireAllNonNull(index, remark);

        this.index = index;
        this.remark = remark;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Doctor> lastShownList = model.getFilteredDoctorList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_DOCTOR_DISPLAYED_INDEX);
        }

        Doctor doctorToEdit = lastShownList.get(index.getZeroBased());
        Doctor editedDoctor = new Doctor(
                doctorToEdit.getName(),
                doctorToEdit.getNric(),
                remark, doctorToEdit.getTags());

        model.setDoctor(doctorToEdit, editedDoctor);
        model.updateFilteredDoctorList(PREDICATE_SHOW_ALL_DOCTORS);

        return new CommandResult(generateSuccessMessage(index));
    }

    /**
     * Generates a command execution success message based on whether
     * the remark is added to or removed from
     * {@code doctorToEdit}.
     */
    private String generateSuccessMessage(Index index) {
        String message = !remark.remark.isEmpty() ? MESSAGE_ADD_REMARK_SUCCESS : MESSAGE_DELETE_REMARK_SUCCESS;
        return String.format(message, index.getOneBased());
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
        if (!(other instanceof DoctorRemarkCommand)) {
            return false;
        }

        DoctorRemarkCommand e = (DoctorRemarkCommand) other;
        return index.equals(e.index)
                && remark.equals(e.remark);
    }
}
