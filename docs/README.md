# User Guide
Duke is for users who prefer to use a desktop app for keeping track 
of users' todos, deadlines and events.

## Features

###Feature 1 - Todos.
Todos are a type of task that contain only an indicator to show if it is down, followed by a description.\
Todos are tagged with [T]\
eg. [T][X] Read finish chapter 3. 

###Feature 2 - Deadlines.
Deadlines are a type of task that contain only an indicator to show if it is down, followed by a description 
and a date to show its deadline.\
Deadlines are tagged with [D]\
eg. [D][X] Quizzes for CS2103T (by: May 5 2019, 18:00 PM). 

###Feature 3 - Deadlines.
Events are a type of task that contain only an indicator to show if it is down, followed by a description 
and a date to show its deadline.\
Deadlines are tagged with [D]\
eg. [E][X] Game farming event (at: May 6 2019, 20:00 PM).

### Feature 4 - Saving to file
Whenever you make any changes to the database (ie. Adding, removing or marking tasks as done), 

## Usage

### `list` - Shows every todo, event and deadline in a list 

### `delete` - Deletes the task from the index
Example of usage:
`delete 2`
Expected outcome:
Removes the 2nd task from the list in the database

### `find` - Finds all tasks related to the keyword
Example of usage: 
`find book`
Expected outcome:
Returns all tasks with the keywork 'book'

