# User Guide
A guide on how to use Duke.

## 1. Screenshot
![Screenshot of Duke](https://github.com/heidicrq/duke/blob/master/docs/Ui.png)

## 2. Features 

### Task Management
* #### Add task
    Adds a todo, event or deadline task to your task list. 
* #### Remove duplicate task
    Automatically detects and removes a duplicate task you have just added.
* #### List tasks
    Shows you the list of task you currently have.
* #### Mark task as done
    Marks a tasks as done after you have completed it.
* #### Delete task
    Deletes a task from your task list.
* #### Find task
    Finds a list of task using a keyword you have provided.
* #### Auto-save task
    Automatically saves your task list to your hard disk when you add, remove, mark a task as done or exit the application.
    

## 3. Types of tasks
* #### `todo` - a normal task
* #### `event` - a task occurring at a date
* ####  `deadline` - a task that you need to complete before a date

## 4. Usage
After typing the command, press `enter` to see a reply.

### 4.1 `todo` 

Adds a todo task by typing, `todo "task"`.

Example of usage: 

`todo buy groceries`

Expected outcome:

```
Got it. I've added this task: 
[T][✗] buy groceries
Now you have 1 task in the list.
```


### 4.2 `event`

Adds an event task by typing, `event "task" /at "yyyy-mm-dd"`.

Example of usage: 

`event family outing /at 2020-09-20`

Expected outcome:

```
Got it. I've added this task: 
[E][✗] family outing (at: Sep 20 2020)
Now you have 1 task in the list.
```
  
### 4.3 `deadline`

Adds a deadline task by typing, `deadline "task" /by "yyyy-mm-dd"`.

Example of usage: 

`deadline submit application form /by 2020-05-03`

Expected outcome:

```
Got it. I've added this task: 
[D][✗] submit application form (by: May 3 2020)
Now you have 1 task in the list.
```
  
### 4.4 `list`

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
  
### 4.5 `done`

Marks a task as done by typing: `done "index"`

Example of usage: 

`done 1`

Expected outcome:

```
Nice! I've marked this task as done:
[T][✓] buy groceries
```
  
### 4.6 `delete`

Deletes the task from the list of tasks by typing, `delete "index"`.

Example of usage: 

`delete 1`

Expected outcome:

```
Noted. I've removed this task:
[T][✓] buy groceries
Now you have 2 tasks in the list.
```  
  
### 4.7 `find`

Finds the task from the list of tasks from a keyword by typing, `find "keyword"`.

Example of usage: 

`find outing`

Expected outcome:

```
Here are the matching tasks in your list:
1. [E][✗] family outing (at: Sep 20 2020)
2. [E][✗] class outing (at: Oct 20 2020)
```  
### 4.8 `bye`

Exit the application by typing, `bye`.

Example of usage: 

`bye`

Expected outcome: application closes.
  