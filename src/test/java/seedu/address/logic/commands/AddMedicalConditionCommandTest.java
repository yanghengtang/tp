package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PATIENT_TAG_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PATIENT_TAG_2;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPatientAtIndex;
import static seedu.address.model.DataTest.DEPRESSION_TAG;
import static seedu.address.testutil.TypicalDatabase.getTypicalDatabase;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import java.util.Objects;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Database;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.patient.Patient;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PatientBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for AddMedicalConditionCommand.
 */
public class AddMedicalConditionCommandTest {

    private static final Tag CONDITION_STUB = new Tag("example");

    private Model model = new ModelManager(getTypicalDatabase(), new UserPrefs());

    @Test
    public void execute_addConditionUnfilteredList_success() {
        Patient firstPatient =
                model.getFilteredPatientList().get(INDEX_FIRST_PERSON.getZeroBased());
        Patient editedPatient =
                new PatientBuilder(firstPatient)
                        .withTags(CONDITION_STUB).build();

        AddMedicalConditionCommand addPatientTagCommand =
                new AddMedicalConditionCommand(INDEX_FIRST_PERSON,
                        CONDITION_STUB);

        String expectedMessage =
                String.format(AddMedicalConditionCommand.MESSAGE_ADD_CONDITION_SUCCESS, CONDITION_STUB.tagName);

        Model expectedModel = new ModelManager(new Database(model.getDatabase()), new UserPrefs());

        try {
            expectedModel.setPatient(firstPatient, editedPatient);
        } catch (CommandException e) {
            throw new AssertionError(e.getMessage());
        }

        assertCommandSuccess(addPatientTagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPatientAtIndex(model, INDEX_FIRST_PERSON);

        Patient firstPatient = model.getFilteredPatientList().get(INDEX_FIRST_PERSON.getZeroBased());
        Patient editedPatient = new PatientBuilder(model.getFilteredPatientList()
                .get(INDEX_FIRST_PERSON.getZeroBased()))
                .withTags(CONDITION_STUB).build();

        AddMedicalConditionCommand addPatientTagCommand =
                new AddMedicalConditionCommand(INDEX_FIRST_PERSON,
                        CONDITION_STUB);

        String expectedMessage = String.format(AddMedicalConditionCommand.MESSAGE_ADD_CONDITION_SUCCESS,
                CONDITION_STUB.tagName);

        Model expectedModel = new ModelManager(new Database(model.getDatabase()), new UserPrefs());

        try {
            expectedModel.setPatient(firstPatient, editedPatient);
        } catch (CommandException e) {
            throw new AssertionError(e.getMessage());
        }

        assertCommandSuccess(addPatientTagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPatientIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPatientList().size() + 1);
        AddMedicalConditionCommand tagCommand =
                new AddMedicalConditionCommand(outOfBoundIndex, CONDITION_STUB);

        assertCommandFailure(tagCommand, model, Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of database
     */
    @Test
    public void execute_invalidPatientIndexFilteredList_failure() {
        showPatientAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of database list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getDatabase().getPatientList().size());

        AddMedicalConditionCommand tagCommand =
                new AddMedicalConditionCommand(outOfBoundIndex, CONDITION_STUB);
        assertCommandFailure(tagCommand, model, Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        AddMedicalConditionCommand firstCommand = new AddMedicalConditionCommand(INDEX_FIRST_PERSON,
                new Tag(VALID_PATIENT_TAG_1));
        // same values -> returns true
        AddMedicalConditionCommand commandWithSameValues = new AddMedicalConditionCommand(INDEX_FIRST_PERSON,
                new Tag(VALID_PATIENT_TAG_1));
        assertTrue(firstCommand.equals(commandWithSameValues));
        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));
        // null -> returns false
        assertFalse(firstCommand.equals(null));
        // different types -> returns false
        assertFalse(firstCommand.equals(new ListAppointmentCommand()));
        // different index -> returns false
        assertFalse(firstCommand.equals(new AddMedicalConditionCommand(INDEX_SECOND_PERSON,
                new Tag(VALID_PATIENT_TAG_1))));
        // different tag -> returns false
        assertFalse(firstCommand.equals(new AddMedicalConditionCommand(INDEX_FIRST_PERSON,
                new Tag(VALID_PATIENT_TAG_2))));
    }

    @Test
    public void hashCodeMethod() {
        Index targetIndex = Index.fromOneBased(1);
        AddMedicalConditionCommand command =
                new AddMedicalConditionCommand(targetIndex,
                        DEPRESSION_TAG);

        // same value -> returns same hashcode
        assertEquals(command.hashCode(), Objects.hash(targetIndex,
                DEPRESSION_TAG));
    }
}
