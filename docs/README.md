# User Guide

## Features 

### Calendar Command
Find Deadline and Event Tasks that falls on a given date.

### Deadline Command
Creates a Deadline Task which is a Task that has a due date.

### Delete Command
Deletes a given Task.

### Done Command
Marks a given Task as done.

### Event Command
Creates an Event Task which is happening on a given date.

### Find Command
Find Task(s) that contains given keyword(s) in it's description.

### List Command
List down all Tasks in the To Do List (if any).

### Snooze Command
Snooze a given Task which supports the Snooze functionality. 

### Todo Command
Creates a Todo Task with a description.

## Usage

### `calendar` - invoke the Calendar Command

Find Deadline and Events Tasks that falls on a given date.

Example of usage: 

`calendar 12/12/2019`

Expected outcome:

`Here are the events/deadlines in your list on 12 December 2019`

`3. [E][✓] Birthday Party (on: 12 December 2019, 6:00 PM)`

### `deadline` - invoke the Deadline Command

Creates a Deadline Task which is a Task that has a due date.

Example of usage: 

`deadline CS2103T iP /by 12/12/2019 1800`

Expected outcome:

`Got it. I've added this task:  `

`[D][✗] CS2103T iP (by: 12/12/2019 6:00 PM)  `

`Now you have 5 tasks in the list.`

### `delete` - invoke the Delete Command

Deletes a given Task.

Example of usage: 

`delete 3`

Expected outcome:

`Noted. I've removed this task:  `

`[D][✗] CS2103T iP (by: 12/12/2019 6:00 PM)`

`Now you have 4 tasks in the list.`

### `done` - invoke the Done Command

Marks a given Task as done.

Example of usage: 

`done 1`

Expected outcome:

`Nice! I've marked this task as done:  `

` 	[✓] Birthday Party (on: 12 December 2019, 6:00 PM`
 	
### `event` - invoke the Event Command

Creates an Event Task which is happening on a given date.

Example of usage: 

`event Birthday Party /on 12/12/2019 1800`

Expected outcome:

`Got it. I've added this Event:  `

` 	[E][✗] Birthday Party (on: 12 December 2019, 6:00 PM)`

` Now you have 5 tasks in the list.`

### `find` - invoke the Find Command

Find Task(s) that contains given keyword(s) in it's description.

Example of usage: 

`find Buy`

Expected outcome:

`Here are the matching tasks in your list:  `

` 	1. [T][✗] Buy Apples  `

` 	2. [T][✗] Buy Oranges`
 	
### `list` - invoke the List Command

List down all Tasks in the To Do List (if any).

Example of usage: 

`list`

Expected outcome:

`Here are the tasks in your list:  `

` 1.[T][✗] Buy Apples`

` 2.[E][✓] Birthday Party (on: 12 December 2019, 6:00 PM)`

` 3.[T][✗] Buy Oranges`
 
### `snooze` - invoke the Snooze Command

Snooze a given Task which supports the Snooze functionality. 

Example of usage: 

`snooze 2 12/12/2020 1500`

Expected outcome:

`Noted. I've snoozed this task:  `

` [D][✓] this project (by: 12 December 2020, 3:00 PM)`
 
### `todo` - invoke the Todo Command

Creates a Todo Task with a description.

Example of usage: 

`todo Get a Job`

Expected outcome:

`Got it. I've added this task:  `

` 	[T][✗] Get a Job `

` Now you have 1 task in the list.`
