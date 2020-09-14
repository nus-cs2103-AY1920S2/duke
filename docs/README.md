# User Guide

## 1. Introduction
Duke is a Personal Assistant Chatbot that helps a person to keep track of various tasks and events. Duke is optimised for people who prefer typing and working with the Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).   

## 2. Features 

### 2.1 Add a Deadline task: `deadline`
Adds a deadline task to Duke.

Format: `deadline DESCRIPTION /by DATE TIME`

- `DATE`: yyyy-mm-dd
- `TIME`: HH:mm

### 2.2 Add an Event: `Event`
Adds an event to Duke.

Format: `event DESCRIPTION /on DATE TIME `

- `DATE`: yyyy-mm-dd
- `TIME`: HH:mm

### 2.3 Add a To-Do task: `todo`
Adds a normal task, without any deadlines or timing, to Duke.

Format: `todo DESCRIPTION`

### 2.4 Delete tasks: `delete`
Deletes a currently saved task or event from Duke. 

Format: `delete INDEX`

- `INDEX` refers to the index number shown in the displayed list (under 2.7) and must be a positive integer. 

### 2.5 Mark task as done: `done`
Mark a currently saved task or event as done. 

Format: `done INDEX`

- `INDEX` refers to the index number shown in the displayed list (under 2.7) and must be a positive integer. 

### 2.6 Find tasks using a keyword: `find`
Finds all tasks and events that contain a particular keyword.

Format: `find KEYWORD`

### 2.7 List all tasks: `list`
List all tasks and events saved in Duke.

Format: `list`

### 2.8 Undo the previous command: `undo`
Undo previous commands.

Format: `undo`

- Only commands that changed the data can be undone.

### 2.9 Exit Duke: `bye`
Exit the Duke.

Format: `bye`

### 2.10 Saving the data
Duke data are saved in the hard disks automatically after any command that changes the data. There is no need to save automatically.

## 3. Command Summary
- Add a deadline task: `deadline DESCRIPTION /by DATE TIME`
- Add an event: `event DESCRIPTION /on DATE TIME`
- Add a To-Do task: `todo DESCRIPTION`
- Delete task or event: `delete INDEX`
- Mark task or event as done: `done INDEX`
- Find: `find KEYWORD`
- List tasks and events: `list`
- Undo: `undo` 
- Exit: `bye`