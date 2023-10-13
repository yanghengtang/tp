package seedu.address.storage;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyDatabase;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import static java.util.Objects.requireNonNull;

/**
 * A class to access Database data stored as a json file on the hard disk.
 */
public class JsonDatabaseStorage implements DatabaseStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonDatabaseStorage.class);

    private Path filePath;

    public JsonDatabaseStorage(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public Path getDatabaseFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyDatabase> readDatabase() throws DataLoadingException {
        return readDatabase(filePath);
    }

    /**
     * Similar to {@link #readDatabase()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    public Optional<ReadOnlyDatabase> readDatabase(Path filePath)
            throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializableDatabase> jsonDatabase = JsonUtil.readJsonFile(
                filePath, JsonSerializableDatabase.class);
        if (!jsonDatabase.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonDatabase.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }

    @Override
    public void saveDatabase(ReadOnlyDatabase database) throws IOException {
        saveDatabase(database, filePath);
    }

    /**
     * Similar to {@link #saveDatabase(ReadOnlyDatabase)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveDatabase(ReadOnlyDatabase database, Path filePath) throws IOException {
        requireNonNull(database);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableDatabase(database), filePath);
    }

}
