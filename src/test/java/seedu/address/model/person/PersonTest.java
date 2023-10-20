package seedu.address.model.person;

import org.junit.jupiter.api.Test;
import seedu.address.testutil.PersonUtil;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.testutil.TypicalPatient.BOB;

public class PersonTest {
    private final Name ALICE_NAME = new Name(PersonUtil.ALICE_NAME);
    private final Name BENSON_NAME = new Name(PersonUtil.BENSON_NAME);
    private final Nric ALICE_NRIC = new Nric(PersonUtil.ALICE_NRIC);
    private final Nric BENSON_NRIC = new Nric(PersonUtil.BENSON_NRIC);

    private final PersonStub ALICE = new PersonStub(ALICE_NAME, ALICE_NRIC);

    private final PersonStub ALICE_COPY = new PersonStub(ALICE_NAME, ALICE_NRIC);

    private final PersonStub BENSON = new PersonStub(BENSON_NAME, BENSON_NRIC);

    private final PersonStub ALICE_WITH_BENSON_NRIC = new PersonStub(ALICE_NAME, BENSON_NRIC);

    private final PersonStub BENSON_WITH_ALICE_NRIC = new PersonStub(BENSON_NAME, ALICE_NRIC);

    @Test
    public void getName() {
        assertEquals(ALICE.getName(), ALICE_NAME);
        assertNotEquals(BENSON.getName(), ALICE_NAME);
    }

    @Test
    public void getNric() {
        assertEquals(ALICE.getNric(), ALICE_NRIC);
        assertNotEquals(BENSON.getName(), ALICE_NAME);
    }

    @Test
    public void isSame() {
        // same object -> returns true
        assertTrue(ALICE.isSame(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSame(null));

        // same NRIC, all other attributes different -> returns true
        assertTrue(ALICE.isSame(BENSON_WITH_ALICE_NRIC));

        // different NRIC, all other attributes same -> returns false
        assertFalse(ALICE.isSame(ALICE_WITH_BENSON_NRIC));
    }

    @Test
    public void equals() {
        // same values -> returns true
        assertTrue(ALICE.equals(ALICE_COPY));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different person -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        assertFalse(ALICE.equals(BENSON_WITH_ALICE_NRIC));

        // different nric -> returns false
        assertFalse(ALICE.equals(ALICE_WITH_BENSON_NRIC));
    }

    @Test
    public void hashode() {
        assertEquals(ALICE.hashCode(), Objects.hash(
                new Name(PersonUtil.ALICE_NAME),
                new Nric(PersonUtil.ALICE_NRIC)));
    }
}

class PersonStub extends Person {
    public PersonStub(Name name, Nric nric) {
        super(name, nric);
    }
}
