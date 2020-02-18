# User Guide

The official guide to using Dude to his full potential!

## Table of Contents
--------------------

1. [Screenshot](#screenshot)
2. [Installation](#installation)
3. [Quick Start](#quick-start)
4. [Command Guide](#command-guide)
    1. [Help](#help)
    2. [Bye](#bye)
    3. [List](#list)
    4. [Todo](#todo)
    5. [Deadline](#deadline)
    6. [Event](#event)
    7. [Done](#done)
    8. [Delete](#delete)
    9. [Find](#find)
    10. [Check](#check)
    11. [Today](#today)
5. [Dates In Dude](#dates-in-dude)
6. [Storing your tasks](#storing-your-tasks)
7. [Acknowledgements](#acknowledgements)


Screenshot
==========
![](Ui.png)

Installation
============
Make sure you have [java](https://www.oracle.com/java/technologies/javase-downloads.html) installed on your computer.

Head over to the [releases](https://github.com/CornCobs/duke/releases) page and download the latest version of Dude!

Quick Start
===========
To start Dude, double click the jar file. Alternatively, on the command prompt / terminal, navigate to the directory where the jar file is and run the following:

```
> java -jar dude-0.2.2.jar
```
After Dude has started up, enter "help" to see the available commands, and enter "bye" to exit. That's all you need to get started!

Command Guide 
=============
### **4.1 Help**
Dude lends you a helping hand! The help command can give you more information of how to use Dude, what kinds of input Dude understands and what the various commands do.

If no arguments are given, Dude tells you the format of all of the commands he understands.

If the name of a command is specified, Dude gives more information about that command - the format, and what it does.

If you ask for `help -date`, Dude explains the types of dates he can understand.

**Format**
```
help [command | -date]
```

**Example Usage**

![](Help.png)

### **Bye**
---
Closes Dude. Before closing, Dude makes sure to save all of the tasks you have currently in ./data so when you start him up again, it's as if you never left!

### Format
```
bye
```

4.3 List
----
Shows all the tasks you currently have, and their completion status - unless you don't have any, of course!

### Format
```
list
```

### Example Usage
![](List.png)

Todo
----

Deadline
--------

Event
-----

### `Keyword` - Describe action

Describe action and its outcome.

Example of usage: 

`keyword (optional arguments)`

Expected outcome:

`outcome`
