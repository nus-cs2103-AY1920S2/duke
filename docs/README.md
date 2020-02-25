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

`event read books /by 2020-03-25`
`event explore the world /by 2060-12-31`

### 3. deadline

`deadline` : add an deadline to your list. 

Format : `deadline [name] /by [date] [priority]`

Remarks:

- `[date]` is in `YYYY-MM-DD` format.
- `[priority]` is optional. Its default value is 10. 
Lower value has higher priority.

Example:

`deadline read books /by 2020-03-25`
`deadline explore the world /by 2060-12-31`

### 4. list

`list` : list all your tasks, deadlines and events.

Format : `list`


Example:

`deadline read books /by 2020-03-25`
`deadline explore the world /by 2060-12-31`

### `Keyword` - Describe action

Describe action and its outcome.

Example of usage: 

`help`

Expected outcome:

    Here's a list of command examples you can use:
    1. todo read book 2
    2. deadline finish book /by 2020-02-30 5
    3. event book meeting /at 2020-03-02 1
    4. list
    5. done 2
    6. delete 2
    7. bye
