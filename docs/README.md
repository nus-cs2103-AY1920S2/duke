# User Guide

## Features 

### Task list
Keeps track of tasks that you are working on.
You can refer to tasks using their index number displayed by the `list` command.

### Adding and removing tasks
You can modify your task list by adding or removing tasks from it using commands.
There are three types of tasks:
* ToDo tasks do not have any associated time.
* Event tasks have an event date.
* Deadline tasks have a due date.

### Marking tasks as completed
You can mark a task as completed using the `done` command.

### Save to disk
Your task list is automatically saved to disk on exit.
This occurs if you close Duke using the `bye` command, or by closing Duke gracefully.

## Usage
### Entering commands
1. Select the text box on the bottom of the window.
    Click on it to focus on the text box.
2. Type your command into the text box.
3. Press Enter to send the command to Duke.
4. The result of the command is displayed in the space above the text box.


### `help` command
Displays help for the given command name.

Usage: `help [name of command]`

Example of usage:
```
help list
help sort
```

Expected outcome:
For a valid command, help text for a valid command is printed.
For an invalid command, an error is displayed.


### `todo` command
Adds a ToDo task, which has no associated time, to your task list.

Usage: `todo [name of task]`

Example of usage:
```
todo Buy some groceries
```

Expected outcome:
```
Added task:
  [T][✘] Buy some groceries
Now you have 2 task(s).
```


### `bye` command
Saves task list to file and exits.

Usage: `bye`

Example of usage:
```
bye
```

Expected outcome:
Duke saves the task list and immediately exits.


### `deadline` command
Adds a new Deadline task into the task list.

Usage: `deadline [task description] /by [yyyy-mm-dd]`

Example of usage:
```
deadline Write Duke user guide /by 2020-03-02
```

Expected outcome:
```
Added task:
  [D][✘] Write Duke user guide (by: Mar 2 2020)
Now you have 2 task(s).
```


### `delete` command
Removes the i-th task from the task list.
An error message is displayed if the task number `i` is invalid.

Usage: `delete [i]`

Example of usage:
```
delete 2
```

Expected outcome:
```
Removed task:
  [D][✘] Write Duke user guide (by: Mar 2 2020)
Now you have 1 task(s).
```


### `done` command
Marks the i-th task in the task list as done.
An error message is displayed if the task number `i` is invalid.

Usage: `done [i]`

Example of usage:
```
done 2
```

Expected outcome:
```
Marked task as done:
  [T][✓] Buy some groceries
```


### `event` command
Adds a new Event task into the task list.

Usage: `event [task description] /at [yyyy-mm-dd]`

Example of usage:
```
event Holidays /at 2050-12-12
```

Expected outcome:
```
Added task:
  [E][✘] Holidays (at: Dec 12 2050)
Now you have 2 task(s).
```


### `find` command
Finds and displays tasks whose description matches the query string.
The numbers displayed _do not_ correspond to task indices.

Usage: `find [task description]`

Example of usage:
```
find groceries
```

Expected outcome:
```
Here are the matching tasks in your list:
1.[T][✓] Buy some groceries
2.[T][✘] Eat raw groceries
```

### `list` command
Displays the tasks in the task list.

Usage: `list`

Example of usage:
```
list
```

Expected outcome:
A list of tasks in the task list is displayed.
```
1.[T][✓] Buy some groceries
2.[E][✘] Holidays (at: Dec 12 2050)
3.[T][✘] a
4.[T][✘] Eat raw groceries
```


### `sort` command
Sorts all tasks in chronological order, then lists the tasks.

Usage: `sort`

Example of usage:
```
sort
```

Expected outcome:
```
Sorted tasks by date. Here are your tasks:
1.[E][✘] Holidays (at: Dec 12 2050)
2.[T][✓] Buy some groceries
3.[T][✘] a
4.[T][✘] Eat raw groceries
```
