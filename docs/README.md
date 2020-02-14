# Duke User Guide

## Product Overview

**Duke** is a Personal Assistant Chatbot that can help you keep track of your tasks at hand. 
**Duke** can help you remember your to-dos,deadlines and events. 
**Duke** will also remind you of your upcoming and overdue undone tasks.
With **Duke**, you can get an overview of your tasks at a glance. 
![Representative Screenshot](Ui.png?raw=true)
*Figure 1: The graphical user interface for **Duke**.*

## About
Here are some notations that Duke uses:

|Notation|Meaning|
|:------:|:-----:|
|[T]|To-do|
|[D]|Deadline|
|[E]|Event|
|[Y]|Task is completed|
|[N]|Task is incomplete|

## Getting Started
Download the jar file for Duke [here](https://github.com/CheyanneSim/duke/releases/tag/A-Release).

## Features 

### Add a Task
Add a to-do, deadline, or event to your task list.

### Delete a Task
Delete a task from your task list that you don't want to keep track of anymore.

### Mark a task as done
Check off the tasks that you have completed.

### List your tasks
Have an overview of all the tasks that you have at hand.

### Get reminded of deadlines
Duke will remind you of the upcoming deadlines and overdue deadlines that you have not completed on start up.

### Find your task
Search for a task from your task list.

## Usage

### `todo [description]`

Add a to-do to your task list.

Example of usage: 

`todo read book`

Expected outcome:

```
    Got it. I've added this task:`
       [T][N] read book`
    Now you have 1 tasks in the list.
```

### `deadline [description] \by [yyyy-mm-dd]`

Add a deadline to your task list.

Example of usage: 

`deadline return book /by 2020-06-06`

Expected outcome:

```
     Got it. I've added this task:
       [D][N] return book (by: Jun 6 2020)
     Now you have 2 tasks in the list.
```

### `event [description] \at [yyyy-mm-dd]`

Add an event to your task list.

Example of usage: 

`event project meeting /at 2020-08-06`

Expected outcome:

```
     Got it. I've added this task:
       [E][N] project meeting (at: Aug 6 2020)
     Now you have 3 tasks in the list.
```

### `list`

List all your task in your task list.

Example of usage: 

`list`

Expected outcome:

```
     Here are the tasks in your list:
     1. [T][Y] read book
     2. [D][N] return book (by: Jun 6 2020)
     3. [E][N] project meeting (at: Aug 6 2020)
```

### `done [Task number]`

Mark a task as done.

Example of usage: 

`done 1`

Expected outcome:

```
     Nice! I've marked this task as done:
       [T][Y] read book
```

### `find [Keyword]`

Find a task from your task list.

Example of usage: 

`find book`

Expected outcome:

```
     Here are the matching tasks in your list:
     1. [T][Y] read book
     2. [D][N] return book (by: Jun 6 2020)
```

### `delete [Task number]`

Delete a task from your task list.

Example of usage: 

`delete 3`

Expected outcome:

```
    Noted. I've removed this task:`
       [E][N] project meeting (at: Aug 6 2020)
    Now you have 2 tasks in the list.
```

### `bye`

Exit the application.

Example of usage: 

`bye`

Expected outcome:

```
     Bye. Hope to see you again soon!
```