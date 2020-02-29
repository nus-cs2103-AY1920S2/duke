# Duke User Guide

## Introduction
Duke is a customized task manager with an easy to use GUI.
Tasks are categorized into **todos**, **deadlines** and **events**.
Duke supports basic CRUD features and other task management operations
such as searching, marking a task as done. 

## Features 

### Interactive GUI

Duke comes with an cute GUI which you can interact with by 
typing in commands and press `Enter` / click `Send`.
![Duke ui](Ui.png)

### Displaying your tasks
You can view what are your current tasks in Duke.

### Adding a task
Duke supports three kinds of tasks:
- todo
- deadline
- event

You can add either one of them to the task manager.
You can also specify a due date or scheduled date for deadline and
event respectively.

### Updating your tasks
You can mark a task as done, or delete a task from your current tasks.

### Searching for a task
You can search for tasks that match a given keyword.

### Sorting the deadlines
This feature comes in handy when you want to decide on which deadlinie
to work on first. Duke supports sorting the deadlines in chronological order.

### Storing your tasks
Duke will automatically save your tasks each time you update your tasks.
Your tasks will be stored even when you exit the application. On start up, 
Duke will also load the existing tasks without requiring you to reenter all the tasks again!

### Fast exit
You can exit Duke just by tying on keyboard, saving you the time to
move your hand to the mouse and click the exit button!

## Usage

### `hey` - Say hi to Duke

- Say hi to Duke and receive a warm welcome.

- Example of usage: 
  
  `hey`

- Expected outcome:

  `Hi Stan! This is Eric! Give me something to do so I can lose some weight!`

### `todo [description]` - Add a todo task

- Add a todo task with the given description to your list of tasks.

- Example of usage: 

  `todo read book`

- Expected outcome:

    ```
    Got it. I've added this task:
    [T][✘]read book
    now you have 1 tasks in the list.
    ```

### `deadline [description] /by [date]` - Add a deadline

- Add a deadline task with the given description and due date to your list of tasks.

- Example of usage: 

  `deadline return book /by 2020-06-08`

- Expected outcome:

    ```
    Got it. I've added this task:
    [D][✘]return book (by: Jun 8 2020)
    now you have 2 tasks in the list.
    ```

### `event [description] /at [date]` - Add an event

- Add an event with the given description and scheduled date to your list of tasks.

- Example of usage: 

  `event project meeting /at 2020-08-03`

- Expected outcome:

    ```
    Got it. I've added this task:
    [E][✘]project meeting (at: Aug 3 2020)
    now you have 3 tasks in the list.
    ```

### `list` - Display your current tasks

- Display your current list of tasks.

- Example of usage: 

  `list`

- Expected outcome:

    ```
    Here are all the tasks in your list:
    1. [T][✘]read book`
    2. [D][✘]return book (by: Jun 8 2020)
    3. [E][✘]project meeting (at: Aug 3 2020)
    ```

### `delete [index]` - Delete the task with the specified index in the task list

- Remove a task in the list of tasks according to the given index.

- Example of usage: 

  `delete 3`

- Expected outcome:

    ```
    Noted. I've removed this task:
    [E][✘]project meeting (at: Aug 3 2020)
    now you have 2 tasks in the list.
    ```

### `done [index]` - Mark the task with the specified index as done  

- Mark a task as done according to the given index.

- Example of usage: 

  `done 1`

- Expected outcome:

    ```
    Nice! Congratulations for completing this task:
    [T][✓]read book
    ```

### `find [keyword]` - Find tasks that match the keyword

- Find tasks that match the given keyword in your list of tasks.

- Example of usage: 

  `find book`

- Expected outcome:

    ```
    Here are the matching tasks in your list:
    1. [T][✓]read book
    2. [D][✘]return book (by: Jun 6 2020)
    ```

### `sort` - Sort the deadlines in chronological order

- Display added deadlines in chronological order to the user.

- Example of usage: 

  `sort`

- Expected outcome:

    ```
    Here are your deadlines in chronologial order:
    1. [D][✘]finish ip (by: Mar 1 2020)
    2. [D][✘]return book (by: Jun 6 2020)
    ```

### `bye` - Exit the Duke application

- Exit the Duke application

- Example of usage: 

  `bye`

- Expected outcome:

  The Duke GUI window closes.