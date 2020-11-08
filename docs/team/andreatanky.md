---
layout: page
title: Andrea Tan's Project Portfolio Page
---

## Project: ProductiveNUS

### Project Overview
ProductiveNUS is a desktop application targeted at Computing students of National University of Singapore (NUS) to help them manage and schedule their academic tasks efficiently. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

### My Contributions

#### Code contributed
This is the link to the code contributed by me:
[RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=andreatanky&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

#### Enhancements and features implemented

* **Creating the module code class** [\#35](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/35)

I refactored the address field in the original AB3 to module code field of ProductiveNUS. The validation regex had to be changed to suit that of module codes in NUS as well. This process was quite tedious as it was easy to miss out some address fields, which would cause a build error.

* **Implemented task list from assignment and lesson list** 
[\#81](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/81/files)

The task list we had in mind consists of assignments and lessons which are initially stored in two separate lists. Thus, I had to find a way to combine the two lists and sort the tasks by ascending time fields. I created the `UniqueTaskList` class along with other relevant classes and implemented methods that modifies and updates the list throughout relevant files. Many files had to be changed such as `Logic.java`, `Model.java`, `ModelManager.java` and `ReadOnlyAddressBook.java` etc. 
This was challenging as I had to think about the method to add the assignments and lessons into the task list. There were many ways such as for example adding an assignment into the task list immediately after it is added into the assignment list or clearing the task list and then adding all assignments and lessons. 
This was because the ease of deleting assignments had to be taken into account. 

* **Created storage for lessons** 
[\#35](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/35)

I created the `JsonAdaptedLesson` class and updated other json files such as `JsonSerializableProductiveNus.java` so that lessons are be stored.

* **Update classes for help, clear, undo and exit command such that no arguments are accepted**
[\#237](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/237/files)
[\#247](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/247)

I changed the constructor of these commands and updated its `execute` method to include checking for user input with Regular Expressions.

* **List Command** 
[\#81](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/81/files)
[\#119](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/119/files)
[\#81](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/81/files)
[\#237](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/237/files)
[\#272](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/272)

I enhanced the list command by allowing users to key in an optional argument index (number of days from 1 to 50) so that they can view assignments with deadlines that fall within the current date and time and number of days later. This command required quite a few changes as we initially did not decide on a fixed range of indexes that is deemed valid for this command. However, we eventually decided to do so as it would suit the purpose of this command better, which is to allow users to view assignments that he needs to complete soon. Also, we initially wanted to make this command work with task list instead of assignment list but after it was implemented, we felt that the list command should work with assignment list and more changes had to be made. There was also the use of Optionals in its implementation.

Tests were also added for `ListCommand` and `ListCommandParser`.

* **Find Command** 
[\#126](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/126/files)
[\#249](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/249)

I enhanced the find command by allowing users to find assignments by its name, module code, deadline or priority and multiple keywords is supported. This command was rather tedious to implement as there were many factors and aspects to consider. The prefixes found in the user's input have to be identified and the respective parse methods should be called. There were also many exceptions and invalid inputs to account for, such as having more than one prefix, inputting an empty command and whether there is a preamble before the prefix and after the `find` command. Since we decided to allow users to find assignments based on date or time of deadline to make it easier for them to find assignments (The user might not be able to remember the full deadline of assignment, and inputting an incorrect date or time will lead to inefficient searches), the keywords after the prefix for deadline have to be individually identified (whether it is in date or time format) with Regular Expressions before the String keyword can be parsed. 

Tests were also added for `FindCommand` and `FindCommandParser`.

* **Delete Command** 
[\#145](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/145/files)
[\#162](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/162/files)

I enhanced the delete command to support multiple deletions of assignments in a single command. I implemented the methods used in CommandLogic (Refactoring into this common class was done by another team member when we decided that other commands should support multiple indexes as well) which checks for invalid and duplicated index inputs. 

Tests were also added for `DeleteCommand` and `DeleteCommandParser`.

### My Contributions to User Guide 

I contributed to the introduction of the User Guide and added documentation for list, find, delete and edit under features. I also added the About segment description, GUI terminologies and diagrams. [\#127](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/127/files)
                                                                                                                                                                                                        [\#148](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/148/files)
                                                                                                                                                                                                        [\#161](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/161/files)
                                                                                                                                                                                                        [\#173](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/173/files)
                                                                                                                                                                                                        [\#175](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/175/files)
                                                                                                                                                                                                        [\#176](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/176/files)
                                                                                                                                                                                                        [\#235](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/235/files)

### My Contributions to Developer Guide

I contributed to list, find and delete features under Implementations and its UML diagrams. I also updated the description and contributed to the diagram for `Model` and added in the Introduction segment of the Developer Guide. I also contributed to the Appendix section (User stories, target user profile etc.).[\#53](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/53/files)
                                                                                                                                                                                                                                                                                                                        [\#131](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/131)
                                                                                                                                                                                                                                                                                                                        [\#160](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/160/files)
                                                                                                                                                                                                                                                                                                                        [\#255](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/255/files)
  [\#273](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/273)                                                                                                                                                                                                                                                                                                                  

### My Contributions to team-based tasks

* I had set up the Github team org/repo and I manage the github settings for our team.
* I maintained the issue tracker and I assigned and added relevant tags for issues.
* I released v1.2 of ProductiveNUS before the deadline [v1.2 release](https://github.com/AY2021S1-CS2103T-F11-3/tp/releases/tag/v1.2)
* I updated parts of the User/Developer Guide that are not specific to a feature
* I contributed to team meeting notes that were taken down during our weekly team discussions
* I sent summaries of what was discussed during our team meeting into our chat group to remind everyone of our individual/team responsibilities and deadlines 
* I contributed to the README file for ProductiveNUS. [\#57](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/57/files)
                                                      [\#69](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/69/files)
                                                      [\#70](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/70/files)

### Review contributions:
* PRs reviewed (with non-trivial review comments): [\#253](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/253)
[\#135](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/135), [\#125](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/125), [\#74](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/74)
* Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/andreatanky/ped/issues/1), [2](https://github.com/andreatanky/ped/issues/2), [3](https://github.com/andreatanky/ped/issues/3), [4](https://github.com/andreatanky/ped/issues/4), [5](https://github.com/andreatanky/ped/issues/5), [6](https://github.com/andreatanky/ped/issues/6), [7](https://github.com/andreatanky/ped/issues/7), [8](https://github.com/andreatanky/ped/issues/8))


