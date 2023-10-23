package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showDoctorAtIndex;
import static seedu.address.testutil.TypicalDatabase.getTypicalDatabase;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_DOCTOR;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListDoctorCommand.
 */
public class ListDoctorCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() throws CommandException {
        model = new ModelManager(getTypicalDatabase(), new UserPrefs());
        expectedModel = new ModelManager(model.getDatabase(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListDoctorCommand(), model, ListDoctorCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showDoctorAtIndex(model, INDEX_FIRST_DOCTOR);
        assertCommandSuccess(new ListDoctorCommand(), model, ListDoctorCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
