# Cute User Guide

## Product Screenshot
<img src="./Ui.png" width="400">

## Description
Cute is an adorable, innocent little kitten that would never hurt you! She'll eagerly help you manage all your tasks! Well, as long as she gets <i>some food</i> in return...

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

*Command Usage*:
`help`

*Expected Output*:<br>
`1. list`<br>
`2. todo <task>`<br>
`3. event <task> /at <date>`<br>
`4. deadline <task> /by <date>`<br>
`5. done <index>`<br>
`6. delete <index>`<br>
`7. find <keyword>`<br>
`8. update description <index> <new description>`<br>
`9. update time <index> <new time>`<br>
`10. bye`

### 2. Exiting The Application
In order to exit, you can click to close the window of the application.
Alternatively, you can type `bye` into the application and it will automatically close for you!

##  Task Management
### 1. Listing Tasks
Displays an indexed list of all tasks.

*Command Usage*:
`list`

*Expected Output*:<br>
`1. [D][X] Assignment (by: 20 Apr 2019 11.59pm)`<br>
`2. [T][O] Worksheet`

### 2. Marking Task As Done
Marks a specified task as done.

*Command Usage*:
`done <task index>`

*Example Usage*:
`done 3`

*Expected Output*:<br>
Mmm, Cute thinks your hardworkingness smells yummy!<br>
`3. [D][O] Assignment (by: 20 Apr 2019 11.59pm)`

### 3. Deleting A Task
Deletes a task from the task list.

*Command Usage*:
`delete <task index>`

*Example Usage:*
`delete 3`

*Expected Output*:<br>
Noted. I've eaten - uhh, I mean, removed this task:<br>
`3. [D][O] Assignment (by: 20 Apr 2019 11.59pm)`

## Adding Tasks
### 1. Adding A Todo
Adds a todo task to the list.

*Command Usage*:
`todo <task description>`

*Example Usage*:
`todo Assignment`

*Expected Output*:<br>
Wow, you add tasks faster than I eat fishes! Hmm...<br>
`1. [T][X] Assignment`

### 2. Adding An Event
Adds an event task to the list.

*Command Usage*:
`event <task description> /at <date and time>`

*Example Usage*:
`event Party 2019-06-20 1159`

*Expected Output*:<br>
Wow, you add tasks faster than I eat fishes! Hmm...<br>
`1. [E][X] Party (at: 20 Apr 2019 11.59pm)`

### 3. Adding A Deadline
Adds a deadline task to the list.

*Command Usage*:
`deadline <task description> /by <date and time>`

*Example Usage*:
`deadline Work /by 2019-06-20 1159`

*Expected Output*:<br>
Wow, you add tasks faster than I eat fishes! Hmm...<br>
`1. [D][X] Work (by: 20 Apr 2019 11.59pm)`

## Additional Functions
### 1. Searching Tasks By Keyword
Returns a list of tasks that match the keyword. The search is case-sensitive. The search also returns tasks whereby the keyword is a substring of the description.

*Command Usage*:
`find <keyword>`

*Example Usage*:
`find Book`

*Expected Output*:<br>
Cute has fished up these results for you!<br>
`1. [T][O] Book`<br>
`2. [T][O] Bookzzz`

### 2. Update Task Description
Updates the description of a task.

*Command Usage*:
`update description <task index> <new description>`

*Example Usage*:
`update description 1 Assignment Number 2`

*Expected Output*:<br>
Updating a description? Forgetful fishy, are we?<br>
`1. [T][O] Assignment Number 2`

### 2. Update Task Time
Updates the time of a task.

*Command Usage*:
`update description <task index> <new time>`

*Example Usage*:
`update time 1 Party at house`

*Expected Output*:<br>
Ooh, you updated the time, but is it time for me to eat yet?<br>
`1. [E][O] Party at house (at: 20 Apr 2019 8pm)`