# User Guide
M4RCK is a helper bot created to manage an individual's daily tasks.

_You can trust him with your personal data._

## Features 

### Feature 1: Add a to-do task
Format: `todo <description>`

M4RCK will add a to-do task provided to the user's list of tasks.

##### Example of usage:
`todo finish README doc`

### Feature 2: Add a deadline task
Format: `deadline <description> /by <yyyy-mm-dd>`

M4RCK will add a deadline task along with the date provided to the user's list of tasks.

_Providing an invalid date format will return an error message._

##### Example of usage:
`deadline CS2103T iP /by 2020-03-02`

### Feature 3: Add an event
Format: `event <description> /at <yyyy-mm-dd>`

M4RCK will add an event along with the date provided to the user's list of tasks.

_Providing an invalid date format will return an error message._

##### Example of usage:
`event CS2103T finals /at 2020-04-25`

### Feature 4: List all tasks
Format: `list`

M4RCK will display all the tasks currently in the user's list.

##### Example of usage:
`list`

### Feature 5: Mark task as done
Format: `done <index>`

M4RCK will mark the task at the specified index as done. 

_Providing an invalid index will return an error message._

##### Example of usage:
`done 2` will mark the second task as done

### Feature 6: Delete task
Format: `delete <index>`

M4RCK will delete the task at the specified index.

_Providing an invalid index will return an error message._

##### Example of usage:
`delete 3` will delete the third task

### Feature 7: Find task
Format: `find <keyword>`

M4RCK will display a list of tasks that contains the keyword specified.

##### Example of usage:
`find exam`

### Feature 8: Terminate the program
Format: `bye`

M4RCK will bid farewell and automatically close the window in 2 seconds.

##### Example of usage:
`bye`

### Minor Feature: Detecting duplicates

When adding a task (i.e. todo, deadline or event), M4RCK will check if there exists a duplicated task
within the user's list and prevents user from adding duplicated tasks.