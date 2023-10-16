package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertNewCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertNewCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPatientAtIndex;
import static seedu.address.testutil.TypicalDatabase.getTypicalDatabase;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.NewModel;
import seedu.address.model.NewModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.patient.Patient;

/**
 * Contains integration tests (interaction with the NewModel) and unit tests for
 * {@code DeletePatientCommand}.
 */
public class DeletePatientCommandTest {

    private NewModel model = new NewModelManager(getTypicalDatabase(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Patient patientToDelete = model.getFilteredPatientList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeletePatientCommand deletePatientCommand = new DeletePatientCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeletePatientCommand.MESSAGE_DELETE_PATIENT_SUCCESS,
                Messages.format(patientToDelete));

        NewModelManager expectedModel = new NewModelManager(model.getDatabase(), new UserPrefs());
        expectedModel.deletePatient(patientToDelete);

        assertNewCommandSuccess(deletePatientCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPatientList().size() + 1);
        DeletePatientCommand deletePatientCommand = new DeletePatientCommand(outOfBoundIndex);

        assertNewCommandFailure(deletePatientCommand, model, Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPatientAtIndex(model, INDEX_FIRST_PERSON);

        Patient patientToDelete = model.getFilteredPatientList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeletePatientCommand deletePatientCommand = new DeletePatientCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeletePatientCommand.MESSAGE_DELETE_PATIENT_SUCCESS,
                Messages.format(patientToDelete));

        NewModel expectedModel = new NewModelManager(model.getDatabase(), new UserPrefs());
        expectedModel.deletePatient(patientToDelete);
        showNoPatient(expectedModel);

        assertNewCommandSuccess(deletePatientCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPatientAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getDatabase().getPatientList().size());

        DeletePatientCommand deletePatientCommand = new DeletePatientCommand(outOfBoundIndex);

        assertNewCommandFailure(deletePatientCommand, model, Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeletePatientCommand deleteFirstPatientCommand = new DeletePatientCommand(INDEX_FIRST_PERSON);
        DeletePatientCommand deleteSecondPatientCommand = new DeletePatientCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(deleteFirstPatientCommand.equals(deleteFirstPatientCommand));

        // same values -> returns true
        DeletePatientCommand deleteFirstPatientCommandCopy = new DeletePatientCommand(INDEX_FIRST_PERSON);
        assertTrue(deleteFirstPatientCommand.equals(deleteFirstPatientCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstPatientCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstPatientCommand.equals(null));

        // different patient -> returns false
        assertFalse(deleteFirstPatientCommand.equals(deleteSecondPatientCommand));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        DeletePatientCommand deletePatientCommand = new DeletePatientCommand(targetIndex);
        String expected = DeletePatientCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertEquals(expected, deletePatientCommand.toString());
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPatient(NewModel model) {
        model.updateFilteredPatientList(p -> false);

        assertTrue(model.getFilteredPatientList().isEmpty());
    }
}
