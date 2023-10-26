package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Nric;
import seedu.address.model.person.doctor.Doctor;
import seedu.address.model.person.patient.Patient;

/**
 * Represents the in-memory model of the MediConnect data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);
    private final Database database;
    private final UserPrefs userPrefs;
    private final FilteredList<Appointment> filteredAppointments;
    private final FilteredList<Doctor> filteredDoctors;
    private final FilteredList<Patient> filteredPatients;
    private Appointment selectedAppointment;
    private Doctor selectedDoctor;
    private Patient selectedPatient;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyDatabase database, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(database, userPrefs);

        logger.fine("Initializing with database: " + database + " and user prefs " + userPrefs);

        this.database = new Database(database);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredAppointments = new FilteredList<>(this.database.getAppointmentList());
        filteredDoctors = new FilteredList<>(this.database.getDoctorList());
        filteredPatients = new FilteredList<>(this.database.getPatientList());
    }

    public ModelManager() {
        this(new Database(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getDatabaseFilePath() {
        return userPrefs.getDatabaseFilePath();
    }

    @Override
    public void setDatabaseFilePath(Path databaseFilePath) {
        requireNonNull(databaseFilePath);
        userPrefs.setDatabaseFilePath(databaseFilePath);
    }

    //=========== Database ================================================================================

    @Override
    public void setDatabase(ReadOnlyDatabase database) {
        this.database.setDoctors(database.getDoctorList());
        this.database.setPatients(database.getPatientList());
        this.database.setAppointments(database.getAppointmentList());
    }

    @Override
    public ReadOnlyDatabase getDatabase() {
        return database;
    }

    @Override
    public boolean hasAppointment(Appointment appointment) {
        requireNonNull(appointment);
        return database.hasAppointment(appointment);
    }

    @Override
    public boolean hasDoctor(Doctor doctor) {
        requireNonNull(doctor);
        return database.hasDoctor(doctor);
    }

    @Override
    public boolean hasPatient(Patient patient) {
        requireNonNull(patient);
        return database.hasPatient(patient);
    }

    @Override
    public boolean hasDoctorWithNric(Nric nric) {
        requireNonNull(nric);
        return database.hasDoctorWithNric(nric);
    }

    @Override
    public boolean hasPatientWithNric(Nric nric) {
        requireNonNull(nric);
        return database.hasPatientWithNric(nric);
    }

    @Override
    public void deleteAppointment(Appointment appointment) {
        database.removeAppointment(appointment);
    }

    @Override
    public void deleteDoctor(Doctor doctor) {
        database.removeDoctor(doctor);
    }

    @Override
    public void deletePatient(Patient patient) {
        database.removePatient(patient);
    }

    @Override
    public void addAppointment(Appointment appointment) throws CommandException {
        database.addAppointment(appointment);
        updateFilteredAppointmentList(PREDICATE_SHOW_ALL_APPOINTMENTS);
    }

    @Override
    public void addDoctor(Doctor doctor) {
        database.addDoctor(doctor);
        updateFilteredDoctorList(PREDICATE_SHOW_ALL_DOCTORS);
    }

    @Override
    public void addPatient(Patient patient) {
        database.addPatient(patient);
        updateFilteredPatientList(PREDICATE_SHOW_ALL_PATIENTS);
    }

    @Override
    public void setAppointment(Appointment target, Appointment editedAppointment) throws CommandException {
        requireAllNonNull(target, editedAppointment);
        database.setAppointment(target, editedAppointment);
    }

    @Override
    public void setDoctor(Doctor target, Doctor editedDoctor) throws CommandException {
        requireAllNonNull(target, editedDoctor);

        database.setDoctor(target, editedDoctor);
    }

    @Override
    public void setPatient(Patient target, Patient editedPatient) throws CommandException {
        requireAllNonNull(target, editedPatient);

        database.setPatient(target, editedPatient);
    }

    //=========== Filtered List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Appointment} backed by the internal list of
     * {@code versionedDatabase}
     */
    @Override
    public ObservableList<Appointment> getFilteredAppointmentList() {
        return filteredAppointments;
    }

    /**
     * Returns an unmodifiable view of the list of {@code Doctor} backed by the internal list of
     * {@code versionedDatabase}
     */
    @Override
    public ObservableList<Doctor> getFilteredDoctorList() {
        return filteredDoctors;
    }

    /**
     * Returns an unmodifiable view of the list of {@code Patient} backed by the internal list of
     * {@code versionedDatabase}
     */
    @Override
    public ObservableList<Patient> getFilteredPatientList() {
        return filteredPatients;
    }

    @Override
    public void updateFilteredAppointmentList(Predicate<Appointment> predicate) {
        requireNonNull(predicate);
        filteredAppointments.setPredicate(predicate);
    }

    @Override
    public void updateFilteredDoctorList(Predicate<Doctor> predicate) {
        requireNonNull(predicate);
        filteredDoctors.setPredicate(predicate);
    }

    @Override
    public void updateFilteredPatientList(Predicate<Patient> predicate) {
        requireNonNull(predicate);
        filteredPatients.setPredicate(predicate);
    }

    //=========== Selected Entity Accessors ===========================================================

    @Override
    public Appointment getSelectedAppointment() {
        return selectedAppointment;
    }

    @Override
    public Doctor getSelectedDoctor() {
        return selectedDoctor;
    }

    @Override
    public Patient getSelectedPatient() {
        return selectedPatient;
    }

    @Override
    public void updateSelectedAppointment(Appointment appointment) {
        requireNonNull(appointment);
        selectedAppointment = appointment;
    }

    @Override
    public void updateSelectedDoctor(Doctor doctor) {
        requireNonNull(doctor);
        selectedDoctor = doctor;
    }

    @Override
    public void updateSelectedPatient(Patient patient) {
        requireNonNull(patient);
        selectedPatient = patient;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModelManager)) {
            return false;
        }

        ModelManager otherModelManager = (ModelManager) other;
        return database.equals(otherModelManager.getDatabase())
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredAppointments.equals(otherModelManager.filteredAppointments)
                && filteredDoctors.equals(otherModelManager.filteredDoctors)
                && filteredPatients.equals(otherModelManager.filteredPatients);
    }

}
