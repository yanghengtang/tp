package seedu.address.commons.util.LambdaUtil;

import java.util.function.Function;

public class CheckedFunctionUtil {
    public static <T, R> Function<T, R> Unchecked(CheckedFunction<T, R> checkedFunction) {
        return t -> {
            try {
                return checkedFunction.apply(t);
            } catch (Exception e) {
                throw new RuntimeException(e); // or custom subclass of RuntimeException
            }
        };
    }
}
