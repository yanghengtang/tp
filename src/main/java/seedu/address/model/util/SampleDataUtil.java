package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.Database;
import seedu.address.model.ReadOnlyDatabase;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentEndTime;
import seedu.address.model.appointment.AppointmentStartTime;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Phone;
import seedu.address.model.person.doctor.Doctor;
import seedu.address.model.person.patient.Patient;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {

    public static Patient[] getSamplePatients() {
        return new Patient[] {
            new Patient(new Name("Kim Shi Tong"), new Phone("87438807"), new Nric("G0234723F")),
            new Patient(new Name("Mah Chee Teng"), new Phone("99272758"), new Nric("T0384394D")),
            new Patient(new Name("Mounil Sankar"), new Phone("93210283"), new Nric("T0123483C")),
            new Patient(new Name("Derek Tan JX"), new Phone("91031282"), new Nric("T0151708E")),
            new Patient(new Name("Mohd Yang Heng"), new Phone("92492021"), new Nric("T0157283A"))
        };
    }

    public static Doctor[] getSampleDoctors() {
        return new Doctor[] {
            new Doctor(new Name("Kim Shi Tong"), new Nric("G0234723F")),
            new Doctor(new Name("Mah Chee Teng"), new Nric("T0384394D")),
            new Doctor(new Name("Mounil Sankar"), new Nric("T0123483C")),
            new Doctor(new Name("Derek Tan JX"), new Nric("T0151708E")),
            new Doctor(new Name("Mohd Yang Heng"), new Nric("T0157283A"))
        };
    }

    public static Appointment[] getSampleAppointments() {
        return new Appointment[] {
            new Appointment(new Nric("T0157283A"), new Nric("G0234723F"),
                    new AppointmentStartTime("2023-09-11 07:30"), new AppointmentEndTime("2023-09-11 08:30")),
            new Appointment(new Nric("T0151708E"), new Nric("T0384394D"),
                    new AppointmentStartTime("2023-09-11 08:30"), new AppointmentEndTime("2023-09-11 09:30")),
            new Appointment(new Nric("T0123483C"), new Nric("T0123483C"),
                    new AppointmentStartTime("2023-09-11 09:30"), new AppointmentEndTime("2023-09-11 10:30")),
            new Appointment(new Nric("T0384394D"), new Nric("T0151708E"),
                    new AppointmentStartTime("2023-09-11 10:30"), new AppointmentEndTime("2023-09-11 11:30")),
            new Appointment(new Nric("T0384394D"), new Nric("T0157283A"),
                    new AppointmentStartTime("2023-09-11 11:30"), new AppointmentEndTime("2023-09-11 12:30"))
        };
    }

    public static ReadOnlyDatabase getSampleDatabase() {
        Database sampleDb = new Database();
        for (Patient samplePatient : getSamplePatients()) {
            sampleDb.addPatient(samplePatient);
        }
        for (Doctor sampleDoctor : getSampleDoctors()) {
            sampleDb.addDoctor(sampleDoctor);
        }
        for (Appointment sampleAppointment : getSampleAppointments()) {
            sampleDb.addAppointment(sampleAppointment);
        }
        return sampleDb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
