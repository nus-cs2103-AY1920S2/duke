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
<a name="intro"/>
## 1. Introduction
Jarvis is a program that is adapted from Duke by Se-edu. It is primarily a task management application suitable for users
who prefer to use the **Command Line Interface** while still having the benefits of a 
**Graphical User Interface**.
<a name="quickstart"/>
## Quick Start
1. Ensure you have Java `11` or above installed in your Computer.
2. Double-click the file to start the program.  
<a name="features"/>
## Features
The program has the following in-built functionalities:
<a name="help"/>
### Usage Help - `help`
Gives a list of all functions that are possible, and their formats.
<br/>Format: `help`
<a name="todo"/>
### Add a todo item - `todo`
Adds a todo item to the list.
<br/>Format: `todo ITEM`
<a name="event"/>
### Add an event item - `event`
Adds an event item with the date the event is at to the list.
<br/>Format: `event ITEM /at DATE`
<a name="deadline"/>
### Add a deadline item - `deadline`
Adds a deadline item with the date it is due to the list.
<br/>Format: `deadline ITEM /by DATE`
<a name="list"/>
### View all items in list - `list`
Displays, in order of addition, all the items in the current list.
<br/>Format: `list`
<a name="done"/>
### Complete an item in the list - `done`
Sets the status of the item in the list at the given index as 'done'.
<br/>Format: `done INDEX`
<a name="delete"/>
### Delete an item from the list - `delete`
Deletes the item in the list at the given index.
<br/>Format: `delete INDEX`
<a name="find"/>
### Find Items - `find`
Finds all items in the list that contains KEYWORD as its substring.
<br/>Format: `find KEYWORD`
<a name="bye"/>
### Exit Program - `bye`
Exits the program and closes the Ui window.
<br/>Format: `bye`
<a name="acknowledgements"/>
## Acknowledgements
Code Organization adapted from seedu-ab-2.  
Referenced from: https://github.com/se-edu/addressbook-level2/tree/master/src/seedu/addressbook

