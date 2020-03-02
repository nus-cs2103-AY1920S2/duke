# User Guide

## Features 

### Add tasks to list
Add either a todo, event or deadline task to the list.
For event and deadline, a schedule is required.

### Mark task as done
Mark selected tasks as done.

### Delete task from list
Delete selected tasks from the list.

### Snooze task to later date
For tasks with a schedule, delay the schedule a certain amount of days based on user input.

### Find task with a specific keyword
Find list of tasks from a keyword specified by user.

## Usage

### `todo` - Create a todo task and add it to the list

Example of usage: 

`todo learn java`

Expected outcome:

`I've added this task to the list: [T][X] learn java`

### `event` - Create a event task and add it to the list

Example of usage: 

`event nus career fair /at 2020-02-04`

Expected outcome:

`I've added this task to the list: [E][X] nus career fair (at: Feb 04 2020)`

### `deadline` - Create a deadline task and add it to the list

Example of usage: 

`deadline submit project /by 2020-03-24`

Expected outcome:

`I've added this task to the list: [D][X] submit project (at: Mar 24 2020)`

### `delete` - Delete selected tasks from the list (multiple tasks selection available)

Example of usage: 

`delete 1 2 4`

Expected outcome:

`Nice! Deleted tasks(unknown task number ignored): [T][X] learn java`

### `done` - Mark selected tasks as done (multiple tasks selection available)

Example of usage: 

`done 2 3 5`

Expected outcome:

`Nice! Tasks marked as done(unknown task number ignored): [T][V] learn java`

### `find` - Look up all tasks that contain a specific keyword

Example of usage: 

`find school`

Expected outcome:

`Here are the matching tasks in your list: 1. [T][X] go to school`

### `list` - List all tasks stored by duke

Example of usage: 

`list`

### `snooze` - delay a task with schedule by a certain number of days

Example of usage: 

`snooze 1 20`

Expected outcome:

`Task schedule or deadline updated: [E][X] nus career fair (at: Jun 2020)`

### `bye` - Exit duke

Example of usage: 

`bye`

### `help` - List out all commands available or details about a specific command

Example of usage: 

`help`
`help snooze`