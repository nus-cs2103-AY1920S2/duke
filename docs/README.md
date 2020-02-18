# User Guide

## Interface
<img src="Ui.png" width="400">

## Overview
Tinga is an Personal Assistant Chatbot that helps you keep track of your tasks.

## Features 

### Feature 1 - Create 
Create a tasks (To do, event or deadline) and add it to your tasks list.
You can specify dates for events and deadline tasks.

### Feature 2 - Edit 
Edit or modify the description of a task.

### Feature 3 - Delete 
Delete a task from the list.

### Feature 4 - Search 
Search the list for tasks containing a keyword.

### Feature 5 - View 
View list of all tasks.

## Usage

### 1. todo - Adds a To do task

#### Format

    todo {description} 

Creates a To do task and adds it to the list.

#### Example of usage: 

    todo read book

#### Expected outcome:

    Got it. I've added this task:
    [T][✗] read book
    Now you have 5 tasks in the list.

### 2. deadline - Adds a Deadline task

#### Format

    deadline {description} /by {yyyy-mm-dd}

Creates a Deadline task and adds it to the list.

#### Example of usage: 

    deadline borrow book /by 2020-02-19

#### Expected outcome:

    Got it. I've added this task:
    [D][✗] borrow book (by: Feb 19 2020)
    Now you have 6 tasks in the list.
    
### 3. event - Adds an Event task

#### Format

    event {description} /at {yyyy-mm-dd}

Creates an Event task and adds it to the list.

#### Example of usage: 

    event return book /at 2020-02-20

#### Expected outcome:

    Got it. I've added this task:
    [D][✗] return book (by: Feb 20 2020)
    Now you have 6 tasks in the list.
