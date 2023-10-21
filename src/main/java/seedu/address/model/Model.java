package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Nric;
import seedu.address.model.person.doctor.Doctor;
import seedu.address.model.person.patient.Patient;

/**
 * The API of the Model component.
 */
public interface Model {

    /** {@code Predicate} that always evaluate to true */
    Predicate<Appointment> PREDICATE_SHOW_ALL_APPOINTMENTS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Doctor> PREDICATE_SHOW_ALL_DOCTORS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Patient> PREDICATE_SHOW_ALL_PATIENTS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' database file path.
     */
    Path getDatabaseFilePath();


    /**
     * Sets the user prefs' database file path.
     */
    void setDatabaseFilePath(Path addressBookFilePath);


    /**
     * Replaces current Database data with the data in {@code database}.
     */
    void setDatabase(ReadOnlyDatabase database);

    /** Returns the Database */
    ReadOnlyDatabase getDatabase();

    /**
     * Returns true if an appointment with the same fields as {@code appointment} exists in the database.
     */
    boolean hasAppointment(Appointment appointment);

    /**
     * Returns true if a doctor with the same NRIC as {@code doctor} exists in the database.
     */
    boolean hasDoctor(Doctor doctor);

    /**
     * Returns true if a patient with the same NRIC as {@code patient} exists in the database.
     */
    boolean hasPatient(Patient patient);

    /**
     * Returns true if a doctor with the same NRIC as {@code doctor} exists in the database.
     */
    boolean hasDoctorWithNric(Nric nric);

    /**
     * Returns true if a patient with the same NRIC as {@code patient} exists in the database.
     */
    boolean hasPatientWithNric(Nric nric);

    /**
     * Deletes the given appointment.
     * The appointment must exist in the database.
     */
    void deleteAppointment(Appointment target);

    /**
     * Deletes the given doctor.
     * The doctor must exist in the database.
     */
    void deleteDoctor(Doctor target);

    /**
     * Deletes the given patient.
     * The patient must exist in the database.
     */
    void deletePatient(Patient target);

    /**
     * Adds the given appointment.
     * {@code appointment} must not already exist in the database.
     */
    void addAppointment(Appointment appointment);

    /**
     * Adds the given doctor.
     * {@code doctor} must not already exist in the database.
     */
    void addDoctor(Doctor doctor);

    /**
     * Adds the patient doctor.
     * {@code patient} must not already exist in the database.
     */
    void addPatient(Patient patient);

    /**
     * Replaces the given appointment {@code target} with {@code editedAppointment}.
     * {@code target} must exist in the database.
     * The doctor, patient and dateTime of {@code editedAppointment} must not be the same as another existing
     * appointment in the database.
     */
    void setAppointment(Appointment target, Appointment editedAppointment);

    /**
     * Replaces the given doctor {@code target} with {@code editedDoctor}.
     * {@code target} must exist in the database.
     * The NRIC of {@code editedDoctor} must not be the same as another existing doctor in the database.
     */
    void setDoctor(Doctor target, Doctor editedDoctor);

    /**
     * Replaces the given patient {@code target} with {@code editedPatient}.
     * {@code target} must exist in the database.
     * The NRIC of {@code editedPatient} must not be the same as another existing patient in the database.
     */
    void setPatient(Patient target, Patient editedPatient);

    /** Returns an unmodifiable view of the filtered appointment list */
    ObservableList<Appointment> getFilteredAppointmentList();

    /** Returns an unmodifiable view of the filtered doctor list */
    ObservableList<Doctor> getFilteredDoctorList();

    /** Returns an unmodifiable view of the filtered patient list */
    ObservableList<Patient> getFilteredPatientList();

    /**
     * Updates the filter of the filtered appointment list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredAppointmentList(Predicate<Appointment> predicate);

    /**
     * Updates the filter of the filtered doctor list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredDoctorList(Predicate<Doctor> predicate);

    /**
     * Updates the filter of the filtered patient list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPatientList(Predicate<Patient> predicate);
}
