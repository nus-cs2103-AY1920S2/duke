# User Guide

## Table of Contents
- [1. Introduction](#1-introduction)
- [2. Quick Start](#2-quick-start)
- [3. Features](#3-features)
  - [3.1 Create a todo](#31-create-a-todo-todo)
  - [3.2 Create an event](#32-create-an-event-event)
  - [3.3 Create a deadline](#33-create-a-deadline-deadline)
  - [3.4 List all added tasks](#34-list-all-added-tasks-list)
  - [3.5 Search for an added task](#35-search-for-an-added-task-find)
  - [3.6 Mark task as done](#36-mark-task-as-done-done)
  - [3.7 Delete a task](#37-delete-a-task-delete)
  - [3.8 Quit the program](#38-quit-the-program-bye)
 
## 1. Introduction
YATL is **Y**et **A**nother **T**odo **L**ist that helps a person to keep track of various things.

## 2. Quick Start
1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest `YATL.jar` [here](https://github.com/nelsontky/duke/releases).
3. Copy the file to the folder you want to use as the home folder for your todo list.
4. Double-click the file to start the app. The GUI should appear in a few seconds.

## 3. Features 
### 3.1 Create a todo: `todo`
Create a simple todo task with a description.

Format: `todo TASK`

Example:
<ul>
    <li>
        <code>todo Buy Maggi</code><br>
        Adds the todo task <code>Buy Maggi</code>.
    </li>
</ul>


### 3.2 Create an event: `event`
Create a event task with a description, start time, and date.

Format: `event TASK /at START_TIME DATE`

- `START_TIME`: `HH:mm`

- `DATE`: `dd/MM/yyyy`

Example:
<ul>
    <li>
        <code>event Take Temperature /at 14:00 29/2/2020</code><br>
        Adds the event <code>Take Temperature</code>. Set the start time to <code>14:00</code> and date to <code>29/02/2020</code>.
    </li>
</ul>

### 3.3 Create a deadline: `deadline`
Create a deadline task with a description, end time, and date.

Format: `deadline TASK /by END_TIME DATE`
- `END_TIME`: `HH:mm`
- `DATE`: `dd/MM/yyyy`

Example:
<ul>
    <li>
        <code>deadline CS2107 quiz /by 23:59 20/2/2020</code><br>
        Adds the deadline <code>CS2107 quiz</code>. Set the end time to <code>23:59</code> and date to <code>20/02/2020</code>.
    </li>
</ul>

### 3.4 List all added tasks: `list`
Lists all your added tasks.

Format: `list`

### 3.5 Search for an added task: `find`
Finds the tasks have that name that contain any of the given keywords. Keywords are not matched exactly, minor typos will still yield results.

Format: `find KEYWORD [MORE_KEYWORDS]`

Examples:
<ul>
    <li>
        <code>find magi</code><br>
        Returns any task that contains or <em>almost</em> contains the keyword <code>magi</code>.
    </li>
    <li>
        <code>find magi quiz temparature</code><br>
        Returns any task that contains or <em>almost</em> contains the keywords <code>magi</code>, <code>buy</code>, or <code>temparature</code>.
    </li>
</ul>

### 3.6 Mark task as done: `done`
Mark a task as done.

Format: `done INDEX`
- `INDEX`: Index number of a task shown when running the `list` command.

Example:
<ul>
    <li>
        <code>done 1</code><br>
        Marks task at index <code>1</code> as done.
    </li>
</ul>

### 3.7 Delete a task: `delete`
Delete a task.

Format: `delete INDEX`
- `INDEX`: Index number of a task shown when running the `list` command.

Example:
<ul>
    <li>
        <code>delete 1</code><br>
        Deletes the task at index <code>1</code>.
    </li>
</ul>

### 3.8 Quit the program: `bye`
Close the program.

Format: `bye`
