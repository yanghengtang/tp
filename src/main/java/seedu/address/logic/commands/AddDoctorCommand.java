package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.NewModel;
import seedu.address.model.person.doctor.Doctor;


/**
 * Adds a doctor to the database.
 */
public class AddDoctorCommand extends NewCommand {

    public static final String COMMAND_WORD = "add_d";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a doctor to the database. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_NRIC + "NRIC";

    public static final String MESSAGE_SUCCESS = "New doctor added: %1$s";
    public static final String MESSAGE_DUPLICATE_DOCTOR = "This doctor already exists in the database";

    private final Doctor toAdd;

    /**
     * Creates an AddDoctorCommand to add the specified {@code Doctor}
     */
    public AddDoctorCommand(Doctor doctor) {
        requireNonNull(doctor);
        toAdd = doctor;
    }

    @Override
    public CommandResult execute(NewModel model) throws CommandException {
        requireNonNull(model);

        if (model.hasDoctor(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_DOCTOR);
        }

        model.addDoctor(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddDoctorCommand)) {
            return false;
        }

        AddDoctorCommand otherAddCommand = (AddDoctorCommand) other;
        return toAdd.equals(otherAddCommand.toAdd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(toAdd);
    }
}
