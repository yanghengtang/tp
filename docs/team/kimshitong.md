---
layout: page
title: Shi Tong's Project Portfolio Page
---

### Project: MediConnect

AddressBook - MediConnect is a desktop address book application used for managing hospital staff and patients. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 21 kLoC.

Given below are my contributions to the project.

* **New Feature**: 
  * Add Doctor 
    * What it does: Allows user to add doctors to the database
    * Justification: We are able to retrieve multiple inputs to store more details about doctor like name and nric  
    * Highlights: We check the validity of inputs with regular expressions like NRIC will check if it starts with S/T/F/G/M and followed by 7 digits and end with a uppercase alphabet to ensure data integrity

  * Delete Doctor
    * What it does: Allows User to remove doctors from the database
    * Justification: This allows the receptionists to remove invalid doctors or doctors that are going to leave the clinics.
    * Highlights : The appointments that is associated with the doctors will also be deleted for the data integrity.

  * Edit Doctor
    * What it does: Edit existing doctors' details
    * Justification: This allows receptionists to edit the doctor's name and nric with index if there's any mistakes 
    * Highlights: With the aid of OOP, the doctor details at other classes like appointment will also be updated

  * Find Doctor
    * What it does: Find doctors based on the name
    * Justification: This allows receptionists to find doctor very quickly just based on the name
    * Highlights: It finds doctors that contains the input name keywords (case-insensitive)

  * View Doctor
    * What it does: View doctor's details 
    * Justification: 
    * Highlights: Allows user to modify the details

  * List Doctor
    * What it does: Doctor's Command : Add/Delete/Edit/Find/View/List,
    * Justification: It allows the CRUD of Doctor, also allowing user to find and view the doctor's details
    * Highlights: Allows user to modify the details

* **Code contributed**: [RepoSense link]()

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
