---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

* Libraries used: [JavaFX](https://openjfx.io/), [Jackson](https://github.com/FasterXML/jackson), [JUnit5](https://github.com/junit-team/junit5).
* This project is based on [AddressBook Level 3 (AB3)](https://github.com/se-edu/addressbook-level3) by [SE-EDU initiative](https://se-education.org/)

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document `docs/diagrams` folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.
</div>

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** (consisting of classes [`Main`](https://github.com/AY2324S1-CS2103T-T08-1/tp/blob/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/AY2324S1-CS2103T-T08-1/tp/blob/master/src/main/java/seedu/address/MainApp.java)) is in charge of the app launch and shut down.
* At app launch, it initializes the other components in the correct sequence, and connects them up with each other.
* At shut down, it shuts down the other components and invokes cleanup methods where necessary.

The bulk of the app's work is done by the following four components:

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete_p 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2324S1-CS2103T-T08-1/tp/blob/master/src/main/java/seedu/address/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PatientListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `Ui` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2324S1-CS2103T-T08-1/tp/blob/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2324S1-CS2103T-T08-1/tp/blob/master/src/main/resources/view/MainWindow.fxml)

The `Ui` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `Ui` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Appointment`, `Doctor` and `Patient` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/AY2324S1-CS2103T-T08-1/tp/blob/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("delete_a 1")` API call as an example.

![Interactions Inside the Logic Component for the `delete_a 1` Command](images/DeleteAppointmentSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteAppointmentCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it is passed to an `MediConnectParser` object which in turn creates a parser that matches the command (e.g., `DeleteAppointmentCommandParser`) and uses it to parse the command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `DeleteAppointmentCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to delete a person).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `MediConnectParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddPatientCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddPatientCommand`) which the `MediConnectParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddPatientCommandParser`, `DeletePatientCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/AY2324S1-CS2103T-T08-1/tp/blob/master/src/main/java/seedu/address/model/Model.java)

<img src="images/ModelClassDiagram.png" width="450" />

A detailed breakdown of the subclasses of `Data` is shown below:

<img src="images/ModelDataClassDiagram.png" width="600" />

The `Model` component,

* stores the MediConnect data i.e., all `Appointment`, `Doctor` and `Patient` objects (which are contained in a `UniqueItemList<Appointment/Doctor/Appointment>` object respectively).
* stores the currently 'selected' `Data` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Data>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `Database`, which `Data` references. This allows `Database` to only require one `Tag` object per unique tag, instead of each `Data` needing their own `Tag` objects.<br>

<img src="images/BetterModelClassDiagram.png" width="450" />

</div>


### Storage component

**API** : [`Storage.java`](https://github.com/AY2324S1-CS2103T-T08-1/tp/blob/master/src/main/java/seedu/address/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="550" />

The `Storage` component,
* can save both MediConnect data and user preference data in JSON format, and read them back into corresponding objects.
* inherits from both `DatabaseStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.address.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Edit Appointment / Doctor / Patient feature

**Introduction**

This section describes the feature that allows users to edit appointments/doctors/patients in the MediConnect database by index.

#### Implementation

The proposed edit appointment/doctors/patient mechanism is facilitated by `LogicManager` and it extends `Logic`. It holds a `MediConnectParser` that parses the user input, and a `Model` where the command is executed. Additionally, it implements the following operations:

* `LogicManager#execute(String)` —  Executes the given user String input and returns a 'CommandResult'

Given below is an example usage scenario and how the edit patient mechanism behaves at each step.

**Step 1**: The user launches the application. The `Database` will be initialized with all data in the order that it was stored in.

**Step 2**: The user inputs `edit_p 5 p\23456789` command to edit the phone number of the 5th patient in the MediConnect database. The `edit_p` command calls `EditPatientCommandParser#parse(String)` which parses the parameters to edit the current patient with. 
A new `EditPatientDescriptor` instance will be created in the parse command call, and a new `EditPatientCommand` instance will be created with the `EditPatientDescriptor` and the given `Index`.

**Step 3**: The created `EditPatientCommand` instance is returned to `LogicManager` and its `execute` method is called.
`EditPatientCommand#execute(Model)` then calls `Model#setPatient(Patient, Patient)` and with the patient of the given `Index` and the target patient created by the `EditPatientDescriptor`.

The example usage scenario for the edit doctor and edit appointment mechanisms would be similar to the scenario above.

<div markdown="span" class="alert alert-info">:information_source: **Note:** If a command fails its execution, it will not call `Model#setPatient(Patient, Patient)`, so the database will not be updated.

</div>

**UML Diagrams**

The following sequence diagram shows how the edit patient operation would work:
![SortSequenceDiagram](images/EditPatientSequenceDiagram.png)

The sequence diagram for editing an appointment and editing a doctor would be similar.

The following activity diagram summarizes what happens when a user wants to edit an appointment/patient/doctor:

![EditXYZCommandActivityDiagram](images/EditXYZActivityDiagram.png)

### Find Doctor / Patient

**Introduction**

This section describes the feature that allows users to find doctors/patient in the MediConnect database by name.

#### Implementation
The finding of a doctor/patient in MediConnect is facilitated by `LogicManager`, which extends `Logic`. It holds a `MediConnectParser` that parses the user input, and a `Model` where the command is executed. Additionally, it implements the following operations:

* `LogicManager#execute(String)` — Executes the given user String input and returns a CommandResult

These operations are exposed in the UI interface as `MainWindow#executeCommand(String)`.

Given below is an example usage scenario and how the `FindDoctorCommand` mechanism behaves at each step.

**Step 1**: The user inputs `find_d John` to search for doctors named "John" in the database.
* The `find_d` command triggers `MediConnectParser#parseCommand(String)`, which identifies the command word and calls `FindDoctorCommandParser#parse(String)` to handle the arguments.

**Step 2**: The `FindDoctorCommandParser#parse(String)` method splits the argument "John" into a list of keywords. It then creates a `NameContainsKeywordsDoctorPredicate` object, using the list of keywords.

**Step 3**: A new `FindDoctorCommand` instance is created using the `NameContainsKeywordsDoctorPredicate` object.

**Step 4**: The created `FindDoctorCommand` instance is returned to `LogicManager`, and its `execute` method is called.
`FindDoctorCommand#execute(Model)` filters the list of doctors/patients in `Model` using the `NameContainsKeywordsDoctorPredicate`.

**Step 5**: The filtered list is displayed to the user through the UI.

The example usage scenario for the find patient mechanisms would be similar to the scenario above.

**UML Diagrams**

The following sequence diagram shows how the find doctor operation would work:

![FindDoctorSequence](images/FindDoctorSequence.png)

The sequence diagram for the find patient operation would be similar

The following activity diagram summarizes what happens when a user wants to find a new doctor/patient:

![FindCommandActivity](images/FindCommandActivityDiagram.png)

### List Doctors / Patients

**Introduction**

This section describes the feature that allows users to list doctors/patients in the MediConnect database.

#### Implementation

The listing of all doctors/patient in the database is facilitated by `LogicManager`. It extends `Logic` and stores the `MediConnectParser` that parses the user input, and the model in which the command is executed. Additionally, it implements the following operations:

* `LogicManager#execute(String)` — Executes the given user String input and returns a `CommandResult`.

These operations are exposed in the Ui interface as `Ui#executeCommand(String)`.

Given below is an example usage scenario and how the `ListDoctorCommand` mechanism behaves at each step.

Step 1: The user inputs `list_d`. The application will display the `FilteredDoctorList`.

* The `list_d` command calls `MediConnectParser#parseCommand(String)` which recognizes the command word as `list_d`.

* A new `ListDoctorCommand` instance will be created.

Step 2: The created `ListDoctorCommand` instance is returned to `LogicManager` and its execute method is called.

* `ListDoctorCommand#execute(Model)` then calls `Model#updateFilteredDoctorList(Predicate<Doctor>)` with the predicate `PREDICATE_SHOW_ALL_DOCTORS`.

* The `FilteredDoctorList` is updated to show all doctors by calling `ObservableList#setPredicate(Predicate<Doctor>)`.

Step 3: A `CommandResult` object is created with a message indicating success, and this result is returned to the UI to be displayed to the user.

The example usage scenario for the list patients mechanisms would be similar to the scenario above.

**UML Diagrams**

The following sequence diagram shows how the list doctor operation would work:
![ListDoctorSequence](images/ListDoctorSequence.png)

The sequence diagram for the list patient operation would be similar

The following activity diagram summarizes what happens when a user wants to list a new patient/doctor:
![ListCommandActivity](images/ListCommandActivityDiagram.png)

### List Appointments

**Introduction**

This section describes the feature that allows users to list appointments in the MediConnect database. Users can either list all appointments or filter them based on the NRIC of doctors or patients.

#### Implementation

The listing of appointments in MediConnect is facilitated by the `LogicManager`, which implements the `Logic` interface. It holds a `MediConnectParser` that parses the user input, and a model where the command is executed. Additionally, it implements the following operations:

* `LogicManager#execute(String)` — Executes the given user String input and returns a `CommandResult`.

These operations are exposed in the Ui interface as `MainWindow#executeCommand(String)`.

Given below is an example usage scenario and how the `ListAppointmentCommand` mechanism behaves at each step:

**Step 1**: The user inputs `list_a` to list all appointments or `list_a pic\PATIENT_NRIC dic\DOCTOR_NRIC` to filter appointments.
* The `list_a` command triggers `MediConnectParser#parseCommand(String)`, which identifies the command word and calls `ListAppointmentCommandParser#parse(String)` to handle the arguments.

**Step 2**: The `ListAppointmentCommandParser#parse(String)` method checks for the presence of optional flags like `dic\` for doctor NRIC and `pic\` for patient NRIC. Based on these, it creates appropriate `Predicate` objects.

**Step 3**: A new `ListAppointmentCommand` instance is created using the `Predicate` object(s).

**Step 4**: The created `ListAppointmentCommand` instance is returned to `LogicManager`, and its execute method is called.
* `ListAppointmentCommand#execute(Model)` filters the list of appointments in `Model` using the specified predicate(s).

**Step 5**: The filtered list is displayed to the user through the UI.

**UML Diagrams**

The following sequence diagram shows how the list appointment operation would work:
![ListPatientSequence](images/ListAppointmentSequence.png)

The following activity diagram summarizes what happens when a user wants to list a new appointment:
![ListCommandActivity](images/ListAppointmentCommandActivityDiagram.png)

### Add Appointment / Doctor / Patient feature

**Introduction**

This section describes the add appointment/doctor/patient features.

#### Implementation
The adding of an appointment/doctor/patient to MediConnect is facilitated by `LogicManager`. It extends `Logic` and stores the `MediConnectParser` that parses the user input, and the model in which the command is executed. Additionally, it implements the following operations:

* `LogicManager#execute(String)` —  Executes the given user String input and returns a `CommandResult`

These operations are exposed in the `Ui` interface as `MainWindow#executeCommand(String)`.

Given below is an example usage scenario and how the add `Appointment` mechanism behaves at each step.

**Step 1**: The user launches the application. The `Database` will be initialized with all data in the order that it was stored in.

**Step 2**: The user inputs `add_a pic\T0123456J dic\S9876543F from\2023-12-01 07:30 to\2023-12-01 08:30` to add an appointment into MediConnect.
The `add_a` command calls `AddAppointmentCommandParser#parse(String)` which parses the parameters that build the appointment to be added.
A new `AddAppointmentCommand` instance will be created with the correct `Appointment` object to be added.

**Step 3**: The created `AddAppointmentCommand` instance is returned to `LogicManager` and its `execute` method is called.
`AddAppointmentCommand#execute(Model)` then calls `Model#addAppointment(Appointment)` and with the given `Appointment`.

The example usage scenario for the add patient and add doctor mechanisms would be similar to the scenario above.

**UML Diagrams**

The following sequence diagram shows how the add appointment operation would work:
![AddAppoointmentSequenceDiagram](images/AddAppointmentSequenceDiagram.png)

The sequence diagram for the add patient and add doctor operations would be similar.

The following activity diagram summarizes what happens when a user wants to add a new appointment/patient/doctor:
![AddXYZCommandActivityDiagram](images/AddXYZActivityDiagram.png)

### Delete Appointment / Doctor / Patient feature

**Introduction**

This section describes the delete appointment/doctor/patient features.

#### Implementation
The deletion of an appointment/doctor/patient from MediConnect is facilitated by `LogicManager`. It extends `Logic` and stores the `MediConnectParser` that parses the user input, and the model in which the command is executed. Additionally, it implements the following operations:

* `LogicManager#execute(String)` —  Executes the given user String input and returns a `CommandResult`

These operations are exposed in the `Ui` interface as `MainWindow#executeCommand(String)`.

Given below is an example usage scenario and how the add `Appointment` mechanism behaves at each step.

**Step 1**: The user launches the application. The `Database` will be initialized with all data in the order that it was stored in.

**Step 2**: The user inputs `delete_a 2`  to delete an appointment into MediConnect.
The `delete_a` command calls `DeleteAppointmentCommandParser#parse(String)` which returns index of the appointment to delete.
A new `DeleteAppointmentCommand` instance will be created

**Step 3**: The created `DeleteAppointmentCommand` instance is returned to `LogicManager` and its `execute` method is called.
`DeleteAppointmentCommand#execute(Model)` then calls `Model#deleteAppointment(Appointment)` and with the appointment to be deleted.

The example usage scenario for the delete patient and delete doctor mechanisms would be similar to the scenario above.

**UML Diagrams**

The following sequence diagram shows how the delete appointment operation would work:
![DeleteAppointmentSequenceDiagram](images/DeleteAppointmentSequenceDiagram.png)

The sequence diagram for the delete patient and doctor operations would be similar.

The following activity diagram summarizes what happens when a user wants to delete an appointment/patient/doctor:
![DeleteXYZCommandActivityDiagram](images/DeleteXYZActivityDiagram.png)

### Edit remark feature

**Introduction**

This section describes the appointment/doctor/patient remark features.

#### Implementation
The adding/deleting/editing of a remark for an appointment/doctor/patient in MediConnect is facilitated by `LogicManager`. It extends `Logic` and stores the `MediConnectParser` that parses the user input, and the model in which the command is executed. Additionally, it implements the following operations:

* `LogicManager#execute(String)` —  Executes the given user String input and returns a `CommandResult`

These operations are exposed in the `Ui` interface as `MainWindow#executeCommand(String)`.

Given below is an example usage scenario and how the edit `Remark` of an `Appointment` mechanism behaves at each step.

**Step 1**: The user launches the application. The `Database` will be initialized with all data in the order that it was stored in.

**Step 2**: The user inputs `remark_a 2 r\follow up required` to edit the remark of the second appointment in the appointment list.
The `remark_a` command calls `AppointmentRemarkCommandParser#parse(String)` which parses the parameters that is used to edit the remark of the appointment specified.
A new `AppointmentRemarkCommand` instance will be created with the correct `Remark` object to be added to the appointment specified.

**Step 3**: The created `AppointmentRemarkCommand` instance is returned to `LogicManager` and its `execute` method is called.
`AppointmentRemarkCommand#execute(Model)` then calls `Model#setAppointment(Appointment, Appointment)` and with the given `Remark`.
The edited `Appointment` is then replaced in the `Database` by calling `Database#setAppointment(Appointment, Appointment)`.

The example usage scenario for the edit patient remark and edit doctor remark mechanisms would be similar to the scenario above.

The following sequence diagram shows how the edit appointment remark operation would work:
![EditRemarkSequenceDiagram](images/EditRemarkSequenceDiagram.png)

The sequence diagram for the editing patient and doctor remark would be similar.

The following activity diagram summarizes what happens when a user wants to edit the remark of an appointment/patient/doctor:
![EditXYZRemarkActivityDiagram](images/EditXYZRemarkActivityDiagram.png)

### Add Specialisation / Medical condition / Prescription feature

**Introduction**

This section describes the add specialisations/medical conditions/prescriptions features.

#### Implementation
The addition of a specialisation/medical condition/prescription to an existing doctor/patient/appointment respectively in MediConnect is facilitated by `LogicManager`. It extends `Logic` and stores the `MediConnectParser` that parses the user input, and the model in which the command is executed. Additionally, it implements the following operations:

* `LogicManager#execute(String)` —  Executes the given user String input and returns a `CommandResult`

These operations are exposed in the `Ui` interface as `MainWindow#executeCommand(String)`.

Given below is an example usage scenario and how the add specialisation mechanism behaves at each step.

**Step 1**: The user launches the application. The `Database` will be initialized with all data in the order that it was stored in.

**Step 2**: The user inputs `list_d`. MediConnect will display the `FilteredDoctorList`.

**Step 3**: The user inputs `add_tag_d 2 t\Orthopaedic` to add the prescription `Orthopaedic` to the doctor at index 2 in the displayed doctor list.
The `add_tag_d` command calls `AddSpecialisationCommandParser#parse(String)` the index argument which is the index of the doctor we are adding the tag into. It also parses the tag argument which contains the specialisation to be added.

**Step 4**: The created `AddSpecialisationCommand` instance is returned to `LogicManager` and its `execute` method is called.
`AddSpecialisationCommand#execute(Model)` then calls `Model#setDoctor(Doctor, Doctor)` and with the doctor to update and the doctor with the updated specialisation.

The example usage scenario for the add prescription and add medical condition mechanisms would be similar to the scenario above.

The following sequence diagram shows how the add specialisation operation would work:
![AddSpecialisationSequenceDiagram](images/AddSpecialisationSequenceDiagram.png)

The sequence diagram for the add prescription and medical condition operations would be similar.

The following activity diagram summarizes what happens when a user wants to add a specialisation/medical condition/prescription:
![AddXYZTagActivityDiagram](images/AddXYZTagActivityDiagram.png)

### Delete Specialisation / Medical Condition / Prescription feature

**Introduction**

This section describes the delete specialisation/medical condition/prescription features.

#### Implementation
The deletion of a specialisation/medical condition/prescription to MediConnect is facilitated by `LogicManager`. It extends `Logic` and stores the `MediConnectParser` that parses the user input, and the model in which the command is executed. Additionally, it implements the following operations:

* `LogicManager#execute(String)` —  Executes the given user String input and returns a `CommandResult`

These operations are exposed in the `Ui` interface as `MainWindow#executeCommand(String)`.

Given below is an example usage scenario and how the add `Specialisation` mechanism behaves at each step.

**Step 1**: The user launches the application. The `Database` will be initialized with all data in the order that it was stored in.

**Step 2**: The user inputs `delete_tag_d 2 t\Orthopaedic`  to delete a doctor's specialisation from MediConnect.
The `delete_tag_d` command calls `DeleteSpecialisationCommandParser#parse(String)` which returns the index of the doctor to modify.
A new `DeleteSpecialisationCommand` instance will be created.

**Step 3**: The created `DeleteSpecialisationCommand` instance is returned to `LogicManager` and its `execute` method is called.
`DeleteSpecialisationCommand#execute(Model)` then calls `Model#getFilteredDoctorList()` and retrieve the doctor with the given `Index`. 
Then, the specialisation will be removed from the doctor if exists and replace the existing doctor in Model with the command of `Model#setDoctor(Doctor, Doctor)`.

The example usage scenario for delete medical condition and delete prescriptions mechanisms would be similar to the scenario above.

The following sequence diagram shows how the delete specialisation operation would work and will be similar to deletion of medical condition and prescription:
![DeleteSpecialisationSequenceDiagram](images/DeleteSpecialisationSequenceDiagram.png)

The following activity diagram summarizes what happens when a user wants to delete a specialisation/medical condition/prescription:
![DeleteXYZTagActivityDiagram](images/DeleteXYZTagActivityDiagram.png)

### View Appointment / Doctor / Patient feature

**Introduction**

This section describes the feature that allows users to view the full details of appointments/doctors/patients in the MediConnect database by index.

#### Implementation

The View Patient mechanism is facilitated by `ModelManager`. It extends `Model` and stores the appointment, doctor
and patient to be shown as `selectedAppointment`, `selectedDoctor` and `selectedPatient` respectively. Additionally, it implements the following operations:

- `ModelManager#getSelectedAppointment()`  —  Returns the Appointment currently selected in the Model.
- `ModelManager#updateSelectedAppointment(Appointment)`  —  Sets the selected Appointment currently in the Model.
- `ModelManager#getSelectedDoctor()`  —  Returns the Doctor currently selected in the Model.
- `ModelManager#updateSelectedDoctor(Doctor)`  —  Sets the selected Doctor currently in the Model.
- `ModelManager#getSelectedPatient()`  —  Returns the Patient currently selected in the Model.
- `ModelManager#updateSelectedPatient(Patient)`  —  Sets the selected Patient currently in the Model.

The getter operations are exposed in the `Logic` interface as `Logic#getSelectedAppointment()`, `Logic#getSelectedDoctor()` and `Logic#getSelectedPatient()`.

It is also facilitated by `AppointmentWindow`, `DoctorWindow` and `PatientWindow` which extend `UiPart`. They are stored in the `MainWindow` and implements the following operations:

- `AppointmentWindow#updateAppointment(Appointment)`  —  Sets the Appointment to be shown in the window.
- `AppointmentWindow#show()`  —  Displays the Appointment Window.
- `AppointmentWindow#focus()`  —  Toggles to the Appointment Window.
- `DoctorWindow#updateDoctor(Doctor)`  —  Sets the Doctor to be shown in the window.
- `DoctorWindow#show()`  —  Displays the Doctor Window.
- `DoctorWindow#focus()`  —  Toggles to the Doctor Window.
- `PatientWindow#updatePatient(Patient)`  —  Sets the Patient to be shown in the window.
- `PatientWindow#show()`  —  Displays the Patient Window.
- `PatientWindow#focus()`  —  Toggles to the Patient Window.

Lastly, it is also facilitated by `CommandResult` which stores the boolean value `showAppointment`, `showDoctor` and `showPatient` and implement the following operations:

- `CommandResult#isShowAppointment()`  —  Indicates if the command is View Appointment
- `CommandResult#isShowDoctor()`  —  Indicates if the command is View Doctor
- `CommandResult#isShowPatient()`  —  Indicates if the command is View Patient

Given below is an example usage scenario and how the view patient mechanism behaves at each step.

**Step 1**: The user launches the application for the first time. `selectedAppointment`, `selectedDoctor` and `selectedPatient` has not been initialised and `AppointmentWindow`, `DoctorWindow` and `PatientWindow` are closed by default.

**Step 2**: The user executes `view_p 2` command to view the 2nd patient in the patient list. The `view` command calls `ModelManager#updateSelectedPatient(Patient)`, causing the `selectedPatient` to be initialised the 2nd patient in the patient list.
The `CommandResult` returned will call `PatientWindow#updatePatient(Patient)` followed by `PatientWindow#show()`, launching the `PatientWindow` with the details of the 2nd patient.

**Step 3**: The user toggle back to the main window and executes `view_p 4` command to view the 4th patient in the patient list. The `view` command calls `ModelManager#updateSelectedPatient(Patient)`, causing the `selectedPatient` to be updated with the 4th patient in the patient list.
The `CommandResult` returned will call `PatientWindow#updatePatient(Patient)` followed by `PatientWindow#focus()`, toggling to the `PatientWindow` with the details of the 4th patient.

<div markdown="span" class="alert alert-info">
    :information_source: **Note:** If the user decides to close the Patient Window before executing the command, `PatientWindow#focus()` will not be invoked, instead it will invoke `PatientWindow#show()` similar to Step 2.
</div>

The view doctor and view appointment mechanism function similarly by utilising on their respective filtered lists, methods and windows.

**UML Diagrams**

The following sequence diagram shows how the View Patient command is executed in the `Logic`:

![ViewPatientLogicSequenceDiagram](images/ViewPatientLogicSequenceDiagram.png)

The sequence diagram for view appointment and view doctor would be similar.

The following sequence diagram shows how the view patient command results is handled in the `Ui`:

![ViewPatientUiSequenceDiagram](images/ViewPatientUiSequenceDiagram.png)

The sequence diagram for view appointment and view doctor would be similar.

The following activity diagram summarizes what happens when a user executes a new view command:

![ViewActivityDiagram](images/ViewActivityDiagram.png)

#### Design considerations

**Aspect: How view appointment / doctor / patient executes:**

* **Alternative 1 (current choice):** Store the selected Patient in the model and retrieve on `CommandResult` instruction.
    * Pros: Straight forward to implement.
    * Cons: Requires extensive additions to the `Model` and `Logic` interface.

* **Alternative 2:** Store selected Patient in `CommandResult` and retrieve directly from there.
    * Pros: No changes to the `Model` and `Logic` interface required.
    * Cons: Reduces `CommandResult` cohesiveness as it will now have the responsibility of passing the selected Patient to the Ui.

--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:

* has a need to manage a significant number of patients and doctors
* has a need to manage appointments between the patients and doctors
* prefers desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**:
* provides easy storage and management of patients' and doctors' profiles
* provides convenient management of appointments of doctors and patients


### User stories

Priorities: High (Must-Have) - * * *, Medium (Good-To-Have) - * *, Low (To-Forgo) - *

| Priority  | As a …  | I want to …                                            | So that I can…                                                                      |
|-----------|---------|--------------------------------------------------------|-------------------------------------------------------------------------------------|
| * * *     | user    | add appointment into the system                        | keep track of a new appointment                                                     |
| * * *     | user    | add doctor's profile into the system                   | keep track of a new doctor working in the clinic                                    |
| * * *     | user    | add patient's data into the system                     | keep track of a new patient visiting the clinic                                     |
| * * *     | user    | delete an appointment from the system                  | remove any appointment that has been cancelled from the system                      |
| * * *     | user    | delete doctor profile from the system                  | remove any doctor no longer working for the clinic                                  |
| * * *     | user    | delete patients' data from the system                  | remove any patient no longer visiting the clinic                                    |
| * * *     | user    | view the list of appointment in the system             | see all the appointments currently in the system                                    |
| * * *     | user    | view the list of doctor in the system                  | see all the doctors currently in the system                                         |
| * * *     | user    | view the list of patient in the system                 | see all the patients currently in the system                                        |
| * * *     | user    | edit an appointment in the system                      | correct any mistake made when adding or updating the appointment previously         |
| * * *     | user    | edit a doctor's details in the system                  | correct any mistake made when adding or updating the doctor previously              |
| * * *     | user    | edit a patient’s data in the system                    | correct any mistake made when adding or updating the patient previously             |
| * *       | user    | view the full details of an appointment                | retrieve the medicine prescribed or information discovered during that appointment. |
| * *       | user    | view the full details of an doctor                     | retrieve the doctor's availability and specialisation.                              |
| * *       | user    | view the full details of an patient                    | retrieve the specified patient's full medical history and any ongoing treatment     |
| * *       | user    | add remarks for an appointment                         | note down any significant finding during the appointment                            |
| * *       | user    | add remarks for a doctor                               | note down the availability of the specified doctor                                  |
| * *       | user    | add remarks for a patient                              | note down any ongoing treatment of the specified patient                            |
| * *       | user    | add prescription for an appointment                    | keep track of the medicine prescribed during the specified appointment.             |
| * *       | user    | remove prescription for an appointment                 | update the medicine prescribed during the specified appointment.                    |
| * *       | user    | add specialisation for a doctor                        | keep track of the specified  doctor's specialisation.                               |
| * *       | user    | remove specialisation for a doctor                     | update the specified doctor's specialisation.                                       |
| * *       | user    | add medical condition for a patient                    | keep track of the medical condition the specified patient has.                      |
| * *       | user    | remove medical condition for a patient                 | update the specified patient's medical condition.                                   |
| *         | user    | view the list of appointment sorted by start date time | look through the list of appointment in a chronological manner.                     |
| *         | user    | view the list of doctor sorted by name                 | look through the list of doctors logically.                                         |
| *         | user    | view the list of patient sorted by name                | look through the list of patient in a logical manner.                               |


### Use cases

(For all use cases below, the **System** is the `MediConnect` and the **Actor** is the `user`, unless specified otherwise)

**Use case: UC01 List all patients**

**MSS**

1.  User requests to list all the patients.
2.  MediConnect lists all the patients.
3.  MediConnect shows a success message to the user.

    Use case ends.

**Extensions**

* 1a. MediConnect detects that the command is invalid.
    * 1a1. MediConnect shows an error message.
    * 1a2. MediConnect requests for the correct input.
      
      Use case resumes at step 1.

**Use case: UC02 View a patient's full record**

**MSS**

1.  User requests to view a patient's record.
2.  MediConnect shows the patient’s record.
3.  MediConnect shows a success message to the user.

    Use case ends.

**Extensions**

* 1a. MediConnect detects that the command is invalid.
    * 1a1. MediConnect shows an error message.
    * 1a2. MediConnect requests for the correct input.

      Use case resumes at step 1.
* 1b. The patient does not exist in the system.
    * 1b1. MediConnect shows an error message.
    
      Use case ends.

**Use case: UC03 Add a patient**

**MSS**

1.  User requests to add a specific patient to the system.
2.  MediConnect adds the specific patient to the system.
3.  MediConnect shows a success message to the user.

    Use case ends.

**Extensions**

* 1a.MediConnect detects that the arguments provided to add a patient is invalid.
    * 1a1. MediConnect shows an error message.    
    * 1a2. MediConnect requests for the correct input.

      Use case resumes at step 1.

* 1b. MediConnect detects that patient to be added already exists in the system.
    * 1b1. MediConnect shows an error message.

      Use case ends.


**Use case: UC04 Edit a patient’s particulars**

**MSS**

1.  User edits a specific patient’s particulars.
2.  MediConnect edits the patient’s particulars.
3.  MediConnect shows a success message to the user.

    Use case ends.

**Extensions**

* 1a.MediConnect detects that the arguments provided to edit a patient is invalid.
    * 1a1. MediConnect shows an error message.
    * 1a2. MediConnect requests for the correct input.
   
      Use case resumes at step 1.
  
**Use case: UC05 Delete a patient**
  
**MSS**

1.  User requests to delete a specific patient in the system.
2.  MediConnect deletes the patient.
3.  MediConnect shows a success message to the user.

    Use case ends.

**Extensions**

* 1a. MediConnect detects that the given index is invalid.
    * 1a1. MediConnect shows an error message.
    * 1a2. MediConnect requests for the correct input.

      Use case resumes at step 1.

**Use case: UC06 Edit a specified patient’s remark**

**MSS**

1.  User requests to edit a patient’s remarks.
2.  MediConnect edits the patient’s remarks.
3.  MediConnect shows a success message to the User.

Use case ends.

**Extensions**

* 1a. MediConnect detects that the given command is invalid.
    * 1a1. MediConnect shows an error message.
    * 1a2. MediConnect requests the correct input.

      Use case resumes at step 1.
* 1b. MediConnect detects that the given patient does not exist in the system.
    * 1b1. MediConnect shows an error message.
  
      Use case ends.


**Use case: UC07 Add a medical condition to a patient**

**MSS**

1.  User requests to add a medical condition to a patient.
2.  MediConnect adds a medical condition to a patient.
3.  MediConnect shows a success message to the user.

Use case ends.

Extensions

* 1a. MediConnect detects that the given command is invalid.
    * 1a1. MediConnect shows an error message.
    * 1a2. MediConnect requests the correct input. 
  
      Use case resumes at step 1.
* 1b. MediConnect detects that the given patient does not exist in the system.
    * 1b1. MediConnect shows an error message.
  
      Use case ends.
* 1c. MediConnect detects that the patient already has the given medical condition.
    * 1c1. MediConnect shows an error message.
  
      Use case ends.

**Use case: UC08 Delete medical condition from a patient**

**MSS**

1.  User requests to delete a medical condition from a patient.
2.  MediConnect deletes the medical condition from the patient.
3.  MediConnect shows a success message to the User.

Use case ends.

**Extensions**

* 1a. MediConnect detects that the given command is invalid.
    * 1a1. MediConnect shows an error message.
    * 1a2. MediConnect requests the correct input.
      
     Use case resumes at step 1.
* 1b. MediConnect detects that the given patient does not exist in the system.
    * 1b1. MediConnect shows an error message.
  
      Use case ends.
* 1c. MediConnect detects that the given patient does not have the specified medical condition.
    * 1c1. MediConnect shows an error message.
  
      Use case ends.

**Use case: UC09 List all doctors**

**MSS**

1.  User requests to list all doctors.
2.  MediConnect shows the list of all doctors.
3.  MediConnect shows a success message to the user.

    Use case ends.

**Extensions**

* 1a. MediConnect detects that the command is invalid.
    * 1a1. MediConnect shows an error message.
    * 1a2. MediConnect requests for the correct input. 
    
       Use case resumes at step 1.

**Use case: UC10 View a doctor’s full record**

**MSS**

1.  User requests to view a doctor's full record.
2.  MediConnect shows the doctor’s full record.
3.  MediConnect shows a success message to the user.

    Use case ends.

**Extensions**

* 1a. MediConnect detects that the command is invalid.
    * 1a1. MediConnect shows an error message. 
    * 1a2. MediConnect requests for the correct input.

      Use case resumes at step 1.
* 1b. MediConnect detects that the doctor does not exist in the system.
    * 1b1. MediConnect shows an error message. 
  
      Use case resumes at step 1.

**Use case: UC11 Add a doctor**

**MSS**

1. User requests to add a specific doctor to the system.
2. MediConnect adds the specific doctor to the system. 
3. MediConnect shows a success message to the user.

    Use case ends.
  
**Extensions**
  
* 1a. MediConnect detects that the arguments provided to add a doctor is invalid.
    * 1a1. MediConnect shows an error message.
    * 1a2. MediConnect requests for the correct input.
  
      Use case resumes at step 1.
* 1b. MediConnect detects that doctor to be added already exists in the system.
   * 1b1. MediConnect shows an error message.
  
     Use case ends.

**Use case: UC12 Edit a doctor’s particulars**

**MSS**

1.  User requests to edit a specific doctor’s particulars.
2.  MediConnect edits the doctor’s particulars.
3.  MediConnect shows a success message to the user.

    Use case ends.

**Extensions**

* 1a.MediConnect detects that the arguments provided to edit a doctor is invalid.
    * 1a1. MediConnect shows an error message.
    * 1a2. MediConnect requests for the correct input.
  
      Use case resumes at step 1.
* 1b. MediConnect detects that doctor to be edited does not exist in the system.
    * 1b1. MediConnect shows an error message.
  
      Use case ends.

**Use case: UC13  Edit a specified doctor’s remark**

**MSS**

1.  User requests to edit a doctor’s remarks.
2.  MediConnect edits the doctor’s remarks.
3.  MediConnect shows a success message to the user.

    Use case ends.

**Extensions**

* 1a.MediConnect detects that the given command is invalid.
    * 1a1. MediConnect shows an error message.
    * 1a2. MediConnect requests the correct input.

      Use case resumes at step 1.
* 1b. MediConnect detects that the given doctor does not exist in the system.
  * 1b1. MediConnect shows an error message. 
  
      Use case ends.

**Use case: UC14 Add specialisation to a doctor**

**MSS**

1. User requests to add a specialisation to a doctor.
2. MediConnect adds a specialisation to a doctor.
3. MediConnect shows a success message to the user.

   Use case ends.

**Extensions**

* 1a. MediConnect detects that the given command is invalid.
    * 1a1. MediConnect shows an error message.
    * 1a2. MediConnect requests the correct input。

      Use case resumes at step 1.

* 1b. MediConnect detects that the given doctor does not exist in the system.
  * 1b1. MediConnect shows an error message.

    Use case ends.

* 1c. MediConnect detects that the doctor already has the  given specialisation.
  * 1c1. MediConnect shows an error message.

    Use case ends.

**Use case: UC15 Delete specialisation from a doctor**

**MSS**

1. User requests to delete a specialisation from a doctor.
2. MediConnect deletes the specialisation from the doctor.
3. MediConnect shows a success message to the User.

    Use case ends.

**Extensions**

* 1a. MediConnect detects that the given command is invalid.
  * 1a1. MediConnect shows an error message.
  * 1a2. MediConnect requests the correct input.

    Use case resumes at step 1.

* 1b. MediConnect detects that the given doctor does not exist in the system.
  * 1b1. MediConnect shows an error message.

    Use case ends.

* 1c. MediConnect detects that the given doctor does not have the specified specialisation.
  * 1c1. MediConnect shows an error message.
  
    Use case ends.

**Use case: UC16 Delete a doctor**

**MSS**

1. User requests to delete a specific doctor in the list.
2. MediConnect deletes the doctor.
3. MediConnect shows a success message to the User.

    Use case ends.

**Extensions**

* 1a. MediConnect detects that the given arguments to delete a doctor is invalid.
  * 1a1. MediConnect shows an error message. 
  * 1a2. MediConnect requests for the correct input.

    Use case resumes at step 1.

**Use case: UC17 View a patient’s appointments**

**MSS**

1. User requests to view a patient’s appointments.
2. MediConnect shows the patient’s appointment list.
3. MediConnect shows a success message to the User.

    Use case ends.

**Extensions**

* 1a. MediConnect detects that the command is invalid. 
  * 1a1. MediConnect shows an error message. 
  * 1a2. MediConnect requests the correct input.

    Use case resumes at step 1.

* 1b. MediConnect detects that the patient does not exist in the system. 
  * 1b1. MediConnect shows an error message.

    Use case ends.

**Use case: UC18 View a doctor’s appointments**

**MSS**

1. User requests to view a doctor’s list of  appointments. 
2. MediConnect shows the doctor’s appointment list.
3. MediConnect shows a success message to the User.

    Use case ends.

**Extensions**

* 1a. MediConnect detects that the command is invalid. 
  * 1a1. MediConnect shows an error message. 
  * 1a2. MediConnect requests the correct input.

    Use case resumes at step 1.

* 1b. MediConnect detects that the doctor does not exist in the system. 
  * 1b1. MediConnect shows an error message.

    Use case ends.

**Use case: UC19 Add a new appointment**

**MSS**

1. User requests to add a specific appointment between a patient and doctor. 
2. MediConnect adds the appointment to the appointment list.
3. MediConnect shows a success message to the User.

    Use case ends.

**Extensions**

* 1a.MediConnect detects that the arguments provided to add an appointment is invalid. 
  * 1a1. MediConnect shows an error message. 
  * 1a2. MediConnect requests for the correct input.

    Use case resumes at step 1.

* 1b. MediConnect detects that the appointment to be added already exists in the system.
  * 1b1. MediConnect shows an error message.

    Use case ends.

**Use case: UC20 Edit an appointment’s particulars**

**MSS**

1. User requests to edit an appointment’s particulars. 
2. MediConnect edits the appointment particulars.
3. MediConnect shows a success message to the User.

    Use case ends.

**Extensions**

* 1a.MediConnect detects that the arguments provided to edit an appointment are invalid. 
  * 1a1. MediConnect shows an error message. 
  * 1a2. MediConnect requests for the correct input.

    Use case resumes at step 1.

* 1b. MediConnect detects that the appointment to be edited does not exist in the system. 
  * 1b1. MediConnect shows an error message.

    Use case ends.

**Use case: UC21 Delete an appointment**

**MSS**

1. User requests to delete an appointment from the appointment list.
2. MediConnect deletes the appointment.
3. MediConnect shows a success message to the User.

    Use case ends.

**Extensions**

* 1a. MediConnect detects that the given index is invalid. 
  * 1a1. MediConnect shows an error message. 
  * 1a2. MediConnect requests the correct input.

    Use case resumes at step 1.

**Use case: UC22 Edit a specified appointment remark**

**MSS**

1. User requests to edit an appointment’s remarks. 
2. MediConnect edits the appointment’s remarks. 
3. MediConnect shows a success message to the User.

    Use case ends.

**Extensions**

* 1a. MediConnect detects that the given command is invalid. 
  * 1a1. MediConnect shows an error message.
  * 1a2. MediConnect requests the correct input.

    Use case resumes at step 1.

* 1b. MediConnect detects that the given appointment does not exist in the system. 
  * 1b1. MediConnect shows an error message.

    Use case ends.

**Use case: UC23 Add prescription**

**MSS**

1. User requests to add a prescription to an appointment. 
2. MediConnect adds a prescription to an appointment. 
3. MediConnect shows a success message to the User.

    Use case ends.

**Extensions**

* 1a. MediConnect detects that the given command is invalid. 
  * 1a1. MediConnect shows an error message. 
  * 1a2. MediConnect requests the correct input.

    Use case resumes at step 1.

* 1b. MediConnect detects that the given appointment does not exist in the system. 
  * 1b1. MediConnect shows an error message.

    Use case ends.

* 1c. MediConnect detects that the given prescription already exists in the appointment. 
  * 1c1. MediConnect shows an error message.

    Use case ends.

**Use case: UC24 Delete prescription**

**MSS**

1. User requests to delete a prescription from an appointment. 
2. MediConnect deletes a prescription from an appointment. 
3. MediConnect shows a success message to the User.

    Use case ends.

**Extensions**

* 1a. MediConnect detects that the given command is invalid. 
  * 1a1. MediConnect shows an error message. 
  * 1a2. MediConnect requests the correct input.

    Use case resumes at step 1.

* 1b. MediConnect detects that the given appointment does not exist in the system. 
  * 1b1. MediConnect shows an error message.

    Use case ends.

* 1c. MediConnect detects that the given prescription does not exist in the appointment. 
  * 1c1. MediConnect shows an error message.

    Use case ends.



### Non-Functional Requirements

1. Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2. Should be able to handle at least 3,000 patients at any point of time.
3. Should be able to add, view, edit and delete patients and doctors without a noticeable lag.
4. Should be able to retrieve all appointments for a particular doctor or patient within 1 second.
6. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.

### Glossary

* **Appointment**: A arranged meeting between a patient and a doctor at a particular date and time
* **Doctor**: A person providing medical services at a clinic
* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **NRIC**: Identity card number of the National Registration Identity Card, used as the primary means of identification for patients and doctors in MediConnect
* **Patient**: A person receiving medical services at a clinic
* **Receptionist**: A person handling administrative work at a clinic, and is the target user of MediConnect

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   2. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

2. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   2. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

### Adding a patient

1. Adding a patient

    1. Prerequisites: There exist no patient with NRIC `T1258979J` in the database.

    2. Test case (Valid parameters): `add_p n\Bunny Cai Hong ic\T1258979J p\83425673`<br>
       Expected: Patient successfully added into patient list. Details of the added patient shown in the status message. The list of patients now consists of the added patient.

    3. Test case (Missing parameter): `add_p n\Bunny Cai Hong ic\T1258979J`, `add_p ic\T1258979J p\83425673`, `add_p n\Bunny Cai Hong p\83425673` or any command with missing parameters<br>
       Expected: No patient is added. Error details shown in the status message.

    4. Test case (Invalid Name): `add_p n\ ic\T1258979J p\83425673`<br>
       Expected: Similar to previous.

    5. Test case (Invalid NRIC): `add_p n\Bunny Cai Hong ic\S1979Y p\83425673`<br>
       Expected: Similar to previous.

    6. Test case (Invalid Phone): `add_p n\Bunny Cai Hong ic\T1258979J p\83`<br>
       Expected: Similar to previous.

    7. Test case (Repeated Parameter): `add_p n\Bunny Cai Hong ic\T1258979J p\83425673 ic\T1258979H` or any command with repeated parameter<br>
       Expected: Similar to previous.

### Listing all patients

1. Listing all patients

    1. Prerequisites: Multiple patients in the patient list.

    2. Test case: `list_p`<br>
       Expected: List of patient updates to show all patients. Success message shown in the status message.

    3. Test case: `list_p 512807`, `list_p xxfajkl`, `list_p n\Bunny Cai Hong` or any command with extra characters supplied<br>
       Expected: Similar to previous.

### Editing a patient

1. Editing a patient's name while all patients are being shown

    1. Prerequisites: List all patients using the `list_p` command. Multiple patients in the patient list.

    2. Test case (Valid Name): `edit_p 1 n\Bunny Cai Hong`<br>
       Expected: First patient's name successfully edited. Details of the edited patient shown in the status message.

    3. Test case (Missing index): `edit_p n\Bunny Cai Hong`<br>
       Expected: Patient's name is not edited. Error details shown in the status message.

    4. Test case (Missing Parameter): `edit_p 1`<br>
       Expected: Similar to previous.

    5. Test case (Invalid Index): `edit_p x n\Bunny Cai Hong` (where x is less than one or larger than the list size)<br>
       Expected: Similar to previous.

    6. Test case (Repeated Parameter): `edit_p 1 n\Bunny Cai Hong n\Cai Hong`<br>
       Expected: Similar to previous.

2. Editing a patient's NRIC while all patients are being shown

    1. Prerequisites: List all patients using the `list_p` command. Multiple patients in the patient list. There exist no patient with NRIC `T1258979J` in the database.

    2. Test case (Valid NRIC): `edit_p 1 ic\T1258979J`<br>
       Expected: First patient's NRIC successfully edited. Details of the edited patient shown in the status message. Appointments with patient's old NRIC will be updated to the new patient's NRIC.

    3. Test case (Missing index): `edit_p ic\T1258979J`<br>
       Expected: Patient's NRIC is not edited. Error details shown in the status message.

    4. Test case (Missing Parameter): `edit_p 1`<br>
       Expected: Similar to previous.

    5. Test case (Invalid Index): `edit_p x ic\T1258979J` (where x is less than one or larger than the list size)<br>
       Expected: Similar to previous.

   6. Test case (Repeated Parameter): `edit_p 1 ic\T1258979J ic\T1258979H`<br>
      Expected: Similar to previous.

3. Editing a patient phone while all patients are being shown

    1. Prerequisites: List all patients using the `list_p` command. Multiple patients in the patient list.

    2. Test case (Valid Phone): `edit_p 1 p\83425673`<br>
       Expected: First patient's phone successfully edited. Details of the edited patient shown in the status message.

    3. Test case (Missing index): `edit_p p\83425673`<br>
       Expected: Patient's phone is not edited. Error details shown in the status message.

    4. Test case (Missing Parameter): `edit_p 1`<br>
       Expected: Similar to previous.

    5. Test case (Invalid Index): `edit_p x p\83425673` (where x is less than one or larger than the list size)<br>
       Expected: Similar to previous.

   6. Test case (Repeated Parameter): `edit_p 1 p\83425673 p\109257290`<br>
      Expected: Similar to previous.

### Finding a patient

1. Finding a patient with a given name `Jonathan`

    1. Prerequisites: List all patients using the `list_p` command. Multiple patients in the patient list. There exist a patient named `Jonathan`.

    2. Test case: `find_p jonathan`<br>
       Expected: Patient list updates to show all patients named jonathan. Success message shown in the status message.

    3. Test case (Missing keyword): `find_p`<br>
       Expected: Patient list will not update. Error details shown in the status message.

### Deleting a patient

1. Deleting a patient while all patients are being shown

   1. Prerequisites: List all patients using the `list_p` command. Multiple patients in the list.

   2. Test case: `delete_p 1`<br>
      Expected: First patient is deleted from the list. Details of the deleted patient shown in the status message.

   3. Test case (Missing Index): `delete_p`<br>
      Expected: No patient is deleted. Error details shown in the status message.

   4. Test case (Invalid Index): `delete_p x`(where x is smaller than one or larger than the list size)<br>
      Expected: Similar to previous.

### Viewing a patient

1. Viewing a patient while all patients are being shown

    1. Prerequisites: List all patients using the `list_p` command. Multiple patients in the list.

    2. Test case: `view_p 1`<br>
       Expected: Patient window pop up with details of the first patient from the list. Details of the selected patient shown in the status message.

    3. Test case (Invalid Index): `view_p x` (where x is smaller than one or larger than the list size)<br>
       Expected: Patient window does not pop up. Error details shown in the status message.

### Adding a medical condition

1. Adding a medical condition to a patient while all patients are being shown

    1. Prerequisites: List all patients using the `list_p` command. Multiple patients in the patient list. Ensure the first patient does not have the medical condition "Depression".

    2. Test case: `add_tag_p 1 t\Depression`<br>
       Expected: Medical condition successfully added to first patient. Details of the medical condition shown in the status message.

    3. Test case (Missing index): `add_tag_p t\Depression`<br>
       Expected: Medical condition is not added. Error details shown in the status message.

    4. Test case (Missing parameter): `add_tag_p 1`<br>
       Expected: Similar to previous.

    5. Test case (Multiple parameters): `add_tag_p 1 t\Depression t\Depression`<br>
       Expected: Similar to previous.

### Deleting a medical condition

1. Deleting a medical condition from a patient while all patients are being shown

    1. Prerequisites: List all patients using the `list_p` command. Multiple patients in the patient list. Ensure the first patient has the medical condition "Depression".

    2. Test case: `delete_tag_p 1 t\Depression`<br>
       Expected: Medical condition successfully deleted from the first patient. Details of the medical condition shown in the status message.

    3. Test case (Missing index): `delete_tag_p t\Depression`<br>
       Expected: Medical condition is not deleted. Error details shown in the status message.

    4. Test case (Missing parameter): `delete_tag_p 1`<br>
       Expected: Similar to previous.

    5. Test case (Multiple parameters): `delete_tag_p 1 t\Depression t\Depression`<br>
       Expected: Similar to previous.

### Editing a patient's remark

1. Editing a patient's remark while all patients are being shown

    1. Prerequisites: List all patients using the `list_p` command. Multiple patients in the patient list.

    2. Test case: `remark_p 1 r\Patient undergoing speech therapy`<br>
       Expected: Remark successfully added to the first patient. Success message shown in the status message.

    3. Test case: `remark_p 1 r\` or `remark_p 1`<br>
       Expected: Remark successfully removed from the first patient. Success message shown in the status message.

    4. Test case (Missing index): `remark_p r\Patient undergoing speech therapy`<br>
       Expected: Remark is not deleted. Error details shown in the status message.

### Adding a doctor

1. Adding a doctor

    1. Prerequisites: There exist no doctor with NRIC `T1258979J` in the database.

    2. Test case (Valid parameters): `add_d n\Bunny Cai Hong ic\T1258979J`<br>
       Expected: Doctor successfully added into doctor list. Details of the added patient shown in the status message. The list of doctors now consists of the added doctor.

    3. Test case (Missing parameter): `add_d n\Bunny Cai Hong`, `add_d ic\T1258979J` or any command with missing parameters<br>
       Expected: No patient is added. Error details shown in the status message.

    4. Test case (Invalid Name): `add_d n\ ic\T1258979J`<br>
       Expected: Similar to previous.

    5. Test case (Invalid NRIC): `add_d n\Bunny Cai Hong ic\S1979Y`<br>
       Expected: Similar to previous.

    6. Test case (Repeated Parameter): `add_d n\Bunny Cai Hong ic\T1258979J ic\T1258979H` or any command with repeated parameter<br>
       Expected: Similar to previous.

### Listing all doctors

1. Listing all doctors

    1. Prerequisites: Multiple doctors in the doctor list.

    2. Test case: `list_d`<br>
       Expected: List of doctor updates to show all doctors. Success message shown in the status message.

    3. Test case: `list_d 512807`, `list_d xxfajkl`, `list_d n\Bunny Cai Hong` or any command with extra characters supplied<br>
       Expected: Similar to previous.


### Editing a doctor

1. Editing a doctor's name while all doctors are being shown

    1. Prerequisites: List all doctors using the `list_d` command. Multiple doctors in the doctor list.

    2. Test case (Valid Name): `edit_d 1 n\Bunny Cai Hong`<br>
       Expected: First doctor's name successfully edited. Details of the edited doctor shown in the status message.

    3. Test case (Missing index): `edit_d n\Bunny Cai Hong`<br>
       Expected: Doctor's name is not edited. Error details shown in the status message.

    4. Test case (Missing Parameter): `edit_d 1`<br>
       Expected: Similar to previous.

    5. Test case (Invalid Index): `edit_d x n\Bunny Cai Hong` (where x is less than one or larger than the list size)<br>
       Expected: Similar to previous.

    6. Test case (Repeated Parameter): `edit_d 1 n\Bunny Cai Hong n\Cai Hong`<br>
       Expected: Similar to previous.

2. Editing a doctor's NRIC while all doctors are being shown

    1. Prerequisites: List all doctors using the `list_d` command. Multiple doctors in the doctor list. There exist no doctor with NRIC `T1258979J` in the database.

    2. Test case (Valid NRIC): `edit_d 1 ic\T1258979J`<br>
       Expected: First doctor's NRIC successfully edited. Details of the edited doctor shown in the status message. Appointments with doctor's old NRIC will be updated to the new doctor's NRIC.

    3. Test case (Missing index): `edit_d ic\T1258979J`<br>
       Expected: Doctor's NRIC is not edited. Error details shown in the status message.

    4. Test case (Missing Parameter): `edit_d 1`<br>
       Expected: Similar to previous.

    5. Test case (Invalid Index): `edit_d x ic\T1258979J` (where x is less than one or larger than the list size)<br>
       Expected: Similar to previous.

    6. Test case (Repeated Parameter): `edit_d 1 ic\T1258979J ic\T1258979H`<br>
       Expected: Similar to previous.

### Finding a doctor

1. Finding a doctor with a given name `Jonathan`

    1. Prerequisites: List all doctors using the `list_d` command. Multiple doctors in the doctor list. There exist a doctor named `Jonathan`.

    2. Test case: `find_d jonathan`<br>
       Expected: Doctor list updates to show all doctors named jonathan. Success message shown in the status message.

    3. Test case (Missing keyword): `find_d`<br>
       Expected: Doctor list will not update. Error details shown in the status message.

### Deleting a doctor

1. Deleting a doctor while all doctors are being shown

    1. Prerequisites: List all doctors using the `list_d` command. Multiple doctors in the list.

    2. Test case: `delete_d 1`<br>
       Expected: First doctor is deleted from the list. Details of the deleted doctor shown in the status message.

    3. Test case (Missing Index): `delete_d`<br>
       Expected: No doctor is deleted. Error details shown in the status message.

    4. Test case (Invalid Index): `delete_d x`(where x is smaller than one or larger than the list size)<br>
       Expected: Similar to previous.

### Viewing a doctor

1. Viewing a doctor while all doctors are being shown

    1. Prerequisites: List all doctors using the `list_d` command. Multiple doctors in the list.

    2. Test case: `view_d 1`<br>
       Expected: Doctor window pop up with details of the first doctor from the list. Details of the selected doctor shown in the status message.

    3. Test case (Invalid Index): `view_d x` (where x is smaller than one or larger than the list size)<br>
       Expected: Doctor window does not pop up. Error details shown in the status message.

### Adding a specialisation

1. Adding a specialisation to a doctor while all doctors are being shown

    1. Prerequisites: List all doctors using the `list_d` command. Multiple doctors in the doctor list. Ensure the first doctor does not have the specialisation "Orthopaedic".

    2. Test case: `add_tag_d 1 t\Orthopaedic`<br>
       Expected: Specialisation successfully added to first doctor. Details of the specialisation shown in the status message.

    3. Test case (Missing index): `add_tag_d t\Orthopaedic`<br>
       Expected: Specialisation is not added. Error details shown in the status message.

    4. Test case (Missing parameter): `add_tag_d 1`<br>
       Expected: Similar to previous.

    5. Test case (Multiple parameters): `add_tag_d 1 t\Orthopaedic t\Orthopaedic`<br>
       Expected: Similar to previous.

### Deleting a specialisation

1. Deleting a specialisation from a doctor while all doctors are being shown

    1. Prerequisites: List all doctors using the `list_d` command. Multiple doctors in the doctor list. Ensure the first doctor has the specialisation "Orthopaedic".

    2. Test case: `delete_tag_d 1 t\Orthopaedic`<br>
       Expected: Specialisation successfully deleted from the first doctor. Details of the specialisation shown in the status message.

    3. Test case (Missing index): `delete_tag_d t\Orthopaedic`<br>
       Expected: Specialisation is not deleted. Error details shown in the status message.

    4. Test case (Missing parameter): `delete_tag_d 1`<br>
       Expected: Similar to previous.

    5. Test case (Multiple parameters): `delete_tag_d 1 t\Orthopaedic t\Orthopaedic`<br>
       Expected: Similar to previous.

### Editing a doctor's remark

1. Editing a doctor's remark while all doctors are being shown

    1. Prerequisites: List all doctors using the `list_d` command. Multiple doctors in the doctor list.

    2. Test case: `remark_d 1 r\Doctor away from 2023-11-01 to 2023-11-15`<br>
       Expected: Remark successfully added to the first doctor. Success message shown in the status message.

    3. Test case: `remark_d 1 r\` or `remark_d 1`<br>
       Expected: Remark successfully removed from the first doctor. Success message shown in the status message.

    4. Test case (Missing index): `remark_d r\Doctor away from 2023-11-01 to 2023-11-15`<br>
       Expected: Remark is not deleted. Error details shown in the status message.

### Adding an appointment

1. Adding an appointment

    1. Prerequisites: There exist no appointment with patient's NRIC `S8712461K`, doctor's NRIC `T1258979J` from `2023-09-11 07:30` to `2023-09-11 08:00` in the database. There exists a doctor with NRIC `T1258979J` in the doctor list and a patient with NRIC `S8712461K`.

    2. Test case (Valid parameters): `add_a dic\T1258979J pic\S8712461K from\2023-09-11 07:30 to\2023-09-11 08:00`<br>
       Expected: Appointment successfully added into appointment list. Details of the added appointment shown in the status message. The list of appointments now consists of the added appointment.

    3. Test case (Missing parameter): `add_a dic\T1258979J pic\S8712461K`, `add_a dic\T1258979J from\2023-09-11 07:30 to\2023-09-11 08:00` or any command with missing parameters<br>
       Expected: No appointment is added. Error details shown in the status message.

    4. Test case (Invalid patient NRIC): `add_a dic\T1258979J pic\S871461K from\2023-09-11 07:30 to\2023-09-11 08:00`<br>
       Expected: Similar to previous.

    5. Test case (Invalid doctor NRIC): `add_a dic\T125899J pic\S8712461K from\2023-09-11 07:30 to\2023-09-11 08:00`<br>
       Expected: Similar to previous.

    6. Test case (Invalid start time): `add_a dic\T1258979J pic\S8712461K from\2023-13-09 07:30 to\2023-09-11 08:00`<br>
       Expected: Similar to previous.

    7. Test case (Invalid end time): `add_a dic\T1258979J pic\S8712461K from\2023-09-11 07:30 to\2023-13-11 08:00`<br>
      Expected: Similar to previous.

    8. Test case (Repeated Parameter): `add_a dic\T1258979J dic\T1258979J pic\S8712461K from\2023-09-11 07:30 to\2023-09-11 08:00` or any command with repeated parameter<br>
       Expected: Similar to previous.

### Listing appointments

1. Listing all appointments

    1. Prerequisites: Multiple appointments in the appointment list.

    2. Test case: `list_a`<br>
       Expected: List of appointments updates to show all appointment. Success message shown in the status message.

    3. Test case (Additional characters): `list_a 512807`, `list_a xxfajkl`, `list_a n\Bunny Cai Hong` or any command with extra characters supplied<br>
       Expected: List of appointments will not update. Error details shown in the status message.

2. Listing all appointments by patient NRIC

    1. Prerequisites: Multiple patients in the patient list. There exist a patient with NRIC `T1258979J` in the database.

    2. Test case: `list_a pic\T1258979J`<br>
       Expected: List of appointment with given patient NRIC shown. Success message shown in the status message.

    3. Test case (Invalid NRIC): `list_a pic\2134`<br>
       Expected: List of appointments will not update. Error details shown in the status message.

    4. Test case (Repeated Parameter): `list_a pic\T1258979J pic\T1258979H`<br>
       Expected: Similar to previous.

### Editing an appointment

1. Editing the doctor's NRIC of an appointment while all appointments are being shown

    1. Prerequisites: List all appointments using the `list_a` command. Multiple appointments in the appointment list. There exists a doctor with NRIC `T0123456N` in the database.

    2. Test case (Valid doctor's NRIC): `edit_a 1 dic\T0123456N`<br>
       Expected: Doctor's NRIC of the first appointment successfully edited. Details of the edited appointment shown in the status message.

    3. Test case (Missing index): `edit_a dic\T0123456N`<br>
       Expected: Doctor's NRIC of the first appointment is not edited. Error details shown in the status message.

    4. Test case (Missing Parameter): `edit_a 1`<br>
       Expected: Similar to previous.

    5. Test case (Invalid Index): `edit_a x dic\T0123456N` (where x is less than one or larger than the list size)<br>
       Expected: Similar to previous.

    6. Test case (Repeated Parameter): `edit_a 1 dic\T0123456N dic\T0123456N`<br>
       Expected: Similar to previous.

2. Editing a patient's NRIC of an appointment while all appointments are being shown

    1. Prerequisites: List all doctors using the `list_a` command. Multiple appointments in the appointment list. There exist a patient with NRIC `T0123456N` in the database.

    2. Test case (Valid patient's NRIC): `edit_a 1 pic\T0123456N`<br>
       Expected: Patient's NRIC of the first appointment successfully edited. Details of the edited appointment shown in the status message.

    3. Test case (Missing index): `edit_a pic\T0123456N`<br>
       Expected: Patient's NRIC of the first appointment is not edited. Error details shown in the status message.

    4. Test case (Missing Parameter): `edit_a 1`<br>
       Expected: Similar to previous.

    5. Test case (Invalid Index): `edit_a x pic\T0123456N` (where x is less than one or larger than the list size)<br>
       Expected: Similar to previous.

    6. Test case (Repeated Parameter): `edit_a 1 pic\T0123456N pic\T0123456N`<br>
       Expected: Similar to previous.

### Deleting an appointment

1. Deleting an appointment while all appointments are being shown

    1. Prerequisites: List all appointments using the `list_a` command. Multiple appointments in the list.

    2. Test case: `delete_a 1`<br>
       Expected: First appointment is deleted from the list. Details of the deleted appointment shown in the status message.

    3. Test case (Missing Index): `delete_a`<br>
       Expected: No appointment is deleted. Error details shown in the status message.

    4. Test case (Invalid Index): `delete_a x`(where x is smaller than one or larger than the list size)<br>
       Expected: Similar to previous.

### Viewing an appointment

1. Viewing an appointment while all appointments are being shown

    1. Prerequisites: List all appointments using the `list_a` command. Multiple appointments in the list.

    2. Test case: `view_a 1`<br>
       Expected: Appointment window pop up with details of the first appointment from the list. Details of the selected appointment shown in the status message.

    3. Test case (Invalid Index): `view_a x` (where x is smaller than one or larger than the list size)<br>
       Expected: Appointment window does not pop up. Error details shown in the status message.

### Adding a prescription

1. Adding a prescription to an appointment while all appointments are being shown

    1. Prerequisites: List all appointments using the `list_a` command. Multiple appointments in the appointment list. Ensure the first appointment does not have the prescription "Panadol".

    2. Test case: `add_tag_a 1 t\Panadolc`<br>
       Expected: Prescription successfully added to first appointment. Details of the prescription shown in the status message.

    3. Test case (Missing index): `add_tag_a t\Panadol`<br>
       Expected: Prescription is not added. Error details shown in the status message.

    4. Test case (Missing parameter): `add_tag_a 1`<br>
       Expected: Similar to previous.

    5. Test case (Multiple parameters): `add_tag_a 1 t\Panadol t\Panadol`<br>
       Expected: Similar to previous.

### Deleting a prescription

1. Deleting a prescription from an appointment while all appointments are being shown

    1. Prerequisites: List all appointments using the `list_a` command. Multiple appointments in the appointment list. Ensure the first appointment has the prescription "Panadol".

    2. Test case: `delete_tag_a 1 t\Panadol`<br>
       Expected: Prescription successfully deleted from the first appointment. Details of the prescription shown in the status message.

    3. Test case (Missing index): `delete_tag_a t\Panadol`<br>
       Expected: Prescription is not deleted. Error details shown in the status message.

    4. Test case (Missing parameter): `delete_tag_a 1`<br>
       Expected: Similar to previous.

    5. Test case (Multiple parameters): `delete_tag_a 1 t\Panadol t\Panadol`<br>
       Expected: Similar to previous.

### Editing an appointment's remark

1. Editing an appointment's remark while all appointments are being shown

    1. Prerequisites: List all appointments using the `list_a` command. Multiple appointments in the appointment list.

    2. Test case: `remark_a 1 r\Patient needs to follow-up`<br>
       Expected: Remark successfully added to the first appointment. Success message shown in the status message.

    3. Test case: `remark_a 1 r\` or `remark_a 1`<br>
       Expected: Remark successfully removed from the first appointment. Success message shown in the status message.

    4. Test case (Missing index): `remark_a r\Patient needs to follow-up`<br>
       Expected: Remark is not deleted. Error details shown in the status message.

### Saving data

1. Dealing with missing/corrupted data files

   1. Prerequisites: The database.json file in the data directory must exist.
   
   2. Test case: Delete the database.json file
      Expected: The app launches successfully, populated with the sample data.
   
   3. Test case: Delete the contents of the database.json file
      Expected: The app launches successfully, populated with no data.
   
   4. Test case: Add random characters to anywhere in the json file within the first set of curly brackets.
      Expected: Similar to previous.

2. Dealing with wrongly edited data files

   1. Prerequisites: The database.json file in the data directory must exist.
   
   2. Test case: Remove a field from any one of the doctors (e.g. nric, name, etc.)
      Expected: The app launches successfully, populated with no data.
   
   3. Test case: Remove a field from any one of the patients (e.g. nric, name, etc.)
      Expected: Similar to previous.
   
   4. Test case: Remove a field from any one of the appointments (e.g. patientNric, doctorNric, etc.)
      Expected: Similar to previous. 
   
   5. Test case: Edit the nric of a patient without editing the corresponding appointments.
      Expected: Similar to previous.


--------------------------------------------------------------------------------------------------------------------

## **Appendix: Planned Enhancement**

This section covers the list of planned enhancements to address any existing feature flaws.

### Specifying whether the patient or doctor NRIC is invalid when using `add_a` or `edit_a`

Currently, if a user enters an invalid NRIC for either of the parameters `DOCTOR_NRIC` and `PATIENT_NRIC`,
the result box will only state `NRIC should not be blank, should start with 'S', 'T', 'F', 'G' or 'M', contain 7 numbers, and end with a letter`.

For greater clarity and ease of use, we plan to specify which NRIC is the invalid NRIC, so the user would know which NRIC he or she needs to correct.


### Checking if the phone number is a valid Singapore number

Currently, the regex restriction for any phone number enter just needs to be 3 digits or longer and contains no spaces.

Since we are targeting small GP clinics in Singapore, it would be useful if we make the regex ensure that the number entered is a valid Singapore number.
This is done by ensuring the first digit is 6, 8 or 9, and the number entered is exactly 8 digits.

### Limiting the length of the remarks entered and wrapping the remark text

Currently, the length of the remarks entered is not restricted. Should the user decide to enter an extremely long remark,
the view window might truncate the remark, resulting in the user not being able to view the full remark.

To overcome this, we proposed to wrap the text of remark in the view window, so instead of the text getting truncated, it
will continue on the next line. Since we also intended for remarks to be a short note initially, we would like to restrict
the maximum characters entered for a remark to be 100 characters.

### Checking for repeated parameters in the edit remark features

Currently, the edit remark features for appointment, doctor and patient accepts repeated parameters with the prefix `r\ `.
Only the last supplied parameter is taken as the new remark while the others are all ignored.

Since there can only be 1 remark for each appointment/doctor/patient, it would be useful to limit the command to only accept 1 new remark.
This is done by checking for multiple occurrences of `r\ ` and displaying an error message if there are more than 1.
