# User Guide

## 1. Introduction
This application serves as a personal task manager for users who prefer typing.

## 2. Quick Start
1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest version of the Duke application (duke-0.2.1.jar) here.
3. Copy the file to the folder you want to use as the home folder for your Duke.
4. Double-click the file to start the app. The GUI should appear in a few seconds.  
Alternatively, you can open Terminal, `cd` into the home folder of Duke, and run `java -jar duke-0.2.1.jar`

## 3. Features

### 3.1 `help` - Opening Guide
Displays all supported commands and their usage.  
Format: `help`

### 3.2 `list` - Listing all saved tasks
Displays all saved tasks in the application.  
Format: `list`

### 3.3 `todo` - Adding a new to-do
Adds a new `Todo` task with the specified description.  
The task will be marked as '✗' (undone) by default.  
Format: `todo DESCRIPTION`  

Example: `todo ST2132 Homework`  

Expected outcome:  
The application successfully adds a `Todo` with description `ST2132 Homework`
`Got it dude! I've added this task:`  
`[T][✘] ST2132 Homework`  
`Now you have 5 task(s) in the list.`  

Remark:  Tasks with the same `description` will be considered as duplicate and will not be allowed.  

### 3.4 `event` - Adding a new event
Adds a new `Event` task with the specified description and time.
The task will be marked as '✗' (undone) by default.  
Format: `event DESCRIPTION /at YYYY-MM-DD HHMM`

Example: `event project meeting /at 2020-02-21 1800`

Expected outcome:  
The application successfully adds an `Event` with description `project meeting`
and time `21 Feb 2020 18:00`  
`Got it dude! I've added this task:`  
`[E][✘] project meeting (at: 21 Feb 2020 18:00)`  
`Now you have 5 task(s) in the list.`  

### 3.5 `deadline` - Add a new deadline.
Adds a new `Deadline` task with the specified description and deadline date and time.
The task will be marked as '✗' (undone) by default.  

Format: `deadline DESCRIPTION /by YYYY-MM-DD HHMM`  

Example: `deadline CS2102 Assignment /by 2020-02-21 1800`  

Expected outcome:
The application successfully added a `Deadline` with description `Cs2102 Assignment`
and deadline date and time `21 Feb 2020 18:00`  
`Got it dude! I've added this task:`  
`[D][✘] CS2102 Assignment (by: 21 Feb 2020 18:00)`  
`Now you have 5 task(s) in the list.`

### 3.6 `delete` - Deleting a task
Deletes a `Task` at the specified index in the full task list.  
The index must be a positive integer and must exists within the range of the list length.

Format: `delete INDEX`  

Example: `delete 2`

Expected outcome: 
The application successfully deleted a `Task` at index 2 of the current list.
`Got it dude! I've deleted this task:`  
`[E][✘] project meeting (at: 21 Feb 2020 18:00)`  
`Now you have 4 task(s) in the list.`


### 3.7 `done` - Marking a task as done
Marks a `Task` at the specified index in the list as `done`.  
The index must be a positive integer and must exists within the range of the list length.

Format: `done INDEX`  

Example of usage: `done 2`

Expected outcome: 
The application successfully marked the `Task` at index 2 of the current list as `done`.  
`Got it dude! I've marked this task as done:`  
`[T][✓] ST2132 Homework`

### 3.8 `find` - Finding task
Find all tasks whose description contains the specified keyword and return a list of those tasks.
(The list could be empty if there is no task that matches the keyword).

Format: `find [KEYWORD]`

Example: `find Homework`

Expected outcome:
The application found some tasks whose description contains the keyword `Homework`, and return a list of those tasks.  
`Okay dude here are what I found:`  
`2. [T][✓] ST2132 Homework`

If no task contains the keyword, this message will be returned instead:  
`Sorry dude but I found nothing :(`

### 3.9 Saving
There is no need to save the data, as they are auto-saved.

### 3.10 `bye` - Exiting
Exits the program.