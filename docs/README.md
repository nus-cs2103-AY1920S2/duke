# User Guide

## 1. Introduction

Duke is for those who prefer to use a desktop app for managing their tasks and events. More importantly, Duke is optimized for those who prefer to work with a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Duke can help keep track and organise your tasks and events faster than a traditional GUI application!

## 2. Quick Start

1. Ensure you have Java `11` or above installed in your Computer
2. Download the latest `duke-0.1.3.jar`
3. Copy the `jar` file to the folder you want to use as the home folder for your Duke Task Manager
4. Double click the `jar` file to start the app. The GUI should appear in a few seconds.

![Image of GUI](https://github.com/hhjoel/duke/blob/master/docs/Ui.PNG?raw=true)

5. Type your commands in the command box and press `Enter` to execute it.

## 3. Features

Command Format

* Words in `UPPER_CASE` are the parameters to be supplied by the user e.g. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be added as `todo run`.

### 3.1. Display all Tasks: __`list`__

Lists all current tasks and events
Format: `list`

### 3.2. Display, but sorted alphabetically: __`listname`__

Lists all current tasks and events sorted alphabetically
Format: `listname`

### 3.3. Display, but sorted alphabetically: __`listdate`__

Lists all current tasks and events sorted according to date
Format: `listdate`

* Sorts ToDo tasks at the back

### 3.4. Add a ToDo task: __`todo`__

Adds a task without a deadline
Format: `todo DESCRIPTION`

Example:

* `todo practice piano`
Adds a "practice piano" task to the list

### 3.5. Add a Deadline task: __`deadline`__

Adds a task with a deadline at the specified yyyy-mmdd
Format: `deadline DESCRIPTION /yyyy-mm-dd`

Example:

* `deadline finish cs2103 IP /2020-05-05`
Adds a "finish cs2103 IP" task with deadline of 5th May 2020 to the list

### 3.6. Add an Event: __`event`__

Adds an event at a specified date and time
Format: `event DESCRIPTION /yyyy-mm-dd`

Example:

* `event elysia's wedding /2020-07-07`
Adds an "elysia's wedding" event at 7th July to the list

### 3.7. Mark a Task as done: __`done`__

Marks a task at `INDEX` as done
Format: `done INDEX`

Example:

* `done 3`
Marks the task at index 3 as done

### 3.8. Delete a Task: __`delete`__

Deletes a task with index `INDEX`
Format: `delete INDEX`

Example:

* `delete 7`
Deletes the task at index 7 from the list

### 3.9. Find all Tasks with a keyword: __`find`__

Finds all Tasks that contains the keyword `WORD`
Format: `find WORD`

* `WORD` is case sensitive

Example:

* `find cs2103`
Finds all tasks with "cs2103" in its description

### 3.10. Exit the application: __`bye`__

Exits the application
Format: `bye`

## 4. FAQ

__Q__: How do I transfer my data to another Computer?
__A__: Your data will be stored in a file called `database.txt` in your home folder (the same folder that stores the `duke-0.1.3.jar` file). Simply transfer the entire folder to another Computer to ensure your data in preserved!

## 5. Command Summary

* `list`: lists all tasks
* `listname`: lists all tasks sorted alphabetically by name
* `listdate`: lists all tasks sorted by date (if applicable)
* `todo DESCRIPTION`: adds a `ToDo` task
* `deadline DESCRIPTION /yyyy-mm-dd`: adds a `Deadline` task
* `event DESCRIPTION /yyyy-mm-dd`: adds an `Event`
* `done INDEX`: mark a task at `INDEX` (as in `list`) as done
* `delete INDEX`: delete the task at `INDEX` (as in `list`) as done
* `find WORD`: finds all tasks that contains `WORD`
* `bye`: exits the application

