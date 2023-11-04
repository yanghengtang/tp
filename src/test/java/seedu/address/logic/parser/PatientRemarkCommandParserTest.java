package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_1;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.PatientRemarkCommand;
import seedu.address.model.remark.Remark;

public class PatientRemarkCommandParserTest {
    private PatientRemarkCommandParser parser = new PatientRemarkCommandParser();
    @Test
    public void parse_validArgs_returnsPatientRemarkCommand() {
        assertParseSuccess(parser, "1 r\\Patient to follow up in 1 month",
                new PatientRemarkCommand(INDEX_FIRST_PERSON,
                        new Remark(VALID_REMARK_1)));
    }

    @Test
    public void parse_noRemark_returnsPatientRemarkCommand() {
        assertParseSuccess(parser, "1",
                new PatientRemarkCommand(INDEX_FIRST_PERSON,
                        new Remark("")));
    }

    @Test
    public void parse_invalidIndex_throwsParseException() {
        assertParseFailure(parser, "a r\\Patient to follow up in 1 month",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        PatientRemarkCommand.MESSAGE_USAGE));
    }
}
