# User Guide

## Features 

### Add tasks: `todo`, `event`, `deadline`

Adds a task to current list

Example of usage: 

`todo sleep`

Expected outcome:

`Got it. I've added this task:`<br/>
`[T][✘] sleep`<br/>
`Now you have 1 task(s) in the list.`

Example of usage: 

`event wedding /at 2019-05-15`

Expected outcome:

`Got it. I've added this task:`<br/>
`[E][✘] wedding (at: May 15 2019)`<br/>
`Now you have 2 task(s) in the list.`

Example of usage: 

`event submission /by 2019-04-22`

Expected outcome:

`Got it. I've added this task:`<br/>
`[D][✘] submission (at: Apr 22 2019)`<br/>
`Now you have 3 task(s) in the list.`

### Listing out Tasks: `list`

Display current list of tasks

Example of usage: 

`list`

Expected outcome:

`1.[T][✘] sleep`<br/>
`2.[E][✘] wedding (at: May 15 2019)`<br/>
`3.[D][✘] submission (by: Apr 22 2019)`

### Marking completed tasks: `done`

Mark a task as completed

Example of usage: 

`done 1`

Expected outcome:

`Nice! I've marked this task as done:`<br/>
`[T][✓] sleep`

### Deleting tasks: `delete`

Delete a task from current list

Example of usage: 

`delete 3`

Expected outcome:

`Noted. I've removed this task`<br/>
`[D][✘] submission (by: Apr 22 2019)`

### Searching for tasks: `find`

Search for tasks with a keyword.

Example of usage: 

`find sleep`

Expected outcome:

`Here are the matching tasks in your list`<br/>
`1.[T][✓] sleep`

### Updating tasks: `update`

Update a task in the current list.

Example of usage: 

`update 2 description Mary's wedding`

Expected outcome:

`Alright. I've updated this task:`<br/>
`[E][✘] Mary's wedding (by: Apr 22 2019)`

Example of usage: 

`update 2 date 2019-06-07`

Expected outcome:

`Alright. I've updated this task:`<br/>
`[E][✘] Mary's wedding (by: Jun 7 2019)`