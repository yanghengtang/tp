package seedu.address.logic.commands;

import static seedu.address.logic.commands.NewCommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.NewCommandTestUtil.showDoctorAtIndex;
import static seedu.address.testutil.TypicalDatabase.getTypicalDatabase;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_DOCTOR;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.NewModel;
import seedu.address.model.NewModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the NewModel) and unit tests for ListDoctorCommand.
 */
public class ListDoctorCommandTest {
    private NewModel model;
    private NewModel expectedNewModel;

    @BeforeEach
    public void setUp() {
        model = new NewModelManager(getTypicalDatabase(), new UserPrefs());
        expectedNewModel = new NewModelManager(model.getDatabase(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListDoctorCommand(), model, ListDoctorCommand.MESSAGE_SUCCESS, expectedNewModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showDoctorAtIndex(model, INDEX_FIRST_DOCTOR);
        assertCommandSuccess(new ListDoctorCommand(), model, ListDoctorCommand.MESSAGE_SUCCESS, expectedNewModel);
    }
}
