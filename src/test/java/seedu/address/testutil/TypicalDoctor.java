package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_BOB;
import static seedu.address.model.DataTest.DERMATOLOGY_TAG;
import static seedu.address.model.DataTest.VALID_REMARK_STRING;
import static seedu.address.testutil.PersonUtil.ALICE_NAME;
import static seedu.address.testutil.PersonUtil.ALICE_NRIC;
import static seedu.address.testutil.PersonUtil.BENSON_NAME;
import static seedu.address.testutil.PersonUtil.BENSON_NRIC;
import static seedu.address.testutil.PersonUtil.CARL_NAME;
import static seedu.address.testutil.PersonUtil.CARL_NRIC;
import static seedu.address.testutil.PersonUtil.DANIEL_NAME;
import static seedu.address.testutil.PersonUtil.DANIEL_NRIC;
import static seedu.address.testutil.PersonUtil.ELLE_NAME;
import static seedu.address.testutil.PersonUtil.ELLE_NRIC;
import static seedu.address.testutil.PersonUtil.FIONA_NAME;
import static seedu.address.testutil.PersonUtil.FIONA_NRIC;
import static seedu.address.testutil.PersonUtil.GEORGE_NAME;
import static seedu.address.testutil.PersonUtil.GEORGE_NRIC;
import static seedu.address.testutil.PersonUtil.HOON_NAME;
import static seedu.address.testutil.PersonUtil.HOON_NRIC;
import static seedu.address.testutil.PersonUtil.IDA_NAME;
import static seedu.address.testutil.PersonUtil.IDA_NRIC;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.Database;
import seedu.address.model.person.doctor.Doctor;

/**
 * A utility class containing a list of {@code Doctor} objects to be used in tests.
 */
public class TypicalDoctor {
    public static final Doctor ALICE = new DoctorBuilder().withName(ALICE_NAME)
            .withNric(ALICE_NRIC).build();

    public static final Doctor ALICE_WITH_REMARKS = new DoctorBuilder().withName(ALICE_NAME)
            .withNric(ALICE_NRIC).withRemark(VALID_REMARK_STRING).withTags(DERMATOLOGY_TAG).build();
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
        return new ArrayList<>(Arrays.asList(ALICE_WITH_REMARKS, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }

    public static Database getTypicalDatabase() {
        Database ab = new Database();
        for (Doctor doctor : getTypicalDoctor()) {
            ab.addDoctor(doctor);
        }
        return ab;
    }

}
