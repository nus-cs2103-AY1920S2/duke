# User Guide

1. [Introduction](#introduction)
1. [Quick Start](#quick-start)
1. [Features](#features)  
    * 3.1. [Adding task](#adding-task) `todo` `deadline` `event` `recurring` 
    * 3.2. [Deleting task](#deleting-task) `delete`
    * 3.3. [Marking task as done](#marking-task-as-done) `done`
    * 3.4. [Finding task](#finding-task) `find`
    * 3.5. [Checking the list of tasks](#checking-the-list-of-tasks) `list`
    * 3.6. [Resetting the program](#resetting-the-program) `reset`
    * 3.7. [Exiting the program](#exiting-the-program) `bye`
1. [Command Summary](#command-summary)

## <a name="introduction"></a> Introduction
This product is a Personal Assistant Chatbot that helps a person keep track of various tasks.

## <a name="quick-start"></a> Quick Start
1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest [duke.jar](https://github.com/FangShaoHua94/duke/releases/tag/v0.5) here.

1. Copy the file to the folder you want to use as the home folder for your Address Book.

1. Double-click the file to start the app. The GUI should appear in a few seconds.

## <a name="features"></a> Features

**Sample**  
<br>
<img src="Ui.png">
 
### <a name="adding task"></a> Adding task `todo` `deadline` `event` `recurring`
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

### <a name="deleting-task"></a> Deleting task `delete`
Delete the indexed task from the duke. 

Format:  
`detele index`

Example:  
`delete 3`

### <a name="marking-task-as-done"></a> Marking task as done `done`
Mark the indexed task as done.  

Format:  
`delete index`

Example:  
`done 4`

Note: if the indexed task is recurring task, it will recur.

### <a name="finding-task"></a> Finding task `find`
Find the tasks with the specified keyword.  

Format:  
`find keyword`  

Example:  
`find read`

### <a name="checking-the-list-of-tasks"></a> Checking the list of tasks `list`
Check all the tasks in the program.  

Format:  
`list`  

### <a name="resetting-the-program"></a>Resetting the program `reset`
Clear all the tasks store in the program.

Format:  
`reset`

### <a name="exiting-the-program"></a> Exiting the program `bye`
Exit the program.

Format:  
`bye`

## <a name="command-summary"></a> Command Summary
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


