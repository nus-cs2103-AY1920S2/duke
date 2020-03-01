# Task Manager User Guide

## 1. Introduction
Welcome to the Task Manager! Fight your way up the bell curve using this fabulous task management application!

Task Manager is your Personal Assistant to help you take notes of various tasks in your studies at NUS,
which includes To-dos, Deadlines, or Events. To-dos are there to help you jot down any thing 
academic related that you want to cover. Deadlines are for assignment/homework. Never getting chased
by deadlines again by knowing all of your deadlines in advance! And events for anything related,
e.g. a conference that you want to go. Many extra features come along such as, finding tasks
according to a specific day or month, see nearest deadlines, and mark a task as completed.
 
Task Manager (TM) is for NUS Students who want to use a desktop application for managing tasks.
TM is optimised for those who like to work with Command Line Interface (CLI) while still having
a renovated GUI. If you can type fast, which most NUS Computing students can, you will
really love this app. Try it out!

## 2. Quickstart
1. Ensure that you have Java `11` or above installed in your Computer.
2. Download the latest jar file [here](https://github.com/dinhnhobao/duke/releases/tag/v0.2).
3. Copy the file to the folder you want to use as the home folder for your Duke.
4. Double-click the file to start the app. The GUI should appear in a few seconds.
5. Type the command in the command box and press `Enter` or the "Send" button to execute it.
6. Some example commands you can try:
* **`todo`**`pray to the bell curve god`: adds a Todo Task called "pray to bell curve god". A Todo has no timestamp.
Why need a timestamp when you can "pray to the bell curve god" everyday?
* *`list`* : lists all tasks. For now, you should see the task "pray to the bell curve god" above.
* **`deadline`**`CS3230 Assignment 1/2020-03-07`: adds a deadline with a date in format YYYY-MM-DD. Who uses
MM-DD anyways? *ahem*
* **`deadline`**`Buy present/2020-03-08`: adds another deadline.
* **`sort deadlines`**: Ta-da! Now you can see the nearest deadlines that you have. Never worried about
missing a deadline anymore!
* *`exit`* : exits the app. Bye bye!

## 3. Screenshot
![Screenshot of Ui](Ui.png)

## 4. Features & Usage

### 1. `list`
* Format: `list`
* List down all of the current tasks that you have.

### 2. `todo` 
* Format: `todo <ACTIVITY>`
* Adds a **ToDo** task to the task list.
* Example: `todo cs3230 assignment 1`

### 2. `deadline`
* Format: `deadline <ACTIVITY>/ <YYYY-MM-DD>`
* Adds a **Deadline** task to the task list.
* Example: `deadline CS2105 Assignment 1/ 2020-03-01`

### 3. `event`
* Format: `event <ACTIVITY>/ <YYYY-MM-DD>`
* Adds an **Event** task to the task list.
* Example: `event mid-term CS3243/ 2020-03-07`

### 5. `done`
* Format: `done <INDEX>`
* Marks the task as done at the specified `INDEX`. The index refers to the index number shown in the displayed task list. The index *must be a positive integer* 1, 2, 3, ...
* Example: `done 3`

### 6. `delete`
* Format: `delete <INDEX>`
* Deletes the task at the specified `INDEX`. The index refers to the index number shown in the displayed task list. The index *must be a positive integer* 1, 2, 3, ...
* Example: `delete 2`

### 7. `find`
* Format: `find <NAME>`
* Finds and returns the tasks whose names contain **STRING**.
* Example: `find CS`

### 8. `sort deadlines`
* Format: `sort deadlines`
* Finds and returns the nearest deadline tasks in your task list.
* Example: `sort deadlines`

### 9. `bye`
* Format: `bye`
* The application sends a Good bye note and exits.