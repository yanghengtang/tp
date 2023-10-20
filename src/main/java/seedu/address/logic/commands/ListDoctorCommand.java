package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.NewModel.PREDICATE_SHOW_ALL_DOCTORS;

import seedu.address.model.NewModel;

/**
 * Lists all doctors in the database to the user.
 */
public class ListDoctorCommand extends NewCommand {

    public static final String COMMAND_WORD = "list_d";

    public static final String MESSAGE_SUCCESS = "Listed all doctors";

    @Override
    public CommandResult execute(NewModel model) {
        requireNonNull(model);
        model.updateFilteredDoctorList(PREDICATE_SHOW_ALL_DOCTORS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
