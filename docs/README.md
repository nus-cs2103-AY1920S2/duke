# User Guide
## Introduction
NUS-Duke is an elementary task manager which takes in user input 
through Command-Line-Interface-like text bar
and outputs result in the form of chat boxes.

## Quick start
  1. Ensure that your computer has installed Java `11`. 
  2. Download the latest `duke-0.2.jar` file.
  3. Copy the file to a folder that does **not** require administrator 
  permission to write or read files. This will prevent the occurrence
  of `IOException` when the application tries to read data from a file
  or write data into it.
  4. Double click the file to start the application.
  
## Features 

* Add a task into the task list. 
  * There are three types of tasks: todo, deadline and event.
* Mark a task as done.
* List tasks that contain some key word.
* List all tasks or tasks with a specified priority level.
* Change the priority level of a task.
* Remove a task from task list.
* Remove all tasks or tasks that are marked as done.
* Show the greeting message again, which contains the list of valid instructions.

## Usage

### `t/todo [string description]` - Add a todo task

A todo task will be created and added to the end of the task list. Upon creation,
 the task is marked as `Not Done` and its priority level is `ordinary` (i.e. normal).
 The description of the task can contain white spaces. 
 
 The application will print the task created, and report the number of tasks 
 (of all types) in the whole list.

Example of usage: 

`todo read CS3243 textbook`

Expected outcome:

    Noted. The following task is stored:
    [T][Not Done][  ordinary  ] read CS3243 textbook
    Currently there is/are 1 task(s) in the task list.

### `d/deadline [string description] /by YYYY-MM-DD` - Add a deadline task

A deadline task will be created and added to the end of the task list.  Upon creation,
the task is marked as `Not Done` and its priority level is `ordinary` (i.e. normal).
 The description of the task can contain white spaces. Note that you must specify the date
 of the deadline, and you need to type `/by` between task description and the date.

 The application will print the task created, and report the number of tasks 
 (of all types) in the whole list.
 
Example of usage: 

`d complete CS2103 iP /by 2020-02-24`

Expected outcome:

    Noted, the following task is stored:
    [D][Not Done][  ordinary  ] complete CS2103 iP
       (by: 2020-02-24, MONDAY)
    Currently there is/are 2 task(s) in the task list.

### `e/event [string description] /at YYYY-MM-DD` - Add an event task

An event task will be created and added to the end of the task list.  Upon creation,
the task is marked as `Not Done` and its priority level is `ordinary` (i.e. normal).
 The description of the task can contain white spaces. Note that you must specify the date
 of the deadline, and you need to type `/at` between task description and the date.

 The application will print the task created, and report the number of tasks 
 (of all types) in the whole list.
 
Example of usage: 

`e CS2103 group meeting /at 2020-02-19`

Expected outcome:

    Noted, the following task is stored:
    [E][Not Done][  ordinary  ] CS2103 group meeting
       (at: 2020-02-19, WEDNESDAY)
    Currently there is/are 3 task(s) in the task list.

### `done [a positive integer indicating the index of the task]` - Mark a task as done

The application will set the indexed task as `Done` and print it. Note that
the index specified by the user is one-based (i.e. the index of the first task
in the list is 1, instead of 0).

Example of usage 1: 

`done 3`

Expected outcome:

    Noted, the following task is marked as done:
    [E][  Done  ][  ordinary  ] CS2103 group meeting
       (at: 2020-02-19, WEDNESDAY)  

Example of usage 2: 

`done 3` (second time calling this instruction)

Expected outcome:

    The specified task is already marked as done::
    [E][  Done  ][  ordinary  ] CS2103 group meeting
       (at: 2020-02-19, WEDNESDAY)

### `f/find [a character sequence]` - List tasks containing some key word

The application will print the list of tasks whose description contains the
key word input by the user. The index of these tasks are also shown, facilitating
additional actions on these tasks. Note that the key word cannot contain white spaces.

Example of usage: 

`f iP`

Expected outcome:

    1 task(s) were found containing keyword iP:
    2) [D][Not Done][  ordinary  ] complete CS2103 iP
       (at: 2020-02-24, MONDAY)   
 
