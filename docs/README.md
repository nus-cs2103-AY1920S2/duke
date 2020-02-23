# User Guide
Duke is a personalized to do list that helps you keeps track of the tasks you have to do and deadlines you have to adhere to!
<br>
Duke allows you to Add, Mark and Delete to manage your Todo, Deadline, Event and FixedDurationTask.

![User interface](Ui.png)

## Table of Contents
- [Requirements](#requirements)
- [Features](#features)
    - [Feature 1: New Task? Add it.](#feature-1-new-task-add-it)
    - [Feature 2: Finished Task? Delete it.](#feature-2-finished-task-delete-it)
    - [Feature 3: Done Task? Mark it.](#feature-3-done-task-mark-it)
    - [Feature 4: Searching for a Task? Find it.](#feature-4-searching-for-a-task-find-it)
    - [Feature 5: Displaying your tasks? List it.](#feature-5-displaying-your-tasks-list-it)
    - [Feature 6: Close Duke? Just say bye.](#feature-6-close-duke-just-say-bye)
- [Commands](#Commands)
    - [todo](#todo---add-a-todo-to-the-to-do-list)
    - [deadline](#deadline---add-a-deadline-to-the-to-do-list)
    - [event](#event---add-an-event-to-the-to-do-list)
    - [fixedtask](#fixedtask---add-a-fixeddurationtask-to-the-to-do-list)
    - [delete](#delete---delete-a-task-from-the-to-do-list)
    - [done](#done---mark-a-task-as-done-in-the-to-do-list)
    - [find](#find---find-a-task-in-the-to-do-list) 
    - [list](#list---list-all-tasks-in-the-to-do-list)
    - [bye](#bye---close-duke)
    

## Requirements
You need to have the following installed to be able to use Duke.
1. Java SE Development Kit 11
2. The `jar` file, which can be found [here](https://github.com/garysyndromes/duke/releases)
<br>
<br>
<br>
 <a href="#top">Back to top</a>

## Features 
Here are some features that Duke offers to keep you on track with your to do list.
### Feature 1: New Task? Add it.
In Duke, you can add up to **4** types of Tasks.
###### Task type 1: Todo
`Todo` is a task without any date/time attached to it. _e.g., visit new theme park_
###### Task type 2: Deadline
`Deadline` is a task that need to be done before a specific date. _e.g., submit report (by: Mar 03 2020)_
###### Task type 3: Event
`Event` is a task that start at a specific time and ends at a specific time. _e.g., team project meeting (at: 2/10/2019 2-4pm)_
###### Task type 4: FixedDurationTask
`FixedDurationTask` is a task that takes a fixed amount of time but does not have a fixed start/end time. _e.g., reading the sales report (needs 2 hours)_<br>
<br>
<br>
<a href="#top">Back to top</a>

### Feature 2: Finished task? Delete it.
You can `delete` a task from the to do list anytime by entering `delete` `task_number` into Duke.<br>
`task_number` is the position of the task in the to do list.
<br>
<br>
<br>
 <a href="#top">Back to top</a>
 
### Feature 3: Done Task? Mark it.
You can mark a task as `done` anytime by entering `done` `task_number` into Duke. <br>
`task_number` is the position of the task in the to do list.
<br>
<br>
<br>
 <a href="#top">Back to top</a>

### Feature 4: Searching for a Task? Find it.
You can `find` a task in the to do list anytime by entering `find` `task_name` into Duke. <br>
`task_name` is the keyword that make up the task name.
<br>
<br>
<br>

 <a href="#top">Back to top</a>
 
### Feature 5: Displaying your tasks? List it.
You can `list` all the tasks in the to do list anytime by entering `list` into Duke.
<br>
<br>
<br>
 <a href="#top">Back to top</a>

### Feature 6: Close Duke? Just say bye.
You can close Duke anytime by entering `bye` into Duke.
<br>
<br>
<br>
 <a href="#top">Back to top</a>


## Commands

### `todo` - Add a Todo to the to do list.

Add a `Todo` task to the to do list with the command `todo task_name`.

Example of usage: 

`todo study for finals`

Expected outcome:

`Got you covered! Added this task to the list:` <br>
 `[T][UNDONE] study for finals` <br>
 `Now you have 1 task in the list.`
<br>
<br>
<br>
 <a href="#top">Back to top</a>

### `deadline` - Add a Deadline to the to do list.

Add a `Deadline` task to the to do list with the command `deadline task_name` `/by` `date`.<br>
`date` must be entered in the format of _**YYYY-MM-DD**_

Example of usage: 

`deadline submit report /by 2020-03-04`

Expected outcome:

`Got you covered! Added this deadline to the list:` <br>
`[D][UNDONE] submit report (by: Mar 04 2020)`<br>
`Now you have 2 tasks in the list.`
<br>
<br>
<br>
 <a href="#top">Back to top</a>
 
### `event` - Add an Event to the to do list.

Add an `Event` task to the to do list with the command `event task_name` `/at` `time`.

Example of usage: 

`event nus open day /at utown 2pm`

Expected outcome:

`Got you covered! Added this event to the list:` <br>
 `[E][UNDONE] nus open day (at: utown 2pm)` <br>
 `Now you have 3 tasks in the list.`
 <br>
 <br>
 <br>
  <a href="#top">Back to top</a>
  
 ### `fixedtask` - Add a FixedDurationTask to the to do list.
 
 Add a `FixedDurationTask` task to the to do list with the command `fixedtask task_name` `/needs` `time`.<br>
 `time` must be converted to hours.
 
 Example of usage: 
 
 `fixedtask sleep /needs 8`
 
 Expected outcome:
 
`Got you covered! Added this FixedDurationTask to the list:`<br>
`[F][UNDONE] sleep (needs 8 hour(s))`<br>
`Now you have 4 tasks in the list.`

<br>
<br>
<br>

 <a href="#top">Back to top</a>
 

  ### `delete` - Delete a task from the to do list.
  
  Delete a task from the to do list with the command `delete task_number`.<br>
  `task_number` is the position of the task in the to do list.
  
  Example of usage: 
  
  `delete 2`
  
  Expected outcome:
  
`Bye bye Task! I've removed this task:`<br>
`[D][UNDONE] submit report (by: Mar 04 2020)`<br>
`Now you have 3 tasks in the list.`

<br>
<br>
<br>

 <a href="#top">Back to top</a>
 

  ### `done` - Mark a task as Done in the to do list.
  
 Mark a task in the to do list as Done with the command `done task_number`.<br>
 `task_number` is the position of the task in the to do list.
  
  Example of usage: 
  
  `done 1`
  
  Expected outcome:
  
`Nice! I've marked this task as done and dusted:`<br>
`[T][DONE] study for finals`

<br>
<br>
<br>

 <a href="#top">Back to top</a>
 

  ### `find` - Find a task in the to do list.
  
 Find a task in the to do list with the command `find task_name`.<br>
`task_name` is the keyword that make up the task name.
  
  Example of usage: 
  
  `find nus`
  
  Expected outcome:
  
`Here are the matching tasks in your list:`<br>
`1. [E][UNDONE] nus open day (at: utown 2pm)`


<br>
<br>
<br>

 <a href="#top">Back to top</a>
 

  ### `list` - List all tasks in the to do list.
  
 List all tasks in the to do list with the command `list`.
  
  Example of usage: 
  
  `list`
  
  Expected outcome:
  
`1. [T][DONE] study for finals`<br>
`2. [E][UNDONE] nus open day (at: utown 2pm)`<br>
`3. [F][UNDONE] sleep (needs 8 hour(s))`

<br>
<br>
<br>

 <a href="#top">Back to top</a>
 
  ### `bye` - Close Duke
  
 Duke will terminate when the command `bye` is entered.
  
  Example of usage: 
  
  `bye`
  
  Expected outcome:
  
`====================================================================================`<br>
`Bye bye! Thank you for using me! Hope to see you again soon.`

<br>
<br>
<br>

 <a href="#top">Back to top</a>


