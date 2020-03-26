# User Guide

1. [Introduction](#1-introduction)
    
2. [Features](#2-features)
    
    2.1. Adding tasks: `todo` `deadline` `event`
    
    2.2. Showing tasks: `list` `find`
    
    2.3. Marking tasks: `done` `undo`
    
    2.4. Managing tasks: `delete` `save`
    
    2.5. Getting help: `help`
    
    2.6. Conversing with Duke: `hello` `bye` `poke`
    
# 1. Introduction

`Duke` is a simple task managing application interacted with through the Command Line Interface.

![Duke Screenshot](/docs/Ui.png)

# 2. Features 

### 2.1. Adding tasks: `todo` `deadline` `event`

#### `todo`

Format: `todo NAME`

Adds a todo task to the task list.

Example:
 
* `todo make pineapple tarts`

Expected outcome:

* `Added:`

    `[T] [ ] make pineapple tarts`

#### `deadline`

Format: `deadline NAME /by DATE` - date in YYYY-MM-DD

Adds a deadline task to the task list.

Example:
 
* `deadline make pineapple tarts /by 2020-03-05`

Expected outcome:

* `Added:`

    `[D] [ ] make pineapple tarts (by: Thursday, March 5, 2020)`
    
#### `event`
    
Format: `event NAME /at DATETIME`

Adds a event task to the task list.

Example:
 
* `event make pineapple tarts /by Friday`

Expected outcome:

* `Added:`

    `[E] [ ] make pineapple tarts (at: Friday)`
    
    
### 2.2. Showing tasks: `list` `find`  
    
#### `list`
    
Format: `list`

Shows a list of all tasks.

#### `find`

Format: `find PARTIAL_TASKNAME`

Finds a task from the task list with a matching substring.

Example:
 
* `find A`

Expected outcome:

* `Found matching task(s):`

    `[T] [ ] eat an apple`
    
    `[D] [ ] MAKE A LIST`
    
### 2.3. Marking tasks: `done` `undo`

#### `done`

Format: `done ID`

Mark a task with id as completed.

Example:
 
* `done 1`

Expected outcome:

* `Good job for completing the task:`

    `[T] [/] sleep`

#### `undo`

Format: `undo ID`

Mark a task with id as incomplete.

Example:
 
* `undo 1`

Expected outcome:

* `o.o well... good luck completing the task:`

    `[T] [ ] sleep`

### 2.4. Managing tasks: `delete` `save`

#### `delete`

Format: `delete ID`

Deletes the task with the specified id from the task list.

Example:
 
* `delete 7`

Expected outcome:

* `Removed task:`

    `[E] [ ] make pineapple tarts (at: Friday)`

#### `save`

Format: `save`

Saves the task list to a .txt file.

### 2.5. Getting help: `help`

#### `help`

Format: `help`

Gets help on the commands you can enter.

### 2.6. Conversing with Duke: `hello` `bye` `poke`

#### `hello`

Format: `hello`

Exchange hello message with Duke.

#### `bye`

Format: `bye`

Exchange goodbye message with Duke.

#### `poke`

Format: `poke`

Poke Duke for a response.
