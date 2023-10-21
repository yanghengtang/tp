package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.Data;
import seedu.address.model.Listable;

/**
 * A parent class for all different types of people in the system.
 */
public abstract class Person extends Data {
    private final Name name;
    private final Nric nric;

    /**
     * Constructor for Person.
     * @param name name of the person.
     * @param nric nric of the person.
     */
    public Person(Name name, Nric nric) {
        requireAllNonNull(name, nric);
        this.name = name;
        this.nric = nric;
    }

    public Name getName() {
        return this.name;
    }

    public Nric getNric() {
        return nric;
    }

    /**
     * Returns true if both people have the same NRIC.
     * This defines a weaker notion of equality between two people.
     */
    public boolean isSame(Listable otherListable) {
        if (otherListable == this) {
            return true;
        }

        if (!(otherListable instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) otherListable;

        return otherPerson.nric.equals(nric);
    }

    /**
     * Returns true if both people have the same identity fields.
     * This defines a stronger notion of equality between two people.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return name.equals(otherPerson.name)
                && nric.equals(otherPerson.nric);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, nric);
    }
}
