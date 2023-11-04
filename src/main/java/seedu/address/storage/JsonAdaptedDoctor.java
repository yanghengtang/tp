package seedu.address.storage;

import java.util.HashSet;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.model.person.doctor.Doctor;
import seedu.address.model.remark.Remark;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Doctor}.
 */
class JsonAdaptedDoctor extends JsonAdaptedPerson {
    /**
     * Constructs a {@code JsonAdaptedDoctor} with the given doctor details.
     */
    @JsonCreator
    public JsonAdaptedDoctor(@JsonProperty("nric") String nric, @JsonProperty("name") String name,
                             @JsonProperty("remark") String remark, @JsonProperty("tags") List<JsonAdaptedTag> tags) {
        super(nric, name, remark, tags);
    }

    /**
     * Converts a given {@code Doctor} into this class for Jackson use.
     */
    public JsonAdaptedDoctor(Doctor source) {
        super(source);
    }

    /**
     * Converts this Jackson-friendly adapted doctor object into the model's {@code Doctor} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted doctor.
     */
    public Doctor toModelType() throws IllegalValueException {
        final Name modelName = getModelName();
        final Nric modelNric = getModelNric();
        final Remark modelRemark = getModelRemark();
        final HashSet<Tag> modelTags = getModelTags();

        return new Doctor(modelName, modelNric, modelRemark, modelTags);
    }
}
