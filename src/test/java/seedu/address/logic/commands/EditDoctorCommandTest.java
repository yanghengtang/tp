package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showDoctorAtIndex;
import static seedu.address.logic.commands.EditDoctorCommand.MESSAGE_EDIT_DOCTOR_SUCCESS;
import static seedu.address.testutil.TypicalDoctor.getTypicalDatabase;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_DOCTOR;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_DOCTOR;

import java.util.Objects;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditDoctorCommand.EditDoctorDescriptor;
import seedu.address.model.Database;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.doctor.Doctor;
import seedu.address.testutil.DoctorBuilder;
import seedu.address.testutil.EditDoctorDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditDoctorCommand.
 */
public class EditDoctorCommandTest {

    private Model model = new ModelManager(getTypicalDatabase(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Doctor editedDoctor = new DoctorBuilder().build();
        EditDoctorDescriptor descriptor = new EditDoctorDescriptorBuilder(editedDoctor).build();
        EditDoctorCommand editCommand = new EditDoctorCommand(INDEX_FIRST_DOCTOR, descriptor);

        String expectedMessage = String.format(MESSAGE_EDIT_DOCTOR_SUCCESS, Messages.format(editedDoctor));

        Model expectedModel = new ModelManager(new Database(model.getDatabase()), new UserPrefs());
        expectedModel.setDoctor(model.getFilteredDoctorList().get(0), editedDoctor);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastDoctor = Index.fromOneBased(model.getFilteredDoctorList().size());
        Doctor lastDoctor = model.getFilteredDoctorList().get(indexLastDoctor.getZeroBased());

        DoctorBuilder doctorInList = new DoctorBuilder(lastDoctor);
        Doctor editedDoctor = doctorInList.withName(VALID_NAME_BOB).withNric(VALID_NRIC_BOB).build();

        EditDoctorDescriptor descriptor = new EditDoctorDescriptorBuilder().withName(VALID_NAME_BOB)
                .withNric(VALID_NRIC_BOB).build();
        EditDoctorCommand editCommand = new EditDoctorCommand(indexLastDoctor, descriptor);

        String expectedMessage = String.format(MESSAGE_EDIT_DOCTOR_SUCCESS, Messages.format(editedDoctor));

        Model expectedModel = new ModelManager(new Database(model.getDatabase()), new UserPrefs());
        expectedModel.setDoctor(lastDoctor, editedDoctor);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditDoctorCommand editCommand = new EditDoctorCommand(INDEX_FIRST_DOCTOR, new EditDoctorDescriptor());
        Doctor editedDoctor = model.getFilteredDoctorList().get(INDEX_FIRST_DOCTOR.getZeroBased());

        String expectedMessage = String.format(MESSAGE_EDIT_DOCTOR_SUCCESS, Messages.format(editedDoctor));

        Model expectedModel = new ModelManager(new Database(model.getDatabase()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showDoctorAtIndex(model, INDEX_FIRST_DOCTOR);

        Doctor doctorInFilteredList = model.getFilteredDoctorList().get(INDEX_FIRST_DOCTOR.getZeroBased());
        Doctor editedDoctor = new DoctorBuilder(doctorInFilteredList).withName(VALID_NAME_BOB).build();
        EditDoctorCommand editCommand = new EditDoctorCommand(INDEX_FIRST_DOCTOR,
                new EditDoctorDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(MESSAGE_EDIT_DOCTOR_SUCCESS, Messages.format(editedDoctor));

        Model expectedModel = new ModelManager(new Database(model.getDatabase()), new UserPrefs());
        expectedModel.setDoctor(model.getFilteredDoctorList().get(0), editedDoctor);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateDoctorUnfilteredList_failure() {
        Doctor firstDoctor = model.getFilteredDoctorList().get(INDEX_FIRST_DOCTOR.getZeroBased());
        EditDoctorDescriptor descriptor = new EditDoctorDescriptorBuilder(firstDoctor).build();
        EditDoctorCommand editCommand = new EditDoctorCommand(INDEX_SECOND_DOCTOR, descriptor);

        assertCommandFailure(editCommand, model, EditDoctorCommand.MESSAGE_DUPLICATE_DOCTOR);
    }

    @Test
    public void execute_duplicateDoctorFilteredList_failure() {
        showDoctorAtIndex(model, INDEX_FIRST_DOCTOR);

        // edit doctor in filtered list into a duplicate in address book
        Doctor doctorInList = model.getDatabase().getDoctorList().get(INDEX_SECOND_DOCTOR.getZeroBased());
        EditDoctorCommand editCommand = new EditDoctorCommand(INDEX_FIRST_DOCTOR,
                new EditDoctorDescriptorBuilder(doctorInList).build());

        assertCommandFailure(editCommand, model, EditDoctorCommand.MESSAGE_DUPLICATE_DOCTOR);
    }

    @Test
    public void execute_invalidDoctorIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredDoctorList().size() + 1);
        EditDoctorDescriptor descriptor = new EditDoctorDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditDoctorCommand editCommand = new EditDoctorCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_DOCTOR_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidDoctorIndexFilteredList_failure() {
        showDoctorAtIndex(model, INDEX_FIRST_DOCTOR);
        Index outOfBoundIndex = INDEX_SECOND_DOCTOR;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getDatabase().getDoctorList().size());

        EditDoctorCommand editCommand = new EditDoctorCommand(outOfBoundIndex,
                new EditDoctorDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_DOCTOR_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditDoctorCommand standardCommand = new EditDoctorCommand(INDEX_FIRST_DOCTOR, DESC_AMY);

        // same values -> returns true
        EditDoctorDescriptor copyDescriptor = new EditDoctorDescriptor(DESC_AMY);
        EditDoctorCommand commandWithSameValues = new EditDoctorCommand(INDEX_FIRST_DOCTOR, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ListDoctorCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditDoctorCommand(INDEX_SECOND_DOCTOR, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditDoctorCommand(INDEX_FIRST_DOCTOR, DESC_BOB)));
    }

    @Test
    public void hashCodeMethod() {
        Index targetIndex = Index.fromOneBased(1);
        EditDoctorDescriptor descriptor = new EditDoctorDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditDoctorCommand editCommand = new EditDoctorCommand(targetIndex, descriptor);

        // same value -> returns same hashcode
        assertEquals(editCommand.hashCode(), Objects.hash(targetIndex, descriptor));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        EditDoctorDescriptor editDoctorDescriptor = new EditDoctorDescriptor();
        EditDoctorCommand editCommand = new EditDoctorCommand(index, editDoctorDescriptor);
        String expected = EditDoctorCommand.class.getCanonicalName() + "{index=" + index + ", editDoctorDescriptor="
                + editDoctorDescriptor + "}";
        assertEquals(expected, editCommand.toString());
    }

}
