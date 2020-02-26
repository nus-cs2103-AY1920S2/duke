# MyNeckbeard Bot User Guide



## Product Overview
**MyNeckbeard** is a task tracking tool in the form of an interactive chatbot with the persona of a disgusting filthy neckbeard. **MyNeckbeard** operates on a command line basis and is appealing to anyone who wishes to organise their tasks in an easy to use manner!

## Product Interface

<img src = "Ui.png" width = "400">

## Features present

### Feature 1 - `Create Tasks`

Users are able to create three types of tasks
- Todo
- Events
- Deadlines

These tasks will be recorded and stored in **MyNeckbeard**'s database

### Feature 2 - `Update Tasks`

Users are able to update the status of their task by marking their tasks as completed, and view which ones are complete **[✓]** and incomplete **[✗]**.

### Feature 3 - `Delete Tasks`

Users are able to remove tasks from **MyNeckbeard** as required.

### Feature 4 - `Find Tasks`

Users are able to search for specific task titles using a keyword. Relevant tasks will be displayed to the user.

### Feature 5 - `Read Tasks`
Users are able to view all their current tasks in **MyNeckbeard**’s database. **MyNeckbeard** will display the type of tasks present, completion progress and description.


## Commands

### `Create Tasks`

Command | Purpose | Example
------------ | ------------- | -------------
`todo` | A simple *todo* task is created | `todo (name of task)`
`event` | An *event* task is created with details of location | `event (name of task) /at (details)`
`deadline` | A *deadline* task is created with details of due date | `deadline (name of task) /by (YYYY-MM-dd)`
`list` | Displays all tasks in the database | `list`
`done` | Marks specific task as complete | `done (integer value)`
`delete` | Deletes specific task from database | `delete (integer value)`
`find` | Finds all tasks with specified keyword | `find (keyword)`
`bye` | Closes application window | `bye`
