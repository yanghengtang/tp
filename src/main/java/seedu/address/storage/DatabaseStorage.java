package seedu.address.storage;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyAddressBook;
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
    Path getDatabasePatientFilePath();

    /**
     * Returns the file path of the doctor data file.
     */
    Path getDatabaseDoctorFilePath();

    /**
     * Returns the file path of the appointmnet data file.
     */
    Path getDatabaseAppointmentFilePath();

    /**
     * Returns Database data as a {@link seedu.address.model.ReadOnlyDatabase}.
     * Returns {@code Optional.empty()} if storage files are not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyDatabase> readDatabase() throws DataLoadingException;

    /**
     * @see #getDatabasePatientFilePath(), #getDatabaseDoctorFilePath(), #getDatabaseAppointmentFilePath()
     */
    Optional<ReadOnlyAddressBook> readAddressBook(Path patientFilePath, Path doctorFilePath, Path appointmentFilePath)
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
    void saveDatabase(ReadOnlyDatabase database, Path patientFilePath, Path doctorFilePath, Path appointmentFilePath)
            throws IOException;

}
