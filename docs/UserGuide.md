---
layout: page
title: User Guide
---

MediConnect is a **desktop app for managing contacts, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, MediConnect can get your patient, doctor and appointment management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `mediconnect.jar` from [here](https://github.com/AY2324S1-CS2103T-T08-1/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your MediConnect.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar mediconnect.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list_a` : Lists all appointments.

   * `add_p n\Joe Ng ic\T0383462A p\83745623` : Adds a patient named `Joe Ng` to the list of Patient.

   * `delete_d id\T0123321D` : Deletes the doctor with NRIC `T0123321D`.

   * `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n\NAME`, `NAME` is a parameter which can be used as `add n\John Doe`.

* Items in square brackets are optional.<br>
  e.g `n\NAME [t\TAG]` can be used as `n\John Doe t\friend` or as `n\John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t\TAG]…​` can be used as ` ` (i.e. 0 times), `t\friend`, `t\friend t\family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n\NAME p\PHONE_NUMBER`, `p\PHONE_NUMBER n\NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a patient: `add_p`

Adds a patient to the system.

Format: `add_p n\NAME ic\NRIC p\PHONE_NUMBER`

Examples:
* `add_p n\John Doe ic\T0212385J p\98765432`
* `add_p ic\S9912343G n\Betsy Crowe p\81235833`

### Listing all patient : `list_p`

Shows a list of all patients in the system.

Format: `list_p`

### Editing a patient : `edit_p`

Edits an existing patient in the system.

Format: `edit_p ic\NRIC [n\NAME] [p\PHONE]`

* Edits the person with the specified `NRIC`.
* At least one of the optional fields must be provided.
* The NRIC of the patient cannot be changed.
* Existing values will be updated to the input values.

Examples:
*  `edit_p ic\T0212385J n\Joe Ng` Edits the name of the patient with NRIC `T0212385J` to be `Joe Ng`.
*  `edit_p ic\S9912343G p\91234567` Edits the phone of patient with NRIC `S9912343G` to be `91234567`.

### Locating patients by name: `find_p`

Finds patients whose names contain any of the given keywords.

Format: `find_p KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name of the patient is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Patients matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find_p John` returns `john` and `John Doe`
* `find_p alex david` returns `Alex Yeoh`, `David Li`<br>

### Deleting a patient : `delete_p`

Deletes the specified patient from the system.

Format: `delete_p ic\NRIC`

* Deletes the patient with the specified `NRIC`.

Examples:
* `delete_p ic\T0212385J` deletes the patient with NRIC `T0212385J` in the system.

### Adding a doctor: `add_d`

Adds a doctor to the system.

Format: `add_d n\NAME ic\NRIC`

Examples:
* `add_d n\John Doe ic\T0212385J`
* `add_d ic\S9912343G n\Betsy Crowe`

### Listing all doctor : `list_d`

Shows a list of all doctors in the system.

Format: `list_d`

### Editing a doctor : `edit_d`

Edits an existing doctor in the system.

Format: `edit_d ic\NRIC [n\NAME]`

* Edits the doctor with the specified `NRIC`.
* At least one of the optional fields must be provided.
* The NRIC of the dcotor cannot be changed.
* Existing values will be updated to the input values.

Examples:
*  `edit_d ic\T0212385J n\Joe Ng` Edits the name of the doctor with NRIC `T0212385J` to be `Joe Ng`.

### Locating doctors by name: `find_d`

Finds doctor whose names contain any of the given keywords.

Format: `find_d KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name of the doctor is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Doctors matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find_d John` returns `john` and `John Doe`
* `find_d alex david` returns `Alex Yeoh`, `David Li`<br>

### Deleting a doctor : `delete_d`

Deletes the specified doctor from the system.

Format: `delete_d ic\NRIC`

* Deletes the doctor with the specified `NRIC`.

Examples:
* `delete_d ic\T0212385J` deletes the doctor with NRIC `T0212385J` in the system.

### Adding an appointment: `add_a`

Adds an appointment into the system.

Format: `add_a pic\PATIENT_NRIC dic\DOCTOR_NRIC d\DATE_TIME`

Examples:
* `add_a pic\S9912343G dic\T0212385J d\2023-09-11T07:30`

### Listing all appointments : `list_a`

Shows a list of all upcoming appointments in the system.

Format: `list_a`

### Locating appointment by patient or doctor NRIC: `find_a`

Finds appointment with the specified doctor or patient NRIC.

Format: `find_a [pic\PATIENT_NRIC] [dic\DOCTOR_NRIC]`

* Find any appointment of the doctor or patient with the specified `NRIC` has.
* At least one of the optional field must be provided

Examples:
* `find_a pic\S9912343G` returns all the appointment the patient with NRIC `S9912343G` has.

### Deleting an appointment : `delete_a`

Deletes the specified appointment from the system.

Format: `delete_a pic\PATIENT_NRIC dic\DOCTOR_NRIC d\DATE_TIME`

* Deletes the appointment with the specified `PATIENT_NRIC`, `DOCTOR_NRIC` and `DATE_TIME`.

Examples:
* `delete_a pic\S9912343G dic\T0212385J d\2023-09-11T07:30` deletes the existing appointment between patient with NRIC `S9912343G` and doctor with NRIC `T0212385J` at `9 Sep 23 7.30am`.

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

MediConnect data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

MediConnect data are saved automatically as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, MediConnect will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous MediConnect home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. _Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action                 | Format, Examples                                                                                                            |
|------------------------|-----------------------------------------------------------------------------------------------------------------------------|
| **Add Patient**        | `add_p n\NAME ic\NRIC p\PHONE_NUMBER` <br> e.g., `add_p n\John Doe ic\T0212385J p\98765432`                                 |
| **Add Doctor**         | `add_d n\NAME ic\NRIC` <br> e.g., `add_d n\John Doe ic\T0212385J`                                                           |
| **Add Appointment**    | `add_a pic\PATIENT_NRIC dic\DOCTOR_NRIC d\DATE_TIME` <br> e.g., `add_a pic\S9912343G dic\T0212385J d\2023-09-11T07:30`      |
| **Delete Patient**     | `delete_p ic\NRIC`<br> e.g., `delete_p ic\T0212385J`                                                                        |
| **Delete Doctor**      | `delete_d ic\NRIC`<br> e.g., `delete_d ic\T0212385J`                                                                        |
| **Delete Appointment** | `delete_a pic\PATIENT_NRIC dic\DOCTOR_NRIC d\DATE_TIME`<br> e.g., `delete_a pic\S9912343G dic\T0212385J d\2023-09-11T07:30` |
| **Edit Patient**       | `edit_p ic\NRIC [n\NAME] [p\PHONE]`<br> e.g.,`edit_p ic\T0212385J n\Joe Ng`                                                 |
| **Edit Doctor**        | `edit_d ic\NRIC [n\NAME]`<br> e.g.,`edit_d ic\T0212385J n\Joe Ng`                                                           |
| **Find Patient**       | `find_p KEYWORD [MORE_KEYWORDS]`<br> e.g., `find_p James Jake`                                                              |
| **Find Patient**       | `find_d KEYWORD [MORE_KEYWORDS]`<br> e.g., `find_d James Jake`                                                              |
| **List Patient**       | `list_p`                                                                                                                    |
| **List Doctor**        | `list_d`                                                                                                                    |
| **List Appointment**   | `list_a`                                                                                                                    |
| **Help**               | `help`                                                                                                                      |
