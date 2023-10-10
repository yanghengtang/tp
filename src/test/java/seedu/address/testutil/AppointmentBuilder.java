package seedu.address.testutil;

import java.time.LocalDateTime;

import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.doctor.Doctor;
import seedu.address.model.person.patient.Patient;

/**
 * A utility class to help with building Appointment objects.
 */
public class AppointmentBuilder {
    public static final Doctor DEFAULT_DOCTOR = TypicalDoctor.ALICE;
    public static final Patient DEFAULT_PATIENT = TypicalPatient.BOB;
    public static final String DEFAULT_DATE_TIME = "2023-09-11T07:30";
    private Doctor doctor;
    private Patient patient;
    private LocalDateTime dateTime;

    /**
     * Creates a {@code AppointmentBuilder} with the default details.
     */
    public AppointmentBuilder() {
        doctor = DEFAULT_DOCTOR;
        patient = DEFAULT_PATIENT;
        dateTime = LocalDateTime.parse(DEFAULT_DATE_TIME);
    }

    /**
     * Initializes the AppointmentBuilder with the data of {@code doctorToCopy}.
     */
    public AppointmentBuilder(Appointment appointmentToCopy) {
        doctor = appointmentToCopy.getDoctor();
        patient = appointmentToCopy.getPatient();
        dateTime = appointmentToCopy.getDateTime();
    }

    /**
     * Sets the {@code Doctor} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withDoctor(Doctor doctor) {
        this.doctor = doctor;
        return this;
    }

    /**
     * Sets the {@code Patient} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withPatient(Patient patient) {
        this.patient = patient;
        return this;
    }

    /**
     * Sets the {@code LocalDateTime} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withDateTime(String dateTime) {
        this.dateTime = LocalDateTime.parse(dateTime);
        return this;
    }

    public Appointment build() {
        return new Appointment(doctor, patient, dateTime);
    }
}
