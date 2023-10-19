package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.NewCommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.NewCommandTestUtil.INVALID_NRIC_DESC;
import static seedu.address.logic.commands.NewCommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.NewCommandTestUtil.NRIC_DESC_AMY;
import static seedu.address.logic.commands.NewCommandTestUtil.NRIC_DESC_BOB;
import static seedu.address.logic.commands.NewCommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.NewCommandTestUtil.VALID_NRIC_BOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.NewCommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.NewCommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_DOCTOR;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_DOCTOR;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_DOCTOR;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditDoctorCommand;
import seedu.address.logic.commands.EditDoctorCommand.EditDoctorDescriptor;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.testutil.EditDoctorDescriptorBuilder;
public class EditDoctorCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditDoctorCommand.MESSAGE_USAGE);

    private EditDoctorCommandParser parser = new EditDoctorCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditDoctorCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_NRIC_DESC, Nric.MESSAGE_CONSTRAINTS); // invalid nric

        // invalid nric followed by valid index
        assertParseFailure(parser, "1" + INVALID_NRIC_DESC , Nric.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_NRIC_DESC , Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_DOCTOR;
        String userInput = targetIndex.getOneBased() + NRIC_DESC_BOB;

        EditDoctorDescriptor descriptor = new EditDoctorDescriptorBuilder().withNric(VALID_NRIC_BOB).build();
        EditDoctorCommand expectedCommand = new EditDoctorCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_DOCTOR;
        String userInput = targetIndex.getOneBased() + NRIC_DESC_BOB;

        EditDoctorDescriptor descriptor = new EditDoctorDescriptorBuilder().withNric(VALID_NRIC_BOB).build();
        EditDoctorCommand expectedCommand = new EditDoctorCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_DOCTOR;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY;
        EditDoctorDescriptor descriptor = new EditDoctorDescriptorBuilder().withName(VALID_NAME_AMY).build();
        EditDoctorCommand expectedCommand = new EditDoctorCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + NRIC_DESC_BOB;
        descriptor = new EditDoctorDescriptorBuilder().withNric(VALID_NRIC_BOB).build();
        expectedCommand = new EditDoctorCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

    }

    @Test
    public void parse_multipleRepeatedFields_failure() {
        // More extensive testing of duplicate parameter detections is done in
        // AddCommandParserTest#parse_repeatedNonTagValue_failure()

        // valid followed by invalid
        Index targetIndex = INDEX_FIRST_DOCTOR;
        String userInput = targetIndex.getOneBased() + INVALID_NRIC_DESC + NRIC_DESC_BOB;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NRIC));

        // invalid followed by valid
        userInput = targetIndex.getOneBased() + NRIC_DESC_BOB + INVALID_NRIC_DESC;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NRIC));

        // multiple valid fields repeated
        userInput = targetIndex.getOneBased() + NRIC_DESC_AMY + NRIC_DESC_AMY;

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NRIC));

        // multiple invalid values
        userInput = targetIndex.getOneBased() + INVALID_NRIC_DESC + INVALID_NRIC_DESC;

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NRIC));
    }

}
