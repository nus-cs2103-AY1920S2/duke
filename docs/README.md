# User Guide

This guide explains how to torture Jerry by getting him to work for you. 

## Start 

Double click on the JAR file. 

## Features 

Get Jerry to manage your tasks. 

+  ### Task Types
1. `To-do`
2. `Event`
3. `Deadline` 

You may enter a date alongside an Event or a Deadline. 

## Commands 

Press `enter` to execute the command after typing. 


+ ### **`todo`**

    **Adds a 'To-do' task into the list.** 

    *Usage*: 

    |Command|Example|
    |------|-------|
    |`todo (task)`| `todo eat`|

    *Expected outcome*:
    
    `Jerry will show you the updated task list.`


+ ### **`event`** 

    **Adds an 'Event' task into the list.** 

    *Usage*: 

    |Command|Example|
    |------|-------|
    |`event (task) / (yyyy-mm-dd)`| `event project meeting / 2020-12-01`|

    *Expected outcome*:
    
    `Jerry will show you the updated task list.`


+ ### **`deadline`**

    **Adds a 'Deadline' task into the list.** 

    *Usage*: 

    |Command|Example|
    |------|-------|
    |`deadline (task) / (yyyy-mm-dd)`| `deadline return library book / 2020-12-01`|

    *Expected outcome*:
    
    `Jerry will show you the updated task list.`
    
    
+ ### **`find`** 
    
    **Searches for task(s) with user-specified keyword.** 

    *Usage*: 

    |Command|Example|
    |------|-------|
    |`find (keyword)`| `find eat`|

    *Expected outcome*:
    
    `Jerry will show you the a list of task(s) that contains your keyword.`    
    
    
+ ### **`urgent`**

    **Generates a list of urgent task(s).** 

    *Usage*: 

    |Command|Example|
    |------|-------|
    |`urgent`| `urgent`|

    *Expected outcome*:
    
    `Jerry will show you the a list of task(s) that is/are due in the following two weeks.`      
    
    
+ #### **`done`** 

    **Marks a task as completed.**

    *Usage*: 

    |Command|Example|
    |------|-------|
    |`done (task number)`| `done 1`|

    *Expected outcome*:
    
    `Jerry will mark the appropriate task as completed (with a tick).`   
    
    
+ #### **`delete`**

    **Deletes a task from the task list.**

    *Usage*: 

    |Command|Example|
    |------|-------|
    |`delete (task number)`| `delete 1`|

    *Expected outcome*:
    
    `Jerry will delete the appropriate task.`   
    
    
+ #### **`bye`**
 
    **Exit the application.** 

    *Usage*: 

    |Command|Example|
    |------|-------|
    |`bye`| `bye`|

    *Expected outcome*:
    
    `Jerry retreats to his mouse-hole.`    
    
    