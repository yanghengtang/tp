package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.DoctorBuilder;

public class NameContainsKeywordsDoctorPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateDoctorKeywordList = Collections.singletonList("first");
        List<String> secondPredicateDoctorKeywordList = Arrays.asList("first", "second");

        NameContainsKeywordsDoctorPredicate firstPredicate =
                new NameContainsKeywordsDoctorPredicate(firstPredicateDoctorKeywordList);
        NameContainsKeywordsDoctorPredicate secondPredicate =
                new NameContainsKeywordsDoctorPredicate(secondPredicateDoctorKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        NameContainsKeywordsDoctorPredicate firstPredicateCopy =
                new NameContainsKeywordsDoctorPredicate(firstPredicateDoctorKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different doctor -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        NameContainsKeywordsDoctorPredicate predicate =
                new NameContainsKeywordsDoctorPredicate(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new DoctorBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new NameContainsKeywordsDoctorPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new DoctorBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        predicate = new NameContainsKeywordsDoctorPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new DoctorBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new NameContainsKeywordsDoctorPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new DoctorBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        NameContainsKeywordsDoctorPredicate predicate =
                new NameContainsKeywordsDoctorPredicate(Collections.emptyList());
        assertFalse(predicate.test(new DoctorBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new NameContainsKeywordsDoctorPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new DoctorBuilder().withName("Alice Bob").build()));

        // Keywords match nric, but does not match name
        predicate = new NameContainsKeywordsDoctorPredicate(Arrays.asList("G4123573C"));
        assertFalse(predicate.test(new DoctorBuilder().withName("Alice").build()));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        NameContainsKeywordsDoctorPredicate predicate = new NameContainsKeywordsDoctorPredicate(keywords);

        String expected = NameContainsKeywordsDoctorPredicate.class.getCanonicalName() + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }

    @Test
    public void hashcode() {
        List<String> keywords = List.of("keyword1", "keyword2");

        // same value -> return same hashcode
        NameContainsKeywordsDoctorPredicate command = new NameContainsKeywordsDoctorPredicate(keywords);
        assertEquals(command.hashCode(), Objects.hash(keywords));

        List<String> keywords2 = List.of("keyword1", "keyword3");

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(command.hashCode(), Objects.hash(keywords2));

    }

}
