package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_START_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DOCTOR_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PATIENT_NRIC;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditAppointmentCommand;
import seedu.address.logic.commands.EditAppointmentCommand.EditAppointmentDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditAppointmentCommand object
 */
public class EditAppointmentCommandParser implements Parser<EditAppointmentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditAppointmentCommand
     * and returns an EditAppointmentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditAppointmentCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args,
                        PREFIX_PATIENT_NRIC,
                        PREFIX_DOCTOR_NRIC,
                        PREFIX_APPOINTMENT_START_TIME,
                        PREFIX_APPOINTMENT_END_TIME);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditAppointmentCommand.MESSAGE_USAGE),
                    pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_PATIENT_NRIC,
                PREFIX_DOCTOR_NRIC,
                PREFIX_APPOINTMENT_START_TIME,
                PREFIX_APPOINTMENT_END_TIME);

        EditAppointmentDescriptor editAppointmentDescriptor = new EditAppointmentDescriptor();

        if (argMultimap.getValue(PREFIX_PATIENT_NRIC).isPresent()) {
            editAppointmentDescriptor
                    .setPatientNric(ParserUtil
                            .parseNric(argMultimap
                                    .getValue(PREFIX_PATIENT_NRIC).get()));
        }
        if (argMultimap.getValue(PREFIX_DOCTOR_NRIC).isPresent()) {
            editAppointmentDescriptor
                    .setDoctorNric(ParserUtil
                            .parseNric(argMultimap
                                    .getValue(PREFIX_DOCTOR_NRIC).get()));
        }
        if (argMultimap.getValue(PREFIX_APPOINTMENT_START_TIME).isPresent()) {
            editAppointmentDescriptor
                    .setAppointmentStartTime(ParserUtil
                            .parseAppointmentStartTime(argMultimap
                                    .getValue(PREFIX_APPOINTMENT_START_TIME).get()));
        }

        if (argMultimap.getValue(PREFIX_APPOINTMENT_END_TIME).isPresent()) {
            editAppointmentDescriptor
                    .setAppointmentEndTime(ParserUtil
                            .parseAppointmentEndTime(argMultimap
                                    .getValue(PREFIX_APPOINTMENT_END_TIME).get()));
        }

        if (!editAppointmentDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditAppointmentCommand.MESSAGE_NOT_EDITED);
        }

        return new EditAppointmentCommand(index, editAppointmentDescriptor);
    }
}
