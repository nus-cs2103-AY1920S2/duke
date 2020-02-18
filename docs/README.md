# User Guide

## Description
Duke is a simple task manager.

## Features
Duke offers the following features:
1. Add a new task to the list of tasks
2. Delete an existing task from the list of tasks
3. Mark a particular task as done upon completion
4. Display the entire list of tasks

### Add Task
To add a task, enter 'todo', 'deadline' and 'event' followed by relevant details.

### Delete Task
Delete a task from the task list.
 

### Marking Task(s) as Done 
Mark tasks as 'done' after completion.

### List Tasks
View all existing tasks.


## Usage
### `todo` - Add a todo task to the list

Adds a 'todo' task to the list of tasks and updates the list in `duke.txt`.

Example of usage:

`todo cs2101 tutorial`

Expected outcome:

~~~~~~~~~~~~~~~~~~~~~~~~~~~
I've added this task:

[T][X] cs2101 tutorial
~~~~~~~~~~~~~~~~~~~~~~~~~~~

### `deadline` - Add a Deadline task to the list

Adds a 'deadline' task to the list of tasks and updates the list in `duke.txt`. 

Example of usage:

`deadline cs2101 tutorial /by 2020-01-12`

Expected outcome:

~~~~~~~~~~~~~~~~~~~~~~~~~~~
I've added this task:

[D][X] cs2101 tutorial (by:Jan 12 2020)
~~~~~~~~~~~~~~~~~~~~~~~~~~~

### `event` - Add an Event task to the list

Adds a 'event' task to the list of tasks and updates the list in `duke.txt`. 

Example of usage:

`event project meeting /at 2020-06-12`

Expected outcome:

~~~~~~~~~~~~~~~~~~~~~~~~~~~
Alright, I've added this task:

[E][N] project meeting (at:Jun 12 2020)

You currently have 1 task(s) in the list.
~~~~~~~~~~~~~~~~~~~~~~~~~~~

![event_pic](images/Event.png)

### `List` - Displays the entire list of tasks 

Display the entire task list.

Example of usage:
 
`list`

* When there are tasks in the list of tasks: <br>
~~~~~~~~~~~~~~~~~~~~~~~~~~~
Here are your tasks!
1. [T][X] cs2101 tutorial
2. [D][X] cs2101 tutorial (by:Jan 12 2020)
3. [E][N] project meeting (at:Mar 12 2020)
~~~~~~~~~~~~~~~~~~~~~~~~~~~

### `done` - Mark a particular task as done

Upon completion of a task, you can mark the particular task as completed. 

Example of usage: 

`done 3`

Expected outcome:

~~~~~~~~~~~~~~~~~~~~~~~~~~~
Fantastic! This task is a done-deal!

[E][O] project meeting (at:Mar 12 2020)
~~~~~~~~~~~~~~~~~~~~~~~~~~~

### `delete` - Deletes the specified task from the list of tasks

Delete a particular task from the list of tasks in `duke.txt`

Example of usage: 

`delete 2`

Expected outcome:

~~~~~~~~~~~~~~~~~~~~~~~~~~~
Alright, I've removed this task: 

[D][X] return book (by:Jun 12 2020)
~~~~~~~~~~~~~~~~~~~~~~~~~~~


### `bye` - Closes the program
 
Exits the program
 
Example of usage: 
 
`bye`
 
Expected outcome:
 
~~~~~~~~~~~~~~~~~~~~~~~~~~~
Bye-bye! See you again, my friend! 
Please close this window if you do not wish to continue this session.
~~~~~~~~~~~~~~~~~~~~~~~~~~~


## Acknowledgements
The GUI was done based on the 
[JavaFX Tutorial](https://github.com/nus-cs2103-AY1920S2/duke/blob/master/tutorials/javaFxTutorialPart4.md)
written by Jeffry Lum.
With readme inspiration from Exeexe93.

