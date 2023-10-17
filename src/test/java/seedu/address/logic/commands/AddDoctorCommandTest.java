package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalDoctor.ALICE;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Database;
import seedu.address.model.NewModel;
import seedu.address.model.ReadOnlyDatabase;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Nric;
import seedu.address.model.person.doctor.Doctor;
import seedu.address.model.person.patient.Patient;
import seedu.address.testutil.DoctorBuilder;

public class AddDoctorCommandTest {

    @Test
    public void constructor_nullDoctor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddDoctorCommand(null));
    }

    @Test
    public void execute_doctorAcceptedByModel_addSuccessful() throws Exception {
        NewModelStubAcceptingDoctorAdded newModelStub = new NewModelStubAcceptingDoctorAdded();
        Doctor validDoctor = new DoctorBuilder().build();

        CommandResult commandResult = new AddDoctorCommand(validDoctor).execute(newModelStub);

        assertEquals(String.format(AddDoctorCommand.MESSAGE_SUCCESS, Messages.format(validDoctor)),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validDoctor), newModelStub.doctorsAdded);
    }

    @Test
    public void execute_duplicateDoctor_throwsCommandException() {
        Doctor validDoctor = new DoctorBuilder().build();
        AddDoctorCommand addDoctorCommand = new AddDoctorCommand(validDoctor);
        NewModelStub newModelStub = new NewModelStubWithDoctor(validDoctor);

        assertThrows(AssertionError.class,
                NewModelStub.MESSAGE, () -> addDoctorCommand.execute(newModelStub));
    }

    @Test
    public void equals() {
        Doctor alice = new DoctorBuilder().withName("Alice").build();
        Doctor bob = new DoctorBuilder().withName("Bob").build();
        AddDoctorCommand addAliceCommand = new AddDoctorCommand(alice);
        AddDoctorCommand addBobCommand = new AddDoctorCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddDoctorCommand addAliceCommandCopy = new AddDoctorCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different doctor -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    @Test
    public void toStringMethod() {
        AddDoctorCommand addDoctorCommand = new AddDoctorCommand(ALICE);
        String expected = AddDoctorCommand.class.getCanonicalName() + "{toAdd=" + ALICE + "}";
        assertEquals(expected, addDoctorCommand.toString());
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class NewModelStub implements NewModel {
        static final String MESSAGE = "This method should not be called.";
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Returns the user prefs.
         */
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Returns the user prefs' GUI settings.
         */
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Sets the user prefs' GUI settings.
         */
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Returns the user prefs' database file path.
         */
        public Path getDatabaseFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Sets the user prefs' database file path.
         */
        public void setDatabaseFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }


        /**
         * Replaces current Database data with the data in {@code database}.
         */
        public void setDatabase(ReadOnlyDatabase database) {
            throw new AssertionError("This method should not be called.");
        }

        /** Returns the Database */
        public ReadOnlyDatabase getDatabase() {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Returns true if an appointment with the same fields as {@code appointment} exists in the database.
         */
        public boolean hasAppointment(Appointment appointment) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Returns true if a doctor with the same NRIC as {@code doctor} exists in the database.
         */
        public boolean hasDoctor(Doctor doctor) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Returns true if a patient with the same NRIC as {@code patient} exists in the database.
         */
        public boolean hasPatient(Patient patient) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Returns true if a doctor with the same NRIC as {@code doctor} exists in the database.
         */
        public boolean hasDoctorWithNric(Nric nric) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Returns true if a patient with the same NRIC as {@code patient} exists in the database.
         */
        public boolean hasPatientWithNric(Nric nric) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Deletes the given appointment.
         * The appointment must exist in the database.
         */
        public void deleteAppointment(Appointment target) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Deletes the given doctor.
         * The doctor must exist in the database.
         */
        public void deleteDoctor(Doctor target) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Deletes the given patient.
         * The patient must exist in the database.
         */
        public void deletePatient(Patient target) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Adds the given appointment.
         * {@code appointment} must not already exist in the database.
         */
        public void addAppointment(Appointment appointment) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Adds the given doctor.
         * {@code doctor} must not already exist in the database.
         */
        public void addDoctor(Doctor doctor) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Adds the patient doctor.
         * {@code patient} must not already exist in the database.
         */
        public void addPatient(Patient patient) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Replaces the given appointment {@code target} with {@code editedAppointment}.
         * {@code target} must exist in the database.
         * The doctor, patient and dateTime of {@code editedAppointment} must not be the same as another existing
         * appointment in the database.
         */
        public void setAppointment(Appointment target, Appointment editedAppointment) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Replaces the given doctor {@code target} with {@code editedDoctor}.
         * {@code target} must exist in the database.
         * The NRIC of {@code editedDoctor} must not be the same as another existing doctor in the database.
         */
        public void setDoctor(Doctor target, Doctor editedDoctor) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Replaces the given patient {@code target} with {@code editedPatient}.
         * {@code target} must exist in the database.
         * The NRIC of {@code editedPatient} must not be the same as another existing patient in the database.
         */
        public void setPatient(Patient target, Patient editedPatient) {
            throw new AssertionError("This method should not be called.");
        }

        /** Returns an unmodifiable view of the filtered appointment list */
        public ObservableList<Appointment> getFilteredAppointmentList() {
            throw new AssertionError("This method should not be called.");
        }

        /** Returns an unmodifiable view of the filtered doctor list */
        public ObservableList<Doctor> getFilteredDoctorList() {
            throw new AssertionError("This method should not be called.");
        }

        /** Returns an unmodifiable view of the filtered patient list */
        public ObservableList<Patient> getFilteredPatientList() {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Updates the filter of the filtered appointment list to filter by the given {@code predicate}.
         * @throws NullPointerException if {@code predicate} is null.
         */
        public void updateFilteredAppointmentList(Predicate<Appointment> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Updates the filter of the filtered doctor list to filter by the given {@code predicate}.
         * @throws NullPointerException if {@code predicate} is null.
         */
        public void updateFilteredDoctorList(Predicate<Doctor> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Updates the filter of the filtered patient list to filter by the given {@code predicate}.
         * @throws NullPointerException if {@code predicate} is null.
         */
        @Override
        public void updateFilteredPatientList(Predicate<Patient> predicate) {
            throw new AssertionError("This method should not be called.");
        }


    }

    /**
     * A Model stub that contains a single doctor.
     */
    private class NewModelStubWithDoctor extends NewModelStub {
        private final Doctor doctor;

        NewModelStubWithDoctor(Doctor doctor) {
            requireNonNull(doctor);
            this.doctor = doctor;
        }

        @Override
        public boolean hasDoctor(Doctor doctor) {
            requireNonNull(doctor);
            return this.doctor.isSame(doctor);
        }
    }

    /**
     * A Model stub that always accept the doctor being added.
     */
    private class NewModelStubAcceptingDoctorAdded extends NewModelStub {
        final ArrayList<Doctor> doctorsAdded = new ArrayList<>();

        @Override
        public boolean hasDoctor(Doctor doctor) {
            requireNonNull(doctor);
            return doctorsAdded.stream().anyMatch(doctor::isSame);
        }

        @Override
        public void addDoctor(Doctor doctor) {
            requireNonNull(doctor);
            doctorsAdded.add(doctor);
        }

        @Override
        public ReadOnlyDatabase getDatabase() {
            return new Database();
        }
    }

}
