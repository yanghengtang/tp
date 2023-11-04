package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAppointment.APPOINTMENT_1;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.Messages;
import seedu.address.model.Database;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyDatabase;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UniqueItemList;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Nric;
import seedu.address.model.person.doctor.Doctor;
import seedu.address.model.person.patient.Patient;
import seedu.address.testutil.AppointmentBuilder;

public class AddAppointmentCommandTest {

    @Test
    public void constructor_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddAppointmentCommand(null));
    }

    @Test
    public void execute_appointmentAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingAppointmentAdded modelStub = new ModelStubAcceptingAppointmentAdded();
        Appointment validAppointment = new AppointmentBuilder().build();

        CommandResult commandResult = new AddAppointmentCommand(validAppointment).execute(modelStub);

        assertEquals(String.format(AddAppointmentCommand.MESSAGE_SUCCESS, Messages.format(validAppointment)),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validAppointment), modelStub.appointmentsAdded);
    }

    @Test
    public void equals() {
        Appointment appointment1 = new AppointmentBuilder().withPatientNric("T1234567N").build();
        Appointment appointment2 = new AppointmentBuilder().withPatientNric("T2468123N").build();
        AddAppointmentCommand addAppointment1Command = new AddAppointmentCommand(appointment1);
        AddAppointmentCommand addAppointment2Command = new AddAppointmentCommand(appointment2);

        // same object -> returns true
        assertTrue(addAppointment1Command.equals(addAppointment1Command));

        // same values -> returns true
        AddAppointmentCommand addAppointment1CommandCopy = new AddAppointmentCommand(appointment1);
        assertTrue(addAppointment1Command.equals(addAppointment1CommandCopy));

        // different types -> returns false
        assertFalse(addAppointment1Command.equals(1));

        // null -> returns false
        assertFalse(addAppointment1Command.equals(null));

        // different person -> returns false
        assertFalse(addAppointment1Command.equals(addAppointment2Command));
    }

    @Test
    public void hashCodeMethod() {
        AddAppointmentCommand command = new AddAppointmentCommand(APPOINTMENT_1);
        AddAppointmentCommand anotherCommand = new AddAppointmentCommand(APPOINTMENT_1);

        // same value -> returns same hashcode
        assertEquals(command.hashCode(), Objects.hash(APPOINTMENT_1));

        // command with same appointment object -> return same hashcode
        assertEquals(command.hashCode(), anotherCommand.hashCode());
    }

    @Test
    public void toStringMethod() {
        AddAppointmentCommand addAppointmentCommand = new AddAppointmentCommand(APPOINTMENT_1);
        String expected = AddAppointmentCommand.class.getCanonicalName() + "{toAdd=" + APPOINTMENT_1 + "}";
        assertEquals(expected, addAppointmentCommand.toString());
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
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
        public void setDatabase(ReadOnlyDatabase newDatabase) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyDatabase getDatabase() {
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
        public boolean hasPatient(Patient patient) {
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
        public void deleteAppointment(Appointment target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteDoctor(Doctor target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePatient(Patient target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addAppointment(Appointment appointment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addDoctor(Doctor doctor) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPatient(Patient patient) {
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
        public void setPatient(Patient target, Patient editedPatient) {
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
        public void updateFilteredAppointmentList(Predicate<Appointment> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredDoctorList(Predicate<Doctor> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPatientList(Predicate<Patient> predicate) {
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
     * A Model stub that contains a single person.
     */
    private class ModelStubWithAppointment extends ModelStub {
        private final Appointment appointment;
        private final Patient patient;
        private final Doctor doctor;

        ModelStubWithAppointment(Appointment appointment, Patient patient, Doctor doctor) {
            requireNonNull(appointment);
            this.appointment = appointment;
            this.patient = patient;
            this.doctor = doctor;
        }

        @Override
        public boolean hasAppointment(Appointment appointment) {
            requireNonNull(appointment);
            return this.appointment.isSame(appointment);
        }

        @Override
        public boolean hasDoctorWithNric(Nric nric) {
            return this.doctor.getNric().equals(nric);
        }

        @Override
        public boolean hasPatientWithNric(Nric nric) {
            return this.patient.getNric().equals(nric);
        }
    }

    /**
     * A Model stub that always accept the appointment being added.
     */
    private class ModelStubAcceptingAppointmentAdded extends ModelStub {
        final ArrayList<Appointment> appointmentsAdded = new ArrayList<>();

        @Override
        public boolean hasAppointment(Appointment appointment) {
            requireNonNull(appointment);
            return appointmentsAdded.stream().anyMatch(appointment::isSame);
        }

        @Override
        public void addAppointment(Appointment appointment) {
            requireNonNull(appointment);
            appointmentsAdded.add(appointment);
        }

        @Override
        public boolean hasDoctorWithNric(Nric nric) {
            return true;
        }

        @Override
        public boolean hasPatientWithNric(Nric nric) {
            return true;
        }

        public ObservableList<Appointment> getFilteredAppointmentList() {
            return new FilteredList<>(new UniqueItemList<Appointment>().asUnmodifiableObservableList());
        }

        @Override
        public ReadOnlyDatabase getDatabase() {
            return new Database();
        }
    }
}
