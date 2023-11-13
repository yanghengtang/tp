---
layout: page
title: Yang Heng's Project Portfolio Page
---

### Project: MediConnect

MediConnect - MediConnect is a desktop clinic management application used for managing appointments between doctors and patients. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 20 kLoC.

Given below are my contributions to the project.

* **View Feature**: 
  * What it does: Allows user to view the full detail of an appointment, doctor, or patient.
  * Justification: To keep the main window uncluttered, we had to omit the majority of the information related to an appointment, doctor, or patient. Therefore, the view feature enables the user to view the full detail of the entities whenever they want to.
  * Highlights: Whenever the view feature is called, a window consisting of the appointment/doctor/patient will pop up.
  * Credits: This feature was enabled with the JavaFX and Jackson library given the amount of GUI involved.

* **Patient, Doctor and Appointment Model**:
  * What it does: Models the actual entities that is related to a small GP clinic. These three key entities are the main representation of the entities in our problem domain we are attempting to address.
  * Justification: Adapting and morphing our entities based on the Person model in AB3 was crucial to maintain the strong OOP design.

* **Update the Logic and Database**:
  * What it does: The API Logic can now handle all operations to handle appointment, doctor and patients. The Database can also now handle these three entities.
  * Justification: To handle all three entities (appointment, doctor and patient), the Database and Logic requires new attributes and method to facilitate the operations of the application.

* **Implement the UI**:
  * What it does: The new UI now is able to show three list, each for appointment, doctor and patient respectively
  * Justification: Since we had three entites we had to deal with, the optimal way forward was to display all three entities list instead of having one list panel and changing constantly between the three entities.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=yanghengtang&breakdown=true)

* **Project management**:
  * Weekly team meetings: Prepared the agenda and co-chaired the meetings.
  * Planned the mini deadlines to ensure the team is on track to meet the given deadline.
  * De-conflict any potential clashes between features with the same dependencies.

* **Enhancements to existing features**:
  * Implemented the new MediConnect-Turquoise theme to replace the Dark theme from AB3.

* **Documentation**:
  * User Guide:
    * Wrote the introduction, guide to using the guide, known issues and command summary section.
  * Developer Guide:
    * Wrote the UI and Logic component section, implementation of View Feature, and some instructions for manual testing.

* **Community**:
  * PRs reviewed (with non-trivial review comments): [PR #161](https://github.com/AY2324S1-CS2103T-T08-1/tp/pull/161#discussion_r1372966211), [PR #189](https://github.com/AY2324S1-CS2103T-T08-1/tp/pull/189#discussion_r1379868408), [PR #269](https://github.com/AY2324S1-CS2103T-T08-1/tp/pull/269#discussion_r1385095244)
  * Contributed to forum discussions (examples: [#255](https://github.com/nus-cs2103-AY2324S1/forum/issues/225), [#376](https://github.com/nus-cs2103-AY2324S1/forum/issues/376), [#444](https://github.com/nus-cs2103-AY2324S1/forum/issues/444))
  * Reported bugs and suggestions for other teams in the class (examples: [PE-Dry Run](https://github.com/yanghengtang/ped))
  * Some parts of the history feature I added was adopted by several other classmates ([Inaccuracy in error messages bug](https://github.com/AY2324S1-CS2103T-F12-2/tp/issues/149), [Invalid inputs not throwing errors bug](https://github.com/AY2324S1-CS2103T-F12-2/tp/issues/146))
