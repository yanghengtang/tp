package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalAppointment.APPOINTMENT_1;
import static seedu.address.testutil.TypicalAppointment.APPOINTMENT_2;
import static seedu.address.testutil.TypicalDoctor.CARL;
import static seedu.address.testutil.TypicalDoctor.DANIEL;
import static seedu.address.testutil.TypicalPatient.ELLE;
import static seedu.address.testutil.TypicalPatient.FIONA;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.AppointmentBuilder;

public class AppointmentTest {

    @Test
    public void isSame() {
        // same object -> returns true
        assertTrue(APPOINTMENT_1.isSame(APPOINTMENT_1));

        // null -> returns false
        assertFalse(APPOINTMENT_1.isSame(null));

        // different Doctor -> returns false
        assertFalse(APPOINTMENT_1.isSame(new AppointmentBuilder(APPOINTMENT_1).withDoctor(CARL).build()));

        // different Patient -> returns false
        assertFalse(APPOINTMENT_1.isSame(new AppointmentBuilder(APPOINTMENT_1).withPatient(ELLE).build()));

        // different Date Time -> returns false
        assertFalse(APPOINTMENT_1.isSame(new AppointmentBuilder(APPOINTMENT_1)
                .withDateTime("2023-12-01T10:30").build()));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Appointment appointmentCopy = new AppointmentBuilder(APPOINTMENT_1).build();
        assertTrue(APPOINTMENT_1.equals(appointmentCopy));

        // same object -> returns true
        assertTrue(APPOINTMENT_1.equals(APPOINTMENT_1));

        // null -> returns false
        assertFalse(APPOINTMENT_1.equals(null));

        // different type -> returns false
        assertFalse(APPOINTMENT_1.equals(5.0));

        // different person -> returns false
        assertFalse(APPOINTMENT_1.equals(APPOINTMENT_2));

        // different Doctor -> returns false
        Appointment editedAppointment = new AppointmentBuilder(APPOINTMENT_1).withDoctor(DANIEL).build();
        assertFalse(APPOINTMENT_1.equals(editedAppointment));

        // different Patient -> returns false
        editedAppointment = new AppointmentBuilder(APPOINTMENT_1).withPatient(FIONA).build();
        assertFalse(APPOINTMENT_1.equals(editedAppointment));

        // different DateTime -> returns false
        editedAppointment = new AppointmentBuilder(APPOINTMENT_1).withDateTime("2023-12-01T12:00").build();
        assertFalse(APPOINTMENT_1.equals(editedAppointment));
    }

    @Test
    public void toStringMethod() {
        String expected = Appointment.class.getCanonicalName() + "{doctor=" + APPOINTMENT_1.getDoctor()
                + ", patient=" + APPOINTMENT_1.getPatient() + ", dateTime=" + APPOINTMENT_1.getDateTime() + "}";
        assertEquals(expected, APPOINTMENT_1.toString());
    }
}
