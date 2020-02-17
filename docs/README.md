# User Guide

Duke is a Personal Assistant Chatbot that helps you keep track of various tasks.

## Features

### Feature 1 - Tasks

Allows the user to add, update and delete tasks which are stored in a list. User can also mark a task as done. There are 3 types of tasks: `todo`, `deadline` and `event`.

### Feature 2 - Search

Allows the user to find tasks with a keyword.

### Feature 3 - Undo

Allows the user to undo an operation on a task.

### Feature 4 - Helpful error messages

Displays helpful error messages when the user uses a command incorrectly.

## Usage
Words in `UPPER_CASE` are the parameters to be supplied by the user.

E.g. in `todo TASK_DESCRIPTION`, `TASK_DESCRIPTION` is a parameter which can be used as `todo Homework`.

### 1. `todo` - Add a todo task

Adds a todo task to Duke's list of tasks.

#### Format:

    todo TASK_DESCRIPTION

#### Example of usage:

    todo Buy a new wallet

#### Expected Outcome:

    Got it. I've added this task:
        [T][✘] Buy a new wallet
    Now you have 1 tasks in the list.

### 2. `deadline` - Add a task with a deadline

Adds a task with a deadline which consists of the date and time.

#### Format:

    deadline TASK_DESCRIPTION /by DATE_AND_TIME

`DATE AND TIME` is in the format of `dd/MM/yyyy HH:mm`.

#### Example of usage:

    deadline CS3230 Assignment 1 /by 20/02/2020 23:59

#### Expected Outcome:

    Got it. I've added this task:
        [D][✘] CS3230 Assignment 1 (by: 20 Feb 2019 11:59PM)
    Now you have 2 tasks in the list.

### 3. `event` - Add an event happening at a certain time

Adds an event which consists of the date and time that it is happening.

#### Format:

    event TASK_DESCRIPTION /at DATE_AND_TIME

`DATE AND TIME` is in the format of `dd/MM/yyyy HH:mm`.

#### Example of usage:

    event CS2106 Midterm /at 07/03/2020 13:00

#### Expected Outcome:

    Got it. I've added this task:
        [E][✘] CS2106 Midterm (at: 07 Mar 2020 01:00PM)
    Now you have 3 tasks in the list.

### 4. `list` - View all tasks

Shows the list of all the tasks stored.

#### Format:

    list

#### Example of usage:

    list

#### Expected Outcome:

    Here are the tasks in your list:
    1.[T][✘] Buy a new wallet
    2.[D][✘] CS3230 Assignment 1 (by: 20 Feb 2019 11:59PM)
    3.[E][✘] CS2106 Midterm (at: 07 Mar 2020 01:00PM)

### 5. `done` - Mark a task as completed

Marks a task as completed.

#### Format:

    done TASK_INDEX

`TASK_INDEX` refers to the index number shown in the displayed list of tasks after using the `list` command.

#### Example of usage:

    done 1

#### Expected Outcome:

    Nice! I've marked this task as done:
        [T][✔] Buy a new wallet

### 6. `delete` - Delete a task

Deletes a task.

#### Format:

    delete TASK_INDEX

`TASK_INDEX` refers to the index number shown in the displayed list of tasks after using the `list` command.

#### Example of usage:

    delete 1

#### Expected Outcome:

    Noted. I've removed this task:
        [T][✔] Buy a new wallet
    Now you have 2 tasks in the list.

### 7. `find` - Find tasks

Finds tasks which contain the specified keyword.

#### Format:

    find KEYWORD

#### Example of usage:

    find CS

#### Expected Outcome:

    Here are the matching tasks in your list:
    1.[D][✘] CS3230 Assignment 1 (by: 20 Feb 2019 11:59PM)
    2.[E][✘] CS2106 Midterm (at: 07 Mar 2020 01:00PM)

### 8. `undo` - Undo a task operation

Undo a task operation. Only the following commands can be undone: `todo`, `deadline`, `event`, `done` and `delete`.

#### Format:

    undo

#### Example of usage:

    done 1

    undo

#### Expected Outcome:

    Nice! I've marked this task as done:
        [D][✔] CS3230 Assignment 1 (by: 20 Feb 2019 11:59PM)

    Noted! I've reverted your mark as done command:
    You have the following files in this folder.
        [D][✘] CS3230 Assignment 1 (by: 20 Feb 2019 11:59PM)

### 13. `bye` - Exit Duke

Quits the application.

#### Format:

    bye

#### Example of usage:

    bye

#### Expected Outcome:

> Duke exits
