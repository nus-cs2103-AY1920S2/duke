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

alias a/alias c/command

Example of usage: 
alias a/b c/bye

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
List cleared!

### `Deadline` - Creates task with a deadline.

deadline n/name d/date [t/tag]

Example of usage: 
deadline n/iP d/2020-02-19 t/cs2103t

Expected outcome:
[D] IP (by Feb 19 2020)

### `Delete` - deletes task at index.

delete [index]
 
Example of usage: 
delete 1

Expected outcome:
Noted. I've removed this task

### `Done` - marks task with at index as done.

done task number
 
Example of usage: 
done 1

Expected outcome:
Noted. I've marked this task as done.
  
### `Event` - Creates task that is an event.

event n/name d/date [t/tag]

Example of usage: 
event n/CS2103T tutorial d/2020-02-20 t/tutorial

Expected outcome:
[E] CS2103T tutorial (at Feb 20 2020)    
      
### `Find` - Returns list of tasks with keyword in name.

[keyword]

Example of usage: 
keyword cs2103t

Expected outcome:
Here are the tasks containing cs2103t
[E] CS2103T tutorial (at Feb 20 2020)               
           
### `Tag` - Returns list of tasks containing tag.

[tag]

Example of usage: 
tag cs2103T

Expected outcome:
Here are the tasks containing cs2103t
[E] CS2103T tutorial (at Feb 20 2020)            
          
### `Todo` - Creates todo task.
 
 todo n/name [t/tags]
 
 Example of usage: 
 todo n/read notes t/hobby
 
 Expected outcome:
 [T] read notes 
 
 ### Acknowledgements
 Software architecture and structure inspired by AddressBook 3.
           