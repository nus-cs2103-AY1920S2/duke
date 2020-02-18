# User Guide

## Features 

### Viewing help
Displays a list of all the commands available and their usage.

### Adding Event tasks
Adds an event task with the specified description and event time.

### Adding Deadline tasks
Adds a deadline task with the specified description and deadline.

### Adding Todo tasks
Adds a todo task with the specified description.

### Deleting tasks
Deletes an existing task from the application memory.

### Marking tasks as done
Marks an existing task as done. 

### Filtering tasks by priority level
Displays only the tasks tagged with the specified priority level. 
There are three such priority levels; LOW, MEDIUM and HIGH.

### Updating priority level of tasks
Updates the priority level of a specified task with the specified priority level.

### Listing all tasks
Lists all the existing tasks, in order of addition.

### Exiting program
Exits the program. 

## Usage

### `help` - Viewing help
Displays a list of command available and their usage.

Example of usage: 

`help`

Expected outcome:

Displays a help page that summarizes the commands and their usage in list form. 

### `event` - Adding Event tasks 
Adds an event task with the specified description and event time.

Example of usage: 

`event <Task Description> /by <Task Time>`

Expected outcome:

Adds a new event task with the description `<Task Description>` and event time
`<Event Time>`. Returns a descriptive message informing you whether the operation has 
succeeded or failed.

### `deadline` - Adding Deadline tasks
Adds a deadline task with the specified description and deadline.

Example of usage: 

`deadline <Task Description> /by <Task Deadline>`

Expected outcome:

Adds a new event task with the description `<Task Description>` and deadline
`<Task Deadline>`. Returns a descriptive message informing you whether the operation has 
succeeded or failed.

### `todo` - Adding Todo tasks
Adds a todo task with the specified description.

Example of usage: 

`todo <Task Description>`

Expected outcome:

Adds a new todo task with the description `<Task Description>`. Returns a descriptive message informing you whether 
the operation has succeeded or failed.

### `delete` - Deleting tasks
Deletes an existing task, specified by its task number as per the task list. 

Example of usage: 

`delete <Task Number>`

Expected outcome:

Deletes the task with task number `<Task Number>`. Returns a descriptive message informing you whether 
the operation has succeeded or failed.

### `done` - Marking tasks as done 
Marks an existing task as done. 

Example of usage: 

`done <Task Number>`

Expected outcome:

Marks as done the task with task number `<Task Number>`. Returns a descriptive message informing you whether 
the operation has succeeded or failed.

### `priority` - Filtering tasks by priority level
Displays only the tasks tagged with the specified priority level (LOW, MEDIUM or HIGH)

Example of usage: 

`priority <priority level>`

Expected outcome:

Displays as a list all the tasks with the specified priority level. Returns a descriptive message informing you whether 
the operation has succeeded or failed.

### `update` - Updating priority level of tasks
Updates the priority level of a specified task with the specified priority level.

Example of usage: 

`update <Task Number>`

Expected outcome:

Updates the priority level of the task with task number `<Task Number>`. Returns a descriptive message informing you 
whether the operation has succeeded or failed.

### `list` - Listing all tasks
Lists all the existing tasks, in order of addition.

Example of usage: 

`list`

Expected outcome:

Lists all existing tasks, in order of addition.

### `bye`- Exiting program
Exits the program. 

Example of usage: 

`bye`

Expected outcome:

Displays a goodbye message, before closing the application.