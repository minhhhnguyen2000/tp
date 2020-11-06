---
layout: page
title: User Guide
---

## Introduction
Welcome to ProductiveNUS User Guide! :blue_book:

**ProductiveNUS is a desktop application** made for you, **a School of Computing (SoC) student in National University of Singapore (NUS)**, to **manage and schedule your academic tasks more effectively**. It makes use of a **Graphical User Interface (GUI)**, which provides you with an intuitive interface and immediate visual feedback. ProductiveNUS uses a **Command Line Interface (CLI)**; this means that you operate the application by typing commands into a [Command Box](#gui-terminologies). If you are fast at typing, you can manage your academic tasks more efficiently.

As a **student from the SoC in NUS**, you tend to have a **heavy workload**. ProductiveNUS helps **improve your productivity** by **enhancing your organisational skills**. Apart from simply **keeping track of your tasks**, ProductiveNUS is capable of **scheduling** them for you so you will never **miss any deadlines**. ProductiveNUS is also compatible with NUSMods, meaning all your **timetable information can be imported easily** into the application so all your academic tasks can be found in just one application.

--------------------------------------------------------------------------------------------------------------------

## Table of Contents

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## About
This user guide provides you with the necessary information on how to become an expert user of ProductiveNUS. 
You can familiarize yourself with the terminologies, syntax and icons used in this user guide by reading the following sub-sections.  

### GUI terminologies
The figure below shows the GUI of ProductiveNUS, with its sections labelled. 
![GUI](images/GUI.png)
*Figure X: Labelled GUI sections*

### Icon usages
Wondering what each icon is used for? You can refer to the table below to find out.

| Icon        | Icon usage                                             | Box color |
|-------------|--------------------------------------------------------|-----------|
| :clipboard: | - Notes about the command format<br>- Pointers to note | Blue      |
| :bulb:      | - Tip                                                  | Green     |

### Command syntax

All commands and their examples are demarcated with `markups`. `Markups` appear as a grey box as shown.

### Date and time format

All commands with date and/or time parameters have a specific format you must follow, else your input will be deemed as invalid.

#### Date

Date parameters must be in the format `dd-MM-yyyy`, where `dd` is day of the month, `MM` is month and `yyyy` is year. The number of repeated letters indicate the number of digits required for day of the month, month and year. 

For example, `dd` means that the day of the month has to be a 2-digit number. Hence, for dates you want to input from the 1st to the 9th of any month, you must key in `09-01-2020`. `9-01-2020` will be deemed as invalid.

Likewise for month and year, `09-1-2020` and `09-01-20` will be deemed as invalid.

#### Time

Time parameters must be in the format `HHmm`, where `HH` is the hour of the day and `mm` is the minute of the hour. You must input the time according to the 24-hour clock system.

For example, 7:00 AM is `0700` while 7:00 PM is `1900`.

--------------------------------------------------------------------------------------------------------------------

## Getting started

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `productivenus.jar` from [here](https://github.com/AY2021S1-CS2103T-F11-3/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for ProductiveNUS.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>

   ![Ui](images/Ui.png)

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:clipboard: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by you.<br>
  e.g. in `add n/NAME_OF_ASSIGNMENT`, `NAME_OF_ASSIGNMENT` is a parameter which can be used as `add n/Assignment 2`.

* Items in square brackets are optional.<br>
  e.g `n/NAME_OF_ASSIGNMENT [remind]` can be used as `n/Assignment 2 remind` or as `n/Assignment 2`.


* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME_OF_ASSIGNMENT d/DEADLINE`, `d/DEADLINE n/NAME_OF_ASSIGNMENT` is also acceptable.
  
* All instances of `INDEX` **must be a positive integer**.<br>
  e.g. 1, 2, 3, …​

</div>

### Adding an assignment: `add`

Format: `add n/NAME_OF_ASSIGNMENT d/DEADLINE_OF_ASSIGNMENT TIME_ASSIGNMENT_IS_DUE mod/MODULE​[p/PRIORITY] 
[remind]`

You can add your assignments into your schedule so that you can manage all your assignments and
 academic tasks conveniently. 

<div markdown="span" class="alert alert-success">
**:bulb: Tip:**
You can include `remind` when adding an assignment instead of using the `remind` command to set reminders after adding an assignment.
</div>

For example, to add a lab report assignment that is **due** on 23th April 2020 12.30pm and **associated** with the module CS2100, you can 
simply enter `add n/Lab report d/23-04-2020 1230 mod/CS2100`. All the information of the assignment will be displayed as shown in the labelled diagram below.

   ![UserGuideAddCommand](images/UserGuideAddCommand.PNG)
   *Figure 1: Information of the assignment added labelled*

<div markdown="block" class="alert alert-primary">

 **:clipboard: Pointers to note:**<br>
* `DEADLINE_OF_ASSIGNMENT` must be in the format dd-MM-yyyy and time in the format HHmm (24-hour).
</div>

<div markdown="span" class="alert alert-success">
**:bulb: Tip:**
If the lab report assignment is of **high priority**, you can enter `add n/Lab report d/23-04-2020 1230 mod/CS2100 p/HIGH`
</div> 

Examples:
* `add n/Lab report d/23-04-2020 1230 mod/CS2100 remind`
* `add n/Lab report d/30-04-2020 1230 mod/CS2100 remind p/LOW`

### Deleting assignments : `delete`

Format: `delete INDEX [MORE_INDEXES]`

You can delete assignments from your assignment list by specifying the assignment `INDEX` as shown in your list.

You can delete **one or more** assignments at a time. Here is an example with steps to follow:

1) To delete assignments with the name "Statistics tutorial" and "Biology lab report" as shown in the figure below, you can simply enter `delete 1 3` into the Command Box as per their indexes that are labelled in the figure.

![DeleteCommand1](images/DeleteCommandDiagram1.png)
*Figure X: User input and location of assignment indexes*

2) You have successfully deleted both assignments from the assignment list, and they are no longer displayed.

3) A "Deleted assignment(s)" message that includes the information of your deleted assignments will be displayed in the Message Box.

![DeleteCommand2](images/DeleteCommandDiagram2.png)
*Figure X: The displayed message and the updated list*

Examples:
* `delete 1`
* `delete 2 3 1`

<div markdown="block" class="alert alert-primary">

 **:clipboard: Pointers to note:**<br>
* At least one index must be **present**. For example, `delete` without any index will not work.
* The indexes **must be found in your assignment list**.
* The indexes **must not be duplicated**. For example, `delete 3 3` will not work.
</div>

### Importing your timetable : `import`

Format: `import url/YOUR_NUSMODS_URL`

You can import your NUSMods timetable data into ProductiveNUS by providing the URL to your NUSMods timetable share
link. Imported lesson information can be found in the `Upcoming tasks`.
 
Follow these steps and try importing your timetable:

1) At your NUSMods timetable website, click on the "Share/Sync" button to obtain your timetable share link URL.

   ![NusModsShareSync](images/NusModsShareSync.PNG)
   *Figure X: The "Share/Sync" at the NUSMods website highlighted in green*

2) The URL obtained will be `YOUR_NUSMODS_URL` to be used in the import command.

3) Added lesson information can be found in your task list.

   ![UserGuideUpcomingTasks](images/UserGuideUpcomingTasks.PNG)
   *Figure X: Added lesson information can be found in the `Upcoming tasks` list highlighted in red*

<div markdown="block" class="alert alert-primary">

 **:clipboard: Pointers to note:**<br>
* Importing a new timetable will override your previous timetable data.
</div>

Example:
* `import url/https://nusmods.com/timetable/sem-1/share?CS2100=TUT:01,LAB:11,LEC:1&CS2101=&CS2103T=LEC:G16&CS2105=TUT:14,LEC:1&EC1301=TUT:S28,LEC:1&IS1103=` 

### Listing assignments : `list`

Format: `list [NUMBER_OF_DAYS]`

You can list all your assignments with `list`. Alternatively, you can type `list` followed by an index `NUMBER_OF_DAYS` to list your assignments with deadlines that fall within the current date and time and `NUMBER_OF_DAYS` later. The index must be a number from 1 to 50.

<div markdown="span" class="alert alert-success">

**:bulb: Tip:**
You can use this `NUMBER_OF_DAYS` index to quickly view assignments that you need to complete soon!
</div>

Here is an example with steps for you to follow: 

1) You can enter `list 3` as shown in the diagram below to list your assignments that are due within 3 days from the current date and time. 

![ListCommand1](images/ListCommandDiagram1.png)
*Figure X: `list 3` inputted by user*

2) If the current date and time is 29/10/2020 1800, all assignments due within this date and time to 01/11/2020 1800 will be displayed in the assignment list. 

