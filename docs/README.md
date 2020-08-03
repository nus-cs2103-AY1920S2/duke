# User Guide

Duke is a simple application that helps you keep track of your tasks. :D
## Features 
This application handles 4 types of tasks:

| Type | Description |
| --- | --- |
| [Todo](#todo---adds-a-todo-task) | Any regular task without time constraints |
| [Deadline](#deadline---adds-a-deadline-task) | A task to be done by a certain date / time |
| [Event](#event---adds-an-event) | A task that happens at a certain date / time |
| [FixedDuration](#fixed---adds-a-fixed-duration-task) | A task to be completed within a fixed duration |

## Usage

![Ui of duke](./Ui.png)

Interact with duke by typing commands into the text box!

Press **Enter** or Click the '**Send**' button to run each command.





## Commands
Overview

- [todo](#todo---adds-a-todo-task)
- [deadline](#deadline---adds-a-deadline-task)
- [event](#event---adds-an-event)
- [fixed](#fixed---adds-a-fixed-duration-task)
- [list](#list---displays-all-tasks)
- [find](#find---searches-for-tasks-that-contains-a-keyword)
- [done](#done---marks-a-task-as-done)
- [delete](#delete---deletes-a-task)
- [bye](#bye---saves-the-data-and-closes-the-application)


#### `todo` - adds a todo task 
Format
```
todo [description]
```
Example of usage: 
```
todo clean storeroom
```
Outcome: the following message will be shown
```
_________________________
Got it, I've added this task:
[T][x] clean storeroom
Now you have 1 task(s) in the list.
_________________________
```

#### `deadline` - adds a deadline task 
Format
```
deadline [description] /by [when]
```
Example of usage: 
```
deadline submit assignment /by today 10pm
```
Outcome: the following message will be shown
```
_________________________
Got it, I've added this task:
[D][x] submit assignment (by: today 10pm)
Now you have 2 task(s) in the list.
_________________________
```

#### `event` - adds an event 
Format
```
event [description] /at [when]
```
Example of usage: 
```
event dinner with friends /at Sunday 
```
Outcome: the following message will be shown
```
_________________________
Got it, I've added this task:
[E][x] dinner with friends (at: Sunday)
Now you have 3 task(s) in the list.
_________________________
```
#### `fixed` - adds a fixed duration task
Format
```
fixed [description] /duration [how long]
```
Example of usage: 
```
fixed piano practice /duration 1.5 hours
```
Outcome: the following message will be shown
```
_________________________
Got it, I've added this task:
[F][x] piano practice (duration: 1.5 hours)
Now you have 4 task(s) in the list.
_________________________
```

#### `list` - displays all tasks
Usage:
```
list
```
Outcome: the tasks will be shown
```
_________________________
1: [T][x] clean storeroom
2: [D][x] submit assignment (by: today 10pm)
3: [E][x] dinner with friends (at: Sunday)
4: [F][x] piano practice (duration: 1.5 hours)
_________________________
```
 
#### `find` - searches for tasks that contains a keyword
Format
```
find [keyword]
```
Example of usage: 
```
find dinner
```
Outcome: tasks containing the keyword will be shown
```
_________________________
Here are the matching tasks in your list:
    1: [E][x] dinner with friends (at: Sunday)
_________________________
```
#### `done` - marks a task as done
Format
```
done [task index]
```
Example of usage: 
```
done 1
```
Outcome: specified task is marked as done
```
_________________________
Awesome! I've marked this task as done:
[T][v] clean storeroom
_________________________
```
#### `delete` - deletes a task
Format
```
delete [task index]
```
Example of usage: 
```
delete 3
```
Outcome: specified task is deleted
```
_________________________
Noted, I've removed this task:
[E][x] dinner with friends (at: Sunday)
Now you have 3 task(s) in the list. 
_________________________
```
#### `bye` - saves the data and closes the application
Usage:
```
bye
```
Outcome: the application will automatically close after a short delay.
```
_________________________
Goodbye! Hope to hear from you soon :)
_________________________
```
