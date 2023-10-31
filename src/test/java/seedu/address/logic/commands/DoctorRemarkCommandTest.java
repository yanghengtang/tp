package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_3;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_4;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showDoctorAtIndex;
import static seedu.address.testutil.TypicalDatabase.getTypicalDatabase;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_DOCTOR;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_DOCTOR;

import java.util.Objects;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Database;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.doctor.Doctor;
import seedu.address.model.remark.Remark;
import seedu.address.testutil.DoctorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for DoctorRemarkCommand.
 */
public class DoctorRemarkCommandTest {

    private static final String REMARK_STUB = "Some remark";

    private Model model = new ModelManager(getTypicalDatabase(), new UserPrefs());

    @Test
    public void execute_addRemarkUnfilteredList_success() {
        Doctor firstDoctor =
                model.getFilteredDoctorList().get(INDEX_FIRST_DOCTOR.getZeroBased());
        Doctor editedDoctor =
                new DoctorBuilder(firstDoctor)
                        .withRemark(REMARK_STUB).build();

        DoctorRemarkCommand doctorRemarkCommand =
                new DoctorRemarkCommand(INDEX_FIRST_DOCTOR,
                        new Remark(editedDoctor.getRemark().remark));

        String expectedMessage =
                String.format(DoctorRemarkCommand.MESSAGE_ADD_REMARK_SUCCESS, editedDoctor);

        Model expectedModel = new ModelManager(new Database(model.getDatabase()), new UserPrefs());

        try {
            expectedModel.setDoctor(firstDoctor, editedDoctor);
        } catch (CommandException e) {
            throw new AssertionError(e.getMessage());
        }

        assertCommandSuccess(doctorRemarkCommand, model, expectedMessage, expectedModel);
        assertEquals(REMARK_STUB,
                model.getFilteredDoctorList().get(INDEX_FIRST_DOCTOR.getZeroBased())
                        .getRemark()
                        .remark);
    }

    @Test
    public void execute_deleteRemarkUnfilteredList_success() {
        Doctor firstDoctor = model.getFilteredDoctorList().get(INDEX_FIRST_DOCTOR.getZeroBased());
        Doctor editedDoctor = new DoctorBuilder(firstDoctor).withRemark("").build();

        DoctorRemarkCommand remarkCommand = new DoctorRemarkCommand(INDEX_FIRST_DOCTOR,
                new Remark(editedDoctor.getRemark().toString()));

        String expectedMessage = String.format(DoctorRemarkCommand.MESSAGE_DELETE_REMARK_SUCCESS,
                editedDoctor);

        Model expectedModel = new ModelManager(new Database(model.getDatabase()), new UserPrefs());

        try {
            expectedModel.setDoctor(firstDoctor, editedDoctor);
        } catch (CommandException e) {
            throw new AssertionError(e.getMessage());
        }

        assertCommandSuccess(remarkCommand, model, expectedMessage, expectedModel);
        assertEquals("",
                model.getFilteredDoctorList().get(INDEX_FIRST_DOCTOR.getZeroBased())
                        .getRemark()
                        .remark);
    }

    @Test
    public void execute_filteredList_success() {
        showDoctorAtIndex(model, INDEX_FIRST_DOCTOR);

        Doctor firstDoctor = model.getFilteredDoctorList().get(INDEX_FIRST_DOCTOR.getZeroBased());
        Doctor editedDoctor = new DoctorBuilder(model.getFilteredDoctorList()
                .get(INDEX_FIRST_DOCTOR.getZeroBased()))
                .withRemark(REMARK_STUB).build();

        DoctorRemarkCommand doctorRemarkCommand =
                new DoctorRemarkCommand(INDEX_FIRST_DOCTOR,
                        new Remark(editedDoctor.getRemark().remark));

        String expectedMessage = String.format(DoctorRemarkCommand.MESSAGE_ADD_REMARK_SUCCESS,
                editedDoctor);

        Model expectedModel = new ModelManager(new Database(model.getDatabase()), new UserPrefs());

        try {
            expectedModel.setDoctor(firstDoctor, editedDoctor);
        } catch (CommandException e) {
            throw new AssertionError(e.getMessage());
        }

        assertCommandSuccess(doctorRemarkCommand, model, expectedMessage, expectedModel);
        assertEquals(REMARK_STUB,
                model.getFilteredDoctorList().get(INDEX_FIRST_DOCTOR.getZeroBased())
                        .getRemark()
                        .remark);
    }

    @Test
    public void execute_invalidDoctorIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredDoctorList().size() + 1);
        DoctorRemarkCommand remarkCommand =
                new DoctorRemarkCommand(outOfBoundIndex, new Remark(REMARK_STUB));

        assertCommandFailure(remarkCommand, model, Messages.MESSAGE_INVALID_DOCTOR_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of database
     */
    @Test
    public void execute_invalidDoctorIndexFilteredList_failure() {
        showDoctorAtIndex(model, INDEX_FIRST_DOCTOR);
        Index outOfBoundIndex = INDEX_SECOND_DOCTOR;
        // ensures that outOfBoundIndex is still in bounds of database list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getDatabase().getDoctorList().size());

        DoctorRemarkCommand remarkCommand =
                new DoctorRemarkCommand(outOfBoundIndex, new Remark(REMARK_STUB));
        assertCommandFailure(remarkCommand, model, Messages.MESSAGE_INVALID_DOCTOR_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final DoctorRemarkCommand firstCommand = new DoctorRemarkCommand(INDEX_FIRST_DOCTOR,
                new Remark(VALID_REMARK_3));
        // same values -> returns true
        DoctorRemarkCommand commandWithSameValues = new DoctorRemarkCommand(INDEX_FIRST_DOCTOR,
                new Remark(VALID_REMARK_3));
        assertTrue(firstCommand.equals(commandWithSameValues));
        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));
        // null -> returns false
        assertFalse(firstCommand.equals(null));
        // different types -> returns false
        assertFalse(firstCommand.equals(new ListDoctorCommand()));
        // different index -> returns false
        assertFalse(firstCommand.equals(new DoctorRemarkCommand(INDEX_SECOND_DOCTOR,
                new Remark(VALID_REMARK_3))));
        // different remark -> returns false
        assertFalse(firstCommand.equals(new DoctorRemarkCommand(INDEX_FIRST_DOCTOR,
                new Remark(VALID_REMARK_4))));
    }

    @Test
    public void hashCodeMethod() {
        Index targetIndex = Index.fromOneBased(1);
        DoctorRemarkCommand command =
                new DoctorRemarkCommand(targetIndex,
                        new Remark(""));

        // same value -> returns same hashcode
        assertEquals(command.hashCode(), Objects.hash(targetIndex,
                new Remark("")));
    }
}
