# User Guide

## Features 

### Check off your tasks
Duke provides the functionality of a to-do list with 3 built-in task types: Todos, Deadlines and Events.
Once you are done with a task, you can mark it as complete and Duke will remember its completion status.

### Search for related tasks
If your task list is simply too long, Duke allows you to streamline your search for related tasks by keyword.

### Reschedule tasks
A deadline changed, or you made a typo? Fret not! Duke lets you reschedule your tasks with one easy command.

## Usage

### `Keyword` - todo

Create a Todo task with a given description.

Example of usage: 

`todo 2103 IP`

Expected outcome:

`Got it. I've added this task:`<br/>
`[T][✘] 2103 IP`<br/>
`Now you have x tasks in the list.`

### `Keyword` - deadline

Create a Deadline task with a given description as well as due date.

Example of usage: 

`Usage: deadline 2103 IP submission /by 24-02-2020`

Expected outcome:

`Got it. I've added this task:`<br/>
`[D][✘] 2103 IP submission (by: Feb 24 2020, 00:00)`<br/>
`Now you have 2 tasks in the list.`

### `Keyword` - event

Create an Event task with a given description, start and end time.

Example of usage: 

`Usage: event 2103 tutorial /at 19/02/2020 12:00 to 19/02/2020 13:00`

Expected outcome:

`Got it. I've added this task:`<br/>
`[E][✘] 2103 tutorial (at: Feb 19 2020, 12:00 to Feb 19 2020, 13:00)`<br/>
`Now you have 3 tasks in the list.`

### `Keyword` - done

Mark a task as complete.

Example of usage: 

`done 1`

Expected outcome:

`Nice! I've marked this task as done:`<br/>
`[T][✓] 2103 IP`

### `Keyword` - done

Mark a task as complete by index.

Example of usage: 

`done 1`

Expected outcome:

`Nice! I've marked this task as done:`<br/>
`[T][✓] 2103 IP`

### `Keyword` - list

List all tasks.

Example of usage: 

`list`

Expected outcome:

`Here are the tasks in your list:`<br/>
`1.[T][✓] 2103 IP`<br/>
`2.[D][✘] 2103 IP submission (by: Feb 24 2020, 00:00)`<br/>
`3.[E][✘] 2103 tutorial (at: Feb 19 2020, 12:00 to Feb 19 2020, 13:00)`

### `Keyword` - delete

Delete a task by index.

Example of usage: 

`delete 3`

Expected outcome:

`Noted. I've removed this task:`<br/>
`[E][✘] 2103 tutorial (at: Feb 19 2020, 12:00 to Feb 19 2020, 13:00)`<br/>
`Now you have 2 tasks in the list.`

### `Keyword` - find

Search for related tasks by keyword.

Example of usage: 

`find submission`

Expected outcome:

`Here are the matching tasks in your list:`<br/>
`1.[D][✘] 2103 IP submission (by: Feb 24 2020, 00:00)`

### `Keyword` - reschedule

Reschedule a task by index (Deadline or Event).

Example of usage: 

`reschedule 2 25/12/2020`

Expected outcome:

`I've rescheduled this task:`<br/>
`[D][✘] 2103 IP submission (by: Dec 25 2020, 00:00)`

### `Keyword` - bye

Exit Duke.

Example of usage: 

`bye`

Expected outcome:

`Save Success! See you next time!`




