package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.NewModel.PREDICATE_SHOW_ALL_APPOINTMENTS;

import seedu.address.model.NewModel;

/**
 * Lists all persons in the address book to the user.
 */
public class ListAppointmentCommand extends NewCommand {

    public static final String COMMAND_WORD = "list_a";

    public static final String MESSAGE_SUCCESS = "Listed all appointments";


    @Override
    public CommandResult execute(NewModel model) {
        requireNonNull(model);
        model.updateFilteredAppointmentList(PREDICATE_SHOW_ALL_APPOINTMENTS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
