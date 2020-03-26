# Duke User Guide

Duke is a chatbot that can help you organize your tasks, deadlines, and events.

## Features 

List of feature:

### 1. todo

`todo` : add a task to your list. 

Format : `todo [name] [priority]`

Remarks:

- `[priority]` is optional. Its default value is 10.
Lower value has higher priority.

Example:

- `todo read books`
- `todo do homework 1`

### 2. event

`event` : add an event to your list. 

Format : `event [name] /by [date] [priority]`

Remarks:

- `[date]` is in `YYYY-MM-DD` format.
- `[priority]` is optional. Its default value is 10. 
Lower value has higher priority.

Example:

`event read books /by 2020-03-25`\
`event explore the world /by 2060-12-31`

### 3. deadline

`deadline` : add an deadline to your list. 

Format : `deadline [name] /by [date] [priority]`

Remarks:

- `[date]` is in `YYYY-MM-DD` format.
- `[priority]` is optional. Its default value is 10. 
Lower value has higher priority.

Example:

`deadline read books /by 2020-03-25`\
`deadline explore the world /by 2060-12-31`

### 4. list

`list` : list all your tasks, deadlines and events.

Format : `list`

Example response:

    Here are the tasks in your list:
    1. [E][✘] party (by: May 23 2020)
    2. [T][✓] read book
    
### 5. done

`done` : Do one of your task and be done with it. 

Format : `done [index]`

Remarks:

- `[index]` is 1-indexing (starts with 1).

Example:

`list`

    Here are the tasks in your list:
    1. [E][✘] party (by: May 23 2020)
    2. [T][✓] read book
    
`done 1`

    Nice! I've marked this task as done:
    [E][✓] party (by: May 23 2020)
    
`list`

    Here are the tasks in your list:
    1. [E][✘] party (by: May 23 2020)
    2. [T][✓] read book
    
### 6. delete

`delete` : Delete your existing task from the list. 

Format : `delete [index]`

Remarks:
- `[index]` is 1-indexing (starts with 1).

Example:

`list`

    Here are the tasks in your list:
    1. [E][✘] party (by: May 23 2020)
    2. [T][✓] read book
    
`delete 1`

    Noted. I've removed this task:
    [E][✓] party (by: May 23 2020)
    Now you have 1 tasks in the list.
    
`list`

    Here are the tasks in your list:
    1. [T][✓] read book
    
    
### 7. bye

`bye`: Exit from duke

Format: `bye`

### 8. help

`help`: get list of instruction examples you can use. 

Format: `help`

Example:

`help`

    Here's a list of command examples you can use:
    1. todo read book 2
    2. deadline finish book /by 2020-02-30 5
    3. event book meeting /at 2020-03-02 1
    4. list
    5. done 2
    6. delete 2
    7. bye
