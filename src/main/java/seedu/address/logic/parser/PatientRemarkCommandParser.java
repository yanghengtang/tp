package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.PatientRemarkCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.remark.Remark;

/**
 * Parses input arguments and creates a new {@code PatientRemarkCommand} object
 */
public class PatientRemarkCommandParser implements Parser<PatientRemarkCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code PatientRemarkCommand}
     * and returns a {@code PatientRemarkCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public PatientRemarkCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_REMARK);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    PatientRemarkCommand.MESSAGE_USAGE), ive);
        }

        Remark remark = new Remark(argMultimap.getValue(PREFIX_REMARK).orElse(""));

        return new PatientRemarkCommand(index, remark);
    }
}
