# User Guide

## Features
1. `List` 
1. `Todo`
1. `Event`
1. `Deadline`
1. `Do`
1. `Delete`
1. `Find`
1. `View Schedules`
1. `Bye`


### Feature 1: `List`
`list` : List all tasks recorded

Format: `list`

Example of usage: `list`

Expected Outcome:

!(/docs/list.png)

###Feature 2: `Adding a Todo task`
`Todo`: Creates a Todo task

Format: `todo [description]`

Remark: [description] can have spaces

Example of usage: `todo Complete CS2103 iP`

![Expected outcome:](/docs/todo.png) 

```
Got it. I've added this task:
  [T][✘] Complete CS2103 iP
Now you have 1 task in the list.
```

###Feature 3: `Adding a Deadline task`
`Deadline`: Creates a Deadline task

Format: `deadline [description] /by YYYY-MM-DD`

Remarks:
* [description] can have spaces

* date must follow the YYYY-MM-DD format exactly

Example of usage: `deadline Complete CS2103 iP /by 2020-03-01`

![Expected outcome:](/docs/deadline.png)
```
Got it. I've added this task:
  [D][✘] Complete CS2103 iP (by: 1-Mar-2020)
Now you have 1 task in the list.
```

###Feature 4: `Adding an Event task`
`Deadline`: Creates a Event task

Format: `event [description] /at YYYY-MM-DD`

Remarks:
* [description] can have spaces

* date must follow the YYYY-MM-DD format exactly

Example of usage: `event Celebrate completion of CS2103 iP /at 2020-03-02`

![Expected outcome:](/docs/event.png)
```
Got it. I've added this task:
  [E][✘] Celebrate completion of CS2103 iP (at: 2-Mar-2020)
Now you have 1 task in the list.
```

###Feature 5: `Doing a task`
`Done`: Marks a task as complete

Format: `do [index]`

Remarks:
* [index] follows the number of the task when `list` is used

Example of usage: `done 1`

![Expected outcome:](/docs/done.png)
```
Nice! I've marked this task as done:
  [✓] Complete iP
```

###Feature 6: `Deleting a task`
`Delete`: Deletes a task

Format: `delete [index]`

Remarks:
* [index] follows the number of the task when `list` is used

Example of usage: `delete 1`

![Expected outcome:](/docs/delete.png)
```
Noted. I've removed this task:
  [✓] Complete iP
Now you have 1 task in the list.
```

###Feature 7: `Finding a task`
`Find`: Finds any task that matches or has a partial match to the input

Format: `find [description]`

Remarks:
* [description] can include spaces

Example of usage: `find book`

![Expected outcome:](/docs/find.png)
```
1.[T][✘] find a book
2.[D][✘] delete the e-book (by: 25-Dec-2020)
4.[E][✘] install e-bookreader app (at: 24-Dec-2020) 
```

###Feature 8: `Viewing Schedules`
`View Schedules`: Displays tasks occurring on input date 

Format: `view [date]`

Remarks:
* [date] has to follow the YYYY-MM-DD format

Example of usage: `view 2020-12-25`

![Expected outcome:](/docs/view.png)
```
2.[D][✘] delete the e-book (by: 25-Dec-2020)
3.[E][✘] Christmas partay (at: 25-Dec-2020)
```


###Feature 9: `Bye`
`Bye`: Exits Duke

Format: `bye`

Example of usage: `bye`

![Expected outcome:](/docs/bye.png)
```
Bye. Hope to see you again soon!
```