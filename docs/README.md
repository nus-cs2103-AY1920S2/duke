# User Guide

## Features

### Add tasks

You can add tasks of type **Todo** (which only has a description) **Deadline** (with a single date-time group as the deadline), or **Event** (with two date-time groups as the start/end instants respectively), using the `todo`, `deadline`, and `event` commands respectively.

### List tasks

You can list tasks using the command `list`. 

### Delete tasks

You can delete previously-added tasks using the `delete` command. 

### Find task

You can find tasks using keywords, with the `find` command. 

### File output

The task file is saved as a `.csv` for easy viewing in <kbd>Microsoft Excel</kbd> and other spreadsheet programs. 

## Usage

### `todo <what is to be done>` — Creates a To-Do

Creates a To-Do, set by default as incomplete. 

### `deadline <what is due> </by | /at> <due date/time>` — Creates a Deadline

Creates a Deadline, set by default as incomplete, with the due date/time as given.  _Some_ natural date-time groups are accepted (but some are still buggy):

- `24th Feb 2020`
- `Monday 5 pm` (buggy, as this goes to the *current* week's Monday, and not the next)
- `11:40`, or `4:20pm`, or `12am SGT`
- `11/2/2020` or `22/7/2020` or even `14-3-2020` (European format: `dd/MM/uuuu`)
- `tomorrow` is not legit.

> Example use:
>
> - `deadline return book /by 7 pm`
> - `deadline complete CS2103T iP /by Tuesday 18/2/2020 23:59`

### `event <event name> /from <start instant> /to <end instant>` — Creates an Event from `start instant` to `end instant`

As for Deadlines, natural dates/times are accepted, and the same bugs apply.

### `list`

Lists all the tasks.

### `find <keyword>` — Finds tasks

Finds all tasks that have a detail which contain `keyword`.

> Example of usage: `find book`

### `delete <task number>`

Deletes the task at the given `number`.
