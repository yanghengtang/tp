package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.NewCommandTestUtil.DESC_APPOINTMENT_1;
import static seedu.address.logic.commands.NewCommandTestUtil.DESC_APPOINTMENT_2;
import static seedu.address.logic.commands.NewCommandTestUtil.VALID_DOCTOR_NRIC;
import static seedu.address.logic.commands.NewCommandTestUtil.VALID_PATIENT_NRIC;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditAppointmentCommand.EditAppointmentDescriptor;
import seedu.address.testutil.EditAppointmentDescriptorBuilder;

public class EditAppointmentDescriptorTest {

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
