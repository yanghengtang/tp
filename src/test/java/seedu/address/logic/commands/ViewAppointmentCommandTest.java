package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showAppointmentAtIndex;
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
import seedu.address.model.appointment.Appointment;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code ViewAppointmentCommand}.
 */
public class ViewAppointmentCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalDatabase(), new UserPrefs());
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Appointment appointmentToView = model.getFilteredAppointmentList().get(INDEX_FIRST_PERSON.getZeroBased());
        ViewAppointmentCommand viewAppointmentCommand = new ViewAppointmentCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(ViewAppointmentCommand.MESSAGE_VIEW_APPOINTMENT_SUCCESS,
                Messages.format(appointmentToView));

        ModelManager expectedModel = new ModelManager(model.getDatabase(), new UserPrefs());
        expectedModel.updateSelectedAppointment(appointmentToView);

        assertCommandSuccess(viewAppointmentCommand, model, expectedMessage,
                true,
                false,
                false,
                expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPatientList().size() + 1);
        ViewAppointmentCommand viewPatientCommand = new ViewAppointmentCommand(outOfBoundIndex);

        assertCommandFailure(viewPatientCommand, model, Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showAppointmentAtIndex(model, INDEX_FIRST_PERSON);

        Appointment appointmentToView = model.getFilteredAppointmentList().get(INDEX_FIRST_PERSON.getZeroBased());
        ViewAppointmentCommand viewAppointmentCommand = new ViewAppointmentCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(ViewAppointmentCommand.MESSAGE_VIEW_APPOINTMENT_SUCCESS,
                Messages.format(appointmentToView));

        Model expectedModel = new ModelManager(model.getDatabase(), new UserPrefs());
        showAppointmentAtIndex(expectedModel, INDEX_FIRST_PERSON);
        expectedModel.updateSelectedAppointment(appointmentToView);
        assertCommandSuccess(viewAppointmentCommand, model, expectedMessage,
                true,
                false,
                false,
                expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showAppointmentAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of database list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getDatabase().getAppointmentList().size());

        ViewAppointmentCommand viewAppointmentCommand = new ViewAppointmentCommand(outOfBoundIndex);

        assertCommandFailure(viewAppointmentCommand, model, Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        ViewAppointmentCommand viewFirstAppointmentCommand = new ViewAppointmentCommand(INDEX_FIRST_PERSON);
        ViewAppointmentCommand viewSecondAppointmentCommand = new ViewAppointmentCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(viewFirstAppointmentCommand.equals(viewFirstAppointmentCommand));

        // same values -> returns true
        ViewAppointmentCommand viewFirstAppointmentCommandCopy = new ViewAppointmentCommand(INDEX_FIRST_PERSON);
        assertTrue(viewFirstAppointmentCommand.equals(viewFirstAppointmentCommandCopy));

        // different types -> returns false
        assertFalse(viewFirstAppointmentCommand.equals(1));

        // null -> returns false
        assertFalse(viewFirstAppointmentCommand.equals(null));

        // different index -> returns false
        assertFalse(viewFirstAppointmentCommand.equals(viewSecondAppointmentCommand));
    }

    @Test
    public void hashCodeMethod() {
        Index targetIndex = Index.fromOneBased(1);
        ViewAppointmentCommand viewCommand = new ViewAppointmentCommand(targetIndex);

        // same value -> returns same hashcode
        assertEquals(viewCommand.hashCode(), Objects.hash(targetIndex));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        ViewAppointmentCommand viewAppointmentCommand = new ViewAppointmentCommand(targetIndex);
        String expected = ViewAppointmentCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertEquals(expected, viewAppointmentCommand.toString());
    }
}
