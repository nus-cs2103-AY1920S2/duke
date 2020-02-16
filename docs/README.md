# User Guide

## Description
A personal assistant for tracking the tasks to be done.

<img src="Ui.png" alt="Screenshot for Duke GUI" about="Screenshot for Duke GUI" align="center">

## Features 

### Adding Task
* You can add task as either Todo or Event or Deadline to the list to `task.txt`.

### Delete Task
* You can delete task from the list.

### Mark Task as Done
* You can mark the task as done.

### List Tasks
* You can list the tasks out to see.

### Storing tasks to `task.txt`
* You can store the list of tasks into hard disk in the file `task.txt`.

### Sorting tasks
* You would not required to sort the tasks manually. <br/>
Duke will sort the tasks according to date and time first. If it is same for both tasks, <br/> 
Duke will sort them according to their descriptions in alphabetical order.

## Usage

### `todo` - Add todo to the list

* Inform duke to add the todo with the description provided to the list and <br/>
update the list in `task.txt`.

* Example of usage: <br/><br/>
`todo read topics for CS2103`

* Expected outcome: <br/><br/>
`Got it. I've added this task:` <br/>
`[T][N] read topics for CS2103T`<br/>
` Now you have 1 task in the list.`

<img src="images/Todo.png" alt="Screenshot for todo command" about="Screenshot for todo command" align="center">

### `deadline` - Add deadline to the list

* Inform duke to add the deadline with the description and due date provided to the list <br/>
and update the list in `task.txt`. 

* Example of usage: <br/><br/>
`deadline project /by 2020-02-20`

* Expected outcome: <br/><br/>
`Got it. I've added this task:` <br/>
`[D][N] project (by: Feb 20 2020)`<br/>
` Now you have 1 task in the list.`

<img src="images/Deadline.png" alt="Screenshot for deadline command" about="Screenshot for deadline command" align="center">

### `event` - Add event to the list

Inform duke to add the event with the description, date and time provided to the list <br/>
and update the list in `task.txt`. 

Example of usage: 

`event party /at 2020-03-12 2215`

Expected outcome:

`Got it. I've added this task:` <br/>
`[E][N] party (at: Mar 12 2020 2215)`<br/>
` Now you have 1 task in the list.`

<img src="images/Event.png" alt="Screenshot for event command" about="Screenshot for event command" align="center">

### `List` - List the tasks

Tell Duke to list all of the tasks out.

Example of usage: 

`list`

Expected outcomes:

* When there are three tasks in the list:<br/>
`Here are the tasks in your list:` <br/>
`1.[E][N] party (at: Mar 12 2020 2215)`<br/>
`2.[T][N] do readme for CS2103T iP`<br/>
`3.[T][Y] read topics for cs2103T`

<img src="images/List.png" alt="Screenshot for list command" about="Screenshot for list command" align="center">

* When there are no task in the list: <br/>
`Horray! You do not have any task now!`

<img src="images/ListWithNoTask.png" alt="Screenshot for empty list" about="Screenshot for empty list" align="center">

### `done` - Mark the task done

Tell Duke to mark the task as done.

Example of usage: 

`done 1`

Expected outcomes:

`Nice! I've marked this task as done:` <br/>
`[T][Y] do readme for CS2103T iP`

<img src="images/Done.png" alt="Screenshot for done command" about="Screenshot for done command" align="center">

### `delete` - Remove the specific task at the index from the list

Tell Duke to remove the specific task at the index from the list and <br/>
 update the list in `task.txt`.

Example of usage: 

`delete 1`

Expected outcomes:

`Nice! I've removed this task:` <br/> 
`[E][N] party (at: Mar 12 2020 2215)`

<img src="images/Delete.png" alt="Screenshot for delete command" about="Screenshot for delete command" align="center">

 ### `bye` - Exit the program
 
 Say goodbye to Duke and exit the program.
 
 Example of usage: 
 
 `bye`
 
 Expected outcomes:
 
 `Alright! See you next time!`
 
<img src="images/Bye.png" alt="Screenshot for bye command" about="Screenshot for bye command" align="center">

## Acknowledgements
Credit to Jeffry Lum for the fxml and java files for JAVAFX GUI - <br/>
https://github.com/nus-cs2103-AY1920S2/duke/blob/master/tutorials/javaFxTutorialPart4.md
