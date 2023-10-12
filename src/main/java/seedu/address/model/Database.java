package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Objects;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Nric;
import seedu.address.model.person.doctor.Doctor;
import seedu.address.model.person.patient.Patient;
import seedu.address.model.person.patient.UniqueItemList;

/**
 * Wraps all data at the system level
 * Duplicates are not allowed (by .isSame comparison)
 */
public class Database implements ReadOnlyDatabase {
    private final UniqueItemList<Appointment> appointments;
    private final UniqueItemList<Doctor> doctors;
    private final UniqueItemList<Patient> patients;

    /**
     * Creates a new empty Database.
     */
    public Database() {
        appointments = new UniqueItemList<>();
        doctors = new UniqueItemList<>();
        patients = new UniqueItemList<>();
    }

    /**
     * Creates a Database using the Persons in the {@code toBeCopied}
     */
    public Database(ReadOnlyDatabase toBeCopied) {
        this();
        setAppointments(toBeCopied.getAppointmentList());
        setDoctors(toBeCopied.getDoctorList());
        setPatients(toBeCopied.getPatientList());
    }

    /**
     * Replaces the contents of the appointment list with {@code appointments}.
     * {@code appointments} must not contain duplicate appointments.
     */
    public void setAppointments(List<Appointment> appointments) {
        this.appointments.setItems(appointments);
    }

    /**
     * Replaces the contents of the doctor list with {@code doctors}.
     * {@code doctors} must not contain duplicate doctors.
     */
    public void setDoctors(List<Doctor> doctors) {
        this.doctors.setItems(doctors);
    }

    /**
     * Replaces the contents of the doctor list with {@code doctors}.
     * {@code doctors} must not contain duplicate doctors.
     */
    public void setPatients(List<Patient> patients) {
        this.patients.setItems(patients);
    }

    /**
     * Returns true if an appointment with the same key fields as {@code appointment} exists in the database.
     */
    public boolean hasAppointment(Appointment appointment) {
        requireNonNull(appointment);
        return appointments.contains(appointment);
    }

    /**
     * Adds an appointment to the database.
     * The appointment must not already exist in the database.
     */
    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    /**
     * Replaces the given appointment {@code target} in the list with {@code editedAppointment}.
     * {@code target} must exist in the database.
     * The key fields of {@code editedAppointment} must not be the same as another existing appointment in the database.
     */
    public void setAppointment(Appointment target, Appointment editedAppointment) {
        requireNonNull(editedAppointment);

        appointments.setItem(target, editedAppointment);
    }

    /**
     * Removes {@code appointment} from this {@code Database}.
     * {@code appointment} must exist in the database.
     */
    public void removeAppointment(Appointment appointment) {
        appointments.remove(appointment);
    }

    /**
     * Returns true if a doctor with the same NRIC as {@code appointment} exists in the database.
     */
    public boolean hasDoctor(Doctor doctor) {
        requireNonNull(doctor);
        return doctors.contains(doctor);
    }

    /**
     * Returns true if a doctor with the given NRIC as {@code nric} exists in the database.
     */
    public boolean hasDoctorWithNric(Nric nric) {
        requireNonNull(nric);
        return doctors.contains(doctor -> doctor.getNric().equals(nric));
    }

    /**
     * Adds a doctor to the database.
     * The doctor must not already exist in the database.
     */
    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
    }

    /**
     * Replaces the given doctor {@code target} in the list with {@code editedDoctor}.
     * {@code target} must exist in the database.
     * The NRIC of {@code editedDoctor} must not be the same as another existing doctor in the database.
     */
    public void setDoctor(Doctor target, Doctor editedDoctor) {
        requireNonNull(editedDoctor);

        doctors.setItem(target, editedDoctor);
    }

    /**
     * Removes {@code doctor} from this {@code Database}.
     * {@code doctor} must exist in the database.
     */
    public void removeDoctor(Doctor doctor) {
        doctors.remove(doctor);
    }

    /**
     * Returns true if a patient with the same NRIC as {@code appointment} exists in the database.
     */
    public boolean hasPatient(Patient patient) {
        requireNonNull(patient);
        return patients.contains(patient);
    }

    /**
     * Returns true if a patient with the given NRIC {@code nric} exists in the database.
     */
    public boolean hasPatientWithNric(Nric nric) {
        requireNonNull(nric);
        return patients.contains(patient -> patient.getNric().equals(nric));
    }

    /**
     * Adds a patient to the database.
     * The patient must not already exist in the database.
     */
    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    /**
     * Replaces the given patient {@code target} in the list with {@code editedDoctor}.
     * {@code target} must exist in the database.
     * The NRIC of {@code editedDoctor} must not be the same as another existing patient in the database.
     */
    public void setPatient(Patient target, Patient editedPatient) {
        requireNonNull(editedPatient);

        patients.setItem(target, editedPatient);
    }

    /**
     * Removes {@code patient} from this {@code Database}.
     * {@code patient} must exist in the database.
     */
    public void removePatient(Patient patient) {
        patients.remove(patient);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("appointments", appointments)
                .add("doctors", doctors)
                .add("patients", patients)
                .toString();
    }

    @Override
    public ObservableList<Appointment> getAppointmentList() {
        return appointments.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Doctor> getDoctorList() {
        return doctors.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Patient> getPatientList() {
        return patients.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Database)) {
            return false;
        }

        Database otherDatabase = (Database) other;
        return appointments.equals(otherDatabase.appointments)
                && doctors.equals(otherDatabase.doctors)
                && patients.equals(otherDatabase.patients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(appointments, doctors, patients);
    }
}
