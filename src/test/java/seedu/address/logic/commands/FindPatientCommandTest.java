package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_PATIENTS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalDatabase.getTypicalDatabase;
import static seedu.address.testutil.TypicalPatient.CARL;
import static seedu.address.testutil.TypicalPatient.ELLE;
import static seedu.address.testutil.TypicalPatient.FIONA;

import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.NameContainsKeywordsPatientPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindPatientCommand}.
 */
public class FindPatientCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() throws CommandException {
        model = new ModelManager(getTypicalDatabase(), new UserPrefs());
        expectedModel = new ModelManager(getTypicalDatabase(), new UserPrefs());
    }

    @Test
    public void equals() {
        NameContainsKeywordsPatientPredicate firstPredicate =
                new NameContainsKeywordsPatientPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPatientPredicate secondPredicate =
                new NameContainsKeywordsPatientPredicate(Collections.singletonList("second"));

        FindPatientCommand findFirstPatientCommand = new FindPatientCommand(firstPredicate);
        FindPatientCommand findSecondPatientCommand = new FindPatientCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstPatientCommand.equals(findFirstPatientCommand));

        // same values -> returns true
        FindPatientCommand findFirstPatientCommandCopy = new FindPatientCommand(firstPredicate);
        assertTrue(findFirstPatientCommand.equals(findFirstPatientCommandCopy));

        // different types -> returns false
        assertFalse(findFirstPatientCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstPatientCommand.equals(null));

        // different patients -> returns false
        assertFalse(findFirstPatientCommand.equals(findSecondPatientCommand));
    }

    @Test
    public void execute_zeroKeywords_noPatientFound() {
        String expectedMessage = String.format(MESSAGE_PATIENTS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPatientPredicate predicate = preparePredicate(" ");
        FindPatientCommand command = new FindPatientCommand(predicate);
        expectedModel.updateFilteredPatientList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPatientList());
    }

    @Test
    public void execute_multipleKeywords_multiplePatientsFound() {
        String expectedMessage = String.format(MESSAGE_PATIENTS_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPatientPredicate predicate = preparePredicate("Kurz Elle Kunz");
        FindPatientCommand command = new FindPatientCommand(predicate);
        expectedModel.updateFilteredPatientList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPatientList());
    }

    @Test
    public void toStringMethod() {
        NameContainsKeywordsPatientPredicate predicate =
                new NameContainsKeywordsPatientPredicate(Arrays.asList("keyword"));
        FindPatientCommand findPatientCommand = new FindPatientCommand(predicate);
        String expected = FindPatientCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findPatientCommand.toString());
    }

    @Test
    public void hashcode() {
        NameContainsKeywordsPatientPredicate firstPredicate =
                new NameContainsKeywordsPatientPredicate(Collections.singletonList("first"));

        FindPatientCommand findFirstCommand = new FindPatientCommand(firstPredicate);

        // same value -> returns same hashcode
        assertEquals(findFirstCommand.hashCode(), Objects.hash(firstPredicate));

        //objects are equal should have same hashcode
        FindPatientCommand findSecondCommand = new FindPatientCommand(firstPredicate);
        assertEquals(findFirstCommand.hashCode(), findSecondCommand.hashCode());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPatientPredicate}.
     */
    private NameContainsKeywordsPatientPredicate preparePredicate(String userInput) {
        return new NameContainsKeywordsPatientPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
