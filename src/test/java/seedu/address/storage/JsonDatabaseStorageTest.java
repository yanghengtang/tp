package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalDatabase.getTypicalDatabase;
import static seedu.address.testutil.TypicalPatient.ALICE;
import static seedu.address.testutil.TypicalPatient.HOON;
import static seedu.address.testutil.TypicalPatient.IDA;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.Database;
import seedu.address.model.ReadOnlyDatabase;

public class JsonDatabaseStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonDatabaseStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readDatabase_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readDatabase(null));
    }

    private java.util.Optional<ReadOnlyDatabase> readDatabase(String filePath) throws Exception {
        return new JsonDatabaseStorage(Paths.get(filePath)).readDatabase(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readDatabase("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataLoadingException.class, () -> readDatabase("notJsonFormatDatabase.json"));
    }

    @Test
    public void readDatabase_invalidPersonAddressBook_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readDatabase("invalidPatientDatabase.json"));
         assertThrows(DataLoadingException.class, () -> readDatabase("invalidDoctorDatabase.json"));
         assertThrows(DataLoadingException.class, () -> readDatabase("invalidAppointmentDatabase.json"));
    }

    @Test
    public void readAddressBook_invalidAndValidPersonAddressBook_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readDatabase("invalidAndValidPatientDatabase.json"));
        assertThrows(DataLoadingException.class, () -> readDatabase("invalidAndValidDoctorDatabase.json"));
        assertThrows(DataLoadingException.class, () -> readDatabase("invalidAndValidAppointmentDatabase.json"));
    }

    @Test
    public void readAndSaveDatabase_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempDatabase.json");
        Database original = getTypicalDatabase();
        JsonDatabaseStorage jsonDatabaseStorage = new JsonDatabaseStorage(filePath);

        // Save in new file and read back
        jsonDatabaseStorage.saveDatabase(original, filePath);
        ReadOnlyDatabase readBack = jsonDatabaseStorage.readDatabase(filePath).get();
        assertEquals(original, new Database(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPatient(HOON);
        original.removePatient(ALICE);
        jsonDatabaseStorage.saveDatabase(original, filePath);
        readBack = jsonDatabaseStorage.readDatabase(filePath).get();
        assertEquals(original, new Database(readBack));

        // Save and read without specifying file path
        original.addPatient(IDA);
        jsonDatabaseStorage.saveDatabase(original); // file path not specified
        readBack = jsonDatabaseStorage.readDatabase().get(); // file path not specified
        assertEquals(original, new Database(readBack));

    }

    @Test
    public void saveDatabase_nullAddressBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveDatabase(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveDatabase(ReadOnlyDatabase database, String filePath) {
        try {
            new JsonDatabaseStorage(Paths.get(filePath))
                    .saveDatabase(database, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveDatabase_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveDatabase(new Database(), null));
    }
}
