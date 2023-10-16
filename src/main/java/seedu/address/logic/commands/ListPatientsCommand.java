package seedu.address.logic.commands;

import seedu.address.model.NewModel;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.NewModel.PREDICATE_SHOW_ALL_PATIENTS;

/**
 * Lists all patients in the address book to the user.
 */
public class ListPatientsCommand extends NewCommand {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all patients";


    @Override
    public CommandResult execute(NewModel model) {
        requireNonNull(model);
        model.updateFilteredPatientList(PREDICATE_SHOW_ALL_PATIENTS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
