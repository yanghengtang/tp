package seedu.address.storage;

import org.junit.jupiter.api.Test;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedDoctor.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalDoctor.BENSON;

public class JsonAdaptedDoctorTest {
    private static final String INVALID_NRIC = "X0123456L";
    private static final String INVALID_NAME = "R@chel";

    private static final String VALID_NRIC = BENSON.getNric().toString();
    private static final String VALID_NAME = BENSON.getName().toString();

    @Test
    public void toModelType_validDoctorDetails_returnsDoctor() throws Exception {
        JsonAdaptedDoctor doctor = new JsonAdaptedDoctor(BENSON);
        assertEquals(BENSON, doctor.toModelType());
    }

    @Test
    public void toModelType_invalidNric_throwsIllegalValueException() {
        JsonAdaptedDoctor doctor =
                new JsonAdaptedDoctor(INVALID_NRIC, VALID_NAME);
        String expectedMessage = Nric.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, doctor::toModelType);
    }

    @Test
    public void toModelType_nullNric_throwsIllegalValueException() {
        JsonAdaptedDoctor doctor = new JsonAdaptedDoctor(null, VALID_NAME);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Nric.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, doctor::toModelType);
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedDoctor doctor =
                new JsonAdaptedDoctor(VALID_NRIC, INVALID_NAME);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, doctor::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedDoctor doctor = new JsonAdaptedDoctor(VALID_NRIC, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, doctor::toModelType);
    }
}
