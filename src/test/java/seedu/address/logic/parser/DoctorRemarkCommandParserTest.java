package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_3;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_DOCTOR;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DoctorRemarkCommand;
import seedu.address.model.remark.Remark;

public class DoctorRemarkCommandParserTest {
    private DoctorRemarkCommandParser parser = new DoctorRemarkCommandParser();
    @Test
    public void parse_validArgs_returnsDoctorRemarkCommand() {
        assertParseSuccess(parser, "1 r\\Doctor to be back by 30/12/2023",
                new DoctorRemarkCommand(INDEX_FIRST_DOCTOR,
                        new Remark(VALID_REMARK_3)));
    }

    @Test
    public void parse_noRemark_returnsDoctorRemarkCommand() {
        assertParseSuccess(parser, "1",
                new DoctorRemarkCommand(INDEX_FIRST_DOCTOR,
                        new Remark("")));
    }

    @Test
    public void parse_invalidIndex_throwsParseException() {
        assertParseFailure(parser, "a r\\Doctor to be back by 30/12/2023",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        DoctorRemarkCommand.MESSAGE_USAGE));
    }
}
