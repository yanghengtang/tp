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
import seedu.address.logic.commands.AddPrescriptionCommand;
import seedu.address.logic.commands.AppointmentRemarkCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteAppointmentCommand;
import seedu.address.logic.commands.DeleteDoctorCommand;
import seedu.address.logic.commands.DeletePatientCommand;
import seedu.address.logic.commands.DeletePrescriptionCommand;
import seedu.address.logic.commands.DeleteSpecialisationCommand;
import seedu.address.logic.commands.DoctorRemarkCommand;
import seedu.address.logic.commands.EditAppointmentCommand;
import seedu.address.logic.commands.EditDoctorCommand;
import seedu.address.logic.commands.EditPatientCommand;
import seedu.address.logic.commands.FindDoctorCommand;
import seedu.address.logic.commands.FindPatientCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListAppointmentCommand;
import seedu.address.logic.commands.ListDoctorCommand;
import seedu.address.logic.commands.ListPatientsCommand;
import seedu.address.logic.commands.PatientRemarkCommand;
import seedu.address.logic.commands.ViewAppointmentCommand;
import seedu.address.logic.commands.ViewDoctorCommand;
import seedu.address.logic.commands.ViewPatientCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class MediConnectParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    private static final Logger logger = LogsCenter.getLogger(MediConnectParser.class);

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException, CommandException {
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
        case AddAppointmentCommand.COMMAND_WORD:
            return new AddAppointmentCommandParser().parse(arguments);

        case AddDoctorCommand.COMMAND_WORD:
            return new AddDoctorCommandParser().parse(arguments);

        case AddPatientCommand.COMMAND_WORD:
            return new AddPatientCommandParser().parse(arguments);

        case ListAppointmentCommand.COMMAND_WORD:
            return new ListAppointmentCommandParser().parse(arguments);

        case ListDoctorCommand.COMMAND_WORD:
            return new ListDoctorCommand();

        case ListPatientsCommand.COMMAND_WORD:
            return new ListPatientsCommand();

        case EditAppointmentCommand.COMMAND_WORD:
            return new EditAppointmentCommandParser().parse(arguments);

        case EditDoctorCommand.COMMAND_WORD:
            return new EditDoctorCommandParser().parse(arguments);

        case EditPatientCommand.COMMAND_WORD:
            return new EditPatientCommandParser().parse(arguments);

        case DeleteAppointmentCommand.COMMAND_WORD:
            return new DeleteAppointmentCommandParser().parse(arguments);

        case DeleteDoctorCommand.COMMAND_WORD:
            return new DeleteDoctorCommandParser().parse(arguments);

        case DeletePatientCommand.COMMAND_WORD:
            return new DeletePatientCommandParser().parse(arguments);

        case FindDoctorCommand.COMMAND_WORD:
            return new FindDoctorCommandParser().parse(arguments);

        case FindPatientCommand.COMMAND_WORD:
            return new FindPatientCommandParser().parse(arguments);

        case ViewAppointmentCommand.COMMAND_WORD:
            return new ViewAppointmentCommandParser().parse(arguments);

        case ViewDoctorCommand.COMMAND_WORD:
            return new ViewDoctorCommandParser().parse(arguments);

        case ViewPatientCommand.COMMAND_WORD:
            return new ViewPatientCommandParser().parse(arguments);

        case AppointmentRemarkCommand.COMMAND_WORD:
            return new AppointmentRemarkCommandParser().parse(arguments);

        case DoctorRemarkCommand.COMMAND_WORD:
            return new DoctorRemarkCommandParser().parse(arguments);

        case PatientRemarkCommand.COMMAND_WORD:
            return new PatientRemarkCommandParser().parse(arguments);

        case AddPrescriptionCommand.COMMAND_WORD:
            return (new AddPrescriptionCommandParser().parse(arguments));

        case DeleteSpecialisationCommand.COMMAND_WORD:
            return (new DeleteSpecialisationCommandParser().parse(arguments));

        case DeletePrescriptionCommand.COMMAND_WORD:
            return (new DeletePrescriptionCommandParser().parse(arguments));

        default:
            logger.finer("This user input caused a ParseException: " + userInput);
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
