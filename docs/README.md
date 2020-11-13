# User Guide

## Introduction
Momo is your very own personal chat bot task manager! This cutie can help you to keep track of various tasks at hand
with various interactive features.
Enjoy!

## Application Interface
<img src="Ui.png" width="400">

## Features 

### 1. Add `Todo`  
Adds in a new `todo` task into the list, specifying the task description.

#### Example of usage: 
    todo complete assignment

#### Expected outcome: 
    Got it. I've added this task:
    [T][✗] complete assignment
    Now you have 1 tasks in the list.

### 2. Add `Deadline` 

#### Example of usage: 
    deadline complete assignment /by 2020-01-01
    
#### Expected outcome: 
    Got it. I've added this task:
    [T][✗] complete assignment (by: Jan 1 2020)
    Now you have 2 tasks in the list.
    
### 3. Add `Event` 
Adds in a new `event` task into the list, specifying the task description and the date of the event.

#### Example of usage: 
    event competition /at 2020-01-01
    
#### Expected outcome: 
    Got it. I've added this task:
    [T][✗] competition (at: Jan 1 2020)
    Now you have 3 tasks in the list.

### 4. Display `List`  
Displays the current `list` of all your tasks.

#### Example of usage: 
    list
        
#### Expected outcome: 
    Here are the tasks in your list:
    1. [T][✗] complete assignment
    2. [T][✗] complete assignment (by: Jan 1 2020)
    3. [T][✗] competition (at: Jan 1 2020)

### 5. Mark task(s) as `Done` 
Marks the selected tasks to be `done` in the list.

#### Example of usage 1: 
    done 1
        
#### Expected outcome 1: 
    Nice! I've marked this task as done:
     [T][✓] complete assignment
     
#### Example of usage 2: 
    done 1 2  
        
#### Expected outcome 2: 
    Nice! I've marked this task as done:
     [T][✓] complete assignment
     [T][✓] complete assignment (by: Jan 1 2020)
     
### 6. `Delete` task(s)
Removes the selected tasks from the list.

#### Example of usage 1: 
    delete 1
        
#### Expected outcome 1: 
    Nice! I've removed this task:
     [T][✓] complete assignment
     Now you have 2 tasks in the list.
     
#### Example of usage 2: 
    delete 1 2  
        
#### Expected outcome 2: 
    Nice! I've removed this task:
     [T][✓] complete assignment
     [T][✓] complete assignment (by: Jan 1 2020)
     Now you have 1 task in the list.

### 7. `Find` task(s) 
Search for specific task(s) from the task list by specifying a keyword.

#### Example of usage: 
    find assignment
        
#### Expected outcome: 
    Here are the matching tasks in your list:
    [T][✓] complete assignment
    [T][✓] complete assignment (by: Jan 1 2020)

### 8. `Exit`  
Exits the programme and automatically closes it.

#### Example of usage: 
    bye
        
#### Expected outcome: 
    Bye. Hope to see you again soon!
