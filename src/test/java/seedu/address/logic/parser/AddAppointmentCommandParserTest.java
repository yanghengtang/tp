package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.APPOINTMENT_END_TIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.APPOINTMENT_START_TIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.DOCTOR_NRIC_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_APPOINTMENT_END_TIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_APPOINTMENT_START_TIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DOCTOR_NRIC_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PATIENT_NRIC_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PATIENT_NRIC_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_END_TIME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_START_TIME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DOCTOR_NRIC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PATIENT_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_START_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DOCTOR_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PATIENT_NRIC;
import static seedu.address.logic.parser.NewCommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.NewCommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalAppointment.APPOINTMENT_1;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddAppointmentCommand;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentEndTime;
import seedu.address.model.appointment.AppointmentStartTime;
import seedu.address.model.person.Nric;
import seedu.address.testutil.AppointmentBuilder;

public class AddAppointmentCommandParserTest {
    private AddAppointmentCommandParser parser = new AddAppointmentCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Appointment expectedAppointment = new AppointmentBuilder(APPOINTMENT_1).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE
                + PATIENT_NRIC_DESC
                + DOCTOR_NRIC_DESC
                + APPOINTMENT_START_TIME_DESC
                + APPOINTMENT_END_TIME_DESC , new AddAppointmentCommand(expectedAppointment));

    }

    @Test
    public void parse_repeatedValue_failure() {
        String validExpectedAppointmentString = PATIENT_NRIC_DESC
                + DOCTOR_NRIC_DESC
                + APPOINTMENT_START_TIME_DESC
                + APPOINTMENT_END_TIME_DESC;

        // multiple patient nric
        assertParseFailure(parser, PATIENT_NRIC_DESC + validExpectedAppointmentString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PATIENT_NRIC));

        // multiple doctor nric
        assertParseFailure(parser, DOCTOR_NRIC_DESC + validExpectedAppointmentString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DOCTOR_NRIC));

        // multiple apointment start dates
        assertParseFailure(parser, APPOINTMENT_START_TIME_DESC + validExpectedAppointmentString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_APPOINTMENT_START_TIME));

        // multiple appointment end dates
        assertParseFailure(parser, APPOINTMENT_END_TIME_DESC + validExpectedAppointmentString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_APPOINTMENT_END_TIME));

        // multiple fields repeated
        assertParseFailure(parser,
                validExpectedAppointmentString + PATIENT_NRIC_DESC
                        + DOCTOR_NRIC_DESC
                        + APPOINTMENT_START_TIME_DESC
                        + APPOINTMENT_END_TIME_DESC
                        + validExpectedAppointmentString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PATIENT_NRIC, PREFIX_DOCTOR_NRIC,
                        PREFIX_APPOINTMENT_START_TIME, PREFIX_APPOINTMENT_END_TIME));

        // invalid patient Nric
        assertParseFailure(parser, INVALID_PATIENT_NRIC_DESC + validExpectedAppointmentString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PATIENT_NRIC));

        // invalid email
        assertParseFailure(parser, INVALID_DOCTOR_NRIC_DESC + validExpectedAppointmentString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DOCTOR_NRIC));

        // invalid phone
        assertParseFailure(parser, INVALID_APPOINTMENT_START_TIME_DESC + validExpectedAppointmentString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_APPOINTMENT_START_TIME));

        // invalid address
        assertParseFailure(parser, INVALID_APPOINTMENT_END_TIME_DESC + validExpectedAppointmentString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_APPOINTMENT_END_TIME));

        // valid value followed by invalid value

        // invalid patient Nric
        assertParseFailure(parser, validExpectedAppointmentString + INVALID_PATIENT_NRIC_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PATIENT_NRIC));

        // invalid doctor Nric
        assertParseFailure(parser, validExpectedAppointmentString + INVALID_DOCTOR_NRIC_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DOCTOR_NRIC));

        // invalid Appointment start date
        assertParseFailure(parser, validExpectedAppointmentString + INVALID_APPOINTMENT_START_TIME_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_APPOINTMENT_START_TIME));

        // invalid Appointment end date
        assertParseFailure(parser, validExpectedAppointmentString + INVALID_APPOINTMENT_END_TIME_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_APPOINTMENT_END_TIME));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAppointmentCommand.MESSAGE_USAGE);

        // missing patient nric prefix
        assertParseFailure(parser, VALID_PATIENT_NRIC
                        + DOCTOR_NRIC_DESC
                        + APPOINTMENT_START_TIME_DESC
                        + APPOINTMENT_END_TIME_DESC,
                expectedMessage);

        // missing doctor nric prefix
        assertParseFailure(parser, PATIENT_NRIC_DESC
                        + VALID_DOCTOR_NRIC
                        + APPOINTMENT_START_TIME_DESC
                        + APPOINTMENT_END_TIME_DESC,
                expectedMessage);

        // missing appointment start time prefix
        assertParseFailure(parser, PATIENT_NRIC_DESC
                        + DOCTOR_NRIC_DESC
                        + VALID_APPOINTMENT_START_TIME
                        + APPOINTMENT_END_TIME_DESC,
                expectedMessage);

        // missing appointment end time prefix
        assertParseFailure(parser, PATIENT_NRIC_DESC
                        + DOCTOR_NRIC_DESC
                        + APPOINTMENT_START_TIME_DESC
                        + VALID_APPOINTMENT_END_TIME,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_PATIENT_NRIC
                        + VALID_DOCTOR_NRIC
                        + VALID_APPOINTMENT_START_TIME
                        + VALID_APPOINTMENT_END_TIME,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid patient nric
        assertParseFailure(parser, INVALID_PATIENT_NRIC_DESC
                        + DOCTOR_NRIC_DESC
                        + APPOINTMENT_START_TIME_DESC
                        + APPOINTMENT_END_TIME_DESC,
                Nric.MESSAGE_CONSTRAINTS);
        // invalid doctor nric
        assertParseFailure(parser, PATIENT_NRIC_DESC
                        + INVALID_DOCTOR_NRIC_DESC
                        + APPOINTMENT_START_TIME_DESC
                        + APPOINTMENT_END_TIME_DESC,
                Nric.MESSAGE_CONSTRAINTS);
        // invalid appointment start time
        assertParseFailure(parser, PATIENT_NRIC_DESC
                        + DOCTOR_NRIC_DESC
                        + INVALID_APPOINTMENT_START_TIME_DESC
                        + APPOINTMENT_END_TIME_DESC,
                AppointmentStartTime.MESSAGE_CONSTRAINTS);
        // invalid appoinrment end time
        assertParseFailure(parser, PATIENT_NRIC_DESC
                        + DOCTOR_NRIC_DESC
                        + APPOINTMENT_START_TIME_DESC
                        + INVALID_APPOINTMENT_END_TIME_DESC,
                AppointmentEndTime.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_PATIENT_NRIC_DESC
                        + DOCTOR_NRIC_DESC
                        + INVALID_APPOINTMENT_START_TIME_DESC
                        + APPOINTMENT_END_TIME_DESC,
                Nric.MESSAGE_CONSTRAINTS);
        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY
                        + PATIENT_NRIC_DESC
                        + DOCTOR_NRIC_DESC
                        + APPOINTMENT_START_TIME_DESC
                        + APPOINTMENT_END_TIME_DESC,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAppointmentCommand.MESSAGE_USAGE));
    }
}
