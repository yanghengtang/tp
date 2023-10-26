package seedu.address.model;

import java.util.Comparator;

import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.doctor.Doctor;
import seedu.address.model.person.patient.Patient;

/**
 * A Class to hold all the Comparators required for sorting.
 */
public class Sorters {
    // appointment Comparators
    public static final Comparator<Appointment> APPOINTMENT_START_TIME_ASC_COMPARATOR = (a1, a2)
            -> a1.getStartTime().compareTo(a2.getStartTime());
    public static final Comparator<Appointment> APPOINTMENT_START_TIME_DESC_COMPARATOR = (a1, a2)
            -> -(a1.getStartTime().compareTo(a2.getStartTime()));
    public static final Comparator<Appointment> APPOINTMENT_END_TIME_ASC_COMPARATOR = (a1, a2)
            -> a1.getEndTime().compareTo(a2.getEndTime());
    public static final Comparator<Appointment> APPOINTMENT_END_TIME_DESC_COMPARATOR = (a1, a2)
            -> -(a1.getEndTime().compareTo(a2.getEndTime()));
    public static final Comparator<Appointment> APPOINTMENT_PATIENT_NRIC_ASC_COMPARATOR = (a1, a2)
            -> a1.getPatientNric().compareTo(a2.getPatientNric());
    public static final Comparator<Appointment> APPOINTMENT_PATIENT_NRIC_DESC_COMPARATOR = (a1, a2)
            -> -(a1.getPatientNric().compareTo(a2.getPatientNric()));
    public static final Comparator<Appointment> APPOINTMENT_DOCTOR_NRIC_ASC_COMPARATOR = (a1, a2)
            -> a1.getDoctorNric().compareTo(a2.getDoctorNric());
    public static final Comparator<Appointment> APPOINTMENT_DOCTOR_NRIC_DESC_COMPARATOR = (a1, a2)
            -> -(a1.getDoctorNric().compareTo(a2.getDoctorNric()));

    // doctor comparators
    public static final Comparator<Doctor> DOCTOR_NRIC_ASC_COMPARATOR = (d1, d2)
            -> d1.getNric().compareTo(d2.getNric());
    public static final Comparator<Doctor> DOCTOR_NRIC_DESC_COMPARATOR = (d1, d2)
            -> -(d1.getNric().compareTo(d2.getNric()));
    public static final Comparator<Doctor> DOCTOR_NAME_ASC_COMPARATOR = (d1, d2)
            -> d1.getName().compareTo(d2.getName());
    public static final Comparator<Doctor> DOCTOR_NAME_DESC_COMPARATOR = (d1, d2)
            -> -(d1.getName().compareTo(d2.getName()));

    // patient comparators
    public static final Comparator<Patient> PATIENT_NRIC_ASC_COMPARATOR = (p1, p2)
            -> p1.getNric().compareTo(p2.getNric());
    public static final Comparator<Patient> PATIENT_NRIC_DESC_COMPARATOR = (p1, p2)
            -> -(p1.getNric().compareTo(p2.getNric()));
    public static final Comparator<Patient> PATIENT_NAME_ASC_COMPARATOR = (p1, p2)
            -> p1.getName().compareTo(p2.getName());
    public static final Comparator<Patient> PATIENT_NAME_DESC_COMPARATOR = (p1, p2)
            -> -(p1.getName().compareTo(p2.getName()));
    public static final Comparator<Patient> PATIENT_PHONE_ASC_COMPARATOR = (p1, p2)
            -> p1.getPhone().compareTo(p2.getPhone());
    public static final Comparator<Patient> PATIENT_PHONE_DESC_COMPARATOR = (p1, p2)
            -> -(p1.getPhone().compareTo(p2.getPhone()));
}
