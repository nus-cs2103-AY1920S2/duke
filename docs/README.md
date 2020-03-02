# User Guide
## Introduction 
Welcome to Duke, a task management application. 

The application allows users to create, store, and mark done multiple tasks. Other functionalities are also included. 
### Quick Start
To launch the program: 
1. Ensure that Java 11 or later version is installed on your computer.
2. Download the latest `duke-0.2.jar` file from [this page](https://github.com/wardetu/duke/releases/tag/v0.2).
3. Move `duke-0.2.jar` to a folder you want to use as the home folder for the program. 
*Note: the first time Duke runs a new storage folder will be created. The program data is stored in a `data.csv` file. 
Do not modify it.*
4. Double-click on the file icon to run. Alternatively, start up a Terminal program, navigate to the directory
that contains `duke-0.2.jar` and run the following command: `java -jar duke-0.2.jar`

## Usage
A user can create tasks whose type is one of the following: event, deadline, and todo. Events and 
deadlines require a timestamp while todos do not. 

Tasks can be stored and viewed. They can also be marked as completed or deleted from storage.
By providing a keyword, a user can find tasks that contain such keyword in their description.

Duke also provides an undo option. Unfortunately, redo is not supported.

The following commands are available:
* `event`
* `deadline`
* `todo`
* `done`
* `delete`
* `find`
* `list`
* `undo`
* `bye`

### `event` - Adding an event

Adds a new event with description and date & time.

Format: `event <description> at <yyyy-mm-dd hhmm>`

The keyword `at` is mandatory. Also do note that the time input should be in *24 Hour Time* format.

E.g: `event go to CO concert at 2020-03-03 1730`

Expected outcome:
    
    Added:
        EVENT    :go to CO concert at Mar 3 2020 05:30PM (Uncompleted)
        

### `deadline` - Adding a deadline

Adds a new deadline with description and date & time.

Format: `deadline <description> by <yyyy-mm-dd hhmm>`

The keyword `by` is mandatory. The time input should be in *24 Hour Time* format.

E.g: `deadline buy milk by 2020-03-16 1430`

Expected outcome:
    
    Added:
        DEADLINE    :go to CO concert by Mar 16 2020 02:30PM (Uncompleted)
        

### `todo` - Adding a todo

Adds a new todo with description.

Format: `todo <description>`

E.g: `todo buy pen`

Expected outcome:
    
    Added:
        TODO   :buy pen (Uncompleted)
        

### `done` - Marking a task as completed
Marks a specified task as completed.

Format: `done <index>`

The `<index>` field is mandatory and has to be a whole number. Indices are consistent with the indexing
shown when the command `list` is used.

E.g: `done 2`

A possible expected outcome:
    
    Task successfully completed:    
        EVENT    :go to CO concert at Mar 3 2020 05:30PM (Completed)
       

### `delete` - Deleting a task

Deletes a specified task.

Format: `delete <index>`

The `<index>` field is mandatory and has to be a whole number. Indices are consistent with the indexing
shown when the command `list` is used. 

Indices will be updated after the command is successfully executed. Be careful with successive deletion.

E.g: `delete 3`

A possible expected outcome:

    Your burden has been lifted, removed:
        TODO    : buy new detergent (Uncompleted)
        
### `find` - Finding tasks 

Finds all tasks that contain a specified keyword.

Format: `find <keyword>`

The `<keyword>` field is mandatory and has to be a single word (i.e. without spacing).
This is not case sensitive does not require matching of whole word, so `find egg` will match 
both *"buy more eggs"* and *"Throw rotten Egg away"*.


E.g: `find get`

A possible expected outcome:

    Tasks that contain "get" in your list:
        1 - DEADLINE: get milk by Mar 16 2020 08:30AM (Completed)
        2 - DEADLINE: get stapler by Mar 03 2020 05:30AM (Uncompleted)
        3 - TODO    : Get new earphones (Completed)

### `list` - Listing all tasks

Shows all tasks currently present in the list. Also shows their status and index which
can be used for the purpose of `delete` and `done` command.

Format: `list`

A possible expected outcome:

    Here are your tasks:
        1 - DEADLINE: get milk by Mar 16 2020 08:30AM (Completed)
        2 - DEADLINE: get stapler by Mar 03 2020 05:30AM (Uncompleted)
        3 - TODO    : Get new earphones (Completed)
        4 - EVENT   : go to ComicCon at Apr 14 2020 09:00AM (Uncompleted)
    
### `undo` - Undoing 

Undoes the previous command and restores the program state to before the command
was executed. 

Do note that you can only undo up to the point when Duke is run. Thus, if the program
is closed and then re-opened, any change made cannot be undone. 

Undoing a `done` command will revert a task to *uncompleted*, while undoing
a `delete` command will maintain the status of the task when it was deleted.

There is also no option to redo your undone choices. Sorry, but you can only regret so often.

Format: `undo`

E.g: if an item is deleted as a result of `delete 2`, `undo` will bring it back to life.

### `exit` - Quitting

Exits the program. Changes made will be saved.