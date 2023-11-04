package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Database;
import seedu.address.model.ReadOnlyDatabase;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.doctor.Doctor;
import seedu.address.model.person.patient.Patient;

/**
 * An Immutable Database that is serializable to JSON format.
 */
@JsonRootName(value = "database")
class JsonSerializableDatabase {

    public static final String MESSAGE_DUPLICATE_PATIENT = "Patients list contains duplicate patients(s).";
    public static final String MESSAGE_DUPLICATE_DOCTOR = "Doctors list contains duplicate doctors(s).";
    public static final String MESSAGE_DUPLICATE_APPOINTMENT = "Appointments list contains duplicate appointments(s).";

    private final List<JsonAdaptedPatient> patients = new ArrayList<>();
    private final List<JsonAdaptedDoctor> doctors = new ArrayList<>();
    private final List<JsonAdaptedAppointment> appointments = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableDatabase} with the given persons.
     */
    @JsonCreator
    public JsonSerializableDatabase(@JsonProperty("patients") List<JsonAdaptedPatient> patients,
                                    @JsonProperty("doctors") List<JsonAdaptedDoctor> doctors,
                                    @JsonProperty("appointments") List<JsonAdaptedAppointment> appointments) {
        this.patients.addAll(patients);
        this.doctors.addAll(doctors);
        this.appointments.addAll(appointments);
    }

    /**
     * Converts a given {@code ReadOnlyDatabase} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableDatabase}.
     */
    public JsonSerializableDatabase(ReadOnlyDatabase source) {
        patients.addAll(source.getPatientList().stream().map(JsonAdaptedPatient::new).collect(Collectors.toList()));
        doctors.addAll(source.getDoctorList().stream().map(JsonAdaptedDoctor::new).collect(Collectors.toList()));
        appointments.addAll(source.getAppointmentList().stream().map(JsonAdaptedAppointment::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this database into the model's {@code Database} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Database toModelType() throws IllegalValueException, CommandException {
        Database database = new Database();
        for (JsonAdaptedPatient jsonAdaptedPatient : patients) {
            Patient patient = jsonAdaptedPatient.toModelType();
            if (database.hasPatient(patient)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PATIENT);
            }
            database.addPatient(patient);
        }
        for (JsonAdaptedDoctor jsonAdaptedDoctor : doctors) {
            Doctor doctor = jsonAdaptedDoctor.toModelType();
            if (database.hasDoctor(doctor)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_DOCTOR);
            }
            database.addDoctor(doctor);
        }
        for (JsonAdaptedAppointment jsonAdaptedAppointment : appointments) {
            Appointment appointment = jsonAdaptedAppointment.toModelType();
            if (database.hasAppointment(appointment)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_APPOINTMENT);
            }
            database.addAppointment(appointment);
        }
        return database;
    }

}
