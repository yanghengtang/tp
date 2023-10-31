package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PATIENT_TAG_1;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddPatientTagCommand;
import seedu.address.model.tag.Tag;

public class AddPatientTagCommandParserTest {
    private AddPatientTagCommandParser parser = new AddPatientTagCommandParser();
    @Test
    public void parse_validArgs_returnsPatientRemarkCommand() {
        assertParseSuccess(parser, "1 t\\diabetes",
                new AddPatientTagCommand(INDEX_FIRST_PERSON,
                        new Tag(VALID_PATIENT_TAG_1)));
    }

    @Test
    public void parse_noRemark_returnsPatientRemarkCommand() {
        assertParseFailure(parser, "1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        AddPatientTagCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidIndex_throwsParseException() {
        assertParseFailure(parser, "a t\\diabetes",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        AddPatientTagCommand.MESSAGE_USAGE));
    }
}
