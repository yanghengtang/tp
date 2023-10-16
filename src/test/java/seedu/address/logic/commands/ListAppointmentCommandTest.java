package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.testutil.TypicalDatabase.getTypicalDatabase;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.*;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListAppointmentCommandTest {

    private NewModel model;
    private NewModel expectedModel;

    @BeforeEach
    public void setUp() {
        model = new NewModelManager(getTypicalDatabase(), new UserPrefs());
        expectedModel = new NewModelManager(model.getDatabase(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertNewCommandSuccess(new ListAppointmentCommand(), model, ListAppointmentCommand.MESSAGE_SUCCESS, expectedModel);
    }

//    @Test
//    public void execute_listIsFiltered_showsEverything() {
//        showPersonAtIndex(model, INDEX_FIRST_PERSON);
//        assertNewCommandSuccess(new ListAppointmentCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
//    }
}