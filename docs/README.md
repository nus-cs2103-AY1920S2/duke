# User Guide

## Introduction
This is Goose, the annoying task manager that you never wanted.
Goose is optimized for those who prefer to work with a Command Line Interface (CLI) because
he can only listen to commands.

## Features

### `list`

Displays current todo list.

* Format: `list`

### `todo`

Adds a task to your list.

* Format: `todo [description]`
* Example of usage: `todo borrow book`

### `event`

Adds an event to your list.

* Format: `event [description] /at [DD/MM/YYYY] [24-hr Time]`
* Example of usage: `event meeting /at 15/2/2020 1400`

### `deadline`

Adds a deadline to your list.

* Format: `deadline [description] /by [DD/MM/YYYY] [24-hr Time]`
* Example of usage: `deadline homework /by 15/3/2020 2359`

### `find`

Retrieves list of tasks containing keyword.

* Format: `find [KEYWORD]`
* Example of usage: 
`find homework`

### `delete`

Deletes the task at index specified.

* Format: `delete [INDEX]`
* Example of usage: 
`delete 1`

### `done`

Marks task at index specified as done.

* Format: `done [INDEX]`
* Example of usage: 
`done 1`

### `undone`

Marks task at index specified as undone.

* Format: `undone [INDEX]`
* Example of usage: 
`undone 1`

### `undo`

Undo your last action. If you 'undo' after an 'undo',
 the list will revert to its original state.

* Format: `undo`

### `bye`

Say goodbye to Goose :(

* Format: `bye`