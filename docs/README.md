# User Guide

> Your friendly neighbourhood personal assistant chatbot. 

Contents:
1. [Features](#features)
    - [Simple to Use](#simple-to-use)
    - [Task Management](#task-management)
    - [Expense Management](#expense-management)
    - [Autosave Data](#autosave-data)
2. [Installation](#installation)
2. [Usage](#usage)
3. [Commands](#commands)

## Features 

### Simple to Use

The main interface of the application is through a chat system.
If you have used a messaging app before, it should be easy for you to use it.

### Task Management 

You can perform simple actions such as add, remove and mark tasks from a task list.
A basic search feature is also available for you to search from the task list.

There are 3 types of tasks:

Task Type   |Description
------------|------------
Todo        |The most basic task which one can mark as completed.
Deadline    |A todo task with a deadline.
Event       |An event task with start and end time.

### Expense Management

You can perform simple action such as adding and removing expense from an expense list.
The date of the expense will also be recorded.

The total expense will be automatically calculated for you.

### Autosave Data

All data will be saved automatically, and will be loaded up when you open the application again.

They will be saved in: `data/tasks.txt` and `data/expenses.txt`.

## Installation

Requirements:
- Java JDK 11

Download the jar file from [here](https://github.com/zwasd/duke/releases).

## Usage

To run the chatbot,
```
java -jar duke-*.jar
```
You will see an application similar to the one shown below:

![User Interface Screenshot](Ui.png)

Key [Commands](#commands) into the text box at the bottom of the window. 
You can press `<ENTER>` or click the `Send` button to send your messages.
The chatbot will instantly reply you.

If you key in commands that the chatbot does not recognize, you'll receive a random message from the chatbot.

## Commands

1. General
    - [`bye` - Exit the program](#bye---exit-the-program)
2. Task
    - [`tasks` - List tasks](#tasks---list-tasks)
    - [`todo` - Add a todo task](#todo---add-a-todo-task)
    - [`deadline` - Add a deadline task](#deadline---add-a-deadline-task)
    - [`event` - Add an event task](#event---add-an-event-task)
    - [`done` - Mark a task](#done---mark-a-task)
    - [`find` - Find a task](#find---find-a-task)
    - [`delete task` - Remove a task](#delete-task---remove-a-task)
3. Expense
    - [`expenses` - List expenses](#expenses---list-expenses)
    - [`expense` - Add an expense record](#expense---add-an-expense-record)
    - [`delete expense` - Remove an expense record](#delete-expense---remove-an-expense-record)

---

### `bye` - Exit the program

**Command:**

```
bye
```

**Result:**

Exits the program after 1 minute.

---

### `tasks` - List tasks

**Command:**

```
tasks
```

**Result:**

List all the tasks.

---

### `todo` - Add a todo task

**Command:**

```
todo [description]
```

**Arguments:**

Name            |Format             |Required   |Description
----------------|-------------------|-----------|--------------
`description`   |words              |yes        |Description of the task

**Example:**

```todo visit the the doctor```

**Result:**

Adds a todo task to the task list.

---

### `deadline` - Add a deadline task

**Command:**

```
deadline [description] /by [datetime]
```

**Arguments:**

Name            |Format             |Required   |Description
----------------|-------------------|-----------|--------------
`description`   |words              |yes        |Description of the task
`datetime`      |`yyyy-mm-dd hh:mm` |yes        |The date and time the task is dued on

**Example:**

```deadline visit the the doctor /by 2020-12-12 00:00```

**Result:**

Adds a deadline task to the task list.

---

### `event` - Add an event task

**Command:**

```
event [description] /from [start datetime] /to [end datetime]
```

**Arguments:**

Name            |Format             |Required   |Description
----------------|-------------------|-----------|--------------
`description`   |words              |yes        |Description of the event task
`start datetime`|`yyyy-mm-dd hh:mm` |yes        |The date and time the event starts
`end datetime`  |`yyyy-mm-dd hh:mm` |yes        |The date and time the event ends

**Example:**

```event doctor appointment /from 2020-12-10 08:00 /to 2020-12-10 14:00```

**Result:**

Adds an event task to the task list.

---

### `done` - Mark a task

**Command:**

```
done [index]
```

**Arguments:**

Name            |Format             |Required   |Description
----------------|-------------------|-----------|--------------
`index`         |number             |yes        |The number of the task in the task list

**Example:**

```done 1```

**Result:**

Marks a task as done.

---

### `find` - Find a task

**Command:**

```
find [description]
```

**Arguments:**

Name            |Format             |Required   |Description
----------------|-------------------|-----------|--------------
`description`   |words              |yes        |Partial description of the task

**Example:**

```find appointment```

**Result:**

Searches the tasks which contains the description and lists them.

---

### `delete task` - Remove a task

**Command:**

```
delete task [index]
```

**Arguments:**

Name            |Format             |Required   |Description
----------------|-------------------|-----------|--------------
`index`         |number             |yes        |The number of the task in the task list

**Example:**

```delete task 2```

**Result:**

Removes the task from the task list.

---

### `expenses` - List expenses

**Command:**

```
expenses
```

**Result:**

List all the expenses and view the total amount spent.

---

### `expense` - Add an expense record

**Command:**

```
expense [amount] /dollar [description] /on [date]
```

**Arguments:**

Name            |Format             |Required   |Description
----------------|-------------------|-----------|--------------
`amount`        |number             |yes        |Amount spent in dollars
`description`   |words              |yes        |Description of the expense
`date`          |`yyyy-mm-dd`       |yes        |The date of the expense

**Example:**

```expense 5 /dollars chicken rice /on 2020-01-25```

**Result:**

Adds a deadline task to the task list.

---

### `delete expense` - Remove an expense record

**Command:**

```
delete expense [index]
```

**Arguments:**

Name            |Format             |Required   |Description
----------------|-------------------|-----------|--------------
`index`         |number             |yes        |The number of the expense item in the expense list

**Example:**

```delete expense 1```

**Result:**

Removes the expense item from the expense list.
