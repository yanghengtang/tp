package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.PersonUtil.ALICE_NRIC;
import static seedu.address.testutil.PersonUtil.CARL_NRIC;
import static seedu.address.testutil.TypicalAppointment.APPOINTMENT_1;
import static seedu.address.testutil.TypicalDoctor.ALICE;
import static seedu.address.testutil.TypicalPatient.CARL;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Nric;
import seedu.address.model.person.doctor.Doctor;
import seedu.address.model.person.exceptions.ItemNotFoundException;
import seedu.address.model.person.patient.Patient;
import seedu.address.testutil.AppointmentBuilder;
import seedu.address.testutil.DoctorBuilder;
import seedu.address.testutil.PatientBuilder;
import seedu.address.testutil.TypicalDatabase;

public class ModelManagerTest {
    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new Database(), new Database(modelManager.getDatabase()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setDatabaseFilePath(Paths.get("database/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setDatabaseFilePath(Paths.get("new/database/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setDatabaseFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setDatabaseFilePath(null));
    }

    @Test
    public void setDatabaseFilePath_validPath_setsAddressBookFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setDatabaseFilePath(path);
        assertEquals(path, modelManager.getDatabaseFilePath());
    }

    @Test void setDatabase_validDatabase_returnsTrue() throws CommandException {
        ReadOnlyDatabase database = TypicalDatabase.getTypicalDatabase();
        modelManager.setDatabase(database);
        assertTrue(database.equals(modelManager.getDatabase()));
    }

