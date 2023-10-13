package seedu.address.storage;

import org.junit.jupiter.api.Test;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Phone;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedAppointment.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAppointment.APPOINTMENT_1;
import static seedu.address.testutil.TypicalPatient.BENSON;

public class JsonAdaptedAppointmentTest {
    private static final String INVALID_NRIC = "X0123456L";
    private static final String INVALID_DATETIME = "06-09-2023T25:30";

    private static final String VALID_NRIC = BENSON.getNric().toString();
    private static final String VALID_DATETIME = "2023-10-13T20:30";

    @Test
    public void toModelType_validAppointmentDetails_returnsAppointment() throws Exception {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(APPOINTMENT_1);
        assertEquals(APPOINTMENT_1, appointment.toModelType());
    }

    @Test
    public void toModelType_invalidNric_throwsIllegalValueException() {
        // invalid doctor nric
        JsonAdaptedAppointment appointment =
                new JsonAdaptedAppointment(INVALID_NRIC, VALID_NRIC, VALID_DATETIME);
        String expectedMessage = Nric.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);

        // invalid patient nric
        appointment = new JsonAdaptedAppointment(VALID_NRIC, INVALID_NRIC, VALID_DATETIME);
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);

        // invalid doctor nric and patient nric
        appointment = new JsonAdaptedAppointment(INVALID_NRIC, INVALID_NRIC, VALID_DATETIME);
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

    @Test
    public void toModelType_nullNric_throwsIllegalValueException() {
        // doctor nric is null
        JsonAdaptedAppointment appointment =
                new JsonAdaptedAppointment(null, VALID_NRIC, VALID_DATETIME);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Nric.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);

        // patient nric is null
        appointment = new JsonAdaptedAppointment(VALID_NRIC, null, VALID_DATETIME);
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);

        // doctor and patient nric is null
        appointment = new JsonAdaptedAppointment(null, null, VALID_DATETIME);
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

    @Test
    public void toModelType_invalidDateTime_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment =
                new JsonAdaptedAppointment(VALID_NRIC, VALID_NRIC, INVALID_DATETIME);
        // TODO: remove magic literal
        String expectedMessage = "throwing datetime exception";
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(VALID_NRIC, VALID_NRIC, null);
        // TODO: remove magic literal
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "dateTime");
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }
}
