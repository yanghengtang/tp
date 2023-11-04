package seedu.address.storage;

import java.util.HashSet;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentEndTime;
import seedu.address.model.appointment.AppointmentStartTime;
import seedu.address.model.person.Nric;
import seedu.address.model.remark.Remark;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Appointment}.
 */
class JsonAdaptedAppointment extends JsonAdaptedData {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Appointment's %s field is missing!";

    private final String doctorNric;
    private final String patientNric;
    private final String startTime;
    private final String endTime;

    /**
     * Constructs a {@code JsonAdaptedAppointment} with the given appointment details.
     */
    @JsonCreator
    public JsonAdaptedAppointment(@JsonProperty("doctorNric") String doctorNric,
                                  @JsonProperty("patientNric") String patientNric,
                                  @JsonProperty("startTime") String startTime,
                                  @JsonProperty("endTime") String endTime,
                                  @JsonProperty("remark") String remark,
                                  @JsonProperty("tags") List<JsonAdaptedTag> tags) {
        super(remark, tags);
        this.doctorNric = doctorNric;
        this.patientNric = patientNric;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Converts a given {@code Patient} into this class for Jackson use.
     */
    public JsonAdaptedAppointment(Appointment source) {
        super(source);
        doctorNric = source.getDoctorNric().nric;
        patientNric = source.getPatientNric().nric;
        startTime = source.getStartTime().toString();
        endTime = source.getEndTime().toString();
    }

    /**
     * Converts this Jackson-friendly adapted patient object into the model's {@code Patient} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted patient.
     */
    public Appointment toModelType() throws IllegalValueException, CommandException {
        if (doctorNric == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Nric.class.getSimpleName()));
        }
        if (!Nric.isValidNric(doctorNric)) {
            throw new IllegalValueException(Nric.MESSAGE_CONSTRAINTS);
        }
        final Nric modelDoctorNric = new Nric(doctorNric);

        if (patientNric == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Nric.class.getSimpleName()));
        }
        if (!Nric.isValidNric(patientNric)) {
            throw new IllegalValueException(Nric.MESSAGE_CONSTRAINTS);
        }
        final Nric modelPatientNric = new Nric(patientNric);

        if (startTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    AppointmentStartTime.class.getSimpleName()));
        }
        if (!AppointmentStartTime.isValidAppointmentTime(startTime)) {
            throw new IllegalValueException(AppointmentStartTime.MESSAGE_CONSTRAINTS);
        }
        final AppointmentStartTime modelAppointmentStartTime = new AppointmentStartTime(startTime);

        if (endTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    AppointmentEndTime.class.getSimpleName()));
        }
        if (!AppointmentEndTime.isValidAppointmentTime(endTime)) {
            throw new IllegalValueException(AppointmentEndTime.MESSAGE_CONSTRAINTS);
        }
        final AppointmentEndTime modelAppointmentEndTime = new AppointmentEndTime(endTime);

        final Remark modelRemark = getModelRemark();
        final HashSet<Tag> modelTags = getModelTags();

        return new Appointment(modelDoctorNric, modelPatientNric, modelAppointmentStartTime, modelAppointmentEndTime,
                modelRemark, modelTags);
    }
}
