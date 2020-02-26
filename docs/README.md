# User Guide

### Command Format
- Words in square brackets are user specified items e.g. [NAME] can be John.
- All dates and times are given using the format YYYY-MM-DD HH:MM.
- All parameters must be given in order.
- Descriptions can consist of more than one word.

## Features 

### Adding a todo: `todo`
Format: `todo [DESCRIPTION]`  

Examples:
* `todo sleep`
* `todo create UI screenshot`

### Adding a deadline: `deadline`
Format: `deadline [DESCRIPTION] /by [DATETIME]`

Examples:
* `deadline homework /by 2069-04-20 04:20`
* `deadline CS2103 IP /by 2020-03-01 23:59`

### Adding an event: `event`
Format: `event [DESCRIPTION] /at [DATETIME] to [DATETIME]`

Examples:
* `event recess week /at 2020-02-24 00:00 to 2020-03-01 23:59`
* `event project hell /at 2020-02-24 00:00 to 2020-03-01 23:59`

### Listing all tasks: `list`
Format: `list`

### Marking task as done: `done`
Format: `done [INDEX]`

### Deleting a task: `delete`
Format: `delete [INDEX]`

### Finding relevant tasks: `find`
Finds all the tasks containing an exact match of the string.  
Format: `find [DESCRIPTION]`

Examples:
* `find CS1231` finds tasks with 'CS1231' but not 'cs1231'
* `find CS2106 lab` finds 'CS2106 lab project' but not 'lab CS2106'

### Snoozing tasks: `snooze`
Changes the time information of an event or a deadline.

Format: 
* `snooze [INDEX] [DATETIME]` for deadlines
* `snooze [INDEX] [DATETIME] to [DATETIME]` for events

Examples:
* `snooze 1 2050-05-05 05:05`
* `snooze 2 2040-07-09 to 2060-08-10`





