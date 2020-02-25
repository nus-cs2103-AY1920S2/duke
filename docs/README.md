# User Guide

## Introduction
---
__Duke__ is an application that allows you to record your daily tasks.

![Image](Ui.png)

## Features
---

### Adding tasks
You can add and classify your tasks into three types: _todo_, _deadline_, and _event_.

### Listing out the tasks
Duke lists out the tasks you have added previously.

### Adding status to a task
You can mark your task as _done_ or _undone_.

### Deleting a task
You can delete an unwanted task from the existing tasks.

### Finding tasks
You can search for tasks based on their descriptions.

### Sorting the tasks
You can sort your tasks in the list by _time_ (time which the task has to be done), 
_task type_ (type of the tasks) and _task name_ (name of the task, in alphabetical order).

## Usage
---

### Greeting Duke: `hello` or `hi`
Greets Duke.

- Format: `hello` or `hi`

### Adding a _todo_ task: `todo`
Adds a _todo_ task to the tasks list.

- Format: `todo <task descriptions>`

- Example: `todo buy book`
    
    Expected outcome: 
    ```
    Got it. I've added this task:
    [T][x] buy book
    Now you have 1 task in the list.
    ``` 

### Adding a _deadline_: `deadline`
Adds a _deadline_ to the tasks list.

> Kindly take note that the date and time should be in the format of __yyyy-MM-dd HHmm__.

- Format: `deadline <task descriptions> /by <date and time>`

- Example: `deadline submit project /by 2020-02-02 2020`
    
    Expected outcome: 
    ```
    Got it. I've added this task:
    [D][x] submit project (by: Feb 2 2020 20:20)
    Now you have 2 tasks in the list.
    ``` 

### Adding an _event_: `event`
Adds an _event_ to the tasks list.

> Kindly take note that the date and time should be in the format of __yyyy-MM-dd HHmm__.

- Format: `event <task descriptions> /at <date and time>`

- Example: `event birthday party /at 2020-02-18 1900`
    
    Expected outcome: 
    ```
    Got it. I've added this task:
    [E][x] birthday party (by: Feb 18 2020 19:00)
    Now you have 3 tasks in the list.
    ```

### Listing the tasks: `list`
Lists out all the existing tasks.

- Format: `list`

- Example: `list`
    
    Expected outcome: 
    ```
    Here are the tasks in your list:
    1. [T][x] buy book
    2. [D][x] submit project (by: Feb 2 2020 20:20)
    3. [E][x] birthday party (by: Feb 18 2020 19:00)
    ```

### Marking a task as _done_: `done`
Changes the status of a task to _done_.

- Format: `done <index>`

- Example: `done 1`
    
    Expected outcome: 
    ```
    Nice! I've mark this task as done:
    [T][/] buy book
    ```

### Marking a task as _undone_: `undone`
Changes the status of a task to _undone_.

- Format: `undone <index>`

- Example: `undone 1`
    
    Expected outcome: 
    ```
    The task's status has been changed to incomplete:
    [T][x] buy book
    ```

### Deleting a task: `delete`
Removes a task from the tasks list.

- Format: `delete <index>`

- Example: `delete 1`
    
    Expected outcome: 
    ```
    Noted. I've removed this task:
    [T][x] buy book
    Now you have 2 tasks in the list.
    ```

### Finding tasks: `find`
Finds tasks by their description.

- Format: `find <keyword>`

- Example: `find project`
    
    Expected outcome: 
    ```
    Here are the matching tasks in your list:
    1. [D][x] submit project (by: Feb 2 2020 20:20)
    2. [D][x] CS2103T project (by: Mar 1 2020 23:59)
    ```

### Sorting the tasks: `sort`

#### Sorting the tasks by _time_
Sorts the tasks by time, in ascending order.

- Format: `sort /by time`

- Example: `sort /by time`
    
    Expected outcome: 
    ```
    Here are the tasks in your list:
    1. [D][x] submit project (by: Feb 2 2020 20:20)
    2. [E][x] birthday party (by: Feb 18 2020 19:00)
    3. [D][x] CS2103T project (by: Mar 1 2020 23:59)
    4. [T][x] buy book
    ```
    
#### Sorting the tasks by _type_
Categorizes the tasks by their types.

- Format: `sort /by task type`

- Example: `sort /by task type`
    
    Expected outcome: 
    ```
    Here are the tasks in your list:
    1. [D][x] submit project (by: Feb 2 2020 20:20)
    2. [D][x] CS2103T project (by: Mar 1 2020 23:59)
    3. [E][x] birthday party (by: Feb 18 2020 19:00)
    4. [T][x] buy book
    ```    

#### Sorting the tasks by _name_
Sorts the tasks by their names, in alphabetical order.

- Format: `sort /by task name`

- Example: `sort /by task name`
    
    Expected outcome: 
    ```
    Here are the tasks in your list:
    1. [E][x] birthday party (by: Feb 18 2020 19:00)
    2. [T][x] buy book
    3. [D][x] CS2103T project (by: Mar 1 2020 23:59)
    4. [D][x] submit project (by: Feb 2 2020 20:20)
    ```

### Exiting the window: `bye`
Closes the window.

- Format: `bye`

## Command Summary
---
* __Greet:__ `hello` or `hi`
* __Add todo:__ `todo <task descriptions>`
    * e.g. `todo buy book`
* __Add deadline:__ `deadline <task descriptions> /by <date and time>`
    * e.g. `deadline submit project /by 2020-02-02 2020`
* __Add event:__ `event <task descriptions> /at <date and time>`
    * e.g. `event birthday party /at 2020-02-18 1900`
* __List:__ `list`
* __Done:__ `done <index>`
    * e.g. `done 1`
* __Undone:__ `undone <index>`
    * e.g. `undone 1`
* __Delete:__ `delete <index>`
    * e.g. `delete 1`
* __Find:__ `find <keyword>`
    * e.g. `find project`
* __Sort by time:__ `sort /by time`
* __Sort by task type:__ `sort /by task type`
* __Sort by task name:__ `sort /by task name`
* __Exit:__ `bye`
