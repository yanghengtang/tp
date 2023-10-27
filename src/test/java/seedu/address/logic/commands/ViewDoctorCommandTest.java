package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showDoctorAtIndex;
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
import seedu.address.model.person.doctor.Doctor;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code ViewDoctorCommand}.
 */
public class ViewDoctorCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalDatabase(), new UserPrefs());
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Doctor doctorToView = model.getFilteredDoctorList().get(INDEX_FIRST_PERSON.getZeroBased());
        ViewDoctorCommand viewDoctorCommand = new ViewDoctorCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(ViewDoctorCommand.MESSAGE_VIEW_DOCTOR_SUCCESS,
                Messages.format(doctorToView));

        ModelManager expectedModel = new ModelManager(model.getDatabase(), new UserPrefs());
        expectedModel.updateSelectedDoctor(doctorToView);

        assertCommandSuccess(viewDoctorCommand, model, expectedMessage,
                false,
                true,
                false,
                expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredDoctorList().size() + 1);
        ViewDoctorCommand viewDoctorCommand = new ViewDoctorCommand(outOfBoundIndex);

        assertCommandFailure(viewDoctorCommand, model, Messages.MESSAGE_INVALID_DOCTOR_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showDoctorAtIndex(model, INDEX_FIRST_PERSON);

        Doctor doctorToView = model.getFilteredDoctorList().get(INDEX_FIRST_PERSON.getZeroBased());
        ViewDoctorCommand viewDoctorCommand = new ViewDoctorCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(ViewDoctorCommand.MESSAGE_VIEW_DOCTOR_SUCCESS,
                Messages.format(doctorToView));

        Model expectedModel = new ModelManager(model.getDatabase(), new UserPrefs());
        showDoctorAtIndex(expectedModel, INDEX_FIRST_PERSON);
        expectedModel.updateSelectedDoctor(doctorToView);
        assertCommandSuccess(viewDoctorCommand, model, expectedMessage,
                false,
                true,
                false,
                expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showDoctorAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of database list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getDatabase().getDoctorList().size());

        ViewDoctorCommand viewDoctorCommand = new ViewDoctorCommand(outOfBoundIndex);

        assertCommandFailure(viewDoctorCommand, model, Messages.MESSAGE_INVALID_DOCTOR_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        ViewDoctorCommand viewFirstDoctorCommand = new ViewDoctorCommand(INDEX_FIRST_PERSON);
        ViewDoctorCommand viewSecondDoctorCommand = new ViewDoctorCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(viewFirstDoctorCommand.equals(viewFirstDoctorCommand));

        // same values -> returns true
        ViewDoctorCommand viewFirstDoctorCommandCopy = new ViewDoctorCommand(INDEX_FIRST_PERSON);
        assertTrue(viewFirstDoctorCommand.equals(viewFirstDoctorCommandCopy));

        // different types -> returns false
        assertFalse(viewFirstDoctorCommand.equals(1));

        // null -> returns false
        assertFalse(viewFirstDoctorCommand.equals(null));

        // different index -> returns false
        assertFalse(viewFirstDoctorCommand.equals(viewSecondDoctorCommand));
    }

    @Test
    public void hashCodeMethod() {
        Index targetIndex = Index.fromOneBased(1);
        ViewDoctorCommand viewCommand = new ViewDoctorCommand(targetIndex);

        // same value -> returns same hashcode
        assertEquals(viewCommand.hashCode(), Objects.hash(targetIndex));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        ViewDoctorCommand viewDoctorCommand = new ViewDoctorCommand(targetIndex);
        String expected = ViewDoctorCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertEquals(expected, viewDoctorCommand.toString());
    }
}
