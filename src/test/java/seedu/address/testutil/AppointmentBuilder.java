package seedu.address.testutil;

import java.time.LocalDateTime;

import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Nric;

/**
 * A utility class to help with building Appointment objects.
 */
public class AppointmentBuilder {
    public static final String DEFAULT_DOCTOR_NRIC = "T0123456A";
    public static final String DEFAULT_PATIENT_NRIC = "S9987654B";
    public static final String DEFAULT_START_TIME = "2023-09-11T07:30";
    public static final String DEFAULT_END_TIME = "2023-09-11T08:00";
    private Nric doctorNric;
    private Nric patientNric;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    /**
     * Creates a {@code AppointmentBuilder} with the default details.
     */
    public AppointmentBuilder() {
        doctorNric = new Nric(DEFAULT_DOCTOR_NRIC);
        patientNric = new Nric(DEFAULT_PATIENT_NRIC);
        startTime = LocalDateTime.parse(DEFAULT_START_TIME);
        endTime = LocalDateTime.parse(DEFAULT_END_TIME);
    }

    /**
     * Initializes the AppointmentBuilder with the data of {@code appointmentToCopy}.
     */
    public AppointmentBuilder(Appointment appointmentToCopy) {
        doctorNric = appointmentToCopy.getDoctorNric();
        patientNric = appointmentToCopy.getPatientNric();
        startTime = appointmentToCopy.getStartTime();
        endTime = appointmentToCopy.getEndTime();
    }

    /**
     * Sets the {@code DoctorNRIC} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withDoctorNric(String doctorNric) {
        this.doctorNric = new Nric(doctorNric);
        return this;
    }

    /**
     * Sets the {@code PatientNRIC} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withPatientNric(String patientNric) {
        this.patientNric = new Nric(patientNric);
        return this;
    }

    /**
     * Sets the {@code LocalDateTime} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withStartTime(String startTime) {
        this.startTime = LocalDateTime.parse(startTime);
        return this;
    }

    public AppointmentBuilder withEndTime(String endTime) {
        this.endTime = LocalDateTime.parse(endTime);
        return this;
    }

    public Appointment build() {
        return new Appointment(doctorNric, patientNric, startTime, endTime);
    }
}
