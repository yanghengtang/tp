package seedu.address.logic.parser;

import seedu.address.logic.commands.AddAppointmentCommand;
import seedu.address.logic.commands.ListAppointmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.*;
import seedu.address.model.person.Nric;

import java.util.function.Predicate;
import java.util.stream.Stream;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_END_TIME;
import static seedu.address.model.NewModel.PREDICATE_SHOW_ALL_APPOINTMENTS;

public class ListAppointmentCommandParser {

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
        if (!arePrefixesPresent(argMultimap, PREFIX_PATIENT_NRIC)
                && !arePrefixesPresent(argMultimap, PREFIX_DOCTOR_NRIC)) {
            return new ListAppointmentCommand();
        } else if (!arePrefixesPresent(argMultimap, PREFIX_PATIENT_NRIC)) {
            Nric doctorNric = ParserUtil.parseNric(argMultimap.getValue(PREFIX_DOCTOR_NRIC).get());
            return new ListAppointmentCommand(PREDICATE_SHOW_ALL_APPOINTMENTS,
                    new AppointmentEqualDoctorNricPredicate(doctorNric));
        } else if (!arePrefixesPresent(argMultimap, PREFIX_DOCTOR_NRIC)) {
            Nric patientNric = ParserUtil.parseNric(argMultimap.getValue(PREFIX_PATIENT_NRIC).get());
            return new ListAppointmentCommand(new AppointmentEqualPatientNricPredicate(patientNric),
                    PREDICATE_SHOW_ALL_APPOINTMENTS);
        } else {
            Nric patientNric = ParserUtil.parseNric(argMultimap.getValue(PREFIX_PATIENT_NRIC).get());
            Nric doctorNric = ParserUtil.parseNric(argMultimap.getValue(PREFIX_DOCTOR_NRIC).get());
            return new ListAppointmentCommand(new AppointmentEqualPatientNricPredicate(patientNric),
                    new AppointmentEqualDoctorNricPredicate(doctorNric));
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
