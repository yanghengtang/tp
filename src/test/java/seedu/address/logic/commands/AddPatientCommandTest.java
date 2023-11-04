package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPatient.ALICE;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.Messages;
import seedu.address.model.Database;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyDatabase;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Nric;
import seedu.address.model.person.doctor.Doctor;
import seedu.address.model.person.patient.Patient;
import seedu.address.testutil.PatientBuilder;
public class AddPatientCommandTest {

    @Test
    public void hashcode() {
        AddPatientCommand command = new AddPatientCommand(ALICE);

        // same value -> returns same hashcode
        assertEquals(command.hashCode(), Objects.hash(ALICE));
    }

    @Test
    public void constructor_nullPatient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddPatientCommand(null));
    }

    @Test
    public void execute_patientAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPatientAdded modelStub = new ModelStubAcceptingPatientAdded();
        Patient validPatient = new PatientBuilder().build();

        CommandResult commandResult = new AddPatientCommand(validPatient).execute(modelStub);

        assertEquals(String.format(AddPatientCommand.MESSAGE_SUCCESS, Messages.format(validPatient)),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validPatient), modelStub.patientsAdded);
    }

    @Test
    public void execute_duplicatePatient_throwsCommandException() {
        Patient validPatient = new PatientBuilder().build();
        AddPatientCommand addPatientCommand = new AddPatientCommand(validPatient);
        ModelStub modelStub = new ModelStubWithPatient(validPatient);

        assertThrows(AssertionError.class,
                ModelStub.MESSAGE, () -> addPatientCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Patient alice = new PatientBuilder().withName("Alice").build();
        Patient bob = new PatientBuilder().withName("Bob").build();
        AddPatientCommand addAliceCommand = new AddPatientCommand(alice);
        AddPatientCommand addBobCommand = new AddPatientCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddPatientCommand addAliceCommandCopy = new AddPatientCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different person -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    @Test
    public void toStringMethod() {
        AddPatientCommand addPatientCommand = new AddPatientCommand(ALICE);
        String expected = AddPatientCommand.class.getCanonicalName() + "{toAdd=" + ALICE + "}";
        assertEquals(expected, addPatientCommand.toString());
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        static final String MESSAGE = "This method should not be called.";
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public void deleteAppointment(Appointment appointment) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public void deleteDoctor(Doctor doctor) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasAppointment(Appointment appointment) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public boolean hasDoctor(Doctor doctor) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public boolean hasDoctorWithNric(Nric nric) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public boolean hasPatientWithNric(Nric nric) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getDatabaseFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setDatabaseFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPatient(Patient patient) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public void addDoctor(Doctor doctor) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addAppointment(Appointment appointment) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public void setDatabase(ReadOnlyDatabase database) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyDatabase getDatabase() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPatient(Patient patient) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePatient(Patient target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPatient(Patient target, Patient editedPatient) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAppointment(Appointment target, Appointment editedAppointment) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public void setDoctor(Doctor target, Doctor editedDoctor) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public ObservableList<Appointment> getFilteredAppointmentList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Doctor> getFilteredDoctorList() {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public ObservableList<Patient> getFilteredPatientList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPatientList(Predicate<Patient> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredAppointmentList(Predicate<Appointment> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredDoctorList(Predicate<Doctor> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Appointment getSelectedAppointment() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Doctor getSelectedDoctor() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Patient getSelectedPatient() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateSelectedAppointment(Appointment appointment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateSelectedDoctor(Doctor doctor) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateSelectedPatient(Patient patient) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single patient.
     */
    private class ModelStubWithPatient extends ModelStub {
        private final Patient patient;

        ModelStubWithPatient(Patient patient) {
            requireNonNull(patient);
            this.patient = patient;
        }

        @Override
        public boolean hasPatient(Patient patient) {
            requireNonNull(patient);
            return this.patient.isSame(patient);
        }
    }

    /**
     * A Model stub that always accept the patient being added.
     */
    private class ModelStubAcceptingPatientAdded extends ModelStub {
        final ArrayList<Patient> patientsAdded = new ArrayList<>();

        @Override
        public boolean hasPatient(Patient patient) {
            requireNonNull(patient);
            return patientsAdded.stream().anyMatch(patient::isSame);
        }

        @Override
        public void addPatient(Patient patient) {
            requireNonNull(patient);
            patientsAdded.add(patient);
        }

        @Override
        public ReadOnlyDatabase getDatabase() {
            return new Database();
        }
    }

}
