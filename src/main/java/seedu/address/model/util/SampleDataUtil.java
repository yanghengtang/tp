package seedu.address.model.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.logic.commands.exceptions.CommandException;
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
import seedu.address.model.remark.Remark;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code Database} with sample data.
 */
public class SampleDataUtil {

    public static Patient[] getSamplePatients() {
        return new Patient[] {
            new Patient(new Name("Mike Ng"), new Phone("87438807"), new Nric("S6912483J"),
                    new Remark(""),
                    new HashSet<>(getTagSet("ChronicMigraine"))),
            new Patient(new Name("Michelle Lim"), new Phone("99272758"), new Nric("S2349123A"),
                    new Remark(""),
                    new HashSet<>(getTagSet("Depression"))),
            new Patient(new Name("Joe Sin"), new Phone("93210283"), new Nric("S1890251D"),
                    new Remark("Patient is to fast 12 hours before next appointment"),
                    new HashSet<>(getTagSet("Diabetes"))),
            new Patient(new Name("Jonathan Reese"), new Phone("91031282"), new Nric("S7142983F"),
                    new Remark(""),
                    new HashSet<>(getTagSet("Gastric"))),
            new Patient(new Name("Damien Ng"), new Phone("92492021"), new Nric("S1348067K"),
                    new Remark(""),
                    new HashSet<>(getTagSet("HeartDisease")))
        };
    }

    public static Doctor[] getSampleDoctors() {
        return new Doctor[] {
            new Doctor(new Name("Kim Shi Tong"), new Nric("G0234723F"),
                    new Remark("Not available: 13 Nov 2023 - 15 Nov 2023"),
                    new HashSet<>(getTagSet("Psychiatry"))),
            new Doctor(new Name("Mah Chee Teng"), new Nric("T0384394D"),
                    new Remark("Overseas: 5 Dec 2023 - 16 Dec 2023"),
                    new HashSet<>(getTagSet("Pediatric"))),
            new Doctor(new Name("Mounil Sankar"), new Nric("T0123483C"),
                    new Remark("On Course: 1 Dec 2023"),
                    new HashSet<>(getTagSet("Cardiology"))),
            new Doctor(new Name("Derek Tan"), new Nric("T0151708E"),
                    new Remark("National Service ICT: 11 Jan 2024 - 22 Jan 2024"),
                    new HashSet<>(getTagSet("Neurology"))),
            new Doctor(new Name("Tang Yang Heng"), new Nric("T0157283A"),
                    new Remark("On Course: 15 Jan 2024"),
                    new HashSet<>(getTagSet("GP", "Trauma")))
        };
    }

    public static Appointment[] getSampleAppointments() throws CommandException {
        return new Appointment[] {
            new Appointment(new Nric("S6912483J"), new Nric("T0157283A"),
                    new AppointmentStartTime("2023-09-09 10:30"), new AppointmentEndTime("2023-09-09 10:45"),
                    new Remark("Patient came with flu with cough, but Covid ART -ve"),
                    new HashSet<>(getTagSet("Panadol", "CoughSyrup"))),
            new Appointment(new Nric("S2349123A"), new Nric("G0234723F"),
                    new AppointmentStartTime("2023-09-09 14:30"), new AppointmentEndTime("2023-09-09 15:30"),
                    new Remark("Patient exhibits worsening symptoms"),
                    new HashSet<>(getTagSet("Antidepressants"))),
            new Appointment(new Nric("S1890251D"), new Nric("T0157283A"),
                    new AppointmentStartTime("2023-09-10 10:00"), new AppointmentEndTime("2023-09-10 10:30"),
                    new Remark("Patient to return in November for blood sugar testing"),
                    new HashSet<>(getTagSet("Chlorpropamide"))),
            new Appointment(new Nric("S7142983F"), new Nric("T0151708E"),
                    new AppointmentStartTime("2023-09-11 10:30"), new AppointmentEndTime("2023-09-11 11:30"),
                    new Remark("No follow up appointment required"), new HashSet<>()),
            new Appointment(new Nric("S1348067K"), new Nric("T0123483C"),
                    new AppointmentStartTime("2023-09-11 11:30"), new AppointmentEndTime("2023-09-11 12:30"),
                    new Remark("Patient's cholesterol level has steadily decreased"),
                    new HashSet<>(getTagSet("Atorvastatin", "Lovastatin"))),
            new Appointment(new Nric("S2349123A"), new Nric("G0234723F"),
                    new AppointmentStartTime("2023-11-04 14:30"), new AppointmentEndTime("2023-11-04 15:30")),
            new Appointment(new Nric("S1890251D"), new Nric("T0157283A"),
                    new AppointmentStartTime("2023-11-06 10:00"), new AppointmentEndTime("2023-11-06 10:30")),
            new Appointment(new Nric("S1348067K"), new Nric("T0123483C"),
                    new AppointmentStartTime("2023-11-11 10:30"), new AppointmentEndTime("2023-11-11 11:30"))
        };
    }

    public static ReadOnlyDatabase getSampleDatabase() throws CommandException {
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
