---
layout: page
title: Derek's Project Portfolio Page
---

### Project: MediConnect

MediConnect - MediConnect is a desktop clinic management application used for managing hospital staff and patients. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 21 kLoC.

Given below are my contributions to the project.

* **New Feature**: 
  * Generic UniqueItemList
    * What it does: Allows developers to use a single class to handle lists of objects that are unique.
    * Justification: Maintaining a list of unique objects is a common requirement for Appointments, Doctors and Patients. 
    Instead of duplicating code to create a UniqueAppointmentList, UniqueDoctorList, and UniquePatientList, it would be better to create a single class using generics to handle all of them.
    This allows developers to make a change only in this class, rather than having to make the changes in multiple classes. This also reduces the probability of introducing new bugs when copying the code.
    * Highlights: Common functionality like checking of duplicates is handled in this class. Additional flexibility where needed is provided through methods that accept lambda functions as parameters.
  
  * Saving Appointments, Doctors and Patients to file
    * What it does: Allows data in mediconnect to be saved to file.
    * Justification: To allow data entered by the user to be maintained after the application is closed, it is necessary to write the data to a file and retrieve it subsequently.
    
  * Validation of database.json file
    * What it does: When reading the saved data from the database.json file, validation checks are performed to ensure that data in the file is still valid.
    * Justification: Users are allowed to make changes directly to the database.json file. However, this might introduce invalid data. It is necessary to check that the data read is valid when launching the application.
    * Highlights: No invalid data will be read into memory.
  
  * Cascading of changes from patients and doctors to appointments
    * What it does: Cascades changes from edit and delete patient and doctor to appointment.
    * Justification: When the NRIC of a doctor is changed, all the appointments of the doctor needs to reflect the changes, otherwise the appointments will not have a corresponding doctor in the system. 
    Likewise, if a doctor is removed from the system, all his appointments should be removed as well since those appointments no longer have a valid doctor.
    This also applies when patients are edited or removed from the system.
    * Highlights: When a doctor's/patient's NRIC is edited, the changes are reflected in the appointments.
    When a doctor/patient is removed, the corresponding appointments are also removed.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=derek&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&tabOpen=true&tabType=authorship&tabAuthor=derekjxtan&tabRepo=AY2324S1-CS2103T-T08-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Project management**:
  * Release Management
  * Version control

* **Documentation**:
  * User Guide:
    * Quickstart section
    * General features
    * FAQ
  * Developer Guide:
    * Update Architecture, Model Component, Storage component in Design
    * Update instructions for manual testing
    * Add planned enhancements

* **Community**:
  * PRs reviewed (with non-trivial review comments): [#93](https://github.com/AY2324S1-CS2103T-T08-1/tp/pull/93), [#116](https://github.com/AY2324S1-CS2103T-T08-1/tp/pull/116), [#151](https://github.com/AY2324S1-CS2103T-T08-1/tp/pull/151)
  * Reported bugs and suggestions for other team W09-4 during PE-D (examples: [PE-D repolink](https://github.com/derekjxtan/ped/issues))
