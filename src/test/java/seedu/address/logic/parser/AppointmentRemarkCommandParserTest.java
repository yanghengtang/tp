package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_1;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AppointmentRemarkCommand;
import seedu.address.model.remark.Remark;

public class AppointmentRemarkCommandParserTest {
    private AppointmentRemarkCommandParser parser = new AppointmentRemarkCommandParser();
    @Test
    public void parse_validArgs_returnsAppointmentRemarkCommand() {
        assertParseSuccess(parser, "1 r\\Patient to follow up in 1 month",
                new AppointmentRemarkCommand(INDEX_FIRST_PERSON,
                        new Remark(VALID_REMARK_1)));
    }

    @Test
    public void parse_noRemark_returnsAppointmentRemarkCommand() {
        assertParseSuccess(parser, "1",
                new AppointmentRemarkCommand(INDEX_FIRST_PERSON,
                        new Remark("")));
    }

    @Test
    public void parse_invalidIndex_throwsParseException() {
        assertParseFailure(parser, "a r\\Patient to follow up in 1 month",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        AppointmentRemarkCommand.MESSAGE_USAGE));
    }
}
