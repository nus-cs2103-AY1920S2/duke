# User Guide

## Description

Duke is a virtual assistant that is able to keep track of a list of tasks that you have. 

## Features 

The following are features that is offered by Duke. 
    1. Adding a task to the list. 
    2. Deleting a task from the list. 
    3. Marking a task as done
    4. Display the full list of tasks
    5. Find a task by keyword. 
    6. Reminders

### Feature 1: Add tasks

You can enter 3 different tasks type to be stored in Duke's list, namely todo, deadline and event. 

### Feature 2: Deleting a task

You can delete a task from duke's list

### Feature 3: Mark task as done

You will be able to mark a task as completed. 

### Feature 4: List tasks

You will be able to view all the tasks that are currently in your task list. Including ones that are marked as done. 

### Feature 5: Find tasks

You will be able to easily filter tasks by keywords. 

### Feature 6: Reminders

You will be able to see a list of reminders of deadlines that you have yet to complete.

## Usage

### 1.`todo` - Add a todo type of task to the list 

Adds a todo type of task and automatically updates duke.txt. 

Format: 

`todo {description}`

Example of usage: 

`todo Running`

Expected outcome:

`Got it, I've added this task:

[T][NOT-DONE] running

Now you have 1 task(s) in the list.`


### 2.`deadline` - Add a deadline type of task to the list 

Adds a deadline type of task. Tasks with deadline types takes in a date and time as well. 

Format:

`deadline {description} /by {YYYY-MM-DD} {HHMM}`

Example of usage: 

`deadline return book /by 2020-12-01 1800`

Expected outcome:

`Got it, I've added this task:

[D][NOT-DONE] return book (by: Dec 1 2020 06:00)

Now you have 1 task(s) in the list.`



### 3.`event` - Add a event type of task to the list 

Adds a event type of task. Tasks with event types takes in a venue or date as additional parameters 

Format:

`event {description} /at {venue/date}`

Example of usage: 

`event Concert /at Stadium`

Expected outcome:

`Got it, I've added this task:

[E][NOT-DONE] Concert (at: Stadium)

Now you have 1 task(s) in the list.` 


### 4.`Delete` - Delete a task from the list 

Deletes the specified index of the task from the list 

Format:

`delete {task index}`

Example of usage: 

`delete 1`

Expected outcome:

`Alright, I've added this task:

[E][NOT-DONE] Concert (at: Stadium)

Now you have 0 task(s) in the list.`


### 5.`Done` - mark a task from the list as completed 

Mark the specified index's task from the list as completed. 

Format:

`done {task index}`

Example of usage: 

`done 1`

Expected outcome:

`Nice! I've marked this task as done:

[T][DONE] running`

### 6.`find` - Find a task 

Return the task with the keyword that was specified.

Format:

`find {keyword}`

Example of usage: 

`find lunch`

Expected outcome:

`Here are the matching tasks in your list: 

3.[T][NOT-DONE] Have lunch

`

### 7.`list` - List the number of tasks currently in your task list 

Return the task list with all of the current tasks. 

Format:

`list`

Example of usage: 

`list`

Expected outcome:

`Here are the tasks in your list: 

1.[T][DONE] running
2.[T][NOT-DONE] return book
3.[T][NOT-DONE] Have lunch 

`
### 8.`reminders` - reminder for deadlines

shows the deadline that are upcoming and not yet completed

Format:

`reminders`

Example of usage: 

`reminders`

Expected outcome:

`Here are the upcoming deadline tasks in your list: 

1.[D][NOT-DONE] submit essay (by: Feb 10 2020 11:59PM)
2.[D][NOT-DONE] Return book (by: Jun 25 2020 06:00PM)

`





