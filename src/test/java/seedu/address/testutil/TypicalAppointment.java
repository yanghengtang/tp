package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.appointment.Appointment;

/**
 * A utility class containing a list of {@code Appointment} objects to be used in tests.
 */
public class TypicalAppointment {
    public static final Appointment APPOINTMENT_1 = new AppointmentBuilder().withDoctor(TypicalDoctor.ALICE)
            .withPatient(TypicalPatient.BENSON).withDateTime("2023-12-01T07:30").build();

    public static final Appointment APPOINTMENT_2 = new AppointmentBuilder().withDoctor(TypicalDoctor.BENSON)
            .withPatient(TypicalPatient.ALICE).withDateTime("2023-12-01T07:45").build();

    public static final Appointment APPOINTMENT_3 = new AppointmentBuilder().withDoctor(TypicalDoctor.CARL)
            .withPatient(TypicalPatient.DANIEL).withDateTime("2023-12-01T08:00").build();

    public static final Appointment APPOINTMENT_4 = new AppointmentBuilder().withDoctor(TypicalDoctor.DANIEL)
            .withPatient(TypicalPatient.CARL).withDateTime("2023-12-01T08:15").build();

    public static final Appointment APPOINTMENT_5 = new AppointmentBuilder().withDoctor(TypicalDoctor.ELLE)
            .withPatient(TypicalPatient.FIONA).withDateTime("2023-12-01T08:30").build();

    public static final Appointment APPOINTMENT_6 = new AppointmentBuilder().withDoctor(TypicalDoctor.FIONA)
            .withPatient(TypicalPatient.ELLE).withDateTime("2023-12-01T08:45").build();

    public TypicalAppointment() {} // prevents instantiation

    public static List<Appointment> getTypicalAppointment() {
        return new ArrayList<>(Arrays.asList(APPOINTMENT_1, APPOINTMENT_2, APPOINTMENT_3, APPOINTMENT_4,
                APPOINTMENT_5, APPOINTMENT_6));
    }
}
