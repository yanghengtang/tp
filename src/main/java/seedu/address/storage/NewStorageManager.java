package seedu.address.storage;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyDatabase;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * Manages storage of MediConnect data in local storage.
 */
public class NewStorageManager implements NewStorage {

    private static final Logger logger = LogsCenter.getLogger(NewStorageManager.class);
    private DatabaseStorage databaseStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code NewStorageManager} with the given {@code DatabaseStorage} and {@code UserPrefStorage}.
     */
    public NewStorageManager(DatabaseStorage databaseStorage, UserPrefsStorage userPrefsStorage) {
        this.databaseStorage = databaseStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataLoadingException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ Database methods ==============================

    @Override
    public Path getDatabasePatientFilePath() {
        return databaseStorage.getDatabasePatientFilePath();
    }

    @Override
    public Path getDatabaseDoctorFilePath() {
        return databaseStorage.getDatabaseDoctorFilePath();
    }

    @Override
    public Path getDatabaseAppointmentFilePath() {
        return databaseStorage.getDatabaseAppointmentFilePath();
    }

    @Override
    public Optional<ReadOnlyDatabase> readDatabase() throws DataLoadingException {
        return readDatabase(databaseStorage.getDatabasePatientFilePath(),
                databaseStorage.getDatabaseDoctorFilePath(),
                databaseStorage.getDatabaseAppointmentFilePath());
    }

    @Override
    public Optional<ReadOnlyDatabase> readDatabase(Path patientFilePath, Path doctorFilePath, Path appointmentFilePath)
            throws DataLoadingException {
        logger.fine("Attempting to read data from files: " + patientFilePath + ", " + doctorFilePath + ", "
                + appointmentFilePath);
        return databaseStorage.readDatabase(patientFilePath, doctorFilePath, appointmentFilePath);
    }

    @Override
    public void saveDatabase(ReadOnlyDatabase database) throws IOException {
        saveDatabase(database, databaseStorage.getDatabasePatientFilePath(),
                databaseStorage.getDatabaseDoctorFilePath(), databaseStorage.getDatabaseAppointmentFilePath());
    }

    @Override
    public void saveDatabase(ReadOnlyDatabase database, Path patientFilePath, Path doctorFilePath,
                             Path appointmentFilePath) throws IOException {
        logger.fine("Attempting to write to data files: " + patientFilePath + ", " + doctorFilePath + ", "
                + appointmentFilePath);
        databaseStorage.saveDatabase(database, patientFilePath, doctorFilePath, appointmentFilePath);
    }

}
