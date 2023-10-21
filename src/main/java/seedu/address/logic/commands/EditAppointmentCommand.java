package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_START_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DOCTOR_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PATIENT_NRIC;
import static seedu.address.model.NewModel.PREDICATE_SHOW_ALL_APPOINTMENTS;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.NewModel;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentEndTime;
import seedu.address.model.appointment.AppointmentStartTime;
import seedu.address.model.person.Nric;

/**
 * Edits the details of an existing appointment in the database.
 */
public class EditAppointmentCommand extends NewCommand {

    public static final String COMMAND_WORD = "edit_a";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the appointment identified "
            + "by the index number used in the displayed appointment list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_PATIENT_NRIC + "PATIENT_NRIC] "
            + "[" + PREFIX_DOCTOR_NRIC + "DOCTOR_NRIC] "
            + "[" + PREFIX_APPOINTMENT_START_TIME + "START_TIME] "
            + "[" + PREFIX_APPOINTMENT_END_TIME + "END_TIME]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PATIENT_NRIC + "T0123456J "
            + PREFIX_DOCTOR_NRIC + "T5012345A "
            + PREFIX_APPOINTMENT_START_TIME + "2023-09-12 07:30 "
            + PREFIX_APPOINTMENT_END_TIME + "2023-09-12 08:00";
    public static final String MESSAGE_EDIT_APPOINTMENT_SUCCESS = "Edited Appointment: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_APPOINTMENT = "This appointment already exists in the database.";

    private final Index index;
    private final EditAppointmentDescriptor editAppointmentDescriptor;

    /**
     * @param index of the Appointment in the filtered appointment list to edit
     * @param editAppointmentDescriptor details to edit the appointment with
     */
    public EditAppointmentCommand(Index index, EditAppointmentDescriptor editAppointmentDescriptor) {
        requireNonNull(index);
        requireNonNull(editAppointmentDescriptor);

        this.index = index;
        this.editAppointmentDescriptor = new EditAppointmentDescriptor(editAppointmentDescriptor);
    }

    @Override
    public CommandResult execute(NewModel model) throws CommandException {
        requireNonNull(model);
        List<Appointment> lastShownList = model.getFilteredAppointmentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
        }

        Appointment appointmentToEdit = lastShownList.get(index.getZeroBased());
        Appointment editedAppointment = createEditedAppointment(appointmentToEdit, editAppointmentDescriptor);

        if (!appointmentToEdit.isSame(editedAppointment) && model.hasAppointment(editedAppointment)) {
            throw new CommandException(MESSAGE_DUPLICATE_APPOINTMENT);
        }

        model.setAppointment(appointmentToEdit, editedAppointment);
        model.updateFilteredAppointmentList(PREDICATE_SHOW_ALL_APPOINTMENTS);
        return new CommandResult(String.format(MESSAGE_EDIT_APPOINTMENT_SUCCESS, Messages.format(editedAppointment)));
    }

    /**
     * Creates and returns a {@code Appointment} with the details of {@code appointmentToEdit}
     * edited with {@code editAppointmentDescriptor}.
     */
    private static Appointment createEditedAppointment(Appointment appointmentToEdit,
                                                       EditAppointmentDescriptor editAppointmentDescriptor) {
        assert appointmentToEdit != null;

        Nric updatedPatientNric = editAppointmentDescriptor.getPatientNric().orElse(appointmentToEdit.getPatientNric());
        Nric updatedDoctorNric = editAppointmentDescriptor.getDoctorNric().orElse(appointmentToEdit.getDoctorNric());
        AppointmentStartTime updatedStartTime =
                editAppointmentDescriptor.getAppointmentStartTime()
                        .orElse(appointmentToEdit.getStartTime());
        AppointmentEndTime updateEndTime =
                editAppointmentDescriptor.getAppointmentEndTime()
                        .orElse(appointmentToEdit.getEndTime());
        return new Appointment(updatedDoctorNric, updatedPatientNric, updatedStartTime, updateEndTime);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditAppointmentCommand)) {
            return false;
        }

        EditAppointmentCommand otherEditAppointmentCommand = (EditAppointmentCommand) other;
        return index.equals(otherEditAppointmentCommand.index)
                && editAppointmentDescriptor.equals(otherEditAppointmentCommand.editAppointmentDescriptor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, editAppointmentDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("editAppointmentDescriptor", editAppointmentDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the appointment with. Each non-empty field value will replace the
     * corresponding field value of the appointment.
     */
    public static class EditAppointmentDescriptor {
        private Nric patientNric;
        private Nric doctorNric;
        private AppointmentStartTime startTime;
        private AppointmentEndTime endTime;

        public EditAppointmentDescriptor() {}

        /**
         * Copy constructor.
         */
        public EditAppointmentDescriptor(EditAppointmentDescriptor toCopy) {
            setPatientNric(toCopy.patientNric);
            setDoctorNric(toCopy.doctorNric);
            setAppointmentStartTime(toCopy.startTime);
            setAppointmentEndTime(toCopy.endTime);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(patientNric, doctorNric, startTime, endTime);
        }

        public void setPatientNric(Nric patientNric) {
            this.patientNric = patientNric;
        }

        public Optional<Nric> getPatientNric() {
            return Optional.ofNullable(patientNric);
        }

        public void setDoctorNric(Nric doctorNric) {
            this.doctorNric = doctorNric;
        }

        public Optional<Nric> getDoctorNric() {
            return Optional.ofNullable(doctorNric);
        }

        public void setAppointmentStartTime(AppointmentStartTime startTime) {
            this.startTime = startTime;
        }

        public Optional<AppointmentStartTime> getAppointmentStartTime() {
            return Optional.ofNullable(startTime);
        }

        public void setAppointmentEndTime(AppointmentEndTime endTime) {
            this.endTime = endTime;
        }

        public Optional<AppointmentEndTime> getAppointmentEndTime() {
            return Optional.ofNullable(endTime);
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditAppointmentDescriptor)) {
                return false;
            }

            EditAppointmentDescriptor otherEditAppointmentDescriptor = (EditAppointmentDescriptor) other;
            return Objects.equals(patientNric, otherEditAppointmentDescriptor.patientNric)
                    && Objects.equals(doctorNric, otherEditAppointmentDescriptor.doctorNric)
                    && Objects.equals(startTime, otherEditAppointmentDescriptor.startTime)
                    && Objects.equals(endTime, otherEditAppointmentDescriptor.endTime);
        }

        @Override
        public int hashCode() {
            return Objects.hash(patientNric, doctorNric, startTime, endTime);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("patientNric", patientNric)
                    .add("doctorNric", doctorNric)
                    .add("startTime", startTime)
                    .add("endTime", endTime)
                    .toString();
        }
    }
}
