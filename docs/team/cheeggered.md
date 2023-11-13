---
layout: page
title: Chee Teng's Project Portfolio Page
---

### Project: MediConnect

MediConnect - MediConnect is a desktop clinic management application used for managing clinic staff and patients. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**:
  * Added the ability to add patients through the add patient command.
    * What it does: Allows users to add a patient to the database.
    * Justification: This allows receptionists to add new patients.
    * Highlights: The challenge in this feature came in the strict validity checks that had to be enforced. In particular, before the said patient could be added, a patient with the same NRIC must not be in the database.

  * Added the ability to delete/edit a patient through the delete/edit patient command.
    * What it does: Allows users to delete/edit a patient from the database.
    * Justification: Allows receptionists to remove patients from the database and also amend errors made when adding a patient.
    * Highlights: To maximise the convenience of the edit patient feature, the inputs to the edit patient command are optional (minimally 1 input required)

  * Added the ability to add/delete a patient's tag through the add/delete medical condition command.
    * What it does: Allows users to add/delete a tag to/from an existing patient in the database.
    * Justification: Allows receptionists to add/delete medical conditions to the patient.
    * Highlights: Receptionists are able to record the medical conditions of their patient.

  * Edit Patient's Remark
    * What it does: Edit the remark of a patient
    * Justification: It allows us to edit the remark of a patient in the database.
    * Highlights: We are able to edit a patients remark to store additional information about the patient.


  * Added the ability to find the patient by keyword.
    * What it does: Allows users to find patients by the entered keyword. If no arguments are provided, an error message is returned.
    * Justification: This allows the receptionists to find a patient through their name.
    * Highlights: To maximise the flexibility of this feature, the keyword is case-insensitive.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=cheeggered&breakdown=true)

* **Project management**:
  * 

* **Enhancements to existing features**:
  * 

* **Documentation**:
  * User Guide:
    * 
  * Developer Guide:
    * 

* **Community**:
  * PRs reviewed (with non-trivial review comments): []()
  * Contributed to forum discussions (examples: []())
  * Reported bugs and suggestions for other teams in the class (examples: []())
  * Some parts of the history feature I added was adopted by several other class mates ([]())

* **Tools**:
  * Integrated a third party library (Natty) to the project ([]())
