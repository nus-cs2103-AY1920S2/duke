# Duke User Guide

## Features 

### Friendlier Syntax
Create shortcuts for commands.

### Help
Fetch the command list.

### Tagging
Tag tasks using the command tag.

## Usage

### `Alias` - Create shortcut for commands.

[alias] [command]

Example of usage: 
alias b bye

Expected outcome:
Alias added.

### `Bye` - Exits programme.

Programme closes.

Example of usage: 
bye

Expected outcome:
Programme closes.

### `Clear` - Deletes all tasks in the list.

Example of usage: 
clear

Expected outcome:
List cleared.

### `Deadline` - Creates task with a deadline.

[description] [date] [tags]

Example of usage: 
deadline iP /2020-02-19

Expected outcome:
[D] IP (by Feb 19 2020)

### `Delete` - deletes task with task number

[task number]
 
Example of usage: 
delete 1

Expected outcome:
Noted. I've removed this task

### `Done` - marks task with task number as done.

[task number]
 
Example of usage: 
done 1

Expected outcome:
Noted. I've marked this task as done.
  
### `Event` - Creates task that is an event.

[description] [date] [tags]

Example of usage: 
event CS2103T tutorial /2020-02-20

Expected outcome:
[E] CS2103T tutorial (at Feb 20 2020)    
      
### `Find` - Returns list of tasks with keyword in name.

[keyword]

Example of usage: 
keyword cs2103t

Expected outcome:
[E] CS2103T tutorial (at Feb 20 2020)               
           
### `Tag` - Returns list of tasks containing tag.

[tag]

Example of usage: 
tag cs2103T

Expected outcome:
[E] CS2103T tutorial (at Feb 20 2020)            
          
### `Todo` - Creates task.
 
 [description] [tags]
 
 Example of usage: 
 todo read notes
 
 Expected outcome:
 [T] read notes 
           