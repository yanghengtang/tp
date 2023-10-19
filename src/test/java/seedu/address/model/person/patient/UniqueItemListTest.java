package seedu.address.model.person.patient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPatient.ALICE;
import static seedu.address.testutil.TypicalPatient.ALICE_NAME;
import static seedu.address.testutil.TypicalPatient.ALICE_NRIC;
import static seedu.address.testutil.TypicalPatient.ALICE_PHONE;
import static seedu.address.testutil.TypicalPatient.BENSON;
import static seedu.address.testutil.TypicalPatient.BENSON_NAME;
import static seedu.address.testutil.TypicalPatient.BENSON_NRIC;
import static seedu.address.testutil.TypicalPatient.BENSON_PHONE;
import static seedu.address.testutil.TypicalPatient.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Phone;
import seedu.address.model.person.doctor.Doctor;
import seedu.address.model.person.exceptions.DuplicateItemException;
import seedu.address.model.person.exceptions.ItemNotFoundException;
import seedu.address.testutil.PatientBuilder;
import seedu.address.testutil.TypicalDoctor;
import seedu.address.testutil.TypicalPatient;

public class UniqueItemListTest {
    private final UniqueItemList<Patient> uniquePatientList = new UniqueItemList<>();
    private final UniqueItemList<Patient> uniquePatientList2 = new UniqueItemList<>();
    private final UniqueItemList<Doctor> uniqueDoctorList = new UniqueItemList<>();

