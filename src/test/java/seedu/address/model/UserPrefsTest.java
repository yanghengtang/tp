package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.GuiSettings;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class UserPrefsTest {
    private UserPrefs userPrefs = new UserPrefs();

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        UserPrefs userPref = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPref.setGuiSettings(null));
    }

    @Test
    public void setDatabaseFilePath_nullPath_throwsNullPointerException() {
        UserPrefs userPrefs = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPrefs.setDatabaseFilePath(null));
    }

    @Test
    public void equals() {
        UserPrefs sameUserPrefs = new UserPrefs(userPrefs);
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setDatabaseFilePath(Paths.get("data", "dataBase.json"));

        // same values -> returns true
        assertTrue(userPrefs.equals(sameUserPrefs));

        // same object -> returns true
        assertTrue(userPrefs.equals(userPrefs));

        // null -> returns false
        assertFalse(userPrefs.equals(null));

        // different types -> returns false
        assertFalse(userPrefs.equals(5.0f));

        // different database FilePath -> return false
        assertFalse(userPrefs.equals(differentUserPrefs));
    }

    @Test
    public void hashCodeMethod() {
        GuiSettings guiSettings = new GuiSettings();
        Path databaseFilePath = Paths.get("data" , "database.json");

        // same value -> return equals
        assertEquals(userPrefs.hashCode(), Objects.hash(guiSettings, databaseFilePath));
    }

}
