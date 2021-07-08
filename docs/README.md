# Peach - User Guide 

## Introduction
Peach (Duke) is an application for managing your tasks! It is optimized for users who prefer to work with a *Command Line Interface (CLI)*.

![](./Ui.png)

## Quick Start
1. Ensure that you have Java `11` or above installed in your Computer.
2. Download the latest `duke.jar` [here](https://github.com/ncslzh/duke/releases).
3. Copy the file to the folder you want to use as the home folder for Peach.
4. Double-click the file to start the app. The GUI should appear in a few seconds.
5. type the command in the command box and press `Enter` to execute it.
   e.g. typing `help` and pressing `Enter` will list the commands you can execute.
6. Some example commands you can try:\
   `list`: lists all tasks\
   `todo`: adds a todo task\
   `bye`: exits the application
7. Refer to *Usage* for details of each command.
 
## Features 

### Add a task
You can add a task belonging to one of these three predefined categories
* Todo
* Event
* Deadline

For example, if you want to remind yourself of a party you have scheduled, you can add an Event task to the list.

### Delete a task
Cancelled plans? Don't feel like doing the task or your homework anymore? 

You can simply delete the task from the list.

### Mark a task as done
To help keep track of your tasks better, Peach allows you to mark tasks as done.

Done tasks will be marked with [✓]
 
Undone tasks will be marked with [✗]

### Display task list
Want to see what tasks you have in your task list?

Every task in your list will be shown, along with it's completion status [✓] / [✗] and its due date (if applicable).

### Save function
Your task list will be saved upon exit.

Make sure to use the command `bye` to exit to ensure proper saving.

### Archival
Want to start a new but still want to keep your current task list for reference in the future?

Peach allows you to archive your current task list. 

Upon archival, all current tasks will be saved to file and you will start with a new and empty task list.  


## Usage

### `help` - Viewing help
Format: `help`

List of all possible executable actions will be shown

### `list` - View all tasks from the list
Format: `list`

Example of usage:

`list`

Expected outcome:
```
Here are your tasks:
1.[E][✘] party (at: 10 OCTOBER 2020)
2.[E][✘] dinner (at : 14 MARCH 2020)
```
### `todo` - Adding a To Do task to the list
Format: `todo <description>`

Example of usage:

`todo homework`

Expected outcome:
```
Alrighty, you added:
  [T][✘] homework
You now have 3 tasks in your list.
```

### `event` - Adding an Event task to the list
Format: `event <description> /at <dd/MM/yyyy>`

Example of usage:

`event birthday party /at 22/02/2021`

Expected outcome:
```
Alrighty, you added:
  [E][✘] birthday party (at: 22 FEBURARY 2021)
You now have 4 tasks in your list.
```

### `deadline` - Adding a Deadline task to the list
Format: `deadline <description> /by <dd/MM/yyyy>`

Example of usage:

`deadline CS2103T tP v2.2 /by 23/05/2022`

Expected outcome:
```
Alrighty, you added:
  [D][✘] CS2103T tP v2.2 (by: 23 MAY 2022)
You now have 5 tasks in your list.
```

### `done` - Set a task as done from the list
Format: `done <task number in list>`

Example of usage:

`done 2`

Expected outcome:
```
You've finally done this task:
  [E][✓] birthday party (at: 22 FEBURARY 2021)
```

### `delete` - Delete a task from the list
Format: `delete <task number in list>`

Example of usage:

`delete 2`

Expected outcome:
```
Do you really want to remove this?
Fine. I've removed:
  [E][✓] birthday party (at: 22 FEBURARY 2021)
Now you have 4 tasks in the list.
```

### `find` - Find tasks
Format: `find <keyword>`

Example of usage:

`find homework`

Expected outcome:
```
Here are the matching tasks in your list
  1. [T][✘] homework
```

### `archive` - Archive tasks
Format: `archive`

Example of usage:

`archive`

Expected outcome:
```
Here are the tasks that you have archived:
  1.[E][✘] party (at: 10 OCTOBER 2020)
  2.[E][✘] dinner (at : 14 MARCH 2020)
```