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
 * Contains integration tests (interaction with the Model) and unit tests for DeleteMedicalConditionCommand.
 */
public class DeleteMedicalConditionCommandTest {

    private static final Tag TAG_STUB = new Tag("example");

    private Model model = new ModelManager(getTypicalDatabase(), new UserPrefs());


    @Test
    public void execute_deleteTagUnfilteredList_success() {
        Patient firstPatient =
                model.getFilteredPatientList().get(INDEX_FIRST_PERSON.getZeroBased());

        Patient editedPatient =
                new PatientBuilder(firstPatient)
                        .withTags(DEPRESSION_TAG).build();

        DeleteMedicalConditionCommand deletePatientTagCommand =
                new DeleteMedicalConditionCommand(INDEX_FIRST_PERSON,
                        DEPRESSION_TAG);

        String expectedMessage =
                String.format(DeleteMedicalConditionCommand.MESSAGE_DELETE_CONDITION_SUCCESS, DEPRESSION_TAG.tagName);

        Model expectedModel = new ModelManager(new Database(model.getDatabase()), new UserPrefs());

        try {
            expectedModel.setPatient(firstPatient, editedPatient);
        } catch (CommandException e) {
            throw new AssertionError(e.getMessage());
        }

        assertCommandSuccess(deletePatientTagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPatientAtIndex(model, INDEX_FIRST_PERSON);

        Patient firstPatient = model.getFilteredPatientList().get(INDEX_FIRST_PERSON.getZeroBased());
        Patient editedPatient = new PatientBuilder(model.getFilteredPatientList()
                .get(INDEX_FIRST_PERSON.getZeroBased()))
                .withTags(DEPRESSION_TAG).build();

        DeleteMedicalConditionCommand deletePatientTagCommand =
                new DeleteMedicalConditionCommand(INDEX_FIRST_PERSON,
                        DEPRESSION_TAG);

        String expectedMessage = String.format(DeleteMedicalConditionCommand.MESSAGE_DELETE_CONDITION_SUCCESS,
                DEPRESSION_TAG.tagName);

        Model expectedModel = new ModelManager(new Database(model.getDatabase()), new UserPrefs());

        try {
            expectedModel.setPatient(firstPatient, editedPatient);
        } catch (CommandException e) {
            throw new AssertionError(e.getMessage());
        }

        assertCommandSuccess(deletePatientTagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPatientList().size() + 1);
        DeleteMedicalConditionCommand command =
                new DeleteMedicalConditionCommand(outOfBoundIndex, DEPRESSION_TAG);

        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of database
     */
    @Test
    public void execute_invalidIndexFilteredList_failure() {
        showPatientAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of database list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getDatabase().getPatientList().size());

        DeleteMedicalConditionCommand command =
                new DeleteMedicalConditionCommand(outOfBoundIndex, DEPRESSION_TAG);
        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
    }

    /**
     * Edit patient where condition is not already present
     */
    @Test
    public void execute_missingTag_failure() {

        DeleteMedicalConditionCommand command =
                new DeleteMedicalConditionCommand(INDEX_FIRST_PERSON,
                        TAG_STUB);
        String expectedMessage =
                String.format(DeleteMedicalConditionCommand.MESSAGE_DELETE_CONDITION_FAILURE, TAG_STUB.tagName);
        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void equals() {
        DeleteMedicalConditionCommand firstCommand = new DeleteMedicalConditionCommand(INDEX_FIRST_PERSON,
                new Tag(VALID_PATIENT_TAG_1));
        // same values -> returns true
        DeleteMedicalConditionCommand commandWithSameValues = new DeleteMedicalConditionCommand(INDEX_FIRST_PERSON,
                new Tag(VALID_PATIENT_TAG_1));
        assertTrue(firstCommand.equals(commandWithSameValues));
        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));
        // null -> returns false
        assertFalse(firstCommand.equals(null));
        // different types -> returns false
        assertFalse(firstCommand.equals(new ListAppointmentCommand()));
        // different index -> returns false
        assertFalse(firstCommand.equals(new DeleteMedicalConditionCommand(INDEX_SECOND_PERSON,
                new Tag(VALID_PATIENT_TAG_1))));
        // different tag -> returns false
        assertFalse(firstCommand.equals(new DeleteMedicalConditionCommand(INDEX_FIRST_PERSON,
                new Tag(VALID_PATIENT_TAG_2))));
    }

    @Test
    public void hashCodeMethod() {
        Index targetIndex = Index.fromOneBased(1);
        DeleteMedicalConditionCommand command =
                new DeleteMedicalConditionCommand(targetIndex,
                        DEPRESSION_TAG);

        // same value -> returns same hashcode
        assertEquals(command.hashCode(), Objects.hash(targetIndex,
                DEPRESSION_TAG));
    }
}
