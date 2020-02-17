# User Guide

## Features 

### Add Todo, Deadline, and Event
Todo is a task without any date/time attached to it e.g., visit new theme park.

Deadline is a task that need to be done before a specific date/time e.g., submit report by 11/10/2019 5pm.

Event is a task that start at a specific time and ends at a specific time e.g., team project meeting on 2/10/2019 2-4pm.
## Usage

### `todo [activity]`

Adds a todo task to the task list, with *[activity]* as the description

Example of usage: 

`todo borrow book`

Expected outcome:

```
Got it. I've added this task: 
  [T][X] borrow book
Now you have 1 tasks in the list.
```

### `deadline [activity] /by [year]-[month]-[day] [hour]:[minute]`

Adds a deadline task to the task list, with *[activity]* as the description,
and *[year]-[month]-[day] [hour]:[minute]* as the datetime.

Example of usage: 

```
deadline rush my homework again /by 2020-03-20 09:00
```

Expected outcome:

```
Got it. I've added this task: 
  [D][X] rush my homework again (by: 20 Mar. 2020 09:00HRS)
Now you have 2 tasks in the list.
```

### `event [activity] /at [year]-[month]-[day] [hour]:[minute] to [year]-[month]-[day] [hour]:[minute]`

Adds an event task to the task list, with *[activity]* as the description,
and *[year]-[month]-[day] [hour]:[minute] to [year]-[month]-[day] [hour]:[minute]* as the datetime.

Example of usage: 

```
event fireworks /at 2020-12-01 09:00 to 2020-12-01 10:00
```

Expected outcome:

```
Got it. I've added this task: 
  [E][X] fireworks (at: 01 Dec. 2020 09:00HRS to 01 Dec. 2020 10:00HRS)
Now you have 3 tasks in the list.
```
### `list`

Lists the task list

Example of usage: 

`list`

Expected outcome:

```
Here are the tasks in your list:
     1.[T][X] borrow book
     2.[D][X] rush my homework again (by: 20 Mar. 2020 09:00HRS)
     3.[E][X] fireworks (at: 01 Dec. 2020 09:00HRS to 01 Dec. 2020 10:00HRS)
```

### `sort [type]`

Sorts the task list by *[type]*.
*[type]* can be *description* or *datetime*.

Example of usage: 

`sort description`

Expected outcome:

```
Successfully sorted your tasks list according to description. Here are the tasks in your list:
     1.[T][X] borrow book
     2.[E][X] fireworks (at: 01 Dec. 2020 09:00HRS to 01 Dec. 2020 10:00HRS)
     3.[D][X] rush my homework again (by: 20 Mar. 2020 09:00HRS)
```

### `find [keyword]`

Finds a list of tasks from the task list which contains *[keyword]*.

Example of usage: 

`find 09:00`

Expected outcome:

```
Here are the matching tasks in your list:
     1.[E][X] fireworks (at: 01 Dec. 2020 09:00HRS to 01 Dec. 2020 10:00HRS)
     2.[D][X] rush my homework again (by: 20 Mar. 2020 09:00HRS)
```

### `done [number]`

Marks a task from the task list as done, with *[number]* as the task number, and 1 as the first task.

Example of usage: 

`done 1`

Expected outcome:

```
Nice! I've marked this task as done:
  [T][V] borrow book
```

### `delete [number]`

Removes a task from the task list, with *[number]* as the task number, and 1 as the first task.

Example of usage: 

`delete 1`

Expected outcome:

```
Noted. I've removed this task: 
  [T][V] borrow book
Now you have 2 tasks in the list.
```

### `bye`

Say bye to Duke and terminates the application

Example of usage: 

`bye`

Expected outcome:

The application closes.
