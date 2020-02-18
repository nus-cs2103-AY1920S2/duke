# User Guide
![](Ui.png)
## DUKE
_**HATES LEFTOVER TASKS**_

### Add Todo, Event and Deadline: `todo` `event` `deadline`

Examples:
* todo `<description>`
    * todo this
    > [T][O] this
    
* event `<description>` /at `<place>` 
    * event fly kite /at sentosa
    > [E][O] fly kite at sentosa
    
* deadline `<description>` /by `<yyyy-mm-dd format>`
    * deadline do ip /by 2020-03-01
    > [D][O]do ip by MAR 01 2020


### Mark tasks as done: `done`

Example:
* done `<index>`
    * done 1
    > [T][X] this

### Delete task: `delete`

Example: 
* delete `<index>`
    * delete 1

### List all tasks: `list`

Example: 
* list
> 1. [E][O] fly kite at sentosa
> 2. [D][O] do ip by MAR 01 2020

### Find a task: `find`

Example: 
* find `<desciption>`

    * find todo
    
    `OR`
    * find kite
    
### Archive: `archive`, `showarchived`

Example:
* archive 1
* showarchived
    
### Exit duke: `bye`

Example: 
* bye

**DUKE SAVES ALL YOUR TASKS BEFORE LEAVING**

##GET YOUR FREE COPY OF DUKE FOR ONLY $19.99!