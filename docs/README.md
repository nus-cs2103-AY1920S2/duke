# User Guide

## Features 

### Calendar Command
Find Deadline Tasks that correspond to a certain date.

### Deadline Command
Create Deadline Tasks that keep track of when they are due.

### Delete Command
Delete a Task that is no longer needed.

### Done Command
Mark a Task that has been completed, as done.

### Event Command
Create Event Tasks that keep track of when events are happening.

### Find Command
Find Tasks that correspond to the keyword(s) given.

### List Command
List all Tasks that have been created (if any).

### Sort Command
Sort all Tasks by their given Task type.

### Todo Command
Create Todo Tasks that keep track of general things to do.

## Usage

### `calendar` - invoke the Calendar Command

Find Deadline Tasks that correspond to a certain date.

Example of usage: 

`calendar 3/3/2020`

Expected outcome:

`Here are the task(s) in your list on that date:  
2.[D][✓] this project (by: 3 March 2020, 4:30PM)`

### `deadline` - invoke the Deadline Command

Create Deadline Tasks that keep track of when they are due.

Example of usage: 

`deadline this project /by midnight tonight`

Expected outcome:

`Got it. I've added this task:  
[D][✗] this project (by: midnight tonight)  
Now you have 2 task(s) in the list.`

### `delete` - invoke the Delete Command

Delete a Task that is no longer needed.

Example of usage: 

`delete 3`

Expected outcome:

`Noted. I've removed this task:  
3.[E][✗] lou hei (at: my office)  
Now you have 3 task(s) in the list.`

### `done` - invoke the Done Command

Mark a Task that has been completed, as done.

Example of usage: 

`done 2`

Expected outcome:

`Nice! I've marked this task as done:  
 	[✓] this project`
 	
### `event` - invoke the Event Command

Create Event Tasks that keep track of when events are happening.

Example of usage: 

`event lou hei /at my office`

Expected outcome:

`Got it. I've added this task:  
 	[E][✗] lou hei (by: my office)  
 Now you have 3 task(s) in the list.`

### `find` - invoke the Find Command

Find Tasks that correspond to the keyword(s) given.

Example of usage: 

`find book`

Expected outcome:

`Here are the matching task(s) in your list:  
 	1. [T][✗] read book  
 	2. [T][✗] return book`
 	
### `list` - invoke the List Command

List all Tasks that have been created (if any).

Example of usage: 

`list`

Expected outcome:

`Here are the task(s) in your list:  
 1.[T][✗] read book  
 2.[D][✓] this project (by: midnight tonight)  
 3.[T][✗] return book`
 
### `sort` - invoke the Sort Command

Sort all Tasks by their given Task type.

Example of usage: 

`sort T`

Expected outcome:

`Here are the task(s) in your list, with Todo Tasks sorted first:  
 1.[T][✗] read book  
 3.[T][✗] return book  
 2.[D][✓] this project (by: midnight tonight)`
 
### `todo` - invoke the Todo Command

Create Todo Tasks that keep track of general things to do.

Example of usage: 

`todo read book`

Expected outcome:

`Got it. I've added this task:  
 	[T][✗] read book  
 Now you have 1 task(s) in the list.`