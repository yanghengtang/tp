package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's NRIC in the system.
 * Guarantees: immutable; is valid as declared in {@link #isValidNric(String)}
 */
public class Nric implements Comparable<Nric> {
    public static final String MESSAGE_CONSTRAINTS =
            "NRIC should only contain alphanumeric characters and it should not be blank";

    /*
     * The first character of the NRIC must either be 'S', 'T', 'F', 'G' or 'M',
     * followed by 7 digits and ending with a uppercase alphabet.
     */
    public static final String VALIDATION_REGEX = "[STFGM]\\d{7}[A-Z]";

    public final String nric;

    /**
     * Constructs a {@code Nric}.
     *
     * @param nric A valid NRIC.
     */
    public Nric(String nric) {
        requireNonNull(nric);
        String uppercaseNric = nric.toUpperCase();
        checkArgument(isValidNric(uppercaseNric), MESSAGE_CONSTRAINTS);
        this.nric = uppercaseNric;
    }

    /**
     * Returns true if a given string is a valid NRIC.
     */
    public static boolean isValidNric(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return nric;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Nric)) {
            return false;
        }

        Nric otherNric = (Nric) other;
        return nric.equals(otherNric.nric);
    }

    @Override
    public int hashCode() {
        return nric.hashCode();
    }

    @Override
    public int compareTo(Nric nric) {
        return this.nric.compareTo(nric.nric);
    }
}
