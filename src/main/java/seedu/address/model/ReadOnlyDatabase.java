package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.doctor.Doctor;
import seedu.address.model.person.patient.Patient;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyDatabase {

    /**
     * Returns an unmodifiable view of the Appointment list.
     * This list will not contain any duplicate Appointments.
     */
    ObservableList<Appointment> getAppointmentList();

    /**
     * Returns an unmodifiable view of the Doctor list.
     * This list will not contain any duplicate Doctors.
     */
    ObservableList<Doctor> getDoctorList();

    /**
     * Returns an unmodifiable view of the Patient list.
     * This list will not contain any duplicate Patients.
     */
    ObservableList<Patient> getPatientList();
}
