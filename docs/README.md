# User Guide

## Introduction
The duke is a task-tracker program that supports 3 kinds of tasks - Todo task, Deadline task and Event task.

In addition, the user can view all the tasks in the order they are created, or in chronological order, 
mark the selected task as done, delete selected task, find tasks that contain a specified keyword. 

## Preview
![Screenshot of the app](Ui.png)

## Supported Tasks 
### 1. Todo task
Todo task has a task description, and a completion status. 

#### Command
To add a todo task, type the following into the program. 
For example, a user would like to "borrow a book", 
the user can input the following command:
```
todo borrow a book
```
The task will be displayed as: 
```
[T][X] borrow a book
```
### 2. Deadline task
Deadline task is a enhanced todo task with a specified deadline.

#### Command
To add a deadline task, type the following into the program.
For example, a user would like to "return a book by 2nd January 2000", 
the user can input the following command:
```
deadline return a book /by 2000-01-02
```
The task will be displayed as: 
```
[D][X] return a book (by: Jan 2 2000)
```

### 3. Event task
Event task is a enhanced todo task with a specified date.

#### Command
To add a event task, type the following into the program.
For example, a user has a "project meeting on the 3rd February 2001", 
the user can input the following command:
```
event project meeting /at 2001-02-03
```
The task will be displayed as: 
```
[E][X] project meeting (at: Feb 3 2001)
```

## Features 
### 1. List tasks
The user can view all the tasks they have at the moment by typing the following command:
#### Command
```
list
```
The tasks will be displayed as: 
```
1.[T][X] borrow book
2.[D][X] return book (by: Jan 2 2000)
3.[E][X] project meeting (at: Feb 3 2001)
4.[D][X] finish CS2103T IP (by: Feb 18 2020)
```

### 2. Mark a task as done
The user can mark a task as "Done" using this command:
#### Command
For example, if they want to mark the 1st task as done:
```
done 1
```
The task will be displayed as: 
```
1.[T][O] borrow book
```

### 3. Delete a task
The user can delete a task by its index.

#### Command
```
delete 2
```
The task will be removed from the task list and not be displayed when a user types
the 'list' command.


### 4. Find a keyword
The user can search for tasks using a keyword.

#### Command
For example, the user wants to find all the tasks that contain
the word `book`. Thus, the user can input the following command:
```
find book
```
Tasks containing the keyword `book` will be displayed:
```
1.[T][O] borrow book
2.[D][O] return book (by: Jan 2 2000)
```

### 5. View tasks in chronological order
The user can view the tasks in chronological order. 
Todo tasks with no dates will be displayed at the end of the list. 

#### Command
For example, the user view the tasks in chronological order by typing the following command: 
```
sort
```
The tasks will be displayed as: 
```
1.[D][O] return book (by: Jan 2 2000)
2.[E][O] project meeting (at: Feb 3 2001)
3.[D][X] finish CS2103T IP (by: Feb 18 2020)
4.[T][O] borrow book
```
