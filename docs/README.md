# User Guide

## 1. Introduction

**duke** is a task management application for busy people who prefer typing.

## 2. Getting Started

1. Ensure you have Java 11 or above installed in your computer.
2. Download the latest version of duke.jar (duke-0.2.jar) [here](https://github.com/helloImHai/duke/releases/tag/v2.0).
3. Copy the file to the folder you want to use as the home folder for duke.
4. Run the file:
    
    4.1 For Windows: Double click the file and you're done!
    
    4.2 For Mac: Open Terminal, `cd` into home folder of duke. Run `java -jar duke-0.2.jar` 

## 3. Features 

> Command format
> * Words in `UPPER_CASE` are the parameters to be supplied by the user, 
> e.g. `todo DESCRIPTION`, `DESCRIPTION` is a parameter that can be use as 
> `todo CS2103 homework`
> * `DATETIME` refers to date time in the format of `yyyy-MM-dd hhmm`, 
> e.g. for the format command`deadline DESCRIPTION /by DATETIME`, 
> `event CS2103 homework /by 2020-02-12 2359` would be a valid command.
> * All `INDEX` parameters refers to the numbering of the item when `list` is used.

List of features:
* Listing all tasks: `list`
* Adding a todo task: `todo`
* Adding a deadline task: `deadline`
* Adding an event task: `event`
* Deleting a task: `delete`
* Setting a task as done: `done`
* Searching for task: `search`
* Updating a task: 
    * Updating a todo task: `updatetodo`
    * Updating a deadline task: `updatedeadline`
    * Updating an event task: `updateevent`
* Saving
* Exiting the program: `bye`

### 3.1 Listing all tasks: `list`
Displays all tasks in the task list.

Format: `list`

### 3.2 Adding a todo task: `todo`
Creates a todo task with description and adds it to the task list.

Format: `todo DESCRIPTION`

### 3.3 Adding a deadline task: `deadline`
Creates a deadline task with description and time and adds it to the task list.

Format: `deadline DESCRIPTION /by DATETIME`

### 3.4 Adding a event task: `event`
Creates a event task with description and time and adds it to the task list.

Format: `event DESCRIPTION /at DATETIME`

### 3.5 Deleting a task: `delete`
Deletes a task at a particular index in the task list.

Format: `delete INDEX`

### 3.6 Marking a task as done: `done`
Set a task at a particular index in the task list as done.

Format: `done INDEX`

### 3.7 Searching for tasks: `search`
Displays the list where each tasks matches the keywords (case sensitive). 
It is also possible to search tasks by date and time as long as it matches formatted
date time. Note that despite some items may have different numbering from the list 
provided be `list`, `INDEX` for subsequent calls still refers to the numbering 
provided by list.

Format: `search KEYWORDS`

### 3.8 Updating a task:

#### 3.8.1 Updating a Todo task: `updatetodo`
Updates description of a certain todo task description.

Format: `updatetodo INDEX desc/DESCRIPTION`

#### 3.8.2 Updating a Deadline task: `updatedeadline`
Updates description and time of a certain deadline task. 
Both description and time fields must be present.

Format: `updatedeadline INDEX desc/DESCRIPTION time/DATETIME`

#### 3.8.3 Updating a Event task: `updateevent`
Updates description and time of a certain event tas. 
Both description and time fields must be present.

Format: `updateevent INDEX desc/DESCRIPTION time/DATETIME`

### 3.9 Saving the tasks: 
Tasks are automatically saved upon actions that affect the task list and tasks.

### 3.10 Exiting the program: `bye`
Exits the program.

Format: `bye`


## 4. Usage

### 4.1 Adding tasks

Adding tasks by using `deadline`, `event`, and `todo`:

`todo eat dinner`
> ```$xslt
>     Got it. I've added this task:
>     [T][✗] eat dinner
>     Now you have 1 tasks in the list.
>```

`deadline CS2103 homework /by 2020-03-15 2359`
> ```$xslt
>     Got it. I've added this task:
>     [D][✗] CS2103 homework (by: 23:59, Mar 15 2020)
>     Now you have 2 tasks in the list.
>```

`event CS2103 tutorial /at 2020-07-07 1000`
> ```$xslt
>     Got it. I've added this task:
>     [E][✗] CS2103 tutorial (at: 10:00, Jul 7 2020)
>     Now you have 3 tasks in the list.
>```

### 4.2 Listing tasks
Listing all tasks using `list`:

`list`
> ```$xslt
>    1. [T][✗] eat dinner
>    2. [D][✗] CS2103 homework (by: 23:59, Mar 15 2020)
>    3. [E][✗] CS2103 tutorial (at: 10:00, Jul 7 2020)
> ```

### 4.3 Setting tasks as done
Listing all tasks using `done`:

`done 1`
> ```$xslt
>    Nice! I've marked this task as done:
>    [T][✓] eat dinner
>```

### 4.4 Searching for tasks 
Search for task by name using: `search`

`search eat`
> ```$xslt
>    Here are the tasks that contains 'eat' in your list:
>    1. [T][✓] eat dinner
>```

It is also possible to search task by time using search: `search`

`search 2020`
> ```$xslt
>    Here are the tasks that contains '2020' in your list:
>    1. [D][✗] CS2103 homework (by: 23:59, Mar 15 2020)
>    2. [E][✗] CS2103 tutorial (at: 10:00, Jul 7 2020)
>```

Remarks: 
 * `search` is case sensitive, this means that, `search cs2103` will not 
return any result despite having `[D][✗] CS2103 homework (by: 23:59, Mar 15 2020)`.

### 4.5 Updating tasks
Update a task using: `updatetodo`, `updateevent`, and `updatedeadline`

`updateevent 3 desc/ CS2103 tutorial time/ 2020-07-07 1300` 
> ```$xslt
>    Got it. I've changed this task to:
>    [E][✗] CS2103 tutorial (at: 13:00, Jul 7 2020)
>```

### 4.6 Exiting the program:
Exit the program using: `bye`

The window will automatically close.