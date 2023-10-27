package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ViewDoctorCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ViewDoctorCommand object
 */
public class ViewDoctorCommandParser implements Parser<ViewDoctorCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ViewDoctorCommand
     * and returns a ViewDoctorCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewDoctorCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new ViewDoctorCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewDoctorCommand.MESSAGE_USAGE), pe);
        }
    }

}
