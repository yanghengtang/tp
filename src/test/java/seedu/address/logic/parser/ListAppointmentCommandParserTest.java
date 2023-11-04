package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_APPOINTMENTS;
import static seedu.address.testutil.PersonUtil.ALICE_NRIC;
import static seedu.address.testutil.PersonUtil.BENSON_NRIC;

import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ListAppointmentCommand;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentEqualDoctorNricPredicate;
import seedu.address.model.appointment.AppointmentEqualPatientNricPredicate;
import seedu.address.model.appointment.AppointmentFilterByNricPredicate;
import seedu.address.model.person.Nric;

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
