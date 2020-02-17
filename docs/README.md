# User Guide for DingDing the Chat Bot

## 1. Introduction
DingDing the chat bot is a personal chat bot that helps you manage your everyday tasks! 

![Screenshot of DingDing](https://github.com/Denise-Ng/duke/tree/master/docs/Ui.png)


## 2. Quick Start
Download the latest version of the jar file e.g. Duke-v2.0.jar from the repository. 

### Windows OS
Simply double-click on the jar file to get started!

If not,
Open your command prompt. Then enter 
```
java -jar {location of the downloaded jar file} 
```
For example, 
```
java -jar Documents/Downloads/Duke-v2.0.jar
```


### Mac OS
Open your terminal. Then enter 
```
java -jar {location of the downloaded jar file} 
```

For example, 
```
java -jar ~/Desktop/Downloads/Duke-v2.0.jar
```


## 3. Features 


#### 3.1 `bye` - Closes Ding Ding

Closes Ding Ding the Chat Bot.

Example of usage: 
`bye`

Expected outcome:

Bot replies with a goodbye message such as: 
```
See you later alligator! 
``` 
and chat Bot closes.



#### 3.2 `date` - Shows the tasks on Date

Shows all tasks on that Date.

Example of usage: 
`date {Date}` shows the task(s) on the `Date`.

`date 2020/3/2`

Expected outcome:

Bot replies with a list of tasks on that date such as: 
```
The task(s) you have on 2020/3/2:
 [E][X] Meeting with CS2103T group (at:2 Mar 2020 12:00PM)
 [D][X] finish CS2103T tutorial (by:2 Mar 2020 11:59PM)
 [T][X] Laundry
```



#### 3.3 `deadline` - Creates a deadline task

Creates a deadline task with the relevant details and adds it into your list of tasks.

Example of usage: 
`deadline {Description} /by {Date}{Time}` creates a deadline with specified `Date` and `Time`.
`deadline {Description} /by {Date}` creates deadline with specified `Date` only.
`deadline {Description} /by {Time}` creates a deadline with today's Date specified `Time`.

`deadline finish CS2103T tutorial /by 2020/3/2 23:59`

Expected outcome:

Bot replies with a confirmation message such as: 
```
Alright! Task added:
  [D][x] finish CS2103T tutorial (at:2 Mar 2020 11:59PM)
Now have 2 task(s) in your list!
```



#### 3.4 `delete` - Deletes the task

Deletes the task corresponding to the specified number in the task list.

Example of usage: 
`delete {task number}` deletes the task with the `task number` on the task list.

`delete 1` 
 
Expected outcome:

Bot replies with a confirmation message such as: 
```
Okay! Task removed:
  [T][Y] Laundry
Now you have 2 task(s) in your list!
```



#### 3.5 `done` - Marks a task as done

Marks the task corresponding to the specified number in the task list as done.

Example of usage: 
`done {task number}` marks the task with the `task number` on the task list as done.

`done 1` 
 
Expected outcome:
```
Bot replies with a confirmation message such as: 
Nice! Task marked as done:
  [E][Y] Meeting with CS2103T group (at:2 Mar 2020 12:00PM)
```



#### 3.6 `event` - Creates an Event task

Creates an event task with the relevant details and adds it into your list of tasks.

Example of usage: 
`event {Description} /at {Date}{Time}` creates an event with specified `Date` and `Time`.
`event {Description} /at {Date}` creates an event with specified `Date` only.
`event {Description} /at {Time}` creates an event with today's Date specified `Time`.

`event Meeting with CS2103T group /at 2020/3/2 12:00`

Expected outcome:

Bot replies with a confirmation message such as: 
```
Alright! Task added:
  [E][x] Meeting with CS2103T group (at:2 Mar 2020 12:00PM)
Now have 3 task(s) in your list!
```



#### 3.7 `find` - Finds task(s) with keyword(s)

Finds all tasks(s) containing the keyword(s). 

Example of usage: 
`find {keywords}` finds all the task(s) containing the `keywords`.

`find meeting tutorial` 
 
Expected outcome:
```
Bot replies with a confirmation message such as: 
Task(s) with keyword meeting tutorial:
  [E][Y] Meeting with CS2103T group (at:2 Mar 2020 12:00PM)
  [D][x] finish CS2103T tutorial (at:2 Mar 2020 11:59PM)
```



#### 3.8 `help` - Shows the help message

Shows the help message if you forgot the commands/ usage of the commands.

Example of usage: 

`help` 
 
Expected outcome:
```

Help is here! Here is a list of keywords that I understand:
p.s. Date & Time are in <YYYY/M/D> & <HH:MM> format respectively.
<Command>   <Details>                     <Bot's Action>
bye                                       Terminates DingDing
date        <Date>                        Shows tasks on Date
deadline    <Details> /by <Date> <Time>   Creates a Deadline with details, date & time
deadline    <Details> /by <Time>          Creates a Deadline details, today's date & time
deadline    <Details> /by <Date>          Creates a Deadline details & date
event       <Details> /by <Date> <Time>   Creates an Event task with details, date & time
event       <Details> /by <Time>          Creates an Event task with details, today's date & time
event       <Details> /by <Date>          Creates an Event task with details & date
todo        <Details>                     Creates a Todo task with Details
delete      <Task Number>                 Deletes the task of task number
done        <Task Number>                 Marks the corresponding task number
find        <Keyword(s)>                  Shows all tasks with keyword(s)
help                                      Shows help message
list                                      Shows the list of tasks
```



#### 3.9 `list` - Shows your list of task(s)

Show your list of task(s).

Example of usage: 

`list` 
 
Expected outcome:
Bot replies with a confirmation message such as: 
```
Task(s) in your list:
  1. [E][Y] Meeting with CS2103T group (at:2 Mar 2020 12:00PM)
  2. [D][X] finish CS2103T tutorial (at:2 Mar 2020 11:59PM)
  3. [T][Y] Laundry
```



#### 3.10 `todo` - Creates a Todo task

Creates a todo task with the relevant details and adds it into your list of tasks.

Example of usage: 

`todo Laundry`

Expected outcome:

Bot replies with a confirmation message such as: 
```
Alright! Task added:
  [T][x] Laundry
Now have 1 task(s) in your list!
```


### 4. Summary of all the Commands

Keywords |Description| Example Usage 
------------ | -------------------- | ----------------------------
`bye` | Closes Ding Ding | `bye` 
`date` |  Shows the tasks on Date. | `date 2020/3/2` 
`deadline` | Creates a deadline task | `deadline finish CS2103T tutorial /by 2020/3/2 23:59` 
`delete` | Deletes the task | `delete 1` 
`done` | Marks a task as done | `done 1` 
`event` | Creates an Event task | `event Meeting with CS2103T group /at 2020/3/2 12:00` 
`find` | Finds task(s) with keyword(s) | `find meeting tutorial` 
`help` | Shows the help message | `help`
`list`| Shows your list of task(s) | `list`
`todo` | Creates a todo task | `todo Laundry` 

