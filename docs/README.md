# User Guide

## Features 

### Tasks
Creation of Todo Tasks (without date) as well as Deadline and Event Tasks (with date). 

### Tagging
Tags Tasks with identifiers. 

## Usage

### `tag`

Tags the selected task with a string for easier searching and identification

* Example of usage: `tag [index] #[identifier]`
* Expected outcome: `Task tagged successfully`

On next occurrence of task being printed out, 
the corresponding tag will be printed at the end. 

`[E][X] CS2103 Exam (at Apr 25 2020 1300) Tags: #sad`

### `todo`

Adds a todo to your list.

* Format: `todo [description]`
* Example of usage: `todo Print CS2103 Textbook`

### `event`

Adds an event to your list with an event time.

* Format: `event [description] /at YYYY-MM-DD (HHmm)`
* Example of usage: `event CS2103 Exam /at 2020-04-25 1300`

### `deadline`

Adds a deadline to your list with a due date.

* Format: `deadline [description] /by YYYY-MM-DD (HHmm)`
* Example of usage: `deadline CS2103 Post Lecture Quiz /by 2020-02-19 2359`

### `list`

Displays a list of all current Tasks.

* Format: `list`

### `find`

Searches for corresponding Tasks containing keyword provided.

* Format: `find [keyword]`
* Example of usage: 
`find life`

### `delete`

Deletes the task at index specified (one-indexed).

* Format: `delete [index]`
* Example of usage: 
`delete 1`

### `done`

Marks task at index specified as done.

* Format: `done [index]`
* Example of usage: 
`done 1`

### `bye`

Exits AutoResponder

* Format: `bye`
