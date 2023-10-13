package seedu.address.testutil;

import seedu.address.model.AddressBook;
import seedu.address.model.Database;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Person;
import seedu.address.model.person.doctor.Doctor;
import seedu.address.model.person.patient.Patient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static seedu.address.testutil.TypicalAppointment.getTypicalAppointment;
import static seedu.address.testutil.TypicalDoctor.getTypicalDoctor;
import static seedu.address.testutil.TypicalPatient.getTypicalPatient;

/**
 * A utility class for testing Database.
 */
public class TypicalDatabase {
    /**
     * Returns an {@code Database} with all the typical patients, doctors and appointments.
     */
    public static Database getTypicalDatabase() {
        Database db = new Database();
        for (Patient patient : getTypicalPatient()) {
            db.addPatient(patient);
        }
        for (Doctor doctor : getTypicalDoctor()) {
            db.addDoctor(doctor);
        }
        for (Appointment appointment : getTypicalAppointment()) {
            db.addAppointment(appointment);
        }
        return db;
    }
}
