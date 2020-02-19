# User Guide

## Features 

### Feature 1 
Add todo, deadline and event tasks to list.
### Feature 2
Mark tasks as done.
### Feature 3
Delete tasks from list.
### Feature 4
Show list.
### Feature 5
Find tasks from list.
### Feature 6
Undo last action.

## Usage

### `todo` - Adding a todo task to list
Adds a todo task to the list. List will now contain newly added todo task.

Example of usage: 

`todo read`

Expected outcome:

Adds todo read task to the list.

### `deadline` - Adding a deadline task to list
Adds a deadline task to the list. List will now contain newly added deadline task.

Example of usage: 

`deadline read /by 2019-10-05 1800`

Expected outcome:

Adds deadline read task to list with date 05 Oct 2019 and time 6pm.

### `event` - Adding a deadline task to list
Adds an event task to the list. List will now contain newly added event task.

Example of usage: 

`event read /at school`

Expected outcome:

Adds event read task to list with location school.

### `done` - Sets a task to done
Sets a task in list to done (changes symbol from cross to tick for task specified).

Example of usage: 

`done 2`

Expected outcome:

Sets second task to done.

### `delete` - Deletes a task 
Deletes a task from the list.

Example of usage: 

`delete 2`

Expected outcome:

Deletes second task from list.

### `list` - Shows list of tasks
Shows list of tasks and their attributes(type, done status and description).

Example of usage: 

`list`

Expected outcome:

Prints list of added tasks.

### `find` - Find tasks in the list
Finds tasks in the list that contain the keyword specified and lists them out.

Example of usage: 

`find read`

Expected outcome:

Prints a list of tasks containing the keyword read.

### `undo` - Undoes the last command
Undoes the last command input by the user.

Example of usage: 

`undo`

Expected outcome:

If the last command was to add a task to list, undo will remove the task from the list.

