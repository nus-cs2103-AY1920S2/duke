# User Guide

## Features 

### Feature 1 : Add Tasks
You can add tasks to your task list so that you can manage them. There are three type of tasks, namely *todo*,
 *deadline*, and *event*:
 
* *todo* tasks only specify content of the task
* *deadline* tasks specify content and deadline of the task
* *event* tasks specify content and venue of the task
 
### Feature 2 : Delete Tasks
You can remove any task with a reference to their index in the task list.

### Feature 3 : Mark Status of Tasks
All tasks are set as undone by default when you add them to the task list. You can mark them as done if you have
finished the task. 

### Feature 4 : List Down All Tasks
You can view the tasks you already have in your task list. They are arranged in the order of the time that they 
were added in. The type, status, and content of tasks are shown.

### Feature 5 : Search for Tasks
You can search for tasks in the list that match your keyword. They are arranged in the order of the time that they 
were added in.

### Feature 6 : Massive Operations 
You can perform several operations of the same command at one go. You can do it for adding and deleting tasks, 
as well as marking task status.




## Usage

### `todo` - Add *todo* Tasks
Add a *todo* task to the end of the task list.

Example of usage: 

`todo content of task`

Expected outcome:

`Got it. I've added this task:`<br/>
`[T][✘] content of task`<br/>
`Now you have x tasks in the list.`


### `deadline` - Add *deadline* Tasks
Add a *deadline* task to the end of the task list.

Example of usage: 

`deadline content of task /by some timing`

Expected outcome:

`Got it. I've added this task:`<br/>
`[D][✘]] content of task (by: some timing)`<br/>
`Now you have x tasks in the list.`

### `event` - Add *event* Tasks
Add an *event* task to the end of the task list.

Example of usage: 

`event content of task /at some venue`

Expected outcome:

`Got it. I've added this task:`<br/>
`[E][✘] content of task (at: some venue)`<br/>
`Now you have x tasks in the list.`

### `delete` - Delete Tasks
Delete a task in the list. The following number is the index of the task in the list.

Example of usage: 

`delete 1`

Expected outcome:

`Noted. I've removed this task:`<br/>
`[T][✘] content of task`<br/>
`Now you have x tasks in the list.`

### `done` - Mark Task as Done
Mark a task as done. The following number is the index of the task in the list.

Example of usage: 

`done 1`

Expected outcome:

`Noted. I've marked this task as done:`<br/>
`[D][✓] content of task (by: some timing)`<br/>

### `list` - List Down All Tasks
List down all existing tasks in the list, in the order of time that they were added in.

Example of usage: 

`list`

Expected outcome:

`Here are the tasks in your list:`<br/>
`1. [D][✓] content of task (by: some timing)`<br/>
`2. [E][✘] content of task (at: some venue)`

### `search` - Search for Tasks
Search for tasks that contains the keyword entered. They are ordered by the time added in.

Example of usage: 

`search keyword`

Expected outcome:

`Here are the matching tasks in your list:`<br/>
`1. [D][✓] keyword blabla (by: some timing)`<br/>
`2. [T][✘] ... keyword`

### `; ` - Massive Operations
Performs several operations of the same type in one command. Each operations is separated by a semicolon ';'
and a whitespace ' '. 

Example of usage: 

`todo A; B; C; D; E`

Expected outcome:

`Got it. I've added this task:`<br/>
`[T][✘] A`<br/>
`[T][✘] B`<br/>
`[T][✘] C`<br/>
`[T][✘] D`<br/>
`[T][✘] E`<br/>
`Now you have x tasks in the list.`

### `bye` - Exit the Program

Prints the closing message and terminate the program.

Example of usage: 

`bye`

Expected outcome:

`Bye. Hope to see you again soon!`<br/>
Program terminates.