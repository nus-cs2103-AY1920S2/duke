# MySponge User Guide

## Product Overview
**MySponge** is a versatile task tracking tool which serves to facilitate management of personal commitments. **MySponge** features are efficient, minimalistic and easy-to-use, appealing to anyone who wishes to better manage their tasks at hand.

## Product Interface
<img src="Ui.png" width="400">

## Features Present

### Feature 1 - `Create Tasks`
Users are able to create tasks, which will be stored in **MySponge's** database. There are 3 main categories of task that can be created, namely _ToDo_, _Deadline_ and _Event_.

### Feature 2 - `Read Tasks`
Users are able to obtain and view all the current tasks in **MySponge's** database. Users will be able to observe the type of tasks present, their tags and completion progress.

### Feature 3 - `Update Tasks`
Users are able to update the progress of every task, from incomplete to completed, represented by a _cross_ and _check_ respectively.

### Feature 4 - `Delete Tasks`
Users are able to remove specific tasks from **MySponge's** database if deemed neccessary.

### Feature 5 - `Tag Tasks`
Users are able to add a specific tag to a task, which acts as an efficient label which can be searched upon. Eg. #NUS

### Feature 6 - `Find Tasks`
Users are able to search and display tasks using keywords. Tasks with keywords present will be displayed to the user.


## Usage

### `todo` 
**Purpose:**

This keyword creates a new todo task.

**Example of usage:**

`todo (name of task)`

**Expected outcome:**

_Task of type todo will be created and added to **MySponge's** database_.

### `deadline`
**Purpose:**

This keyword creates a new deadline task.

**Example of usage:**

`deadline (name of task) /by (YYYY-MM-dd HHmm)` Note: Date and Time must be expressed in proper format specified.

**Expected outcome:**

_Task of type deadline will be created and added to **MySponge's** database._

### `event`
**Purpose:**

This keyword creates a new event task.

**Example of usage:** 

`event (name of task) /at (details)`

**Expected outcome:**

_Task of type event will be created and added to **MySponge's** database._

### `list`

**Purpose:**

This keyword generates the list of tasks currently being tracked in **MySponge**.

**Example of usage:** 

`list`

**Expected outcome:**

_A list of tasks will be displayed to the user._


### `done`
**Purpose:**

This keyword alters a specific task's progress, from incomplete to completed.

**Example of usage:** 

`done (integer value)`

**Expected outcome:**

_The task represented with the corresponding integer value index will be deemed complete, the cross beside the task details will be replaced with a check instead._

### `delete`
**Purpose:**

This keyword removes a specific task's from **MySponge**.

**Example of usage:** 

`delete (integer value)`

**Expected outcome:**

_The task represented with the corresponding integer value index will be removed from database._

### `tag`
**Purpose:**

This keyword places a tag on a specific task.

**Example of usage:** 

`tag (integer value) #(tag name)`

**Expected outcome:**

_The task represented with the corresponding integer value index will have a newly created tag visible._

### `find`
**Purpose:**

This keyword returns a list of tasks containing the keyword specified by the User.

**Example of usage:** 

`find (keyword)`

**Expected outcome:**

_All tasks contaning the keyword specified will be displayed._

### `bye`
**Purpose:**

This keyword closes the application window.

**Example of usage:** 

`bye`

**Expected outcome:**

_Application window will be closed._
