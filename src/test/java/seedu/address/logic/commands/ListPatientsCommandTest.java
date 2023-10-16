package seedu.address.logic.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.address.model.NewModel;
import seedu.address.model.NewModelManager;
import seedu.address.model.UserPrefs;

import static seedu.address.logic.commands.CommandTestUtil.assertNewCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPatientAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalDatabase.getTypicalDatabase;

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
        assertNewCommandSuccess(new ListPatientsCommand(), model, ListPatientsCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showPatientAtIndex(model, INDEX_FIRST_PERSON);
        assertNewCommandSuccess(new ListPatientsCommand(), model, ListPatientsCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
