package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalDatabase.getTypicalDatabase;
import static seedu.address.testutil.TypicalPatient.HOON_NAME;
import static seedu.address.testutil.TypicalPatient.HOON_NRIC;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.doctor.Doctor;
import seedu.address.testutil.DoctorBuilder;

/**
 * Contains integration tests (interaction with the Model) for the AddDoctorCommand class.
 */
public class AddDoctorCommandIntegrationTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalDatabase(), new UserPrefs());
    }

    @Test
    public void execute_newDoctor_success() {
        Doctor validDoctor = new DoctorBuilder().withNric(HOON_NRIC)
                .withName(HOON_NAME).build();

        Model expectedModel = new ModelManager(model.getDatabase(), new UserPrefs());
        expectedModel.addDoctor(validDoctor);
        assertCommandSuccess(new AddDoctorCommand(validDoctor), model,
                String.format(AddDoctorCommand.MESSAGE_SUCCESS, Messages.format(validDoctor)),
                expectedModel);
    }

    @Test
    public void execute_duplicateDoctor_throwsCommandException() {
        Doctor doctorInList = model.getDatabase().getDoctorList().get(0);
        assertCommandFailure(new AddDoctorCommand(doctorInList), model,
                AddDoctorCommand.MESSAGE_DUPLICATE_DOCTOR);
    }
}
