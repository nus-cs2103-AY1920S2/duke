# User Guide

1. [Introduction](#Introduction)
1. [Quick Start](#Quick Start)
1. [Features](#Features)  
    * 3.1. [Adding task](#Adding task) `todo` `deadline` `event` `recurring` 
    * 3.2. [Deleting task](#Deleting task) `delete`
    * 3.3. [Marking task as done](#Marking task as done) `done`
    * 3.4. [Finding task](#Finding task) `find`
    * 3.5. [Checking the list of tasks](#Checking the list of tasks) `list`
    * 3.6. [Resetting the program](#Resetting the program) `reset`
    * 3.7. [Exiting the program](#Exiting the program) `bye`
1. [Command Summary](#Command Summary)

## Introduction
This product is a Personal Assistant Chatbot that helps a person keep track of various tasks.

## Quick Start
1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `duke.jar` here.

1. Copy the file to the folder you want to use as the home folder for your Address Book.

1. Double-click the file to start the app. The GUI should appear in a few seconds.

## Features 
### Adding task `todo` `deadline` `event` `recurring`
Add a task into the duke.  

Format:  
`todo description`  
`deadline description /by yyyy-MM-dd`  
`event description /at yyyy-MM-dd`  
`recurring description /at yyyy-MM-dd`

Example:  
`todo read book`    
`deadline homework /by 2020-04-01`  
`event lecture /at 2020-05-01`  
`recurring jogging /at 2020-04-01`

Note: recurring task recurs every week.

### Deleting task `delete`
Delete the indexed task from the duke. 

Format:  
`detele index`

Example:  
`delete 3`

### Marking task as done `done`
Mark the indexed task as done.  

Format:  
`delete index`

Example:  
`done 4`

Note: if the indexed task is recurring task, it will recur.

### Finding task `find`
Find the tasks with the specified keyword.  

Format:  
`find keyword`  

Example:  
`find read`

### Checking the list of tasks `list`
Check all the tasks in the program.  

Format:  
`list`  

### Resetting the program `reset`
Clear all the tasks store in the program.

Format:  
`reset`

### Exiting the program `bye`
Exit the program.

Format:  
`bye`

## Command Summary
* Add   
`todo description`  
`deadline description /by yyyy-MM-dd`  
`event description /at yyyy-MM-dd`  
`recurring description /at yyyy-MM-dd`  
  * Example:  
    `todo read book`    
    `deadline homework /by 2020-04-01`  
    `event lecture /at 2020-05-01`  
    `recurring jogging /at 2020-04-01`  
    
* Delete `detele index`
    * Example: `delete 3`

* Done `delete index`
    * Example:`done 4`

* Find `find keyword`  
    * Example:`find read`

* List `list`  

* Reset `reset`

* Exit `bye` 


