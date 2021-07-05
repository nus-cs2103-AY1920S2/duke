
# Kuri - User Guide
By: `Wang Qin` Since: `Feb 2019`


* [1. Introduction](#introduction)
* [2. Quick Start](#quick-start)
* [3. Features](#features)
    + [3.1. Adding a task:](#addtask) `add`
    + [3.2. Listing all tasks:](#list) `list`
    + [3.3. Finding a task by keywords:](#find) `find`
    + [3.4. Deleting a task:](#delete) `delete`
    + [3.5. Delete all tasks in the Kuri task list](#clear) `clear`
    + [3.6. Complete a task:](#complete) `done`
    + [3.7. Clear the screen:](#clear) `clear`
    + [3.8. List all possible functions:](#help) `help`
    + [3.9. Save task list to Json:](#save_json) `save_json`
    + [3.10. Exiting the program:](#exit) `bye`
* [4. FAQ](#faq)
* [5. Command Summary](#command-summary)


<a name="introduction"></a>


##  1. Introduction
<p align="center">
  <img src="src/main/resources/images/Screenshot 2020-03-01 at 9.33.27 PM.png">
</p>


Kuri is an app with both graphic user interface and commandline user interface, for those who prefer to use a desktop app to keep track of their to-do lists. More importantly, Duke is **optimized for those who prefer to work with a Command Line Interface (CLI)**. 
Jump to [_Section 2, "Quick Start"_](#quick-start) to get started.


<a name="quick-start"></a>

## 2. Quick Start

*   1. Ensure that `Java 11` or above is installed in your Computer.
*   2. Download the latest `kuri.jar` [_here_](https://github.com/JosephLimWeiJie/duke/releases/download/v0.2.0/duke.jar).
*   3. Copy the file to the folder you want to use as the home folder for your Duke.
*   4. Use the `java -jar + absolute file location` command to run the app.  It should appear in a few seconds. For example, `java -jar /Users/wangqin/Documents/year2/cs2113T/ip/build/libs/kuri-v1.1.jar`


*   5. Choose the platform you desired for Kuri, graphical user interface (GUI) or command line user interface (CLI). 
        Type `1` for GUI and `2` for CLI. 
<p align="center">
  <img src="src/main/resources/images/Screenshot 2020-03-01 at 9.33.41 PM.png">
</p>
*   6. For CLI, type in a command and press `ENTER` to execute it. For e.g. typing `list` and pressing enter will list all your current tasks.
*   7. Some other commands you can try:
    *  `todo return book`: adds a todo task to return a book
    *   `deadline Finish a movie /by 2019-12-01T10:00`: adds a deadline task to finish a movie by 1 Dec 2019 at 1000 hrs.
    *   `event Midnight Party /at Marina Bay Sands`: adds an event task to attend a midnight party at Marina Bay Sands
    *   `done 1`: Mark the first task as done shown in the current list.
    *   `bye`: Exits the app
*   7. Refer to [_Section 3, "Features"_](#features) for more details of each command.

For GUI, simply type in the command and click `Enter` button. 
<p align="center">
  <img src="src/main/resources/images/Screenshot 2020-03-01 at 9.33.55 PM.png">
</p>

<p align="center">
  <img src="src/main/resources/images/Screenshot 2020-03-01 at 9.34.00 PM.png">
</p>

PS: 
   * Since most of the GUI functions and CLI functions are very similar, the following feature list are mainly focused on CLI, but also applied for GUI.  

   * The app is optimized to run in full-screen-size commandline window.

<a name="features"></a>


## 3. Features


<a name="addtask"></a>

### 3.1 Adding a task: `add`

Adds a task into Duke.


* **Format**: 
    * `todo TASK_DESCRIPTION`
    * `deadline TASK_DESCRIPTION /by DATE_TIME`
    * `event TASK_DESCRIPTION /on START_DATE_TIME to END_DATE_TIME`

* **Examples**:
    * `todo return book`
    * `deadline Thesis submission /by 2020-01-12T23:59`
    * `event Wedding Ceremony /on 2020-01-12T11:00 to 2020-01-12T12:00`

<a name="list"></a>

### 3.2. Listing all tasks: `list`

Shows a list of all the tasks in Duke.

**Format**: `list`


<a name="find"></a>

### 3.3 Finding a task: `find`

Finds tasks that contain a given keyword.

**Format** : `find KEYWORD`

**Example**: `find book`

<a name="delete"></a>

### 3.4 Deleting a task: `delete`
Deletes a specified task from Duke.

**Format**: `delete INDEX`

```javascript
    * Deletes the task at the specified INDEX.
    * The index refers to the index number shown on the displayed task list.
    * The index must be a positive number 1,2,3,...
```

<a name="filter"></a>

### 3.5 Delete all tasks in the Kuri task list: `clear`
Filters tasks based on a given DATE.

**Format**: `clear`

**Example**: `clear`

<a name="complete"></a>

### 3.6 Complete a task: `done`

Marks a task as completed at the specified INDEX.

**Format**: `done INDEX`

```javascript
* The index refers to the index number shown on the displayed task list.
* The index must be a positive number 1,2,3,..
```
<a name="exit"></a>

### 3.7 Clear the screen: `clr`

Clear the screen.

**Format**: `clr`


<a name="exit"></a>

### 3.8. List all possible functions: `help`

List all help functions, their usages and their examples.
An interesting fact is not only `help` can make `Kuri` list all possible functions, but also all unrecognized commands. 

**Format**: `help`


<a name="exit"></a>

### 3.9. Save task list to Json: `save_json`

Save the current task list in `Kuri` system  to a Json file. 
It is designed similar as save functions in other editors to avoid unexpected siturations, such as running  out battery, program shut down, or even computer shut down.

**Format**: `save_json`

<a name="exit"></a>

### 3.10 Exiting the program: `bye`

Exits the program.

**Format**: `bye`

### 3.11 Warnings: 

Shown as "Alert!"

<p align="center">
  <img src="src/main/resources/images/Screenshot 2020-03-01 at 9.45.47 PM.png">
</p>

**Format**: `bye`

<a name="faq"></a>

## 4. FAQ

*Q1:* Why the jar file is much larger (100 mega bytes) compared to others?

*A:* Due to the mass use of external libraries, the `Kuri.jar` contains not only the executable program, but also most of the 
    libraries as well as output file (.json). So that, users do not need to copy the data file (.json) when he wants to use Kuri on another
    computer. Only one `Kuri.jar` is needed. 
    
*Q2:* Why there is a text file and also a Json file in the output directory?

*A:* The program only loads data from .json file but it saves to both .json and .txt file. The text file is purely for user to read and the json is mainly used to load task list


<a name="command-summary"></a>

## 5. Command Summary
* Add 
    * Format: 
        * `todo TASK_DESCRIPTION`
        * `deadline TASK_DESCRIPTION /by DATE_TIME`
        * `event TASK_DESCRIPTION /on START_DATE_TIME to END_DATE_TIME`
    * Examples:
        * `todo return book`
        * `deadline Thesis submission /by 2020-01-12T23:59`
        * `event Wedding Ceremony /on 2020-01-12T11:00 to 2020-01-12T12:00`
* List: `list`
* Find: `find KEYWORD`
    * e.g. `find book`
* Delete: `delete INDEX`
    * e.g. `delete 2`
* Complete: `done INDEX`
    * e.g. `done 1`
* Clear/Delete all tasks: `clear`
* Clear the screen: `clr`
* Help: `help`
* Save: `save_json`
* Exit: `bye`

