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
  [T][✗] borrow book
Now you have 5 tasks in the list.
```

### `deadline [activity] /by [year]-[month]-[day] [hour]:[minute]`

Adds a deadline task to the task list, with *[activity]* as the description,
and *[year]-[month]-[day] [hour]:[minute]* as the datetime.

Example of usage: 

```
deadline do homework /by 2020-03-20 09:00
```

Expected outcome:

```
Got it. I've added this task: 
  [D][✗] do homework (by: 20 Mar. 2020 09:00HRS)
Now you have 5 tasks in the list.
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
  [D][✗] fireworks (at: 01 Dec. 2020 09:00HRS to 01 Dec. 2020 10:00HRS)
Now you have 5 tasks in the list.
```
