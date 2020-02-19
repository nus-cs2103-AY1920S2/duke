# User Guide

## Features 

### Adding tasks 
Duke supports the following task types.

Task | Description
------------ | -------------
Todo | A task that only contains a description
Deadline | A task that also has a time tagged to it to represent its deadline
Event | A task that also has a time tagged to it to represent its event timing
Reminder | A task that also has a reminder time tagged to it, a pop up appears at the time with its description

### Task management
- Tasks can be listed
- Tasks can be marked as done
- Tasks can be deleted
- Tasks can be found
- Tasks are saved locally on the machine and reloaded upon the application being reopened.

## Usage

### `todo <description>` - Add a regular todo task

Adds a regular todo item with the corresponding description.

Example of usage: 

`todo buy milk`

Expected outcome:

```
Got it. I've added this task:
[T][✘] buy milk
Now you have 1 tasks in the list.
```

### `event/deadline <description> /at(or by) yyyy-mm-dd` - Add an event or deadline task

Adds a event or deadline with the corresponding description and date. Use `by` for deadline instead of event.

Example of usage: 

`event buy milk /at 2020-02-20`

Expected outcome:

```
Got it. I've added this task:
[E][✘] buy milk (at: Feb 20 2020)
Now you have 2 tasks in the list.
```

### `reminder <description> /at yyyy-mm-ddThh-mm-ss` - Adds a reminder

Adds a reminder which will trigger a pop up with the description during the specified date and time.

Example of usage: 

`reminder buy milk /at 2020-02-11T00:12:00`

Expected outcome:

```
Got it. I've added this task:
[R][✘] buy milk (at: Feb 20 2020 00:12:00)
Now you have 3 tasks in the list.
```

Pop up with description `buy milk` appears at Feb 20 2020 00:12:00.

### `list` - lists tasks

Duke lists out all the tasks you currently have.

### `done/delete <index>` - Marks task as done or deletes it

Duke marks the task as done or deletes according to task index when the task is listed.

### `find <description>` - Finds tasks

Duke shows the task that matches the description. 