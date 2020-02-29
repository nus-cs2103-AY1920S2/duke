# Duke User Guide

![Duke Screenshot](./Ui.png)
Product Screenshot

## Description
Duke is a simple and interactive task manager that is targeted for users who prefer typing commands. This program is created for the sake of CS2103T. Also, this program is inspired by *The Good Place*.

## Technology
1. Java 11
2. JavaFX 11
3. Gradle

## Usage

### Starting Duke
To start Duke, you can double-click on the application. If it does not work, you may enter `java -jar <duke-filename>` into your computer's terminal. If it does not work, please make sure that you have Java 11 installed.

Upon starting Duke, the application will attempt to load the previous task history from `./data/tasks.txt`. If the file is missing, Duke will create a new storage file there.

### Creating Tasks
Tasks in Duke are categorised into: To-Do, Deadline and Event tasks.

#### To-Do tasks
To-Do tasks form the basic tasks in Duke. In Duke, you are allowed to set a To-Do task with a description.

**Creating a new To-Do Task**
Command
```
todo <description>
```
Expected Output
```
Okie! I've added this task:
    <task-info>
You have <number-of-tasks> tasks in the list!"
```
Duplicated Task Output
```
This task is duplicated!
```

#### Deadline tasks
Deadline tasks are tasks that have to be done by a particular date and time. In Duke, you are allowed to set a Deadline task with a description and a date (format: `yyyy-MM-dd HHmm`).

**Creating a new Deadline Task**
Command
```
deadline <description> /by <date>
```
Expected Output
```
Okie! I've added this task:
    <task-info>
You have <number-of-tasks> tasks in the list!"
```
Duplicated Task Output
```
This task is duplicated!
```

#### Event tasks
Event tasks are tasks that have to be done on a particular date and time. In Duke, you are allowed to set an Event task with a description and a date (format: `yyyy-MM-dd HHmm`).

**Creating a new Event Task**
Command
```
event <description> /at <date>
```
Expected Output
```
Okie! I've added this task:
    <description>
You have <number-of-tasks> tasks in the list!"
```
Duplicated Task Output
```
This task is duplicated!
```

### Listing Tasks
After creating your tasks, you may view the list of tasks that you have added into Duke. The list of tasks are also stored in an editable text file at `./data/tasks.txt`.

**Showing the list of tasks**
Command
```
list
```
Expected Output
```
Okay Chidi, listen properly...
These are your tasks:
1. <task-info>
...
```
Empty Task List Output
```
You have no tasks
```

### Searching Tasks
After creating your tasks, you can search for tasks that contain a particular string in its description.

**Showing a list of tasks that contain a string**
Command
```
find <string-to-match>
```
Expected Output
```
Alrighty, I found some tasks! Here they are:
1. <task-info>
...
```
No Matching Tasks Output
```
Ah dang it! There are no matching tasks ):
```

### Completing Tasks
Once you have finished your task, you can update the task and mark it as done. Refer to the task number that is given in the `list` command.

**Marking a task as done**
Command
```
done <task-number>
```
Expected Output
```
Well done Chidi! I've marked this task as done:
    <task-info>
Give yourself a pat on the back!
```
Invalid Task Index Output
```
Oops Chidi! I think you gave me the wrong task index!
Please refer to the 'list' command for available indices.
```

### Deleting Tasks
If your list of tasks get too lengthy, you can also choose to delete any task from Duke at any time. Refer to the task number that is given in the `list` command. Alternatively, you may also delete the tasks from the storage file in `./data/tasks.txt`. Be careful when editing this file, as it may crash the application.

**Deleting a task**
Command
```
delete <task-number>
```
Expected Output
```
Noted. I've removed this task:
    <task-info>
Now you have %d tasks in the list.\n
```
Invalid Task Index Output
```
Oops Chidi! I think you gave me the wrong task index!
Please refer to the 'list' command for available indices.
```

### Closing Duke
When you are finished with using Duke, you can exit the application. Duke's storage is persistent, so your previous task list will be automatically loaded upon the next startup.

**Closing Duke**
Command
```
bye
```
Expected Output
```
Bye Chidi! "Enjoy your stay in The Bad Place! (hehe)
```