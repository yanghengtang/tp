package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_DOCTORS;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.doctor.Doctor;
import seedu.address.model.tag.Tag;

/**
 * Deletes a specialisation of an existing doctor in the database.
 */
public class DeleteSpecialisationCommand extends Command {
    public static final String COMMAND_WORD = "delete_spec";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes a specialisation of the doctor "
            + "by the index number used in the last doctor listing. "
            + "Parameters: INDEX (must be a positive integer) "
            + "t\\ [SPECIALISATION]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "t\\ Orthopaedic ";

    public static final String MESSAGE_DELETE_SPECIALISATION_SUCCESS = "Specialisation deleted: %1$s";
    public static final String MESSAGE_DELETE_SPECIALISATION_FAILURE = "Specialisation does not exist: %1$s";

    private final Index index;
    private final Tag specialisation;

    /**
     * @param index of the doctor in the filtered doctor list to delete the specialisation
     * @param specialisation of the doctor to be deleted to
     */
    public DeleteSpecialisationCommand(Index index, Tag specialisation) {
        requireAllNonNull(index, specialisation);

        this.index = index;
        this.specialisation = specialisation;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.index, this.specialisation);
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Doctor> lastShownList = model.getFilteredDoctorList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_DOCTOR_DISPLAYED_INDEX);
        }
        Doctor doctorToEdit = lastShownList.get(index.getZeroBased());
        HashSet<Tag> currentConditions = doctorToEdit.getTags();

        if (!currentConditions.contains(specialisation)) {
            throw new CommandException(String.format(MESSAGE_DELETE_SPECIALISATION_FAILURE,
                    specialisation.tagName));
        }

        currentConditions.remove(specialisation);
        Doctor editedDoctor = new Doctor(
                doctorToEdit.getName(),
                doctorToEdit.getNric(),
                doctorToEdit.getRemark(), currentConditions);

        model.setDoctor(doctorToEdit, editedDoctor);
        model.updateFilteredDoctorList(PREDICATE_SHOW_ALL_DOCTORS);

        return new CommandResult(String.format(MESSAGE_DELETE_SPECIALISATION_SUCCESS,
                specialisation.tagName));

    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteSpecialisationCommand)) {
            return false;
        }

        DeleteSpecialisationCommand command = (DeleteSpecialisationCommand) other;
        return index.equals(command.index)
                && specialisation.equals(command.specialisation);
    }


}
