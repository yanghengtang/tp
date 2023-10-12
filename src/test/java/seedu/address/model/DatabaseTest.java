package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAppointment.APPOINTMENT_1;
import static seedu.address.testutil.TypicalDoctor.ALICE;
import static seedu.address.testutil.TypicalDoctor.BENSON;
import static seedu.address.testutil.TypicalDoctor.getTypicalDoctor;
import static seedu.address.testutil.TypicalPatient.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Nric;
import seedu.address.model.person.doctor.Doctor;
import seedu.address.model.person.patient.Patient;
import seedu.address.testutil.DoctorBuilder;
import seedu.address.testutil.PatientBuilder;

public class DatabaseTest {
    private final Database database = new Database();

    @Test
    public void emptyConstructor() {
        assertEquals(Collections.emptyList(), database.getAppointmentList());
        assertEquals(Collections.emptyList(), database.getDoctorList());
        assertEquals(Collections.emptyList(), database.getPatientList());
    }

    @Test
    public void copyConstructor() {
        List<Appointment> appointmentList = new ArrayList<>();
        List<Doctor> doctorList = getTypicalDoctor();
        List<Patient> patientList = getTypicalPatient();
        DatabaseStub newData = new DatabaseStub(appointmentList, doctorList, patientList);

        Database newDatabase = new Database(newData);
        assertEquals(appointmentList, newDatabase.getAppointmentList());
        assertEquals(doctorList, newDatabase.getDoctorList());
        assertEquals(patientList, newDatabase.getPatientList());
    }

