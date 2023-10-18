package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.model.NewModel.PREDICATE_SHOW_ALL_APPOINTMENTS;
import static seedu.address.testutil.TypicalDatabase.getTypicalDatabase;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPatient.ALICE_NRIC;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.*;
import seedu.address.model.appointment.AppointmentEqualDoctorNricPredicate;
import seedu.address.model.appointment.AppointmentEqualPatientNricPredicate;
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
        assertNewCommandSuccess(new ListAppointmentCommand(), model, ListAppointmentCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showAppointmentAtIndex(model, INDEX_FIRST_PERSON);
        assertNewCommandSuccess(new ListAppointmentCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsAppointmentsWithPatientNric() {
        Nric aliceNric = new Nric(ALICE_NRIC);
        showAppointmentsWithPatientNric(model, aliceNric);
        assertNewCommandSuccess(new ListAppointmentCommand(new AppointmentEqualPatientNricPredicate(aliceNric),
                        PREDICATE_SHOW_ALL_APPOINTMENTS),
                model,
                ListCommand.MESSAGE_SUCCESS,
                expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsAppointmentsWithDoctorNric() {
        Nric aliceNric = new Nric(ALICE_NRIC);
        showAppointmentsWithDoctorNric(model, aliceNric);
        assertNewCommandSuccess(new ListAppointmentCommand(PREDICATE_SHOW_ALL_APPOINTMENTS,
                        new AppointmentEqualDoctorNricPredicate(aliceNric)),
                model,
                ListCommand.MESSAGE_SUCCESS,
                expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsAppointmentsWithDoctorAndPatientNric() {
        Nric aliceNric = new Nric(ALICE_NRIC);
        Nric aliceNric = new Nric(ALICE_NRIC);
        showAppointmentsWithDoctorNric(model, aliceNric);
        assertNewCommandSuccess(new ListAppointmentCommand(PREDICATE_SHOW_ALL_APPOINTMENTS,
                        new AppointmentEqualDoctorNricPredicate(aliceNric)),
                model,
                ListCommand.MESSAGE_SUCCESS,
                expectedModel);
    }

}