# Duke - User Guide

## 1. Introduction
Duke is a task management application for students and workers to handle tasks efficiently. It includes features for task storage, event ranking, deadline reminding, and searching. Duke is designed for users whose workflow is primarily keyboard-based. 

## 2. Using the Guide
Welcome to Duke User Guide! This User Guide provides detailed documentation of Duke's features and commands. together with a quick way to set up Duke.

Here are the conventions this User Guide follows:

- `mark-up` text represents commands or arguments. For example, `undo`.

- > ⚠️ **WARNING** This box represents a warning.

- > ℹ️ **INFO** This box represents additional information.

## 3. Quick Start
1. Ensure Java 11 has been installed on your computer. 
3. Download the latest `CS2103_duke.jar` [here](https://github.com/WANG-Yuchen-Alice/duke) to the local home folder.
4. Run the app in the command line by entering `java -jar PATH_OF_DUKE`. The Graphic User Interface (GUI) should appear in a few seconds.
![](https://i.imgur.com/dQ1YPL3.png)

5. Type the command in the command box and press Enter or the button `Send` to execute it. Here are a few examples you can try. 

- `todo study`: adds a simple task called _Study_ to the task list.
- `list`: shows the current task list.
- `done 1`: marks the first task in the list as done.
- `event group meeting /at 6pm `: adds an event called `group meeting` with time stamp `6pm` to the task list.
- `deadline submit CS2103 quiz /by 2020-02-20` : adds an upcoming deadline called `submit CS2103 quiz` with the deadline of `20 February 2` to the task list.
- `undo`: revokes the last command.
- `bye`: exits.
6. Refer to **Section 6. Basic Commands** for more details.

## 4. Duke Overview
This section provides an overview of Duke.

### 4.1 Tasks
Duke supports three types of tasks:  **TODO**, **EVENT**, **DEADLINE**.

Here is an overview of the entities. 

- **Todo Task** is the most basic task entity in Duke. It only has a task name. Some examples are `read books` and `return books`.
- **Event Task** is a more complex task entity with an extra field of `time` specifying the time a task takes place.
- **Deadline Task** is similar to **Event Task**, with the `time` field changed to a `deadline`.

### 4.2 Feature Hightlights

Duke allows you to add in your tasks, see the current task list, mark the tasks, rank the events, and search for tasks easily.

## 5. Command Format
This section specifies the formats you need to follow when entering a command.

### 5.1 General Format
- The first word in a user command represents the command keyword. It can be typed in both capitalized, non-capitalized, mixed letters.
- Parameters / Fields in a user command are usually divided by an empty space `" "`.

### 5.2 Date and Time
Duke supports a LocalDate format transfer when users add in a `deadline` task. Date entity in format of `yyyy-mm-dd` will be automatically transformed to `MMM dd yyyy`. In other cases, Duke will keep the input format of date and time.

## 6. Basic Commands
This section introduces the basic and most frequently used commands to get you started.

### 6.1 Add a Task

The general command to add a new task is
`[TYPE] [NAME] (optional) [TIME]`
`TYPE` refers to the task entity name introduced in **Section 4.1**.
`NAME` refers to the user-defined task name.
`TIME` is the optional field representing the time stamp of a task.

Examples: 
-  `todo go for CS2103 lecture`: adds a simple task called `go for CS2103 lecture` to the task list.
- `event group meeting /at 6pm `: adds an event called `group meeting` with time stamp `6pm` to the task list.
- `deadline submit CS2103 quiz /by 2020-02-20` : adds an upcoming deadline called `submit CS2103 quiz` with the deadline of `20 February 2` to the task list.

> ℹ️ **INFO**
> Duke will not recoganize the time stamp added to a Todo-type task. A time field is only supported by Event and Deadline-type tasks.

> ⚠️ **WARNING**
> `/at` and `/by` keywords are compulsory for Event and deadline type-tasks. It is also required that these keywords are folloed by a non-empty string representing the time.

### 6.4 Display the Task List

To view the current task list, the command is `list`.

> ℹ️ **INFO**
> Duke keeps a file of Task List in the backend database, so users can view previous items as long as they have not removed them manually.

> ℹ️ **INFO** 
> The task list follows the chronological order of the tasks being added. Newly added task is appended to the task list at the back.

### 6.3 Mark a Task as Done

To mark a Task as done, type `done [INDEX]` where `INDEX` is the index of the corresponding task in the task list.
If the chosen task has already been marked as done before, this command will be ignored.

### 6.4 Remove a Task

Command: `delete [INDEX]`
Same as the previous command, `INDEX` refers to the task index in the list.

> ⚠️ **WARNING**
> A Task will be deleted regardless of whether it has been done yet.

> ⚠️ **WARNING**
> The indices of the tasks are not fixed. For example, after a task is deleted, the following tasks will have their indices reduced by 1.

> ℹ️ **INFO** 
> If the specified index is invalid (out of the range of the task list), Duke will ignore the command.

### 6.5 Undo a Command

To undo the last executed command, enter `undo`. Users can call `undo` for any times. The commands will be revoked according to the 
reverse chronological order as they were added. `undo` will be ignored when no commands 
in the history can be undone.

> ℹ️ **INFO** 
> Only commands that are undoable will be counted. For example, while `done 1` is undoable (Task 1 will be marked back as undone), `list` is not undoable.
> In executing undo, the last undoable command will be revoked and other commands will be skipped.

### 6.6 See Remainders

Command: `remainders (optional)[KEYWORD]`

The basic command is `remainders`, which allows users to see all the tasks which are undone 
according to the order they are stored in the task list.

The `KEYWORD` field can be `todo`, `event`, or `deadline` which will further narrowed down the
scope of undone tasks being displayed to a specific task type. The `KEYWORD` field is optional.

### 6.7 Search 

To search for a specific keyword string in the task list, the command is: `find [KEY]` where
`KEY` is the string that users want to search for. 

For example, `find books` will return a list of tasks which contain the keyword `books`.

> ⚠️ **WARNING**
> The `[KEY]` field is necessary. Leaving it empty will trigger an `invalid comman` warning.

### 6.8 Exit

Enter `Bye` will exit Duke within 3 seconds. The task file will be preserved at the backend.
> ℹ️ **INFO** 
> To exit, users can enter any commands starting with word `bye`.


## 7. FAQ
**Q:** How do I transfer my data to another computer?

**A:** Install Duke in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Duke folder.

## 8. Latest Version
Duke v0.2





