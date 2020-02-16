# User Guide

## Features 

### Feature 1 
Todo

## Usage

### `todo` - Describe action

Create a todo task in your task list.

Example of usage: 

`todo read book`

Expected outcome:

```
I've added this task:
[T][X] read book
Now you have 1 tasks in the list.  
```
#

### Feature 2
Event

## Usage

### `event` - Describe action

Create a event task in your task list. 
The time of the event should be keyed in and included as well.

Example of usage: 

`event dance competition /at 2020-03-24`

Expected outcome:

```
I've added this task:
[E][X] dance competition (at: Mar 24 2020)
Now you have 1 tasks in the list.
```

#

### Feature 3
Deadline

## Usage

### `deadline` - Describe action

Create a deadline task in your task list. 
The time for the deadline should be keyed in and included as well.

Example of usage: 

`deadline 2103T assignment /at 2020-03-24`

Expected outcome:

```
I've added this task:
[D][X] 2103T assignment (by: Mar 24 2020)
Now you have 1 tasks in the list.
```


#

### Feature 4
Tag

## Usage

### `tag` - Describe action

Tag the tasks.

Example of usage: 

`tag 1 #chinese`

Expected outcome:

```
Added #chinese to task 1
```

#

### Feature 5
List

## Usage

### `list` - Describe action

Display the list of tasks you currently have.

Example of usage: 

`list`

Expected outcome:

```
Here are the tasks in your list:
1. [T][X] read book
    tags: #chinese
2. [E][X] dance competition (at: Mar 24 2020)
3. [D][X] 2103T assignent (by Mar 24 2020)
```

#

### Feature 6
find

## Usage

### `find` - Describe action

Search for keywords in the list

Example of usage: 

`find read`

Expected outcome:

```
Here are the matching keywords in your list:
1. [T][X] read book
    Tags: #chinese
```

If tags exist, then search by tags is available too.
`find #chinese`

Expected outcome:

```
Here are the matching tags in your list:
1. [T][X] read book
    Tags: #chinese
```

#

### Feature 7
Delete 

## Usage

### `delete` - Describe action

Remove task from task list.

Example of usage: 

`delete 1`

Expected outcome:

```
I've removed this task:
1. [T][X] read book
    Tags: #chinese
```

#

### Feature 8
done

## Usage

### `done` - Describe action

Mark the task as done.

Example of usage: 

`done 1`

Expected outcome:

```
Nice! I've marked this task as done:
1. [T][O] read book
```

#

### Feature 9

bye

## Usage

### `bye` - Describe action

Ends the program.

Example of usage: 

`bye`

Expected outcome:

```
Okie!! Goodbye.
```