    @Test
    public void hasAppointment_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> database.hasAppointment(null));
    }

    @Test
    public void hasDoctor_nullDoctor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> database.hasDoctor(null));
    }

    @Test
    public void hasPatient_nullPatient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> database.hasPatient(null));
    }

    @Test
    public void hasAppointment_appointmentNotInDatabase_returnsFalse() {
        assertFalse(database.hasAppointment(APPOINTMENT_1));
    }

    @Test
    public void hasDoctor_doctorNotInDatabase_returnsFalse() {
        assertFalse(database.hasDoctor(ALICE));
    }

    @Test
    public void hasPatient_patientNotInDatabase_returnsFalse() {
        assertFalse(database.hasPatient(CARL));
    }

    @Test
    public void hasAppointment_appointmentInDatabase_returnsTrue() {
        database.addAppointment(APPOINTMENT_1);
        assertTrue(database.hasAppointment(APPOINTMENT_1));
    }

    @Test
    public void hasDoctor_doctorInDatabase_returnsTrue() {
        database.addDoctor(ALICE);
        assertTrue(database.hasDoctor(ALICE));
    }

    @Test
    public void hasPatient_patientInDatabase_returnsTrue() {
        database.addPatient(CARL);
        assertTrue(database.hasPatient(CARL));
    }

    @Test
    public void hasDoctor_doctorWithSameNricInDatabase_returnsTrue() {
        database.addDoctor(BENSON);
        Doctor editedBenson = new DoctorBuilder(BENSON).withName(VALID_NAME_BOB).build();
        database.setDoctor(BENSON, editedBenson);
        assertTrue(database.hasDoctor(editedBenson));
    }

    @Test
    public void hasPatient_patientWithSameNricInDatabase_returnsTrue() {
        database.addPatient(DANIEL);
        Patient editedDaniel = new PatientBuilder(DANIEL).withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB).build();
        database.setPatient(DANIEL, editedDaniel);
        assertTrue(database.hasPatient(editedDaniel));
    }

    @Test
    public void hasAppointment_appointmentRemovedFromDatabase_returnsFalse() {
        database.addAppointment(APPOINTMENT_1);
        database.removeAppointment(APPOINTMENT_1);
        assertFalse(database.hasAppointment(APPOINTMENT_1));
    }

    @Test
    public void hasDoctor_doctorRemovedFromDatabase_returnsFalse() {
        database.addDoctor(ALICE);
        database.removeDoctor(ALICE);
        assertFalse(database.hasDoctor(ALICE));
    }

    @Test
    public void hasPatient_patientRemovedFromDatabase_returnsFalse() {
        database.addPatient(CARL);
        database.removePatient(CARL);
        assertFalse(database.hasPatient(CARL));
    }

    @Test
    public void getAppointmentList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> database.getAppointmentList().remove(0));
    }

    @Test
    public void getDoctorList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> database.getDoctorList().remove(0));
    }

    @Test
    public void getPatientList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> database.getPatientList().remove(0));
    }

    @Test
    public void hasDoctorWithNric_nullNric_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> database.hasDoctorWithNric(null));
    }

    @Test
    public void hasDoctorWithNric_doctorNotInDatabase_returnsFalse() {
        assertFalse(database.hasDoctorWithNric(new Nric(ALICE_NRIC)));
    }

    @Test
    public void hasDoctorWithNric_doctorInDatabase_returnsTrue() {
        database.addDoctor(ALICE);
        assertTrue(database.hasDoctorWithNric(new Nric(ALICE_NRIC)));
    }

    @Test
    public void hasDoctorWithNric_doctorWithSameNricInDatabase_returnsTrue() {
        database.addDoctor(BENSON);
        Doctor editedBenson = new DoctorBuilder(BENSON).withName(VALID_NAME_BOB).build();
        database.setDoctor(BENSON, editedBenson);
        assertTrue(database.hasDoctorWithNric(new Nric(BENSON_NRIC)));
    }

    @Test
    public void hasDoctorWithNric_doctorRemovedFromDatabase_returnsFalse() {
        database.addDoctor(ALICE);
        database.removeDoctor(ALICE);
        assertFalse(database.hasDoctorWithNric(new Nric(ALICE_NRIC)));
    }

    @Test
    public void equals() {
        // same object -> returns true
        assertTrue(database.equals(database));

        // same values -> returns true
        Database newDatabase = new Database(database);
        assertTrue(database.equals(newDatabase));

        // null -> returns false
        assertFalse(database.equals(null));

        // different type -> returns false
        assertFalse(database.equals(5.0));

        // different doctorList -> return false;
        List<Doctor> doctorList = getTypicalDoctor();
        newDatabase = new Database();
        newDatabase.setDoctors(doctorList);
        assertFalse(database.equals(newDatabase));

        // different patientList -> return false;
        List<Patient> patientList = getTypicalPatient();
        newDatabase = new Database();
        newDatabase.setPatients(patientList);
        assertFalse(database.equals(newDatabase));
    }

    @Test
    public void toStringMethod() {
        String expected = Database.class.getCanonicalName() + "{appointments=" + database.getAppointmentList()
                + ", doctors=" + database.getDoctorList()
                + ", patients=" + database.getPatientList() + "}";
        assertEquals(expected, database.toString());
    }

    /**
     * A stub ReadOnlyDatabase whose persons list can violate interface constraints.
     */
    private static class DatabaseStub implements ReadOnlyDatabase {
        private final ObservableList<Appointment> appointments = FXCollections.observableArrayList();
        private final ObservableList<Doctor> doctors = FXCollections.observableArrayList();
        private final ObservableList<Patient> patients = FXCollections.observableArrayList();

        DatabaseStub(Collection<Appointment> appointments, Collection<Doctor> doctors, Collection<Patient> patients) {
            this.appointments.setAll(appointments);
            this.doctors.setAll(doctors);
            this.patients.setAll(patients);
        }

        @Override
        public ObservableList<Appointment> getAppointmentList() {
            return appointments;
        }

        @Override
        public ObservableList<Doctor> getDoctorList() {
            return doctors;
        }

        @Override
        public ObservableList<Patient> getPatientList() {
            return patients;
        }
    }
}
