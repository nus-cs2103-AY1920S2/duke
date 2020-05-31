# User Guide

## Interface
<img src="Ui.png" width="400">

## Overview
Tinga is an Personal Assistant Chatbot that helps you keep track of your tasks.

## Features 

### `Create` 
Create a tasks (To do, event or deadline) and add it to your tasks list.
You can specify dates for events and deadline tasks.

### `Edit`
Edit or modify the description of a task.

### `Delete` 
Delete a task from the list.

### `Search` 
Search the list for tasks containing a keyword.

### `View` 
View list of all tasks.

## Usage

### 1. `todo` - Adds a To do task

#### Format

    todo {description} 

Creates a To do task and adds it to the list.

#### Example of usage: 

    todo read book

#### Expected outcome:

    Got it. I've added this task:
    [T][✗] read book
    Now you have 5 tasks in the list.

### 2. `deadline` - Adds a Deadline task

#### Format

    deadline {description} /by {yyyy-mm-dd}

Creates a Deadline task and adds it to the list.

#### Example of usage: 

    deadline borrow book /by 2020-02-19

#### Expected outcome:

    Got it. I've added this task:
    [D][✗] borrow book (by: Feb 19 2020)
    Now you have 6 tasks in the list.
    
### 3. `event` - Adds an Event task

#### Format

    event {description} /at {yyyy-mm-dd}

Creates an Event task and adds it to the list.

#### Example of usage: 

    event return book /at 2020-02-20

#### Expected outcome:

    Got it. I've added this task:
    [E][✗] return book (at: Feb 20 2020)
    Now you have 6 tasks in the list.
    
### 4. `update` - Edits description of task

#### Format

    update {task index} {description}

Edits and modifies description of task.

#### Example of usage: 

    update 2 dance

#### Expected outcome:

    Noted. I've updated this task:
    [E][✗] dance (at: Feb 16 2020)
    
### 5. `delete` - Deletes task from list

#### Format

    delete {task index}

Removes task from list.

#### Example of usage: 

    delete 2 

#### Expected outcome:

    Noted. I've removed this task:
    [T][✗] read book
   
### 5. `done` - Marks task as completed

#### Format

    done {task index}

Marks task as completed.

#### Example of usage: 

    done 2 

#### Expected outcome:

    Nice! I've marked this task as done:
    [E][✓] dance (at: Feb 16 2020)

### 6. `find` - Search for task

#### Format

    find {keyword}

Searches list for tasks including keyword.

#### Example of usage: 

    find book

#### Expected outcome:

    Here are the matching tasks in your list:
    1. [D][✗] borrow book (by: Feb 19 2020)
    2. [T][✗] read book
    
### 7. `list` - View list of tasks

#### Format

    list

Displays the list of all tasks.

#### Example of usage: 

    list

#### Expected outcome:

    Here are the tasks in your list:
    1. [D][✓] finish assignment (by: Feb 19 2020)
    2. [E][✓] dance (at: Feb 16 2020)
    3. [D][✗] borrow book (by: Feb 19 2020)
    4. [T][✗] read book
    
### 8. `bye` - Exits program

#### Format

    bye

Ends tinga program.

#### Example of usage: 

    bye

#### Expected outcome:

    Bye. Hope to see you again soon!
    
