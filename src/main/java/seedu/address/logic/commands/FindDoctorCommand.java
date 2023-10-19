package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.NewModel;
import seedu.address.model.person.NameContainsKeywordsDoctorPredicate;


/**
 * Finds and lists all doctors in database whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindDoctorCommand extends NewCommand {

    public static final String COMMAND_WORD = "find_d";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all doctors whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    private final NameContainsKeywordsDoctorPredicate predicate;

    public FindDoctorCommand(NameContainsKeywordsDoctorPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(NewModel model) {
        requireNonNull(model);
        model.updateFilteredDoctorList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_DOCTORS_LISTED_OVERVIEW, model.getFilteredDoctorList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindDoctorCommand)) {
            return false;
        }

        FindDoctorCommand otherFindCommand = (FindDoctorCommand) other;
        return predicate.equals(otherFindCommand.predicate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
