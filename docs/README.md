# User Guide

## 1. Introduction

Duke is for those who prefer to use a desktop app for managing their tasks and events. More importantly, Duke is optimized for those who prefer to work with a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Duke can help keep track and organise your tasks and events faster than a traditional GUI application!

## 2. Features 

### 2.1. Display all tasks and events: __`list`__

Lists all current tasks and events
Format: `list`

### 2.2. Display, but sorted alphabetically: __`listname`__

Lists all current tasks and events sorted alphabetically
Format: `listname`

### 2.3. Display, but sorted alphabetically: __`listdate`__

Lists all current tasks and events sorted according to date
Format: `listdate`

* Sorts ToDo tasks at the back


### 2.4. Add a ToDo task: __`todo`__

Adds a task without a deadline
Format: `todo DESCRIPTION`

### 2.5. Add a Deadline task: __`deadline`__

Adds a task with a deadline at the specified yyyy-mmdd
Format: `deadline DESCRIPTION /yyyy-mm-dd`

### 2.6. Add an event: __`event`__

Adds an event at a specified date and time
Format: `event DESCRIPTION /yyyy-mm-dd`

### 2.7. Mark a task as done: __`done`__

Marks a task at `INDEX` as done
Format: `done INDEX`

### 2.8. Delete a task: __`delete`__

Deletes a task with index `INDEX`
Format: `delete INDEX`

### 2.9. Find all tasks with a keyword: __`find`__

Finds all tasks that contains the keyword `WORD`
Format: `find WORD`

### 2.10. Exit the application: __`bye`__

Exits the application
Format: `bye`

## 3. Command Summary

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

