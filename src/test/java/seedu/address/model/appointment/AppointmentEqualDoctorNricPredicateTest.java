package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPatient.ALICE_NRIC;
import static seedu.address.testutil.TypicalPatient.BENSON_NRIC;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Nric;
import seedu.address.testutil.AppointmentBuilder;

public class AppointmentEqualDoctorNricPredicateTest {

    @Test
    public void equals() {
        Nric firstDoctorNric = new Nric(ALICE_NRIC);
        Nric secondDoctorNric = new Nric(BENSON_NRIC);

        AppointmentEqualDoctorNricPredicate firstPredicate =
                new AppointmentEqualDoctorNricPredicate(firstDoctorNric);
        AppointmentEqualDoctorNricPredicate secondPredicate =
                new AppointmentEqualDoctorNricPredicate(secondDoctorNric);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        AppointmentEqualDoctorNricPredicate firstPredicateCopy =
                new AppointmentEqualDoctorNricPredicate(firstDoctorNric);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different doctor nric -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_appointmentEqualDoctorNric_returnsTrue() {
        // Matching DoctorNric
        AppointmentEqualDoctorNricPredicate predicate =
                new AppointmentEqualDoctorNricPredicate(new Nric(ALICE_NRIC));
        assertTrue(predicate.test(new AppointmentBuilder()
                .withDoctorNric(ALICE_NRIC)
                .build()));

        // Mixed case Nric
        predicate = new AppointmentEqualDoctorNricPredicate(new Nric("t0123456g"));
        assertTrue(predicate.test(new AppointmentBuilder()
                .withDoctorNric("T0123456G")
                .build()));
    }

    @Test
    public void test_appointmentDoesNotHaveEqualDoctorNric_returnsFalse() {
        // Non matching nric
        AppointmentEqualDoctorNricPredicate predicate =
                new AppointmentEqualDoctorNricPredicate(new Nric(ALICE_NRIC));
        assertFalse(predicate.test(new AppointmentBuilder()
                .withDoctorNric(BENSON_NRIC)
                .build()));

        // nric match patient nric, but does not doctor nric
        predicate = new AppointmentEqualDoctorNricPredicate(new Nric(ALICE_NRIC));
        assertFalse(predicate.test(new AppointmentBuilder()
                .withDoctorNric(BENSON_NRIC)
                .withPatientNric(ALICE_NRIC)
                .build()));
    }

    @Test
    public void toStringMethod() {
        Nric nric = new Nric(ALICE_NRIC);
        AppointmentEqualDoctorNricPredicate predicate = new AppointmentEqualDoctorNricPredicate(nric);

        String expected = AppointmentEqualDoctorNricPredicate.class.getCanonicalName() + "{nric=" + nric + "}";
        assertEquals(expected, predicate.toString());
    }
}
