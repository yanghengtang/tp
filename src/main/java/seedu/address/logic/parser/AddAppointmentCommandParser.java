package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_START_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DOCTOR_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PATIENT_NRIC;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddAppointmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentEndTime;
import seedu.address.model.appointment.AppointmentStartTime;
import seedu.address.model.person.Nric;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddAppointmentCommandParser implements NewParser<AddAppointmentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddAppointmentCommand
     * and returns an AddAppointmentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddAppointmentCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                        PREFIX_PATIENT_NRIC,
                        PREFIX_DOCTOR_NRIC,
                        PREFIX_APPOINTMENT_START_TIME, PREFIX_APPOINTMENT_END_TIME);

        if (!arePrefixesPresent(argMultimap,
                PREFIX_PATIENT_NRIC,
                PREFIX_DOCTOR_NRIC,
                PREFIX_APPOINTMENT_START_TIME,
                PREFIX_APPOINTMENT_END_TIME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddAppointmentCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_PATIENT_NRIC,
                PREFIX_DOCTOR_NRIC,
                PREFIX_APPOINTMENT_START_TIME,
                PREFIX_APPOINTMENT_END_TIME);
        Nric patientNric = ParserUtil.parseNric(argMultimap.getValue(PREFIX_PATIENT_NRIC).get());
        Nric doctorNric = ParserUtil.parseNric(argMultimap.getValue(PREFIX_DOCTOR_NRIC).get());
        AppointmentStartTime start = ParserUtil
                .parseAppointmentStartTime(argMultimap.getValue(PREFIX_APPOINTMENT_START_TIME).get());
        AppointmentEndTime end = ParserUtil
                .parseAppointmentEndTime(argMultimap.getValue(PREFIX_APPOINTMENT_END_TIME).get());

        Appointment appointment = new Appointment(doctorNric, patientNric, start, end);

        return new AddAppointmentCommand(appointment);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
