package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_APPOINTMENT_1;
import static seedu.address.logic.commands.CommandTestUtil.DESC_APPOINTMENT_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_END_TIME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_START_TIME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DOCTOR_NRIC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PATIENT_NRIC;

import java.util.Objects;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditAppointmentCommand.EditAppointmentDescriptor;
import seedu.address.model.appointment.AppointmentEndTime;
import seedu.address.model.appointment.AppointmentStartTime;
import seedu.address.model.person.Nric;
import seedu.address.testutil.EditAppointmentDescriptorBuilder;

public class EditAppointmentDescriptorTest {

    @Test
    public void hashCodeMethod() {
        Nric patientNric = new Nric(VALID_NRIC_AMY);
        Nric doctorNric = new Nric(VALID_NRIC_BOB);
        AppointmentStartTime startTime = new AppointmentStartTime(VALID_APPOINTMENT_START_TIME);
        AppointmentEndTime endTime = new AppointmentEndTime(VALID_APPOINTMENT_END_TIME);
        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder()
                .withPatientNric(VALID_NRIC_AMY)
                .withDoctorNric(VALID_NRIC_BOB)
                .withStartTime(VALID_APPOINTMENT_START_TIME)
                .withEndTime(VALID_APPOINTMENT_END_TIME)
                .build();

        // same value -> returns same hashcode
        assertEquals(descriptor.hashCode(), Objects.hash(patientNric, doctorNric, startTime, endTime));
    }
    @Test
    public void equals() {
        // same values -> returns true
        EditAppointmentDescriptor descriptorWithSameValues = new EditAppointmentDescriptor(DESC_APPOINTMENT_1);
        assertTrue(DESC_APPOINTMENT_1.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_APPOINTMENT_1.equals(DESC_APPOINTMENT_1));

        // null -> returns false
        assertFalse(DESC_APPOINTMENT_1.equals(null));

        // different types -> returns false
        assertFalse(DESC_APPOINTMENT_1.equals(5));

        // different values -> returns false
        assertFalse(DESC_APPOINTMENT_1.equals(DESC_APPOINTMENT_2));

        // different patientNric -> returns false
        EditAppointmentDescriptor editedAppointment1 = new EditAppointmentDescriptorBuilder(DESC_APPOINTMENT_1)
                .withPatientNric(VALID_PATIENT_NRIC)
                .build();
        assertFalse(DESC_APPOINTMENT_1.equals(editedAppointment1));

        // different doctornric -> returns false
        editedAppointment1 = new EditAppointmentDescriptorBuilder(DESC_APPOINTMENT_1)
                .withDoctorNric(VALID_DOCTOR_NRIC)
                .build();
        assertFalse(DESC_APPOINTMENT_1.equals(editedAppointment1));

        // different start date
        editedAppointment1 = new EditAppointmentDescriptorBuilder(DESC_APPOINTMENT_1)
                .withStartTime("2023-12-11 07:30")
                .build();
        assertFalse(DESC_APPOINTMENT_1.equals(editedAppointment1));

        // different end date
        editedAppointment1 = new EditAppointmentDescriptorBuilder(DESC_APPOINTMENT_1)
                .withEndTime("2023-12-11 07:30")
                .build();
        assertFalse(DESC_APPOINTMENT_1.equals(editedAppointment1));
    }

    @Test
    public void toStringMethod() {
        EditAppointmentDescriptor editAppointmentDescriptor = new EditAppointmentDescriptor();
        String expected = EditAppointmentDescriptor.class.getCanonicalName() + "{patientNric="
                + editAppointmentDescriptor.getPatientNric().orElse(null) + ", doctorNric="
                + editAppointmentDescriptor.getDoctorNric().orElse(null) + ", startTime="
                + editAppointmentDescriptor.getAppointmentStartTime().orElse(null) + ", endTime="
                + editAppointmentDescriptor.getAppointmentEndTime().orElse(null) + "}";
        assertEquals(expected, editAppointmentDescriptor.toString());
    }
}
