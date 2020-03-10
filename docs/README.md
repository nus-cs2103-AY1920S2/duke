# User Guide

## Features 


### Create Tasks
Create 3 types of task (Todo, Deadline, Event) inside duke.
The 3 types of task is created using command `todo` `deadline` `event` respectively.

### Task Management
List all the task created using `list`, delete tasks using `delete`.

### Find Tasks
Search for tasks with specific name with `find` command.

### Gather statistics
Know the number of tasks due by `stats` command.

### Undo changes
Undo your last changes by `undo` command.

## Usage


### `deadline` - create a Deadline Task

Create Deadline Tasks that keep track of when they are due.

Example of usage: 

`deadline this project /by midnight tonight`

Expected outcome:

`Got it. I've added this task:  
[D][✗] this project (by: midnight tonight)  
Now you have 2 task(s) in the list.`

### `event` - create a Event Task

Create Event Tasks that keep track of when events are happening.

Example of usage: 

`event lou hei /at my office`

Expected outcome:

`Got it. I've added this task:  
 	[E][✗] lou hei (by: my office)  
 Now you have 3 task(s) in the list.`
 
### `todo` - create a Todo Task
 
Create Todo Tasks that keep track of general things to do.

Example of usage: 

`todo read book`

Expected outcome:

`Got it. I've added this task:  
[T][✗] read book  
Now you have 1 task(s) in the list.`
 
### `delete` - delete a task

Delete a Task that is no longer needed.

Example of usage: 

`delete 3`

Expected outcome:

`Noted. I've removed this task:  
3.[E][✗] lou hei (at: my office)  
Now you have 3 task(s) in the list.`

### `done` - mark a task as done

Mark a Task that has been completed, as done.

Example of usage: 

`done 2`

Expected outcome:

`Nice! I've marked this task as done:  
 	[✓] this project`
 	

### `find` - find tasks

Find Tasks that correspond to the keyword(s) given.

Example of usage: 

`find book`

Expected outcome:

`Here are the matching task(s) in your list:  
 	1. [T][✗] read book  
 	2. [T][✗] return book`
 	
### `list` - list all the tasks

List all Tasks that have been created (if any).

Example of usage: 

`list`

Expected outcome:

`Here are the task(s) in your list:  
 1.[T][✗] read book  
 2.[D][✓] this project (by: midnight tonight)  
 3.[T][✗] return book`
 
### `stats` - get statistics about the current tasks

Get the number of tasks due..

Example of usage: 

`stats`

Expected outcome:

`No. of tasks due: 3`

### `undo` - undo the latest changes to Duke

go back to the last state of Duke before the latest change

Example of usage: 

`undo`

Expected outcome:

`Yay! I've nullified your last command!`