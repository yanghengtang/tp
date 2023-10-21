package seedu.address.model;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashSet;

import seedu.address.model.remark.Remark;
import seedu.address.model.tag.Tag;

/**
 * A common class for classes that have remarks and tags to inherit from.
 * Ensures remark and tags are immutable.
 */
public abstract class Data implements Listable {
    private final Remark remark;

    private final HashSet<Tag> tags;

    /**
     * Default constructor for Data which takes in no arguments.
     * {@Code remark} will be an empty string and {@Code tags} will be a empty set by default.
     */
    public Data() {
        this.remark = new Remark("");
        this.tags = new HashSet<>();
    }

    /**
     * Constructor for Data.
     * @param remark remark for the Data object
     * @param tags tags for the Data object
     */
    public Data(Remark remark, HashSet<? extends Tag> tags) {
        requireAllNonNull(remark, tags);
        this.remark = remark;
        this.tags = (HashSet<Tag>) tags.clone();
    }

    public final Remark getRemark() {
        return this.remark;
    }

    public final HashSet<Tag> getTags() {
        return (HashSet<Tag>) this.tags.clone();
    }
}
