package seedu.address.commons.util.LambdaUtil;

@FunctionalInterface
public interface CheckedFunction<T, R> {
    R apply(T t) throws Exception;
}
