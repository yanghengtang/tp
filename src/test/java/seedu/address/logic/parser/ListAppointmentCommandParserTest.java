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
import static seedu.address.model.NewModel.PREDICATE_SHOW_ALL_APPOINTMENTS;
import static seedu.address.testutil.TypicalAppointment.APPOINTMENT_7;
import static seedu.address.testutil.TypicalPatient.ALICE_NRIC;
import static seedu.address.testutil.TypicalPatient.BENSON_NRIC;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddAppointmentCommand;
import seedu.address.logic.commands.ListAppointmentCommand;
import seedu.address.model.appointment.*;
import seedu.address.model.person.Nric;
import seedu.address.testutil.AppointmentBuilder;

import java.util.function.Predicate;

public class ListAppointmentCommandParserTest {
    private ListAppointmentCommandParser parser = new ListAppointmentCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        AppointmentEqualPatientNricPredicate patientPredicate =
                new AppointmentEqualPatientNricPredicate(new Nric(ALICE_NRIC));
        AppointmentEqualDoctorNricPredicate doctorPredicate =
                new AppointmentEqualDoctorNricPredicate(new Nric(BENSON_NRIC));

        AppointmentFilterByNricPredicate expectedNricPredicate =
                new AppointmentFilterByNricPredicate(patientPredicate, doctorPredicate);

        assertParseSuccess(parser, "\t  \r  \n "
                + "pic\\T0123456J "
                + "dic\\S2936283D", new ListAppointmentCommand(expectedNricPredicate));

    }

    @Test
    public void parse_doctorNricFieldMissing_success() {
        AppointmentEqualPatientNricPredicate patientPredicate =
                new AppointmentEqualPatientNricPredicate(new Nric(ALICE_NRIC));
        Predicate<Appointment> doctorPredicate = PREDICATE_SHOW_ALL_APPOINTMENTS;
        AppointmentFilterByNricPredicate expectedNricPredicate =
                new AppointmentFilterByNricPredicate(patientPredicate, doctorPredicate);

        assertParseSuccess(parser, "\t  \r  \n "
                + "pic\\T0123456J ", new ListAppointmentCommand(expectedNricPredicate));
    }

    @Test
    public void parse_patientNricFieldMissing_success() {
        Predicate<Appointment> patientPredicate = PREDICATE_SHOW_ALL_APPOINTMENTS;
        Predicate<Appointment> doctorPredicate = new AppointmentEqualDoctorNricPredicate(new Nric(ALICE_NRIC));
        AppointmentFilterByNricPredicate expectedNricPredicate =
                new AppointmentFilterByNricPredicate(patientPredicate, doctorPredicate);

        assertParseSuccess(parser, "\t  \r  \n "
                + "dic\\T0123456J ", new ListAppointmentCommand(expectedNricPredicate));
    }

    @Test
    public void parse_patientNricFieldsMissing_() {
        Predicate<Appointment> patientPredicate = PREDICATE_SHOW_ALL_APPOINTMENTS;
        Predicate<Appointment> doctorPredicate = new AppointmentEqualDoctorNricPredicate(new Nric(ALICE_NRIC));
        AppointmentFilterByNricPredicate expectedNricPredicate =
                new AppointmentFilterByNricPredicate(patientPredicate, doctorPredicate);

        assertParseSuccess(parser, "\t  \r  \n "
                + "dic\\T0123456J ", new ListAppointmentCommand(expectedNricPredicate));
    }

    @Test
    public void parse_allFieldsMissing_success() {
        assertParseSuccess(parser, "\t  \r  \n ", new ListAppointmentCommand());
    }
    @Test
    public void parse_invalidValue_failure() {
        // invalid patient nric
        assertParseFailure(parser, "\t  \r  \n "
                + "pic\\T0123456JJ "
                + "dic\\S2936283D", Nric.MESSAGE_CONSTRAINTS);

        // invalid doctor nric
        assertParseFailure(parser, "\t  \r  \n "
                + "pic\\T0123456J "
                + "dic\\S2936283DD", Nric.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY
                + "pic\\T0123456J "
                + "dic\\S2936283D",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListAppointmentCommand.MESSAGE_USAGE));
    }
}
