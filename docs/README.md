# User Guide
Welcome to Duke!

Duke is a handy to-do list app that also allows you to keep track of your upcoming deadlines and events.

## Features 

### Create a to-do task
Adds a to-do item to your list.
#### Usages:
#### `todo [task_name]`

Creates a to-do item in your to-do list.

Example of usage: 

* `todo Buy Eggs`

  Expected outcome: Adds `Buy Eggs` to the to-do list.



### Create a deadline task
Adds an item with a deadline to your list.
#### Usages:
#### `deadline [task_name] /by [d/m/yyyy] [hhmm]`

Creates a deadline item in your to-do list. The `[hhmm]` time field can be excluded.

Example of usage: 

* `deadline Finish Homework /by 26/2/2020`

  Expected outcome: Creates the task `Finish Homework` with the deadline on 26/2/2020 at the default time 11:59pm.

* `deadline Finish Project /by 3/10/2020 1400`

  Expected outcome: Creates the task `Finish Project` with the deadline on 3/10/2020 at 2pm.



### Create an event
Adds an event to your list.
#### Usages:
#### `event [task_name] /at [d/m/yyyy] [hhmm]`

Creates an event item in your to-do list.

Example of usage: 

* `event Project Meeting /at 23/2/2020 1500`

  Expected outcome: Creates the event `Project Meeting` on 23/2/2020, 3pm.



### List tasks
You can view your full list, or specifically 
#### Usages:
#### `list [type]`

Views all items of the specified type in your to-do list. 

Example of usage: 

* `list all`

Lists all items regardless of type.

* `list todo`
* `list deadline`
* `list event`

Lists todo, deadline, or event items respectively.



### Mark a task as done
Mark any task as done.
#### Usages:
#### `done [task_type] [position]`

Mark the task at the specific position in any task list as done.

Example of usage: 

* `done todo 3`

  Marks the 3rd task in your todo list as done.
  
  

### Delete a task
Delete any task from your list.
#### Usages:
#### `delete [task_type] [position]`

Deletes the task at the specific position in any task list.

Example of usage: 

* `delete event 3`

  Deletes the 3rd task in your event list.
  
  
  
### Find a task
Finds any task from your list.
#### Usages:
#### `find [keyword]`

Finds all tasks that matches your specified keyword.

Example of usage: 

* `find Homework`

  Finds all tasks that contains the keyword 'Homework'.
  


### Update a task
Update the fields of any task.
#### Usages:
#### `update [task_type] [position] [field] [keyword]`

Updates the specified field of the event at the position to your new keyword.

Example of usage:

* `update todo 1 name Finish Homework`

  Updates the name of the 1st item in your todo list to `Finish Homework`.
  
* `update deadline 4 time 1500`

  Updates the time of the 4th item in your deadline list to `1500`.
  


### Sort a list
You can sort any list by either name in alphabetical order, or by date in earliest first.
#### Usages:
#### `sort [task_type] [sorting_type]`

Sorts the specified list by the sorting type in ascending order.

Example of usage:

* `sort todo name`

Sorts the to-do list by name.

* `sort event date`

Sorts the event list by date and time, earliest first.



### View an overview of your tasks over a specified period
You can see your upcoming deadlines and events up to a specified number of days.
#### Usages:
#### `upcoming [number_of_days]`

Views a list of deadlines and events happening from your system date to your specified number of days.

Example of usage:

* `upcoming 4`

  Lists all your deadlines and events happening for the next 4 days.
  
* `upcoming tomorrow`

  List all your deadlines and events happening for the next 2 days.
  


### View an overview of your tasks on a specific date
You can see your upcoming deadlines and events at a specific date.
#### Usages:
#### `view [date]`

Views a list of deadlines and events happening at your specified date.

Example of usage:

* `view 26/2/2020`

  List all your deadlines and events happening on 26/2/2020.
  
* `view today`

  List all your deadlines and events happening for the day.
  
  
  
### Save your data
While each function automatically saves your data for you, you can also manually save the data yourself.
#### Usages:
#### `save`

Saves your data to the directory `data/duke.txt`, located at where you launched the application.