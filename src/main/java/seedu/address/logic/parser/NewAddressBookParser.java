package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.AddAppointmentCommand;
import seedu.address.logic.commands.AddDoctorCommand;
import seedu.address.logic.commands.AddPatientCommand;
import seedu.address.logic.commands.DeleteAppointmentCommand;
import seedu.address.logic.commands.DeleteDoctorCommand;
import seedu.address.logic.commands.DeletePatientCommand;
import seedu.address.logic.commands.EditDoctorCommand;
import seedu.address.logic.commands.FindPatientCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.NewCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class NewAddressBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    private static final Logger logger = LogsCenter.getLogger(AddressBookParser.class);

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public NewCommand parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        // Note to developers: Change the log level in config.json to enable lower level (i.e., FINE, FINER and lower)
        // log messages such as the one below.
        // Lower level log messages are used sparingly to minimize noise in the code.
        logger.fine("Command word: " + commandWord + "; Arguments: " + arguments);

        switch (commandWord) {

        //Todo
        case AddAppointmentCommand.COMMAND_WORD:
            return new AddAppointmentCommandParser().parse(arguments);

        case AddDoctorCommand.COMMAND_WORD:
            return new AddDoctorCommandParser().parse(arguments);

        case AddPatientCommand.COMMAND_WORD:
            return new AddPatientCommandParser().parse(arguments);

        case DeleteAppointmentCommand.COMMAND_WORD:
            return new DeleteAppointmentCommandParser().parse(arguments);

        case EditDoctorCommand.COMMAND_WORD:
            return new EditDoctorCommandParser().parse(arguments);

        case DeleteDoctorCommand.COMMAND_WORD:
            return new DeleteDoctorCommandParser().parse(arguments);

        case DeletePatientCommand.COMMAND_WORD:
            return new DeletePatientCommandParser().parse(arguments);

        case FindPatientCommand.COMMAND_WORD:
            return new FindPatientCommandParser().parse(arguments);

        default:
            logger.finer("This user input caused a ParseException: " + userInput);
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
