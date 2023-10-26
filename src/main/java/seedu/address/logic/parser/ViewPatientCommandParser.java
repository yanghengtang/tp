package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ViewPatientCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ViewPatientCommandParser object
 */
public class ViewPatientCommandParser implements Parser<ViewPatientCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ViewPatientCommand
     * and returns a ViewPatientCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewPatientCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new ViewPatientCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewPatientCommand.MESSAGE_USAGE), pe);
        }
    }

}
