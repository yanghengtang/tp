package seedu.address.commons.util.lambdautil;

/**
 * A equivalent Interface for Functions that throws checked exceptions.
 * @param <T> Type of the input to the CheckedFunction.
 * @param <R> Type of the output of the CheckedFunction.
 */
@FunctionalInterface
public interface CheckedFunction<T, R> {
    R apply(T t) throws Exception;
}
