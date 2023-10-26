package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.commons.util.lambdautil.CheckedFunctionUtil.unchecked;

import java.util.function.Function;

import org.junit.jupiter.api.Test;

import seedu.address.commons.util.lambdautil.CheckedFunction;
import seedu.address.logic.commands.exceptions.CommandException;

public class CheckedFunctionUtilTest {
    public static final CheckedFunction<Integer, Integer> FAILURE =
            i -> {throw new CommandException("Test");};
    public static final CheckedFunction<Integer, Integer> SUCCESS = i -> i;
    public static final Function<Integer, Integer> SUCCESS_EQUIVALENT = i -> i;

    @Test
    public void uncheck_test() {
        assertThrows(RuntimeException.class, () -> unchecked(FAILURE).apply(10));
        assertEquals(unchecked(SUCCESS).apply(10), SUCCESS_EQUIVALENT.apply(10));
    }
}