    @Test
    public void hasAppointment_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasAppointment(null));
    }

    @Test
    public void hasDoctor_nullDoctor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasDoctor(null));
    }

    @Test
    public void hasPatient_nullPatient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasPatient(null));
    }

    @Test
    public void hasAppointment_appointmentNotInDatabase_returnsFalse() {
        assertFalse(modelManager.hasAppointment(APPOINTMENT_1));
    }

    @Test
    public void hasDoctor_doctorNotInDatabase_returnsFalse() {
        assertFalse(modelManager.hasDoctor(ALICE));
    }

    @Test
    public void hasPatient_patientNotInDatabase_returnsFalse() {
        assertFalse(modelManager.hasPatient(CARL));
    }

    @Test
    public void hasDoctorWithNric_doctorNricNotInDatabase_returnsFalse() {
        Nric doctorNric = new Nric(ALICE_NRIC);
        assertFalse(modelManager.hasDoctorWithNric(doctorNric));
    }

    @Test
    public void hasPatientWithNric_patientNricNotInDatabase_returnsFalse() {
        Nric patientNric = new Nric(CARL_NRIC);
        assertFalse(modelManager.hasPatientWithNric(patientNric));
    }

    @Test
    public void hasDoctorWithNric_doctorNricInDatabase_returnsFalse() {
        Nric doctorNric = new Nric(ALICE_NRIC);
        modelManager.addDoctor(ALICE);
        assertTrue(modelManager.hasDoctorWithNric(doctorNric));
    }

    @Test
    public void hasPatientWithNric_patientNricInDatabase_returnsFalse() {
        Nric patientNric = new Nric(CARL_NRIC);
        modelManager.addPatient(CARL);
        assertTrue(modelManager.hasPatientWithNric(patientNric));
    }

    @Test
    public void hasAppointment_appointmentInDatabase_returnsTrue() throws CommandException {
        modelManager.addAppointment(APPOINTMENT_1);
        assertTrue(modelManager.hasAppointment(APPOINTMENT_1));
    }

    @Test
    public void hasDoctor_doctorInDatabase_returnsTrue() {
        modelManager.addDoctor(ALICE);
        assertTrue(modelManager.hasDoctor(ALICE));
    }

    @Test
    public void hasPatient_patientInDatabase_returnsTrue() {
        modelManager.addPatient(CARL);
        assertTrue(modelManager.hasPatient(CARL));
    }

    @Test
    public void hasAppointment_appointmentRemovedFromDatabase_returnsTrue() throws CommandException {
        modelManager.addAppointment(APPOINTMENT_1);
        modelManager.deleteAppointment(APPOINTMENT_1);
        assertFalse(modelManager.hasAppointment(APPOINTMENT_1));
    }

    @Test
    public void hasDoctor_doctorRemovedFromDatabase_returnsTrue() {
        modelManager.addDoctor(ALICE);
        modelManager.deleteDoctor(ALICE);
        assertFalse(modelManager.hasDoctor(ALICE));
    }

    @Test
    public void hasPatient_patientRemovedFromDatabase_returnsTrue() {
        modelManager.addPatient(CARL);
        modelManager.deletePatient(CARL);
        assertFalse(modelManager.hasPatient(CARL));
    }

    @Test
    public void hasAppointment_appointmentWithDifferentEndTime_returnsFalse() throws CommandException {
        Appointment editedAppointment = new AppointmentBuilder(APPOINTMENT_1).withEndTime("2023-09-11 08:30").build();
        modelManager.addAppointment(APPOINTMENT_1);
        modelManager.setAppointment(APPOINTMENT_1, editedAppointment);
        assertFalse(modelManager.hasAppointment(APPOINTMENT_1));
    }

    @Test
    public void hasDoctor_doctorWithDifferentName_returnsTrue() throws CommandException {
        Doctor editedAlice = new DoctorBuilder(ALICE).withName(VALID_NAME_BOB).build();
        modelManager.addDoctor(ALICE);
        modelManager.setDoctor(ALICE, editedAlice);
        assertTrue(modelManager.hasDoctor(ALICE));
    }

    @Test
    public void hasPatient_patientWithDifferentName_returnsTrue() throws CommandException {
        Patient editedCarl = new PatientBuilder(CARL).withName(VALID_NAME_BOB).build();
        modelManager.addPatient(CARL);
        modelManager.setPatient(CARL, editedCarl);
        assertTrue(modelManager.hasPatient(CARL));
    }

    @Test
    public void deleteAppointment_appointmentNotInDatabase_throwsItemNotFoundException() {
        assertThrows(ItemNotFoundException.class, () -> modelManager.deleteAppointment(APPOINTMENT_1));
    }

    @Test
    public void deleteDoctor_doctorNotInDatabase_throwsItemNotFoundException() {
        assertThrows(ItemNotFoundException.class, () -> modelManager.deleteDoctor(ALICE));
    }

    @Test
    public void deletePatient_patientNotInDatabase_throwsItemNotFoundException() {
        assertThrows(ItemNotFoundException.class, () -> modelManager.deletePatient(CARL));
    }

    @Test
    public void setAppointment_nullAppointment_throwsNullPointerException() throws CommandException {
        modelManager.addAppointment(APPOINTMENT_1);
        assertThrows(NullPointerException.class, () -> modelManager.setAppointment(APPOINTMENT_1, null));
    }

    @Test
    public void setDoctor_nullDoctor_throwsNullPointerException() {
        modelManager.addDoctor(ALICE);
        assertThrows(NullPointerException.class, () -> modelManager.setDoctor(ALICE, null));
    }

    @Test
    public void setPatient_nullPatient_throwsNullPointerException() {
        modelManager.addPatient(CARL);
        assertThrows(NullPointerException.class, () -> modelManager.setPatient(CARL, null));
    }

    @Test
    public void getFilteredAppointmentList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredAppointmentList()
                .remove(0));
    }

    @Test
    public void getFilteredDoctorList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredDoctorList()
                .remove(0));
    }

    @Test
    public void getFilteredPatientList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredPatientList()
                .remove(0));
    }

    @Test
    public void updateSelectedAppointment_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.updateSelectedAppointment(null));
    }

    @Test
    public void updateSelectedDoctor_nullDoctor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.updateSelectedDoctor(null));
    }

    @Test
    public void updateSelectedPatient_nullPatient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.updateSelectedPatient(null));
    }

    @Test
    public void updateSelectedAppointment_validAppointment_success() {
        modelManager.updateSelectedAppointment(APPOINTMENT_1);
        assertTrue(modelManager.getSelectedAppointment().equals(APPOINTMENT_1));
    }

    @Test
    public void updateSelectedDoctor_validDoctor_success() {
        modelManager.updateSelectedDoctor(ALICE);
        assertTrue(modelManager.getSelectedDoctor().equals(ALICE));
    }

    @Test
    public void updateSelectedPatient_validPatient_success() {
        modelManager.updateSelectedPatient(CARL);
        assertTrue(modelManager.getSelectedPatient().equals(CARL));
    }

    @Test
    public void equals() {
        Database database = new Database();
        Database differentDatabase = new Database();
        differentDatabase.addDoctor(ALICE);
        differentDatabase.addPatient(CARL);
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(database, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(database, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different addressBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentDatabase, userPrefs)));
    }
}
