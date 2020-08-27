# User Guide

## Introduction
Dobby SPEWs is a Harry Potter-themed program that helps you keep track of your tasks.

## Preview
![UI Preview](/docs/Ui.png)

## Features
### Add tasks 
Adds three different types of tasks - TODO, EVENT, DEADLINE

### Usage

#### `todo <description>` 

Example of usage: 

`todo read book`

Expected outcome:

`Got it. I've added this task:
  [T][✘] borrow book
Now you have 1 tasks in the list.`

#### `deadline <description>` 

Example of usage: 

`deadline return book /by 2019-10-15`

Expected outcome:

`Got it. I've added this task:
  [D][✘] return book (by: OCTOBER 15 2019)
Now you have 2 tasks in the list.`


#### `event <description>` 

Example of usage: 

`event project meeting /at Mon 2-4pm`

Expected outcome:

`Got it. I've added this task:
  [E][✘] project meeting (Sat: Mon 2-4pm)
Now you have 3 tasks in the list.`

### List tasks 
List all tasks and their status.

#### `list` 
### Usage
Example of usage: 

`list`

Expected outcome:

`Here are the tasks in your list:
1. [T][✘] borrow book`
`2. [D][✘] return book (by: OCTOBER 15 2019)
3. [E][✘] project meeting (Sat: Mon 2-4pm)`

### Mark task as done
Mark a task as done and edit their status.
#### `done <taskNumber>` 

### Usage
Example of usage: 

`done 2`

Expected outcome:

`Nice! I've marked this task as done: 
  [D][✓] return book (by: OCTOBER 15 2019)`
  
### Delete a task
Delete a task from tasklist.
#### `delete <taskNumber>` 

### Usage
Example of usage: 

`delete 2`

Expected outcome:

`Nice! I've deleted this task: 
  [D][✓] return book (by: OCTOBER 15 2019)`

### Find a task
Find any task that matches the word entered.
#### `find <word>` 

### Usage
Example of usage: 

`find book`

Expected outcome:

`Here are the matching tasks in your list: 
  [D][✓] return book (by: OCTOBER 15 2019)`
  
### Archive
Archive current tasks and start afresh.
#### `archive` 

### Usage
Example of usage: 

`archive`

Expected outcome:

`Archived!`

### History
Shows list of most recently archived tasks. 
Cannot be used unless archive has been used at least once.
#### `history` 

### Usage
Example of usage: 

`history`

Expected outcome:

`Here are your archived tasks:
1. [T][✘] borrow book`
`2. [D][✘] return book (by: OCTOBER 15 2019)
3. [E][✘] project meeting (Sat: Mon 2-4pm)`
  
### Exit
Exits a program and saves current tasks.
#### `bye` 

### Usage
Example of usage: 

`bye`

Expected outcome:

`Dobby has no master. Dobby is freee now!`
