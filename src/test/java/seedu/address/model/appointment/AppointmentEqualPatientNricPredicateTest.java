package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.PersonUtil.ALICE_NRIC;
import static seedu.address.testutil.PersonUtil.BENSON_NRIC;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Nric;
import seedu.address.testutil.AppointmentBuilder;

public class AppointmentEqualPatientNricPredicateTest {

    @Test
    public void equals() {
        Nric firstPatientNric = new Nric(ALICE_NRIC);
        Nric secondPatientNric = new Nric(BENSON_NRIC);

        AppointmentEqualPatientNricPredicate firstPredicate =
                new AppointmentEqualPatientNricPredicate(firstPatientNric);
        AppointmentEqualPatientNricPredicate secondPredicate =
                new AppointmentEqualPatientNricPredicate(secondPatientNric);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        AppointmentEqualPatientNricPredicate firstPredicateCopy =
                new AppointmentEqualPatientNricPredicate(firstPatientNric);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different patient nric -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_appointmentEqualPatientNric_returnsTrue() {
        // Matching Patient Nric
        AppointmentEqualPatientNricPredicate predicate =
                new AppointmentEqualPatientNricPredicate(new Nric(BENSON_NRIC));
        assertTrue(predicate.test(new AppointmentBuilder()
                .withPatientNric(BENSON_NRIC)
                .build()));

        // Mixed case Nric
        predicate = new AppointmentEqualPatientNricPredicate(new Nric("t0123456g"));
        assertTrue(predicate.test(new AppointmentBuilder()
                .withPatientNric("T0123456G")
                .build()));
    }

    @Test
    public void test_appointmentDoesNotHaveEqualPatientNric_returnsFalse() {
        // Non matching nric
        AppointmentEqualPatientNricPredicate predicate =
                new AppointmentEqualPatientNricPredicate(new Nric(ALICE_NRIC));
        assertFalse(predicate.test(new AppointmentBuilder()
                .withPatientNric(BENSON_NRIC)
                .build()));

        // nric match doctor nric, but does not matching patient nric
        predicate = new AppointmentEqualPatientNricPredicate(new Nric(ALICE_NRIC));
        assertFalse(predicate.test(new AppointmentBuilder()
                .withPatientNric(BENSON_NRIC)
                .withDoctorNric(ALICE_NRIC)
                .build()));
    }

    @Test
    public void toStringMethod() {
        Nric nric = new Nric(ALICE_NRIC);
        AppointmentEqualPatientNricPredicate predicate = new AppointmentEqualPatientNricPredicate(nric);

        String expected = AppointmentEqualPatientNricPredicate.class.getCanonicalName() + "{nric=" + nric + "}";
        assertEquals(expected, predicate.toString());
    }
}
