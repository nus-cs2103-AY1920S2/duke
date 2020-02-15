# User Guide

## Introduction
![Duke Ui](Ui.png)

Duke is a Personal Assistant Chatbot that helps
a person keep track of various things in the form of
**tasks**.
> There are 3 types of **Tasks**: 
> 1. **todo**
>   * Tasks without any date attached to it.  
> 2. **event**
>   * Tasks that need to be done before a specific date.
> 3. **deadline**
>   * Tasks that start at a specific time and ends at a specific time.

Duke can perform the following:
1. **Add** a task
2. **Delete** a task
3. **Mark** a task as completed
4. **Find** a specific task using a given keyword
5. **List** out all tasks
6. **Undo** a command

## Features 

### Add a task 
The specific steps used to add a task depends on the task type.

You can add a task to your list by using one of the following
**keywords**:
1. todo
2. event
3. deadline

## Usage

### `todo` - Add a new todo task

Adding a new todo task requires the following format:

`todo [description]`

A description is required for adding a new todo task.

Example of usage: 

`todo Pwn the Deus Group`

Expected outcome:
![Add todo task successfully](add_todo_success.png)


### `event` - Add a new Event task

Adding a new event task requires the following format:

`event [description] /at [event time in yyyy-mm-dd]`

Example of usage: 

`event Five/Nine /at 2015-05-09`

Expected outcome:
![Add event task successfully](add_event_success.png)

### `deadline` - Add a new deadline task

Adding a new deadline task requires the following format:

`deadline [description] /by [due date in yyyy-mm-dd]`

Example of usage: 

`deadline Coding Assignment /by 2020-02-02`

Expected outcome:
![Add deadline task successfully](add_deadline_success.png)


### Feature 2 
Description of feature.

## Usage

### `Keyword` - Describe action

Describe action and its outcome.

Example of usage: 

`keyword (optional arguments)`

Expected outcome:

`outcome`