# User Guide

## 1. Introduction

Duke is an interactive To-Do List.

## 2. Quick Start
1. Download the `duke.jar` file [here](https://github.com/ziyingli/duke/releases/tag/A-Release).
2. Copy the file to the folder you want to use. 
3. Double click the file to start the app. 

## 3. Features

**Command Format**
- Words in `UPPER_CASE` are the parameters to be supplied by the user. e.g. in `todo TASK`, `TASK` is a parameter which can be used as `todo buy snacks`

### 3.1. Adding a todo task: `todo`
Format: `todo TASK`
Examples: 
- `todo eat`
    - Adds a todo task with the description `eat` 

### 3.2. Adding a deadline task: `deadline`
Format: `deadline TASK /by DATE`
- `DATE` is in the format `yyyy-mm-dd`

Examples: 
- `deadline homework /by 2020-10-10`
    - Adds a deadline task `homework` with the deadline `10 Oct 2020`

### 3.3. Adding an event task: `event`
Format: `event TASK /at DATE`
- `DATE` is in the format `yyyy-mm-dd`

Examples: 
- `event meeting /at 2020-10-10`
    - Adds an event task `meeting` with the date `10 Oct 2020`

### 3.4. Listing all tasks: `list`
Shows a list of all tasks. 
Format: `list`

### 3.5. Marking a task as done: `done`
Marks a task at the specified index as done. 
Format: `done INDEX`
- `INDEX` has to be a positive integer.

Example: 
- `done 3` 
  - Marks the third task as done. 

### 3.6. Deleting a task: `delete`
Deletes the task at the specified index. 
Format: `delete INDEX`
- `INDEX` has to be a positive integer.

Example:
- `delete 3`
  - Deletes the third task in the list.

### 3.7. Finding tasks by keyword: `find`
Finds and lists all tasks that contain the specified keyword. 
Format: `find KEYWORD`

Example:
- `find afternoon` 
  - Returns a list of all tasks that contain the word `afternoon`

### 3.8. Sorting tasks: `sort`
Sorts the tasks according either chronological order or description, depending on the type of task given. 
Format: `sort TYPE`
- `TYPE` can be either one of `todos`, `deadlines` or `events`
- If `sort todos` is entered, todo tasks will be sorted by description. 
- If `sort deadlines` or `sort events` is entered, deadline tasks or event tasks will be sorted by chronological order. 

Example:
- `sort deadlines`
  - Returns a list of deadline tasks sorted by chronological order. 