package seedu.address.model;

/**
 * A interface for all classes that needs to be added to a UniqueList to implement.
 */
public interface Listable {
    /**
     * Checks if 2 Listables are the same. This method is separate from equals as it represents a weaker notion of
     * equality.
     *
     * @param otherListable Another Listable to compare the current instance to.
     * @return True if both listables are the same.
     */
    public boolean isSame(Listable otherListable);


}
