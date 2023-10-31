package seedu.address.model.person.patient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.testutil.TypicalPatient.ALICE;
import static seedu.address.testutil.TypicalPatient.ALICE_WITH_REMARKS;
import static seedu.address.testutil.TypicalPatient.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PatientBuilder;

public class PatientTest {
    @Test
    public void equals() {
        // same values -> returns true
        Patient aliceCopy = new PatientBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different person -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Patient editedAlice = new PatientBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new PatientBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different name -> returns false
        editedAlice = new PatientBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different NRIC -> returns false
        editedAlice = new PatientBuilder(ALICE).withNric(VALID_NRIC_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // same NRIC, different remarks -> return true
        assertTrue(ALICE.isSame(ALICE_WITH_REMARKS));
    }

    @Test
    public void toStringMethod() {
        String expected = Patient.class.getCanonicalName()
                + "{name=" + ALICE_WITH_REMARKS.getName()
                + ", phone=" + ALICE_WITH_REMARKS.getPhone()
                + ", nric=" + ALICE_WITH_REMARKS.getNric()
                + ", remark=" + ALICE_WITH_REMARKS.getRemark()
                + "}";
        assertEquals(expected, ALICE_WITH_REMARKS.toString());
    }
}
