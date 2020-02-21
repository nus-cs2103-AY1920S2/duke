# User Guide

## Features 

### Add task
Add a Deadline or Event or Todo task to the task list.
A Todo task has a description.
An event task has a description, a specific start and end time and a specific date.
A deadline task has a description and a specific date and time.

### Delete task
Delete a specific task based on the index of the task in the task list.

### Mark a task as done
Mark a specific task on the task list as done.

### Find task
Find a specific task from the task list based on a keyword.

### List all tasks
List all tasks on the task list.

### List filtered tasks
List all tasks that occur at or need to be fulfilled by a certain date.

## Usage

### `todo` - Add a Todo task 

This keyword adds a Todo task to the task list with the given description.

Example of usage: 

`todo read book`

Expected outcome:

```    _____________________________DUKE___________________________
          Got it. I've added this task:
          [T][X] read book
          Now you have 1 task in the list.
     _________--__--__--__--__--__--__--__--__--__--__--_________
```

### `deadline` - Add a Deadline task 

This keyword adds a Deadline task to the task list with the given description, date and time.

Example of usage: 

`deadline submit assignment /by 2020-12-30 1800`

Expected outcome:

```    _____________________________DUKE___________________________
          Got it. I've added this task:
          [D][X] submit assignment (by: Dec 30 2020 1800)
          Now you have 2 tasks in the list.
     _________--__--__--__--__--__--__--__--__--__--__--_________
```

### `event` - Add an Event task

This keyword adds a Deadline task to the task list with the given description, date and start and end time.

Example of usage: 

`event go carnival /at 2020-10-21 2-4pm`

Expected outcome:

```    _____________________________DUKE___________________________
          Got it. I've added this task:
          [E][X] go carnival (at: Oct 21 2020 2-4pm)
          Now you have 3 tasks in the list.
     _________--__--__--__--__--__--__--__--__--__--__--_________
```

### `delete` - Delete task

This keyword deletes a specific task based on the index of the task in the task list.

Example of usage: 

`delete 3`

Expected outcome:

```    _____________________________DUKE___________________________
          Noted. I've removed this task:
          [E][X] go carnival (at: Oct 21 2020 2-4pm)
          Now you have 2 tasks in the list.
     _________--__--__--__--__--__--__--__--__--__--__--_________
```

### `done` - Mark a task as done

This keyword marks a specific task on the task list as done, based on the index of the task in the task list.

Example of usage: 

`done 1`

Expected outcome:

```    _____________________________DUKE___________________________
          Nice! I've marked this task as done:
          [T][✓] read book
     _________--__--__--__--__--__--__--__--__--__--__--_________
```

### `find` - Find task

This keyword finds a specific task from the task list based on a keyword.

Example of usage: 

`find read`

Expected outcome:

```    _____________________________DUKE___________________________
          Here are the tasks in your list: 
          1. [T][✓] read book
     _________--__--__--__--__--__--__--__--__--__--__--_________
```

### `list` - List all tasks

This keyword lists all tasks on the task list.

Example of usage: 

`list`

Expected outcome:

```    _____________________________DUKE___________________________
          Here are the tasks in your list: 
          1. [T][X] read book
          2. [D][X] submit assignment (by: Dec 30 2020 1800)
    _________--__--__--__--__--__--__--__--__--__--__--_________
```

### `list` - Add an Event task

This keyword lists all tasks that occur at or need to be fulfilled by a certain date.

Example of usage: 

`list 2020-12-30`

Expected outcome:

```    _____________________________DUKE___________________________
          Here are the tasks in your list: 
          1. [D][X] submit assignment (by: Dec 30 2020 1800)
    _________--__--__--__--__--__--__--__--__--__--__--_________
```
