package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.person.patient.Patient;

/**
 * A utility class containing a list of {@code Patient} objects to be used in tests.
 */
public class TypicalPatient {
    public static final String ALICE_NAME = "Alice Pauline";
    public static final String ALICE_PHONE = "94351253";
    public static final String ALICE_NRIC = "T0123456J";
    public static final Patient ALICE = new PatientBuilder().withName(ALICE_NAME).withPhone(ALICE_PHONE)
            .withNric(ALICE_NRIC).build();

    public static final String BENSON_NAME = "Benson Meier";
    public static final String BENSON_PHONE = "98765432";
    public static final String BENSON_NRIC = "S2936283D";
    public static final Patient BENSON = new PatientBuilder().withName(BENSON_NAME).withPhone(BENSON_PHONE)
            .withNric(BENSON_NRIC).build();

    public static final String CARL_NAME = "Carl Kurz";
    public static final String CARL_PHONE = "95352563";
    public static final String CARL_NRIC = "T3856391A";
    public static final Patient CARL = new PatientBuilder().withName(CARL_NAME).withPhone(CARL_PHONE)
            .withNric(CARL_NRIC).build();

    public static final String DANIEL_NAME = "Daniel Meier";
    public static final String DANIEL_PHONE = "87652533";
    public static final String DANIEL_NRIC = "S9754123F";
    public static final Patient DANIEL = new PatientBuilder().withName(DANIEL_NAME).withPhone(DANIEL_PHONE)
            .withNric(DANIEL_NRIC).build();

    public static final String ELLE_NAME = "Elle Meyer";
    public static final String ELLE_PHONE = "9482224";
    public static final String ELLE_NRIC = "G4123573C";
    public static final Patient ELLE = new PatientBuilder().withName(ELLE_NAME).withPhone(ELLE_PHONE)
            .withNric(ELLE_NRIC).build();

    public static final String FIONA_NAME = "Fiona Kunz";
    public static final String FIONA_PHONE = "9482427";
    public static final String FIONA_NRIC = "G6739542H";
    public static final Patient FIONA = new PatientBuilder().withName(FIONA_NAME).withPhone(FIONA_PHONE)
            .withNric(FIONA_NRIC).build();

    public static final String GEORGE_NAME = "George Best";
    public static final String GEORGE_PHONE = "9482442";
    public static final String GEORGE_NRIC = "T0359320R";
    public static final Patient GEORGE = new PatientBuilder().withName(GEORGE_NAME).withPhone(GEORGE_PHONE)
            .withNric(GEORGE_NRIC).build();

    public static final String HOON_NAME = "Hoon Meier";
    public static final String HOON_PHONE = "8482424";
    public static final String HOON_NRIC = "T0248362R";
    // Manually added
    public static final Patient HOON = new PatientBuilder().withName(HOON_NAME).withPhone(HOON_PHONE)
            .withNric(HOON_NRIC).build();

    public static final String IDA_NAME = "Ida Mueller";
    public static final String IDA_PHONE = "8482131";
    public static final String IDA_NRIC = "S9348573F";
    public static final Patient IDA = new PatientBuilder().withName(IDA_NAME).withPhone(IDA_PHONE)
            .withNric(IDA_NRIC).build();

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
