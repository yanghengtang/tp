package seedu.address.storage;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyDatabase;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

/**
 * Represents a storage for {@link seedu.address.model.Database}.
 */
public interface DatabaseStorage {

    /**
     * Returns the file path of the patient data file.
     */
    Path getDatabaseFilePath();

    /**
     * Returns Database data as a {@link seedu.address.model.ReadOnlyDatabase}.
     * Returns {@code Optional.empty()} if storage files are not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyDatabase> readDatabase() throws DataLoadingException;

    /**
     * @see #getDatabaseFilePath()
     */
    Optional<ReadOnlyDatabase> readDatabase(Path filePath)
            throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyDatabase} to the storage.
     * @param database cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveDatabase(ReadOnlyDatabase database) throws IOException;

    /**
     * @see #saveDatabase(ReadOnlyDatabase)
     */
    void saveDatabase(ReadOnlyDatabase database, Path filePath) throws IOException;
}
