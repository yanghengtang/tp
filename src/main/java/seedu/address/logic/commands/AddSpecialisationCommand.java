package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_DOCTORS;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.doctor.Doctor;
import seedu.address.model.tag.Tag;

/**
 * Adds a prescription to an existing appointment in the database.
 */
public class AddSpecialisationCommand extends Command {

    public static final String COMMAND_WORD = "add_tag_d";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds a specialisation tag to the doctor identified "
            + "by the index number used in the displayed doctor list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + PREFIX_TAG + "[SPECIALISATION]\n"
            + "Example: " + COMMAND_WORD + " 1 " + PREFIX_TAG + "Orthopaedic";

    public static final String MESSAGE_ADD_SPECIALISATION_SUCCESS = "New Specialisation added: %1$s";
    public static final String MESSAGE_ADD_SPECIALISATION_FAILURE = "The Specialisation already exists in doctor";

    private final Index index;
    private final Tag specialisation;

    /**
     * Creates an AddSpecialisationCommand to add the specified {@code Doctor}
     */
    public AddSpecialisationCommand(Index index, Tag specialisation) {
        requireAllNonNull(index, specialisation);

        this.index = index;
        this.specialisation = specialisation;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Doctor> lastShownList = model.getFilteredDoctorList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_DOCTOR_DISPLAYED_INDEX);
        }

        Doctor doctorToEdit = lastShownList.get(index.getZeroBased());

        HashSet<Tag> doctorTags = doctorToEdit.getTags();

        if (doctorTags.contains(specialisation)) {
            throw new CommandException(MESSAGE_ADD_SPECIALISATION_FAILURE);
        }

        doctorTags.add(specialisation);

        Doctor editedDoctor = new Doctor(
                doctorToEdit.getName(), doctorToEdit.getNric(),
                doctorToEdit.getRemark(), doctorTags);

        model.setDoctor(doctorToEdit, editedDoctor);
        model.updateFilteredDoctorList(PREDICATE_SHOW_ALL_DOCTORS);


        return new CommandResult(String.format(MESSAGE_ADD_SPECIALISATION_SUCCESS, editedDoctor));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddSpecialisationCommand)) {
            return false;
        }

        AddSpecialisationCommand otherAddCommand = (AddSpecialisationCommand) other;
        return index.equals(otherAddCommand.index)
                && specialisation.equals(otherAddCommand.specialisation);
    }
    @Override
    public int hashCode() {
        return Objects.hash(index, specialisation);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("specialisation", specialisation)
                .toString();
    }
}
