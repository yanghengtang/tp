package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.Messages.MESSAGE_INVALID_DOCTOR_DISPLAYED_INDEX;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NRIC_DESC_AMY;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalDoctor.AMY;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.logic.commands.AddDoctorCommand;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.ListDoctorCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.NewModel;
import seedu.address.model.NewModelManager;
import seedu.address.model.ReadOnlyDatabase;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.doctor.Doctor;
import seedu.address.storage.JsonDatabaseStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.NewStorageManager;
import seedu.address.testutil.DoctorBuilder;

public class NewLogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy IO exception");
    private static final IOException DUMMY_AD_EXCEPTION = new AccessDeniedException("dummy access denied exception");

    @TempDir
    public Path temporaryFolder;

    private NewModel newModel = new NewModelManager();
    private NewLogic newLogic;

    @BeforeEach
    public void setUp() {
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        JsonDatabaseStorage databaseStorage =
                new JsonDatabaseStorage(temporaryFolder.resolve("database.json"));
        NewStorageManager newStorage = new NewStorageManager(databaseStorage, userPrefsStorage);
        newLogic = new NewLogicManager(newModel, newStorage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        String deleteDoctorCommand = "delete_d 9999";
        assertCommandException(deleteDoctorCommand, MESSAGE_INVALID_DOCTOR_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validDoctorCommand_success() throws Exception {
        String listDoctorCommand = ListDoctorCommand.COMMAND_WORD;

        assertNewCommandSuccess(listDoctorCommand, ListDoctorCommand.MESSAGE_SUCCESS, newModel);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        assertCommandFailureForExceptionFromStorage(DUMMY_IO_EXCEPTION, String.format(
                NewLogicManager.FILE_OPS_ERROR_FORMAT, DUMMY_IO_EXCEPTION.getMessage()));
    }

    @Test
    public void execute_storageThrowsAdException_throwsCommandException() {
        assertCommandFailureForExceptionFromStorage(DUMMY_AD_EXCEPTION, String.format(
                NewLogicManager.FILE_OPS_PERMISSION_ERROR_FORMAT, DUMMY_AD_EXCEPTION.getMessage()));
    }

    @Test
    public void getFilteredDoctorList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> newLogic.getFilteredDoctorList().remove(0));
    }

    /**
     * Executes the command and confirms that
     * - no exceptions are thrown <br>
     * - the feedback message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     * @see #assertCommandFailure(String, Class, String, NewModel)
     */
    private void assertNewCommandSuccess(String inputCommand, String expectedMessage,
                                      NewModel expectedModel) throws CommandException, ParseException {
        CommandResult result = newLogic.execute(inputCommand);
        assertEquals(expectedMessage, result.getFeedbackToUser());
        assertEquals(expectedModel, newModel);
    }

    /**
     * Executes the command, confirms that a ParseException is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, NewModel)
     */
    private void assertParseException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, ParseException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that a CommandException is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, NewModel)
     */
    private void assertCommandException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, CommandException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that the exception is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, NewModel)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
            String expectedMessage) {
        NewModel expectedModel = new NewModelManager(newModel.getDatabase(), new UserPrefs());
        assertCommandFailure(inputCommand, expectedException, expectedMessage, expectedModel);
    }

    /**
     * Executes the command and confirms that
     * - the {@code expectedException} is thrown <br>
     * - the resulting error message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     * @see #assertCommandSuccess(String, String, NewModel)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
            String expectedMessage, NewModel expectedModel) {
        assertThrows(expectedException, expectedMessage, () -> newLogic.execute(inputCommand));
        assertEquals(expectedModel, newModel);
    }

    /**
     * Tests the Logic component's handling of an {@code IOException} thrown by the Storage component.
     *
     * @param e the exception to be thrown by the Storage component
     * @param expectedMessage the message expected inside exception thrown by the Logic component
     */
    private void assertCommandFailureForExceptionFromStorage(IOException e, String expectedMessage) {
        Path prefPath = temporaryFolder.resolve("ExceptionUserPrefs.json");

        // Inject LogicManager with an AddressBookStorage that throws the IOException e when saving
        JsonDatabaseStorage databaseStorage = new JsonDatabaseStorage(prefPath) {
            @Override
            public void saveDatabase(ReadOnlyDatabase database, Path filePath)
                    throws IOException {
                throw e;
            }
        };

        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ExceptionUserPrefs.json"));
        NewStorageManager storage = new NewStorageManager(databaseStorage, userPrefsStorage);

        newLogic = new NewLogicManager(newModel, storage);

        // Triggers the saveDatabase method by executing an add command
        String addCommand = AddDoctorCommand.COMMAND_WORD + NAME_DESC_AMY + NRIC_DESC_AMY;
        Doctor expectedDoctor = new DoctorBuilder(AMY).build();
        NewModelManager expectedModel = new NewModelManager();
        expectedModel.addDoctor(expectedDoctor);
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    }
}
