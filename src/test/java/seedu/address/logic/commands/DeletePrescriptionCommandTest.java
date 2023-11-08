package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showAppointmentAtIndex;
import static seedu.address.model.DataTest.PARACETAMOL_TAG;
import static seedu.address.model.DataTest.PEDIATRICIAN_TAG;
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
import seedu.address.model.tag.Tag;
import seedu.address.testutil.AppointmentBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for DeletePrescriptionCommand.
 */
public class DeletePrescriptionCommandTest {

    private static final Tag PRESCRIPTION_STUB = new Tag("SomePrescription");

    private Model model = new ModelManager(getTypicalDatabase(), new UserPrefs());

    @Test
    public void execute_addPrescriptionUnfilteredList_success() {
        Appointment firstAppointment =
                model.getFilteredAppointmentList().get(INDEX_FIRST_PERSON.getZeroBased());
        Appointment editedAppointment =
                new AppointmentBuilder(firstAppointment)
                        .withTags(PEDIATRICIAN_TAG).build();

        DeletePrescriptionCommand deletePrescriptionCommand =
                new DeletePrescriptionCommand(INDEX_FIRST_PERSON,
                        PARACETAMOL_TAG);

        String expectedMessage =
                String.format(DeletePrescriptionCommand.MESSAGE_DELETE_PRESCRIPTION_SUCCESS, PARACETAMOL_TAG.tagName);

        Model expectedModel = new ModelManager(new Database(model.getDatabase()), new UserPrefs());

        try {
            expectedModel.setAppointment(firstAppointment, editedAppointment);
        } catch (CommandException e) {
            throw new AssertionError(e.getMessage());
        }

        assertCommandSuccess(deletePrescriptionCommand, model, expectedMessage, expectedModel);
    }


    @Test
    public void execute_filteredList_success() {
        showAppointmentAtIndex(model, INDEX_FIRST_PERSON);

        Appointment firstAppointment = model.getFilteredAppointmentList().get(INDEX_FIRST_PERSON.getZeroBased());
        Appointment editedAppointment = new AppointmentBuilder(model.getFilteredAppointmentList()
                .get(INDEX_FIRST_PERSON.getZeroBased()))
                .withTags(PEDIATRICIAN_TAG).build();

        DeletePrescriptionCommand deletePrescriptionCommand =
                new DeletePrescriptionCommand(INDEX_FIRST_PERSON,
                        PARACETAMOL_TAG);

        String expectedMessage = String.format(DeletePrescriptionCommand.MESSAGE_DELETE_PRESCRIPTION_SUCCESS,
                PARACETAMOL_TAG.tagName);

        Model expectedModel = new ModelManager(new Database(model.getDatabase()), new UserPrefs());

        try {
            expectedModel.setAppointment(firstAppointment, editedAppointment);
        } catch (CommandException e) {
            throw new AssertionError(e.getMessage());
        }

        assertCommandSuccess(deletePrescriptionCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredAppointmentList().size() + 1);
        DeletePrescriptionCommand command =
                new DeletePrescriptionCommand(outOfBoundIndex, PARACETAMOL_TAG);

        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of database
     */
    @Test
    public void execute_invalidIndexFilteredList_failure() {
        showAppointmentAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of database list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getDatabase().getAppointmentList().size());

        DeletePrescriptionCommand command =
                new DeletePrescriptionCommand(outOfBoundIndex, PARACETAMOL_TAG);
        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
    }

    /**
     * Edit appointment where tag is not already present
     */
    @Test
    public void execute_missingTag_failure() {

        DeletePrescriptionCommand command =
                new DeletePrescriptionCommand(INDEX_FIRST_PERSON,
                        PRESCRIPTION_STUB);
        String expectedMessage =
                String.format(DeletePrescriptionCommand.MESSAGE_DELETE_PRESCRIPTION_FAILURE, PRESCRIPTION_STUB);
        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void equals() {
        final DeletePrescriptionCommand firstCommand = new DeletePrescriptionCommand(INDEX_FIRST_PERSON,
                PARACETAMOL_TAG);
        // same values -> returns true
        DeletePrescriptionCommand commandWithSameValues = new DeletePrescriptionCommand(INDEX_FIRST_PERSON,
                PARACETAMOL_TAG);
        assertTrue(firstCommand.equals(commandWithSameValues));
        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));
        // null -> returns false
        assertFalse(firstCommand.equals(null));
        // different types -> returns false
        assertFalse(firstCommand.equals(new ListAppointmentCommand()));
        // different index -> returns false
        assertFalse(firstCommand.equals(new DeletePrescriptionCommand(INDEX_SECOND_PERSON,
               PARACETAMOL_TAG)));
        // different prescription -> returns false
        assertFalse(firstCommand.equals(new DeletePrescriptionCommand(INDEX_FIRST_PERSON,
                PEDIATRICIAN_TAG)));
    }

    @Test
    public void hashCodeMethod() {
        Index targetIndex = Index.fromOneBased(1);
        DeletePrescriptionCommand command =
                new DeletePrescriptionCommand(targetIndex,
                        PRESCRIPTION_STUB);

        // same value -> returns same hashcode
        assertEquals(command.hashCode(), Objects.hash(targetIndex,
                PRESCRIPTION_STUB));
    }
}
