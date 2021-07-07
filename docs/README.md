# User Guide

## Table of Contents
* [1. Introduction](#1-introduction)
* [2. Quick Start](#2-quick-start)
* [3. Feature](#3-feature)
    * [3.1. Chatting](#31-chatting)
    * [3.2. Load task immediately from hard disk](#32-load-task-immediately-from-hard-disk)
    * [3.3. Add a new task](#33-add-a-new-task)
    * [3.4. Display list of tasks](#34-display-list-of-tasks)
    * [3.5. Mark task as done](#35-mark-task-as-done)
    * [3.6. Delete a task](#36-delete-a-task)
    * [3.7. Find a task](#37-find-a-task)
    * [3.8. Close the chatbot with user input](#38-close-the-chatbot-with-user-input)
* [4. Usage](#4-usage)
    * [4.1. Add a new task](#41-add-a-new-task)
    * [4.2. `list`](#42-list)
    * [4.3. `done [index of task]`](#43-done-index-of-task)
    * [4.4. `delete [index of task]`](#44-delete-index-of-task)
    * [4.5. `find [keyword]`](#45-find-keyword)

## 1. Introduction
Tired of being a procrastinator? Fret not! Dodo is here to make sure you do your work.

You can save your todo, deadline, event list--all in one!

![Figure of Dodo](Ui.png)

##### Figure 1. Screenshot of Dodo

## 2. Quick Start
1. Install `java 11` or above in your computer.
2. Download the `jar` file [here](https://github.com/johannagwan/duke/releases/tag/v0.2)
3. In your terminal, run `java -jar [path to jar file]` to run the jar file.
4. Type your input accordingly. List of commands are available at [Section 4](#4-usage). 

## 3. Feature
### 3.1. Chatting
Dodo is a personalised chatbot who will reply you in an instant. It's your friend!

### 3.2. Load task immediately from hard disk
When Dodo starts, it will automatically load your tasks from your hard disk file. If you have no existing file, Dodo will automatically create one for you as you add your tasks to the list.

### 3.3. Add a new task
You can add your upcoming todo, deadline and event into your list of tasks. It is automatically saved by Dodo!

Details of the different types of task are as follows: 
* Todo: a task to complete **without** a specified due date.
* Deadline: a task to complete **with** a specified due date.
* Event: an occasion that will happen in a specified date.

### 3.4. Display list of tasks
Dodo can display the list of tasks (todo, deadline, and event) that you have saved in chronological order according to the due dates and in alphabetical order.

### 3.5. Mark task as done
All tasks are initially marked as undone. When you have accomplished your task, don't forget to inform Dodo, by marking your task as done. Before ticking off your task, remember to type `list` to check your task's index, as the index may change if you have previously added or deleted a task.

If the command `done [index]` is done after `find [keywords]`, the command `done [index]` will operate on the **full** list instead of the items filtered out by the `find [keyword]` command.

### 3.6. Delete a task
If you accidentally add a new task, or simply want to reduce your task clutter, you can delete your task according to its index. Remember  to type `list` to check your task's index, as the index may change if you have previously added or deleted a task.

If the command `delete [index]` is done after `find [keywords]`, the command `delete [index]` will operate on the **full** list instead of the items filtered out by the `find [keyword]` command.

### 3.7. Find a task
Want to find a task with a specified keyword? Fret not, Dodo got your back. Dodo can display a list of tasks which contain the keyword you input.

### 3.8. Close the chatbot with user input
You can close the chatbot by simply keying in `bye`.

## 4. Usage

### 4.1. Add a new task
All tasks must be input in the correct format to be added successfully. Don't worry if you do not remember the exact format. As a good friend, Dodo will remind you.
```
Invalid input format!
Format:
- To list all tasks: list
- To add new deadline: deadline [description] /by [due date in yyyy-mm-dd]
- To add new event: event [description] /at [date in yyyy-mm-dd]
- To add new todo: todo [description]
- To mark task as done: done [index]
- To delete a task: delete [index]
- To find a task: find [keyword]
- To exit: bye
```

#### 4.1.1. `todo [description]`
Creates a new todo task with a description.

Example of usage: `todo running at gym`

Expected outcome:
```
Gotcha. Added this to your list:
[T][N] running at gym
Now you got 1 task in your list!
```

#### 4.1.2. `deadline [description] /by [due date in yyyy-mm-dd]`
Creates a new deadline task with a description and a due date.

Example of usage: `deadline assignment 1 /by 2020-02-02`

Expected outcome:
```
Gotcha. Added this to your list:
[D][N] assignment 1 (by: Feb 2 2020)
Now you got 2 tasks in your list!
```

#### 4.1.3. `event [description] /at [date in yyyy-mm-dd]`
Creates a new event task with a description and a date.

Example of usage: `event formal dinner /at 2020-02-16`

Expected outcome:
```
Gotcha. Added this to your list:
[E][N] formal dinner (at: Feb 16 2020)
Now you got 3 tasks in your list!
```

### 4.2. `list`
Displays a list of tasks that is loaded from the hard disk and that you have added.

Example of usage: `list`

Expected outcome:
```
Stop procrastinating. Do it now!
1. [D][N] assignment 1 (by: Feb 2 2020)
2. [E][N] formal dinner (at: Feb 16 2020)
3. [T][N] running at gym
```

### 4.3. `done [index of task]`
Marks a task as done. All tasks are initially marked as undone by default.

Example of usage: `done 1`

Expected outcome:
```
Good job! One off your chest!
[D][Y] assignment 1 (by: Feb 2 2020)
```

When you key in `list` again, the expected outcome would be:
```
Stop procrastinating. Do it now!
1. [D][Y] assignment 1 (by: Feb 2 2020)
2. [E][N] formal dinner (at: Feb 16 2020)
3. [T][N] running at gym
```

### 4.4. `delete [index of task]`
Deletes a particular task with the specified index.

Example of usage: `delete 1`

Expected outcome:
```
Okay, I have removed this task for you:
[D][Y] assignment 1 (by: Feb 2 2020)
Now you got 2 tasks in your list!
```

When you key in `list` again, the expected outcome would be:
```
Stop procrastinating. Do it now!
1. [E][N] formal dinner (at: Feb 16 2020)
2. [T][N] running at gym
```

### 4.5. `find [keyword]`
Finds a particular task which contains the specified keyword or.

Example of usage: `find o`

Expected output:
```
Here are the matching tasks in your list:
Stop procrastinating. Do it now!
1. [E][N] formal dinner (at: Feb 16 2020)
```

Example of usage: `find gym`

Expected output:
```
Here are the matching tasks in your list:
Stop procrastinating. Do it now!
1. [T][N] running at gym
```