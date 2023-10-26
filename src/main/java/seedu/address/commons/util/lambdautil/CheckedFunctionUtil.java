package seedu.address.commons.util.lambdautil;

import java.util.function.Function;

/**
 * This class handles the conversion of a CheckedFunction to a Function.
 */
public class CheckedFunctionUtil {
    /**
     * Converts a CheckedFunction to a Function.
     * Throws runtime error when a checked exception is thrown.
     * @param <T> Input type of the Function.
     * @param <R> Return type of the Function.
     * @param checkedFunction Function that throws a checked exception.
     * @return An equivalent Function to the CheckedFunction.
     */
    public static <T, R> Function<T, R> unchecked(CheckedFunction<T, R> checkedFunction) {
        return t -> {
            try {
                return checkedFunction.apply(t);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
}
