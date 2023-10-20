package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.NewCommandTestUtil.PATIENT_DESC_AMY;
import static seedu.address.logic.commands.NewCommandTestUtil.PATIENT_DESC_BOB;
import static seedu.address.logic.commands.NewCommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.NewCommandTestUtil.VALID_NRIC_BOB;
import static seedu.address.logic.commands.NewCommandTestUtil.VALID_PHONE_BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditPatientCommand.EditPatientDescriptor;
import seedu.address.testutil.EditPatientDescriptorBuilder;

public class EditPatientDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditPatientDescriptor descriptorWithSameValues = new EditPatientDescriptor(PATIENT_DESC_AMY);
        assertTrue(PATIENT_DESC_AMY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(PATIENT_DESC_AMY.equals(PATIENT_DESC_AMY));

        // null -> returns false
        assertFalse(PATIENT_DESC_AMY.equals(null));

        // different types -> returns false
        assertFalse(PATIENT_DESC_AMY.equals(5));

        // different values -> returns false
        assertFalse(PATIENT_DESC_AMY.equals(PATIENT_DESC_BOB));

        // different name -> returns false
        EditPatientDescriptor editedAmy =
                new EditPatientDescriptorBuilder(PATIENT_DESC_AMY).withName(VALID_NAME_BOB).build();
        assertFalse(PATIENT_DESC_AMY.equals(editedAmy));

        // different phone -> returns false
        editedAmy = new EditPatientDescriptorBuilder(PATIENT_DESC_AMY).withPhone(VALID_PHONE_BOB).build();
        assertFalse(PATIENT_DESC_AMY.equals(editedAmy));

        // different email -> returns false
        editedAmy = new EditPatientDescriptorBuilder(PATIENT_DESC_AMY).withNric(VALID_NRIC_BOB).build();
        assertFalse(PATIENT_DESC_AMY.equals(editedAmy));
    }

    @Test
    public void toStringMethod() {
        EditPatientDescriptor editPatientDescriptor = new EditPatientDescriptor();
        String expected = EditPatientDescriptor.class.getCanonicalName() + "{name="
                + editPatientDescriptor.getName().orElse(null) + ", phone="
                + editPatientDescriptor.getPhone().orElse(null) + ", nric="
                + editPatientDescriptor.getNric().orElse(null) + "}";
        assertEquals(expected, editPatientDescriptor.toString());
    }
}
