package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPatientAtIndex;
import static seedu.address.testutil.TypicalDatabase.getTypicalDatabase;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import java.util.Objects;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.patient.Patient;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code ViewPatientCommandTest}.
 */
public class ViewPatientCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalDatabase(), new UserPrefs());
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Patient patientToView = model.getFilteredPatientList().get(INDEX_FIRST_PERSON.getZeroBased());
        ViewPatientCommand viewPatientCommand = new ViewPatientCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(ViewPatientCommand.MESSAGE_VIEW_PATIENT_SUCCESS,
                Messages.format(patientToView));

        ModelManager expectedModel = new ModelManager(model.getDatabase(), new UserPrefs());
        expectedModel.updateSelectedPatient(patientToView);

        assertCommandSuccess(viewPatientCommand, model, expectedMessage,
                false,
                false,
                true,
                expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPatientList().size() + 1);
        ViewPatientCommand viewPatientCommand = new ViewPatientCommand(outOfBoundIndex);

        assertCommandFailure(viewPatientCommand, model, Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPatientAtIndex(model, INDEX_FIRST_PERSON);

        Patient patientToView = model.getFilteredPatientList().get(INDEX_FIRST_PERSON.getZeroBased());
        ViewPatientCommand viewPatientCommand = new ViewPatientCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(ViewPatientCommand.MESSAGE_VIEW_PATIENT_SUCCESS,
                Messages.format(patientToView));

        Model expectedModel = new ModelManager(model.getDatabase(), new UserPrefs());
        showPatientAtIndex(expectedModel, INDEX_FIRST_PERSON);
        expectedModel.updateSelectedPatient(patientToView);
        assertCommandSuccess(viewPatientCommand, model, expectedMessage,
                false,
                false,
                true,
                expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPatientAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of database list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getDatabase().getPatientList().size());

        ViewPatientCommand viewPatientCommand = new ViewPatientCommand(outOfBoundIndex);

        assertCommandFailure(viewPatientCommand, model, Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        ViewPatientCommand viewFirstPatientCommand = new ViewPatientCommand(INDEX_FIRST_PERSON);
        ViewPatientCommand viewSecondPatientCommand = new ViewPatientCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(viewFirstPatientCommand.equals(viewFirstPatientCommand));

        // same values -> returns true
        ViewPatientCommand viewFirstPatientCommandCopy = new ViewPatientCommand(INDEX_FIRST_PERSON);
        assertTrue(viewFirstPatientCommand.equals(viewFirstPatientCommandCopy));

        // different types -> returns false
        assertFalse(viewFirstPatientCommand.equals(1));

        // null -> returns false
        assertFalse(viewFirstPatientCommand.equals(null));

        // different index -> returns false
        assertFalse(viewFirstPatientCommand.equals(viewSecondPatientCommand));
    }

    @Test
    public void hashCodeMethod() {
        Index targetIndex = Index.fromOneBased(1);
        ViewPatientCommand viewPatientCommand = new ViewPatientCommand(targetIndex);

        // same value -> returns same hashcode
        assertEquals(viewPatientCommand.hashCode(), Objects.hash(targetIndex));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        ViewPatientCommand viewPatientCommand = new ViewPatientCommand(targetIndex);
        String expected = ViewPatientCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertEquals(expected, viewPatientCommand.toString());
    }
}
