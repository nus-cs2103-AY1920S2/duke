# User Guide
## Introduction
Dory's TaskList is a program that helps you keep track of your tasks. 

## Preview
![Screenshot](Ui.png)

## Features 
### Keeps track of tasks
Lists the tasks currently present in the task list.

### Conveniently update task list
The tasks in the task list can be easily added, removed and changed.

### Search for tasks
The tasks you have listed can be searched for with your keyword.


## Command
### `list` - Lists the tasks.
Lists the tasks currently present in the task list.

Example of usage: 
`list`

Expected outcome: 
`A list of the tasks in the task list will be printed.`

### `bye` - Closes the program.
Saves the tasks and closes the program.

Example of usage: 
`bye`

Expected outcome: 
`Program closes.`

### `delete INDEX` - Delete a task
Deletes the task at the specified index.

Example of usage: 
`delete 3`

Expected outcome: 
`Task number 3 is deleted.`

### `done INDEX` - Mark a task as done
Marks the task at the specified index as done.

Example of usage: 
`done 2`

Expected outcome: 
`Task number 2 is marked as done.`

### `find KEYWORD` - Search for tasks
Lists the tasks that contain the specified keyword.

Example of usage: 
`find hello`

Expected outcome: 
`Lists the task with 'hello' in their description.`

### `todo DESCRIPTION` - Add a to do task
Adds a to do task to the task list.

Example of usage: 
`todo homework`

Expected outcome: 
`A new todo task with description 'homework' will be added to the list.`

### `event DESCRIPTION YYYY-MM-DD` - Add a event task
Adds an event task that will be at YYYY-MM-DD to the task list.

Example of usage: 
`event meet nemo /at 2020-02-19`

Expected outcome: 
`A new event task with description 'meet nemo' at date '19 February 2020' will be added to the list.`

### `deadline DESCRIPTION YYYY-MM-DD` - Add a deadline task
Adds an deadline task that will be at YYYY-MM-DD to the task list.

Example of usage: 
`deadline search through marine litter /by 2021-01-01`

Expected outcome: 
`A new deadline task with description 'search through marine litter' due by '19 February 2020' will be added to the list.`

### `updatem INDEX MESSAGE` - Update task description
Updates the message of the task at the specified index.

Example of usage: 
`updatem 1 do assignment`

Expected outcome: 
`The description of the task at index 1 will be changed to 'do assignment'.`

### `updated INDEX YYYY-MM-DD` - Update task date
Updates the date of a event or deadline task at the specified index.

Example of usage: 
`updatem 1 2020-02-19`

Expected outcome: 
`The date of the event or deadline task at index 1 will be changed to '19 February 2020'.`