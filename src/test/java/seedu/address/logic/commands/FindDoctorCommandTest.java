package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_DOCTORS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.NewCommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalDatabase.getTypicalDatabase;
import static seedu.address.testutil.TypicalDoctor.CARL;
import static seedu.address.testutil.TypicalDoctor.ELLE;
import static seedu.address.testutil.TypicalDoctor.FIONA;

import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

import org.junit.jupiter.api.Test;

import seedu.address.model.NewModel;
import seedu.address.model.NewModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.NameContainsKeywordsDoctorPredicate;

/**
 * Contains integration tests (interaction with the NewModel) for {@code FindDoctorCommand}.
 */
public class FindDoctorCommandTest {
    private NewModel model = new NewModelManager(getTypicalDatabase(), new UserPrefs());
    private NewModel expectedNewModel = new NewModelManager(getTypicalDatabase(), new UserPrefs());

    @Test
    public void equals() {
        NameContainsKeywordsDoctorPredicate firstPredicate =
                new NameContainsKeywordsDoctorPredicate(Collections.singletonList("first"));
        NameContainsKeywordsDoctorPredicate secondPredicate =
                new NameContainsKeywordsDoctorPredicate(Collections.singletonList("second"));

        FindDoctorCommand findFirstCommand = new FindDoctorCommand(firstPredicate);
        FindDoctorCommand findSecondCommand = new FindDoctorCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindDoctorCommand findFirstCommandCopy = new FindDoctorCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void hashcode() {
        NameContainsKeywordsDoctorPredicate firstPredicate =
                new NameContainsKeywordsDoctorPredicate(Collections.singletonList("first"));

        FindDoctorCommand findFirstCommand = new FindDoctorCommand(firstPredicate);

        // same value -> returns same hashcode
        assertEquals(findFirstCommand.hashCode(), Objects.hash(firstPredicate));

        //objects are equal should have same hashcode
        FindDoctorCommand findSecondCommand = new FindDoctorCommand(firstPredicate);
        assertEquals(findFirstCommand.hashCode(), findSecondCommand.hashCode());
    }


    @Test
    public void execute_zeroKeywords_noDoctorFound() {
        String expectedMessage = String.format(MESSAGE_DOCTORS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsDoctorPredicate predicate = preparePredicate(" ");
        FindDoctorCommand command = new FindDoctorCommand(predicate);
        expectedNewModel.updateFilteredDoctorList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedNewModel);
        assertEquals(Collections.emptyList(), model.getFilteredDoctorList());
    }

    @Test
    public void execute_multipleKeywords_multipleDoctorsFound() {
        String expectedMessage = String.format(MESSAGE_DOCTORS_LISTED_OVERVIEW, 3);
        NameContainsKeywordsDoctorPredicate predicate = preparePredicate("Kurz Elle Kunz");
        FindDoctorCommand command = new FindDoctorCommand(predicate);
        expectedNewModel.updateFilteredDoctorList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedNewModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredDoctorList());
    }

    @Test
    public void toStringMethod() {
        NameContainsKeywordsDoctorPredicate predicate =
                new NameContainsKeywordsDoctorPredicate(Arrays.asList("keyword"));
        FindDoctorCommand findCommand = new FindDoctorCommand(predicate);
        String expected = FindDoctorCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsDoctorPredicate}.
     */
    private NameContainsKeywordsDoctorPredicate preparePredicate(String userInput) {
        return new NameContainsKeywordsDoctorPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
