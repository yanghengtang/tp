package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.NewCommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.NewCommandTestUtil.showAppointmentAtIndex;
import static seedu.address.model.NewModel.PREDICATE_SHOW_ALL_APPOINTMENTS;
import static seedu.address.testutil.TypicalDatabase.getTypicalDatabase;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.PersonUtil.ALICE_NRIC;
import static seedu.address.testutil.PersonUtil.BENSON_NRIC;

import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.NewModel;
import seedu.address.model.NewModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentEqualDoctorNricPredicate;
import seedu.address.model.appointment.AppointmentEqualPatientNricPredicate;
import seedu.address.model.appointment.AppointmentFilterByNricPredicate;
import seedu.address.model.person.Nric;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListAppointmentCommandTest {

    private NewModel model;
    private NewModel expectedModel;

    @BeforeEach
    public void setUp() {
        model = new NewModelManager(getTypicalDatabase(), new UserPrefs());
        expectedModel = new NewModelManager(model.getDatabase(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListAppointmentCommand(), model,
                ListAppointmentCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showAppointmentAtIndex(model, INDEX_FIRST_PERSON);
        assertCommandSuccess(new ListAppointmentCommand(),
                model,
                ListAppointmentCommand.MESSAGE_SUCCESS,
                expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsAppointmentsWithPatientNric() {
        Nric aliceNric = new Nric(ALICE_NRIC);
        AppointmentEqualPatientNricPredicate patientPredicate =
                new AppointmentEqualPatientNricPredicate(aliceNric);
        Predicate<Appointment> doctorPredicate = PREDICATE_SHOW_ALL_APPOINTMENTS;
        AppointmentFilterByNricPredicate predicate =
                new AppointmentFilterByNricPredicate(patientPredicate, doctorPredicate);
        ListAppointmentCommand command = new ListAppointmentCommand(predicate);
        expectedModel.updateFilteredAppointmentList(predicate);
        assertCommandSuccess(command,
                model,
                ListAppointmentCommand.MESSAGE_SUCCESS,
                expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsAppointmentsWithDoctorNric() {
        Nric aliceNric = new Nric(ALICE_NRIC);
        AppointmentEqualDoctorNricPredicate doctorPredicate =
                new AppointmentEqualDoctorNricPredicate(aliceNric);
        Predicate<Appointment> patientPredicate = PREDICATE_SHOW_ALL_APPOINTMENTS;
        AppointmentFilterByNricPredicate predicate =
                new AppointmentFilterByNricPredicate(patientPredicate, doctorPredicate);
        ListAppointmentCommand command = new ListAppointmentCommand(predicate);
        expectedModel.updateFilteredAppointmentList(predicate);
        assertCommandSuccess(command,
                model,
                ListAppointmentCommand.MESSAGE_SUCCESS,
                expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsAppointmentsWithDoctorAndPatientNric() {
        Nric aliceNric = new Nric(ALICE_NRIC);
        Nric bensonNric = new Nric(BENSON_NRIC);
        AppointmentEqualPatientNricPredicate patientPredicate =
                new AppointmentEqualPatientNricPredicate(aliceNric);
        AppointmentEqualDoctorNricPredicate doctorPredicate =
                new AppointmentEqualDoctorNricPredicate(bensonNric);
        AppointmentFilterByNricPredicate predicate =
                new AppointmentFilterByNricPredicate(patientPredicate, doctorPredicate);
        ListAppointmentCommand command = new ListAppointmentCommand(predicate);
        expectedModel.updateFilteredAppointmentList(predicate);
        assertCommandSuccess(command,
                model,
                ListAppointmentCommand.MESSAGE_SUCCESS,
                expectedModel);
    }

    @Test
    public void equals() {
        Nric aliceNric = new Nric(ALICE_NRIC);
        Nric bensonNric = new Nric(BENSON_NRIC);
        AppointmentEqualPatientNricPredicate patientPredicate =
                new AppointmentEqualPatientNricPredicate(aliceNric);
        AppointmentEqualDoctorNricPredicate doctorPredicate =
                new AppointmentEqualDoctorNricPredicate(bensonNric);
        AppointmentFilterByNricPredicate predicate =
                new AppointmentFilterByNricPredicate(patientPredicate, doctorPredicate);
        ListAppointmentCommand command1 = new ListAppointmentCommand(predicate);
        ListAppointmentCommand command2 = new ListAppointmentCommand();

        // same object -> returns true
        assertTrue(command1.equals(command1));

        // same values -> returns true
        ListAppointmentCommand command1Copy = new ListAppointmentCommand(predicate);
        assertTrue(command1.equals(command1Copy));

        // different types -> returns false
        assertFalse(command1.equals(1));

        // null -> returns false
        assertFalse(command1.equals(null));

        // different person -> returns false
        assertFalse(command1.equals(command2));
    }

    @Test
    public void toStringMethod() {
        Nric aliceNric = new Nric(ALICE_NRIC);
        Nric bensonNric = new Nric(BENSON_NRIC);
        AppointmentEqualPatientNricPredicate patientPredicate =
                new AppointmentEqualPatientNricPredicate(aliceNric);
        AppointmentEqualDoctorNricPredicate doctorPredicate =
                new AppointmentEqualDoctorNricPredicate(bensonNric);
        AppointmentFilterByNricPredicate predicate =
                new AppointmentFilterByNricPredicate(patientPredicate, doctorPredicate);
        ListAppointmentCommand command1 =
                new ListAppointmentCommand(predicate);
        String expected = ListAppointmentCommand.class.getCanonicalName()
                + "{predicate=" + predicate + "}";
        assertEquals(expected, command1.toString());
    }
}
