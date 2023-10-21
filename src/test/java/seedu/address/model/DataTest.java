package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.address.model.remark.Remark;
import seedu.address.model.tag.Tag;

public class DataTest {
    private static final Remark VALID_REMARK = new Remark("Allergic to sunlight");
    private static final Remark EMPTY_REMARK = new Remark("");
    private static final Tag DEPRESSION_TAG = new Tag("Depression");
    private static final Tag PARACETAMOL_TAG = new Tag("Paracetamol");
    private static final Tag PEDIATRICIAN_TAG = new Tag("Pediatrician");
    private static final HashSet<Tag> TAGS = new HashSet<>();
    private static final Data DATA_1 = new DataStub();
    private static final Data DATA_2 = new DataStub(VALID_REMARK, TAGS);

    @Test
    public void getRemark() {
        // no remark set -> return remark with empty string
        assertEquals(DATA_1.getRemark(), EMPTY_REMARK);

        // remark set -> returns the correct remark
        assertEquals(DATA_2.getRemark(), VALID_REMARK);
    }

    @Test
    public void getTags() {
        // Empty set of tags initialized
        assertEquals(DATA_1.getTags(), TAGS);

        // Same set of tags returned
        assertEquals(DATA_2.getTags(), TAGS);

        // changing the set of tags does not cause changes in Data
        TAGS.add(DEPRESSION_TAG);
        assertNotEquals(DATA_2.getTags(), TAGS);

        // creating a new Data object with the modified set of tags returns the modified set
        Data data3 = new DataStub(VALID_REMARK, TAGS);
        assertEquals(data3.getTags(), TAGS);

        // modifying tags retrived from Data does not modify the set of tags in Data
        HashSet<Tag> retrivedTags = data3.getTags();
        retrivedTags.add(PARACETAMOL_TAG);
        assertNotEquals(data3.getTags(), retrivedTags);
    }
}

/**
 * A Data stub that has isSame Failing.
 */
class DataStub extends Data {
    public DataStub() {
        super();
    }
    public DataStub(Remark remark, HashSet<? extends Tag> tags) {
        super(remark, tags);
    }

    @Override
    public boolean isSame(Listable otherListable) {
        throw new AssertionError("This method should not be called.");
    }
}
