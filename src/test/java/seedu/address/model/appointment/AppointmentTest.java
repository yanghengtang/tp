package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_END_TIME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_START_TIME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_BOB;
import static seedu.address.model.appointment.AppointmentStartTime.DATE_TIME_INPUT_FORMATTER;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAppointment.APPOINTMENT_1;
import static seedu.address.testutil.TypicalAppointment.APPOINTMENT_1_WITH_REMARKS;
import static seedu.address.testutil.TypicalAppointment.APPOINTMENT_2;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.Nric;
import seedu.address.testutil.AppointmentBuilder;

public class AppointmentTest {

    @Test
    public void appointmentConsturctor() {
        //same patient and doctor nric
        Nric duplicateNric = new Nric(VALID_NRIC_AMY);
        AppointmentStartTime startTime = new AppointmentStartTime(VALID_APPOINTMENT_START_TIME);
        AppointmentEndTime endTime = new AppointmentEndTime(VALID_APPOINTMENT_END_TIME);
        assertThrows(CommandException.class, () -> new Appointment(duplicateNric, duplicateNric, startTime, endTime));

        //swapped end time and start time
        Nric patientNric = new Nric(VALID_NRIC_AMY);
        Nric doctorNric = new Nric(VALID_NRIC_BOB);
        AppointmentStartTime startTime2 = new AppointmentStartTime(VALID_APPOINTMENT_END_TIME);
        AppointmentEndTime endTime2 = new AppointmentEndTime(VALID_APPOINTMENT_START_TIME);
        assertThrows(CommandException.class, () -> new Appointment(patientNric, doctorNric, startTime2, endTime2));
    }

    @Test
    public void isSame() {
        // same object -> returns true
        assertTrue(APPOINTMENT_1.isSame(APPOINTMENT_1));

        // null -> returns false
        assertFalse(APPOINTMENT_1.isSame(null));

        // different Doctor NRIC -> returns false
        assertFalse(APPOINTMENT_1.isSame(new AppointmentBuilder(APPOINTMENT_1)
                .withDoctorNric(VALID_NRIC_AMY).build()));

        // different Patient NRIC -> returns false
        assertFalse(APPOINTMENT_1.isSame(new AppointmentBuilder(APPOINTMENT_1)
                .withPatientNric(VALID_NRIC_BOB).build()));

        // different Date Time -> returns false
        assertFalse(APPOINTMENT_1.isSame(new AppointmentBuilder(APPOINTMENT_1)
                .withStartTime("2023-09-11 07:00").build()));

        // same appointment with remarks and tags -> returns true
        assertTrue(APPOINTMENT_1.isSame(APPOINTMENT_1_WITH_REMARKS));
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

        // different Doctor NRIC -> returns false
        Appointment editedAppointment = new AppointmentBuilder(APPOINTMENT_1).withDoctorNric(VALID_NRIC_AMY).build();
        assertFalse(APPOINTMENT_1.equals(editedAppointment));

        // different Patient NRIC -> returns false
        editedAppointment = new AppointmentBuilder(APPOINTMENT_1).withPatientNric(VALID_NRIC_BOB).build();
        assertFalse(APPOINTMENT_1.equals(editedAppointment));

        // different startTime -> returns false
        editedAppointment = new AppointmentBuilder(APPOINTMENT_1).withStartTime("2023-09-11 07:00").build();
        assertFalse(APPOINTMENT_1.equals(editedAppointment));

        // different endTime -> returns false
        editedAppointment = new AppointmentBuilder(APPOINTMENT_1).withEndTime("2023-12-01 12:00").build();
        assertFalse(APPOINTMENT_1.equals(editedAppointment));

    }

    @Test
    public void withinInterval() {
        LocalDateTime withinInterval = LocalDateTime.parse("2023-09-11 07:45", DATE_TIME_INPUT_FORMATTER);
        LocalDateTime outsideInterval = LocalDateTime.parse("2023-09-11 08:45", DATE_TIME_INPUT_FORMATTER);
        LocalDateTime startTime = LocalDateTime.parse("2023-09-11 07:30", DATE_TIME_INPUT_FORMATTER);
        LocalDateTime endTime = LocalDateTime.parse("2023-09-11 08:00", DATE_TIME_INPUT_FORMATTER);

        //time within interval
        assertTrue(APPOINTMENT_1.withinInterval(withinInterval));

        //time outside interval
        assertFalse(APPOINTMENT_1.withinInterval(outsideInterval));

        //time equal to start time
        assertFalse(APPOINTMENT_1.withinInterval(startTime));

        //time equal to end time
        assertFalse(APPOINTMENT_1.withinInterval(endTime));
    }

    @Test
    public void overlaps() {

        // same values -> returns true
        Appointment appointmentCopy = new AppointmentBuilder(APPOINTMENT_1).build();
        assertTrue(APPOINTMENT_1.overlaps(appointmentCopy));

        //startTime within interval but end time not
        assertTrue(APPOINTMENT_1.overlaps(new AppointmentBuilder()
                .withStartTime("2023-09-11 07:45")
                .withEndTime("2023-09-11 08:45")
                .build()));

        //endTime within interval but start time not
        assertTrue(APPOINTMENT_1.overlaps(new AppointmentBuilder()
                .withStartTime("2023-09-11 07:15")
                .withEndTime("2023-09-11 07:45")
                .build()));

        //endTime within interval but start time not
        assertTrue(APPOINTMENT_1.overlaps(new AppointmentBuilder()
                .withStartTime("2023-09-11 07:30")
                .withEndTime("2023-09-11 08:15")
                .build()));

        //Both startTime and endTime within interval
        assertTrue(APPOINTMENT_1.overlaps(new AppointmentBuilder()
                .withStartTime("2023-09-11 07:40")
                .withEndTime("2023-09-11 07:50")
                .build()));

        //both start and end time not within interval but appointment is contained within the other
        assertTrue(APPOINTMENT_1.overlaps(new AppointmentBuilder()
                .withStartTime("2023-09-11 07:15")
                .withEndTime("2023-09-11 08:45")
                .build()));

        // no overlap
        assertFalse(APPOINTMENT_1.overlaps(new AppointmentBuilder()
                .withStartTime("2023-09-11 08:15")
                .withEndTime("2023-09-11 08:45")
                .build()));
    }

    @Test
    public void toStringMethod() {
        String expected = Appointment.class.getCanonicalName() + "{doctorNric=" + APPOINTMENT_1.getDoctorNric()
                + ", patientNric=" + APPOINTMENT_1.getPatientNric()
                + ", startTime=" + APPOINTMENT_1.getStartTime()
                + ", endTime=" + APPOINTMENT_1.getEndTime()
                + ", remark=" + APPOINTMENT_1.getRemark()
                + ", prescriptions=" + APPOINTMENT_1.getTags()
                + "}";
        assertEquals(expected, APPOINTMENT_1.toString());
    }
}
