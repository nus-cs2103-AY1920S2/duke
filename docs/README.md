## Navigation
1. [Introduction](#introduction)

2. [Instructions](#instructions)

3. [Features](#features)

+ 3.1 [Overview](#overview)

+ 3.2 [Task List](#task-list)

+ 3.3 [Storage](#storage)

4. [Usage](#usage)

+ 4.1 [`bye`](#4.1)
  
+ 4.2 [`todo`](#todo)
  
+ 4.3 [`deadline`](#deadline)
  
+ 4.4 [`event`](#event)
  
+ 4.5 [`list`](#list)
  
+ 4.6 [`find`](#find)

+ 4.7 [`done`](#done)

## Introduction

Project Duke is a personalised chat bot for a user
who can use Command Line Interface to input and keep 
track of their tasks. Generally, user can add, mark 
the task as complete, delete and find a combination of 
tasks. 

![](Ui.png)

## Instructions
  1. Download the jar file [here](#build/libs/duke-0.1.3.jar).
  2. Open the jar file to launch the app.
  3. Type in the text box your command or instruction. Hit `Enter` or the `Send` button to execute.
  4. See Section [4. Usage] for different commands.

## Features 

### Overview

> The app currently processes 3 types of user tasks: **Todo**, **Deadline** and **Event**. Each of these tasks will require a different input from the user to be recorded and kept track of their completion. 



### Task List

> All the tasks are stored in the list of tasks according to the order they were added. Each task is mapped to an index which corresponds to its position in the list. User can thus refer to a specific task or multiple tasks based on their indexes. There is also a "list" command which allows user to check the ordering of the tasks.

### Storage

> The tasks are stored to and loaded from "/data/duke.txt". When the application starts, data is loaded from "/data/duke.txt" into the task list to be displayed or manipulated. After each user command, if there is any modification to any task or the task list, the data in "/data/duke.txt" will be overwritten with the current state of the task list.

## Usage

### 4.1 `bye`

Exits from the application.

Example of usage: 

`bye`

Expected outcome:

`Bye. Hope to see you again soon!`

### 4.2 `todo [TaskAction]`
 
Adds a todo task into the list.

Example of usage: 

`todo wake up`

Expected outcome:

```
Got it. I've added this task: 
    [T][x] wake up
Now you have 1 tasks in the list.
```

### 4.3 `deadline [TaskAction] /by [Date Time]`

Adds a task representing a deadline to the list with a deadline specified

Example of usage: 

`deadline return book /by 20/02/2020 2020`

Expected outcome:

```
Got it. I've added this task:
 [D][x] return book (by: Feb 20 2020, 8:20PM)
Now you have 2 tasks in the list.
```

### 4.4 `event [TaskAction] /at [Date Time]`

Adds a task representing an event to the list with the time specified

Example of usage: 

`event book sharing /at 20/02/2020 2040`

Expected outcome:

```
Got it. I've added this task:
 [E][x] book sharing (at: Feb 20 2020, 8:40PM)
Now you have 3 tasks in the list.
```

### 4.5 `list`

Prints all the tasks in the list with their indexes.

Example of usage: 

`list`

Expected outcome:

```
Here are the tasks in your list:
1. [T][x] wake up
2. [D][x] return book (by: Feb 20 2020, 8:20PM)
3. [E][x] book sharing (at: Feb 20 2020, 8:40PM)
```

### 4.6 `find [TaskAction]` 

Finds all tasks whose TaskAction contains the keyword specified.

Example of usage:

`find book`

Expected outcome:

```
Here are the matching tasks in your list:
1. [D][x] return book (by: Feb 20 2020, 8:20PM)
2. [T][x] borrow book (at: Feb 20 2020, 8:40PM)`
```

### 4.7 `done [Index]` 

Mark indexed tasks as done.

Example of usage:

`done 1 2`

Expected outcome:

```
Nice! I've marked this task(s) as done:
1. [D][^] return book (by: Feb 20 2020, 8:20PM)
2. [T][^] borrow book (at: Feb 20 2020, 8:40PM)`
```

