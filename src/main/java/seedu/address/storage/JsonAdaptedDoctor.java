package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Phone;
import seedu.address.model.person.doctor.Doctor;

/**
 * Jackson-friendly version of {@link Doctor}.
 */
class JsonAdaptedDoctor {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Doctor's %s field is missing!";

    private final String nric;
    private final String name;

    /**
     * Constructs a {@code JsonAdaptedDoctor} with the given doctor details.
     */
    @JsonCreator
    public JsonAdaptedDoctor(@JsonProperty("nric") String nric, @JsonProperty("name") String name) {
        this.name = name;
        this.nric = nric;
    }

    /**
     * Converts a given {@code Doctor} into this class for Jackson use.
     */
    public JsonAdaptedDoctor(Doctor source) {
        nric = source.getNric().nric;
        name = source.getName().fullName;
    }

    /**
     * Converts this Jackson-friendly adapted doctor object into the model's {@code Doctor} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted doctor.
     */
    public Doctor toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (nric == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Nric.class.getSimpleName()));
        }
        if (!Nric.isValidNric(nric)) {
            throw new IllegalValueException(Nric.MESSAGE_CONSTRAINTS);
        }
        final Nric modelNric = new Nric(nric);

        return new Doctor(modelName, modelNric);
    }

}
