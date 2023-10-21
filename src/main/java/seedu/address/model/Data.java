package seedu.address.model;

import seedu.address.model.remark.Remark;
import seedu.address.model.tag.Tag;

import java.util.HashSet;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * A common class for classes that have remarks and tags to inherit from.
 * Ensures remark and tags are immutable.
 */
public abstract class Data implements Listable {
    private final Remark remark;

    private final HashSet<Tag> tags;

    public Data() {
        this.remark = new Remark("");
        this.tags = new HashSet<>();
    }

    public Data(Remark remark, HashSet<? extends Tag> tags) {
        requireAllNonNull(remark, tags);
        this.remark = remark;
        this.tags = (HashSet<Tag>) tags.clone();
    }

    public Remark getRemark() {
        return this.remark;
    }

    public HashSet<Tag> getTags() {
        return (HashSet<Tag>) this.tags.clone();
    }
}
