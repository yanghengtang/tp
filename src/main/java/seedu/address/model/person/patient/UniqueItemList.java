package seedu.address.model.person.patient;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.Listable;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;

import java.util.Iterator;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * A list of Listables that enforces uniqueness between its elements and does not allow nulls.
 * A Listable is considered unique by comparing using {@code Listable#isSame(Listable)}. As such, adding and updating of
 * items uses Listable#isSame(Listable) for equality so as to ensure that the item being added or updated is
 * unique in terms of identity in the UniqueItemList. However, the removal of a item uses Listable#equals(Object) so
 * as to ensure that the person with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Listable#isSame(Listable)
 */
public class UniqueItemList<S extends Listable> implements Iterable<S> {
    private final ObservableList<S> internalList = FXCollections.observableArrayList();
    private final ObservableList<S> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent item as the given argument.
     */
    public boolean contains(S toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSame);
    }

    /**
     * Adds a item to the list.
     * The item must not already exist in the list.
     */
    public void add(S toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            // TODO: update the exception thrown
            throw new DuplicatePersonException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the item {@code target} in the list with {@code editedItem}.
     * {@code target} must exist in the list.
     * The item identity of {@code editedItem} must not be the same as another existing item in the list.
     */
    public void setItem(S target, S editedItem) {
        requireAllNonNull(target, editedItem);

        int index = internalList.indexOf(target);
        if (index == -1) {
            // TODO: update the exception thrown
            throw new PersonNotFoundException();
        }

        if (!target.isSame(editedItem) && contains(editedItem)) {
            // TODO: update the exception thrown
            throw new DuplicatePersonException();
        }

        internalList.set(index, editedItem);
    }

    /**
     * Removes the equivalent item from the list.
     * The item must exist in the list.
     */
    public void remove(S toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PersonNotFoundException();
        }
    }

    public void setItems(UniqueItemList<S> replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code items}.
     * {@code items} must not contain duplicate items.
     */
    public void setPersons(List<S> items) {
        requireAllNonNull(items);
        if (!itemsAreUnique(items)) {
            throw new DuplicatePersonException();
        }

        internalList.setAll(items);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<S> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<S> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniqueItemList<?>)) {
            return false;
        }

        UniqueItemList<?> otherUniqueItemList = (UniqueItemList<?>) other;
        return internalList.equals(otherUniqueItemList.internalList);
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public String toString() {
        return internalList.toString();
    }

    /**
     * Returns true if {@code items} contains only unique items.
     */
    private boolean itemsAreUnique(List<S> items) {
        for (int i = 0; i < items.size() - 1; i++) {
            for (int j = i + 1; j < items.size(); j++) {
                if (items.get(i).isSame(items.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
