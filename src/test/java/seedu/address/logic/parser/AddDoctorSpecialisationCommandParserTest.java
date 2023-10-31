package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SPECIALISATION_1;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddDoctorSpecialisationCommand;
import seedu.address.model.tag.Tag;

public class AddDoctorSpecialisationCommandParserTest {
    private AddDoctorSpecialisationCommandParser parser = new AddDoctorSpecialisationCommandParser();
    @Test
    public void parse_validArgs_returnsAddDoctorSpecialisationCommand() {
        assertParseSuccess(parser, "1 t\\" + VALID_SPECIALISATION_1,
                new AddDoctorSpecialisationCommand(INDEX_FIRST_PERSON,
                        new Tag(VALID_SPECIALISATION_1)));
    }

    @Test
    public void parse_noPrescription_throwsParseException() {
        assertParseFailure(parser, "1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        AddDoctorSpecialisationCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidIndex_throwsParseException() {
        assertParseFailure(parser, "a t\\" + VALID_SPECIALISATION_1,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        AddDoctorSpecialisationCommand.MESSAGE_USAGE));
    }
}
