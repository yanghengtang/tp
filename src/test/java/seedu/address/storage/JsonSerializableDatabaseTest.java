package seedu.address.storage;

import org.junit.jupiter.api.Test;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.Database;
import seedu.address.testutil.TypicalDatabase;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

public class JsonSerializableDatabaseTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableDatabaseTest");
    private static final Path TYPICAL_DATABASE_FILE = TEST_DATA_FOLDER.resolve("typicalDatabase.json");
    private static final Path INVALID_PATIENTS_DATABASE_FILE = TEST_DATA_FOLDER.resolve("invalidPatientDatabase.json");
    private static final Path DUPLICATE_PATIENTS_DATABASE_FILE = TEST_DATA_FOLDER.resolve("duplicatePatientDatabase.json");

    @Test
    public void toModelType_typicalDatabaseFile_success() throws Exception {
        JsonSerializableDatabase dataFromFile = JsonUtil.readJsonFile(TYPICAL_DATABASE_FILE,
                JsonSerializableDatabase.class).get();
        Database databaseFromFile = dataFromFile.toModelType();
        Database typicalDatabase = TypicalDatabase.getTypicalDatabase();
        assertEquals(databaseFromFile, typicalDatabase);
    }

    @Test
    public void toModelType_invalidPatientsFile_throwsIllegalValueException() throws Exception {
        JsonSerializableDatabase dataFromFile = JsonUtil.readJsonFile(INVALID_PATIENTS_DATABASE_FILE,
                JsonSerializableDatabase.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePatients_throwsIllegalValueException() throws Exception {
        JsonSerializableDatabase dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PATIENTS_DATABASE_FILE,
                JsonSerializableDatabase.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableDatabase.MESSAGE_DUPLICATE_PATIENT,
                dataFromFile::toModelType);
    }

}
