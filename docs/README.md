# User Guide
Duke is a Personal Assistant Chatbot that helps a person to keep track of various things.

## Features 

### Adding a task
Duke allows a user to add a todo (task without any date/time attached to it), deadline (task that needs to be done before a specific date/time) or an event (task that starts at a specific time and ends at a specific time) task.

### Deleting a task
Duke allows a user to remove an existing task from the list.

### Viewing the tasks list
Duke allows a user to view all the tasks available.

### Marking a task as done
Duke allows a user to mark a task as done.

### Finding a task
 Duke allows a user to find a task by searching for a keyword.

## Usage

### `todo` - Adds a todo task.
By typing the `todo` command together with the description of the task, a user can insert a todo task into the tasks list.

Example of usage: 

`todo read book`

Expected outcome:

    Got it. I've added this task:
     [T][X] read book
    Now you have 1 task(s) in the list.
    
### `deadline` - Adds a deadline task.
By typing the `deadline` command together with the description of the task, followed by a `/by` and the date of the deadline, a user can insert a deadline task into the tasks list. *Ensure* that the date entered is of the format: "YYYY-MM-DD".

Example of usage: 

`deadline submission /by 2020-02-19 1800`

Expected outcome:

    Got it. I've added this task:
     [D][X] submission (by: Feb 19 2020 1800)
    Now you have 2 task(s) in the list.
    
### `event` - Adds an event task.
By typing the `event` command together with the description of the task, followed by an `/at` and the date of the event, a user can insert an event task into the tasks list. *Ensure* that the date entered is of the format: "YYYY-MM-DD".

Example of usage: 

`event party /at 2020-02-06 1400`

Expected outcome:

    Got it. I've added this task:
     [E][X] party (at: Feb 6 2020 1400)
    Now you have 3 task(s) in the list.

### `list` - Displays all the tasks in the tasks list.
By typing the `list` command, a user can see all the tasks available in the tasks list.

Example of usage: 

`list`

Expected outcome:

    1.[T][X] read book
    2.[D][X] submission (by: Feb 19 2020 1800)
    3.[E][X] party (at: Feb 6 2020 1400)
    
### `done` - Marks a task as done.
By typing the `done` command followed by the index of the task, a user can mark that task as done.

Example of usage: 

`done 1`

Expected outcome:

    Nice! I've marked this task as done:
    [T][/] read book

### `delete` - Deletes the task from the tasks list.
By typing the `delete` command followed by the index of the task, a user can delete that task from the tasks list.

Example of usage: 

`delete 2`

Expected outcome:

    Noted. I've removed this task:
     [D][X] submission (by: Feb 19 2020 1800)
    Now you have 2 task(s) in the list.
        
### `find` - Displays all the tasks that contains the keyword provided.
By typing the `find` command followed by the keyword, a user can see the list of tasks matching that keyword.

Example of usage: 

`find book`

Expected outcome:

    Here are the matching tasks in your list:
    1.[T][/] read book
        
### `bye` - Terminates the app.
By typing the `bye` command, a user can exit from the duke program.

Example of usage: 
`bye`

## Acknowledgements
Credit to Jeffry Lum for the fxml and java files for [JAVAFX GUI](https://github.com/nus-cs2103-AY1920S2/duke/blob/master/tutorials/javaFxTutorialPart4.md).