# User Guide for Duke 

A versatile task manager every command-line user has been waiting for. 

## Features & Usage

Duke allows you to add different types of Tasks, search for tasks using keywords, mark tasks as completed, 
list all tasks and delete any task. Tasks are categorised into three categories:

- ToDo: A simple todo task with just a description. 
- Deadline: A task that allows you to specify a 'by' property, in addition to a description, to specify the date 
you need to complete it. 
- Event: A task that allows you to specify a 'at' property in addition to a description, to specify the 
timing of the event 

#### Add Tasks 

- Add a ToDo using the command: `todo <description>`. For example, `todo write user guide for duke` will create 
a todo with description *write user guide for duke* to your list of tasks. 
- Add a Deadline using the command `deadline <description> /by <by>`. For example `deadline submit CS2103 project 
/by Monday` will create a Deadline with description *submit CS2103 project* and with by *Monday*. 
- Add an Event using the command `event <description> /at <time>`. For example `event CS2103 tP showcase /at Mon 2-4pm`
will create an Event with description *CS2103 tp showcase* and with time *Mon 2-4 pm*. 

#### List Tasks

This feature allows you to view all the tasks currently stored in duke. It shows their number in the list 
(starting from 1), task type (todo, deadline or event), completion status, description and by/time (if any). 
For example, running `list` after entering the above task creation commands will show the following output:

```aidl
Here are the tasks in your list:
1. [T][✗] write user guide for duke
2. [D][✗] submit CS2103 project (by: Monday)
3. [E][✗] CS2103 tP showcase (at: Mon 2-4 pm)
```
#### Delete Tasks

You can also delete tasks from duke. You can use the task number (as shown in the list command above) to 
refer to a particular task. The syntax for this command is: `delete <task num>`. For example, running `delete 3` 
in our example above would delete the `event` task. If you run `list` again, you would get the following output:

```aidl
Here are the tasks in your list:
1. [T][✗] write user guide for duke
2. [D][✗] submit CS2103 project (by: Monday)
```

#### Complete Tasks

You can mark tasks as completed when you're done with a particular task. Just like the `delete` command, you can refer 
to a task using its number in the task list. The syntax for this command is: `done <task num>`.  Continuing our example 
from above, we can complete task number 2 using the command `done 2`. If you run `list` again, you would 
get the following output:

```aidl
Here are the tasks in your list:
1. [T][✗] write user guide for duke
2. [D][✓] submit CS2103 project (by: Monday)
```
Note that the completion status of the `deadline` task has changed. It now shows a `[✓]`. 

#### Find Tasks using Keywords

You can also find tasks matching a keyword. The syntax for this command is: `find <keyword>`. If you do `find CS2103` 
in the list now, you should get the following output:

```aidl
Here are the matching tasks in your list:
2. [D][✓] submit CS2103 project (by: Monday)
```

#### Use Shortcuts

You can also use shortcuts to create tasks. To activate shortcuts, simply send the `activate` command to Duke and 
it will activate the shortcuts for you. With this, you can use the initial letter of each task type to create the 
specific task. For example, `t` to create a `todo`, `d` for `deadline`, and `e` for `event`.

To exit duke, you can simply enter `bye`. 