package seedu.address.logic.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.address.logic.Messages;
import seedu.address.model.NewModel;
import seedu.address.model.NewModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.doctor.Doctor;
import seedu.address.model.person.patient.Patient;
import seedu.address.testutil.DoctorBuilder;
import seedu.address.testutil.PatientBuilder;

import static seedu.address.logic.commands.CommandTestUtil.assertNewCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertNewCommandSuccess;
import static seedu.address.testutil.TypicalAppointment.getTypicalDatabase;
import static seedu.address.testutil.TypicalPatient.*;

public class AddDoctorCommandIntegrationTest {
    private NewModel newModel;

    @BeforeEach
    public void setUp() {
        newModel = new NewModelManager(getTypicalDatabase(), new UserPrefs());
    }

    @Test
    public void execute_newDoctor_success() {
        Doctor validDoctor = new DoctorBuilder().withNric(HOON_NRIC)
                .withName(HOON_NAME).build();

        NewModel expectedModel = new NewModelManager(newModel.getDatabase(), new UserPrefs());
        expectedModel.addDoctor(validDoctor);
        assertNewCommandSuccess(new AddDoctorCommand(validDoctor), newModel,
                String.format(AddDoctorCommand.MESSAGE_SUCCESS, Messages.format(validDoctor)),
                expectedModel);
    }

    @Test
    public void execute_duplicateDoctor_throwsCommandException() {
        Doctor doctorInList = newModel.getDatabase().getDoctorList().get(0);
        assertNewCommandFailure(new AddDoctorCommand(doctorInList), newModel,
                AddDoctorCommand.MESSAGE_DUPLICATE_DOCTOR);
    }
}
