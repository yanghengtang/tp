package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Database;
import seedu.address.model.NewModel;
import seedu.address.model.person.NameContainsKeywordsDoctorPredicate;
import seedu.address.model.person.doctor.Doctor;

/**
 * Contains helper methods for testing commands.
 */
public class NewCommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_NRIC_AMY = "T0123456A";
    public static final String VALID_NRIC_BOB = "S9987654Z";
    public static final String VALID_PHONE_AMY = "81732200";
    public static final String VALID_PHONE_BOB = "91234567";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String NRIC_DESC_AMY = " " + PREFIX_NRIC + VALID_NRIC_AMY;
    public static final String NRIC_DESC_BOB = " " + PREFIX_NRIC + VALID_NRIC_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_NRIC_DESC = " " + PREFIX_NRIC + "T012#456A"; // '#' not allowed in nric
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";


    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches
     *  {@code expectedCommandResult} <br>
     * - the {@code actualNewModel} matches {@code expectedNewModel}
     */
    public static void assertCommandSuccess(NewCommand command, NewModel actualNewModel,
                                            CommandResult expectedCommandResult, NewModel expectedNewModel) {
        try {
            CommandResult result = command.execute(actualNewModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedNewModel, actualNewModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(NewCommand, NewModel, CommandResult, NewModel)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(NewCommand command, NewModel actualNewModel, String expectedMessage,
                                            NewModel expectedNewModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualNewModel, expectedCommandResult, expectedNewModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered doctor list and selected doctor in {@code actualNewModel} remain unchanged
     */
    public static void assertCommandFailure(NewCommand command, NewModel actualNewModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        Database expectedDatabase = new Database(actualNewModel.getDatabase());
        List<Doctor> expectedFilteredList = new ArrayList<>(actualNewModel.getFilteredDoctorList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualNewModel));
        assertEquals(expectedDatabase, actualNewModel.getDatabase());
        assertEquals(expectedFilteredList, actualNewModel.getFilteredDoctorList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the doctor at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showDoctorAtIndex(NewModel model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredDoctorList().size());

        Doctor doctor = model.getFilteredDoctorList().get(targetIndex.getZeroBased());
        final String[] splitName = doctor.getName().fullName.split("\\s+");
        model.updateFilteredDoctorList(new NameContainsKeywordsDoctorPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredDoctorList().size());
    }

}
