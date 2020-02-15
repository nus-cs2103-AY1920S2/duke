# User Guide ---- Duke
Duke is an interactive task manager that allows you to track your personal tasks by adding, 
removing, marking, searching, tagging tasks. It has a concise user interface and is easy to use.
This document explains the key features of Duke and shows examples of their usage.
## Features 

### Adding Tasks
A task can be easily added in one of the three formats specified below, depending on the 
type of task you intend to add.

`todo [description]`: Adding a to-do task is a task that contains only 
task information and does not have time information.

`deadline [description] /by [yyyy-mm-dd]`: Adding a task that has to be done by a specified timing.

`event [description] /at [yyyy-mm-dd]`: Adding a task that will take place at a specified timing.

### Removing Tasks
A task can be removed from the list by the `delete` command, followed by the index of the task in the list.

`delete [index]`: Delete a task with the specified index in the list.

### Marking Tasks
A task originally has state "undone". By the following command, you can mark a task as done.

`done [index]`: Mark a task with specified index in the list as done.

### Showing The List 
`list`: This command shows all the tasks on the list.

### Search Keyword
You can also search through the list and ask duke to display those tasks whose descriptions contain a specified keyword.
The command is specified as follow:

`find [keyword]`: Get a list of all tasks containing the specified keyword.

### Tag Function
To enhance searching, you can add tags to tasks and then search them according to the tags. A task can contain
multiple tags and multiple tasks can have the same tag.

`tag [tag_name] [task_index]`: Add the tag with name `tag_name` into the task with index `task_index`.

`tag [tag_name]`: Search through the list of tasks and return those contain a specified tag.

### Close Duke

`bye`: Most elegant way to end the current Duke session.

## Usage

### `todo/deadline/event` - Adding a task

These three commands add tasks into the task list of Duke. Duke will respond whether you have
successfully added a task.

Example of usage: 

`todo call Mum`

Expected outcome:
````
Got it. I've added this task:

 [T][x] call Mum
````
Example of usage: 

`deadline submit homework /by 2020-03-01`

Expected outcome:
````
Got it. I've added this task:

 [T][x] submit homework(by: Mar 1 2020)
````

### `done/delete` - Mark/Delete a task

These commands access a task in the list through index.

Example of usage:
`delete 1`

Expected outcome:
````
Noted. I've removed this task: 

 [T][x] call Mum
````

### `Tag` - Tag and Query

You can tag a task and search through the list using the tag.

Example of usage:

`tag important 1`

Expected outcome:

````
I have tagged the following task with tag 'important':

 [T][x] call Mum`
````

Example of usage:

`tag important`

Expected outcome:
````
Here are the tasks with tag 'important': 

 1. [T][x] call Mum
````

### `find` - Search the list using keyword

You can also search through a list using a keyword.

Example of usage:

`find Mum`

Expected Outcome:

````
Here are the matching tasks in your list: 

 1. [T][x] call Mum
````

## Author

* Zhu Ruicong(Zhu-Ruicong/duke)

## Acknowledgement

* se-edu/duke