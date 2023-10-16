package seedu.address.model.person;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.doctor.Doctor;

/**
 * Tests that a {@code Doctor}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordsDoctorPredicate implements Predicate<Doctor> {
    private final List<String> keywords;

    public NameContainsKeywordsDoctorPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Doctor doctor) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(doctor.getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof NameContainsKeywordsDoctorPredicate)) {
            return false;
        }

        NameContainsKeywordsDoctorPredicate otherNameContainsKeywordsPredicate =
                (NameContainsKeywordsDoctorPredicate) other;
        return keywords.equals(otherNameContainsKeywordsPredicate.keywords);
    }
    @Override
    public int hashCode() {
        return Objects.hash(this);
    }
    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
