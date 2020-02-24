# User Guide
Duke is a simple program which is able to track your tasks and lists.

## Features 
**Adding tasks** 
- Tasks can be added to your list. Available tasks are *event*, *deadline* and *todo*.

**Deleting tasks**
- Tasks can be deleted when done or deemed unnecessary.
- Multi deletion can be performed.

**Display tasks**
- Tasks can be displayed for quick reference.

**Mark tasks**
- Tasks can be marked to determine if done or not.

**Find tasks**
- Tasks can be found with a keyword in their description.

## Usage

### `todo` - Adds a todo task to the task list.

When the `todo` command is called, along with the description, a *todo* task is added into the task list. 


Example of usage: 

`todo swim later`

Expected outcome:
```
Got it. I've added this task:  
[T][Not Done] swim later
Now you have 1 tasks in the list.
```

![todo pic](todo.png)

### `event` - Adds an event task to the task list.

When the `event` command is called, along with the description, followed by an `/at`,
a *event* task is added into the task list. Take note that the format of the date is "YYYY-MM-DD". 

Example of usage: 

`event IHG semis /at 2020-01-01`

Expected outcome:
```
Got it. I've added this task:  
[T][Not Done] swim later
Now you have 2 tasks in the list.
```

### `deadline` - Adds a deadline task to the task list.

When the `deadline` command is called, along with the description, followed by an `/by`,
a *deadline* task is added into the task list. Take note that the format of the date is "YYYY-MM-DD". 


Example of usage: 

`deadline IP /at 2020-02-02`

Expected outcome:
```
Got it. I've added this task:  
[T][Not Done] swim later
Now you have 3 tasks in the list.
```

### `list` - Displays a list of tasks recorded.

When the `list` command is called, a list of existing tasks is displayed.

Example of usage: 

`list`

Expected outcome:
```
Here are your remaining tasks:
1.[T][Not Done] swim
2.[E][Not Done] IHG  (at: Feb 2 2020)
3.[D][Not Done] IP  (by: Feb 2 2020)
4.[T][Not Done] rub dwayne's head for good luck
```
### `delete` - Deletes a task.

When the `delete` command is called, followed by the index of the task, a task from the task list is removed.
When the `delete` command is called, followed by `all`, a task from the task list is removed.

Example of usage: 

`delete 4`

Expected outcome:
```
Noted. I've removed this task:
[T][Not Done] rub dwayne's head for good luck
Now you have 3 tasks in the list.
```

Example of usage: 

`delete all`

Expected outcome:
```
Noted. I've deleted all tasks. Don't come crying 
later!
```
### `done` - Marks tasks as done

When the `done` command is called, followed by the index of the task, a task from the task list is marked as done.


Example of usage: 

`done 2`

Expected outcome:
```
Nice! I've marked this task as done:
[E][Done] IHG  (at: Feb 2 2020)
```

### `find` - Finds a specific task in task list

When the `find` command is called, followed by the keyword of the task, the command returns the tasks with description 
containing the keyword.


Example of usage: 

`find swim`

Expected outcome:
```
Here are your remaining tasks:
1.[T][Not Done] swim
```

### `bye` - Finds a specific task in task list

When the `bye` command is called, the program terminates.

Example of usage: 

`bye`

Expected outcome:
```
Bye nerd. Hope to see you again soon!
```