3) A message that indicates the number of assignments listed is displayed in the Message Box.

![ListCommand2](images/ListCommandDiagram2.png)
*Figure X: The displayed message and the updated list*

<div markdown="span" class="alert alert-success">

**:bulb: Tip:**
You can use this `NUMBER_OF_DAYS` index to quickly view assignments that you need to complete soon!
</div>

More examples: 
- `list`
- `list 7`
 
<div markdown="block" class="alert alert-primary">

 **:clipboard: Pointers to note:**<br>
* `NUMBER_OF_DAYS` **must be a positive integer** 1, 2, 3, …​
* **Only one** number can be keyed in. For example, `list 1 2` will not work. 
</div>

### Finding assignments : `find`

Format: `find PREFIX/KEYWORD [MORE_KEYWORDS]`

You can find your assignments based on keywords you enter. The types of keywords are the name, module code, deadline and priority of assignments. 

<div markdown="span" class="alert alert-success">

**:bulb: Tip:**
You can find assignments with multiple keywords of the same type to widen your search!
</div>

This is the table of prefixes used:

| Prefix | Syntax | Example |
|-|-|-|
| `n/` | n/NAME_OF_ASSIGNMENT | - `n/Tutorial` |
| `mod/` | mod/MODULE_CODE | - `mod/ST2334` |
| `d/` | d/DATE_OR_TIME_OF_ASSIGNMENT | - `d/24-10-2020 1300` |

