# User Guide

## Features List

* Create and add different tasks into a list
  * Todo
  * Deadline
  * Event
* Delete the different tasks from the list
  * Single task at a time
  * All completed tasks
* Check tasks as done on the list
* Display the list 
  * All tasks
  * Specified date
* Find tasks by keywords

### Feature 1 
Create and add todo into a list

#### Usage
To record a task without a deadline

#### Keyword
Create a todo and add into the list  
`todo (name of todo)`

Example of usage: 

todo bake cake for mum

Expected outcome:

Saving now....
    [T][x] bake cake for mum
    Aiyo you still have 2 task(s), what you doing sia

### Feature 2 
Create and add deadline into a list

#### Usage
To record a task with a deadline

#### Keyword
Create a deadline and add into the list. Date of deadline is required.  
`deadline (name of deadline) /by (date in format yyyy/MM/dd)`

Example of usage: 

deadline bake cake for mum /by 2020/12/11

Expected outcome:

Saving now....
    [D][x] bake cake for mum (by: 11 Dec 2020)
    Aiyo you still have 2 task(s), what you doing sia

### Feature 3 
Create and add event into a list

#### Usage
To record an event

#### Keyword 
Create an event and add into the list. Date and time of event is required.  
`event (name of event) /at (date in format yyyy/MM/dd HHmm)`

Example of usage: 

event bake cake for mum /at 2020/12/11 1400

Expected outcome:

Saving now....
    [E][x] bake cake for mum (at: 11 Dec 2020 02:00)
    Aiyo you still have 2 task(s), what you doing sia

### Feature 4 
Delete 1 task at a time

#### Usage
Used to remove a task that is no longer needed

#### Keyword 
`Delete (task number)` 

Example of usage: 

delete 1

Expected outcome:

Okcan. I will remove this task: 
    [T][x] bake cake for mum

### Feature 5 
Delete all completed tasks

#### Usage
Remove all tasks at once for convenience

#### Keyword
`delete complete`

Example of usage: 

delete complete

Expected outcome:

I have deleted all the completed tasks as shown:
   1. [T][✓] cake
   2. [T][✓] swim

### Feature 6
Check tasks as done on the list

#### Usage
Mark a task as done

#### Keyword 
`done (task number)`

Example of usage: 

done 1

Expected outcome:

Okcan, I mark this task as done:
   [T][✓] swim

### Feature 7
Display all tasks from list

#### Usage


#### Keyword 
`list`

Example of usage: 

list

Expected outcome:

I told you save liao loh........
   1.[T][✓] swim

### Feature 8
Display tasks on a specified date

#### Usage
Easily access all the tasks on the same day

#### Keyword 
`list (date in yyyy/MM/dd)`

Example of usage: 

list 2020/12/11

Expected outcome:

This are all the tasks with that date
   1.[D][✓] swim (by: 11 Dec 2020)

### Feature 9
Find tasks with specified keyword

#### Usage
Able to search tasks through keyword. Can be just part of the word.

#### Keyword 
`find (keyword)`

Example of usage: 

find sw

Expected outcome:

Here are the tasks with the keyword
   1.[D][✓] swim (by: 11 Dec 2020)