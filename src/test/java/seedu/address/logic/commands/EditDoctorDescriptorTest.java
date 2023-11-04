package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_BOB;

import java.util.Objects;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditDoctorCommand.EditDoctorDescriptor;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.testutil.EditDoctorDescriptorBuilder;

public class EditDoctorDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditDoctorDescriptor descriptorWithSameValues = new EditDoctorDescriptor(DESC_AMY);
        assertTrue(DESC_AMY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_AMY.equals(DESC_AMY));

        // null -> returns false
        assertFalse(DESC_AMY.equals(null));

        // different types -> returns false
        assertFalse(DESC_AMY.equals(5));

        // different values -> returns false
        assertFalse(DESC_AMY.equals(DESC_BOB));

        // different name -> returns false
        EditDoctorDescriptor editedAmy = new EditDoctorDescriptorBuilder(DESC_AMY).withName(VALID_NAME_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different nric -> returns false
        editedAmy = new EditDoctorDescriptorBuilder(DESC_AMY).withNric(VALID_NRIC_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));
    }

    @Test
    public void hashCodeMethod() {
        Name name = new Name(VALID_NAME_BOB);
        Nric nric = new Nric(VALID_NRIC_BOB);
        EditDoctorDescriptor descriptor = new EditDoctorDescriptorBuilder().withName(VALID_NAME_BOB)
                .withNric(VALID_NRIC_BOB).build();

        // same value -> returns same hashcode
        assertEquals(descriptor.hashCode(), Objects.hash(name, nric));
    }

    @Test
    public void toStringMethod() {
        EditDoctorDescriptor editDoctorDescriptor = new EditDoctorDescriptor();
        String expected = EditDoctorDescriptor.class.getCanonicalName() + "{name="
                + editDoctorDescriptor.getName().orElse(null) + ", nric="
                + editDoctorDescriptor.getNric().orElse(null) + "}";
        assertEquals(expected, editDoctorDescriptor.toString());
    }
}
