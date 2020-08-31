# User Guide
   
   Welcome to Duke! This application allows a user to maintain a list of tasks
   on it, which can be added to and updated. Changes made are all saved on the
   disk automatically, and loaded back onto the app each time it is restarted.


## Features 

    Displaying the task list:      Feature 1
    Adding tasks of various kinds: Features 2 - 4
    Marking tasks as Done:         Feature 5
    Deleting outdated tasks:       Feature 6
    Searching using keywords:      Feature 7
    Other useful features:         Feature 8



## Feature 1 - Displaying the Task List

   This feature is activated by the keyword 'list', and displays the status of
   all tasks currently on the list. The tasks are numbered, starting from 1,
   according to the chronological order in which they were added to the list,
   with more recently added tasks further down the list. The status display
   features which category each task belongs to (To-do, Deadline, or Event),
   the time/deadline where applicable, and also marks which tasks are completed.

### Usage

   In the following example, the 'v' denotes a tick and 'x' denotes a cross.
   These symbols display whether a task has been completed.
   
   There are 3 kinds of tasks tracked by Duke in total:
   T denotes a to-do, D denotes a deadline, while E denotes an event.

### Keyword - 'list'   (not case-sensitive)

Example of usage: 

`list`

Expected outcome:

"    Here are the tasks in your list:
     1. [T][v] Borrow book
     2. [T][v] Read book
     3. [D][x] Essay (by: Mar 2 2020 11.59pm)
     4. [E][x] Midterm (at: Mar 3 2020 4.00pm)"



## Feature 2 - Adding a To-do

   This feature is activated by the keyword 'todo', and allows the user to add a
   simple task to be completed, without any additional accompanying information.

### Usage

### Keyword - 'todo'   (not case-sensitive)

Example of usage:

'todo borrow book'

Expected outcome:

"    Got it. I've added this task:
       [T][x] borrow book
     Now you have 1 task in the list."



## Feature 3 - Adding a Deadline

   This feature is activated by the keyword 'deadline'. It allows the user to add
   an upcoming deadline, accompanied by the date and time it is due. The keyword
   ' /by ' separates the task name and its deadline.

   For Duke to process date-time inputs, they must be entered in specific formats.
   Duke parses dates entered in YYYY-MM-DD or D/M/YYYY formats, and it parses times
   entered in HHmm format. If any other format is encountered, Duke stores the date-
   time input as a simple String instead.

   If Duke successfully parses the date-time input, it outputs it in the format
   shown in the example below.

### Usage

### Keywords - 'deadline' and ' /by '   (only ' /by ' is case-sensitive)
   
Example of usage:

'deadline History Essay /by 2/3/2020 2359'

Expected outcome:

"    Got it. I've added this task:
       [D][x] History Essay (by: Mar 2 2020, 11.59pm)
     Now you have 2 tasks in the list."



## Feature 4 - Adding an Event

   This feature is activated by the keyword 'event'. It allows the user to add an
   upcoming event, accompanied by the date and time of the event. The keyword ' /at '
   separates the event name from the event time.

   As with the Deadline function, dates and times must be entered in specific formats.
   Duke parses dates entered in YYYY-MM-DD or D/M/YYYY formats, and it parses times
   entered in HHmm format. If any other format is encountered, Duke stores the date-
   time input as a simple String instead.

   If Duke successfully parses the date-time input, it outputs it in the format
   shown in the example below.

### Usage

### Keywords - 'event' and ' /at '   (only ' /at ' is case-sensitive)

Example of usage:

'event History Midterm /at 3/3/2020 1600'

Expected outcome:

"    Got it. I've added this task:
       [E][x] History Midterm (at: Mar 3 2020, 4.00pm)
     Now you have 3 tasks in the list."



## Feature 5 - Marking Tasks as 'Done'

   This feature allows the user to mark (or check) certain Tasks as 'Done'.
   Tasks that are marked 'Done' are displayed with a tick instead of a cross
   when the 'list' command is used. It is activated using the 'done' keyword.

   The task to be marked as Done is referred to using its number on the list.
   This number can be easily verified using the 'list' command.

### Usage

### Keyword - 'done'   (not case-sensitive)

Example of usage:

'done 1'

Expected outcome:

"    Nice! I've marked this task as done:
       [T][v] borrow book"



## Feature 6 - Deleting Tasks

   This feature allows the user to remove tasks from the list when they have become
   outdated or no longer relevant. It is activated using the 'delete' keyword.

   As with 'done', the task to be deleted is referred to using its number on the list.
   This number can be easily verified using the 'list' command.

### Usage

### Keyword - 'delete'   (not case-sensitive)

Example of usage:

'delete 1'

Expected outcome:

"    Noted. I've removed this task:
       [T][v] borrow book
     Now you have 2 tasks in the list."



## Feature 7 - Searching for Tasks using Keywords

   This feature allows the user to search for all tasks containing a given keyword
   in their names. It is activated using the 'find' keyword.

### Usage

### Keyword - 'find'   (not case-sensitive, but search string is)

Example of usage:

'find History'

Expected outcome:

"    Here are the matching tasks in your list:
     1. [D][x] History Essay (by: Mar 2 2020, 11.59pm)
     2. [E][x] History Midterm (at: Mar 3 2020, 4.00pm)"



## Feature 8 - Undoing Commands

   This feature allows the user to undo changes to the list he/she has made in the
   current application run. It is activated by the keyword 'undo'.

   If there still is an action that can be undone, Duke outputs 'Undo successful!'
   Otherwise, Duke outputs 'Oops! Can't undo any further.'

### Usage

### Keyword - 'undo'   (not case-sensitive)

Example of usage:

'undo'

Expected outcome:

'Undo successful!'

If 'list' is called again, the following will be displayed:

"    Here are the tasks in your list:
     1. [T][v] borrow book
     2. [D][x] History Essay (by: Mar 2 2020 11.59pm)
     3. [E][x] History Midterm (at: Mar 3 2020 4.00pm)"

The previous 'delete' action on Task 1 ('borrow book') has been undone.



## Feature 9 - Exiting the Application

   To exit the application, the user may use the 'bye' keyword. This causes Duke
   to output a goodbye message, and close the application window.

### Usage

### Keyword - 'bye'   (not case-sensitive)

Example of usage:

'Bye'

Expected outcome:

"    Bye. Hope to see you again soon!"

(application window closes after a 1-second delay)

