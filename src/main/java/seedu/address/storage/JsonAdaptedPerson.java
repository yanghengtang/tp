package seedu.address.storage;

import java.util.List;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;

public abstract class JsonAdaptedPerson extends JsonAdaptedData {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "%s field is missing!";

    private final String nric;
    private final String name;

    /**
     * Constructor used when loading data from file.
     */
    public JsonAdaptedPerson(String nric, String name, String remark, List<JsonAdaptedTag> tags) {
        super(remark, tags);
        this.name = name;
        this.nric = nric;
    }

    /**
     * Constructor used when saving data to file.
     */
    public JsonAdaptedPerson(Person source) {
        super(source);
        nric = source.getNric().nric;
        name = source.getName().fullName;
    }

    /**
     * Validates and returns a {@code Name}.
     */
    public Name getModelName() throws IllegalValueException {
        if (this.name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(this.name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(this.name);
    }

    /**
     * Validates and returns a {@code Nric}.
     */
    public Nric getModelNric() throws IllegalValueException {
        if (this.nric == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Nric.class.getSimpleName()));
        }
        if (!Nric.isValidNric(this.nric)) {
            throw new IllegalValueException(Nric.MESSAGE_CONSTRAINTS);
        }
        return new Nric(this.nric);
    }
}
