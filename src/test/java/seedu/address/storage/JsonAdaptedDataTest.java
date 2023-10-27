package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.model.DataTest.DEPRESSION_TAG;
import static seedu.address.model.DataTest.PEDIATRICIAN_TAG;
import static seedu.address.model.DataTest.VALID_REMARK;
import static seedu.address.model.DataTest.VALID_REMARK_STRING;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.Data;
import seedu.address.model.remark.Remark;
import seedu.address.model.tag.Tag;

public class JsonAdaptedDataTest {
    public static final JsonAdaptedTag TAG1 = new JsonAdaptedTag(DEPRESSION_TAG);
    public static final JsonAdaptedTag TAG2 = new JsonAdaptedTag(PEDIATRICIAN_TAG);
    public static final JsonAdaptedTag INVALID_TAG = new JsonAdaptedTag("%$#");
    public static final List<JsonAdaptedTag> TAG_LIST = new ArrayList<>(Arrays.asList(TAG1, TAG2));
    public static final Set<Tag> TAG_HASHSET = new HashSet<>(Arrays.asList(PEDIATRICIAN_TAG, DEPRESSION_TAG));
    public static final List<JsonAdaptedTag> INVALID_TAG_LIST = new ArrayList<>(Arrays.asList(TAG1, TAG2, INVALID_TAG));

    @Test
    public void getModelRemark_validRemarks_returnsRemark() throws IllegalValueException {
        JsonAdaptedDataStub data = new JsonAdaptedDataStub(VALID_REMARK_STRING, TAG_LIST);
        assertEquals(data.getModelRemark(), VALID_REMARK);
    }

    @Test
    public void getModelRemark_nullRemarks_throwsIllegalValueException() {
        JsonAdaptedDataStub data = new JsonAdaptedDataStub(null, TAG_LIST);
        String msg = String.format(JsonAdaptedData.MISSING_FIELD_MESSAGE_FORMAT, Remark.class.getSimpleName());
        assertThrows(IllegalValueException.class, () -> data.getModelRemark(), msg);
    }

    @Test
    public void getModelTags_validTags_returnsTagsHashSet() throws IllegalValueException {
        JsonAdaptedDataStub data = new JsonAdaptedDataStub(VALID_REMARK_STRING, TAG_LIST);
        assertEquals(data.getModelTags(), TAG_HASHSET);
    }

    @Test
    public void getModelTags_invalidTags_throwsIllegalValueException() {
        JsonAdaptedDataStub data = new JsonAdaptedDataStub(VALID_REMARK_STRING, INVALID_TAG_LIST);
        String msg = Tag.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, () -> data.getModelTags(), msg);
    }
}

class JsonAdaptedDataStub extends JsonAdaptedData {
    public JsonAdaptedDataStub(String remark, List<JsonAdaptedTag> tags) {
        super(remark, tags);
    }
    public JsonAdaptedDataStub(Data source) {
        super(source);
    }
}
