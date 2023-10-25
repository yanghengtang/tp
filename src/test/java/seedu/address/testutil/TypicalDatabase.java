package seedu.address.testutil;

import static seedu.address.testutil.TypicalAppointment.getTypicalAppointment;
import static seedu.address.testutil.TypicalDoctor.getTypicalDoctor;
import static seedu.address.testutil.TypicalPatient.getTypicalPatient;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Database;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.doctor.Doctor;
import seedu.address.model.person.patient.Patient;

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
            try {
                db.addAppointment(appointment);
            } catch (CommandException e) {
                throw new AssertionError("Invalid appointment in typical database");
            }
        }
        return db;
    }
}
