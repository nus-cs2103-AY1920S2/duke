# User Guide

![DUKE](Ui.png)

> One of my favorites!
>
> &mdash; <cite>Carl, a satisfied user</cite>

### Table of Contents
1. **[Introduction](#introduction)**
2. **[Features](#features)**
3. **[Usage](#usage)**
4. **[Commands](#commands)**
   - **[Summary of commands](#summary-of-commands)**
   - **[`help`](#help)**
   - **[`list`](#list)**
   - **[`todo`](#todo)**
   - **[`deadline`](#deadline)**
   - **[`event`](#event)**
   - **[`delete`](#delete)**
   - **[`done`](#done)**
   - **[`find`](#find)**
   -  **[`bye`](#bye)**

## Introduction

Behold DUKE! DUKE is a personal assistant chatbot that helps you manage your tasks and activities in your life. With a designed focused largely on a command-line interface (CLI) and augmented with a graphical user interface (GUI), users familiar with the CLI  will definitely find DUKE a breeze to use!

## Features 

* Keeps tracks of your tasks!
* Understands different types of tasks (to-do's, deadlines, events)!
* Lets you mark tasks as complete!
* Saves your tasks to memory!
* Requires almost no set-up!
* User-friendly!

## Usage

Run DUKE in 3 simple steps:

1. Install JRE 11 or later.
2. Download the latest `jar` file from [here](https://github.com/limdylan/duke/releases).
3. To run DUKE with the GUI, either:
   - Double-click the `jar` file, or
   - Do `java -jar duke.jar`

The above steps are for running DUKE with a GUI. To run DUKE entirely in the terminal without a GUI instead, do `java -jar duke.jar -t`

Take note that DUKE can only be run without a GUI in Windows Subsystem for Linux (WSL), due to [a lack of official support for graphical programs from Microsoft](https://github.com/Microsoft/WSL/issues/2356).

## Commands

### Summary of commands:

|Command|Description|
|-|-|
|[`help`](#help)|Displays the help message.|
|[`list`](#list)|Lists all tasks.|
|[`todo <description>`](#todo)|Adds a new to-do task with the given description.|
|[`deadline <description> /by <datetime>`](#deadline)|Adds a new task with the given description and time of deadline.|
|[`event <description> /at <datetime>`](#event)|Adds a new task with the given description and time of event.|
|[`delete <index>`](#delete)|Deletes the task at the given index.|
|[`done <index>`](#done)|Marks the task at the given index as done.|
|[`find <keyword>`](#find)|Finds tasks whose description contains the given keyword, and lists them.|
|[`bye`](#bye)|Quits the program.|

---
### `help`

Displays the help message, which shows the commands available and their basic usage and any other information.

#### Syntax

`help`

* This command takes in no arguments.

#### Example
* Usage:
  * `help`
* Outcome:
  * Shows the following help message (truncated):

        Commands available:
        help
                Displays the help message.

        list
                Lists all tasks.
        ...


---
### `list`

Lists out all tasks currently tracked by DUKE.

#### Syntax

`list`

* This command takes in no arguments.

#### Example

* Usage:
  * `list`
* Outcome:
  * Shows all tasks, together with their completion status and details, for example:

        Tasks so far:
        {
          1. [T][ ] Eat lunch
          2. [D][ ] Submit work (by: Wed, 19 Feb 2020, 23:59)
          3. [T][X] Take a nap
          4. [E][ ] Lab (at: Thu, 13 Feb 2020, 14:00)
        }

---
### `todo`

Adds a new task with a description for DUKE to track.

To-do tasks are identified by `[T]`.

#### Syntax

`todo <description>`

* Description cannot contain `|` (vertical pipe character).

#### Example

* Usage:
  * `todo Eat lunch`
* Outcome:
  * Adds a new, uncompleted task with description `Eat lunch`, and shows:

        Added 'Eat lunch'

---
### `deadline`

Adds a new task with a description and time of deadline for DUKE to track.

Deadline tasks are identified by `[D]`.

#### Syntax

`deadline <description> /by <datetime>`

* Description cannot contain `|` (vertical pipe character).
* Time of deadline must be in `dd/MM/yyyy HHmm` format.

#### Example

* Usage:
  * `deadline Submit work /by 19/02/2020 2359`
* Outcome:
  * Adds a new, uncompleted task with description `Submit work` and deadline `Wed, 19 Feb 2020, 23:59`, and shows:

        Added 'Submit work'

---
### `event`

Adds a new task with a description time of event for DUKE to track.

Event tasks are identified by `[E]`.

#### Syntax

`event <description> /at <datetime>`

* Description cannot contain `|` (vertical pipe character).
* Time of deadline must be in `dd/MM/yyyy HHmm` format.

#### Example

* Usage:
  * `event Lab /at 19/02/2020 2359`
* Outcome:
  * Adds a new, uncompleted task with description `Lab` and event time `Thu, 13 Feb 2020, 14:00`, and shows:

        Added 'Lab'

---
### `delete`

Removes a given task from DUKE, so that DUKE will no longer keep track of this task. Tasks are identified by their  index numbers as shown in [`list`](#list).

#### Syntax

`delete <index>`

#### Example

* Usage:
  * `delete 2`
* Outcome:
  * Removes the second task (as per the example in [`list`](#list)), and shows:

        Removed 'Submit work'

    An immediate subsequent `list` command will show:

        Tasks so far:
        {
          1. [T][ ] Eat lunch
          2. [T][X] Take a nap
          3. [E][ ] Lab (at: Thu, 13 Feb 2020, 14:00)
        }

    Notice that the `Submit work` task has been removed.

---
### `done`

Marks a given task tracked by DUKE as done. Tasks are identified by their  index numbers as shown in [`list`](#list).

#### Syntax

`done <index>`

#### Example

* Usage:
  * `done 2`
* Outcome:
  * Marks the second task (as per the example in [`list`](#list)) as done, and shows:

        Marked 'Submit work' as done

    An immediate subsequent `list` command will show:

        Tasks so far:
        {
          1. [T][ ] Eat lunch
          2. [D][X] Submit work (by: Wed, 19 Feb 2020, 23:59)
          3. [T][X] Take a nap
          4. [E][ ] Lab (at: Thu, 13 Feb 2020, 14:00)
        }

    Notice that the completion status for the second task has now changed from `[ ]` to `[X]`.

---
### `find`

Finds and returns all tasks whose description contains the given keyword.

#### Syntax

`find <keyword>`

#### Example

* Usage:
  * `find submit`
* Outcome:
  * Shows all tasks with description containing `submit`:

        Tasks with 'submit':
        {
          1. [D][ ] Submit work (by: Wed, 19 Feb 2020, 23:59)
        }

---
### `bye`

Quits the program gracefully.

#### Syntax

`bye`

* This command takes in no arguments.

#### Example

* Usage:
  * `bye`
* Outcome:
  * Shows:

        Goodbye!

    and exits the application gracefully.
