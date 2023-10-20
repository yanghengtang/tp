package seedu.address.logic.commands;

import static seedu.address.logic.commands.HelpCommand.SHOWING_HELP_MESSAGE;
import static seedu.address.logic.commands.NewCommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.model.NewModel;
import seedu.address.model.NewModelManager;

public class HelpCommandTest {
    private NewModel model = new NewModelManager();
    private NewModel expectedModel = new NewModelManager();

    @Test
    public void execute_help_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_HELP_MESSAGE, true, false);
        assertCommandSuccess(new HelpCommand(), model, expectedCommandResult, expectedModel);
    }
}
