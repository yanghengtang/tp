package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showDoctorAtIndex;
import static seedu.address.model.DataTest.DERMATOLOGY_TAG;
import static seedu.address.model.DataTest.DERMATOLOGY_TAG_STRING;
import static seedu.address.model.DataTest.ORTHOPAEDIC_TAG_STRING;
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
import seedu.address.model.person.doctor.Doctor;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.DoctorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for AddSpecialisationCommand.
 */
public class DeleteSpecialisationCommandTest {

    private static final Tag TAG_STUB = new Tag("example");

    private Model model = new ModelManager(getTypicalDatabase(), new UserPrefs());


    @Test
    public void execute_deleteTagUnfilteredList_success() {
        Doctor firstDoctor =
                model.getFilteredDoctorList().get(INDEX_FIRST_PERSON.getZeroBased());

        Doctor editedDoctor =
                new DoctorBuilder(firstDoctor)
                        .withTags(DERMATOLOGY_TAG).build();

        DeleteSpecialisationCommand deleteSpecialisationCommand =
                new DeleteSpecialisationCommand(INDEX_FIRST_PERSON,
                        DERMATOLOGY_TAG);

        String expectedMessage =
                String.format(DeleteSpecialisationCommand.MESSAGE_DELETE_SPECIALISATION_SUCCESS,
                        DERMATOLOGY_TAG.tagName);

        Model expectedModel = new ModelManager(new Database(model.getDatabase()), new UserPrefs());

        try {
            expectedModel.setDoctor(firstDoctor, editedDoctor);
        } catch (CommandException e) {
            throw new AssertionError(e.getMessage());
        }

        assertCommandSuccess(deleteSpecialisationCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showDoctorAtIndex(model, INDEX_FIRST_PERSON);

        Doctor firstDoctor = model.getFilteredDoctorList().get(INDEX_FIRST_PERSON.getZeroBased());
        Doctor editedDoctor = new DoctorBuilder(model.getFilteredDoctorList()
                .get(INDEX_FIRST_PERSON.getZeroBased()))
                .withTags(DERMATOLOGY_TAG).build();

        DeleteSpecialisationCommand deleteSpecialisationCommand =
                new DeleteSpecialisationCommand(INDEX_FIRST_PERSON,
                        DERMATOLOGY_TAG);

        String expectedMessage = String.format(DeleteSpecialisationCommand.MESSAGE_DELETE_SPECIALISATION_SUCCESS,
                DERMATOLOGY_TAG.tagName);

        Model expectedModel = new ModelManager(new Database(model.getDatabase()), new UserPrefs());

        try {
            expectedModel.setDoctor(firstDoctor, editedDoctor);
        } catch (CommandException e) {
            throw new AssertionError(e.getMessage());
        }

        assertCommandSuccess(deleteSpecialisationCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredDoctorList().size() + 1);
        DeleteSpecialisationCommand command =
                new DeleteSpecialisationCommand(outOfBoundIndex, DERMATOLOGY_TAG);

        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_DOCTOR_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of database
     */
    @Test
    public void execute_invalidIndexFilteredList_failure() {
        showDoctorAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of database list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getDatabase().getDoctorList().size());

        DeleteSpecialisationCommand command =
                new DeleteSpecialisationCommand(outOfBoundIndex, DERMATOLOGY_TAG);
        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_DOCTOR_DISPLAYED_INDEX);
    }

    /**
     * Edit patient where condition is not already present
     */
    @Test
    public void execute_missingTag_failure() {

        DeleteSpecialisationCommand command =
                new DeleteSpecialisationCommand(INDEX_FIRST_PERSON,
                        TAG_STUB);
        String expectedMessage =
                String.format(DeleteSpecialisationCommand.MESSAGE_DELETE_SPECIALISATION_FAILURE,
                        TAG_STUB.tagName);
        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void equals() {
        DeleteSpecialisationCommand firstCommand = new DeleteSpecialisationCommand(INDEX_FIRST_PERSON,
                new Tag(DERMATOLOGY_TAG_STRING));
        // same values -> returns true
        DeleteSpecialisationCommand commandWithSameValues = new DeleteSpecialisationCommand(INDEX_FIRST_PERSON,
                new Tag(DERMATOLOGY_TAG_STRING));
        assertTrue(firstCommand.equals(commandWithSameValues));
        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));
        // null -> returns false
        assertFalse(firstCommand.equals(null));
        // different types -> returns false
        assertFalse(firstCommand.equals(new ListAppointmentCommand()));
        // different index -> returns false
        assertFalse(firstCommand.equals(new DeleteSpecialisationCommand(INDEX_SECOND_PERSON,
                new Tag(DERMATOLOGY_TAG_STRING))));
        // different tag -> returns false
        assertFalse(firstCommand.equals(new DeleteSpecialisationCommand(INDEX_FIRST_PERSON,
                new Tag(ORTHOPAEDIC_TAG_STRING))));
    }

    @Test
    public void hashCodeMethod() {
        Index targetIndex = Index.fromOneBased(1);
        DeleteSpecialisationCommand command =
                new DeleteSpecialisationCommand(targetIndex,
                        DERMATOLOGY_TAG);

        // same value -> returns same hashcode
        assertEquals(command.hashCode(), Objects.hash(targetIndex,
                DERMATOLOGY_TAG));
    }
}
