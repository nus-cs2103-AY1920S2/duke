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
#### `deadline [task_name] /by [dd/m/yyyy] [hhmm]`

Creates a deadline item in your to-do list. The `[hhmm]` time field can be excluded.

Example of usage: 

* `deadline Finish Homework /by 26/2/2020`

  Expected outcome: Creates the task `Finish Homework` with the deadline on 26/2/2020 at the default time 11:59pm.

* `deadline Finish Project /by 23/10/2020 1400`

  Expected outcome: Creates the task `Finish Project` with the deadline on 23/10/2020 at 2pm.



### Create an event
Adds an event to your list.
#### Usages:
#### `event [task_name] /at [dd/m/yyyy] [hhmm]`

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

* `list`
* `list all`

Lists all items regardless of type.

* `list todo`
* `list deadline`
* `list event`

Lists todo, deadline, or event items respectively.



### Mark a task as done
Mark any task as done.
#### Usages:
#### `done [position]`

Mark the task at the specific position in your full list as done.

Example of usage: 

* `done 3`

  Marks the 3rd task in your full list as done.
  
  

### Delete a task
Delete any task from your list.
#### Usages:
#### `delete [position]`

Deletes the task at the specific position in your full list

Example of usage: 

* `delete 3`

  Deletes the 3rd task in your full list.
  
  
  
### Find a task
Delete any task from your list.
#### Usages:
#### `find [keyword]`

Finds all tasks that matches your specified keyword.

Example of usage: 

* `find Homework`

  Finds all tasks that contains the keyword 'Homework'.
  


### Update a task
Update the fields of any task.
#### Usages:
#### `update [position] [field], [keyword]`

Updates the specified field of the event at the position to your new keyword.

Example of usage:

* `update 1 name, Finish Homework`

  Updates the name of the 1st item in your list to `Finish Homework`.
  
* `update 4 time, 1500`

  Updates the time of the 4th item in your list to `1500`.
  


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

Saves your data to the data file `duke.txt`.