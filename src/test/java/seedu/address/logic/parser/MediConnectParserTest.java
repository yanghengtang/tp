package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_DOCTOR;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddAppointmentCommand;
import seedu.address.logic.commands.AddDoctorCommand;
import seedu.address.logic.commands.AddPatientCommand;
import seedu.address.logic.commands.AddPrescriptionCommand;
import seedu.address.logic.commands.AppointmentRemarkCommand;
import seedu.address.logic.commands.DeleteAppointmentCommand;
import seedu.address.logic.commands.DeleteDoctorCommand;
import seedu.address.logic.commands.DeletePatientCommand;
import seedu.address.logic.commands.DoctorRemarkCommand;
import seedu.address.logic.commands.EditAppointmentCommand;
import seedu.address.logic.commands.EditAppointmentCommand.EditAppointmentDescriptor;
import seedu.address.logic.commands.EditDoctorCommand;
import seedu.address.logic.commands.EditDoctorCommand.EditDoctorDescriptor;
import seedu.address.logic.commands.EditPatientCommand;
import seedu.address.logic.commands.EditPatientCommand.EditPatientDescriptor;
import seedu.address.logic.commands.FindDoctorCommand;
import seedu.address.logic.commands.FindPatientCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListAppointmentCommand;
import seedu.address.logic.commands.ListDoctorCommand;
import seedu.address.logic.commands.ListPatientsCommand;
import seedu.address.logic.commands.PatientRemarkCommand;
import seedu.address.logic.commands.ViewAppointmentCommand;
import seedu.address.logic.commands.ViewDoctorCommand;
import seedu.address.logic.commands.ViewPatientCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.NameContainsKeywordsDoctorPredicate;
import seedu.address.model.person.NameContainsKeywordsPatientPredicate;
import seedu.address.model.person.doctor.Doctor;
import seedu.address.model.person.patient.Patient;
import seedu.address.model.remark.Remark;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.AppointmentBuilder;
import seedu.address.testutil.AppointmentUtil;
import seedu.address.testutil.DoctorBuilder;
import seedu.address.testutil.DoctorUtil;
import seedu.address.testutil.EditAppointmentDescriptorBuilder;
import seedu.address.testutil.EditDoctorDescriptorBuilder;
import seedu.address.testutil.EditPatientDescriptorBuilder;
import seedu.address.testutil.PatientBuilder;
import seedu.address.testutil.PatientUtil;

public class MediConnectParserTest {

    private final MediConnectParser parser = new MediConnectParser();

    @Test
    public void parseCommand_addAppointment() throws Exception {
        Appointment appointment = new AppointmentBuilder().build();
        AddAppointmentCommand command =
                (AddAppointmentCommand) parser.parseCommand(AppointmentUtil.getAddAppointmentCommand(appointment));
        assertEquals(new AddAppointmentCommand(appointment), command);
    }

    @Test
    public void parseCommand_addDoctor() throws Exception {
        Doctor doctor = new DoctorBuilder().build();
        AddDoctorCommand command = (AddDoctorCommand) parser.parseCommand(DoctorUtil.getAddDoctorCommand(doctor));
        assertEquals(new AddDoctorCommand(doctor), command);
    }

    @Test
    public void parseCommand_addPatient() throws Exception {
        Patient patient = new PatientBuilder().build();
        AddPatientCommand command = (AddPatientCommand) parser.parseCommand(PatientUtil.getAddPatientCommand(patient));
        assertEquals(new AddPatientCommand(patient), command);
    }

