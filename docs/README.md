# Introduction

A brief User Guide for "My Little Tracker".
The features of the tracker is listed below!

## Quick Start

Ensure you have Java 11 or above installed in your Computer.

Download the latest duke.jar here! (https://github.com/joshlay96/duke/releases/download/v0.2/Duke-0.2.jar).

Copy the file to the folder you want to use as the home folder for the tracker!

Double-click the file to start the app. The GUI should appear in a few seconds.

![](https://raw.githubusercontent.com/joshlay96/duke/blob/master/docs/Ui.png)

## Features 

1. Setting Tasks
    1. Set To-Do Tasks
    2. Set Deadlines
    3. Set Events
   
2. Editing Set Tasks
    1. Delete To-do/Deadlines/Events
    2. Set To-do/Deadlines/Events to done
    3. Shows Tasks as a list

## Setting Tasks

### Set To-Do Tasks
Allows a user to set a task *without* a specific deadline.
The user can input multiple To-Do tasks which will be marked undone.
After the user has completed the task, he can set the task to be done
and delete it. 

## Usage
![](https://raw.githubusercontent.com/joshlay96/duke/blob/master/docs/Todo.png)

### `To-Do` - Creates a new To-Do task
After adding the To-Do Task, you will see your To-Do task displayed.
Look below for the Example usage and Expected outcome. 

Example of usage: 

`todo run`

Expected outcome:


    ---------------------------------
    Got it. I have added this task:
    [T][x] run
    Now you have 1 task in the list.
    ---------------------------------

#### Legend  for Todo Identifiers

`[T]` is an identifier to represent To-Do tasks. 

`[x]` is an identifier to represent undone tasks.


### Set Deadlines
Allows a user to set a task *with* a specific Deadline.

The user can input multiple deadlines which will be marked undone.

After the user has completed the task, he can set the task to be done
and delete it. 

## Usage
![](https://raw.githubusercontent.com/joshlay96/duke/blob/master/docs/Deadline.png)

### `Deadline` - To add a new Deadline
After adding the Deadline, you will see your Deadline displayed.
Look below for the Example usage and Expected outcome. 

Example of usage: 

There are multiple ways deadlines can be formatted. 

1. Adding a predefined date and time

    1. `deadline (Give Speech) /by 18/04/2019 1800`

2. Adding a predefined date

    1. `deadline (Finish Tutorial) /by 18/04/2019`

3. Replacing a predefined date with a specific day of the week with time
    1. `deadline (Complete 2103 Assignment) /by Monday 1800`

4. Replacing a predefined date with a specific day of the week without time
    1. `deadline (Go Running) /by Monday` 


Expected outcome:

Depending on which format is used to set a Deadline, you will
get the following formats. 

Refer to `Example of Usage` to know which type of outcome will be produced

1. If a predefined date and time is used
<pre><code>
   ---------------------------------------------
   Got it. I have added this task:
   [D][x] Give Speech (by: Apr 18 2019 18:00)
   Now you have 1 task in the list.
   ---------------------------------------------- 
</code></pre>    
2 . If only a predefined date is used 
(The time is preset to 00:00 if no time is added)
   
   
    ---------------------------------------------
    Got it. I have added this task:
    [D][x] Finish Tutorial (by: Apr 18 2019 00:00)
    Now you have 1 task in the list.
    ----------------------------------------------  
  
   
3 . If a predefined day of the week and time is added.

    --------------------------------------------------------
    Got it. I have added this task:
    [D][x] Complete 2103 Assignment (by: Feb 17 2020 18:00)
    Now you have 1 task in the list.
    -------------------------------------------------------- 
    
4 . If only predefined day of the week is added
(The time is preset to 00:00 if no time is added)

    ---------------------------------------------
    Got it. I have added this task:
    [D][x] Go Running (by: Apr 18 2019 00:00)
    Now you have 1 task in the list.
    ----------------------------------------------  
    
    
#### Legend for Deadline Identifiers  

`[D]` is an identifier to represent Deadline tasks. 

`[x]` is an identifier to represent undone tasks.



### Set Events
Allows a user to set a task *with* a specific Event.

The user can input multiple Events which will be marked undone.

After the user has completed the task, he can set the task to be done
and delete it. 

## Usage
![](https://raw.githubusercontent.com/joshlay96/duke/blob/master/docs/Event.png)

### `Event` - To add a new Event
After adding the Event, you will see your Event displayed.
Look below for the Example usage and Expected outcome. 

Example of usage: 

There are multiple ways Event can be formatted. 

1. Adding a predefined date and time

    1. `event (Give Speech) /at 18/04/2019 1800`

2. Adding a predefined date

    1. `event (Finish Tutorial) /at 18/04/2019`

3. Replacing a predefined date with a specific day of the week with time
    1. `event (Complete 2103 Assignment) /at Monday 1800`

4. Replacing a predefined date with a specific day of the week without time
    1. `event (Go Running) /at Monday` 


Expected outcome:

Depending on which format is used to set a Event, you will
get the following formats. 

Refer to `Example of Usage` to know which type of outcome will be produced

1. If a predefined date and time is used
<pre><code>
    ------------------------------------------
    Got it. I have added this task:
    [E][x] Give Speech (by: Apr 18 2019 18:00)
    Now you have 1 task in the list.
    ------------------------------------------
</code></pre>    
2 . If only a predefined date is used 
(The time is preset to 00:00 if no time is added)
   
   
    ---------------------------------------------
    Got it. I have added this task:
    [E][x] Finish Tutorial (by: Apr 18 2019 00:00)
    Now you have 1 task in the list.
    ----------------------------------------------  
  
   
3 . If a predefined day of the week and time is added.

    --------------------------------------------------------
    Got it. I have added this task:
    [E][x] Complete 2103 Assignment (by: Feb 17 2020 18:00)
    Now you have 1 task in the list.
    -------------------------------------------------------- 
    
4 . If only predefined day of the week is added
(The time is preset to 00:00 if no time is added)

    ---------------------------------------------
    Got it. I have added this task:
    [E][x] Go Running (by: Apr 18 2019 00:00)
    Now you have 1 task in the list.
    ----------------------------------------------  
    
    
#### Legend for Event Identifiers  

`[E]` is an identifier to represent Event tasks. 

`[x]` is an identifier to represent undone tasks.

### Editing Set Tasks

#### Delete To-Do/Deadlines/Events

Allows a user to delete various kinds of tasks

## Usage
![](https://raw.githubusercontent.com/joshlay96/duke/blob/master/docs/Delete2.png)

### `delete` - Deletes a task
Deletes a task, according to its position in the list.

Example of usage: 

`delete 1`

Expected outcome:

    The Deleted task is (Task Name)
    ---------------------------------
    Got it. I have removed this task:
    [T][x] run
    Now you have 1 task in the list.
    ---------------------------------
    
    
#### Set To-Do/Deadlines Events to done

Allows a user to set a task to be done

## Usage
![](https://raw.githubusercontent.com/joshlay96/duke/blob/master/docs/Done.png)

### `done` - Marks a task as done
Marks a task as done, according to its position in the list.

Example of usage: 

`done 1`

Expected outcome:

    ---------------------------------
    Nice I have marked this task as done
    [T][✔] run
    ---------------------------------
    
#### Shows Tasks as a List


## Usage
![](https://raw.githubusercontent.com/joshlay96/duke/blob/master/docs/List.png)

### `list` - Shows the list
Shows the user the remaining tasks in as a list

Example of usage: 

`list`

Expected outcome:

    ---------------------------------
    [T][x] sleep
    [T][x] Exercise
    ---------------------------------
