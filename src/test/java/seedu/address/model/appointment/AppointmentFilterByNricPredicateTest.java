package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPatient.ALICE_NRIC;
import static seedu.address.testutil.TypicalPatient.BENSON_NRIC;
import static seedu.address.testutil.TypicalPatient.CARL_NRIC;
import static seedu.address.testutil.TypicalPatient.DANIEL_NRIC;

import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Nric;
import seedu.address.testutil.AppointmentBuilder;

public class AppointmentFilterByNricPredicateTest {

    @Test
    public void equals() {
        Predicate<Appointment> firstPatientNric = new AppointmentEqualPatientNricPredicate(new Nric(ALICE_NRIC));
        Predicate<Appointment> firstDoctorNric = new AppointmentEqualDoctorNricPredicate(new Nric(BENSON_NRIC));
        Predicate<Appointment> secondPatientNric = new AppointmentEqualPatientNricPredicate(new Nric(CARL_NRIC));
        Predicate<Appointment> secondDoctorNric = new AppointmentEqualDoctorNricPredicate(new Nric(DANIEL_NRIC));

        AppointmentFilterByNricPredicate firstPredicate =
                new AppointmentFilterByNricPredicate(firstPatientNric, firstDoctorNric);
        AppointmentFilterByNricPredicate secondPredicate =
                new AppointmentFilterByNricPredicate(secondPatientNric, secondDoctorNric);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        AppointmentFilterByNricPredicate firstPredicateCopy =
                new AppointmentFilterByNricPredicate(firstPatientNric, firstDoctorNric);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different values in predicates -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));

        // same patient predicate but different doctor predicate -> returns false
        AppointmentFilterByNricPredicate thirdPredicate =
                new AppointmentFilterByNricPredicate(firstPatientNric, secondDoctorNric);
        assertFalse(firstPredicate.equals(thirdPredicate));

        // same doctor predicate but different patient predicate -> returns false
        AppointmentFilterByNricPredicate fourthPredicate =
                new AppointmentFilterByNricPredicate(secondPatientNric, firstDoctorNric);
        assertFalse(firstPredicate.equals(fourthPredicate));
    }

    @Test
    public void test_appointmentEqualPatientandDoctorNric_returnsTrue() {
        // Matching Patient Nric
        AppointmentEqualPatientNricPredicate patientPredicate =
                new AppointmentEqualPatientNricPredicate(new Nric(ALICE_NRIC));
        AppointmentEqualDoctorNricPredicate doctorPredicate =
                new AppointmentEqualDoctorNricPredicate(new Nric(BENSON_NRIC));
        AppointmentFilterByNricPredicate predicate =
                new AppointmentFilterByNricPredicate(patientPredicate, doctorPredicate);
        assertTrue(predicate.test(new AppointmentBuilder()
                .withPatientNric(ALICE_NRIC)
                .withDoctorNric(BENSON_NRIC)
                .build()));
    }

    @Test
    public void test_appointmentDoesNotHaveEqualPatientNric_returnsFalse() {
        AppointmentEqualPatientNricPredicate patientPredicate =
                new AppointmentEqualPatientNricPredicate(new Nric(ALICE_NRIC));
        AppointmentEqualDoctorNricPredicate doctorPredicate =
                new AppointmentEqualDoctorNricPredicate(new Nric(BENSON_NRIC));
        AppointmentFilterByNricPredicate predicate =
                new AppointmentFilterByNricPredicate(patientPredicate, doctorPredicate);

        // Non-matching  patient nric
        assertFalse(predicate.test(new AppointmentBuilder()
                .withPatientNric(CARL_NRIC)
                .withDoctorNric(BENSON_NRIC)
                .build()));

        // Non-matching  doctor nric
        assertFalse(predicate.test(new AppointmentBuilder()
                .withPatientNric(ALICE_NRIC)
                .withDoctorNric(CARL_NRIC)
                .build()));

        // Non-matching doctor and patient nric
        assertFalse(predicate.test(new AppointmentBuilder()
                .withPatientNric(CARL_NRIC)
                .withDoctorNric(DANIEL_NRIC)
                .build()));
    }

    @Test
    public void toStringMethod() {
        Predicate<Appointment> patientPredicate = new AppointmentEqualPatientNricPredicate(new Nric(ALICE_NRIC));
        Predicate<Appointment> doctorPredicate = new AppointmentEqualDoctorNricPredicate(new Nric(BENSON_NRIC));

        AppointmentFilterByNricPredicate predicate = new AppointmentFilterByNricPredicate(patientPredicate,
                doctorPredicate);

        String expected = AppointmentFilterByNricPredicate.class.getCanonicalName()
                + "{patientPredicate=" + patientPredicate
                + ", doctorPredicate=" + doctorPredicate
                + "}";
        assertEquals(expected, predicate.toString());
    }
}
