package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PatientBuilder;

public class NameContainsKeywordsPatientPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicatePatientKeywordList = Collections.singletonList("first");
        List<String> secondPredicatePatientKeywordList = Arrays.asList("first", "second");

        NameContainsKeywordsPatientPredicate firstPredicate =
                new NameContainsKeywordsPatientPredicate(firstPredicatePatientKeywordList);
        NameContainsKeywordsPatientPredicate secondPredicate =
                new NameContainsKeywordsPatientPredicate(secondPredicatePatientKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        NameContainsKeywordsPatientPredicate firstPredicateCopy =
                new NameContainsKeywordsPatientPredicate(firstPredicatePatientKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        NameContainsKeywordsPatientPredicate predicate =
                new NameContainsKeywordsPatientPredicate(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new PatientBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new NameContainsKeywordsPatientPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new PatientBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        predicate = new NameContainsKeywordsPatientPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new PatientBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new NameContainsKeywordsPatientPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new PatientBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        NameContainsKeywordsPatientPredicate predicate =
                new NameContainsKeywordsPatientPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PatientBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new NameContainsKeywordsPatientPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new PatientBuilder().withName("Alice Bob").build()));

        // Keywords match phone, but does not match name
        predicate = new NameContainsKeywordsPatientPredicate(Arrays.asList("12345"));
        assertFalse(predicate.test(new PatientBuilder().withName("Alice").withPhone("12345").build()));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        NameContainsKeywordsPatientPredicate predicate = new NameContainsKeywordsPatientPredicate(keywords);

        String expected = NameContainsKeywordsPatientPredicate.class.getCanonicalName() + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}
