package seedu.address.logic;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.NewCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.NewAddressBookParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.NewModel;
import seedu.address.model.ReadOnlyDatabase;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.doctor.Doctor;
import seedu.address.model.person.patient.Patient;
import seedu.address.storage.NewStorage;

/**
 * The main LogicManager of the app.
 */
public class NewLogicManager implements NewLogic {
    public static final String FILE_OPS_ERROR_FORMAT = "Could not save data due to the following error: %s";

    public static final String FILE_OPS_PERMISSION_ERROR_FORMAT =
            "Could not save data to file %s due to insufficient permissions to write to the file or the folder.";

    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final NewModel model;
    private final NewStorage storage;
    private final NewAddressBookParser newAddressBookParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public NewLogicManager(NewModel model, NewStorage storage) {
        this.model = model;
        this.storage = storage;
        newAddressBookParser = new NewAddressBookParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        NewCommand command = newAddressBookParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveDatabase(model.getDatabase());
        } catch (AccessDeniedException e) {
            throw new CommandException(String.format(FILE_OPS_PERMISSION_ERROR_FORMAT, e.getMessage()), e);
        } catch (IOException ioe) {
            throw new CommandException(String.format(FILE_OPS_ERROR_FORMAT, ioe.getMessage()), ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyDatabase getDatabase() {
        return model.getDatabase();
    }

    @Override
    public ObservableList<Appointment> getFilteredAppointmentList() {
        return model.getFilteredAppointmentList();
    }

    @Override
    public ObservableList<Patient> getFilteredPatientList() {
        return model.getFilteredPatientList();
    }

    @Override
    public ObservableList<Doctor> getFilteredDoctorList() {
        return model.getFilteredDoctorList();
    }
    @Override
    public Path getDatabaseFilePath() {
        return model.getDatabaseFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }
}
