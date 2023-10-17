package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.NewCommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.NewCommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.NewCommandTestUtil.showDoctorAtIndex;
import static seedu.address.testutil.TypicalDoctor.getTypicalDatabase;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_DOCTOR;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_DOCTOR;

import java.util.Objects;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.NewModel;
import seedu.address.model.NewModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.doctor.Doctor;


/**
 * Contains integration tests (interaction with the NewModel) and unit tests for
 * {@code DeleteDoctorCommand}.
 */
public class DeleteDoctorCommandTest {

    private NewModel model = new NewModelManager(getTypicalDatabase(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Doctor personToDelete = model.getFilteredDoctorList().get(INDEX_FIRST_DOCTOR.getZeroBased());
        DeleteDoctorCommand deleteCommand = new DeleteDoctorCommand(INDEX_FIRST_DOCTOR);

        String expectedMessage = String.format(DeleteDoctorCommand.MESSAGE_DELETE_DOCTOR_SUCCESS,
                Messages.format(personToDelete));

        NewModelManager expectedNewModel = new NewModelManager(model.getDatabase(), new UserPrefs());
        expectedNewModel.deleteDoctor(personToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedNewModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredDoctorList().size() + 1);
        DeleteDoctorCommand deleteCommand = new DeleteDoctorCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_DOCTOR_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showDoctorAtIndex(model, INDEX_FIRST_DOCTOR);

        Doctor personToDelete = model.getFilteredDoctorList().get(INDEX_FIRST_DOCTOR.getZeroBased());
        DeleteDoctorCommand deleteCommand = new DeleteDoctorCommand(INDEX_FIRST_DOCTOR);

        String expectedMessage = String.format(DeleteDoctorCommand.MESSAGE_DELETE_DOCTOR_SUCCESS,
                Messages.format(personToDelete));

        NewModel expectedNewModel = new NewModelManager(model.getDatabase(), new UserPrefs());
        expectedNewModel.deleteDoctor(personToDelete);
        showNoDoctor(expectedNewModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedNewModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showDoctorAtIndex(model, INDEX_FIRST_DOCTOR);

        Index outOfBoundIndex = INDEX_SECOND_DOCTOR;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getDatabase().getDoctorList().size());

        DeleteDoctorCommand deleteCommand = new DeleteDoctorCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_DOCTOR_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteDoctorCommand deleteFirstCommand = new DeleteDoctorCommand(INDEX_FIRST_DOCTOR);
        DeleteDoctorCommand deleteSecondCommand = new DeleteDoctorCommand(INDEX_SECOND_DOCTOR);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteDoctorCommand deleteFirstCommandCopy = new DeleteDoctorCommand(INDEX_FIRST_DOCTOR);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    @Test
    public void hashcode() {
        Index targetIndex = Index.fromOneBased(1);
        DeleteDoctorCommand deleteCommand = new DeleteDoctorCommand(targetIndex);

        // same value -> returns same hashcode
        assertEquals(deleteCommand.hashCode(), Objects.hash(targetIndex));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        DeleteDoctorCommand deleteCommand = new DeleteDoctorCommand(targetIndex);
        String expected = DeleteDoctorCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertEquals(expected, deleteCommand.toString());
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoDoctor(NewModel model) {
        model.updateFilteredDoctorList(p -> false);

        assertTrue(model.getFilteredDoctorList().isEmpty());
    }
}
