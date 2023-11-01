package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.model.DataTest.DERMATOLOGY_TAG_STRING;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteSpecialisationCommand;
import seedu.address.model.tag.Tag;


public class DeleteSpecialisationCommandParserTest {
    private DeleteSpecialisationCommandParser parser = new DeleteSpecialisationCommandParser();
    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "1 t\\Dermatology",
                new DeleteSpecialisationCommand(INDEX_FIRST_PERSON,
                        new Tag(DERMATOLOGY_TAG_STRING)));
    }

    @Test
    public void parse_noPrescription_throwsParseException() {
        assertParseFailure(parser, "1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        DeleteSpecialisationCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidIndex_throwsParseException() {
        assertParseFailure(parser, "a t\\Dermatology",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        DeleteSpecialisationCommand.MESSAGE_USAGE));
    }
}
