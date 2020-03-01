# User Guide

## Features 

### Feature #1 - Adding Tasks 
This feature allows users to add new tasks to the task list.
Tasks can be of type ToDo (these are tasks with no set time limit),
type Deadline (these are tasks that need to be done by some deadline),
and Event (these are tasks that take place on a particular day).

#### Format to add tasks
1. For ToDo tasks: "todo [task description]"
2. For Deadline tasks: "deadline [task description] /by [dd-mm-yyyy HHmm]"
3. For Event tasks: "event [task description] /at [dd-mm-yyyy HHmm]"

#### Example of usage:
1. todo laundry
2. deadline essay /by 02-02-2020 1600
3. event movie /at 03-03-2020 1400

#### Expected Outcome
The program will display a confirmation message once a task has been added successfully.
For example, for the command "todo laundry", you'd get the following message (this example assumes that you already had 
two tasks in your task list):

Got it. I've added this task:

[T] [✘] laundry

Now you have 3 tasks in your list.

### Feature #2 - Deleting Tasks 
This feature allows users to delete tasks from the list using task number.

#### Format to delete tasks
"delete [task number]"

#### Example of usage:
1. delete 1 

#### Expected Outcome
The program will display a confirmation message once a task has been deleted successfully.
For example, for the command "delete 1" where the 1st command is todo laundry,
you will get the following confirmation message:

Noted. I have removed this task:
 
[T] [✘] laundry

You now have 2 tasks in the list.

### Feature #3 - Find 
This feature allows users to find tasks from the list using keywords.

#### Format to find tasks
"find [keyword]"

#### Example of usage:
1. find essay 

#### Expected Outcome
The program will display the tasks that match your search request.
For example, for "find essay":

Here are the matching tasks in your list:
 
1. [D] [✘] submit GES essay (by: 02 Feb 2020 1600)

2. [T] [✘] CS2101 essay

### Feature #4 - Mark as Done 
This feature allows users to mark a task as completed using task number.
 
#### Format to mark as done
"done [task number]"

#### Example of usage:
1. done 1 

#### Expected Outcome
The program will display a confirmation message once a task has been marked as done successfully. 
For example, for the command "done 1" where the 1st command is todo laundry,
you will get the following confirmation message:

Nice! I've marked this task as done:
 
[T] [✓] laundry

### Feature #5 - List 
This feature lists all the tasks. 
 
#### Format to list
"list"

#### Example of usage:
1. list

#### Expected Outcome
It will display the entire list of tasks.

Here are the tasks in your list:
 
1. [T] [✓] laundry

2. [D] [✘] submit GES essay (by: 02 Feb 2020 1600)

3. [T] [✘] CS2101 essay

### Feature #6 - Sort 
This feature sorts the task list either chronologically or alphabetically.
 
#### Format to sort
1. To sort chronologically: "sort chronologically"
2. To sort alphabetically: "sort alphabetically"

#### Example of usage:
1. sort chronologically
2. sort alphabetically

#### Expected Outcome
It will display a confirmation message. For example if you do "sort chronologically":

I have sorted the tasks chronologically.

You can use the list command to view it.
 

### Feature #7 - Saving
This feature saves all the data to the hard disk and allows the user to exit the application if the want to. They can still continue to use the application after this.
 
#### Format to exit 
"bye"

#### Example of usage:
1. bye

#### Expected Outcome
Duke will reply with a goodbye message. Example:

Bye! Hope to see you again soon!

