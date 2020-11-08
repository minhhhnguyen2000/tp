---
layout: page
title: Choo Jia Xin's Project Portfolio Page
---

## Project: ProductiveNUS

PProductiveNUS is a desktop application targeted at Computing students of National University of Singapore (NUS) to help them manage and schedule their academic tasks efficiently. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to set/remove reminders for assignments.
  * What it does: allows the user to set/remove reminders for one assignments at a time. The user can set reminders for multuiple assignments at a time as well.
  * Justification: This feature improves the product because a user may forget assignments that have faraway deadline and the app should provide a convenient way for users to be reminded to finish their assignments. A user can make mistakes when setting reminders for assignments as well, thus the app should provide a way for users to rectify their mistake.
  
* **New Feature**: Added automation of the task list so that it is updated in real time.
  * What it does: Checks the user's upcoming tasks in real time and automatically updates removes any task that has passed.
  * Justification: This feature improves the product significantly because a user will refer to their upcoming tasks frequently to quickly view what task they have next on their schedule. Thus, the app should accurately reflect the next task in their schedule without the need for a user to manually update it himself.
  * Highlights: This enhancement affects existing data and future data stored in the app in real time. The implementation too was challenging as it required an in-depth understanding of both multithreading and JavaFX.
  * Credits: Code implemented is adapted from one of the examples given in the [Task<V> JavaFX API](https://docs.oracle.com/javafx/2/api/javafx/concurrent/Task.html) as well as an answer from [StackOverflow](https://stackoverflow.com/questions/9966136/javafx-periodic-background-task).

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=choojiaxin)

* **Project management**:
  * Managed releases `v1.2` - `v1.4` (3 releases) on GitHub

* **Enhancements to existing features**:
  * Updated the GUI color scheme (Pull requests [\#33](), [\#34]())
  * Wrote additional tests for existing features to increase coverage from 88% to 92% (Pull requests [\#36](), [\#38]())

* **Documentation**:
  * User Guide:
    * Added documentation for the features `remind` and `unremind` [\#72]()
    * Added documentation for `Icon usages`, `Command syntax` and `Date and time format`
    * Did cosmetic tweaks to existing documentation tips and pointers to note [\#74]()
  * Developer Guide:
    * Added implementation details of the `remind`, `unremind, unprioritize and undone` feature.
    * Added implementation details of `Automated updating of task list`.
    * Edited the Logic Class Diagram.
    * Added Sequence Diagram for `feature`, Activity diagram for `Automated updating of task list` and Class diagram for `NegateCommand`.

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#12](), [\#32](), [\#19](), [\#42]()
  * Contributed to forum discussions (examples: [1](), [2](), [3](), [4]())
  * Reported bugs and suggestions for other teams in the class (examples: [1](), [2](), [3]())
  * Some parts of the history feature I added was adopted by several other class mates ([1](), [2]())

* **Tools**:
  * Integrated a third party library (Natty) to the project ([\#42]())
  * Integrated a new Github plugin (CircleCI) to the team repo

* _{you can add/remove categories in the list above}_