    @Test
    public void parseCommand_deleteAppointment() throws Exception {
        DeleteAppointmentCommand command = (DeleteAppointmentCommand) parser.parseCommand(
                DeleteAppointmentCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteAppointmentCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_deletePatient() throws Exception {
        DeletePatientCommand command = (DeletePatientCommand) parser.parseCommand(
                DeletePatientCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeletePatientCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_deleteDoctor() throws Exception {
        DeleteDoctorCommand command = (DeleteDoctorCommand) parser.parseCommand(
                DeleteDoctorCommand.COMMAND_WORD + " " + INDEX_FIRST_DOCTOR.getOneBased());
        assertEquals(new DeleteDoctorCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_editDoctor() throws Exception {
        Doctor doctor = new DoctorBuilder().build();
        EditDoctorDescriptor descriptor =
                new EditDoctorDescriptorBuilder(doctor).build();
        EditDoctorCommand command = (EditDoctorCommand) parser.parseCommand(EditDoctorCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + DoctorUtil.getEditDoctorDescriptorDetails(descriptor));
        assertEquals(new EditDoctorCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_editPatient() throws Exception {
        Patient patient = new PatientBuilder().build();
        EditPatientDescriptor descriptor =
                new EditPatientDescriptorBuilder(patient).build();
        EditPatientCommand command = (EditPatientCommand) parser.parseCommand("edit_p 1 "
                + PatientUtil.getEditPatientDescriptorDetails(descriptor));
        assertEquals(new EditPatientCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_editAppointment() throws Exception {
        EditAppointmentCommand command = (EditAppointmentCommand) parser.parseCommand(
                "edit_a 1 pic\\T0123456N dic\\T0234872G from\\2023-09-11 11:00 to\\2023-09-11 11:15");
        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder()
                .withPatientNric("T0123456N")
                .withDoctorNric("T0234872G")
                .withStartTime("2023-09-11 11:00")
                .withEndTime("2023-09-11 11:15")
                .build();
        assertEquals(new EditAppointmentCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_findPatient() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindPatientCommand command = (FindPatientCommand) parser.parseCommand(
                FindPatientCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindPatientCommand(new NameContainsKeywordsPatientPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_listPatients() throws Exception {
        assertTrue(parser.parseCommand(ListPatientsCommand.COMMAND_WORD) instanceof ListPatientsCommand);
        assertTrue(parser.parseCommand(ListPatientsCommand.COMMAND_WORD + " 3") instanceof ListPatientsCommand);
    }

    @Test
    public void parseCommand_listDoctor() throws Exception {
        assertTrue(parser.parseCommand(ListDoctorCommand.COMMAND_WORD) instanceof ListDoctorCommand);
        assertTrue(parser.parseCommand(ListDoctorCommand.COMMAND_WORD + " 3") instanceof ListDoctorCommand);
    }

    @Test
    public void parseCommand_findDoctor() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindDoctorCommand command = (FindDoctorCommand) parser.parseCommand(
                FindDoctorCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindDoctorCommand(new NameContainsKeywordsDoctorPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_listAppointments() throws Exception {
        assertTrue(parser.parseCommand(ListAppointmentCommand.COMMAND_WORD) instanceof ListAppointmentCommand);
        assertTrue(parser.parseCommand(ListAppointmentCommand.COMMAND_WORD
                + " pic\\ T0123456J dic\\ S2936283D") instanceof ListAppointmentCommand);

    }

    @Test
    public void parseCommand_viewAppointment() throws Exception {
        ViewAppointmentCommand command = (ViewAppointmentCommand) parser.parseCommand(
                "view_a 1");
        assertEquals(new ViewAppointmentCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_viewDoctor() throws Exception {
        ViewDoctorCommand command = (ViewDoctorCommand) parser.parseCommand(
                "view_d 1");
        assertEquals(new ViewDoctorCommand(INDEX_FIRST_DOCTOR), command);
    }

    @Test
    public void parseCommand_viewPatient() throws Exception {
        ViewPatientCommand command = (ViewPatientCommand) parser.parseCommand(
                "view_p 1");
        assertEquals(new ViewPatientCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_appointmentRemark() throws Exception {
        AppointmentRemarkCommand command = (AppointmentRemarkCommand) parser.parseCommand(
                "remark_a 1 r\\Patient to follow up in 1 month");
        assertEquals(new AppointmentRemarkCommand(INDEX_FIRST_PERSON,
                new Remark("Patient to follow up in 1 month")), command);
    }

    @Test
    public void parseCommand_doctorRemark() throws Exception {
        DoctorRemarkCommand command = (DoctorRemarkCommand) parser.parseCommand(
                "remark_d 1 r\\Doctor to be back by 30/12/2023");
        assertEquals(new DoctorRemarkCommand(INDEX_FIRST_DOCTOR,
                new Remark("Doctor to be back by 30/12/2023")), command);
    }

    public void parseCommand_patientRemark() throws Exception {
        PatientRemarkCommand command = (PatientRemarkCommand) parser.parseCommand(
                "remark_p 1 r\\Patient to follow up in 1 month");
        assertEquals(new PatientRemarkCommand(INDEX_FIRST_PERSON,
                new Remark("Patient to follow up in 1 month")), command);
    }


    @Test
    public void parseCommand_addPresription() throws Exception {
        AddPrescriptionCommand command = (AddPrescriptionCommand ) parser.parseCommand(
                "add_tag_a 1 t\\Panadol");
        assertEquals(new AddPrescriptionCommand(INDEX_FIRST_PERSON,
                new Tag("Panadol")), command);
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
