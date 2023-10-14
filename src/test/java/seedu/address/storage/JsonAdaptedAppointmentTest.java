package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedAppointment.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAppointment.APPOINTMENT_1;
import static seedu.address.testutil.TypicalPatient.BENSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.appointment.AppointmentEndTime;
import seedu.address.model.appointment.AppointmentStartTime;
import seedu.address.model.person.Nric;

public class JsonAdaptedAppointmentTest {
    private static final String INVALID_NRIC = "X0123456L";
    private static final String INVALID_DATETIME = "06-09-2023T25:30";

    private static final String VALID_NRIC = BENSON.getNric().toString();
    private static final String VALID_START_TIME = "2023-10-13 20:30";
    private static final String VALID_END_TIME = "2023-10-13 22:30";

    @Test
    public void toModelType_validAppointmentDetails_returnsAppointment() throws Exception {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(APPOINTMENT_1);
        assertEquals(APPOINTMENT_1, appointment.toModelType());
    }

    @Test
    public void toModelType_invalidNric_throwsIllegalValueException() {
        // invalid doctor nric
        JsonAdaptedAppointment appointment =
                new JsonAdaptedAppointment(INVALID_NRIC, VALID_NRIC, VALID_START_TIME, VALID_END_TIME);
        String expectedMessage = Nric.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);

        // invalid patient nric
        appointment = new JsonAdaptedAppointment(VALID_NRIC, INVALID_NRIC, VALID_START_TIME, VALID_END_TIME);
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);

        // invalid doctor nric and patient nric
        appointment = new JsonAdaptedAppointment(INVALID_NRIC, INVALID_NRIC, VALID_START_TIME, VALID_END_TIME);
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

    @Test
    public void toModelType_nullNric_throwsIllegalValueException() {
        // doctor nric is null
        JsonAdaptedAppointment appointment =
                new JsonAdaptedAppointment(null, VALID_NRIC, VALID_START_TIME, VALID_END_TIME);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Nric.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);

        // patient nric is null
        appointment = new JsonAdaptedAppointment(VALID_NRIC, null, VALID_START_TIME, VALID_END_TIME);
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);

        // doctor and patient nric is null
        appointment = new JsonAdaptedAppointment(null, null, VALID_START_TIME, VALID_END_TIME);
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

    @Test
    public void toModelType_invalidStartTime_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment =
                new JsonAdaptedAppointment(VALID_NRIC, VALID_NRIC, INVALID_DATETIME, VALID_END_TIME);
        assertThrows(IllegalValueException.class, AppointmentStartTime.MESSAGE_CONSTRAINTS, appointment::toModelType);
    }

    @Test
    public void toModelType_nullStartTime_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(VALID_NRIC, VALID_NRIC, null,
                VALID_END_TIME);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                AppointmentStartTime.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

    @Test
    public void toModelType_invalidEndTime_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment =
                new JsonAdaptedAppointment(VALID_NRIC, VALID_NRIC, VALID_START_TIME, INVALID_DATETIME);
        assertThrows(IllegalValueException.class, AppointmentEndTime.MESSAGE_CONSTRAINTS, appointment::toModelType);
    }

    @Test
    public void toModelType_nullEndTime_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(VALID_NRIC, VALID_NRIC, VALID_START_TIME,
                null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, AppointmentEndTime.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }
}
