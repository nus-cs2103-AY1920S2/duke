# Duke - User Guide
By: `@jiachen247`      Since: `Jun 2020`      Licence: `MIT`

## Introduction

Duke is for those who *prefer to use a desktop app for managing tasks*. More importantly, Duke is personal assistant *optimized for those who prefer to work with a Command Line Interface* (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Duke can manage yout tasks faster than traditional GUI apps. Interested? Jump to the <<Quick Start>> to get started. Enjoy!

## Quick Start

- Ensure you have Java `11` installed in your Computer.
- Download the latest `duke.jar` [here](https://github.com/jiachen247/duke/releases).
-  Copy the file to the folder you want to use as the home folder for Duke.
-  Double-click the file to start the app. The GUI should appear in a few seconds.

![](Ui.png?raw=true)

-  Type the command in the command box and press kbd:[Enter] to execute it. +
e.g. typing *`help`* and pressing kbd:[Enter] will open the help window.
-  Some example commands you can try:

* *`hello`* : say hello to duke
* *`todo`* : add a todo task
* *`event`* : add a new event 
* *`deadline`* : add a new deadline 
* **`done`**`2`: mark task 2 as complete 
* **`delete`**`3` : delete task 3
* *`list`* : list all tasks
* *`find`* : find tasks
* *`help`* : ask duke for help
* *`exit`* : exit duke

-  Refer to <<Features>> for details of each command.

## Features

###
*Command Format*

* Words in `UPPER_CASE` are the parameters to be supplied by the user e.g. in `add DESCRIPTION`
* Items in square brackets are optional e.g `[MORE KEYWORDS]` can be used as needed.
###

### Viewing help : `help`

Format: `help`

### Adding a todo: `todo`

Adds a todo task to the duke +
Format: `todo DESCRIPTION`

[TIP]
A description can be more than a single word

Examples:

* `todo clean my room`

### Adding a todo: `event`

Adds a event task to the duke +
Format: `event DESCRIPTION /at DATETIME`

[TIP]
Datetime has to be in the form `dd/MM/yyyy HHmm`

Examples:

* `event sleep /by 01/01/2019 2130`

### Adding a todo: `deadline`

Adds a deadline task to the duke +
Format: `deadline DESCRIPTION /by DATETIME`

[TIP]
Datetime has to be in the form `dd/MM/yyyy HHmm`

Examples:

* `deadline do homework /by 01/01/2019 2359`

### Listing all tasks : `list`

Shows a list of all task in duke. +
Format: `list`

### Searching for tasks: `find`

Finds persons whose names contain any of the given keywords. +
Format: `find KEYWORD [MORE_KEYWORDS]`

****
* The search is case insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the task description is searched.
* Matched if task description contains the keywords entirely
****

Examples:

* `find John` +
Returns `john` and `John Doe`
* `find Betsy Tim John` +
Returns any person having names `Betsy`, `Tim`, or `John`

### Deleting a task : `delete`

Deletes the specified task from duke. +
Format: `delete INDEX`

****
* Deletes the task at the specified `INDEX`.
* The index refers to the index number shown in the displayed task list. (*does not work for find window)
* The index *must be a positive integer* 1, 2, 3, ...
****

Examples:

* `list` +
`delete 2` +
Deletes the 2nd task in duke's task list.

### Exiting the program : `exit`

Exits the program. +
Format: `exit`

### Saving the data

Duke task data are saved in the hard disk automatically after any command that changes the data. +
There is no need to save manually.

## FAQ

*Q*: How do I transfer my data to another Computer? +
*A*: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Duke folder.

## Command Summary
* *Hello* : `hello`
* *Todo* : `todo DESCRIPTION`
* *Event* : `event DESCRIPTION /at DATETIME`+
eg. `event sleep /at 01/01/2019 2130`
* *Deadline* : `deadline DESCRIPTION /by DATETIME` +
eg. `deadline return book /by 01/01/2019 1800`
* *Delete* : `delete INDEX` + eg. `delete 3`
* *List* : `list`
* *Find* : `find KEYWORD [MORE_KEYWORDS]` + eg. `find jiachen`
* *Done* : `done INDEX` + 
eg. `done 2`
* *Help* : `help`
* *Exit* : `exit`

This user guide format has been adapted from [addressbook level 3](https://github.com/nus-cs2103-AY1920S2/addressbook-level3/blob/master/docs/UserGuide.adoc)