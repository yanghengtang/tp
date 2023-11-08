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

  * View Doctor
    * What it does: View doctor's details 
    * Justification: We can see doctor's detail like his specialisations, nric and remarks.
    * Highlights: A clean window will be prompt out with all the doctor's details.

  * List Doctor
    * What it does: List all the existing doctors in database
    * Justification: you are able to see all the existing doctors along with their name and nric
    * Highlights: All doctor's will be shown in a list of box form along with the name and nric.


* **Code contributed**:  ~4kLOC [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&tabOpen=true&tabType=authorship&tabAuthor=kimshitong&tabRepo=AY2324S1-CS2103T-T08-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false%23%2F)
  *  Test Code : 2126 LOC
  *  Functional Code : 1046 LOC
  *  Docs : 725 LOC


* **Project management**:
  * Version control 
  * Documentation Management
  * Team Communication 


* **Documentation**:
  * User Guide:
    * Document New Features 
    * Updated Command Summary
    * Updated Parameter Table 
  * Developer Guide:
    * User Stories
    * Document Features for List Patient/Doctor/Appointments with Activity/Sequence UML Diagram
    * Document Features for View Patient/Doctor/Appointments with Activity/Sequence UML Diagram
    * Document Features for Delete Specialisation/Medical Conditions/Prescriptions with Activity/Sequence UML Diagram

* **Community**:
  * PRs reviewed (with non-trivial review comments)
  * Contributed to forum discussions
  * Reported bugs and suggestions for other teams in the class
