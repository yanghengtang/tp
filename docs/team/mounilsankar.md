---
layout: page
title: Mounil's Project Portfolio Page
---

### Project: MediConnect

MediConnect - MediConnect is a desktop clinic management application used for managing clinic staff and patients. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: 
  * Added the ability to add appointments through the add appointment command.
    * What it does: Allows users to add an appointment to the database.
    * Justification: This allows receptionists to add new appointments between a doctor and patient to the database.
    * Highlights: The challenge in this feature came in the strict validity checks that had to be enforced. In particular, before the said appointment could be added, both the doctor and patient in the given appointment had to exist first.

  * Added the ability to list the appointments by doctor and/or patient.
    * What it does: Allows users to list appointments by doctor and/or patient. If no arguments are provided, all appointments are listed.
    * Justification: This allows the receptionists to view what appointments belong to a particular patient/doctor or simply view every appointment scheduled in the clinic.
    * Highlights : This feature was designed to provide users with as much flexibility as possible when listing appointment. In order to achieve this, the arguments to the command were made optional. If no arguments were specified, then every appointment would be listed.

  * Added the ability to view details of a specific appointment in the clinic.
    * What it does: Opens a pop up window containing the full details of an appointment when the command view_a is entered.
    * Justification: This allows receptionists to see full details about an appointment so that they can see any remarks that doctors have made as well as prescriptions.
    * Highlights: The main challenge of this feature was to update the right attribute in the model so that the ui would be able to display the right appointment in the pop up window that is showed to the receptionist.
  
  * Added the ability to delete a specific appointment
    * What it does: Deletes a specific appointment from the current list of appointments.
    * Justification: This allows receptionists to delete any appointments mistakenly added.
    * Highlights: This feature identifies the appointment to delete through its index in the appointment list.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=mounilsankar&breakdown=true)

* **Project management**:
  * Setup GitHub Team Organisation and Repository
  * Version control

* **Documentation**:
  * User Guide:
    * Added documentation for the features `edit_a`, `add_tag_a`, `delete_tag_a` (Pull request [#192](https://github.com/AY2324S1-CS2103T-T08-1/tp/pull/192)).
  * Developer Guide:
    * Added first half of use cases (Pull requests [#86](https://github.com/AY2324S1-CS2103T-T08-1/tp/pull/86), [#294](https://github.com/AY2324S1-CS2103T-T08-1/tp/pull/294)).
    * Added implementation for `add` `delete` and `addTag` features (Pull requests [#165](https://github.com/AY2324S1-CS2103T-T08-1/tp/pull/165), [#267](https://github.com/AY2324S1-CS2103T-T08-1/tp/pull/267)).
    * Added part of instruction for manual testing (Pull requests [#318](https://github.com/AY2324S1-CS2103T-T08-1/tp/pull/318)).

* **Community**:
  * PRs reviewed (with non-trivial review comments): [#177](https://github.com/AY2324S1-CS2103T-T08-1/tp/pull/177), [#156](https://github.com/AY2324S1-CS2103T-T08-1/tp/pull/156), [#158](https://github.com/AY2324S1-CS2103T-T08-1/tp/pull/158), [103](https://github.com/AY2324S1-CS2103T-T08-1/tp/pull/103).
  * Reported bugs and suggestions for team W08 during PE-D (examples: [PE-D repolink](https://github.com/mounilsankar/ped/issues))
  * Contributed to forum discussion (examples: [#455](https://github.com/nus-cs2103-AY2324S1/forum/issues/455), [#446](https://github.com/nus-cs2103-AY2324S1/forum/issues/446))
