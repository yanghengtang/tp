package seedu.address.model.person.patient;

import org.junit.jupiter.api.Test;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.testutil.PatientBuilder;
import seedu.address.testutil.TypicalPatient;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPatient.ALICE;
import static seedu.address.testutil.TypicalPatient.BOB;

public class UniqueItemListTest {
    private final UniqueItemList<Patient> uniquePatientList = new UniqueItemList<>();

    @Test
    public void contains_nullItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePatientList.contains(null));
    }

    @Test
    public void contains_itemNotInList_returnsFalse() {
        assertFalse(uniquePatientList.contains(ALICE));
    }

    @Test
    public void contains_itemInList_returnsTrue() {
        uniquePatientList.add(TypicalPatient.ALICE);
        assertTrue(uniquePatientList.contains(TypicalPatient.ALICE));
    }

    @Test
    public void contains_itemWithSameIdentityFieldsInList_returnsTrue() {
        uniquePatientList.add(TypicalPatient.ALICE);
        Patient editedAlice = new PatientBuilder(TypicalPatient.ALICE).withPhone(VALID_PHONE_BOB).withName(VALID_NAME_BOB)
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
        assertThrows(DuplicatePersonException.class, () -> uniquePatientList.add(TypicalPatient.ALICE));
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
        assertThrows(PersonNotFoundException.class, () -> uniquePatientList.setItem(TypicalPatient.ALICE, TypicalPatient.ALICE));
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
        Patient editedAlice = new PatientBuilder(TypicalPatient.ALICE).withPhone(VALID_PHONE_BOB).withName(VALID_NAME_BOB)
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
        assertThrows(DuplicatePersonException.class, () -> uniquePatientList.setItem(TypicalPatient.ALICE, BOB));
    }

    @Test
    public void remove_nullItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePatientList.remove(null));
    }

    @Test
    public void remove_itemDoesNotExist_throwsItemNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> uniquePatientList.remove(TypicalPatient.ALICE));
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
        assertThrows(DuplicatePersonException.class, () -> uniquePatientList.setItems(listWithDuplicatePatients));
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
}
