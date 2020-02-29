# User Guide
Shinobu is designed to be your personal assistant, allowing you to plan and track your daily tasks in a simple and intuitive manner!
This is a quick guide on the features that Shinobu has and the respective commands needed to utilize these features. Have fun!

## Features 

### Feature 1 - Adding **To-Do** task
This allows you to add a To-Do task into the list of tasks that Shinobu is tracking for you! 
A To-Do task does not require an accompanying timing for the task to be done by.

### Usage

#### *todo eventDescription*

Add a To-Do task that is to be tracked into an existing list of tasks. 
Output should be a confirmation message of a successful addition, followed by the
total number of tasks in your current list.

Example of usage: 

 `todo Hunt For Demons`

Expected outcome:

`Got it! I've added this task:`

`Hunt for Demons`

`Now you have 1 task in the list`

### Feature 2 - Adding **Deadline** task
This allows you to add a deadline into the list of tasks that Shinobu is tracking for you! 
A Deadline task requires an accompanying timing for the task to be done by, else the task will not be added.

### Usage

#### *deadline eventDescription /by eventTiming*

Add a deadline task that is to be tracked into an existing list of tasks. 

*eventTiming* needs to be in YYYY-MM-DD format, else Shinobu will not accept the entry!

Output should be a confirmation message of a successful addition, followed by the
total number of tasks in your current list.

Example of usage: 

 `deadline Train recruits /by 2020-08-30`

Expected outcome:

`Got it! I've added this task:`

`Train recuits`

`Now you have 1 task in the list`

### Feature 3 - Adding **Event** task
This allows you to add an event into the list of tasks that Shinobu is tracking for you! 
An Event task requires an accompanying timing for the task to happen at, else the task will not be added.

### Usage

#### *deadline eventDescription /at eventTiming*

Add an event task that is to be tracked into an existing list of tasks. 

*eventTiming* needs to be in YYYY-MM-DD format, else Shinobu will not accept the entry!

Output should be a confirmation message of a successful addition, followed by the
total number of tasks in your current list.

Example of usage: 

 `event Tanjirou's Birthday Party /at 2020-07-14`

Expected outcome:

`Got it! I've added this task:`

`Tanjirou's Birthday Party`

`Now you have 1 task in the list`

### Feature 4 - Marking a task as **Done**
This allows you to tell Shinobu to mark certain tracked tasks as complete.

### Usage

#### *done eventIndex*

Marks a tracked task as completed from the existing list of tasks. The eventIndex is 1-indexed and
the index must actually exist in the list, else Shinobu will not accept your command!

Output should be a confirmation message of a successful mark and the details of the task.

Example of usage: 

`event Tanjirou's Birthday Party /at 2020-07-14`
 
`done 1`

Expected outcome:

`Nice! I have marked this as done!:`

`[E][O] Tanjirou's Birthday Party (at: Jul 14 2020)`

### Feature 5 - **Deleting** a task
This allows you to delete an event from the list of tasks that Shinobu is tracking for you! 

### Usage

#### *delete eventIndex*

Removes a tracked task from the existing list of tasks. The eventIndex is 1-indexed and
the index must actually exist in the list, else Shinobu will not accept your command!

Output should be a confirmation message of a successful deletion, followed by the
total number of tasks in your current list, after the deletion.

Example of usage: 

 `event Tanjirou's Birthday Party /at 2020-07-14`
 
 `delete 1`

Expected outcome:

`The task requested has been successfully removed:`

`[E][X] Tanjirou's Birthday Party (at: Jul 14 2020)`

`Now you have 0 task in the list`

### Feature 6 - See current **List** of tasks
This allows you to the list of tasks that Shinobu is tracking for you! 

### Usage

#### *list*

Shows you the current list of tasks that are being tracked.

If the list is not empty, output should be an 1-indexed list of tasks.
Else, the output will be a message that the list is empty.

Example of usage: 

 `todo Hunt For Demons`
 
 `deadline Train recruits /by 2020-08-30`

 `event Tanjirou's Birthday Party /at 2020-07-14`
 
 `list`

Expected outcome:

`Here are the task in your list:`

`1.[T][X] Hunt For Demons`

`2.[D][X] Train recruits (by: Aug 30 2020)`

`3.[E][X] Tanjirou's Birthday Party (at: Jul 14 2020)`

### Feature 7 - **Find** a task description that matches a keyword
This allows you to search the list of tracked tasks and
find and task description that contains a keyword that you specified! 

### Usage

#### *find keyWord*

Returns the tasks that contain the keyword, else it will display a message
saying that no such task has been found.


Example of usage: 

 `todo Hunt For Demons`
 
 `deadline Train recruits /by 2020-08-30`

 `event Tanjirou's Birthday Party /at 2020-07-14`
 
 `find Birthday`

Expected outcome:

`Here's the list of tasks that contains your keyword!`

`1. [E][X] Tanjirou's Birthday Party (at: Jul 14 2020)`