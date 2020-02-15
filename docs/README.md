# User Guide

## Description
The Duke ChatBot is a CLI application that aids users in managing tasks, deadlines and events. It is a javafx based application and can be supported among many different platforms.

## Screenshot Of Product
How the chatbot looks like:
![Duke ChatBot In Action](./Ui.png)

## Features
- Allows Users to add in tasks
- Tasks are categorized into the following
	- `Todo` - a simple tasks with no deadline or location
	- `Deadline` - a task with a stipulated deadline
	- `Event` - an event (task) with location and timing 
- Listing of current tasks
- Allows setting which tasks are done
	- Default tasks are set as undone
- Allows setting which tasks deserves more priority
	- Default tasks are medium priority 
	- Priorities include:
		- Low
		- Medium
		- High	
- Deleting of tasks
- Undo commands
- Help commands
- Saving tasks for future references
- Find relevant tasks with keywords
- Sorting of tasks

## Usage
| Command | Purpose | Outcome |
| ------- | ------- | ------- |
| `todo X` | Adds a todo task with name X to the tasklist | Updates tasklist with todo task with default settings |
| `event X /at RC4 22-12-2020` | Adds an event task with name X and location/date as RC4 22-12-2020 | Updates the tasklist with the new event with default settings |
| `deadline X /by 12-12-2020 23:59` | Adds a deadline task with name X and dateTime of deadline as 12-12-2020 23:59. The format for the date is DD-MM-YYYY HH:MM | Updates the tasklist with the new deadline with default settings |
| `list` | List all the tasks in the tasklist | A pretty printing with the tasks (based on chronological order that it was added) will be displayed on the screen |
| `done 2` | Sets the given task with that number (which is 2 in the example) to done | If the task number exist and task has not been set as done, then it will be updated. Else, nothing happens |
| `delete 2` | Deletes the task associated with the task number (eg: 2) | If the task number exist, then it will be deleted and no longer shown via `list` command | 
| `priority 1 low` | Sets the given task with that number to the given priority (low, medium or high). | If task number exist and the priority of that task is different from the command, then it will be updated. Else, nothing changes. |
| `undo` | Undo the last command | It will undo the last command if there is, can only undo max of 5 previous commands |
| `list-priority` | List tasks based on priority (Sorted) | List the tasks based on priority from Low to High with pretty printing |
|`find X` | Find tasks that contains the keyword (eg: X) | List the tasks that corresponds to the given keyword if any. |
| `help` | Provides some help regarding how to use the bot | Provides a list of commands for the user |
| `bye` | Allows the user to exit the bot. | Upon entering the command, the application will automatically close withing the next 5s |