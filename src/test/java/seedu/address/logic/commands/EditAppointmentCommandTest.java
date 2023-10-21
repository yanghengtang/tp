package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_APPOINTMENT_1;
import static seedu.address.logic.commands.CommandTestUtil.DESC_APPOINTMENT_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_END_TIME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_START_TIME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DOCTOR_NRIC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PATIENT_NRIC;
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
import seedu.address.logic.commands.EditAppointmentCommand.EditAppointmentDescriptor;
import seedu.address.model.Database;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.appointment.Appointment;
import seedu.address.testutil.AppointmentBuilder;
import seedu.address.testutil.EditAppointmentDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditAppointmentCommand.
 */
public class EditAppointmentCommandTest {

    private Model model = new ModelManager(getTypicalDatabase(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Appointment editedAppointment = new AppointmentBuilder().build();
        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder(editedAppointment).build();
        EditAppointmentCommand editAppointmentCommand = new EditAppointmentCommand(INDEX_FIRST_PERSON, descriptor);

        String expectedMessage = String.format(EditAppointmentCommand.MESSAGE_EDIT_APPOINTMENT_SUCCESS,
                Messages.format(editedAppointment));

        Model expectedModel = new ModelManager(new Database(model.getDatabase()), new UserPrefs());
        expectedModel.setAppointment(model.getFilteredAppointmentList().get(0), editedAppointment);

        assertCommandSuccess(editAppointmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastAppointment = Index.fromOneBased(model.getFilteredAppointmentList().size());
        Appointment lastAppointment = model.getFilteredAppointmentList().get(indexLastAppointment.getZeroBased());

        AppointmentBuilder appointmentInList = new AppointmentBuilder(lastAppointment);
        Appointment editedAppointment = appointmentInList
                .withPatientNric(VALID_PATIENT_NRIC)
                .withDoctorNric(VALID_DOCTOR_NRIC)
                .withStartTime(VALID_APPOINTMENT_START_TIME)
                .withEndTime(VALID_APPOINTMENT_END_TIME)
                .build();

        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder()
                .withPatientNric(VALID_PATIENT_NRIC)
                .withDoctorNric(VALID_DOCTOR_NRIC)
                .withStartTime(VALID_APPOINTMENT_START_TIME)
                .withEndTime(VALID_APPOINTMENT_END_TIME)
                .build();
        EditAppointmentCommand editAppointmentCommand = new EditAppointmentCommand(indexLastAppointment, descriptor);

        String expectedMessage = String.format(EditAppointmentCommand.MESSAGE_EDIT_APPOINTMENT_SUCCESS,
                Messages.format(editedAppointment));

        Model expectedModel = new ModelManager(new Database(model.getDatabase()), new UserPrefs());
        expectedModel.setAppointment(lastAppointment, editedAppointment);

        assertCommandSuccess(editAppointmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditAppointmentCommand editAppointmentCommand =
                new EditAppointmentCommand(INDEX_FIRST_PERSON, new EditAppointmentDescriptor());
        Appointment editedAppointment = model.getFilteredAppointmentList().get(INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(EditAppointmentCommand.MESSAGE_EDIT_APPOINTMENT_SUCCESS,
                Messages.format(editedAppointment));

        Model expectedModel = new ModelManager(new Database(model.getDatabase()), new UserPrefs());

        assertCommandSuccess(editAppointmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showAppointmentAtIndex(model, INDEX_FIRST_PERSON);

        Appointment appointmentInFilteredList =
                model.getFilteredAppointmentList().get(INDEX_FIRST_PERSON.getZeroBased());
        Appointment editedAppointment = new AppointmentBuilder(appointmentInFilteredList)
                .withPatientNric(VALID_PATIENT_NRIC)
                .build();
        EditAppointmentCommand editAppointmentCommand = new EditAppointmentCommand(INDEX_FIRST_PERSON,
                new EditAppointmentDescriptorBuilder().withPatientNric(VALID_PATIENT_NRIC).build());

        String expectedMessage = String.format(EditAppointmentCommand.MESSAGE_EDIT_APPOINTMENT_SUCCESS,
                Messages.format(editedAppointment));

        Model expectedModel = new ModelManager(new Database(model.getDatabase()), new UserPrefs());
        expectedModel.setAppointment(model.getFilteredAppointmentList().get(0), editedAppointment);

        assertCommandSuccess(editAppointmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateAppointmentUnfilteredList_failure() {
        Appointment firstPerson = model.getFilteredAppointmentList().get(INDEX_FIRST_PERSON.getZeroBased());
        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder(firstPerson).build();
        EditAppointmentCommand editAppointmentCommand = new EditAppointmentCommand(INDEX_SECOND_PERSON, descriptor);

        assertCommandFailure(editAppointmentCommand, model, EditAppointmentCommand.MESSAGE_DUPLICATE_APPOINTMENT);
    }

    @Test
    public void execute_duplicateAppointmentFilteredList_failure() {
        showAppointmentAtIndex(model, INDEX_FIRST_PERSON);

        // edit appointment in filtered list into a duplicate in database
        Appointment appointmentInList =
                model.getDatabase().getAppointmentList().get(INDEX_SECOND_PERSON.getZeroBased());
        EditAppointmentCommand editAppointmentCommand = new EditAppointmentCommand(INDEX_FIRST_PERSON,
                new EditAppointmentDescriptorBuilder(appointmentInList).build());

        assertCommandFailure(editAppointmentCommand, model, EditAppointmentCommand.MESSAGE_DUPLICATE_APPOINTMENT);
    }

    @Test
    public void execute_invalidAppointmentIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPatientList().size() + 1);
        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder()
                .withPatientNric(VALID_PATIENT_NRIC)
                .build();
        EditAppointmentCommand editAppointmentCommand = new EditAppointmentCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editAppointmentCommand, model, Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of database
     */
    @Test
    public void execute_invalidAppointmentIndexFilteredList_failure() {
        showAppointmentAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of database list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getDatabase().getAppointmentList().size());

        EditAppointmentCommand editAppointmentCommand = new EditAppointmentCommand(outOfBoundIndex,
                new EditAppointmentDescriptorBuilder().withPatientNric(VALID_PATIENT_NRIC).build());

        assertCommandFailure(editAppointmentCommand, model, Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditAppointmentCommand standardCommand =
                new EditAppointmentCommand(INDEX_FIRST_PERSON, DESC_APPOINTMENT_1);

        // same values -> returns true
        EditAppointmentDescriptor copyDescriptor = new EditAppointmentDescriptor(DESC_APPOINTMENT_1);
        EditAppointmentCommand commandWithSameValues =
                new EditAppointmentCommand(INDEX_FIRST_PERSON, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ListAppointmentCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditAppointmentCommand(INDEX_SECOND_PERSON, DESC_APPOINTMENT_1)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditAppointmentCommand(INDEX_FIRST_PERSON, DESC_APPOINTMENT_2)));
    }

    @Test
    public void hashCodeMethod() {
        Index index = Index.fromOneBased(1);
        EditAppointmentDescriptor editAppointmentDescriptor = new EditAppointmentDescriptor();
        EditAppointmentCommand editAppointmentCommand = new EditAppointmentCommand(index, editAppointmentDescriptor);
        assertEquals(editAppointmentCommand.hashCode(), Objects.hash(index, editAppointmentDescriptor));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        EditAppointmentDescriptor editAppointmentDescriptor = new EditAppointmentDescriptor();
        EditAppointmentCommand editAppointmentCommand = new EditAppointmentCommand(index, editAppointmentDescriptor);
        String expected =
                EditAppointmentCommand.class.getCanonicalName() + "{index=" + index + ", editAppointmentDescriptor="
                        + editAppointmentDescriptor + "}";
        assertEquals(expected, editAppointmentCommand.toString());
    }
}
