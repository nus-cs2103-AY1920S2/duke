# User Guide
Duke is a personalised chat bot designed to help you keep track of your day-to-day task.

![The GUI of duke](./Ui.png)

**_[Link to the repo](https://github.com/kschiew/duke)_**
## Features 

### Quit and Save
Quit the Duke program. The task list will be updated before saving.

#### Usage

#### `bye`

Example of usage: 

`bye`

Expected outcome:

`Bye. Hope to see you again soon! :)`

### Show task list
Print the entire task list
#### Usage

#### `list`
Example of usage:

`list`

Expected outcome:

`Here is your task list:`

`1. task 1`

`2. task 2`

`3. task 3`

### Add a Todo Task
Add a task that does not have a due date to the task list.
#### Usage
Example of usage:

`todo collect laundry`

Expected outcome:

`Added: [T} [Not Done] collect laundry`

`Now you have 1 task(s) on your list.`

### Add a Deadline Task
Add a task that has a due date to the task list.
#### Usage
#### `deadline [task] /by [YYYY-MM-DD]`
Example of usage:

`deadline collect laundry /by 2020-01-02`

Expected outcome:

`Added: [D} [Not Done] collect laundry by 02-Jan-2020`

`Now you have 1 task(s) on your list.`

### Add a Event Task
Add an event that happens at a certain date to the task list.
#### Usage
#### `event [task] /at [YYYY-MM-DD]`
Example of usage:

`event collect laundry /at 2020-01-02`

Expected outcome:

`Added: [E} [Not Done] collect laundry at 02-Jan-2020`

`Now you have 1 task(s) on your list.`

### Mark task as done
Mark a certain task in task list as a finished task.
#### Usage
#### `done [index of task]`
Example of usage:

`done 1`

Expected outcome:
`Good job! I've marked this task as done`
`1. [T] [Done] collect laundry`

### Delete task
Deletes a certain task in task list.
#### Usage
#### `delete [index of task]`
Example of usage:

`delete 1`

Expected outcome:

`Deleted: [T] [Done] collect laundry`
