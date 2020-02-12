# User Guide

## Features


#### 1. Add a To-Do task: todo
#### 2. Add an Event task: event
#### 3. Add a Deadline task: deadline
#### 4. List all tasks: list
#### 5. Delete a task: delete
#### 6. Mark task as done: done
#### 7. Find tasks: find
#### 8. Postpone / snooze tasks: snooze

# Usage

## 1. `todo` - Adds a To-Do task.

Adds a To-Do task into the task list.

Example of usage: 

`todo CS2103 Assignment`

Expected outcome:

`Understood. I have added: [T] CS2103 Assignment 0`  
`Number of items in the list: 1`  


## 2. `event` - Adds an Event task.

Adds an Event task into the task list.

Format: `event` *task_name* `/at` *date*

Note: Date format (YYYY-MM-DD)

Example of usage: 

`event Temperature Taking /at 2020-03-03`

Expected outcome:

`Understood. I have added: [E] Temperature Taking 0 (at: Mar 3 2020)`  
`Number of items in the list: 2`  


## 3. `deadline` - Adds a Deadline task.

Adds a Deadline task into the task list.

Format: `deadline` *task_name* `/by` *date*

Note: Date format (YYYY-MM-DD)

Example of usage: 

`deadline Individual Project /by 2020-02-27`

Expected outcome:


`Understood. I have added: [D] Individual Project 0 (by: Feb 27 2020)`  
`Number of items in the list: 3`
  

## 4. `list` - Lists all tasks.

Lists all tasks in the task list.

Format: `list` 

Example of usage: 

`list`

Expected outcome:

`These items are in your list.`  
`1.[T] CS2103 Assignment 0`  
`2.[E] Temperature Taking 0 (at: Mar 3 2020)`  
`3.[D] Individual Project 0 (by: Feb 27 2020)`  


## 5. `delete` - Deletes a task.

Deletes one of the tasks in the task list.  

Format: `delete` *task_number*

Example of usage: 

`delete 3`

Expected outcome:  

`[D] Individual Project 0 (by: Feb 27 2020) has been removed.`  
`Number of items in list: 2`  

  
## 6. `done` - Marks a task as done.

Marks one of the tasks in the task list as done.  

Format: `done` *task_number*

Example of usage: 

`done 1`

Expected outcome:  

`You have completed this task.`  
`[T] CS2103 Assignment 1`


## 7. `find` - Find related tasks.

Find all tasks related to the keyword provided by the user.  

Format: `find` *keyword*

Example of usage: 

`find Assignment`

Expected outcome:  

`These are the tasks that matched what you were looking for.`  
`These items are in your list:`  
`1.[T] CS2103 Assignment 1`

## 8. Snooze
`snooze` - Snooze/postpone a task.

Postpone a task by a set number of days. 

Format: `snooze` *task_number* *number_of_days*

Example of usage: 

`snooze 2 2`

Expected outcome:  

`[E] Temperature Taking 0 (at: Mar 5 2020) has been snoozed for 2 days.`  

