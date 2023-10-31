package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRESCRIPTION_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRESCRIPTION_2;
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
import seedu.address.model.tag.Tag;
import seedu.address.testutil.AppointmentBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for AddPrescriptionCommand.
 */
public class AddPrescriptionCommandTest {

    private static final Tag PRESCRIPTION_STUB = new Tag("SomePrescription");

    private Model model = new ModelManager(getTypicalDatabase(), new UserPrefs());

    @Test
    public void execute_addPrescriptionUnfilteredList_success() {
        Appointment firstAppointment =
                model.getFilteredAppointmentList().get(INDEX_FIRST_PERSON.getZeroBased());
        Appointment editedAppointment =
                new AppointmentBuilder(firstAppointment)
                        .withTags(PRESCRIPTION_STUB).build();

        AddPrescriptionCommand addPrescriptionCommand =
                new AddPrescriptionCommand(INDEX_FIRST_PERSON,
                        PRESCRIPTION_STUB);

        String expectedMessage =
                String.format(AddPrescriptionCommand.MESSAGE_ADD_PRESCRIPTION_SUCCESS, editedAppointment);

        Model expectedModel = new ModelManager(new Database(model.getDatabase()), new UserPrefs());

        try {
            expectedModel.setAppointment(firstAppointment, editedAppointment);
        } catch (CommandException e) {
            throw new AssertionError(e.getMessage());
        }

        assertCommandSuccess(addPrescriptionCommand, model, expectedMessage, expectedModel);
    }


    @Test
    public void execute_filteredList_success() {
        showAppointmentAtIndex(model, INDEX_FIRST_PERSON);

        Appointment firstAppointment = model.getFilteredAppointmentList().get(INDEX_FIRST_PERSON.getZeroBased());
        Appointment editedAppointment = new AppointmentBuilder(model.getFilteredAppointmentList()
                .get(INDEX_FIRST_PERSON.getZeroBased()))
                .withTags(PRESCRIPTION_STUB).build();

        AddPrescriptionCommand addPrescriptionCommand =
                new AddPrescriptionCommand(INDEX_FIRST_PERSON,
                        PRESCRIPTION_STUB);

        String expectedMessage = String.format(AddPrescriptionCommand.MESSAGE_ADD_PRESCRIPTION_SUCCESS,
                editedAppointment);

        Model expectedModel = new ModelManager(new Database(model.getDatabase()), new UserPrefs());

        try {
            expectedModel.setAppointment(firstAppointment, editedAppointment);
        } catch (CommandException e) {
            throw new AssertionError(e.getMessage());
        }

        assertCommandSuccess(addPrescriptionCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredAppointmentList().size() + 1);
        AddPrescriptionCommand command =
                new AddPrescriptionCommand(outOfBoundIndex, PRESCRIPTION_STUB);

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

        AddPrescriptionCommand command =
                new AddPrescriptionCommand(outOfBoundIndex, PRESCRIPTION_STUB);
        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of database
     */
    @Test
    public void execute_duplicateTag_failure() {
        Appointment firstAppointment =
                model.getFilteredAppointmentList().get(INDEX_FIRST_PERSON.getZeroBased());
        Appointment editedAppointment =
                new AppointmentBuilder(firstAppointment)
                        .withTags(PRESCRIPTION_STUB).build();

        AddPrescriptionCommand command =
                new AddPrescriptionCommand(INDEX_FIRST_PERSON,
                        PRESCRIPTION_STUB);
        try {
            command.execute(model);
        } catch (CommandException e) {
            throw new AssertionError(e.getMessage());
        }

        AddPrescriptionCommand duplicateCommand =
                new AddPrescriptionCommand(INDEX_FIRST_PERSON,
                        PRESCRIPTION_STUB);
        String expectedMessage =
                String.format(AddPrescriptionCommand.MESSAGE_ADD_PRESCRIPTION_FAILURE, PRESCRIPTION_STUB);
        assertCommandFailure(duplicateCommand, model, expectedMessage);
    }

    @Test
    public void equals() {
        final AddPrescriptionCommand firstCommand = new AddPrescriptionCommand(INDEX_FIRST_PERSON,
                new Tag(VALID_PRESCRIPTION_1));
        // same values -> returns true
        AddPrescriptionCommand commandWithSameValues = new AddPrescriptionCommand(INDEX_FIRST_PERSON,
                new Tag(VALID_PRESCRIPTION_1));
        assertTrue(firstCommand.equals(commandWithSameValues));
        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));
        // null -> returns false
        assertFalse(firstCommand.equals(null));
        // different types -> returns false
        assertFalse(firstCommand.equals(new ListAppointmentCommand()));
        // different index -> returns false
        assertFalse(firstCommand.equals(new AddPrescriptionCommand(INDEX_SECOND_PERSON,
                new Tag(VALID_PRESCRIPTION_1))));
        // different prescription -> returns false
        assertFalse(firstCommand.equals(new AddPrescriptionCommand(INDEX_FIRST_PERSON,
                new Tag(VALID_PRESCRIPTION_2))));
    }

    @Test
    public void hashCodeMethod() {
        Index targetIndex = Index.fromOneBased(1);
        AddPrescriptionCommand command =
                new AddPrescriptionCommand(targetIndex,
                        PRESCRIPTION_STUB);

        // same value -> returns same hashcode
        assertEquals(command.hashCode(), Objects.hash(targetIndex,
                PRESCRIPTION_STUB));
    }
}
