# User Guide

## Introduction
This is a brief user guide for "Hanako's Personal Assistant".

## Features 
1. Set-up a Task
    1. To-do tasks
    2. Deadlines
    3. Events
2. Editing Tasks
    1. Edit name
    2. Edit date
    3. Set task to completed
    4. Delete task
3. List all tasks

## Set-up a Task

### Setting a To-do Task
Allows a user to create a to-do task that does not take in a date. 
When user enters a to-do task, it will be marked as undone.

### Usage

### `todo` - Creates a to-do Task
After entering the command, user will see the task displayed.
Below is an example of this command.

Example of usage: 

`todo Study`

Expected outcome:

`I have added the following task:`

`[T][✘] Study`

`You now have 1 task`

### Setting a Deadline
Allows a user to create a deadline that takes in a date.
When user enters a deadline, it will be marked as undone.

### Usage

### `deadline` - Creates a to-do Task
After entering the command, user will see the task displayed.
Below is an example of this command.

Example of usage: 

`deadline Project /by 2020-04-03`

Expected outcome:

`I have added the following task:`

`[D][✘] Project (by: 03 Apr 2020)`

`You now have 1 task`

### Setting a Event
Allows a user to create a event that takes in a date.
When user enters a deadline, it will be marked as undone.

### Usage

### `event` - Creates a to-do Task
After entering the command, user will see the task displayed.
Below is an example of this command.

Example of usage: 

`event Exam /at 2020-05-09`

Expected outcome:

`I have added the following task:`

`[E][✘] Exam (at: 09 May 2020)`

`You now have 1 task`

## Editing Tasks

### Edit name of Task
Allows a user to edit the name of a task according to the number in the list.

### Usage

### `edit name` - Edits the name of the Task
After entering the command, user will see the new name of the task.
Below is an example of this command.

Example of usage: 

`edit name 1 Study Science`

Expected outcome:

`The task Study has been changed to Study Science`

`[T][✘] Study Science`

### Edit date of Task
Allows a user to edit the date of a deadline/event according to the number in the list.

### Usage

### `edit date` - Edits the name of the Task
After entering the command, user will see the new date for the task.
Below is an example of this command.

Example of usage: 

`edit date 1 2020-05-01`

Expected outcome:

`The task date has been changed to 2020-05-01`

`[D][✘] Project (by: 01 May 2020)`

### Set Task to completed
Allows a user to set a task to completed.

### Usage

### `done` - Edits the name of the Task
After entering the command, user will see the status of the task changed to completed, 
as represented by a tick.
Below is an example of this command.

Example of usage: 

`done 1`

Expected outcome:

`I shall mark task 1 as completed`

`[T][✔] Study`

### Delete a task
Allows a user to delete a task.

### Usage

### `done` - Edits the name of the Task
After entering the command, the task will be removed from the list
Below is an example of this command.

Example of usage: 

`delete 1`

Expected outcome:

`I shall delete task: Study`

## List all Tasks

### List all Tasks
Allows a user to view the list of task that has been entered. 

### Usage

### `list` - Edits the name of the Task
After entering the command, user will see the list of tasks.
Below is an example of this command.

Example of usage: 

`list`

Expected outcome:

`Here are the list of task:`

`1. [T][✘] Read`

`2. [D][✘] Project (by: 01 May 2020)`

`3. [E][✘] Exam (at: 09 May 2020)`

`You have 3 task`