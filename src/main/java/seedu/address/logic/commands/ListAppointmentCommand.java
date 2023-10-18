package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.NewModel.PREDICATE_SHOW_ALL_APPOINTMENTS;

import seedu.address.model.NewModel;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentEqualDoctorNricPredicate;
import seedu.address.model.appointment.AppointmentEqualPatientNricPredicate;

import java.util.function.Predicate;

/**
 * Lists all persons in the address book to the user.
 */
public class ListAppointmentCommand extends NewCommand {

    public static final String COMMAND_WORD = "list_a";

    public static final String MESSAGE_SUCCESS = "Listed all appointments";
    public final Predicate<Appointment> predicatePatient;
    public final Predicate<Appointment> predicateDoctor;

    public ListAppointmentCommand(Predicate<Appointment> predicatePatient,
                                  Predicate<Appointment> predicateDoctor) {
        requireNonNull(predicatePatient);
        requireNonNull(predicateDoctor);
        this.predicatePatient = predicatePatient;
        this.predicateDoctor = predicateDoctor;
    }

    public ListAppointmentCommand() {
        this.predicatePatient = PREDICATE_SHOW_ALL_APPOINTMENTS;
        this.predicateDoctor = PREDICATE_SHOW_ALL_APPOINTMENTS;
    }

    @Override
    public CommandResult execute(NewModel model) {
        requireNonNull(model);
        model.updateFilteredAppointmentList(predicatePatient);
        model.updateFilteredAppointmentList(predicateDoctor);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
