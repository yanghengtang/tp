package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.AppointmentRemarkCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.remark.Remark;

/**
 * Parses input arguments and creates a new {@code AppointmentRemarkCommand} object
 */
public class AppointmentRemarkCommandParser implements Parser<AppointmentRemarkCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code AppointmentRemarkCommand}
     * and returns a {@code AppointmentRemarkCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AppointmentRemarkCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_REMARK);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AppointmentRemarkCommand.MESSAGE_USAGE), ive);
        }

        Remark remark = new Remark(argMultimap.getValue(PREFIX_REMARK).orElse(""));

        return new AppointmentRemarkCommand(index, remark);
    }
}
