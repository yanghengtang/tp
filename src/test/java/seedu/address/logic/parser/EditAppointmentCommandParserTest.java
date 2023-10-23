package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DOCTOR_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PATIENT_NRIC;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditAppointmentCommand;
import seedu.address.logic.commands.EditAppointmentCommand.EditAppointmentDescriptor;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.appointment.AppointmentEndTime;
import seedu.address.model.appointment.AppointmentStartTime;
import seedu.address.model.person.Nric;
import seedu.address.testutil.EditAppointmentDescriptorBuilder;

public class EditAppointmentCommandParserTest {


    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditAppointmentCommand.MESSAGE_USAGE);

    private EditAppointmentCommandParser parser = new EditAppointmentCommandParser();

    @Test
    public void parse_missingParts_failure() throws CommandException {
        // no index specified
        assertParseFailure(parser, "pic\\ T0123456J", MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditAppointmentCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() throws CommandException {
        // negative index
        assertParseFailure(parser, "-5" + "pic\\ T0123456J" , MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + "pic\\ T0123456J", MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() throws CommandException {
        assertParseFailure(parser, "1" + " pic\\T012#456J", Nric.MESSAGE_CONSTRAINTS); // invalid patient nric
        assertParseFailure(parser, "1" + " dic\\T012#456J", Nric.MESSAGE_CONSTRAINTS); // invalid doctor nric
        assertParseFailure(parser, "1" + " from\\14-01-2022 07:30",
                AppointmentStartTime.MESSAGE_CONSTRAINTS); // invalid start date
        assertParseFailure(parser,
                "1" + " to\\14-01-2022 07:30", AppointmentEndTime.MESSAGE_CONSTRAINTS); // invalid end date

        // invalid doctor nric followed by valid start time
        assertParseFailure(parser, "1" + " dic\\T012#456J" + "from\\2023-09-11 07:30", Nric.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + " dic\\T012#456J" + "from\\14-01-2022 07:30",
                Nric.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() throws CommandException {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + " pic\\T0232356N dic\\T0123456J "
                + "from\\2023-09-11 07:30 to\\2023-09-11 08:30";

        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder()
                .withPatientNric("T0232356N")
                .withDoctorNric("T0123456J")
                .withStartTime("2023-09-11 07:30")
                .withEndTime("2023-09-11 08:30")
                .build();
        EditAppointmentCommand expectedCommand = new EditAppointmentCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() throws CommandException {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased()
                + " from\\2023-09-11 07:30 to\\2023-09-11 08:30";

        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder()
                .withStartTime("2023-09-11 07:30")
                .withEndTime("2023-09-11 08:30")
                .build();
        EditAppointmentCommand expectedCommand = new EditAppointmentCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() throws CommandException {
        // patient nric
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + " pic\\T0232356N";
        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder()
                .withPatientNric("T0232356N").build();
        EditAppointmentCommand expectedCommand = new EditAppointmentCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // doctor nric
        targetIndex = INDEX_THIRD_PERSON;
        userInput = targetIndex.getOneBased() + " dic\\T0232356N";
        descriptor = new EditAppointmentDescriptorBuilder()
                .withDoctorNric("T0232356N").build();
        expectedCommand = new EditAppointmentCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // start time
        targetIndex = INDEX_THIRD_PERSON;
        userInput = targetIndex.getOneBased() + " from\\2023-09-11 07:30";
        descriptor = new EditAppointmentDescriptorBuilder()
                .withStartTime("2023-09-11 07:30").build();
        expectedCommand = new EditAppointmentCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // end time
        targetIndex = INDEX_THIRD_PERSON;
        userInput = targetIndex.getOneBased() + " to\\2023-09-11 08:30";
        descriptor = new EditAppointmentDescriptorBuilder()
                .withEndTime("2023-09-11 08:30").build();
        expectedCommand = new EditAppointmentCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_failure() throws CommandException {

        // valid followed by invalid
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + " pic\\T0232356N" + " pic\\T023#356N";

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PATIENT_NRIC));

        // invalid followed by valid
        userInput = targetIndex.getOneBased() + " pic\\T023#356N" + " pic\\T0232356N";

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PATIENT_NRIC));

        // mulltiple valid fields repeated
        userInput = targetIndex.getOneBased() + " pic\\T0232356N" + " pic\\T0242356N"
                + " dic\\T0232356N" + " dic\\T0242356N";

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PATIENT_NRIC, PREFIX_DOCTOR_NRIC));

        // multiple invalid values
        userInput = targetIndex.getOneBased() + " pic\\T0#32356N" + " pic\\T02#2356N"
                + " dic\\T0232#56N" + " dic\\T024#356N";

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PATIENT_NRIC, PREFIX_DOCTOR_NRIC));
    }
}
