package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PATIENTS;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.patient.Patient;
import seedu.address.model.tag.Tag;

/**
 * Adds a medical condition to an existing patient in the database.
 */
public class AddMedicalConditionCommand extends Command {
    public static final String COMMAND_WORD = "add_tag_p";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds a medical condition to the patient identified "
            + "by the index number used in the last patient listing. "
            + "Parameters: INDEX (must be a positive integer less than 2147483648) "
            + PREFIX_TAG + "MEDICAL CONDITION\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_TAG + "diabetes";

    public static final String MESSAGE_ADD_CONDITION_SUCCESS = "New medical condition added: %1$s";
    public static final String MESSAGE_ADD_CONDITION_FAILURE = "The medical condition "
            + "already exists in patient: %1$s";
    private final Index index;
    private final Tag medicalCondition;

    /**
     * @param index of the patient in the filtered patient list to add the medical condition
     * @param medicalCondition of the patient to be updated to
     */
    public AddMedicalConditionCommand(Index index, Tag medicalCondition) {
        requireAllNonNull(index, medicalCondition);

        this.index = index;
        this.medicalCondition = medicalCondition;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.index, this.medicalCondition);
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Patient> lastShownList = model.getFilteredPatientList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
        }

        Patient patientToEdit = lastShownList.get(index.getZeroBased());
        if (patientToEdit.getTags().contains(medicalCondition)) {
            throw new CommandException(String.format(MESSAGE_ADD_CONDITION_FAILURE, this.medicalCondition.tagName));
        }
        HashSet<Tag> currentConditions = patientToEdit.getTags();
        currentConditions.add(this.medicalCondition);
        Patient editedPatient = new Patient(
                patientToEdit.getName(), patientToEdit.getPhone(),
                patientToEdit.getNric(), patientToEdit.getRemark(), currentConditions);

        model.setPatient(patientToEdit, editedPatient);
        model.updateFilteredPatientList(PREDICATE_SHOW_ALL_PATIENTS);

        return new CommandResult(String.format(MESSAGE_ADD_CONDITION_SUCCESS, medicalCondition.tagName));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddMedicalConditionCommand)) {
            return false;
        }

        AddMedicalConditionCommand command = (AddMedicalConditionCommand) other;
        return index.equals(command.index)
                && medicalCondition.equals(command.medicalCondition);
    }
}
