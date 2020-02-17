# User Guide
Welcome, this is an application that is made to help you store and manage your studies.
## Table Of Content 
* Introduction
* How to use

## Introduction
This is a note taker application. The data is stored automatically when you entered a command into the application. 
It has three main features which are: 

* Todo, 
* Deadlines, 
* Events.

**Todo** feature allows for normal activities that do not requires date. On the other hand, it is compulsory for 
**deadlines** to enter a specific date in order for it to work. 
**Events** is where your notes requires a venue to be entered.

## How to use

![User Interface](https://github.com/EdmondOng/duke/blob/master/docs/Ui.png?raw=true=50x50)

Refer to the image above. There is two ways that you can enter the inputs into the application,
which is one on the left and the another on the right.


### `Todo, Deadline, Event` - Inputs command

Example of usage: 

*`todo (arguments)` : todo helloworld

    Expected outcome : 

> Got it. I've added this task:
>
> [T][x] helloworld
>
> Now you have N tasks in the list

*`deadline (arguments) /by (YYYY-MM-DD)` : deadline hello world /by 2019-06-01

    Expected outcome : 

> Got it. I've added this task:
>
> [D][x] hello world (by: Jun 01 2019)
>
> Now you have N tasks in the list

*`events (arguments) /at (arguments)` : event gjfasjfa /at hello

    Expected outcome : 

> Got it. I've added this task:
>
> [E][x] gjfasjfa (at: hello)
>
> Now you have N tasks in the list

### `Delete, Done` - Updates command

Example of usage: 

*`delete (argument 1) (argument 2) ... (argument n)` : delete 1 2 3 ... n 

    Expected outcome : 

> Noted. I've removed this task:
>
> [E][x] gjfasjfa (at: hello)
>
> Now you have N tasks in the list
>
> Noted. I've removed this task:
>
> [Di][x] hello world (by: Jun 01 2019)
>
> Now you have N tasks in the list

*`delete all`

    Expected outcome : 

> Removed all items in list

*`done (argument 1) (argument 2) ... (argument n)` : done 1 2 3 ... n 

    Expected outcome : 

> Nice! I've finish this task:
>
> [T][o] gjfasjfa (at: hello)
>

*`done all`

    Expected outcome : 

> Finished all items in list
