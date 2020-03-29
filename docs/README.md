# User Guide

## 1. Introduction
Duke is for those who prefer to use a **desktop app for managing contacts**. More importantly, Duke is **optimized for those who prefer to work with a Command Line Interface (CLI)** while still having the benefits of a Graphical User Interface (GUI). If you can type fast, AB3 can get your contact management tasks done faster than traditional GUI apps. Interested? Jump to the Section 2, “Quick Start” to get started. Enjoy!

## 2. Quick Start
1. Ensure you have Java 11 or above installed in your Computer.

1. Download the latest `duke.jar` [here](https://github.com/Dban1/duke/releases).

1. Copy the file to the folder you want to use as the home folder for your Address Book.

1. Double-click the file to start the app. The GUI should appear in a few seconds.

1. Type the command in the command box and press Enter to execute it.
e.g. typing `help` and pressing Enter will open the help window.

1. Some example commands you can try:

- `list` : lists all tasks

- `todo ATTEND JUDO`: adds a todo called ATTEND JUDO to the task list.

- `delete 3` : deletes the 3rd task shown in the current list

- `bye` : exits the app

Refer to Section 3, “Features” for details of each command.

## 3. Features

Here is the list of available commands this program understands.
1. `todo`
2. `event`
3. `deadline`
4. `done`
5. `list`
6. `delete`
7. `find`
8. `tag`

#### Command Format
Words in `UPPER_CASE` are the parameters to be supplied by the user e.g. in `todo TASK`, TASK is a parameter which can be used as `todo REVISE HOMEWORK`.

## 4. Usage
The commands of the program should always be given as the first word in the entry for the program to understand the command, as well as adhering to the required amount of entries following the command.

### 4.1 - Add todo: `todo`

Adds a todo to the task list.

Example of usage: 

`todo ATTEND JUDO`

Expected outcome:

`I've added the task to the list!`

### 4.2 - Add event: `event`

Adds an event to the task list:

Example of usage:
`event BOB'S PARTY /at TUESDAY EVENING`

Expected outcome:
`I've added the task to the list!`

### 4.3 - Add deadline: `deadline`

Adds a deadline to the task list:

Example of usage:
`deadline SUBMIT MATH ASSIGNMENT /by 4.30PM`

Expected outcome:
`I've added the task to the list!`

### 4.4 - Mark task as done: `done`

Marks a task according to its index number on the task list as done.

Example of usage:
`done 2`

Expected outcome:
`Nice! I've marked this task as done:
[T][✔] ATTEND JUDO`

### 4.5 - List all tasks: `list`

Lists the current task list.

Example of usage:
`list`

Expected outcome:
`Here are the tasks in your list`
`1. [T}[✔]ATTEND JUDO`

### 4.6 - Delete a task: `delete`

Deletes a task according to its index number on the task list.

Example of usage:
`delete 2`

Expected outcome:
`Boi. I've went and deleted that taks.`
`[T][✔]ATTEND JUDO`

### 4.7 - Find a task: `find`

Find a task in the task list

Example of usage:

`find ATTEND`

Expected outcome:
`1. [T][✔] ATTEND JUDO`
`2. [T][✔] ATTEND BOB'S BIRTHDAY`

### 4.8 - Tag a task: `tag`

Tags a task according to its index number in the task list.

Example usage:
`tag 1 IMPORTANT`

Expected outcome:
`Nice! I've tagged important to the task`
`1. [T][✔] ATTEND JUDO #IMPORTANT`

Tags are also considered in `find`.