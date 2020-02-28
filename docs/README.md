# User Guide

![Import docs](Ui.png)

## Features 

### Task Management 
- Manage various tasks such as TodDo, Deadline, Event.
- Add and delete any task.
- Mark tasks as completed.
- Search for tasks easily.

## Installation
- Download .jar file from [release page](http:github.com/teriaiw/duke/releases)
- Store downloaded file in existing or newly created directory

## Usage

### `Aloha`/`aloha` - Say Hi!
Say Hi to Stitch!

### `todo` - Adds ToDo to Task List

Add a simple ToDo task to Task List.

Example of usage: 

`todo {task_description}`

 
### `deadline` - Adds Deadline to Task List
 
Add a Deadline with specific **date** to Task List.
Date has to be entered in `YYYY-MM-DD` format.

Example of usage:

`deadline {task_description} /by {specific_date}`

### `event` - Adds Event to Task List

Add an Event with specific **date** and **time** to Task List. 
Date and time has to be entered in `YYYY-MM-DDTHH:MM` format

Example of usage:

`event {task_description} /at {specific_date_time}`

### `list` - Lists all tasks in Task List

Show list of tasks in Task List in added order.

Example of usage:

`list`

### `delete` - Deletes specific task in Task List

Delete specific task by deleting the task number.

Example of usage:

`delete {task_number}`

### `done` - Marks specific task as done

Mark specific task as done with the task number.

Example of usage:

`done {task_number}`

### `find` - Finds tasks with specified keyword

Search for tasks by finding keywords in tasks.

Example of usage:

`find {keyword}`

### `bye` - Exit program

Exit the program.

Example of usage:

`bye`