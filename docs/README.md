# User Guide

## Features 

### Feature 1
Add a new `Deadline` task.

## Usage

### `deadline` - Describe action

Add a new `Deadline` task with the specified description and deadline date and time.
The task will be marked as 'undone' by default.
You cannot add a task whose name and type combination already existed before,
e.g. adding a `Deadline` named `assignement` followed by a `Todo` named `assignement` is allowed,
but not a `Deadline` named `assignement` followed by another `Deadline` named `assignement`
(even though they have different deadline date and time).

Example of usage: 

`deadline CS2102 Assignment /by 2020-02-21 1800`

Expected outcome:
The application successfully added a `Deadline` with description `Cs2102 Assignment`
and deadline date and time `21 Feb 2020 18:00`

`Got it dude! I've added this task:`
`[D][✘] CS2102 Assignment (by: 21 Feb 2020 18:00)`
`Now you have 5 task(s) in the list.`

### Feature 2
Add a new `Event` task.

## Usage

### `event` - Describe action

Add a new `Event` task with the specified description and time.
The task will be marked as 'undone' by default.
Similar to `Deadline`, you cannot add a task whose name and type combination already existed before.

Example of usage: 

`event project meeting /at 2020-02-21 1800`

Expected outcome:
The application successfully added an `Event` with description `project meeting`
and time `21 Feb 2020 18:00`

`Got it dude! I've added this task:`
`[E][✘] project meeting (at: 21 Feb 2020 18:00)`
`Now you have 5 task(s) in the list.`

### Feature 3
Add a new `Todo` task.

## Usage

### `todo` - Describe action

Add a new `Todo` task with the specified description.
The item will be marked as 'undone' by default.
Similar to `Deadline` and `Event`, you cannot add a task whose name and type combination already existed before.

Example of usage: 

`todo ST2132 Homework`

Expected outcome:
The application successfully added a `Todo` with description `ST2132 Homework`

`Got it dude! I've added this task:`
`[T][✘] ST2132 Homework`
`Now you have 5 task(s) in the list.`

### Feature 4
Delete a task.

## Usage

### `delete` - Describe action

Delete a `Task` at the specified index in the list. The index must be a positive integer and must exists within the range of the list length.

Example of usage:

`delete 2`

Expected outcome: 
The application successfully deleted a `Task` at index 2 of the current list.

`Got it dude! I've deleted this task:`
`[E][✘] project meeting (at: 21 Feb 2020 18:00)`
`Now you have 4 task(s) in the list.`


### Feature 5
Mark a task as 'done'.

## Usage

### `done` - Describe action

Mark a `Task` at the specified index in the list as `done`. The index must be a positive integer and must exists within the range of the list length.

Example of usage:

`done 2`

Expected outcome: 
The application successfully marked the `Task` at index 2 of the current list as `done`.

`Got it dude! I've marked this task as done:`
`[T][✓] ST2132 Homework`

### Feature 6
Find task(s) containing keyword.

## Usage

### `find` - Describe action

Find all tasks whose description contains the specified keyword and return a list of those tasks.
(The list could be empty if there is no task that matchest the keyword).

Example of usage:

`find Homework`

Expected outcome:
The application found some tasks whose description contains the keyword `Homework`, and return a list of those tasks.

`Okay dude here are what I found:`
`2. [T][✓] ST2132 Homework`

If no task contains the keyword, this message will be returned instead:
`Sorry dude but I found nothing :(`

### Feature 6
List all tasks.

## Usage

### `list` - Describe action

List out all `Task` in the list.

Example of usage:

`list`

Expected outcome:
The application listed out all `Task` in the list.

`Here's your list of tasks dude:`
`1. [D][✘] CS2102 Assignment (by: 21 Feb 2020 18:00)`
`2. [T][✓] ST2132 Homework`

### Feature 7
Exit the program.

## Usage

### `bye` - Describe action

Exits the program.