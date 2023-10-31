package seedu.address.model.appointment;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Data;
import seedu.address.model.Listable;
import seedu.address.model.person.Nric;
import seedu.address.model.remark.Remark;
import seedu.address.model.tag.Tag;

/**
 * Represents a Appointment in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Appointment extends Data {
    public static final String MESSAGE_INVALID_APPOINTMENT_TIME = "Appointment's end time cannot be before start time";
    public static final String MESSAGE_SAME_PIC_DIC = "Patient's NRIC cannot be the same as Doctor's NRIC";
    private final Nric doctorNric;
    private final Nric patientNric;
    private final AppointmentStartTime startTime;
    private final AppointmentEndTime endTime;

    /**
     * Constructor for Appointment.
     * Used for initial creation of an Appointment, as remarks and tags need not be initialized.
     * Every field must be present and not null.
     */

    public Appointment(Nric doctorNric, Nric patientNric, AppointmentStartTime startTime, AppointmentEndTime endTime)
            throws CommandException {
        super();
        requireAllNonNull(doctorNric, patientNric, startTime, endTime);
        validateFields(doctorNric, patientNric, startTime, endTime);
        this.doctorNric = doctorNric;
        this.patientNric = patientNric;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Constructor for Appointment.
     * Used when remark and tags need to be set.
     * Every field must be present and not null.
     */
    public Appointment(Nric doctorNric, Nric patientNric, AppointmentStartTime startTime, AppointmentEndTime endTime,
                       Remark remark, HashSet<Tag> tags) throws CommandException {
        super(remark, tags);
        requireAllNonNull(doctorNric, patientNric, startTime, endTime);
        validateFields(doctorNric, patientNric, startTime, endTime);
        this.doctorNric = doctorNric;
        this.patientNric = patientNric;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Checks if the endTime is after the startTime and if the patient and doctor nric are different
     */
    public static void validateFields(Nric doctorNric, Nric patientNric,
                                      AppointmentStartTime startTime, AppointmentEndTime endTime)
            throws CommandException {
        if (!endTime.getTime().isAfter(startTime.getTime())) {
            throw new CommandException(MESSAGE_INVALID_APPOINTMENT_TIME);
        }

        if (patientNric.equals(doctorNric)) {
            throw new CommandException(MESSAGE_SAME_PIC_DIC);
        }
    }
    public Nric getDoctorNric() {
        return doctorNric;
    }

    public Nric getPatientNric() {
        return patientNric;
    }

    public AppointmentStartTime getStartTime() {
        return startTime;
    }

    public AppointmentEndTime getEndTime() {
        return endTime;
    }
    @Override
    public boolean isSame(Listable otherListable) {
        if (!(otherListable instanceof Appointment)) {
            return false;
        }

        Appointment otherAppointment = (Appointment) otherListable;

        if (otherAppointment == this) {
            return true;
        }

        return otherAppointment.getDoctorNric().equals(getDoctorNric())
                && otherAppointment.getPatientNric().equals(getPatientNric())
                && otherAppointment.getStartTime().equals(getStartTime())
                && otherAppointment.getEndTime().equals(getEndTime());
    }

    /**
     * Returns true if both appointments have overlapping timings
     */
    public boolean overlaps(Appointment a) {
        if (this.startTime.equals(a.startTime) && this.endTime.equals(a.endTime)) {
            return true;
        }
        return this.withinInterval(a.startTime.getTime())
                || this.withinInterval(a.endTime.getTime())
                || a.withinInterval(this.startTime.getTime())
                || a.withinInterval(this.endTime.getTime());
    }

    /**
     * Returns true if given time is within the appointment timings
     */
    public boolean withinInterval(LocalDateTime time) {
        return time.isBefore(this.endTime.getTime()) && time.isAfter(this.startTime.getTime());
    }

    /**
     * Returns true if both appointments have the same identity fields.
     * This defines a stronger notion of equality between two appointments.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Appointment)) {
            return false;
        }

        Appointment otherAppointment = (Appointment) other;
        return doctorNric.equals(otherAppointment.doctorNric)
                && patientNric.equals(otherAppointment.patientNric)
                && startTime.equals(otherAppointment.startTime)
                && endTime.equals(otherAppointment.endTime);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(doctorNric, patientNric, startTime, endTime);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("doctorNric", doctorNric)
                .add("patientNric", patientNric)
                .add("startTime", startTime)
                .add("endTime", endTime)
                .add("remark", getRemark())
                .add("tags", getTags())
                .toString();
    }
}
