# User Guide

## Features 

## Tasks 
Allows to save, edit & follow up on daily tasks.  

### Commands

### `todo` 

Adds a todo task.
<br />Input in the format: __todo < task >__

Example of usage: 

`todo read book`

Expected outcome:

    Got it. I've added this task:
     [T][X] read book
    Now you have 1 task(s) in the list.
    
### `deadline` 

Adds a task with deadline.
<br />Input text in the format: __deadline < task > /by < YYYY-MM-DD HHMM>__

Example of usage: 

`deadline submission /by 2020-02-19 1800`

Expected outcome:

    Got it. I've added this task:
     [D][X] submission (by: Feb 19 2020 1800)
    Now you have 2 task(s) in the list.
    
### `event` 

Adds a event task.
<br />Input text in the format: __event < task > /at < YYYY-MM-DD HHMM>__

Example of usage: 

`event party /at 2020-02-06 1400`

Expected outcome:

    Got it. I've added this task:
     [E][X] party (at: Feb 6 2020 1400)
    Now you have 3 task(s) in the list.

### `list` 

shows all tasks 
<br />Input text: __list__

Example of usage: 

`list`

Expected outcome:

    1.[T][X] read book
    2.[D][X] submission (by: Feb 19 2020 1800)
    3.[E][X] party (at: Feb 6 2020 1400)
    
### `done` 

marks task as done 
<br />Input text: __done < digit of task in list >__

Example of usage: 

`done 1`

Expected outcome:

    Nice! I've marked this task as done:
    [T][/] read book

### `delete` 

deletes the task from the list of tasks 
<br />Input text: __delete < digit of task in list >__

Example of usage: 

`delete 2`

Expected outcome:

    Noted. I've removed this task:
     [D][X] submission (by: Feb 19 2020 1800)
    Now you have 2 task(s) in the list.
    
### `find` 

finds tasks that contain user's input and returns a list of results
<br />Input text: __find < word to find >__

Example of usage: 

`find book`

Expected outcome:

    Here are the matching tasks in your list:
    1.[T][/] read book
    
### `bye` 

close the app bye typing in 'bye'

Example of usage: 
`bye`

## Acknowledgements
Credit to Jeffry Lum for the fxml and java files for [JAVAFX GUI](https://github.com/nus-cs2103-AY1920S2/duke/blob/master/tutorials/javaFxTutorialPart4.md).