# User Guide
A guide on how to use Duke.
## Features 

* #### Add task
* #### Remove duplicate task
* #### Mark task as done
* #### Delete task
* #### List tasks
* #### Auto-save task

## Types of tasks
#### `todo` - a normal task
#### `event` - a task occurring at a date
####  `deadline` - a task that you need to complete before a date

## Usage
After typing the command, press `enter` to see a reply.

* ### `todo`

    Adds a todo task by typing, `todo "task"`.
    
    Example of usage: 

    `todo buy groceries`

    Expected outcome:

    ```
    Got it. I've added this task: 
    [T][✗] buy groceries
    Now you have 1 task in the list.
    ```


* ### `event`

    Adds an event task by typing, `event "task" /at "yyyy-mm-dd"`.
    
    Example of usage: 

    `event family outing /at 2020-09-20`

    Expected outcome:

    ```
    Got it. I've added this task: 
    [E][✗] family outing (at: Sep 20 2020)
    Now you have 1 task in the list.
    ```
  
* ### `deadline`

    Adds a deadline task by typing, `deadline "task" /by "yyyy-mm-dd"`.

    Example of usage: 

    `deadline submit application form /by 2020-05-03`

    Expected outcome:

    ```
    Got it. I've added this task: 
    [D][✗] submit application form (by: May 3 2020)
    Now you have 1 task in the list.
    ```
  
* ### `list`

    Displays a list of tasks by typing, `list`.

    Example of usage: 

    `list`

    Expected outcome:

    ```
    Here are the tasks in your list:
    1. [T][✗] buy groceries
    2. [E][✗] family outing (at: Sep 20 2020)
    3. [D][✗] submit application form (by: May 3 2020)
    ```
  
* ### `done`

    Marks a task as done by typing: `done "index"`

    Example of usage: 

    `done 1`

    Expected outcome:

    ```
    Nice! I've marked this task as done:
    [T][✓] buy groceries
    ```
  
* ### `delete`

    Deletes the task from the list of tasks by typing, `delete "index"`.

    Example of usage: 

    `delete 1`

    Expected outcome:

    ```
    Noted. I've removed this task:
    [T][✓] buy groceries
    Now you have 2 tasks in the list.
    ```
  
  
* ### `bye`

    Exit the application by typing, `bye`.

    Example of usage: 

    `bye`

    Expected outcome:
    
    Application closes.
  