# Duke User Guide
Duke is your customized helper chatbot that is always available for you to help you managing
your tasks, deadlines, and events. You can add, mark, and delete the tasks by typing it in the
GUI.

## Table of Contents
- [Requirements](#requirements)
- [Features](#features)
    - [Interactive GUI](#interactive-gui)
    - [Your regular events and deadlines](#your-regular-events-and-deadlines)
    - [Finds your tasks](#finds-your-tasks)
    - [Reminds your upcoming events and deadlines](#reminds-your-upcoming-events-and-deadlines)
    - [Archive your tasks](#archive-your-tasks)
    - [Take random notes](#take-random-notes)
    - [Close your Duke without clicking](#close-your-duke-without-clicking)
- [Usage](#usage)
    - [List tasks](#list-tasks)
    - [Adds a task](#adds-a-task)
    - [Deletes a task](#deletes-a-task)
    - [Marks a task as done](#marks-a-task-as-done)
    - [Finds tasks](#finds-tasks)
    - [Archives](#archives)
    - [Notes](#notes)
    
## Requirements
- Duke runs in Java 11 or later.
- The `.jar` file of Duke can be found [here](https://github.com/mario7lorenzo/duke/releases)

## Features 
### Interactive GUI
Duke has a GUI to provide the users more personal feeling toward Duke. You can use Duke similar
to your daily routine, which is chatting with your friends! The difference is your friends may be lazy to 
talk to you, but Duke always responds to you immediately :D. You will never be lonely afterwards, how cool
is that?

<img src="Ui.png" width="200">

### Your regular events and deadlines
Duke helps you to manage your tasks, that are categorized into three types:
- Todo
- Deadline
- Event

You can add, delete, and mark the done tasks in your list.

### Reminds your upcoming events and deadlines
Whenever you start Duke, you will be given a warm-welcome message, and your nearest events and deadlines, so that
you are keep reminded.

### Archive your tasks
Duke can archive your tasks and retrieve them back upon your command.

### Take random notes
Duke provides a service for you to put miscellaneous notes, such as the size of your girlfriend's clothes,
the movies that your mother like, etc. Duke is perfect for you who have a goldfish memory :p.

### Close your Duke without clicking
Duke can bid farewell to you, simply by typing `bye` in your text box. As simple as that.

## Usage

### List tasks
List all the tasks that Duke has memorized.<br>
`list`<br>

Example of usage:
`list`<br>It will show all the tasks in the list.

### Adds a task
Adds a new task to your list so Duke can memorize it for you. Since Duke classifies a task into three types, you can
have the following format:
- `todo [description]`<br>Adds your to-do task to your list.
- `deadline [description] /by [due-date]`<br>Adds your deadline that dues on the `due-date` to the list. Your `due-date`
must be in `dd/MM/YYYY HH:mm` format.
- `event [description] /at [event-date]`<br>Adds your event that happens on the `event-date` to the list. Your `event-date`
must be in `dd/MM/YYYY HH:mm` format.

Example of usage: 
- `todo swimming in money`<br>
Adds a new todo task with description `swimming in money`. Duke will chat you back to inform whether it is added
successfully or there exists a format error.
- `event valentine date with my girlfriend :* /at 14-02-2020 22:00`<br>
Adds a new event task with description `event valentine date with my girlfriend :*` at `14-02-2020 22:00`. Duke will 
chat you back to inform whether it is added successfully or there exists a format error, such as the event date format.
- `deadline CS2103 Duke Project /by 14-02-2020 23:59`<br>
Adds a new deadline task with description `CS2103 Duke Project` which due at `14-02-2020 23:59`. Duke will 
chat you back to inform whether it is added successfully or there exists a format error, such as the due date format.

### Deletes a task
Removes a task from your list, according to its number inside the list.<br>
`delete [task-index]`<br>

Example of usage:
`delete 1`
Removes the first task that appears in your task list. Duke will chat you back to inform whether it is deleted
successfully or there exists an error, such as the number exceeds the number of tasks in the list.

### Marks a task as done
Marks a task in a list, according to its number inside the list.<br>
`done [task-index]`<br>

Example of usage:
`done 1`<br>
Marks the first task as done. Duke will chat you back to inform whether it is deleted successfully or there exists 
an error, such as the number exceeds the number of tasks in the list.

### Finds tasks
Lists all the task with certain keyword.<br>
`find [keyword]`<br>

Example of usage:
`find CS2103`<br>
Displays all the tasks that contains `CS2103` in their description.

### Archives
Duke can put your tasks into an archive. The archived tasks can be deleted, or added back to the list. The commands 
regarding to archives are listed below:
- `archive-list`<br>Displays all the archived tasks.
- `archive-add [task-index]`<br>Moves the task of a given index in the list to the archive.
- `archive-delete [archived-task-index]`<br>Deletes the task permanently from the archive.
- `unarchive [archived-task-index]`<br>Unarchives the task from the archive.

Example of usage:
- `archive-list`<br>
Duke will list all the archived tasks.
- `archive-add 1`<br>
Duke will move the first task in the current list to the archive.
- `archive-delete 1`<br>
Duke will delete the first task in the archived task list from the archive.
- `unarchive 1`<br>
Duke will unarchive the first task in the archived list.

### Notes
Duke can memorize random notes from the user. The commands regarding to notes are listed below:
- `notes-list`<br>Displays all the notes that Duke has memorized.
- `notes-add [description]`<br> Adds a note with the description.
- `notes-delete [notes-index]`<br> Deletes the note with a certain index from the note list.

Example of usage:
- `notes-list`<br>
Duke will list all the notes that it has memorized.
- `notes-add my girlfriend's birthday is on 5th of November`<br>
Duke will add a note with details `my girlfriend's birthday is on 5th of November`.
- `notes-delete 1`<br>
Duke will delete the first note in the notes list.