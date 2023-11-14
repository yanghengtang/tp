---
layout: page
title: Shi Tong's Project Portfolio Page
---

### Project: MediConnect

MediConnect - MediConnect is a desktop clinic management application used for managing clinic staff and patients. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 21 kLoC.

Given below are my contributions to the project.


* **New Feature**: 
  * Add Doctor 
    * What it does: Allows user to add doctor to the database
    * Justification: We are able to retrieve multiple inputs to store more details about doctor like name and nric  
    * Highlights: We check the validity of inputs with regular expressions like NRIC will check if it starts with S/T/F/G/M and followed by 7 digits and end with a uppercase alphabet to ensure data integrity

  * Delete Doctor
    * What it does: Allows User to remove doctor from the database
    * Justification: This allows the receptionists to remove invalid doctors or doctors that are going to leave the clinics.
    * Highlights : The appointments that is associated with the doctors will also be deleted for the data consistency.

  * Edit Doctor
    * What it does: Edit existing doctor's details
    * Justification: This allows receptionists to edit the doctor's name and nric with index if there's any mistakes 
    * Highlights: With the aid of OOP, the doctor details at other classes like appointment will also be updated

  * Find Doctor
    * What it does: Find doctors based on the name
    * Justification: This allows receptionist to find doctor very quickly just based on the name
    * Highlights: It finds doctors that contains the input name keywords (case-insensitive)

  * Add Doctor's Specialisation
    * What it does: Add Specialisation to a Doctor
    * Justification: It allows us to identify the doctor's specialisation like "Orthopaedic" and "Pediatric" quickly  
    * Highlights: We are able to add multiple specialisation to a doctor 

  * Delete Doctor's Specialisation
    * What it does: Delete Specialisation from a Doctor
    * Justification: It allows us to remove the doctor's specialisation based on the index.
    * Highlights: We are able to remove specialisation to a doctor 

  * Edit Doctor's Remark
    * What it does: Edit Remark of a Doctor
    * Justification: It allows us to leave a remark of the doctor which can be used to indicate doctor's availability.
    * Highlights: We are able to edit doctor remark whenever there is a new update on availability.

  * List Doctor
    * What it does: List all the existing doctors in database
    * Justification: you are able to see all the existing doctors along with their name and nric
    * Highlights: All doctors will be shown in a list of box form along with the name and nric.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=kimshitong&breakdown=true)

* **Project management**:
  * Version control 
  * Documentation Management

* **Documentation**:
  * User Guide:
    * Documented Features for Add Doctor's Specialisation [#189](https://github.com/AY2324S1-CS2103T-T08-1/tp/pull/189)
    * Documented Features for Delete Doctor's Specialisation [#189](https://github.com/AY2324S1-CS2103T-T08-1/tp/pull/189)
    * Documented Edit Doctor's Remark [#189](https://github.com/AY2324S1-CS2103T-T08-1/tp/pull/189)
    * Updated Command Summary  [#189](https://github.com/AY2324S1-CS2103T-T08-1/tp/pull/189) [#245](https://github.com/AY2324S1-CS2103T-T08-1/tp/pull/245)
    * Updated Parameter Table [#248](https://github.com/AY2324S1-CS2103T-T08-1/tp/pull/248)
  * Developer Guide:
    * Added User Stories [#57](https://github.com/AY2324S1-CS2103T-T08-1/tp/pull/57)
    * Documented Features for Find Patient/Doctor/Appointment with Activity/Sequence Diagram [#156](https://github.com/AY2324S1-CS2103T-T08-1/tp/pull/156)
    * Documented Features for List Patient/Doctor/Appointment with Activity/Sequence Diagram [#156](https://github.com/AY2324S1-CS2103T-T08-1/tp/pull/156)
    * Documented Features for Delete Specialisation/MedicalCondition/Prescription with Activity/Sequence Diagram [#264](https://github.com/AY2324S1-CS2103T-T08-1/tp/pull/264)
    * Improved and Standardised Features' Activity/Sequence Diagram [#327](https://github.com/AY2324S1-CS2103T-T08-1/tp/pull/327)

* **Community**:
  * PRs reviewed (with non-trivial review comments) [#298](https://github.com/AY2324S1-CS2103T-T08-1/tp/pull/298) [#290](https://github.com/AY2324S1-CS2103T-T08-1/tp/pull/290) [#287](https://github.com/AY2324S1-CS2103T-T08-1/tp/pull/287) [#192](https://github.com/AY2324S1-CS2103T-T08-1/tp/pull/192) [#119](https://github.com/AY2324S1-CS2103T-T08-1/tp/pull/118)
  * Reported bugs and suggestions for other teams in the class
