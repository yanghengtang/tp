package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.Database;
import seedu.address.testutil.TypicalDatabase;

public class JsonSerializableDatabaseTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableDatabaseTest");
    private static final Path TYPICAL_DATABASE_FILE = TEST_DATA_FOLDER.resolve("typicalDatabase.json");
    private static final Path INVALID_PATIENTS_DATABASE_FILE = TEST_DATA_FOLDER.resolve("invalidPatientDatabase.json");
    private static final Path DUPLICATE_PATIENTS_DATABASE_FILE =
            TEST_DATA_FOLDER.resolve("duplicatePatientDatabase.json");
    private static final Path INVALID_DOCTORS_DATABASE_FILE = TEST_DATA_FOLDER.resolve("invalidDoctorDatabase.json");
    private static final Path DUPLICATE_DOCTORS_DATABASE_FILE =
            TEST_DATA_FOLDER.resolve("duplicateDoctorDatabase.json");
    private static final Path INVALID_APPOINTMENTS_DATABASE_FILE =
            TEST_DATA_FOLDER.resolve("invalidAppointmentDatabase.json");
    private static final Path DUPLICATE_APPOINTMENTS_DATABASE_FILE =
            TEST_DATA_FOLDER.resolve("duplicateAppointmentDatabase.json");

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

    @Test
    public void toModelType_invalidDoctorsFile_throwsIllegalValueException() throws Exception {
        JsonSerializableDatabase dataFromFile = JsonUtil.readJsonFile(INVALID_DOCTORS_DATABASE_FILE,
                JsonSerializableDatabase.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateDoctors_throwsIllegalValueException() throws Exception {
        JsonSerializableDatabase dataFromFile = JsonUtil.readJsonFile(DUPLICATE_DOCTORS_DATABASE_FILE,
                JsonSerializableDatabase.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableDatabase.MESSAGE_DUPLICATE_DOCTOR,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_invalidAppointmentsFile_throwsIllegalValueException() throws Exception {
        JsonSerializableDatabase dataFromFile = JsonUtil.readJsonFile(INVALID_APPOINTMENTS_DATABASE_FILE,
                JsonSerializableDatabase.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateAppointments_throwsIllegalValueException() throws Exception {
        JsonSerializableDatabase dataFromFile = JsonUtil.readJsonFile(DUPLICATE_APPOINTMENTS_DATABASE_FILE,
                JsonSerializableDatabase.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableDatabase.MESSAGE_DUPLICATE_APPOINTMENT,
                dataFromFile::toModelType);
    }
}
