# Duke - User Guide

## 1. Introduction

Duke is for those who prefer to use a desktop app for managing tasks. 
More importantly, Duke is optimized for those who prefer to work with a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). 
If you can type fast, Duke can get your task management done faster than traditional GUI apps. 
Interested? Jump to Section 2, “Quick Start” to get started. Enjoy!

## 2. Quick Start

1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest jar file for Duke [here](https://github.com/zixinn/duke/releases/tag/v0.2.1).
3. Copy the file to the folder you want to use as the home folder for Duke.
4. Double-click the file to start the app. The GUI should appear in a few seconds.
![Screenshot](Ui.png)
5. Type the command in the command box and press `Enter` to execute it.
6. Refer to Section 3, “Features” for details of each command.

## 3. Features 

**Command Format**

* Words in `UPPER_CASE` are the parameters to be supplied by the user e.g. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo read book`.
* Items in square brackets are optional e.g `[/d DATE] [/t TIME]` can be used as `/d 2020-01-28 /t 14:30-16:30` or as `/d 2020-01-28` or `/t 14:30-16:30`.

### 3.1. Adding a todo: `todo`
Adds a todo to the task list.

Format: `todo DESCRIPTION`

Example of usage: 

`todo read book`

Expected outcome:
```
Got it. I've added this task:
  [T][N] read book
Now you have 1 tasks in the list.
```

### 3.2. Adding a deadline: `deadline`
Adds a deadline to the task list.

Format: `deadline DESCRIPTION /by DATE TIME`

* Date must be in **yyyy-mm-dd** format.
* Time must be in **hh:mm** format.

Example of usage: 

`deadline return book /by 2020-02-02 18:00`

Expected outcome:
```
Got it. I've added this task:
  [D][N] return book (by: 2 Feb 2020 18:00)
Now you have 2 tasks in the list.
```

### 3.3. Adding an event: `event`
Adds an event to the task list.

Format: `event DESCRIPTION /at DATE START_TIME-END_TIME`

* Date must be in **yyyy-mm-dd** format.
* Time must be in **hh:mm** format.

Example of usage: 

`event project meeting /at 2020-01-27 14:00-16:00`

Expected outcome:
```
Got it. I've added this task:
  [E][N] project meeting (at: 27 Jan 2020 14:00-16:00)
Now you have 3 tasks in the list.
```

### 3.4. Listing all tasks: `list`
Lists all tasks in the task list.

Format: `list`

Example of usage: 

`list`

Expected outcome:
```
Here are the tasks in your list:
1.[T][N] read book
2.[D][N] return book (by: 2 Feb 2020 18:00)
3.[E][N] project meeting (at: 27 Jan 2020 14:00-16:00)
```

### 3.5. Marking a task as done: `done`
Marks a task in the task list as done.

Format: `done INDEX`

* Marks the task at the specified INDEX as done.
* The index refers to the index number shown in the displayed task list.
* The index must be a positive integer 1, 2, 3, …​

Example of usage: 

`done 2`

Expected outcome:
```
Nice! I've marked this task as done:
  [D][Y] return book (by: 2 Feb 2020 18:00)
```

### 3.6. Updating a task: `update`
Updates a task in the task list.

Format: `update INDEX [/d DATE] [/t TIME]`

* Updates the task at the specified INDEX.
* The index refers to the index number shown in the displayed task list.
* The index must be a positive integer 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* Date must be in **yyyy-mm-dd** format.
* Time must be in **hh:mm** format.

Example of usage: 

`update 3 /d 2020-01-28 /t 14:30-16:30`

Expected outcome:
```
Noted. I've updated this task:
  [E][N] project meeting (at: 28 Jan 2020 14:30-16:30)
```

### 3.7. Locating tasks by name: `find`
Finds tasks with description containing any of the given keywords.

Format: `find KEYWORD`

* The search is case sensitive. e.g book will not match Book
* Only the description is searched.
* Partial words will be matched e.g. bo will match book

Example of usage: 

`find book`

Expected outcome:
```
Here are the matching tasks in your list:
1.[T][N] read book
2.[D][Y] return book (by: 2 Feb 2020 18:00)
```

### 3.8. Locating tasks by date: `get`
Finds tasks with the given date.

Format: `find DATE`

* Date must be in **yyyy-mm-dd** format.

Example of usage: 

`get 2020-02-02`

Expected outcome:
```
Here are the matching tasks in your list:
1.[D][Y] return book (by: 2 Feb 2020 18:00)
```

### 3.9. Deleting a task: `delete`
Deletes a task in the task list.

Format: `delete INDEX`

* Deletes the task at the specified INDEX.
* The index refers to the index number shown in the displayed task list.
* The index must be a positive integer 1, 2, 3, …​

Example of usage: 

`delete 1`

Expected outcome:
```
Noted I've removed this task:
  [T][N] read book
Now you have 2 tasks in the list.
```

### 3.10. Exiting the program: `bye`
Exits the program.

Format: `bye`

Example of usage: 

`bye`

Expected outcome:
```
Bye. Hope to see you again soon!
```

### 3.11. Saving the data
Duke data are saved in the hard disk automatically after any command that changes the data.
There is no need to save manually.

## 4. FAQ

**Q**: How do I transfer my data to another Computer?

**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Duke folder.

## 5. Command Summary

* **Bye** : `bye`

* **Deadline** : `deadline DESCRIPTION /by DATE TIME`

  e.g. `deadline return book /by 2020-02-02 18:00`
  
* **Delete** : `delete INDEX`

  e.g. `delete 1`

* **Done** : `done INDEX`

  e.g. `done 2`

* **Event** : `event DESCRIPTION /at DATE START_TIME-END_TIME`

  e.g. `event project meeting /at 2020-01-27 14:00-16:00`

* **Find** : `find KEYWORD`

  e.g. `find book`

* **Get** : `get DATE`

  e.g. `get 2020-02-02`

* **List** : `list`

* **Todo** : `todo DESCRIPTION`

  e.g. `todo read book`

* **Update** : `update INDEX [/d DATE] [/t TIME]`

  e.g. `update 3 /d 2020-01-28 /t 14:30-16:30`
