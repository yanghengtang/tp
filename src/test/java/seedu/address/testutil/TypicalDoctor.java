package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.person.doctor.Doctor;

/**
 * A utility class containing a list of {@code Doctor} objects to be used in tests.
 */
public class TypicalDoctor {
    public static final Doctor ALICE = new DoctorBuilder().withName("Alice Pauline")
            .withNric("T0123456J").build();
    public static final Doctor BENSON = new DoctorBuilder().withName("Benson Meier")
            .withNric("S2936283D").build();
    public static final Doctor CARL = new DoctorBuilder().withName("Carl Kurz")
            .withNric("T3856391A").build();
    public static final Doctor DANIEL = new DoctorBuilder().withName("Daniel Meier")
            .withNric("S9754123F").build();
    public static final Doctor ELLE = new DoctorBuilder().withName("Elle Meyer").withNric("G4123573C").build();
    public static final Doctor FIONA = new DoctorBuilder().withName("Fiona Kunz").withNric("G6739542H").build();
    public static final Doctor GEORGE = new DoctorBuilder().withName("George Best").withNric("T0359320R").build();

    // Manually added
    public static final Doctor HOON = new DoctorBuilder().withName("Hoon Meier")
            .withNric("T0248362R").build();
    public static final Doctor IDA = new DoctorBuilder().withName("Ida Mueller")
            .withNric("S9348573F").build();

    // Manually added - Doctor's details found in {@code CommandTestUtil}
    public static final Doctor AMY = new DoctorBuilder().withName(VALID_NAME_AMY)
            .withNric(VALID_NRIC_AMY).build();
    public static final Doctor BOB = new DoctorBuilder().withName(VALID_NAME_BOB)
            .withNric(VALID_NRIC_BOB).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalDoctor() {} // prevents instantiation

    public static List<Doctor> getTypicalDoctor() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }

}
