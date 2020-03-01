# Cute User Guide

## Product Screenshot
![Duke Screenshot](./Ui.png)

## Description
Cute is an adorable, innocent little kitten that would never hurt you! She'll help you manage all your tasks, well, as long as she gets some food in return...

## Technology
1. Java 11
2. JavaFX 11
3. Gradle

## Usage

### Starting Up Cute
Firstly, ensure you have the relevant technologies installed.
To start the application, simply double click on the .jar file to run it.
Alternatively, you may run `java -jar cute.jar` in command line.

## General Commands
### 1. Looking For Help
If you need any help, you can refer to this user guide.
Alternatively, you can type `help` into the application and it will display a list of valid commands to try!

### 2. Exiting The Application
In order to exit, you can click to close the window of the application.
Alternatively, you can type `bye` into the application and it will automatically close for you!

##  Task Management
### 1. Listing Tasks
Displays an indexed list of all tasks.

*Command Usage*:
`list`

*Expected Output*:
`1. [D][X] Assignment (by: 20 Apr 2019 11.59pm)`<br>
`2. [T][O] Worksheet`

### 2. Marking Task As Done
Marks a specified task as done.

*Command Usage*:
`done <task index>`

*Example Usage*
`done 3`

*Expected Output*:
`3. [D][O] Assignment (by: 20 Apr 2019 11.59pm)`

### 3. Deleting A Task
Deletes a task from the task list.

*Command Usage*:
`delete <task index>`

*Example Usage*
`delete 3`

*Expected Output*:
Noted. I've eaten - uhh, I mean, removed this task:
`3. [D][O] Assignment (by: 20 Apr 2019 11.59pm)`

## Adding Tasks
### 1. Adding A Todo
Adds a todo task to the list.

*Command Usage*:
`todo <task description>`

*Example Usage*
`todo Assignment`

*Expected Output*:
Wow, you add tasks faster than I eat fishes! Hmm...
`1. [T][X] Assignment`

### 2. Adding An Event
Adds an event task to the list.

*Command Usage*:
`event <task description> /at <date and time>`

*Example Usage*
`event Party 2019-06-20 1159`

*Expected Output*:
Wow, you add tasks faster than I eat fishes! Hmm...
`1. [E][X] Party (/at: 20 Apr 2019 11.59pm)`

### 3. Adding A Deadline
Adds a deadline task to the list.

*Command Usage*:
`deadline <task description> /by <date and time>`

*Example Usage*
`deadline Work /by 2019-06-20 1159`

*Expected Output*:
Wow, you add tasks faster than I eat fishes! Hmm...
`1. [D][X] Work (/by: 20 Apr 2019 11.59pm)`

## Supplemental Task Management
### 1. Searching Tasks By Keyword
Returns a list of tasks that match the keyword. The search is case-sensitive, but can have partial matching.

*Command Usage*:
`find <keyword>`

*Example Usage*
`find Book`

*Expected Output*:
`1. [T][O] Book`<br>
`2. [T][O] Bookzzz`

### 2. Update Task Description
Updates the description of a task.

*Command Usage*:
`update description <task index> <new description>`

*Example Usage*
`update description 1 Assignment Number 2`

*Expected Output*:
Updating a description? Forgetful fishy, are we?<br>
`1. [T][O] Assignment Number 2`

### 2. Update Task Time
Updates the time of a task.

*Command Usage*:
`update description <task index> <new time>`

*Example Usage*
`update time 1 Party at house`

*Expected Output*:
Ooh, you updated the time, but is it time for me to eat yet?<br>
`1. [E][O] Party at house (/at: 20 Apr 2019 8pm)`