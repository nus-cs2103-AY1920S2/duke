# Personal Scheduler User Guide

## Introduction 
Personal Scheduler is created to help keep tracks of activities going.  
### Features 
There are the scheduler are able to keep track of 3 main task which are `todo`, `deadline` and `event`.

## Usage

### `todo` - Add a todo task. 

This adds a todo task to the scheduler to keep track of.

Example of usage: 

`todo [buy bread]`

Expected outcome:
```
Got it. I've added this task: 
[T][X] buy bread 
Now you have 1 tasks in the list.
```
### `deadline` - Add a deadline task. 

This adds a deadline task with its date to the scheduler. Time is optional in the input. 

Example of usage: 

`deadline [assignment] /by [25/02/2020 2359]`

Expected outcome:
```
Got it. I've added this task: 
[D][X] assignment | by: Feb 25 2020 11:59pm 
Now you have 2 tasks in the list.
```
### `event` - Add an event task. 

This adds a even task and the date to the scheduler. Time is optional in the input.

Example of usage: 

`event [project meeting] /at [24/02/2020 1400]`

Expected outcome:
```
Got it. I've added this task: 
[E][X] project meeting | at: Feb 24 2020 02:00 PM
Now you have 3 tasks in the list
```

## Other functions

### `list` - List all task in the scheduler. 

This lists all the task available in the scheduler.

Example of usage: 

`list`

Expected outcome:
``` 
[T][X] buy bread
[D][X] assignment | by: Feb 25 2020 11:59pm 
[E][X] project meeting | at: Feb 24 2020 02:00 PM
```
### `done` - Mark a task as done.

This function marks one task as completed. 

Example of usage:

`done [1]`

Expected outcome: 
 ```
Nice! I've marked this task as done: 
[T][✓] buy bread
```

### `find` - Find tasks that matches the search.

This function find all the tasks that matches the user input.

Example of usage:

`find [bread]`

Expected outcome: 
```
Here are the matching tasks in your list:
1. [T][✓] buy bread
```

### `delete` - Delete a task. 

This function remove one task from the scheduler.

Example of usage:

`delete 2`

Expected outcome: 
```
Noted. I've removed this task:
[D][X] assignment | by: feb 25 2020 11:59 PM
now you have 2 task in the list.
```

## Extra features

### Detecting Anomalies

The scheduler has been program to detect any clashes of task on the same day and at the exact time. 

Example: 

`deadline 2103 indivual project /by 24/02/2020 1400`

Outcome: 

```
Clashes with:
[E][X] project meeting | at: Feb 24 2020 02:00 PM
```