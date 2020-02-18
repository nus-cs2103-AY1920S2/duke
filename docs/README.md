# User Guide

## Introduction
Duke Personal Assistant (DPA) is for those who prefer to use a desktop app for managing their to-do list. More importantly, DPA is optimized for those who prefer to work with a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, DPA can get your tasks managed faster than traditional GUI apps. DPA supports 3 types of tasks: Todos, Events, and Deadlines.

## Quick Start
1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest duke.jar [here](www.google.com).
3. Copy the file to the folder you want to use as the home folder for your Duke Personal Assistant.
4. Double-click the file to start the app. The GUI should appear in a few seconds.
5. Type the command in the command box and press `Enter` to execute it.
e.g. typing `help` and pressing `Enter` will open the help window.

## Features 
### Command Format
* Words in `UPPER_CASE` are the parameters to be supplied by the user e.g. in `todo TASK_NAME`, `TASK_NAME` is a parameter which can be used as follows: `todo Submit Assignment`.
* Where the parameters are strings, space separated strings can be input.

### Viewing help: `help`
Format: `help`

Displays all possible commands the user can enter.


### Adding a new To-do: `todo`
Format: `todo TASK_NAME`

Adds a todo with the given `TASK_NAME` to Duke.

E.g. `todo Peer review`


### Adding a new Event: `event`
Format: `event EVENT_NAME /at LOCATION`

Adds an event with the given `EVENT_NAME` and `LOCATION` to Duke.

E.g. `event CS2103 team meeting /at SoC SR1`

### Adding a new Deadline: `deadline`
Format: `deadline TASK_NAME /by DATE`

Adds a deadline with the given `TASK_NAME` and `DATE` to Duke.
The format of `DATE` must be `YYYY-MM-DD`.

E.g. `deadline math assignment /by 2020-02-20`

### Listing all tasks: `list`
Format: `list`

Lists all tasks that Duke has stored.


### Locate tasks by name: `find`
Format: `find SEARCH_PHRASE`

Lists all tasks that contain the input search phrase in the task name. The search phrase is case-insensitive and can search across spaces.

E.g. a task with description `return book` can be found by search phrases `ReTuRn` or `rn bo`.

### Deleting a task: `delete`
Format: `delete INDEX`

Deletes the task at the displayed index. The displayed index is the bullet number of the task when viewed with the command `list`.

### Clearing all entries: `clear`
Format: `clear`

Deletes all tasks that have been stored in Duke.

### Exiting the program: `bye`
Format: `bye`

Exits Duke Personal Assistant.

### Saving data
The all task data is saved the hard disk automatically after any command that changes the data.

There is no need to save manually.

## FAQ
**Q**: How do I transfer my data to another Computer?

**A**: Duke uses the data from the text file it creates in the data/duke.txt folder of the directory duke.jar was run from.

Run the app in the other computer and overwrite the duke.txt file with your previous duke.txt file.
