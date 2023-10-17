package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.*;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.doctor.Doctor;
import seedu.address.model.person.patient.Patient;
import seedu.address.testutil.AppointmentBuilder;
import seedu.address.testutil.DoctorBuilder;
import seedu.address.testutil.PatientBuilder;
import seedu.address.testutil.PersonUtil;


public class NewAddressBookParserTest {

    private final NewAddressBookParser parser = new NewAddressBookParser();

    @Test
    public void parseCommand_addAppointment() throws Exception {
        Appointment appointment = new AppointmentBuilder().build();
        AddAppointmentCommand command =
                (AddAppointmentCommand) parser.parseCommand(PersonUtil.getAddAppointmentCommand(appointment));
        assertEquals(new AddAppointmentCommand(appointment), command);
    }

    @Test
    public void parseCommand_addDoctor() throws Exception {
        Doctor doctor = new DoctorBuilder().build();
        AddDoctorCommand command = (AddDoctorCommand) parser.parseCommand(PersonUtil.getAddDoctorCommand(doctor));
        assertEquals(new AddDoctorCommand(doctor), command);
    }

    @Test
    public void parseCommand_addPatient() throws Exception {
        Patient patient = new PatientBuilder().build();
        AddPatientCommand command = (AddPatientCommand) parser.parseCommand(PersonUtil.getAddPatientCommand(patient));
        assertEquals(new AddPatientCommand(patient), command);
    }

    @Test
    public void parseCommand_deleteAppointment() throws Exception {
        DeleteAppointmentCommand command = (DeleteAppointmentCommand) parser.parseCommand(
                DeleteAppointmentCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteAppointmentCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
                -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