    @Test
    public void contains_nullItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePatientList.contains((Patient) null));
        assertThrows(NullPointerException.class, () -> uniquePatientList.contains((Function<Patient, Boolean>) null));
    }

    @Test
    public void contains_itemNotInList_returnsFalse() {
        assertFalse(uniquePatientList.contains(ALICE));
        assertFalse(uniquePatientList.contains(patient -> patient.getNric().equals(new Nric(ALICE_NRIC))));
        assertFalse(uniquePatientList.contains(patient -> patient.getName().equals(new Name(ALICE_NAME))));
        assertFalse(uniquePatientList.contains(patient -> patient.getPhone().equals(new Phone(ALICE_PHONE))));

        uniquePatientList.add(ALICE);
        assertFalse(uniquePatientList.contains(BENSON));
        assertFalse(uniquePatientList.contains(patient -> patient.getNric().equals(new Nric(BENSON_NRIC))));
        assertFalse(uniquePatientList.contains(patient -> patient.getName().equals(new Name(BENSON_NAME))));
        assertFalse(uniquePatientList.contains(patient -> patient.getPhone().equals(new Phone(BENSON_PHONE))));
        assertFalse(uniquePatientList.contains(patient -> patient.getName().equals(new Nric(ALICE_NRIC))));
    }

    @Test
    public void contains_itemInList_returnsTrue() {
        uniquePatientList.add(TypicalPatient.ALICE);
        assertTrue(uniquePatientList.contains(TypicalPatient.ALICE));
        assertTrue(uniquePatientList.contains(patient -> patient.getNric().equals(new Nric(ALICE_NRIC))));
        assertTrue(uniquePatientList.contains(patient -> patient.getName().equals(new Name(ALICE_NAME))));
        assertTrue(uniquePatientList.contains(patient -> patient.getPhone().equals(new Phone(ALICE_PHONE))));
    }

    @Test
    public void contains_itemWithSameIdentityFieldsInList_returnsTrue() {
        uniquePatientList.add(TypicalPatient.ALICE);
        Patient editedAlice = new PatientBuilder(TypicalPatient.ALICE).withPhone(VALID_PHONE_BOB)
                .withName(VALID_NAME_BOB)
                .build();
        assertTrue(uniquePatientList.contains(editedAlice));
    }

    @Test
    public void add_nullItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePatientList.add(null));
    }

    @Test
    public void add_duplicateItem_throwsDuplicateItemException() {
        uniquePatientList.add(TypicalPatient.ALICE);
        assertThrows(DuplicateItemException.class, () -> uniquePatientList.add(TypicalPatient.ALICE));
    }

    @Test
    public void setItem_nullTargetItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePatientList.setItem(null, TypicalPatient.ALICE));
    }

    @Test
    public void setItem_nullEditedItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePatientList.setItem(TypicalPatient.ALICE, null));
    }

    @Test
    public void setItem_targetItemNotInList_throwsItemNotFoundException() {
        assertThrows(ItemNotFoundException.class, () -> uniquePatientList.setItem(
                TypicalPatient.ALICE, TypicalPatient.ALICE));
    }

    @Test
    public void setItem_editedItemIsSameItem_success() {
        uniquePatientList.add(TypicalPatient.ALICE);
        uniquePatientList.setItem(TypicalPatient.ALICE, TypicalPatient.ALICE);
        UniqueItemList<Patient> expectedUniqueItemList = new UniqueItemList<>();
        expectedUniqueItemList.add(TypicalPatient.ALICE);
        assertEquals(expectedUniqueItemList, uniquePatientList);
    }

    @Test
    public void setItem_editedItemHasSameIdentity_success() {
        uniquePatientList.add(TypicalPatient.ALICE);
        Patient editedAlice = new PatientBuilder(TypicalPatient.ALICE).withPhone(VALID_PHONE_BOB)
                .withName(VALID_NAME_BOB)
                .build();
        uniquePatientList.setItem(TypicalPatient.ALICE, editedAlice);
        UniqueItemList<Patient> expectedUniquePatientList = new UniqueItemList<>();
        expectedUniquePatientList.add(editedAlice);
        assertEquals(expectedUniquePatientList, uniquePatientList);
    }

    @Test
    public void setItem_editedItemHasDifferentIdentity_success() {
        uniquePatientList.add(TypicalPatient.ALICE);
        uniquePatientList.setItem(TypicalPatient.ALICE, BOB);
        UniqueItemList<Patient> expectedUniquePatientList = new UniqueItemList<>();
        expectedUniquePatientList.add(BOB);
        assertEquals(expectedUniquePatientList, uniquePatientList);
    }

    @Test
    public void setItem_editedItemHasNonUniqueIdentity_throwsDuplicateItemException() {
        uniquePatientList.add(TypicalPatient.ALICE);
        uniquePatientList.add(BOB);
        assertThrows(DuplicateItemException.class, () -> uniquePatientList.setItem(TypicalPatient.ALICE, BOB));
    }

    @Test
    public void remove_nullItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePatientList.remove((Patient) null));
        assertThrows(NullPointerException.class, () -> uniquePatientList.remove((Predicate<Patient>) null));
    }

    @Test
    public void remove_itemDoesNotExist_throwsItemNotFoundException() {
        assertThrows(ItemNotFoundException.class, () -> uniquePatientList.remove(TypicalPatient.ALICE));
    }

    @Test
    public void remove_existingItem_removesItem() {
        uniquePatientList.add(TypicalPatient.ALICE);
        uniquePatientList.remove(TypicalPatient.ALICE);
        UniqueItemList<Patient> expectedUniquePatientList = new UniqueItemList<>();
        assertEquals(expectedUniquePatientList, uniquePatientList);
    }

    @Test
    public void setItems_nullUniqueItemList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePatientList.setItems((UniqueItemList<Patient>) null));
    }

    @Test
    public void setItems_uniqueItemList_replacesOwnListWithProvidedUniqueItemList() {
        uniquePatientList.add(TypicalPatient.ALICE);
        UniqueItemList<Patient> expectedUniquePatientList = new UniqueItemList<>();
        expectedUniquePatientList.add(BOB);
        uniquePatientList.setItems(expectedUniquePatientList);
        assertEquals(expectedUniquePatientList, uniquePatientList);
    }

    @Test
    public void setItems_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePatientList.setItems((List<Patient>) null));
    }

    @Test
    public void setItems_list_replacesOwnListWithProvidedList() {
        uniquePatientList.add(TypicalPatient.ALICE);
        List<Patient> patientList = Collections.singletonList(BOB);
        uniquePatientList.setItems(patientList);
        UniqueItemList<Patient> expectedUniquePatientList = new UniqueItemList<>();
        expectedUniquePatientList.add(BOB);
        assertEquals(expectedUniquePatientList, uniquePatientList);
    }

    @Test
    public void setItems_listWithDuplicateItems_throwsDuplicateItemException() {
        List<Patient> listWithDuplicatePatients = Arrays.asList(TypicalPatient.ALICE, TypicalPatient.ALICE);
        assertThrows(DuplicateItemException.class, () -> uniquePatientList.setItems(listWithDuplicatePatients));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniquePatientList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniquePatientList.asUnmodifiableObservableList().toString(), uniquePatientList.toString());
    }

    @Test
    public void equalsMethod() {
        // same object -> return True
        assertTrue(uniquePatientList.equals(uniquePatientList));

        // different type -> return False
        assertFalse(uniquePatientList.equals(5));

        // null -> returns false
        assertFalse(uniquePatientList.equals(null));

        uniquePatientList.add(ALICE);
        // different number of patients -> return False
        assertFalse(uniquePatientList.equals(uniquePatientList2));

        uniquePatientList2.add(ALICE);
        // same patients -> return True
        assertTrue(uniquePatientList.equals(uniquePatientList2));

        // different contents -> return False
        uniqueDoctorList.add(TypicalDoctor.ALICE);
        assertFalse(uniquePatientList.equals(uniqueDoctorList));
    }
}