<div markdown="block" class="alert alert-primary">

 **:clipboard: Pointers to note for prefixes and keywords:**<br>
* Date keywords must be in the format **dd-MM-yyyy** and time keywords must be in the format **HHmm**.
* For prefix `d/`, date keywords are **irrespective of time** and time keywords are **irrespective of date**. For example, `find d/1300 25-11-2020` finds all assignments due on 25-11-2020, at any time of the day and all assignments due at 1300, on any date.
* Keywords used with prefixes `n/`,`mod/` and `p/` are **case-insensitive**.
</div>

Here is an example with steps to follow:

1) To find assignments from the modules CS2100 and ST2334, you can simply key in `find mod/CS2100 ST2334`. 

![FindCommand1](images/FindCommandDiagram1.png)
*Figure X: `find mod/CS2100 ST2334` inputted by user*

2) Assignments from the modules CS2100 and ST2334 will appear in the assignment list.

3) A message that indicates the number of assignments found is displayed in the Message Box.

![FindCommand2](images/FindCommandDiagram2.png)
*Figure X: The displayed message and the updated list*


<div markdown="block" class="alert alert-primary">

 **:clipboard: Pointers to note:**<br>
* You can only **find assignments with keywords of the same prefix**. For example, `find n/Assignment d/23-10-2020` will not work.
</div>

### Editing your assignment : `edit`

Format: `edit INDEX PREFIX/EDITTED_FIELD [MORE_PREFIX/EDITTED_FIELD]`

You can specify an assignment's index to edit its fields. These fields are the name, module code and deadline of assignment.

<div markdown="span" class="alert alert-success">

**:bulb: Tip:**
You can edit more than one field of an assignment at a time so that you can conveniently amend all spelling errors or mistakes when adding assignments! For example, you can key in `edit 1 n/Tutorial mod/CS2103T` to edit both the name and module code in a single command.
</div>

This is the table of prefixes used:

| Prefix | Syntax | Example |
|-|-|
| `n/` | n/NAME_OF_ASSIGNMENT | - `n/Tutorial` |
| `mod/` | mod/MODULE_CODE | - `mod/ST2334` |
| `d/` | d/DATE_OR_TIME_OF_ASSIGNMENT | - `d/24-10-2020 1300` |

<div markdown="block" class="alert alert-primary">

Here is an example with steps to follow:

1) To edit the name of the assignment with the first index in the assignment list to "Statistics Lab", you can simply key in `edit 1 n/Statistics Lab` in the Command Box. 

![EditCommand1](images/EditCommand1.png)

*Figure X: `edit 1 n/Statistics Lab` inputted by user*

2) The assignment name will be changed to "Statistics Lab".

3) A message that indicates details of the new edited assignment is displayed in the Message Box.

![EditCommand2](images/EditCommand2.png)

*Figure X: The displayed message and the edited assignment*

<div markdown="block" class="alert alert-primary">

 **:clipboard: Pointers to note:**<br>
