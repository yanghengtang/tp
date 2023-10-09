package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NricTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Nric(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidNric = "";
        assertThrows(IllegalArgumentException.class, () -> new Nric(invalidNric));
    }

    @Test
    public void isValidNric() {
        // null NRIC
        assertThrows(NullPointerException.class, () -> Nric.isValidNric(null));

        // invalid NRIC
        assertFalse(Nric.isValidNric("")); // empty string
        assertFalse(Nric.isValidNric("S012345 A")); // contains space
        assertFalse(Nric.isValidNric("S012^456A")); // contains non-alphanumeric characters
        assertFalse(Nric.isValidNric("S01222456A")); // too many characters
        assertFalse(Nric.isValidNric("S01456A")); // too few characters
        assertFalse(Nric.isValidNric("A0123456A")); // invalid starting character
        assertFalse(Nric.isValidNric("s0123456A")); // lowercase starting character
        assertFalse(Nric.isValidNric("S0123456a")); // lowercase end character

        // valid NRIC
        assertTrue(Nric.isValidNric("S0123456A")); // starting with S
        assertTrue(Nric.isValidNric("T2414628F")); // starting with T
        assertTrue(Nric.isValidNric("G1238858J")); // starting with G
        assertTrue(Nric.isValidNric("F7423926D")); // starting with F
        assertTrue(Nric.isValidNric("M4812734E")); // starting with M
    }

    @Test
    public void equals() {
        Nric nric = new Nric("S0123456A");

        // same values -> returns true
        assertTrue(nric.equals(new Nric("S0123456A")));

        // same object -> returns true
        assertTrue(nric.equals(nric));

        // null -> returns false
        assertFalse(nric.equals(null));

        // different types -> returns false
        assertFalse(nric.equals(5.0f));

        // different values -> returns false
        assertFalse(nric.equals(new Nric("T0987654Z")));
    }
}
