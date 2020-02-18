# User Guide

![Duke Screenshot](/docs/Ui.png)

**Duke** is a personal task manager. Useful features include, but not limited to:
* Maintain a todo list
* Maintain an event happening list
* Maintain a deadline list
* Quickly search through the list using keywords

## Features

### Adding a todo

Adds a todo to **Duke**'s list.
Command: `todo <title>`
Example: `todo buy halloween costume`

### Adding an event

Adds an event (plus the time it's happening) to **Duke**'s list.
Command: `event <title> /at <yyyy-mm-dd>`
Example: `event open house /at 2020-05-20`

### Adding an deadline

Adds an deadline (plus the time it's due) to **Duke**'s list.
Command: `deadline <title> /by <yyyy-mm-dd>`
Example: `deadline cs2103t post lecture quiz /by 2020-05-20`

### Marking a todo/event/deadline as done

Marks a todo/event/deadline as done, thus removing it from **Duke**'s list.
Command: `done <item-index>`
Example: `done 1` will mark the first item in the list as done.

### Finding by keyword

Finds a todo/event/deadline using a specified keyword from **Duke**'s list.
Command: `find <keyword>`
Example: `find cs3230` will find all the items containing the keyword `cs3230`.

### Deleting a todo/event/deadline

Deletes a todo/event/deadline from **Duke**'s list by its index.
Command: `delete <item-index>`
Example: `delete 1` will remove the first item from the list.

### Listing all todos/events/deadlines

Lists all todos/events/deadlines stored in **Duke**'s list.
Command: `list`

### Displaying help message

Displays **Duke**'s available commands.
Command: `help`