* **One** `INDEX` and **at least one** `PREFIX/EDITTED_FIELD` must be present. For example, `edit` will not work.
* `DATE_OR_TIME_OF_ASSIGNMENT` has date in the format **dd-MM-yyyy** and time in the format **HHmm** (24 hour).
</div>

### Setting reminders for assignments : `remind`

Format: `remind INDEX [MORE_INDEXES]`

You can set reminders for specific assignments which will be displayed in `Your reminders` (Highlighted in red in the figure below) for your easy referral.

   ![YourReminders](images/YourReminders.png)
   *Figure X: `Your reminders` highlighted in red*

You can use the `INDEX` of the assignment as shown in your assignment list to set reminders for that assignment. 

For example, `remind 1` will set reminders for the first assignment in your assignment list ("Statistics tutorial" as shown in the Figure X) and adds it to `Your reminders` (Figure X).

   ![Remind1](images/Remind1.png)
   *Figure X: Before "Statistics tutorial" is added into `Your reminders`*
      
   ![Remind1InYourReminders](images/Remind1InYourReminders.png)
   *Figure X: "Statistics tutorial" (highlighted in red) is added into `Your reminders`*

You can set reminders for **more than one** assignments at a time as well. Here is an example with steps for you to follow:

1) To set reminders assignments "Essay" and "Assignment 3" (the second and fourth assignment respectively in your assignment list as shown in Figure X), you can simply enter `remind 2 4` into the Command Box.

   ![Remind24](images/Remind24.png)
   *Figure X: Before "Essay" and "Assignment 3" are added into `Your reminders`*

2) You have successfully set reminders for both assignments, and they can now be found under `Your reminders`.

   ![Remind24InYourReminders](images/Remind24InYourReminders.png)
   *Figure X: "Essay" and "Assignment 3" (highlighted in red) are added into `Your reminders`*


<div markdown="block" class="alert alert-primary">
  
**:clipboard: Pointers to note:**<br>
* At least one `INDEX` must be present. For example, `remind` will not work.
* The `INDEX` must be found in your assignment list.

</div>

### Removing reminders for assignments : `unremind`

Format: `unremind INDEX`

You can remove your reminded assignments from `Your reminders` by specifying the `INDEX` of the assignment as shown in `Your reminders`. 

For example, `unremind 1` will remove the first assignment in `Your reminders` ("CS2106 Lab" as shown in the figure below).

   ![Unemind1](images/Unremind1.png)
   *Figure X: Before "Statistics tutorial" (highlighted in red) is removed from `Your reminders`*
      
   ![Unremind1YourReminders](images/Unremind1YourReminders.png)
   *Figure X: "Statistics tutorial" is removed from`Your reminders`*

<div markdown="block" class="alert alert-primary">
  
**:clipboard: Pointers to note:**<br>
* At least one `INDEX` must be present. For example, `unremind` will not work.
* The `INDEX` must be found in `Your reminders`.

</div>

### Setting priority for assignments : `prioritize`

Format: `prioritize INDEX p/PRIORITY`

You can set priority levels for assignments based on their urgency. Assignments tagged with a priority level will be
shown with a coloured priority tag (As shown highlighted in red in the figure below).

   ![UserGuidePriorityTags](images/UserGuidePriorityTags.PNG)
   *Figure X: The priority tags of assignments highlighted in red*

You can use the `INDEX` of the assignment as shown in your assignment list to set priority tag for that assignment.

For example, `prioritize 1 p/LOW` will set a low priority tag for the first assignment in your assignment list.

<div markdown="block" class="alert alert-primary">
  
**:clipboard: Pointers to note:**<br>
* The `INDEX` must be found in your assignment list.
* Priority levels you can use are `LOW`, `MEDIUM` and `HIGH` and they are case-insensitive.
* If the assignment already has a priority tag, this command will replace the previous priority tag with the new one.
</div>

### Removing priority for assignments : `unprioritize`

Format: `unprioritize INDEX`

You can remove a priority tag from an assignment that has a priority tag by specifying the `INDEX` of the assignment
you wish to have the priority tag removed.

For example, `unprioritize 1` will remove the priority tag, if present, of the first assignment in your assignment list.

<div markdown="block" class="alert alert-primary">
  
**:clipboard: Pointers to note:**<br>
* At least one `INDEX` must be present. For example, `unprioritize` will not work.
* The `INDEX` must be found in your assignment list.
</div>

### Marking assignments as done : `done`

Format: `done INDEX [MORE_INDEXES]`

