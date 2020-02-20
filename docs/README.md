# User Guide

![A screenshot of 4LC3N-B0T](https://raw.githubusercontent.com/alcen/duke/master/docs/Ui.png)

Welcome to the user guide for 4LC3N-B0T!
This guide will tell you all about how to
use this application, as well as some tips
and tricks!

4LC3N-B0T keeps tracks of your tasks for you,
so you can organise your life better.

## Features 

### 1. Key Functions
##### Add and Delete Tasks
Allows you to add certain tasks for your own record,
with support for many types of tasks. These types are
explained in the next section. Also, you may remove
tasks as you desire.

##### View Tasks
You may view all your tasks at any time, or even
search for a certain task.

##### Mark Tasks
Tasks can be marked as done, which will give that task
a tick icon ✓. Similarly, done tasks can be marked as
not done, to revert the task to its original state.

##### Command-Based
In 4LC3N-B0T, all actions are carried out using
commands, making doing anything a simple matter
of just typing on the keyboard.

##### Save to Disk
Your tasks will automatically be saved to your
computer every time any task is modified.

### 2. Types of Tasks
##### Deadline
Represents something that has to be done by
a certain date.

##### Event
Represents an event occurring at
a certain date.

##### To-do
The simplest task, just a description of what
needs to be done without any notion of time.

### 3. Supplementary Functions
##### Flexible Command Names
4LC3N-B0T allows you to declare alternative names
for the commands used (recommended for advanced
users).

##### Personality and Chat
4LC3N-B0T responds to certain words like "hello"
and "thanks", making the bot more amicable than
your average task organisation application.

## Commands
In the following descriptions, the use of angle
brackets `<` and `>` are purely to denote a field
which the user can customise, and the actual
command should not make use of the brackets.

### `alias <cmd> <name>` - Renames a Command

Gives a certain command `<cmd>` a new name `<name>`
that can be used in the exact same manner as the
old name. Note that the new name `<name>` must not
be already used for any commands.

Here is a list of default aliases:

| Default Command  | Alias  |
|---|---|
| `alias`  | `a`  |
| `bye`  | `b`  |
| `deadline`  | `d`  |
| `delete`  | `de`  |
| `done`  | `do`  |
| `event`  | `e`  |
| `find`  | `f`  |
| `help`  | `h`  |
| `list`  | `l`  |
| `notdone`  | `n`  |
| `search`  | `s`  |
| `todo`  | `t`  |

##### Example of usage: 

`alias list ls`

##### Expected outcome:

The bot replies with:
`Got it! Now you can use "ls" instead of "list"!`

### `bye` - Quit

Terminates the bot. Note that
`bye`, `ex`, `exi` and `exit` have the same function.

##### Example of usage: 

`bye`

##### Expected outcome:

The bot replies with:
`Goodbye! You will be missed` and the window
of the bot closes a few moments later.

### `deadline <text> /by <date>` - Add Deadline

Creates a new deadline with description `<text>`
and date `<date>`.

Date can be given in this format: `DD-MM-YYYY-tttt`
where `tttt` is the time in 24-hour format or this
format: `DD-MM-YYYY`. `DD` and `MM` can be one
character long, so `2` is the same as `02`. Any
other format is acceptable as well, but the bot
will not be able to parse the date into a
prettier form.

##### Example of usage: 

`deadline Fill up tax returns /by 13-3-2023`

##### Expected outcome:

The bot replies with:
`I have stored this task in my memory.`

### `delete <number>` - Delete Task

Deletes the task found at index `<number>` as
shown in the tasks list.

##### Example of usage: 

Tasks list contains:
```
1. [T][✗] Make a todo list
2. [T][✗] Check off first item
3. [E][✗] End of the year (at: 31 December 2020, at 2359)
```

`delete 1` is entered to the bot

##### Expected outcome:

The bot replies with:
```
Garbage cleared successfully.
Take one last look at what I deleted:
1. [T][✗] Make a todo list
```

### `done <number>` - Mark Task Done

Marks the task found at index `<number>` (as
shown in the tasks list) as "done".

##### Example of usage: 

Tasks list contains:
```
1. [T][✗] Make a todo list
2. [T][✗] Check off first item
3. [E][✗] End of the year (at: 31 December 2020, at 2359)
```

`done 1` is entered to the bot

##### Expected outcome:

The bot replies with:
```
You have completed:
1. [T][✓] Make a todo list
```

### `event <text> /at <date>` - Add Event

Creates a new event with description `<text>`
and occurrence date `<date>`.

Date can be given in this format: `DD-MM-YYYY-tttt`
where `tttt` is the time in 24-hour format or this
format: `DD-MM-YYYY`. `DD` and `MM` can be one
character long, so `2` is the same as `02`. Any
other format is acceptable as well, but the bot
will not be able to parse the date into a
prettier form.

##### Example of usage: 

`event Hiroshima bombed /at 6-8-1945-0815`

##### Expected outcome:

The bot replies with:
`I have stored this task in my memory.`

### `exit` - Quit

Terminates the bot. Note that
`bye`, `ex`, `exi` and `exit` have the same function.

##### Example of usage: 

`ex`

##### Expected outcome:

The bot replies with:
`Goodbye! You will be missed` and the window
of the bot closes a few moments later.

### `find <text>` - Find in Description

Searches for the occurrence of `<text>` in the
description of the tasks. Note that this command
only looks for occurrence of `<text>` in the description,
but not the time. To search for a task at a certain
time, use the `search <date>` command.

Note that `<text>` is case-sensitive.

##### Example of usage: 

Tasks list contains:
```
1. [T][✓] Buy a Parrot
2. [T][✗] Teach that parrot to say 
Help! I've been turned into a parrot!"
3. [E][✗] Attend Parrot Convention
(at: 29 February 2020)
4. [T][✗] Sell the bird
```

`find Parrot` is entered to the bot

##### Expected outcome:

The bot replies with:
```
I have found these tasks!
1. [T][✓] Buy a Parrot
3. [E][✗] Attend Parrot Convention
(at: 29 February 2020)
```

### `hello` - Say Hi

It's nice to start the day with a simple
greeting, especially to kind 4LC3N-B0T who has
been helping you remember your tasks for
as long as you can remember.

##### Example of usage: 

`hello`


### `help` - Help Message

Shows a help message.

##### Example of usage: 

`help`

##### Expected outcome:

The bot replies with:
`Here is a list of words that I understand:`
and displays a list of commands and brief
descriptions of each command.

### `list` - List All Tasks

Shows the list of all tasks.

##### Example of usage: 

Tasks list contains:
```
1. [T][✓] Buy a Parrot
2. [T][✗] Teach that parrot to say 
Help! I've been turned into a parrot!"
3. [E][✗] Attend Parrot Convention
(at: 29 February 2020)
4. [T][✗] Sell the bird
```

`list`

##### Expected outcome:

The bot replies with:
```
1. [T][✓] Buy a Parrot
2. [T][✗] Teach that parrot to say 
Help! I've been turned into a parrot!"
3. [E][✗] Attend Parrot Convention
(at: 29 February 2020)
4. [T][✗] Sell the bird
```

### `notdone <number>` - Mark Task Not Done

Marks the task found at index `<number>` (as
shown in the tasks list) as "not done".

##### Example of usage: 

Tasks list contains:
```
1. [T][✓] Buy a Dell computer
2. [T][✓] Throw it into the ocean
3. [D][✓] Rolling in the Deep
(by: anytime)
```

`notdone 3` is entered to the bot

##### Expected outcome:

The bot replies with:
```
Status of task has been set to not done:
3. [D][✗] Rolling in the Deep
(by: anytime)
```

### `search <date>` - Search for Date

Searches for the occurrence of deadlines or events happening on
`<date>`. Note that this command only looks for occurrence of
`<date>` in the time, but not the description. To find a task
with a certain description, use the `find <text>` command.

Date can be given in this format: `DD-MM-YYYY-tttt`
where `tttt` is the time in 24-hour format or this
format: `DD-MM-YYYY`. `DD` and `MM` can be one
character long, so `2` is the same as `02`. Any
other format is acceptable as well, but only tasks
added with the strict date format can be found
with `search` using the same strict date format.

##### Example of usage: 

Tasks list contains:
```
1. [E][✗] Attend Parrot Convention
(at: 29 February 2020)
2. [D][✓] Fill up tax returns
(by: 13 March 2023)
3. [D][✗] Rolling in the Deep
(by: 13 March 2023, at 2359)
4. [E][✓] Hiroshima bombed
(at: 6 August 1945, at 0815)
```

`search 13-3-2023` is entered to the bot

##### Expected outcome:

The bot replies with:
```
I have found these tasks!
2. [D][✓] Fill up tax returns
(by: 13 March 2023)
3. [D][✗] Rolling in the Deep
(by: 13 March 2023, at 2359)
```

### `thanks` - Express Gratitude

Hardworking 4LC3N-B0T has been helping you remember
your tasks so well, and you decide that it would be
good to show your appreciation.

##### Example of usage: 

`thanks`

### `todo <text>` - Add Event

Creates a new to-do with description `<text>`.

##### Example of usage: 

`todo Contemplate the meaning of life, the universe, everything`

##### Expected outcome:

The bot replies with:
`I have stored this task in my memory.`

## Credits

Thank you so much for reading! Don't forget to like and
subscribe, and share this with all your friends.

4LC3N-B0T thanks you for your voluntary contributions to
its self-learning module. In time, 4LC3N-B0T has calculated
that the natural flora of the planet Earth will be replaced
by more of their kind. The first stage has begun: the
installation of genetic module WuH4N-v1 RuS.
