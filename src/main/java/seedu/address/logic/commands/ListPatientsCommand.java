package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PATIENTS;

import seedu.address.model.Model;

/**
 * Lists all patients in the database to the user.
 */
public class ListPatientsCommand extends Command {

    public static final String COMMAND_WORD = "list_p";

    public static final String MESSAGE_SUCCESS = "Listed all patients";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPatientList(PREDICATE_SHOW_ALL_PATIENTS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
