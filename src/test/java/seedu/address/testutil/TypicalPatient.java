package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.model.DataTest.DEPRESSION_TAG;
import static seedu.address.model.DataTest.VALID_REMARK_STRING;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.person.patient.Patient;

/**
 * A utility class containing a list of {@code Patient} objects to be used in tests.
 */
public class TypicalPatient {
    public static final String ALICE_PHONE = "94351253";
    public static final Patient ALICE = new PatientBuilder().withName(PersonUtil.ALICE_NAME).withPhone(ALICE_PHONE)
            .withNric(PersonUtil.ALICE_NRIC).build();

    public static final Patient ALICE_WITH_REMARKS = new PatientBuilder().withName(PersonUtil.ALICE_NAME)
            .withPhone(ALICE_PHONE)
            .withNric(PersonUtil.ALICE_NRIC)
            .withRemark(VALID_REMARK_STRING)
            .withTags(DEPRESSION_TAG)
            .build();

    public static final String BENSON_PHONE = "98765432";
    public static final Patient BENSON = new PatientBuilder().withName(PersonUtil.BENSON_NAME).withPhone(BENSON_PHONE)
            .withNric(PersonUtil.BENSON_NRIC).build();

    public static final String CARL_PHONE = "95352563";
    public static final Patient CARL = new PatientBuilder().withName(PersonUtil.CARL_NAME).withPhone(CARL_PHONE)
            .withNric(PersonUtil.CARL_NRIC).build();

    public static final String DANIEL_PHONE = "87652533";
    public static final Patient DANIEL = new PatientBuilder().withName(PersonUtil.DANIEL_NAME).withPhone(DANIEL_PHONE)
            .withNric(PersonUtil.DANIEL_NRIC).build();

    public static final String ELLE_PHONE = "9482224";
    public static final Patient ELLE = new PatientBuilder().withName(PersonUtil.ELLE_NAME).withPhone(ELLE_PHONE)
            .withNric(PersonUtil.ELLE_NRIC).build();

    public static final String FIONA_PHONE = "9482427";
    public static final Patient FIONA = new PatientBuilder().withName(PersonUtil.FIONA_NAME).withPhone(FIONA_PHONE)
            .withNric(PersonUtil.FIONA_NRIC).build();

    public static final String GEORGE_PHONE = "9482442";
    public static final Patient GEORGE = new PatientBuilder().withName(PersonUtil.GEORGE_NAME).withPhone(GEORGE_PHONE)
            .withNric(PersonUtil.GEORGE_NRIC).build();

    public static final String HOON_PHONE = "8482424";
    // Manually added
    public static final Patient HOON = new PatientBuilder().withName(PersonUtil.HOON_NAME).withPhone(HOON_PHONE)
            .withNric(PersonUtil.HOON_NRIC).build();

    public static final String IDA_PHONE = "8482131";
    public static final Patient IDA = new PatientBuilder().withName(PersonUtil.IDA_NAME).withPhone(IDA_PHONE)
            .withNric(PersonUtil.IDA_NRIC).build();

    // Manually added - Patient's details found in {@code CommandTestUtil}
    public static final Patient AMY = new PatientBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withNric(VALID_NRIC_AMY).build();
    public static final Patient BOB = new PatientBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withNric(VALID_NRIC_BOB).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPatient() {} // prevents instantiation

    public static List<Patient> getTypicalPatient() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
