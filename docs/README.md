# User Guide

## Features 

### Feature 1 : Add
This feature allows the user to add new tasks to the list. The tasks can be of three types, "todo", "event" and "deadline". 

#### Usage
1. Type todo : "todo [task name]"
2. Type event : "event [task name] /at [yyyy-mm-dd HHmm]" 
3. Type deadline : "deadline [task name] /by [yyyy-mm-dd HHmm]"

**Example of usage:** 

1. todo clean room
2. event project meeting /at 2020-02-10 1900
3. deadline essay /by 2020-03-01 2200

**Expected outcome:**

The program will display a confirmation that the task has been successfully added to the list.

Example: 

Got it. I've added this task:

 [T][N] clean room
 
Now you have 3 tasks in the list.

### Feature 2 : Delete
This feature allows the user to delete tasks from the list, using task number.

#### Usage
"delete [task number]"

**Example of usage:** 

1. delete 1
2. delete 6

**Expected outcome:**

The program will display a confirmation that the task has been successfully deleted from the list.

Example: 

Noted. I've removed this task:

 [T][N] clean room
 
Now you have 2 tasks in the list.

### Feature 3 : Find
This feature allows the user to find tasks in the list, using a keyword.

#### Usage
"find [keyword]"

**Example of usage:** 

1. find meeting
2. find essay

**Expected outcome:**

The program will display the tasks that match your search request.

Example: 

Here are the matching tasks in your list:

 2. [E][N] project meeting (at: Feb-10-2020 19:00)
 
### Feature 4 : Mark as Done
This feature allows the user mark a task as completed using the task number.

#### Usage
"done [task number]"

**Example of usage:** 

1. done 1
2. done 3

**Expected outcome:**

The program will display a confirmation that the task has been marked as completed. The next time the list is displayed, the [N] will be changed to a [Y].

Example: 

Nice! I've marked this task as done:

[T][N] clean room

### Feature 5 : List
This feature allows the user to view all the tasks in the list.

#### Usage
"list"

**Example of usage:** 

1. list

**Expected outcome:**

The program will display the entire list. It uses [T], [E] and [D] to represent todo, event and deadline respectively. It also uses [N] and [Y] to denote if task is done or not respectively.

Example: 

Here is the list of tasks:

1. [T][N] clean room
2. [E][N] project meeting (at: Feb-10-2020 19:00)
3. [D][N] essay (by: March-01-2020 22:00)

### Feature 6 : Exit
This feature allows the user to exit the application.

#### Usage
"bye"

**Example of usage:** 

1. bye

**Expected outcome:**

The program will close the window after showing a confirmation that message was correctly interpreted.

Example: 

Bye. Hope to see you again soon!




 

 



