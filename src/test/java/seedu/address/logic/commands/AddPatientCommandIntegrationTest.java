package seedu.address.logic.commands;

import static seedu.address.logic.commands.NewCommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.NewCommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalDatabase.getTypicalDatabase;
import static seedu.address.testutil.PersonUtil.HOON_NAME;
import static seedu.address.testutil.PersonUtil.HOON_NRIC;
import static seedu.address.testutil.TypicalPatient.HOON_PHONE;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.NewModel;
import seedu.address.model.NewModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.patient.Patient;
import seedu.address.testutil.PatientBuilder;

/**
 * Contains integration tests (interaction with the Model) for the AddPatientCommand class
 */
public class AddPatientCommandIntegrationTest {
    private NewModel newModel;

    @BeforeEach
    public void setUp() {
        newModel = new NewModelManager(getTypicalDatabase(), new UserPrefs());
    }

    @Test
    public void execute_newPatient_success() {
        Patient validPatient = new PatientBuilder().withNric(HOON_NRIC)
                .withName(HOON_NAME).withPhone(HOON_PHONE).build();

        NewModel expectedModel = new NewModelManager(newModel.getDatabase(), new UserPrefs());
        expectedModel.addPatient(validPatient);
        assertCommandSuccess(new AddPatientCommand(validPatient), newModel,
                String.format(AddPatientCommand.MESSAGE_SUCCESS, Messages.format(validPatient)),
                expectedModel);
    }

    @Test
    public void execute_duplicatePatient_throwsCommandException() {
        Patient patientInList = newModel.getDatabase().getPatientList().get(0);
        assertCommandFailure(new AddPatientCommand(patientInList), newModel,
                AddPatientCommand.MESSAGE_DUPLICATE_PATIENT);
    }
}
