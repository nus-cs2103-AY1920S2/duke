# User Guide

## Feature

* Keep track of tasks added to the task list.
* Complete task(s) by marking the status of the task(s) as done.
* Add diffrent types of tasks to the task list.
* Remove tasks using task ID(s) from the task list.
* Search tasks using key words.


## Usage

### `list` - To list all tasks on the task list.

Command: `list`

Example: `list`

Expected outcome:

```
Here are your tasks in your list:
1.[E][✓] have a party (at: Dec 20 2020)
2.[T][✘] read book
3.[D][✘] submit homework (by: Dec 12 2020)
```
---

### `done` - To mark one or more tasks as done.

Command: `done` <task id...>

Example: `done 2 3`

Expected outcome:

```
Nice! I've marked the following task as done:
[T][✓] read book
[D][✓] submit homework (by: Dec 12 2020)
```
---

### `todo` - To add new todo task to the task list.

Command: `todo` <task description>

Example: `todo play game`

Expected outcome:

```
Got it. I've added this task:
[T][✘] play game
Now you have 4 tasks in your list.
```
---

### `deadline` - To add new deadline task to the task list.

Command: `deadline` <task description> /by <YYYY-MM-DD>

Example: `deadline return book /by 2020-10-10`

Expected outcome:

```
Got it. I've added this task:
[D][✘] return book (by: Oct 10 2020)
Now you have 5 tasks in your list.
```
---

### `event` - To add new event task to the task list.

Command: `event` <task description> /at <YYYY-MM-DD>

Example: `event graduation /at 2021-05-15`

Expected outcome:

```
Got it. I've added this task:
[E][✘] graduation (at: May 15 2021)
Now you have 6 tasks in your list.
```
---

### `delete` - To delete one or more tasks from the task list.

Command: `delete` <task id...>

Example: `delete 2 3`

Expected outcome:

```
Got it. I've removed the following task:
[T][✓] read book
[D][✓] submit homework (by: Dec 12 2020)
Now you have 4 tasks in your list.
```
---


### `find` - To search task(s) that contain the keywords.

Command: `find` <keywords>

Example: `find book`

Expected outcome:

```
Here are the matching tasks in your list:
[D][✘] return book (by: Oct 10 2020)
```
---


### `bye` - To exit the program

Command: `bye`

Example: `bye`

Expected outcome:

```
-----------------------------------
Bye. Hope to see you again soon!
-----------------------------------
```
---
