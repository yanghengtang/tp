package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /** Help information should be shown to the user. */
    private final boolean showAppointment;

    /** Help information should be shown to the user. */
    private final boolean showDoctor;

    /** Help information should be shown to the user. */
    private final boolean showPatient;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** The application should exit. */
    private final boolean exit;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showAppointment, boolean showDoctor,
                         boolean showPatient, boolean showHelp, boolean exit) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showAppointment = showAppointment;
        this.showDoctor = showDoctor;
        this.showPatient = showPatient;
        this.showHelp = showHelp;
        this.exit = exit;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, false, false, false);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser}, {@code showHelp},
     * {@code exit}, and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit) {
        this(feedbackToUser, false, false, false, showHelp, exit);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser}, {@code showAppointment},
     * {@code showDoctor}, {@code showPatient}, and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, boolean showAppointment, boolean showDoctor, boolean showPatient) {
        this(feedbackToUser, showAppointment, showDoctor, showPatient, false, false);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isShowAppointment() {
        return showAppointment;
    }

    public boolean isShowDoctor() {
        return showDoctor;
    }

    public boolean isShowPatient() {
        return showPatient;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isExit() {
        return exit;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandResult)) {
            return false;
        }

        CommandResult otherCommandResult = (CommandResult) other;
        return feedbackToUser.equals(otherCommandResult.feedbackToUser)
                && showAppointment == otherCommandResult.showAppointment
                && showDoctor == otherCommandResult.showDoctor
                && showPatient == otherCommandResult.showPatient
                && showHelp == otherCommandResult.showHelp
                && exit == otherCommandResult.exit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showAppointment, showDoctor, showPatient, showHelp, exit);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("feedbackToUser", feedbackToUser)
                .add("showAppointment", showAppointment)
                .add("showDoctor", showDoctor)
                .add("showPatient", showPatient)
                .add("showHelp", showHelp)
                .add("exit", exit)
                .toString();
    }

}
