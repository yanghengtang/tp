package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.appointment.Appointment;

/**
 * A utility class containing a list of {@code Appointment} objects to be used in tests.
 */
public class TypicalAppointment {
    public static final Appointment APPOINTMENT_1 = new AppointmentBuilder().withDoctorNric("T0123456J")
            .withPatientNric("S9348573F").withDateTime("2023-12-01T07:30").build();

    public static final Appointment APPOINTMENT_2 = new AppointmentBuilder().withDoctorNric("S2936283D")
            .withPatientNric("T0248362R").withDateTime("2023-12-01T07:45").build();

    public static final Appointment APPOINTMENT_3 = new AppointmentBuilder().withDoctorNric("T3856391A")
            .withPatientNric("T0359320R").withDateTime("2023-12-01T08:00").build();

    public static final Appointment APPOINTMENT_4 = new AppointmentBuilder().withDoctorNric("S9754123F")
            .withPatientNric("G6739542H").withDateTime("2023-12-01T08:15").build();

    public static final Appointment APPOINTMENT_5 = new AppointmentBuilder().withDoctorNric("G4123573C")
            .withPatientNric("S9754123F").withDateTime("2023-12-01T08:30").build();

    public static final Appointment APPOINTMENT_6 = new AppointmentBuilder().withDoctorNric("G6739542H")
            .withPatientNric("G4123573C").withDateTime("2023-12-01T08:45").build();

    public TypicalAppointment() {} // prevents instantiation

    public static List<Appointment> getTypicalAppointment() {
        return new ArrayList<>(Arrays.asList(APPOINTMENT_1, APPOINTMENT_2, APPOINTMENT_3, APPOINTMENT_4,
                APPOINTMENT_5, APPOINTMENT_6));
    }
}