You can keep track of your uncompleted assignments by marking assignments that you've completed as done.

You can mark **one or more** assignments as done by specifiying the assignment(s) `INDEX` as shown in your list. Here is
an example with steps to follow:

1) To mark assignments with the name "Essay" and "Biology lab report" shown in the figure below as done, you can simply enter `done 2 3` into
the command line based on the indexes labelled in the figure.

   ![UserGuideDoneCommandIndex](images/UserGuideDoneCommandIndex.PNG)
   *Figure 10a: `Indexes` of assignments labelled*
   
2) The two assignments will be marked as done and each assignment will have a green tick displayed as labelled in the figure. 

   ![UserGuideDoneCommandTicksAdded](images/UserGuideDoneCommandTicksAdded.PNG)
   *Figure 10b: Added ticks labelled*

3) A message that includes the information of the assignments marked as done will be displayed in the Command Box.

   ![UserGuideDoneCommandSuccess](images/UserGuideDoneCommandSuccess.PNG)
   *Figure 10c: Message shown in Command Box labelled*
   
<div markdown="block" class="alert alert-primary">
 **:clipboard: Pointers to note:**<br>
* At least one index must be present. For example, `done` without any index will not work.
* The indexes **must be found in your assignment list**
* The indexes **must not be duplicated**. For example, `done 4 4` will not work.
</div>


### Marking assignments as not done : `undone`

Format: `undone INDEX`

You can unmark assignments that you have previously marked as done by specifying the assignment `INDEX` as shown in your
 list.

You can unmark `one` done assignment at a time. Here is an example with steps to follow:

1) If you have **accidentally** marked the assignment with the name "Essay" as done,
you can simply enter `undone 2` into the command line based on the index labelled in the figure.

   ![UserGuideUndoneCommandIndex](images/UserGuideUndoneCommandIndex.PNG)
   *Figure 11a: `Index` of assignment labelled*

2) The green tick associated to the assignment is no longer displayed as shown in the diagram below.

   ![UserGuideUndoneCommandTickRemoval](images/UserGuideUndoneCommandTickRemoval.PNG)
   *Figure 11b: Deleted green tick labelled*

3) A message that includes the information of your assignment marked as undone will be displayed in the Command Box.

   ![UserGuideUndoneCommandSuccess](images/UserGuideUndoneCommandSuccess.PNG)
   *Figure 11c: Message shown in Command Box labelled*
   
<div markdown="block" class="alert alert-primary">
 **:clipboard: Pointers to note:**<br>
* Assignments are marked as not done **by default**
* The index must be present. For example, `undone` without any index will not work.
* The index **must be found in your assignment list**
</div>

### Scheduling assignments:

Schedules the assignment identified by the `INDEX` number used in the displayed assignment list.
The suggested schedule with start time and end time will be displayed in the assignment card.
It is guaranteed that the suggested time slot will not class with any of your lessons or the suggested time
slots of other assignments.

Format: `schedule INDEX expected/EXPECTED_HOUR dobefore/BEFORE_DATE BEFORE_TIME`
* Suggests a schedule for the assignment at the specified `INDEX`.
* The index refers to the index number shown in the displayed assignment list.
* The index **must be a positive integer** 1, 2, 3, …​
* The expected number of hours for an assignment **must be a positive integer between `1` and `5`**.

Examples:
* `schedule 1 expected/2 before/23-12-2020 2359`
* `schedule 2 expected/5 before/01-01-2021 0530`

### Removing suggested time for assignments:

Removes the suggested time slot of the assignment identified by the `INDEX` number used in the displayed assignment list.

Format: `unschedule INDEX`

* Removes the suggested time slot of the assignment at the specified `INDEX`.
* The index refers to the index number shown in the displayed assignment list.

### Undoing commands:

Undoes the most recent command.

Format: `undo`

### Clearing all assignments: `clear`

Format: `clear`

You can clear all your existing assignments and lessons at the start of every semester.

<div markdown="span" class="alert alert-success">
**:bulb: Tip:**
If you cleared all your assignments and lessons by accident, use `undo` to retrieve your deleted data.
</div>

### Accessing help: `help`

Format: `help`

If you are new to ProductiveNUS or want to have a quick overview of all the available commands, you can simply enter `help` and a Help Window will appear as shown in the labelled diagram below.

   ![UserGuideHelpCommand](images/UserGuideHelpCommand.PNG)
   *Figure 12: Help Window labelled*

### Exiting the program : `exit`

