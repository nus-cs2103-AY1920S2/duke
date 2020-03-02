# Duke User Guide
Duke is a chatbot that can help you manage your tasks. You can tell Duke to add, delete and mark as done tasks via short, simple to remember commands. 

## Table of Contents
- [Requirements](#requirements)
- [Features](#features)
    - [Interactive Graphical User Interface](#interacctive-graphical-user-interface)
    - [Types of tasks](#types-of-tasks)
    - [Search for tasks](#search-for-tasks)
- [Usage](#usage)
    - [List tasks](#list-tasks)
    - [Add a task](#add-a-task)
    - [Delete a task](#delete-a-task)
    - [Mark a task as done](#mark-a-task-as-done)
    - [Find tasks](#find-tasks)
    
## Requirements
- Duke requires Java 11 or later.
- Duke only starts from the command line by entering the command: `java -jar Duke.jar`
- You may download the Duke executable from [here](https://github.com/kevinswk94/duke/releases)

## Features 

### Interactive Graphical User Interface
Duke comes with a GUI to provide users with visual feedback when Duke performs tasks.

<img src="Ui.png" width="250">

### Types of tasks
Duke manages your tasks which are categorized into three types:
- Todos
- Deadlines
- Events

You can add, delete, find and mark tasks as done from the list.


## Usage

### List tasks
List all the tasks that Duke has in its database.

Example usage:
`list`<br>
It will show all the tasks in the database.


### Add a task
Adds a new task to Duke's database. Duke classifies tasks into three types, so it accepts the following formats:
- `todo <description>`<br>
Adds your todo to your database.
- `deadline <description> /by <due-date>`<br>
Adds your deadline that is due on the `due-date` to the database. The `due-date`
must be in `yyyy-MM-dd` format.
- `event <description> /at <event-date>`<br>
Adds your event that happens on the `event-date` to the database. The `event-date`
must be in `yyyy-MM-dd` format.

Sample usage: 
- `todo read book`<br>
Adds a new todo task with the description `read book`. Duke will update you if the todo is added successfully or there is a parsing error.
- `event project meeting /at 2020-01-10`<br>
Adds a new event task with the description `project meeting` and event date `Jan 10 2020`. Duke will update you if the event is added successfully or there is a parsing error.
- `deadline return book /by 2020-10-01`<br>
Adds a new deadline task with the description `return book` which is due at `Oct 1 2020`. Duke will update you if the deadline is added successfully or there is a parsing error.


### Delete a task
Removes a task from Duke's database, according to its index in the database.<br>
`delete <index>`<br>

Example of usage:
`delete 2`<br>
Removes the second task that appears in your task list. Duke will update you if the task is deleted successfully or if the index provided is out of the range of tasks shown.


### Mark a task as done
Marks a task in a list, according to its number inside the list.<br>
`done <index>`<br>

Sample usage:
`done 1`<br>
Marks the first task as done. Duke will update you if the task is marked as done successfully or if the index provided is out of the range of tasks shown.


### Find tasks
Lists all tasks with certain keywords in their description.<br>
`find <keyword>`<br>

Sample usage:
`find book`<br>
Duke finds and displays all tasks that contains `book` in their description.