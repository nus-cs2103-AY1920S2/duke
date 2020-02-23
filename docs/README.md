# User Guide

Welcome to using Duke! Theres a music toggle if you like to have music on

## Features 

### Contact Command
Add someone's contact with name and phone number.

### ContactDel Command
Delete a contact from the address book.

### cList Command
List all contacts.

### DeadLine Command
Creates a DeadLine task with due date.

### Delete Command
Delete a task.

### Done Command
Mark a task as done.

### Event Command
Creates a Event task with a date.

### Find Command
Find a task.

### List Command
List all task.

### Todo Command
Creates a todo.


## Usage

### `contact` - creates a contact

Creates a contact to save.

Example of usage: 

`contact Tom /number 12345678`

Expected outcome:

`contact saved`

### `cList` - list all contacts

List all contacts in the address book.

Example of usage: 

`cList`

Expected outcome:

`Here are the contacts in your contact book:`
`1.[C]Name: Tom Contact Number: 12345678`

### `contactDel` - delete a contact

Delete a contact in the address book.

Example of usage: 

`contactDel 1`

Expected outcome:

`Noted! I've removed this Contact:`
`1.[C]Name: Tom Contact Number: 12345678`
`Now you have 0 contacts in the address book`


### `Deadline` - Create a deadline

Create a deadline with a given date.

Example of usage: 

`deadline assigment1 /by 12-12-2019 18:00`

Expected outcome:

`Got it. I've added this task`
`[D][X] assignment1 (by:12-12-2019 18:00)`
`Now you have 1 tasks in the list`

### `delete` - delete a task

Deletes a Task stated by the user.

Example of usage: 

`delete 1`

Expected outcome:

`Noted! I've removed this task:  `
`[D][X] assignment1 (by:12-12-2019 18:00)`
`Now you have 0 tasks in the list.`

### `done` - mark a task as done

Marks a task as done.

Example of usage: 

`done 1`

Expected outcome:

`Nice! I've marked this task as done:  `
`1.[✓] hey`
 	
### `event` - creates an event

Creates an Event Task with a day.

Example of usage: 

`event assignment1 /at monday`

Expected outcome:

`Got it. I've added this task:  `
`[E][X] assignment1 (at: monday)`
`Now you have 1 tasks in the list.`

### `find` - search for a task

Search for a task based off the description.

Example of usage: 

`find assignment1`

Expected outcome:

`Here are the matching tasks in your list:  `
`1.[E][X] assignment1 (at: monday)`

### `list` - list all task

List down all Tasks.

Example of usage: 

`list`

Expected outcome:

`Here are the tasks in your list: `
`1.[T][✓] hey`
`2.[E][X] assignment1 (at: monday)`
 
### `todo` - creates a todo

Creates a Todo Task.

Example of usage: 

`todo fly to space`

Expected outcome:

`Got it. I've added this task:  `
`[T][X] fly to space`
`Now you have 3 task in the list.`


