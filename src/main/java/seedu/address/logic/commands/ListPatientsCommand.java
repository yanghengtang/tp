package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.NewModel.PREDICATE_SHOW_ALL_PATIENTS;

import seedu.address.model.NewModel;

/**
 * Lists all patients in the database to the user.
 */
public class ListPatientsCommand extends NewCommand {

    public static final String COMMAND_WORD = "list_p";

    public static final String MESSAGE_SUCCESS = "Listed all patients";


    @Override
    public CommandResult execute(NewModel model) {
        requireNonNull(model);
        model.updateFilteredPatientList(PREDICATE_SHOW_ALL_PATIENTS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
