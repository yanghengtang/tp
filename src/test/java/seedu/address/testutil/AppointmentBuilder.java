package seedu.address.testutil;

import static seedu.address.model.DataTest.*;
import static seedu.address.testutil.PersonUtil.ALICE_NRIC;
import static seedu.address.testutil.PersonUtil.GEORGE_NRIC;

import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentEndTime;
import seedu.address.model.appointment.AppointmentStartTime;
import seedu.address.model.person.Nric;
import seedu.address.model.remark.Remark;
import seedu.address.model.tag.Tag;

import java.util.Arrays;
import java.util.HashSet;

/**
 * A utility class to help with building Appointment objects.
 */
public class AppointmentBuilder {
    public static final String DEFAULT_DOCTOR_NRIC = ALICE_NRIC;
    public static final String DEFAULT_PATIENT_NRIC = GEORGE_NRIC;
    public static final String DEFAULT_START_TIME = "2023-09-11 07:30";
    public static final String DEFAULT_END_TIME = "2023-09-11 08:00";
    private Nric doctorNric;
    private Nric patientNric;
    private AppointmentStartTime startTime;
    private AppointmentEndTime endTime;
    private Remark remark;
    private HashSet<Tag> tags;

    /**
     * Creates a {@code AppointmentBuilder} with the default details.
     */
    public AppointmentBuilder() {
        doctorNric = new Nric(DEFAULT_DOCTOR_NRIC);
        patientNric = new Nric(DEFAULT_PATIENT_NRIC);
        startTime = new AppointmentStartTime(DEFAULT_START_TIME);
        endTime = new AppointmentEndTime(DEFAULT_END_TIME);
    }

    /**
     * Initializes the AppointmentBuilder with the data of {@code appointmentToCopy}.
     */
    public AppointmentBuilder(Appointment appointmentToCopy) {
        doctorNric = appointmentToCopy.getDoctorNric();
        patientNric = appointmentToCopy.getPatientNric();
        startTime = appointmentToCopy.getStartTime();
        endTime = appointmentToCopy.getEndTime();
        remark = appointmentToCopy.getRemark();
        tags = appointmentToCopy.getTags();
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
     * Sets the {@code startTime} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withStartTime(String startTime) {
        this.startTime = new AppointmentStartTime(startTime);
        return this;
    }

    /**
     * Sets the {@code endTime} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withEndTime(String endTime) {
        this.endTime = new AppointmentEndTime(endTime);
        return this;
    }

    /**
     * Sets the {@code remark} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withRemark(String remark) {
        this.remark = new Remark(remark);
        return this;
    }

    /**
     * Sets the {@code tags} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withTags(Tag... tags) {
        HashSet<Tag> tagSet = new HashSet<>(Arrays.asList(tags));
        this.tags = tagSet;
        return this;
    }

    public Appointment build() {
        if (this.remark == null || this.tags == null) {
            return new Appointment(doctorNric, patientNric, startTime, endTime);
        }
        return new Appointment(doctorNric, patientNric, startTime, endTime, remark, tags);
    }
}