Format: `exit`

You can use this command to exit the program.

### Saving the data

ProductiveNUS data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action | Format | Examples |
|-|-|-|
| **add** | `add n/NAME_OF_ASSIGNMENT d/DEADLINE_OF_ASSIGNMENT TIME_ASSIGNMENT_IS_DUE mod/MODULE [p/PRIORITY] [remind]` | `add n/Math tutorial d/21-03-2020 1100 mod/ST2334` |
| **delete** | `delete INDEX [MORE_INDEXES]` | `delete 3`<br>`delete 2 3 4` |
| **import** | `import url/NUSMODS_URL` | `import url/https://nusmods.com/timetable/sem-2/share?CS2108=LEC:1` |
| **list** | `list [NUMBER_OF_DAYS]` | `list 2`<br>`list` |
| **find** | `find PREFIX/ KEYWORD [MORE_KEYWORD]` | `find mod/CS2103T CS2100`<br>`find p/HIGH` |
| **remind** | `remind INDEX [MORE_INDEXES]` | `remind 5`<br>`remind 2 4 5` |
| **unremind** | `unremind INDEX` | `unremind 2` |
| **prioritize** | `prioritize INDEX p/PRIORITY` | `prioritize 3 p/HIGH`<br>`prioritize 1 p/LOW` |
| **unprioritize** | `unprioritize INDEX` | `unprioritize 1` |
| **done** | `done INDEX` | `done 4` |
| **undone** | `undone INDEX` | `undone 2` |
| **schedule** | `schedule INDEX expected/EXPECTED_HOUR dobefore/BEFORE_DATE BEFORE_TIME` | `schedule 1 expected/2 before/23-12-2020 2359` |
| **unschedule** | `unschedule INDEX` | `unschedule 1` |
| **undo** | `undo` | `undo` |
| **clear** | `clear` | `clear` |
| **help** | `help` | `help` |
| **exit** | `exit` | `exit` |

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: 
1. Install the app in your other computer and start the app.
2. Notice that a data file named `addressbook.json` is created under the `/data` folder.
3. Close the app in your other computer.
4. Overwrite the newly created data file with the data file from your previous computer.
5. All your existing data has been successfully transferred!

**Q**: I am not free during the time that my assignment has been scheduled. How do I reschedule?<br>
**A**:
You can reschedule by using the schedule command on that assignment again.<br>

1. Type the schedule command into the Command Box with the index of the assignment that you want to reschedule. You can refer to the diagram below.<br>
   ![UserGuideReschedule](images/UserGuideReschedule.PNG)
   *Figure 20a: Index of the assignment to reschedule labelled*
   
<div markdown="span" class="alert alert-success">
**:bulb: Tip:**
You can copy the entire command if you intend to reschedule the assignment more than once. 
You can paste the command into the command box after step 2 and repeat step 1-2 until you have obtained your
 ideal schedule.
</div>

2. Press ENTER to reschedule your assignment and your assignment will be scheduled to another time or day as shown in the diagram below.<br>
   ![UserGuideRescheduleSuccess](images/UserGuideRescheduleSuccess.PNG)
   *Figure 20b: New suggested time for assignment labelled*
<br>

**Q**: I double-clicked the productivenus.jar file but the app is not starting. What should I do?<br>
**A**: <br>
*For Windows Users:*
1. Open the folder where you have downloaded and saved the productivenus.jar file.
2. Copy the file directory of the folder. You can refer to the labelled diagram below.<br>
   ![UserGuideLocateDirectory](images/UserGuideLocateDirectory.PNG)
   *Figure 21a: File directory labelled*
   
3. Open your command prompt. To do so, press the Windows key on your keyboard, type in `cmd` and press ENTER.
4. Your command prompt will start, and it should look something similar to the diagram below.<br>
   ![UserGuideCommandPrompt](images/UserGuideCommandPrompt.PNG)
   *Figure 21b: Interface of Command Prompt*
   
4. In the command prompt, type `cd ` and paste the file directory that you have copied in step 2.
<br>For example, `cd C:\Users\YourUserName\Desktop\ProductiveNUS`.
5. Press ENTER and the file directory will be shown in the command prompt as shown in the diagram below.<br>
   ![UserGuideChangeDirectory](images/UserGuideChangeDirectory.PNG)
   *Figure 21c: After changing file directory in Command Prompt*
   
6. Finally, type `java -jar productivenus.jar` and press ENTER to start the app.
