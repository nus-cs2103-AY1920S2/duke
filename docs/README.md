# User Guide
Welcome to Hardy's Duke v0.2. Duke provides a simple way to track your tasks
and upcoming events.

## Features 

### Add and Manipulate tasks
* Add task
    * Todo
    * Deadline
    * Event
* List
* Done
* Delete
* Date
* Find

### Additional
* Undo
* Help
* Bye


## Usage

### `todo` - Adds a new task to your list.

A new task is added to the list. Its name follows what you type following "todo".

Example of usage: 

`todo Tomorrow's Homework`

Expected outcome:

`Got it. I've added this task:`<br>
`[T][✘] Tomorrow's Homework`<br>
`Now you have 1 tasks in the list`

Note that the number of tasks varies.

<br/>

### `deadline` - Adds a new deadline to your list.

A new task with a deadline is added to the list. 
Its name follows what you type following "deadline" and a date `yyyy-MM-dd` must follow.
The name of the deadline and date must be separated bby `/by`.

Example of usage: 

`deadline Biology Assignment /by 2020-03-10`

Expected outcome:

`Got it. I've added this task:`<br>
`[D][✘] Biology Assignment (by: Mar 10 2020 12.00am)`<br>
`Now you have 2 tasks in the list`

Note that the number of tasks varies.

<br/>

### `event` - Adds a new event to your list.

A new event with a date and time is added to the list. 
Its name follows what you type following "event" and a date `yyyy-MM-dd` must follow.
Optionally, include a `HH:mm` after the date. 
The name of the event and date must be separated bby `/at`.

Example of usage: 

`event Joe Hisaishi with SSO /at  2020-02-22 19:30`

Expected outcome:

`Got it. I've added this task:`<br>
`[E][✘] Joe Hisaishi with SSO (at: Feb 22 2020 7.30pm)`<br>
`Now you have 3 tasks in the list`

Note that the number of tasks varies.

<br/>

### `list` - Displays all tasks in the list.

Example of usage: 

`list`

Example expected outcome:

`1.[T][✘] Tomorrow's Homework`<br>
`2.[D][✘] Biology Assignment (by Mar 10 2020 12.00am)`<br>
`3.[E][✘] Joe Hisaishi with SSO (at: Feb 22 2020 7.30pm)`<br>

Outcome for empty list:

`Empty List. You are currently free! Upz lah!`

<br/>

### `done` - Sets a task in the list to 'done' status.

The task is set to done. An `index` based on the numbered list must follow the command.

Example of usage: 

`done 1`

Expected outcome:

`Nice! I've marked this task as done:`<br>
`[T][√] Tomorrow's Homework`<br>

<br/>

### `delete` - Removes a task from the list.

The task specified is removed. An `index` based on the numbered list must follow the command.

Example of usage: 

`delete 1`

Expected outcome:

`Noted! I've removed this task:`<br>
`[T][√] Tomorrow's Homework`<br>
`Now you have 2 tasks in the list.`

<br/>

### `date` - Shows all tasks on a certain date.

All tasks, specifically `deadline` and/or `event` type tasks, 
that occur on a date `yyyy-MM-dd` are displayed.

Example of usage: 

`date 2020-03-09`

Expected outcome:

`1.[E][✘] Midterm Assessment (at: Mar 9 2020 9.00am)`<br>
`2.[D][✘] Science Quiz (by: Mar 9 2020 11.59pm)`<br>
`You have 2 things happening on: Mar 9 2020`

<br/>

### `find` - Shows all tasks that match a keyword.

All tasks that match a keyword/phrase are displayed. The search term must follow the `find` command.

Example of usage: 

`date Assignment`

Expected outcome:

`1.[D][✘] Biology Assignment (by Mar 10 2020 12.00am)`<br>
`2.[T][✘] Submit English Assignment`<br>
`You have 2 matching tasks for keyword: Assignment`

<br/>

### `undo` - Undoes the latest change to the task list.

Undoes the latest change and reverts the task list prior to it.

Example of usage: 

`undo`

Possible Expected outcome after a previous `delete` command:

`Got it. I've added this task:`<br>
`[E][✘] Joe Hisaishi with SSO (at: Feb 22 2020 7.30pm)`<br>
`Now you have 5 tasks in the list.`

Outcome for no prior changes:

`Nothing to undo.`

<br/>

### `help` - Prints all possible user commands.

All commands possible are printed along with some explanations.

<br/>

### `bye` - Exits the program.

