
# User Guide

## Features 

### Duke is a platform to  compile and organize your tasks.
Keeping track of todos, event and deadlines that you add. Duke can show a list of all the tasks that you need to do and provide useful  commands to mark them as done, delete or find tasks.

### Duke saves and loads your tasks
You can always come back later to find your last session automatically loaded without having to type additional commands. Tasks are also stored in plaintext for easy manipulation.

## Usage

### `todo <desc>` - Add a todo item

Adds a todo item and gives it the description \<desc> , then adds it to the list of tasks.

 A succesful addition will show a message indicating the task just added and the number of items in the list after adding it.

Example of usage: 

`todo find my dog`

Expected outcome:
```
---------------------------------------
Got it. I've added this task:
	[T][✘] find my dog
Now you have n task(s) in the list.
---------------------------------------
```

### `deadline <desc> /by <dueDate>` - Add a deadline item

Adds a deadline item and gives it the description \<desc> then and  a due date of \<dueDate>, then adds it to the list of tasks. 

A succesful addition will show a message indicating the task just added, its dueDate and the number of items in the list after adding it.

\<dueDate > must be formatted as YYYY-MM-DD.

Example of usage: 

`deadline adopt a cat /by 2021-11-11`

Expected outcome:
```
---------------------------------------
Got it. I've added this task:
	[D][✘] adopt a cat (by: Nov 11 2021)
Now you have n task(s) in the list.
---------------------------------------
```

### `event <desc> /at <startDate>` - Add an event item

Adds a event item and gives it the description \<desc> then and  a start time of \<startDate>, then adds it to the list of tasks. 

A succesful addition will show a message indicating the task just added, its startDate and the number of items in the list after adding it.

\<startDate> must be formatted as YYYY-MM-DD.

Example of usage: 

`event visit my cat /at 2021-11-12`

Expected outcome:
```
---------------------------------------
Got it. I've added this task:
	[E][✘] visit my cat (at: Nov 12 2021)
Now you have n task(s) in the list.
---------------------------------------
```

### `delete <desc>` - Delete a task

Deletes the first instance of a task in the list of tasks with a matching description to \<desc>. If no matches are found Duke will print a message that the task was not found else it will print the details of the task just deleted and the number of remaining tasks after deletion.

Example of usage: 

`delete find my dog`

Expected outcome:
```
---------------------------------------
Got it. I've deleted this task:
	[T][✓] find my dog
Now you have 8 task(s) in the list.
---------------------------------------
```

### `done <desc>` - Mark a task as done 

Marks done the first instance of a task in the list of tasks with a matching description to \<desc>. If no matches are found Duke will print a message that the task was not found else it will print the details of the task just marked.

A done task has a [✓] indicated just before the task description. Otherwise it would be a [✘].

Example of usage: 

`done find my dog`

Expected outcome:
```
---------------------------------------
done find my dog
Nice! I've marked this task as done:
	[T][✓] find my dog
------------------------------
```


### `find <desc>` - List all tasks with a  matching description 

Finds all task in the list of tasks that match the string  \<desc>.  Descriptions which have \<desc> as a substring are also considered a match. Duke prints all the tasks that match as well as their descriptions.

Example of usage: 

`find cat`

Expected outcome:
```
---------------------------------------
Here are the matching tasks in your list
1.[E][✘] visit my cat (at: Nov 12 2021)
2.[D][✘] adopt a cat (by: Nov 11 2021)
---------------------------------------
```

### `list` - List all tasks and their descriptions 

Finds all task in the list of tasks that match the string  \<desc>.  Descriptions which have \<desc> as a substring are also considered a match. Duke prints all the tasks that match.

Example of usage: 

`list`

Expected outcome:
```
---------------------------------------
1.[T][✘] find my dog
2.[E][✘] visit my cat (at: Nov 12 2021)
3.[D][✘] adopt a cat (by: Nov 11 2021)
---------------------------------------
```


### `bye` - Exit Duke

Exit the program.

Example of usage: 

`bye`

Expected outcome:
```
Bye. Hope to see you again soon!
```


