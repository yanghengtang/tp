package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DOCTOR_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PATIENT_NRIC;
import static seedu.address.model.NewModel.PREDICATE_SHOW_ALL_APPOINTMENTS;

import java.util.function.Predicate;
import java.util.stream.Stream;

import seedu.address.logic.commands.ListAppointmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentEqualDoctorNricPredicate;
import seedu.address.model.appointment.AppointmentEqualPatientNricPredicate;
import seedu.address.model.appointment.AppointmentFilterByNricPredicate;
import seedu.address.model.person.Nric;

/**
 * Parses input arguments and creates a new ListAppointmentCommand object
 */
public class ListAppointmentCommandParser implements NewParser<ListAppointmentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ListAppointmentCommand
     * and returns an ListAppointmentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListAppointmentCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_PATIENT_NRIC,
                PREFIX_DOCTOR_NRIC);
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_PATIENT_NRIC,
                PREFIX_DOCTOR_NRIC);
        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ListAppointmentCommand.MESSAGE_USAGE));
        }

        if (!arePrefixesPresent(argMultimap, PREFIX_PATIENT_NRIC)
                && !arePrefixesPresent(argMultimap, PREFIX_DOCTOR_NRIC)) {
            return new ListAppointmentCommand();
        } else if (!arePrefixesPresent(argMultimap, PREFIX_PATIENT_NRIC)) {
            Nric doctorNric = ParserUtil.parseNric(argMultimap.getValue(PREFIX_DOCTOR_NRIC).get());
            AppointmentEqualDoctorNricPredicate doctorPredicate = new AppointmentEqualDoctorNricPredicate(doctorNric);
            Predicate<Appointment> patientPredicate = PREDICATE_SHOW_ALL_APPOINTMENTS;
            AppointmentFilterByNricPredicate predicate =
                    new AppointmentFilterByNricPredicate(patientPredicate, doctorPredicate);
            return new ListAppointmentCommand(predicate);
        } else if (!arePrefixesPresent(argMultimap, PREFIX_DOCTOR_NRIC)) {
            Nric patientNric =
                    ParserUtil.parseNric(argMultimap.getValue(PREFIX_PATIENT_NRIC).get());
            AppointmentEqualPatientNricPredicate patientPredicate =
                    new AppointmentEqualPatientNricPredicate(patientNric);
            Predicate<Appointment> doctorPredicate = PREDICATE_SHOW_ALL_APPOINTMENTS;
            AppointmentFilterByNricPredicate predicate =
                    new AppointmentFilterByNricPredicate(patientPredicate, doctorPredicate);
            return new ListAppointmentCommand(predicate);
        } else {
            Nric patientNric = ParserUtil.parseNric(argMultimap.getValue(PREFIX_PATIENT_NRIC).get());
            Nric doctorNric = ParserUtil.parseNric(argMultimap.getValue(PREFIX_DOCTOR_NRIC).get());
            AppointmentEqualPatientNricPredicate patientPredicate =
                    new AppointmentEqualPatientNricPredicate(patientNric);
            AppointmentEqualDoctorNricPredicate doctorPredicate =
                    new AppointmentEqualDoctorNricPredicate(doctorNric);
            AppointmentFilterByNricPredicate predicate =
                    new AppointmentFilterByNricPredicate(patientPredicate, doctorPredicate);
            return new ListAppointmentCommand(predicate);
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
