package seedu.address.storage;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyDatabase;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

/**
 * API of the NewStorage component
 */
public interface NewStorage extends DatabaseStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataLoadingException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getDatabasePatientFilePath();

    @Override
    Path getDatabaseDoctorFilePath();

    @Override
    Path getDatabaseAppointmentFilePath();

    @Override
    Optional<ReadOnlyDatabase> readDatabase() throws DataLoadingException;

    @Override
    void saveDatabase(ReadOnlyDatabase database) throws IOException;

}
