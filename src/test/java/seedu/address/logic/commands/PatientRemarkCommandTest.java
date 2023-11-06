
package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_2;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPatientAtIndex;
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
import seedu.address.model.person.patient.Patient;
import seedu.address.model.remark.Remark;
import seedu.address.testutil.PatientBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for PatientRemarkCommand.
 */
public class PatientRemarkCommandTest {

    private static final String REMARK_STUB = "Some remark";

    private Model model = new ModelManager(getTypicalDatabase(), new UserPrefs());

    @Test
    public void execute_addRemarkUnfilteredList_success() {
        Patient firstPatient =
                model.getFilteredPatientList().get(INDEX_FIRST_PERSON.getZeroBased());
        Patient editedPatient =
                new PatientBuilder(firstPatient)
                        .withRemark(REMARK_STUB).build();

        PatientRemarkCommand patientRemarkCommand =
                new PatientRemarkCommand(INDEX_FIRST_PERSON,
                        new Remark(editedPatient.getRemark().remark));

        String expectedMessage =
                String.format(PatientRemarkCommand.MESSAGE_ADD_REMARK_SUCCESS,
                        INDEX_FIRST_PERSON.getOneBased());

        Model expectedModel = new ModelManager(new Database(model.getDatabase()), new UserPrefs());

        try {
            expectedModel.setPatient(firstPatient, editedPatient);
        } catch (CommandException e) {
            throw new AssertionError(e.getMessage());
        }

        assertCommandSuccess(patientRemarkCommand, model, expectedMessage, expectedModel);
        assertEquals(REMARK_STUB,
                model.getFilteredPatientList().get(INDEX_FIRST_PERSON.getZeroBased())
                        .getRemark()
                        .remark);
    }

    @Test
    public void execute_deleteRemarkUnfilteredList_success() {
        Patient firstPatient = model.getFilteredPatientList().get(INDEX_FIRST_PERSON.getZeroBased());
        Patient editedPatient = new PatientBuilder(firstPatient).withRemark("").build();

        PatientRemarkCommand patientRemarkCommand = new PatientRemarkCommand(INDEX_FIRST_PERSON,
                new Remark(editedPatient.getRemark().toString()));

        String expectedMessage = String.format(PatientRemarkCommand.MESSAGE_DELETE_REMARK_SUCCESS,
                INDEX_FIRST_PERSON.getOneBased());

        Model expectedModel = new ModelManager(new Database(model.getDatabase()), new UserPrefs());

        try {
            expectedModel.setPatient(firstPatient, editedPatient);
        } catch (CommandException e) {
            throw new AssertionError(e.getMessage());
        }

        assertCommandSuccess(patientRemarkCommand, model, expectedMessage, expectedModel);
        assertEquals("",
                model.getFilteredPatientList().get(INDEX_FIRST_PERSON.getZeroBased())
                        .getRemark()
                        .remark);
    }

    @Test
    public void execute_filteredList_success() {
        showPatientAtIndex(model, INDEX_FIRST_PERSON);

        Patient firstPatient = model.getFilteredPatientList().get(INDEX_FIRST_PERSON.getZeroBased());
        Patient editedPatient = new PatientBuilder(model.getFilteredPatientList()
                .get(INDEX_FIRST_PERSON.getZeroBased()))
                .withRemark(REMARK_STUB).build();

        PatientRemarkCommand appointmentRemarkCommand =
                new PatientRemarkCommand(INDEX_FIRST_PERSON,
                        new Remark(editedPatient.getRemark().remark));

        String expectedMessage = String.format(PatientRemarkCommand.MESSAGE_ADD_REMARK_SUCCESS,
                INDEX_FIRST_PERSON.getOneBased());

        Model expectedModel = new ModelManager(new Database(model.getDatabase()), new UserPrefs());

        try {
            expectedModel.setPatient(firstPatient, editedPatient);
        } catch (CommandException e) {
            throw new AssertionError(e.getMessage());
        }

        assertCommandSuccess(appointmentRemarkCommand, model, expectedMessage, expectedModel);
        assertEquals(REMARK_STUB,
                model.getFilteredPatientList().get(INDEX_FIRST_PERSON.getZeroBased())
                        .getRemark()
                        .remark);
    }

    @Test
    public void execute_invalidPatientIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPatientList().size() + 1);
        PatientRemarkCommand remarkCommand =
                new PatientRemarkCommand(outOfBoundIndex, new Remark(REMARK_STUB));

        assertCommandFailure(remarkCommand, model, Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of database
     */
    @Test
    public void execute_invalidPatientIndexFilteredList_failure() {
        showPatientAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of database list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getDatabase().getPatientList().size());

        PatientRemarkCommand remarkCommand =
                new PatientRemarkCommand(outOfBoundIndex, new Remark(REMARK_STUB));
        assertCommandFailure(remarkCommand, model, Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final PatientRemarkCommand firstCommand = new PatientRemarkCommand(INDEX_FIRST_PERSON,
                new Remark(VALID_REMARK_1));
        // same values -> returns true
        PatientRemarkCommand commandWithSameValues = new PatientRemarkCommand(INDEX_FIRST_PERSON,
                new Remark(VALID_REMARK_1));
        assertTrue(firstCommand.equals(commandWithSameValues));
        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));
        // null -> returns false
        assertFalse(firstCommand.equals(null));
        // different types -> returns false
        assertFalse(firstCommand.equals(new ListAppointmentCommand()));
        // different index -> returns false
        assertFalse(firstCommand.equals(new PatientRemarkCommand(INDEX_SECOND_PERSON,
                new Remark(VALID_REMARK_1))));
        // different remark -> returns false
        assertFalse(firstCommand.equals(new PatientRemarkCommand(INDEX_FIRST_PERSON,
                new Remark(VALID_REMARK_2))));
    }

    @Test
    public void hashCodeMethod() {
        Index targetIndex = Index.fromOneBased(1);
        PatientRemarkCommand command =
                new PatientRemarkCommand(targetIndex,
                        new Remark(""));

        // same value -> returns same hashcode
        assertEquals(command.hashCode(), Objects.hash(targetIndex,
                new Remark("")));
    }
}
