# User Guide

## 1. Introduction
Duke is an application to manage all your tasks. 
It contains a GUI that allows you to easily input all your task and retrieve them as a list.

You can get the latest jar [here](https://github.com/foochifa/duke/releases)

## 2. Features 
****
Command Formats:

* Words in UPPERCASE denotes user-supplied parameters. 
For example: `todo TASK`, `TASK` is a parameter to be supplied by the user.
* All commands are not case-sensitive. For example: `list`, `LiSt`, `LIST` are all `list` commands.

****
### 2.1. View All Commands: `help`

Show all commands and their format in the GUI.

Format: `help`

### 2.2. List Tasks: `list`

List out all the tasks currently inside the task list.

Format: `list`

### 2.3. Add Task: `todo` `deadline` `event`

Adds a new task. There are three different type of tasks:

1. To-do tasks `todo`
1. Tasks with a deadline `deadline`
1. Tasks held at a certain date `event`

#### 2.3.a. To-do Task: `todo`

Format: `todo TASK`

Example:
* `todo borrow book`

#### 2.3.b. Deadline Task: `deadline`

Format: `deadline TASK /by DD MM YY`

Example:
* `deadline return book /by 18 02 20` adds a deadline task, `return book`, to be done by `18th Feb 2020`

#### 2.3.c. Event Task: `event`

Format: `event TASK /at DD MM YY`

Example:
* `event project meeting /at 18 02 20` adds an event task, `project meeting`, to be done by `18th Feb 2020`

### 2.4. Mark Task as Done: `done`

Marks a task in the list as done, according to the index given.

Format: `done INDEX`

Example:
* `done 1`
 
### 2.5. Delete a Task: `delete`

Deletes a task in the list, according to the index given.

Format: `delete INDEX`

Example:
* `delete 2`

### 2.6. Find Tasks Based on Keywords: `find`

Finds all the task that contain the keywords provided. 
Feature can take in more than one keyword separated by whitespace.
Duke will return a list of all tasks found that contains one or more keywords.

Format: `find KEYWORDS`

Examples:
* `find book` 
returns all tasks that contains `book`
* `find book meeting` returns all tasks that contains `book` or `meeting` 

### 2.7. Tasks on a Date: `schedule`

Shows the schedule of task on a user-specified date.

Format: `schedule DD MM YY`

Example:
* `schedule 18 02 20` returns all tasks on `18th Feb 2020`

### 2.8. Exit Duke: `bye`

Exits duke program.

Format: `bye`

## 3. Command Summary

> All commands are not case-sensitive.
- Help: `help`
- List: `list`
- Add: 
  - todo: `todo TASK`
  - deadline: `deadline TASK /by DD MM YY`
  - event: `event TASK /at DD MM YY` 
- Mark as Done: `done INDEX`
- Delete: `delete INDEX`
- Find: `find KEYWORDS`
- Schedule on a Date: `schedule DD MM YY`
- Exit: `bye`
