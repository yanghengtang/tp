package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.Data;
import seedu.address.model.remark.Remark;
import seedu.address.model.tag.Tag;

/**
 * A parallel class to {@link Data} for saving to/loading from file.
 * Handles and validates remark and tags.
 */
public abstract class JsonAdaptedData {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "%s field is missing!";

    private final String remark;
    private final List<JsonAdaptedTag> tags = new ArrayList<>();

    /**
     * Constructor used when loading data from file.
     */
    public JsonAdaptedData(String remark, List<JsonAdaptedTag> tags) {
        this.remark = remark;
        this.tags.addAll(tags);
    }

    /**
     * Constructor used when saving data to file.
     */
    public JsonAdaptedData(Data source) {
        this.remark = source.getRemark().remark;
        this.tags.addAll(source.getTags().stream().map(JsonAdaptedTag::new).collect(Collectors.toList()));
    }

    /**
     * Validates and returns a {@code Remark}.
     */
    public Remark getModelRemark() throws IllegalValueException {
        if (this.remark == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Remark.class.getSimpleName()));
        }
        if (!Remark.isValidRemark(this.remark)) {
            throw new IllegalValueException(Remark.MESSAGE_CONSTRAINTS);
        }
        return new Remark(this.remark);
    }

    /**
     * Validates and returns {@code HashSet} of {@code Tag}.
     */
    public HashSet<Tag> getModelTags() throws IllegalValueException {
        final List<Tag> dataTags = new ArrayList<>();
        for (JsonAdaptedTag tag : this.tags) {
            dataTags.add(tag.toModelType());
        }
        return new HashSet<Tag>(dataTags);
    }
}
