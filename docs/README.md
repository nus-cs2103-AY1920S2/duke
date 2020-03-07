# CS2103T iP (Jarvis) - User Guide
By: Ng Jun Guang, Jarrod

##### Table of Contents
1. [Introduction](#intro)  
2. [Quick Start](#quickstart)    
3. [Features](#features)  
    3.1. [Usage Help - `help`](#help)  
    3.2. [Add a todo item - `todo`](#todo)  
    3.3. [Add an event item - `event`](#event)  
    3.4. [Add a deadline item - `deadline`](#deadline)  
    3.5. [View all items in list - `list`](#list)  
    3.6. [Complete an item in the list - `done`](#done)  
    3.7. [Delete an item from the list - `delete`](#delete)  
    3.8. [Find Items - `find`](#find)  
    3.9. [Exit Program - `bye`](#bye)  
4. [Acknowledgements](#acknowledgements)  

## 1. Introduction <a name="intro"/>
Jarvis is a program that is adapted from Duke by Se-edu. It is primarily a task management application suitable for users
who prefer to use the **Command Line Interface** while still having the benefits of a 
**Graphical User Interface**.

## 2. Quick Start <a name="quickstart"/>
1. Ensure you have Java `11` or above installed in your Computer.
2. Double-click the file to start the program.    

## 3. Features <a name="features"/>
The program has the following in-built functionalities:

### 3.1. Usage Help - `help` <a name="help"/>
Gives a list of all functions that are possible, and their formats.
<br/>Format: `help`

![Image of help](\duke\src\main\resources\images\helpcommand.PNG)

### 3.2. Add a todo item - `todo` <a name="todo"/>
Adds a todo item to the list.
<br/>Format: `todo ITEM`

![Image of help](\duke\src\main\resources\images\todocommand.PNG)

### 3.3. Add an event item - `event` <a name="event"/>
Adds an event item with the date the event is at to the list.
<blockquote>DATE should be entered in the format <strong>yyyy-mm-dd</strong></blockquote>
<br/>Format: `event ITEM /at DATE`

![Image of help](\duke\src\main\resources\images\eventcommand.PNG)

### 3.4. Add a deadline item - `deadline` <a name="deadline"/>
Adds a deadline item with the date it is due to the list.
<blockquote>DATE should be entered in the format <strong>yyyy-mm-dd</strong></blockquote>
<br/>Format: `deadline ITEM /by DATE`

![Image of help](\duke\src\main\resources\images\deadlinecommand.PNG)

### 3.5. View all items in list - `list` <a name="list"/>
Displays, in order of addition, all the items in the current list.
<br/>Format: `list`

![Image of help](\duke\src\main\resources\images\listcommand.PNG)

### 3.6. Complete an item in the list - `done` <a name="done"/>
Sets the status of the item in the list at the given index as 'done'.
<br/>Format: `done INDEX`

![Image of help](\duke\src\main\resources\images\donecommand.PNG)

### 3.7. Delete an item from the list - `delete` <a name="delete"/>
Deletes the item in the list at the given index.
<br/>Format: `delete INDEX`

![Image of help](\duke\src\main\resources\images\deletecommand.PNG)

### 3.8. Find Items - `find` <a name="find"/>
Finds all items in the list that contains KEYWORD as its substring.
<br/>Format: `find KEYWORD`

![Image of help](\duke\src\main\resources\images\findcommand.PNG)

### 3.9. Exit Program - `bye` <a name="bye"/>
Exits the program and closes the Ui window.
<br/>Format: `bye`

## 4. Acknowledgements <a name="acknowledgements"/>
Credits to 
Code Organization adapted from seedu-ab-2.  
Referenced from: [https://github.com/se-edu/addressbook-level2/tree/master/src/seedu/addressbook](https://github.com/se-edu/addressbook-level2/tree/master/src/seedu/addressbook)

