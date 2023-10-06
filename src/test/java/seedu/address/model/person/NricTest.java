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
        // null name
        assertThrows(NullPointerException.class, () -> Nric.isValidNric(null));

        // invalid name
        assertFalse(Nric.isValidNric("")); // empty string
        assertFalse(Nric.isValidNric(" ")); // spaces only
        assertFalse(Nric.isValidNric("^")); // only non-alphanumeric characters
        assertFalse(Nric.isValidNric("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(Nric.isValidNric("S0123456A")); // starting with S
        assertTrue(Nric.isValidNric("T2414628F")); // numbers only
        assertTrue(Nric.isValidNric("G1238858J")); // alphanumeric characters
        assertTrue(Nric.isValidNric("F7423926D")); // with capital letters
        assertTrue(Nric.isValidNric("M4812734E")); // long names
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
