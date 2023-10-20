package seedu.address.model;

import seedu.address.model.remark.Remark;
import seedu.address.model.tag.Tag;

import java.util.HashSet;
import java.util.Set;

import static java.util.Objects.requireNonNull;

/**
 * A common class for classes that have remarks and tags to inherit from.
 */
public abstract class Data implements Listable {
    private Remark remark = new Remark("");

    private Set<Tag> tags = new HashSet<>();

    public Remark getRemark() {
        return this.remark;
    }

    public Set<Tag> getTags() {
        return this.tags;
    }

    public void setRemark(Remark remark) {
        this.remark = remark;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public void addTags(Tag tag) {
        requireNonNull(tag);
        this.tags.add(tag);
    }

    public void removeTags (Tag... tags) {
        for (Tag tag : tags) {
            this.tags.remove(tag);
        }
    }
}