### `l/list (*optional*: priority level)` - List all tasks or tasks with a specified priority level
 
The application will print the list of all tasks, or tasks whose priority level matches
the level specified by the user. The index of these tasks are also shown, facilitating
additional actions on these tasks. The priority level include: l/low [not important], n/normal [ordinary],
h/high [important], and t/top [very important]).
 
Example of usage 1: 
 
`list`
 
Expected outcome:
 
    Here is the task list:
    1) [T][Not Done][  ordinary  ] read CS3243 textbook
    2) [D][Not Done][  ordinary  ] complete CS2103 iP
       (by: 2020-02-24, MONDAY)
    3) [E][  DOne  ][  ordinary  ] CS2103 group meeting 
       (at: 2020-02-19, WEDNESDAY)
    Currently there is/are 3 tasks(s) in the task list.   
 
Example of usage 2: 
  
`l n`
  
Expected outcome:
  
    Here is the list of tasks with your specified priority level:
    1) [T][Not Done][  ordinary  ] read CS3243 textbook
    2) [D][Not Done][  ordinary  ] complete CS2103 iP
       (by: 2020-02-24, MONDAY)
    3) [E][  DOne  ][  ordinary  ] CS2103 group meeting 
       (at: 2020-02-19, WEDNESDAY)
    3 task(s) belong to this priority level.
    Currently there is/are 3 tasks(s) in the task list. 
   
### `p/priority [a positive integer indicating the index of the task]` - Change the priority level of a task
  
The application will change the priority level of 
the indexed task and print it. Note that
the index specified by the user is one-based (i.e. the index of the first task
in the list is 1, instead of 0). The priority level include: l/low [not important], n/normal [ordinary],
h/high [important], and t/top [very important]).
  
Example of usage: 
  
`p 2 top`
  
Expected outcome:
  
    Noted. The priority level of the following task has been set:
    [D][Not Done][very important!!] complete CS2103 iP
       (by: 2020-02-24, MONDAY)
 
### `r/remove [a positive integer indicating the index of the task]` - Remove a task from the list
  
The application will delete the indexed task from the list and print it.  
It will also report the number of tasks in the list after deletion. Note that
the index specified by the user is one-based (i.e. the index of the first task
in the list is 1, instead of 0). 
  
Example of usage: 
  
`remove 1`
  
Expected outcome:
  
    Noted, the following task is removed from the list:
    [T][Not Done][  ordinary  ] read CS3243 textbook
    Currently there is/are 2 task(s) in the task list.

### `c/clear [a/all/d/done]` - Remove all tasks or tasks that are marked as done
  
If the user specified `d/done` after `c/clear`, the application will delete all tasks 
that are marked as `Done` from the list and print the remaining tasks. Noted that
after the deletion, the indexes of the remaining tasks are adjusted. If the user specified `a/all` after `c/clear`, the application will delete all tasks 
from the list.
  
Example of usage 1: 

`done 1`  (outcome not shown)
`c done`
  
Expected outcome:
  
    Noted, all completed tasks are removed.
    1) [D][Not Done][  ordinary  ] complete CS2103 iP
       (by: 2020-02-24, MONDAY)
    Currently there is/are 1 tasks(s) in the task list.      
    
Example of usage 2: 
  
`clear a`
  
Expected outcome:
  
    Noted, all tasks are removed. Task list is now empty.
    
### `hi/hello` - Show the greeting message again
  
The application will print the following message:

    Hello, this is NUS-Duke. Please give me an instruction followed by relevant description.
    The valid instructions are as follows:
        -- Add a task into the task list --
    - t/todo: add a todo task
    - e/event: add an event task
    - d/deadline: add a deadline task
    - done: mark a task as done
    - f/find: find tasks by searching some key word
    - l/list: list all tasks or tasks with a specified priority level
    - p/priority: change the priority level of a task
    - r/remove: remove a task from task list
    - c/clear: clear all tasks or only completed tasks
    - hi/hello: show this greeting message again
    - b/bye: exit the app

### `b/bye` - Exit the app

The application will print the following message and close after 1 second:

    Goodbye. See you next time!