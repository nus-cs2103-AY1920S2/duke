# User Guide

## Features 

### list
To list out the current list of tasks

## Usage

### `list` - Enter the command above

Doraemon will inform you the task list

Example of usage: 

`list`

Expected outcome:

```
Here are the tasks in your list: 
1.[T][v] hello
```


### todo
To add a "todo" task into the list

## Usage

### `todo something` - Enter the command above

Doraemon will inform you that the task has been added

Example of usage: 

`todo read book`

Expected outcome:

```
Got it. I've added this task:
[T][x] read book
Now you have 2 tasks in the list
```

### deadline
To add a "deadline" task into the list

## Usage

### `deadline something /date` - Enter the command above

Doraemon will inform you that the task has been added

Example of usage: 

`deadline return book /2020-02-20`

Expected outcome:

```
Got it. I've added this task:
[D][x] return book | by: Feb 20 2020
Now you have 3 tasks in the list
```


### event
To add a "event" task into the list

## Usage

### `event something /date` - Enter the command above

Doraemon will inform you that the task has been added

Example of usage: 

`event party /2020-02-21`

Expected outcome:

```
Got it. I've added this task:
[E][x] party | at: Feb 21, 2020
Now you have 4 tasks in the list
```

### delete
To remove a task from the list

## Usage

### `delete taskNumber` - Enter the command above

Doraemon will inform you that the task has been removed

Example of usage: 

`delete 1`

Expected outcome:

```
Noted. I've removed this task:
[T][x] hello
Now you have 3 tasks in the list
```

### done
To mark a task as done

## Usage

### `done taskNumber` - Enter the command above

Doraemon will inform you that the task has been marked as done

Example of usage: 

`done 1`

Expected outcome:

```
Nice! I've marked this task as done: 
[T][x] hello
```

### find
To mark a task as done

## Usage

### `find keyword` - Enter the command above

Doraemon will inform you that the task has been marked as done

Example of usage: 

`find book`

Expected outcome:

```
Here are the matching tasks in your list: 
1. [T][x] read book
2. [D][x] return book | by: Feb 20 2020
```