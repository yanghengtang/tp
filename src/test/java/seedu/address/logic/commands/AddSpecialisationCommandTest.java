package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SPECIALISATION_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SPECIALISATION_2;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showDoctorAtIndex;
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
public class AddSpecialisationCommandTest {

    private static final Tag SPECIALISATION_STUB = new Tag("SomeSpecialisation");

    private Model model = new ModelManager(getTypicalDatabase(), new UserPrefs());

    @Test
    public void execute_addSpecialisationUnfilteredList_success() {
        Doctor firstDoctor =
                model.getFilteredDoctorList().get(INDEX_FIRST_PERSON.getZeroBased());
        Doctor editedDoctor =
                new DoctorBuilder(firstDoctor)
                        .withTags(SPECIALISATION_STUB).build();

        AddSpecialisationCommand addSpecialisationCommand =
                new AddSpecialisationCommand(INDEX_FIRST_PERSON,
                        SPECIALISATION_STUB);

        String expectedMessage =
                String.format(AddSpecialisationCommand.MESSAGE_ADD_SPECIALISATION_SUCCESS, editedDoctor);

        Model expectedModel = new ModelManager(new Database(model.getDatabase()), new UserPrefs());

        try {
            expectedModel.setDoctor(firstDoctor, editedDoctor);
        } catch (CommandException e) {
            throw new AssertionError(e.getMessage());
        }

        System.out.println(expectedMessage);

        assertCommandSuccess(addSpecialisationCommand, model, expectedMessage, expectedModel);
    }


    @Test
    public void execute_filteredList_success() {
        showDoctorAtIndex(model, INDEX_FIRST_PERSON);

        Doctor firstDoctor = model.getFilteredDoctorList().get(INDEX_FIRST_PERSON.getZeroBased());
        Doctor editedDoctor = new DoctorBuilder(model.getFilteredDoctorList()
                .get(INDEX_FIRST_PERSON.getZeroBased()))
                .withTags(SPECIALISATION_STUB).build();

        AddSpecialisationCommand addSpecialisationCommand =
                new AddSpecialisationCommand(INDEX_FIRST_PERSON,
                        SPECIALISATION_STUB);

        String expectedMessage = String.format(AddSpecialisationCommand.MESSAGE_ADD_SPECIALISATION_SUCCESS,
                editedDoctor);

        Model expectedModel = new ModelManager(new Database(model.getDatabase()), new UserPrefs());

        try {
            expectedModel.setDoctor(firstDoctor, editedDoctor);
        } catch (CommandException e) {
            throw new AssertionError(e.getMessage());
        }

        assertCommandSuccess(addSpecialisationCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredDoctorList().size() + 1);
        AddSpecialisationCommand command =
                new AddSpecialisationCommand(outOfBoundIndex, SPECIALISATION_STUB);

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

        AddSpecialisationCommand command =
                new AddSpecialisationCommand(outOfBoundIndex, SPECIALISATION_STUB);
        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_DOCTOR_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of database
     */
    @Test
    public void execute_duplicateTag_failure() {
        Doctor firstDoctor =
                model.getFilteredDoctorList().get(INDEX_FIRST_PERSON.getZeroBased());
        Doctor editedDoctor =
                new DoctorBuilder(firstDoctor)
                        .withTags(SPECIALISATION_STUB).build();

        AddSpecialisationCommand command =
                new AddSpecialisationCommand(INDEX_FIRST_PERSON,
                        SPECIALISATION_STUB);
        try {
            command.execute(model);
        } catch (CommandException e) {
            throw new AssertionError(e.getMessage());
        }

        AddSpecialisationCommand duplicateCommand =
                new AddSpecialisationCommand(INDEX_FIRST_PERSON,
                        SPECIALISATION_STUB);
        String expectedMessage =
                String.format(AddSpecialisationCommand.MESSAGE_ADD_SPECIALISATION_FAILURE, SPECIALISATION_STUB);
        assertCommandFailure(duplicateCommand, model, expectedMessage);
    }

    @Test
    public void equals() {
        final AddSpecialisationCommand firstCommand = new AddSpecialisationCommand(INDEX_FIRST_PERSON,
                new Tag(VALID_SPECIALISATION_1));
        // same values -> returns true
        AddSpecialisationCommand commandWithSameValues = new AddSpecialisationCommand(INDEX_FIRST_PERSON,
                new Tag(VALID_SPECIALISATION_1));
        assertTrue(firstCommand.equals(commandWithSameValues));
        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));
        // null -> returns false
        assertFalse(firstCommand.equals(null));
        // different types -> returns false
        assertFalse(firstCommand.equals(new ListDoctorCommand()));
        // different index -> returns false
        assertFalse(firstCommand.equals(new AddSpecialisationCommand(INDEX_SECOND_PERSON,
                new Tag(VALID_SPECIALISATION_1))));
        // different remark -> returns false
        assertFalse(firstCommand.equals(new AddSpecialisationCommand(INDEX_FIRST_PERSON,
                new Tag(VALID_SPECIALISATION_2))));
    }

    @Test
    public void hashCodeMethod() {
        Index targetIndex = Index.fromOneBased(1);
        AddSpecialisationCommand command =
                new AddSpecialisationCommand(targetIndex,
                        SPECIALISATION_STUB);

        // same value -> returns same hashcode
        assertEquals(command.hashCode(), Objects.hash(targetIndex,
                SPECIALISATION_STUB));
    }
}
