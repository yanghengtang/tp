package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Objects;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.doctor.Doctor;

/**
 * Views an doctor identified using it's displayed index from the database.
 */
public class ViewDoctorCommand extends Command {

    public static final String COMMAND_WORD = "view_d";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Views the specified doctor from the system.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 2";

    public static final String MESSAGE_VIEW_DOCTOR_SUCCESS = "View Doctor: %1$s";

    private final Index targetIndex;

    public ViewDoctorCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Doctor> lastShownList = model.getFilteredDoctorList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_DOCTOR_DISPLAYED_INDEX);
        }

        Doctor doctorToView = lastShownList.get(targetIndex.getZeroBased());
        model.updateSelectedDoctor(doctorToView);
        return new CommandResult(String.format(MESSAGE_VIEW_DOCTOR_SUCCESS,
                Messages.format(doctorToView)), false, true, false);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ViewDoctorCommand)) {
            return false;
        }

        ViewDoctorCommand otherViewDoctorCommand = (ViewDoctorCommand) other;
        return targetIndex.equals(otherViewDoctorCommand.targetIndex);
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
