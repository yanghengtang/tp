package seedu.address.logic.parser;


import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NRIC_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NRIC_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NRIC_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_BOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalDoctor.AMY;
import static seedu.address.testutil.TypicalDoctor.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddDoctorCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.model.person.doctor.Doctor;
import seedu.address.testutil.DoctorBuilder;



public class AddDoctorCommandParserTest {
    private AddDoctorCommandParser parser = new AddDoctorCommandParser();

    @Test
    public void parse_allFieldsPresent_success() throws CommandException {
        Doctor expectedDoctor = new DoctorBuilder(BOB).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE
                + NAME_DESC_BOB + NRIC_DESC_BOB, new AddDoctorCommand(expectedDoctor));


        // multiple tags - all accepted
        Doctor expectedDoctorMultipleTags = new DoctorBuilder(BOB).build();
        assertParseSuccess(parser,
                NAME_DESC_BOB + NRIC_DESC_BOB,
                new AddDoctorCommand(expectedDoctorMultipleTags));
    }

    @Test
    public void parse_repeatedNonTagValue_failure() throws CommandException {
        String validExpectedPersonString = NAME_DESC_BOB + NRIC_DESC_BOB;
        // multiple names
        assertParseFailure(parser, NAME_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // multiple emails
        assertParseFailure(parser, NRIC_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NRIC));

        // multiple fields repeated
        assertParseFailure(parser,
                validExpectedPersonString + NRIC_DESC_AMY + NAME_DESC_AMY
                        + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME, PREFIX_NRIC));

        // invalid value followed by valid value

        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid nric
        assertParseFailure(parser, INVALID_NRIC_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NRIC));

        // valid value followed by invalid value

        // invalid name
        assertParseFailure(parser, validExpectedPersonString + INVALID_NAME_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid nric
        assertParseFailure(parser, validExpectedPersonString + INVALID_NRIC_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NRIC));

    }

    @Test
    public void parse_optionalFieldsMissing_success() throws CommandException {
        // zero tags
        Doctor expectedDoctor = new DoctorBuilder(AMY).build();
        assertParseSuccess(parser, NAME_DESC_AMY + NRIC_DESC_AMY,
                new AddDoctorCommand(expectedDoctor));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() throws CommandException {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddDoctorCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + NRIC_DESC_BOB,
                expectedMessage);

        // missing nric prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_NRIC_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_NRIC_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() throws CommandException {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + NRIC_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // invalid nric
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_NRIC_DESC, Nric.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + INVALID_NRIC_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + NRIC_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddDoctorCommand.MESSAGE_USAGE));
    }
}
