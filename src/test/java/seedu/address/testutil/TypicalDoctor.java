package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_BOB;
import static seedu.address.testutil.TypicalPatient.ALICE_NAME;
import static seedu.address.testutil.TypicalPatient.ALICE_NRIC;
import static seedu.address.testutil.TypicalPatient.BENSON_NAME;
import static seedu.address.testutil.TypicalPatient.BENSON_NRIC;
import static seedu.address.testutil.TypicalPatient.CARL_NAME;
import static seedu.address.testutil.TypicalPatient.CARL_NRIC;
import static seedu.address.testutil.TypicalPatient.DANIEL_NAME;
import static seedu.address.testutil.TypicalPatient.DANIEL_NRIC;
import static seedu.address.testutil.TypicalPatient.ELLE_NAME;
import static seedu.address.testutil.TypicalPatient.ELLE_NRIC;
import static seedu.address.testutil.TypicalPatient.FIONA_NAME;
import static seedu.address.testutil.TypicalPatient.FIONA_NRIC;
import static seedu.address.testutil.TypicalPatient.GEORGE_NAME;
import static seedu.address.testutil.TypicalPatient.GEORGE_NRIC;
import static seedu.address.testutil.TypicalPatient.HOON_NAME;
import static seedu.address.testutil.TypicalPatient.HOON_NRIC;
import static seedu.address.testutil.TypicalPatient.IDA_NAME;
import static seedu.address.testutil.TypicalPatient.IDA_NRIC;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.person.doctor.Doctor;

/**
 * A utility class containing a list of {@code Doctor} objects to be used in tests.
 */
public class TypicalDoctor {
    public static final Doctor ALICE = new DoctorBuilder().withName(ALICE_NAME)
            .withNric(ALICE_NRIC).build();
    public static final Doctor BENSON = new DoctorBuilder().withName(BENSON_NAME)
            .withNric(BENSON_NRIC).build();
    public static final Doctor CARL = new DoctorBuilder().withName(CARL_NAME)
            .withNric(CARL_NRIC).build();
    public static final Doctor DANIEL = new DoctorBuilder().withName(DANIEL_NAME)
            .withNric(DANIEL_NRIC).build();
    public static final Doctor ELLE = new DoctorBuilder().withName(ELLE_NAME).withNric(ELLE_NRIC).build();
    public static final Doctor FIONA = new DoctorBuilder().withName(FIONA_NAME).withNric(FIONA_NRIC).build();
    public static final Doctor GEORGE = new DoctorBuilder().withName(GEORGE_NAME).withNric(GEORGE_NRIC).build();

    // Manually added
    public static final Doctor HOON = new DoctorBuilder().withName(HOON_NAME)
            .withNric(HOON_NRIC).build();
    public static final Doctor IDA = new DoctorBuilder().withName(IDA_NAME)
            .withNric(IDA_NRIC).build();

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
