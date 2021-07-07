# User Guide

## Features 

### Exit
Exiting the program.
### List
Listing all tasks.
### Mark as Done
Marking a task as done.
### ToDo
Adding a task without any date/time attached to it. 
### Deadline
Adding a task that needs to be done before a specific date/time.
### Event
Adding a task that starts at a specific time and ends at a specific time.
### Delete
Deleting a task.
### Find
Locating tasks by name.

## Usage

### `bye` - Exiting the program

Exits the program.

Example of usage: 

`bye`

Expected outcome:

`program terminates`

### `list` - Listing all tasks

Shows a list of all tasks in the task list.

Example of usage: 

`list`

Expected outcome:

`a list of all tasks is displayed`

### `done` - Marking a task as done.

Marks the specified task in the task list as done.

Example of usage: 

`done INDEX`

Marks the task at the specified `INDEX` as done.

Expected outcome:

`task at specified INDEX is marked as done`

### `todo` - Adding a ToDo task

Adds a task without any date/time attached to it

Example of usage: 

`todo DESCRIPTION`

Expected outcome:

`a ToDo task with given description is added to task list`

### `deadline` - Adding a Deadline task

Adds a task that needs to be done before a specific date/time.

Example of usage: 

`deadline DESCRIPTION \by TIME`

Expected outcome:

`a Deadline task with given description and time is added to task list`

### `event` - Adding an Event task

Adds a task that starts at a specific time and ends at a specific time.

Example of usage: 

`event DESCRIPTION \at TIME`

Expected outcome:

`a Event task with given description and time is added to task list`

### `delete` - Deleting a task

Deletes the specified task from the task list.

Example of usage: 

`delete INDEX`

Deletes the task at the specified `INDEX`.

Expected outcome:

`task at specified INDEX is removed`

### `find` - Locating tasks by name

Finds tasks whose descriptions contain any of the given words.

Example of usage: 

`find KEYWORD [MORE_KEYWORDS]`

Expected outcome:

`output tasks with exact-match descriptions, or if there is no exact-match, tasks with partial-match descriptions`