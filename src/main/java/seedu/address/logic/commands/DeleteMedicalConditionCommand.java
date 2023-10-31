package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
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
 * Deletes a medical condition of an existing patient in the database.
 */
public class DeleteMedicalConditionCommand extends Command {
    public static final String COMMAND_WORD = "delete_tag_p";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes a medical condition of the patient "
            + "by the index number used in the last patient listing. "
            + "Parameters: INDEX (must be a positive integer) "
            + "t\\ [MEDICAL CONDITION]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "t\\ diabetes ";

    public static final String MESSAGE_DELETE_CONDITION_SUCCESS = "Medical condition deleted: %1$s";
    public static final String MESSAGE_DELETE_CONDITION_FAILURE = "Medical condition does not exist: %1$s";

    private final Index index;
    private final Tag medicalCondition;

    /**
     * @param index of the patient in the filtered patient list to delete the medical condition
     * @param medicalCondition of the patient to be updated to
     */
    public DeleteMedicalConditionCommand(Index index, Tag medicalCondition) {
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
        HashSet<Tag> currentConditions = patientToEdit.getTags();

        if (!currentConditions.contains(medicalCondition)) {
            throw new CommandException(String.format(MESSAGE_DELETE_CONDITION_FAILURE, medicalCondition.tagName));
        }

        currentConditions.remove(medicalCondition);
        Patient editedPatient = new Patient(
                patientToEdit.getName(), patientToEdit.getPhone(),
                patientToEdit.getNric(),
                patientToEdit.getRemark(), currentConditions);

        model.setPatient(patientToEdit, editedPatient);
        model.updateFilteredPatientList(PREDICATE_SHOW_ALL_PATIENTS);

        return new CommandResult(String.format(MESSAGE_DELETE_CONDITION_SUCCESS, medicalCondition.tagName));

    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteMedicalConditionCommand)) {
            return false;
        }

        DeleteMedicalConditionCommand command = (DeleteMedicalConditionCommand) other;
        return index.equals(command.index)
                && medicalCondition.equals(command.medicalCondition);
    }


}
