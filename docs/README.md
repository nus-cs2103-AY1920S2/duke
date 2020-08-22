## Batman's Alfred
# User Guide

## Features 
   
### Adding a task

Adds a task of type (todo/deadline/event) to your task list.

### Deleting a task

Deletes the task at given index in the task list.

### Marking a task as done

Marks the task at given index as done.

### Undoing the last command

Undoes the previous command which edited the task list (add, delete, done).

### Finding a task

Finds task in the task list containing the given key.

## Usage

### `todo (task details)` - Adds a new todo.

Adds a new task of type, todo, into the task list.

Example of usage: 

`todo Watch 'The Witcher' on Netflix`

Expected outcome:

`Noted. The following task has been added:` 
<br>
`[T][X] Watch 'The Witcher' on Netflix`
<br> 
`There are currently 1 item(s) in the list`
 
### `deadline(task details) /by YYYY-MM-DD HHmm` - Adds a new deadline.
 
Adds a new type of task, deadline, to the task list.
 
Example of usage: 

`deadline CS2105 Assignment1 /by 2020-03-01 2359`

Expected outcome:

`Noted. The following task has been added:`
<br>
`[D][X] CS2105 Assignment 1 (by: 1 Mar 2020 2359)`
<br>
`There are currently 2 item(s) in the list` 

### `event(task details) /at YYYY-MM-DD HHmm` - Adds a new event.
 
Adds a new type of task, event, to the task list.
 
Example of usage: 

`event CS2101 presentation /at 2020-02-20 1200`

Expected outcome:

`Noted. The following task has been added:`
<br>
`[E][X] CS2101 Presentation (at: 20 Feb 2020 1200)`
<br>
`There are currently 3 item(s) in the list`

### `list` - Displays all tasks.

Displays all the tasks in the task list.

Examples of usage:

`list`

Expected outcome:

`There are currently 3 items in your list:`
<br>
`[T][X] Watch 'The Witcher' on Netflix`
<br>
`[D][X] CS2105 Assignment 1 (by: 1 Mar 2020 2359)`
<br>
`[E][X] CS2101 Presentation (at: 20 Feb 2020 1200)`
 
### `done` - Marks a task as complete
 
 Marks the task at the given index as done.
 
 Examples of usage:
 
 `done 2`
 
 Expected outcome:
 
 `Well Done! The task: `
 <br>
 `[D][X] CS2105 Assignment (by: 1 Mar 2020 2359)`
 <br> 
 `has been marked as done.`
 
### `find (keyword)` - Searches for tasks with given key
 
 Returns list of tasks which contains given key in their task description.
 
 Example of usage:
 
 `find CS2101`
 
 Expected outcome:
 `The matching items in your list are:` 
 `[E][X] CS2101 Presentation (at: 20 Feb 2020 1200)`
 
### `delete` - Deletes a task
 
 Removes the task at the given index from the task list.
 
 Examples of usage:
 
 `delete 2`
 
 Expected outcome:
 
 `Noted. Task 2 has been removed:`
  <br>
  `[D][X] CS2105 Assignment (by: 1 Mar 2020 2359)`
  <br>
   `There are currently 2 task(s) in the list.`
  
### `undo` - Undoes a previous command
  
  Undoes the previous command by the user that changed the task list or its items.
  
  Examples of usage:
  
  `undo`
  
  Expected outcome:
  
  `Your previous command:`
   <br>
   `Noted.Task 2 has been removed:` 
   `[D][X] CS2105 Assignment (by: 1 Mar 2020 2359)`
   <br>
   `There are currently 2 task(s) in the list.`
   <br>
   `has been undone.`
  
### `bye` - Exits the program
  
  Exits the program.
  
  Example of usage:
  
  `bye`
  
  Expected outcome:
  
  `Goodbye. I hope I was useful. See you again.`