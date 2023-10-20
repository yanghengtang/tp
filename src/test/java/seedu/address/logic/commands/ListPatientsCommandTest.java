package seedu.address.logic.commands;

import static seedu.address.logic.commands.NewCommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.NewCommandTestUtil.showPatientAtIndex;
import static seedu.address.testutil.TypicalDatabase.getTypicalDatabase;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.NewModel;
import seedu.address.model.NewModelManager;
import seedu.address.model.UserPrefs;
/**
 * Contains integration tests (interaction with the NewModel) and unit tests for ListPatientsCommand.
 */
public class ListPatientsCommandTest {

    private NewModel model;
    private NewModel expectedModel;

    @BeforeEach
    public void setUp() {
        model = new NewModelManager(getTypicalDatabase(), new UserPrefs());
        expectedModel = new NewModelManager(model.getDatabase(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListPatientsCommand(), model, ListPatientsCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showPatientAtIndex(model, INDEX_FIRST_PERSON);
        assertCommandSuccess(new ListPatientsCommand(), model, ListPatientsCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
