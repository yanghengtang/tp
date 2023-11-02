package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_2;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showAppointmentAtIndex;
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
import seedu.address.model.appointment.Appointment;
import seedu.address.model.remark.Remark;
import seedu.address.testutil.AppointmentBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for AppointmentRemarkCommand.
 */
public class AppointmentRemarkCommandTest {

    private static final String REMARK_STUB = "Some remark";

    private Model model = new ModelManager(getTypicalDatabase(), new UserPrefs());

    @Test
    public void execute_addRemarkUnfilteredList_success() {
        Appointment firstAppointment =
                model.getFilteredAppointmentList().get(INDEX_FIRST_PERSON.getZeroBased());
        Appointment editedAppointment =
                new AppointmentBuilder(firstAppointment)
                .withRemark(REMARK_STUB).build();

        AppointmentRemarkCommand appointmentRemarkCommand =
                new AppointmentRemarkCommand(INDEX_FIRST_PERSON,
                        new Remark(editedAppointment.getRemark().remark));

        String expectedMessage =
                String.format(AppointmentRemarkCommand.MESSAGE_ADD_REMARK_SUCCESS,
                        INDEX_FIRST_PERSON.getZeroBased());

        Model expectedModel = new ModelManager(new Database(model.getDatabase()), new UserPrefs());

        try {
            expectedModel.setAppointment(firstAppointment, editedAppointment);
        } catch (CommandException e) {
            throw new AssertionError(e.getMessage());
        }

        assertCommandSuccess(appointmentRemarkCommand, model, expectedMessage, expectedModel);
        assertEquals(REMARK_STUB,
                model.getFilteredAppointmentList().get(INDEX_FIRST_PERSON.getZeroBased())
                        .getRemark()
                        .remark);
    }

    @Test
    public void execute_deleteRemarkUnfilteredList_success() {
        Appointment firstAppointment = model.getFilteredAppointmentList().get(INDEX_FIRST_PERSON.getZeroBased());
        Appointment editedAppointment = new AppointmentBuilder(firstAppointment).withRemark("").build();

        AppointmentRemarkCommand remarkCommand = new AppointmentRemarkCommand(INDEX_FIRST_PERSON,
                new Remark(editedAppointment.getRemark().toString()));

        String expectedMessage = String.format(AppointmentRemarkCommand.MESSAGE_DELETE_REMARK_SUCCESS,
                INDEX_FIRST_PERSON.getZeroBased());

        Model expectedModel = new ModelManager(new Database(model.getDatabase()), new UserPrefs());

        try {
            expectedModel.setAppointment(firstAppointment, editedAppointment);
        } catch (CommandException e) {
            throw new AssertionError(e.getMessage());
        }

        assertCommandSuccess(remarkCommand, model, expectedMessage, expectedModel);
        assertEquals("",
                model.getFilteredAppointmentList().get(INDEX_FIRST_PERSON.getZeroBased())
                        .getRemark()
                        .remark);
    }

    @Test
    public void execute_filteredList_success() {
        showAppointmentAtIndex(model, INDEX_FIRST_PERSON);

        Appointment firstAppointment = model.getFilteredAppointmentList().get(INDEX_FIRST_PERSON.getZeroBased());
        Appointment editedAppointment = new AppointmentBuilder(model.getFilteredAppointmentList()
                .get(INDEX_FIRST_PERSON.getZeroBased()))
                .withRemark(REMARK_STUB).build();

        AppointmentRemarkCommand appointmentRemarkCommand =
                new AppointmentRemarkCommand(INDEX_FIRST_PERSON,
                        new Remark(editedAppointment.getRemark().remark));

        String expectedMessage = String.format(AppointmentRemarkCommand.MESSAGE_ADD_REMARK_SUCCESS,
                INDEX_FIRST_PERSON.getZeroBased());

        Model expectedModel = new ModelManager(new Database(model.getDatabase()), new UserPrefs());

        try {
            expectedModel.setAppointment(firstAppointment, editedAppointment);
        } catch (CommandException e) {
            throw new AssertionError(e.getMessage());
        }

        assertCommandSuccess(appointmentRemarkCommand, model, expectedMessage, expectedModel);
        assertEquals(REMARK_STUB,
                model.getFilteredAppointmentList().get(INDEX_FIRST_PERSON.getZeroBased())
                        .getRemark()
                        .remark);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredAppointmentList().size() + 1);
        AppointmentRemarkCommand remarkCommand =
                new AppointmentRemarkCommand(outOfBoundIndex, new Remark(REMARK_STUB));

        assertCommandFailure(remarkCommand, model, Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of database
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showAppointmentAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of database list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getDatabase().getAppointmentList().size());

        AppointmentRemarkCommand remarkCommand =
                new AppointmentRemarkCommand(outOfBoundIndex, new Remark(REMARK_STUB));
        assertCommandFailure(remarkCommand, model, Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final AppointmentRemarkCommand firstCommand = new AppointmentRemarkCommand(INDEX_FIRST_PERSON,
                new Remark(VALID_REMARK_1));
        // same values -> returns true
        AppointmentRemarkCommand commandWithSameValues = new AppointmentRemarkCommand(INDEX_FIRST_PERSON,
                new Remark(VALID_REMARK_1));
        assertTrue(firstCommand.equals(commandWithSameValues));
        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));
        // null -> returns false
        assertFalse(firstCommand.equals(null));
        // different types -> returns false
        assertFalse(firstCommand.equals(new ListAppointmentCommand()));
        // different index -> returns false
        assertFalse(firstCommand.equals(new AppointmentRemarkCommand(INDEX_SECOND_PERSON,
                new Remark(VALID_REMARK_1))));
        // different remark -> returns false
        assertFalse(firstCommand.equals(new AppointmentRemarkCommand(INDEX_FIRST_PERSON,
                new Remark(VALID_REMARK_2))));
    }

    @Test
    public void hashCodeMethod() {
        Index targetIndex = Index.fromOneBased(1);
        AppointmentRemarkCommand command =
                new AppointmentRemarkCommand(targetIndex,
                        new Remark(""));

        // same value -> returns same hashcode
        assertEquals(command.hashCode(), Objects.hash(targetIndex,
                new Remark("")));
    }
}
