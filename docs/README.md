# User Guide

## Introduction
This is a **chat-bot** called **Duke** for tracking the user's tasks.

<img src="Ui.png" width="400">

### Type of tasks
There are three types of task.

* `todo` - A task to be done at anytime.
* `deadline` - A task to be done before the time stated.
* `event` - A task to be done at the time stated.

## Basic Features 

### Feature 1 - Adding a task

#### Usage
Allows the user to add a task into the list.

##### keyword `[task type]`
* `todo [task description]`
* `deadline [task description]/[yyyy-mm-dd]`
* `event [task description]/[yyyy-mm-dd]` 

Example of usage: 

* `todo read a book`
* `deadline return book/2020-01-12`
* `event duke's party/2020-01-12`

Expected outcome:

<img src="feature1.png" width="400">

### Feature 2 - Deleting a task

#### Usage
Allows the user to delete a task in the list.
Requires a valid **index number** of the task.


#### keyword `delete [index]`

Example of usage:

`delete 2` (delete the second task in the list)

Expected outcome:

<img src="feature2.png" width="400">

### Feature 3 - Mark a task as done
Allows the user to mark a task in the list as done.
Requires a valid **index number** of the task.

#### keyword `done [index]`

Example of usage:

`done 1` (Mark the first task as done in the list)

Expected outcome:

<img src="feature3.png" width="400">

### Feature 4 - Print the existing list of task
Allows the user to print the list of task.

#### keyword `list`

Example of usage:

`list` (Print the list of tasks)

Expected outcome:

<img src="feature4.png" width="400">

### Feature 5 - Find the task using a **keyword**
Allows the user find the list of task containing the **keyword**.

#### keyword `find [keyword]`

Example of usage:

`find book` (Print a list of task containing the word "book")

Expected outcome

<img src="feature5.png" width="400">

### Feature 6 - Statistic
Allows the user see the summary of the tasks in the list.
Calculate the total number of tasks by each type.

#### keyword `statistic`

Example of usage:

`statistic` (Print summary of the tasks)

Expected outcome:

<img src="feature6.png" width="400">

### Feature 7 - Save and exit
Allows the user to save the list of task to a file.

#### keyword `bye`

Example of usage:

`bye` (Save the list of task to a file)

Expected outcome:

<img src="feature7.png" width="400">

# Acknowledgement
JavaFX Tutorials by Jeffry Lum

