# Duke User Guide

## 1. Introduction
Duke is a Personal Assistant Chatbot that helps a person to keep track of various tasks. It allows user to add, delete, and search for tasks. It is also for those who prefer to use a desktop application to manage their tasks.

## 2. Quick Start
1. Ensure that you have Java `11` or above installed in your Computer.
2. Download the latest jar file [here](https://github.com/alushingg/duke/releases/tag/v0.3).
3. Copy the file to the folder you want to use as the home folder for your Duke.
4. Double-click the file to start the app. The GUI should appear in a few seconds.
5. Type the command in the command box and press `Enter` or the "Send" button to execute it.
6. Refer to Section 5, "Usage" for details of each command.

## 3. Screenshot
![Screenshot of Ui](Ui.png)

## 4. Features
#### 4.1 Add task

Adds a task as todo, deadline or event to your task list.

#### 4.2 List

Shows the overview of all the tasks in your task list.

#### 4.3 Mark a task as done

Marks as task as done.

#### 4.4 Delete a task

Deletes a task from your task list.

#### 4.5 Find a task

Finds the task from your task list.

#### 4.6 Undo command

Undo commands you made in the current session when you open the application.

#### 4.7 Save data

The data of your tasks are saved in the hard disk automatically after exiting the application. There is no need to save manually.

## 5. Usage
**Command Format**
- Words in `UPPER_CASE` are the parameters to be supplied by the user e.g. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo quiz`.
- Items in square brackets are optional e.g. `deadline DESCRIPTION /by DATE [TIME]` can be used as `deadline quiz /by 2020-06-06` or `deadline quiz /by 2020-06-06 13:00`.

### 5.1 `todo DESCRIPTION`

Adds a todo task to your task list.

Example of usage: 

`todo read book`

Expected outcome:

```
Got it. I've added this task:
 [T][N] read book
Now you have 1 tasks in the list.
```
### 5.2 `deadline DESCRIPTION /by DATE [TIME]`

Adds a deadline to your task list. The time of a deadline is optional. The date follows `yyyy-mm-dd` format while the time follows `hh:mm` format (24 hour).

Example of usage:

`deadline return book /by 2020-06-06 13:00`

Expected outcome:

```
Got it. I've added this task:
 [D][N] return book (by: Jun 6 2020 1:00PM)
Now you have 1 tasks in the list.
```
### 5.3 `event DESCRIPTION /at DATE [TIME]`

Adds an event to your task list. The time of a deadline is optional. The date follows `yyyy-mm-dd` format while the time follows `hh:mm` format (24 hour).

Example of usage:

`event meeting /at 2020-08-07 13:00`

Expected outcome:

```
Got it. I've added this task:
 [E][N] meeting (at: Aug 7 2020 1:00PM)
Now you have 1 tasks in the list.
```
### 5.4 `list`

Shows the list of all the tasks in your task list.

Example of usage:

`list`

Expected outcome:

```
Here are the tasks in your list:
1. [T][Y] read book
2. [D][N] return book (by: Jun 6 2020)
3. [E][N] project meeting (at: Aug 7 2020 1:00PM)
```
### 5.5 `done INDEX`

Marks a task as done. The index is the index of the task in your task list.

Example of usage:

`done 4`

Expected outcome:

```
Nice! I've marked this task as done:
 [T][Y] join sports club
```
### 5.6 `delete INDEX`

Deletes a task from your task list. The index is the index of the task in your task list.

Example of usage:

`delete 1`

Expected outcome:

```
Noted. I've removed this task:
 [T][Y] join sports club
Now you have 2 tasks in the list.
```
### 5.7 `find KEYWORD`

Finds the tasks from your task list which have their descriptions containing the given keyword.

Example of usage:

`find book`

Expected outcome:

```
Here are the matching tasks in your list:
1. [T][Y] read book
2. [D][N] return book (by: Jun 6 2020)
3. [T][N] borrow book
```
### 5.8 `undo`

Undo commands you made in the current session when you start the application.

Example of usage:

`undo`

Expected outcome:

```
You have successfully undo the previous command.

Here are the current tasks in your list:
1. [T][Y] read book
2. [D][N] return book (by: Jun 6 2020)
```
### 5.9 `bye`

Exits the application.

Example of usage:

`bye`

Expected outcome:

```
Bye. Hope to see you again soon!
```
