package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddDoctorCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.model.person.doctor.Doctor;

/**
 * Parses input arguments and creates a new AddDoctorCommand object
 */
public class AddDoctorCommandParser implements NewParser<AddDoctorCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddDoctorCommand
     * and returns an AddDoctorCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddDoctorCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_NRIC);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_NRIC)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddDoctorCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_NRIC);
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Nric nric = ParserUtil.parseNric(argMultimap.getValue(PREFIX_NRIC).get());


        Doctor doctor = new Doctor(name, nric);

        return new AddDoctorCommand(doctor);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
