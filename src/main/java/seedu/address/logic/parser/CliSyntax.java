package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_PATIENT_NRIC = new Prefix("pic\\");
    public static final Prefix PREFIX_DOCTOR_NRIC = new Prefix("dic\\");
    public static final Prefix PREFIX_APPOINTMENT_START_TIME =  new Prefix("from\\");
    public static final Prefix PREFIX_APPOINTMENT_END_TIME =  new Prefix("end\\");

}
