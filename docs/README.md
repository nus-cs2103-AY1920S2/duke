# User Guide
Aelita is a personal assistant for organizing and managing your tasks at hand. Aelita can create todo, deadline and
 event tasks. Aelita can also find and delete tasks for you.
 
## Notations
<table>
<tr>
    <td>Notation</td>
    <td>Meaning</td>
</tr>
<tr>
    <td>[T]</td>
    <td>A todo task</td>
</tr>
<tr>
    <td>[D]</td>
    <td>A deadline task</td>
</tr>
<tr>
    <td>[E]</td>
    <td>An event task</td>
</tr>
<tr>
    <td>[✘]</td>
    <td>The task is not done</td>
</tr>
<tr>
    <td>[✓]</td>
    <td>The task is not done</td>
</tr>
<tr>
    <td><...></td>
    <td>Parameter to enter</td>
</tr>
</table> 

## Features 

## Exit 
Exits the application.

### Usage

### `bye`

The application will save the existing tasks and close the window. 

Example of usage: 

`bye`

Expected outcome:

The window closes.

## Create Todo
Creates a new Todo task.

### Usage

### `todo <description>`

Aelita creates a new todo task with the given description. If the description is missing, Aelita will prompt you to
 give one.

Example of usage: 

`todo Homework`

Expected outcome:

<pre>
I've got your back. Adding the new task:  
    [T][✘] Homework
</pre>

## Create Deadline
Creates a deadline task.

### Usage

### `deadline <description> /by <YYYY-MM-DD>`

Aelita creates a deadline task with the given description and date. If the description and date is not given, Aelita
 will prompt you to give one. If the date is not in the correct format, Aelita will prompt you to follow the required
 format.

Example of usage: 

`deadline project /by 2020-02-20`

Expected outcome:

<pre>
I've got your back. Adding the new task:  
    [D][✘] project (by Feb 20 2020)
</pre>

### Create Event
Creates an Event Task.

## Usage

### `event <description> /at <YYYY-MM-DD> <Start time>-<End time>`

Aelita creates a new event task with the given description, date, start time and end time. If any of the fields are
 not given, Aelita will prompt you to give one. If the date is not in the correct format, Aelita will prompt you to
 follow the required format.

Example of usage: 

`event project meeting /at 2020-02-20 2pm-4pm`

Expected outcome:

<pre>
I've got your back. Adding the new task:  
    [E][✘] project meeting (at: Feb 20 2020 2pm-4pm)
</pre>

### List all the Tasks 
List out all the tasks or tasks associated with a date.

## Usage

### `list`
Aelita lists out all the task in the application.

### `list <YYYY-MM-DD>`
Aelita lists out all the task associated with the given date. If the date is not in the correct format, Aelita will
 prompt you to follow the required format.

Example of usage: 

`list`

Expected outcome:

<pre>
Here's your list:
  1.[T][✘] Homework
  2.[D][✘] project (by Feb 20 2020)
  3.[E][✘] project meeting (at: Feb 20 2020 2pm-4pm)
</pre>

Example of usage:

`list 2020-02-20`

Expected outcome:

<pre>
Here are the tasks you are looking for:
  1.[E][✘] project meeting (at: Feb 20 2020 2pm-4pm)
</pre>

### Search for Tasks 
Finds tasks related to a keyword.

## Usage

### `find <keyword>`

Aelita will search through the list and show all the tasks that are related to the given keyword.

Example of usage: 

`find project`

Expected outcome:

<pre>
These are the task you are looking for:
  1.[D][✘] project (by Feb 20 2020)
  2.[E][✘] project meeting (at: Feb 20 2020 2pm-4pm)
</pre>

### Complete a Task 
Marks a task as being completed.

## Usage

### `done <index of task in list>`

Aelita marks the task at the given index as completed. If no index is given, Aelita will prompt you for one. If there
 is no task at the index or the task has already been completed, Aelita will give you an error message.

Example of usage: 

`done 1`

Expected outcome:

<pre>
Another task off the list. Good job!
  [T][✓] Homework
</pre>

### Delete a Task 
Deletes a task off the list.

## Usage

### `delete <index of task in list>`

Aelita will remove the task at the given index. If the index is not given, Aelita will prompt you for one. If a task
 does not exists at the given index, Aelita will show an error message. 

Example of usage: 

`delete 2`

Expected outcome:

<pre>
The task has been removed.
  [D][✘] project (by Feb 20 2020)
</pre>