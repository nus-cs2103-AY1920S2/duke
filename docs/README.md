# User Guide

## Features 

### Save tasks
Allows a user to add different types of tasks, namely: 

1. Todo tasks which do not have any deadlines or timings. 
2. Events which have a specific starting time. 
3. Deadlines which have a specific date by which the task needs to be completed.

### List tasks
Allows a user to list all tasks that have been saved in the TaskList.

### Track progress
Allows a user to mark a task as done when completed to track one's own progress. 

### Delete tasks 
Allows a user to delete a task when it has been completed or when it does not have to be done anymore. 

### Find tasks 
Allows a user to find tasks that contains a certain keyword. 

## Usage

### `todo` - Add a todo task to the list

Example of usage: todo (description)

`todo borrow book`

Expected outcome:

`Got it. I've added this task:`

  `[T][X] borrow book`
  
`Now you have 1 task in the list.`

### `event` - Add an event to the list

Example of usage: event (description) /on (date)

`event project meeting /on 2020-02-20 15:00`

Expected outcome:

`Got it. I've added this task:`

  `[E][X] project meeting on Feb 20 2020 03:00 PM`
  
`Now you have 2 tasks in the list.`

### `deadline` - Add a deadline task to the list

Example of usage: deadline (description) /by (date)

`deadline CS2103 iP /by 2020-02-19 23:59`

Expected outcome:

`Got it. I've added this task:`

  `[D][X] CS2103 iP by Feb 19 2020 11:59 PM`
  
`Now you have 3 tasks in the list.`

### `list` - List all the tasks currently in the taskList

Example of usage: list

`list`

Expected outcome:

`1. [T][X] borrow book`

`2. [E][X] project meeting on Feb 20 2020 03:00 PM`

`3. [D][X] CS2103 iP by Feb 19 2020 11:59 PM`

### `done` - Mark completed tasks as done

Example of usage: done (index of task completed)

`done 1`

Expected outcome:

`Nice! I've marked this task as done:`

`[T][Y] borrow book`

### `delete` - Delete tasks that are no longer needed

Example of usage: delete (index of task to be deleted)

`delete 1`

Expected outcome:

`Noted. I've removed this task:`

`[T][Y] borrow book`

`Now you have 2 tasks in the list.`

### `find` - Find tasks using a given keyword

Example of usage: find (keyword)

`find project`

Expected outcome:

`Here are the matching tasks in your list:`

`1. [E][X] project meeting on Feb 20 2020 03:00 PM`

### `bye` - Exit Duke

Example of usage: bye

`bye`

Expected outcome:

`Bye. Hope to see you again soon!`