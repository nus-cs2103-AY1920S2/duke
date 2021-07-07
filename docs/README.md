# User Guide

## Features 

### Feature 1 - `todo`, `event`, `deadline`
Adds a new task

### Feature 2 - `delete`
Deletes an existing task

### Feature 3 - `done`
Mark a task as done

### Feature 4 - `find`
Find a task by searching keyword

### Feature 5 - `reminders`
Remind users of the upcoming deadlines

### Feature 6 - `list`
Shows the task list

## Usage

### `todo` - Adds a new todo task

Adds a new todo task with no date required to be specified.

Example of usage: 

`todo buy bread`

Expected outcome:

`Got it. I've added this task:`
`[T][✘] todo buy bread`

### `event` - Adds a new event task

Adds a new event task with event date and duration.

Example of usage: 

`event birthday party (at: 2020-03-05 2-4pm)`

Expected outcome:

`Got it. I've added this task:`
`[E][✘] event birthday party (at: Mar 05 2020 2-4pm)`

### `deadline` - Adds a new deadline task

Adds a new deadline task with the deadline and time.

Example of usage: 

`deadline assignment 2 (by: 2020-02-20 2pm)`

Expected outcome:

`Got it. I've added this task:`
`[D][✘] deadline assignment 2 (by: 2020-02-20 2pm)`

### `delete` - Deletes a task

Deletes a task in the task list.

Example of usage: 

`delete 2`

Expected outcome:

`Noted. I've removed this task:`
`[E][✘] event birthday party (at: Mar 05 2020 2-4pm)`

### `done` - Mark a task as done

Marks a task as done and changing the cross to tick symbol.

Example of usage: 

`done 1`

Expected outcome:

`Noted. I've removed this task:`
`[T][✓]] todo buy bread`

### `find` - Finds a task by searching keyword

Finds a task by searching keyword and returns the matching tasks in the list.

Example of usage: 

`find assignment`

Expected outcome:

`Here are the matching tasks in your list:`
`[D][✘] deadline assignment 2 (by: 2020-02-20 2pm)`

### `reminders` - Reminds users of the upcoming deadlines

Shows the list of deadlines that have yet to be completed.

Example of usage: 

`reminders`

Expected outcome:

`Here are the upcoming deadlines in your list:`
`[D][✘] deadline assignment 2 (by: 2020-02-20 2pm)`

### `list` - Prints the entire task list

Shows all the tasks in the task list.

Example of usage: 

`list`

Expected outcome:

`Here are the tasks in your list:`
`1. [T][✓]] todo buy bread`
`[D][✘] deadline assignment 2 (by: 2020-02-20 2pm)`




