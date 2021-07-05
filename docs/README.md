# User Guide
Duke is an personal task managing desktop app.

## Features 
1. Add task to list
2. List tasks
3. Mark task as done
4. Delete task from list
5. Find task in list
6. Help for user 
7. Bye

## Usage
### `todo (description)` - Add a Todo task
A todo task will be added with the task name`(description)`.

Example of usage: 

`todo view webcast`

Expected outcome:

`Got it. I've added this task:`  
 `[T][✗] view webcast`  
`Now you have 1 task in the list.`

### `event (description) /at (period)` - Add an Event task
An event task will be added with the task name `(description)` and `(period)`.

Example of usage: 

`event project meeting /at 03/04/2020 1400-1600`

Expected outcome:

`Got it. I've added this task:`   
 `[E][✗] project meeting (at: 3rd of April 2020, 2:00pm to 4:00pm)`  
`Now you have 2 tasks in the list.`

### `deadline (description) /by (deadline)` - Add a Deadline task
A deadline task will be added with the task name `(description)` and `(deadline)`.

Example of usage: 

`deadline coding assignment /by 03/03/2020 2359`

Expected outcome:

`Got it. I've added this task:`  
 `[D][✗] coding assignment (by: 3rd of March 2020, 11:59pm)`  
`Now you have 3 tasks in the list.`

### `list` - List all the tasks
All the tasks in the list will be displayed.

Example of usage:

`list`

Expected outcome:

`Here are the tasks in your list:`  
`1.[T][✗] view webcast`  
`2.[E][✗] project meeting (at: 3rd of April 2020, 2:00pm to 4:00pm)`  
`3.[D][✗] coding assignment (by: 3rd of March 2020, 11:59pm)`

### `done (task number)` - Mark task as done
The task with that particular task number will be marked as done.

Example of usage:

`done 2`

Expected outcome:

`Nice! I've marked this task as done:`  
 `[E][✓] project meeting (at: 3rd of March 2020, 2:00pm to 4:00pm)`

### `delete (task number)` - Delete a task
The task with that particular task number will be deleted.
 
 Example of usage:
 
 `delete 2`
 
 Expected outcome:
 
`Noted. I've removed this task:`  
 `[E][✓] project meeting (at: 3rd of March 2020, 2:00pm to 4:00pm)`  
 `Now you have 2 tasks in the list.`

### `find (keyword)` - Find a task
Find a task by searching for that particular `(keyword)`.
 
 Example of usage:
 
 `find webcast`
 
 Expected outcome:
 
 `Here are the matching tasks in your list:`  
`1.[T][✗] view webcast`  

### `help` - Provide in-App guidance to user
Displays available commands to the user.

 Example of usage:

 `help`

 Expected outcome:
 
 `Available commands:`  
  `list: displays all tasks that is recorded`
  
  `done [index]: marks the task as done`
  
  `todo [task]: adds the task to the list`
  
  `deadline /by [dd/mm/yy HHMM]: adds task with deadline to the list`
  
  `event /at [dd/mm/yy HHMM-HHMM]: adds event with date and time to the list`
  
   `delete [index]: deletes the task`
   
   `find [keyword]: finds all task that is related to the keyword`
   
   `bye: exits the application`
   
### `bye` - Exit from the application
A goodbye message will be shown and the application will close.

Example of usage:

`bye`

Expected outcome:

`Bye~ Hope to see you again soon!`