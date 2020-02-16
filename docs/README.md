# Duke's User Guide

Welcome to my version of Duke! Duke is a help-bot aimed to provide you with a clean, functional experience of storing and checking off tasks that you wish to complete over a period of time.

---

## Features 
Duke is able to store and save your tasks, mark them as done, set times to when they must be completed amongst various other features!

### Tasks
Duke's main feature is to record, save and keep track of the tasks you have uncompleted!

### Commands 
Duke has various commands that you are able to use in order to help you through saving tasks and completing them!

### Statistics
Duke provides you with statistics on tasks that you've completed over the past few weeks!

---

## Usage

#### `bye` - Disables Duke

This command disables Duke and bids you farewell.

Example of usage: 
`bye`

Expected outcome:
`Ok see you! Hopefully I could help you in some way! :)`

#### `deadline` - Adds a deadline task

This command adds a deadline task for you in Duke's task list.

Example of usage: 
`deadline End the world /by 20/12/2012 2359`

Expected outcome:
`I've got it! Added the following task:
[D][X] End the world (by: Dec 20 2012, 11:59 PM)`

#### `delete` - Deletes a task from the list

This command removes a task from the task list, at the specified index.

Example of usage: 
`delete 2`

Expected outcome:
`Noted! I've deleted the following task: <task>`

#### `done` - Completes a task in the list

This command marks a task as "done" in the list.

Example of usage: 
`done 2`

Expected outcome:
`Great job on being productive! I've marked the following task as completed: <task>`

#### `event` - Adds an event task

This command adds an event task for you in Duke's task list.

Example of usage: 
`event Celebrate end of the world /at 20/12/2012 1800 to 20/12/2012 2359`

Expected outcome:
`I've got it! Added the following task: [E][X] Celebrate end of the world (at: Dec 20 2012, 6:00PM to Dec 20, 2012, 11:59PM)`

#### `find` - Finds a task 

This command given a search parameter, finds a task with the word inside it.

Example of usage: 
`find end of the world`

Expected outcome:
`We've found the following tasks related to your search ("end of the world"): <tasks>`

#### `list` - Lists all tasks

This command lists all the tasks you have entered.

Example of usage: 
`list`

Expected outcome:
`Here are the <number of tasks> tasks I've noted down for you: ...`

#### `liston` - Lists all tasks on a specific date

This command lists all the tasks with a deadline / event on that specific date.

Example of usage: 
`liston 20/12/2012`

Expected outcome:
`Here are the <number of tasks> tasks I've noted down for you on Dec 20 2012: ...`

#### `stats` - Produces statistics

This command lists the statistics of tasks you have completed / uncompleted that Duke has recorded down for you.

Example of usage: 
`stats`

Expected outcome:
`Current task statistics as at <current date>: ...`

#### `todo` - Describe action

This command adds a todo task to Duke's task list.

Example of usage: 
`todo End the world`

Expected outcome:
`I've got it! Added the following task: [T][X] End the world`
