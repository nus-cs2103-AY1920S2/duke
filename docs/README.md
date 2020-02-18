# Duke User Guide

Table of Content
1. [Introduction](#introduction)
2. [Features](#features)
3. [Adding a Task](#adding-a-task)
    
    3.1 [Todo](#todo---adding-a-todo-task)

    3.2 [Event](#event---adding-an-event-task)

    3.3 [Deadline](#deadline---adding-an-deadline-task)
4. [List](#list-all-tasks)
5. [Done](#mark-task-as-done)
6. [Find](#find-a-task)
7. [Delete](#delete-a-task)
8. [Bye](#exit-the-program)

# Introduction

![Duke](images/Ui.png)

Duke is a task handler that **keeps all your tasks in a list**. This application is
suitable for anyone who wants to keep track of a huge amount of tasks. 

Duke is an AI in which you are able to interact with. Treat him as a helper whom you
can trust. Give him a command and he will execute it for you. Once he is done, 
he will inform you.

## Features 

In this section, it will show all the available commands that you can key in 
to Duke.

## Adding a task  

The add command allows you to add new tasks to the list. 

There are three things you can add :
Todo - Things that you have to do

Event - Things to you should be doing at a given date and time

Deadline - Task that has to be completed by a given date and time

The list will then be stored in an internal txt file.

However, you should not be adding a duplicate task in the list as Duke will
detect it and print out an error message: 

```
OOPS!!! There is a same task already added into the list
or there is a clash of timing with one of the tasks in your list!
```

## `todo` - Adding a Todo Task

To add a todo task, you have to type two arguments. The first is to type **todo**, 
the second is to type **description of task**

Example of usage:
 
`todo drink water`

Expected outcome:

![Add Todo Task](images/todo.png)

```
Got it. I've added this task:
 [T][N] drink water
 Now you have 1 tasks in the list.
```

## `event` - Adding an Event Task

To add a event task, you have to type as follows : 

event **[event description]** /at [Date yyyy/mm/dd] [Time in 24 hour format]

Example of usage:
 
`event hackathon /at 2020-03-18 0900`

Expected outcome:

![Add Event Task](images/event.png)

```
Got it. I've added this task:8 0900
[E][N] hackathon (at: Mar 18 2020 9:00am)
Now you have 1 tasks in the list.
```

## `deadline` - Adding an Deadline Task

To add a deadline task, you have to type as follows : 

deadline **[deadline description]** /by [Date yyyy/mm/dd] [Time in 24 hour format]

Example of usage:

`deadline submit iP /by 2020-02-19 2359`

Expected outcome:

![Add Deadline Task](images/deadline.png)

```
Got it. I've added this task:
[D][N] submit iP (by: Feb 19 2020 11:59pm)
Now you have 1 tasks in the list.
```

# List all tasks  

List down all the tasks that is added by you.

## `list` - list down all tasks

Duke will print out all the tasks in your list. Simply type `list`. 

Example of usage:

`list`

Expected outcome:
 
![List all tasks](images/list.png)

```
1.[T][N] drink water
2.[E][N] hackathon (at: Mar 18 2020 9:00am)
3.[D][N] submit iP (by: Feb 19 2020 11:59pm)
```

However if there are no tasks left:

![No tasks left in list](images/empty.png)
## Mark task as done  

Whenever you completed a task, you can command Duke to mark your task as done.

### `done` - Set task as done

Duke will change your task status as done. 
[N] - Your task is not done
[Y] - Your Task is done 

Example of usage:

`done 1`

*Take note: The second argument should be the position of the task in the list. 
Let's say you want the first item in your list to be done, you have to type 'done 1'
instead of typing the name of your task.*

Expected outcome:

![Task is Done](images/done.png)
  
```
Nice! I've marked this as done:
[T][Y] drink water

```

And if you use the `list` command again:

![list Done task](images/listdone.png)

```
1.[T][Y] drink water
2.[E][N] hackathon (at: Mar 18 2020 9:00am)
3.[D][N] submit iP (by: Feb 19 2020 11:59pm)
```

You can see that the status of your first task is changed from N to Y.

# Find a task  

You can find a specific task in the list with the help of Duke.

## `find` - find task in a list

To `find` the task that you want, you have to enter the description of your task and 
Duke will find all tasks that are related to the keyword that you have entered.

Example of usage:

`find hackathon`

Expected outcome:

![Find task](images/find.png)
 
```
Here are the matching tasks in your list:
1.[E][N] hackathon (at: Mar 18 2020 9:00am)
```

If Duke can't find the task that you have given :

![Duke can't find a task](images/nofind.png)

# Delete a task  

Removing a task from your list

## `delete` - deleting your task

The `delete` command will make Duke delete a specific task at a given index from you 
Example of usage:

`delete 1`

Expected outcome:
 
Before Delete:

![Before Delete](images/listdone.png)

```
1.[T][Y] drink water
2.[E][N] hackathon (at: Mar 18 2020 9:00am)
3.[D][N] submit iP (by: Feb 19 2020 11:59pm)

```

During Delete:
 
You will receive the message shown below

![Task is Done](images/delete.png)

After Delete:

![Task is Done](images/listafter.png)

```
1.[E][N] hackathon (at: Mar 18 2020 9:00am)
2.[D][N] submit iP (by: Feb 19 2020 11:59pm)

```

## Exit the program  

Type the `bye` command to exit the program 

Example of usage:

`bye`

Expected outcome:

The program will be closed immediately.