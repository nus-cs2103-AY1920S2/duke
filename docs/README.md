# User Guide for Duke Chatbot

## TABLE OF CONTENTS
1. [Introduction](/docs/README.md#1-introduction)
2. [Interacting with Duke](/docs/README.md#2-interacting-with-duke) 
     2.1. [General Guideline](/docs/README.md#21-general-guideline)
     2.2. [Images](/docs/README.md#22-images)
     2.3. [Sample Interaction with the Duke Application](/docs/README.md#23-sample-interaction-with-the-duke-application)
3. [Features](/docs/README.md#3-features)
     3.1. [Todo](/docs/README.md#31-add-a-todo-task-todo)
     3.2. [Event](/docs/README.md#32-add-an-event-task-event)
     3.3. [Deadline](/docs/README.md#33-add-a-deadline-task-deadline)
     3.4. [List](/docs/README.md#34-list-all-tasks-list)
     3.5. [Done](/docs/README.md#35-mark-task-as-done-done)
     3.6. [Delete](/docs/README.md#36-delete-task-delete)
     3.7. [Find](/docs/README.md#37-find-tasks-containing-a-keyword-find)
     3.8. [Snooze](/docs/README.md#38-reschedule-a-deadline-or-event-snooze)
     3.9. [Bye](/docs/README.md#39-exit-duke-bye)
     3.10. [Storing Data](/docs/README.md#310-storing-data)
4. [Command Overview](/docs/README.md#4-command-overview)
5. [Acknowledgements](/docs/README.md#5-acknowledgements)


## 1. Introduction
Duke is a Personal Assistant Chatbot which assists in tracking tasks.

## 2. Interacting with Duke

### 2.1 General Guideline
When started up, Duke will introduce itself.  


After the introduction, the user may input any command, which Duke will try to execute.  
Duke will then inform the user if their command was successfully executed.  
Duke's expression will also change depending on whether or not the user's input was successfully executed.  


After informing the user if their command was successfully executed, Duke will wait for the user's next command.  


It should be noted that Duke's image and dialog box are always on the left, while the user's image and dialog box are always on the right.

### 2.2 Images
As mentioned in 2.1, Duke's expressions change depending on whether or not the user's command was successfully executed.  
This is done by having a different image correspond to a different case.  


Below are a list of possible images used in the Duke Application.

#### Images used for the User:

There is only one image used to represent the user. The image looks like this:  

<img src = "NewUser.png" width = "100">


#### Images used for the Duke:
There are three possible images used to represent the Duke.


Firstly, there is a unique image that is only used during the first interaction with the user. This serves as an introduction to the Duke.  


The image looks like this:  

<img src = "IntroDuke.png" width = "100">


Next, there is the image used when the Duke successfully carries out a command from the user.  
The image looks like this:


<img src = "NewDuke.png" width = "100">

Lastly, there is the image used when the Duke is unable to carry out a command from the user.  
The image looks like this:


<img src = "Fail.png" width = "100">

### 2.3 Sample Interaction with the Duke Application
Below is a sample interaction with the Duke Application.  

<img src = "Ui.png" width = "350">


## 3. Features

### 3.1 Add a todo task: `todo`
Adds a todo task to Duke.

Format: `todo DESCRIPTION`

- `DESCRIPTION`: any sequence of characters that are found on a standard keyboard.

**Example of usage:** 

`todo CS2103 Quiz`

**Expected outcome:**

```
I've added this task:
[T][N] CS2103 Quiz
Now you have 1 task in the list.  
```

### 3.2 Add an event task: `event`
Adds an event task to Duke.

Format: `event DESCRIPTION /at DETAILS`

- `DESCRIPTION`: any sequence of characters that are found on a standard keyboard.
- `DETAILS`: any sequence of characters that are found on a standard keyboard.

**Example of usage:** 

`event Steamboat dinner /at Bugis, 15 Mar 2020 8PM`

**Expected outcome:**

```
I've added this task:
[E][N] Steamboat dinner (at: Bugis, 15 Mar 2020 8PM)
Now you have 1 task in the list.
```


### 3.3 Add a deadline task: `deadline`
Adds a deadline task to Duke.

Format: `deadline DESCRIPTION /by DATE TIME`

- `DESCRIPTION`: any sequence of characters that are found on a standard keyboard.
- `DATE`: yyyy-MM-dd
- `TIME`: HHmm (Time is optional. If no time is provided, it is assumed to be 0000)

**Example of usage:** 

`deadline CS2343 Assigment /by 2020-02-19`

**Expected outcome:**

```
I've added this task:
[D][N] CS3243 Assignment (by: 19 Feb 2020 0000)
Now you have 1 task in the list.
```

### 3.4 List all tasks: `list`
Lists out all the tasks saved in Duke.

Format: `list`

**Example of usage:** 

`list`

**Expected outcome:**

```
Here are the tasks in your list:
1. [T][N] CS2103 Quiz
2. [E][N] Steamboat dinner (at: Bugis, 15 Mar 2020, 8PM)
3. [D][N] CS3243 Assignment (by: 19 Feb 2020 0000)
```


### 3.5 Mark task as done: `done`
Marks a saved task as done. 

Format: `done INDEX`

- `INDEX`: index number of the desired task (can be obtained from the `list` command in section 3.4 as seen [here](/docs/README.md#34-list-all-tasks-list))

**Example of usage:** 

`done 1`

**Expected outcome:**

```
Nice! I've marked this task as done:
[T][Y] CS2103 Quiz
```


### 3.6 Delete task: `delete`
Deletes saved task from Duke. 

Format: `delete INDEX`

- `INDEX`: index number of the desired task (can be obtained from the `list` command in section 3.4 as seen [here](/docs/README.md#34-list-all-tasks-list))

**Example of usage:** 

`delete 1`

**Expected outcome:**


```
I've removed this task:
[T][Y] CS2103 Quiz
you now have 2 tasks in the list.
```



### 3.7 Find tasks using a keyword: `find`
Finds all tasks that contain a specific keyword.

Format: `find KEYWORD`

**Example of usage:** 

`find Assignment`

**Expected outcome:**


```
Here are the matching keywords in your list:
1. [D][N] CS3243 Assignment (by: 19-02-2020 0000) 
```


### 3.8 Reschedule a deadline or event: `snooze`
Changes the date and timing for a deadline or the details for an event.

Format: `snooze INDEX /to TASK_FORMAT`

- `INDEX`: index number of the desired task (can be obtained from the `list` command in section 3.4 as seen [here](/docs/README.md#34-list-all-tasks-list))
- todo tasks cannot be snoozed, only event tasks and deadline tasks can be snoozed.
- The format for `TASK_FORMAT` must be appropriate for the type of task being rescheduled.
  - Case 1: rescheduled task is a deadline. 
    Format: `snooze INDEX /to DATE TIME`
    (format details can be found [here](/docs/README.md#32-add-an-event-task-event))
    

  - Case 2: Rescheduled task is an event. 
Format: `snooze INDEX /to TEXT` 
(format details can be found [here](/docs/README.md#33-add-a-deadline-task-deadline))

Example of usage: 

`snooze 2 /to 2020-03-16`

Expected outcome:

```
Got it, the updated deadline looks like:
[D][N] CS3243 Assignment (by: 16 Mar 2020 0000)
```

### 3.9 Exit Duke: `bye`
Exits Duke.  
The program will close shortly after the command is entered.

Format: `bye`

**Example of usage:**  
`bye`

**Expected outcome:**
```
Bye, hope to see you again soon!
```


### 3.10 Storing Data
Data in duke is automatically saved after using any of the following commands:
- todo
- event
- deadline
- done
- delete
- snooze



## 4. Command Overview
- Add a todo task: `todo DESCRIPTION`
- Add an event task: `event DESCRIPTION /at DETAILS`
- Add a deadline task: `deadline DESCRIPTION /by DATE TIME`
- List all tasks: `list`
- Mark task as done: `done INDEX`
- Delete task: `delete INDEX`
- Find: `find KEYWORD`
- Snooze: `snooze INDEX /to TASK_FORMAT` 
- Exit: `bye`

## 5. Acknowledgements
Credit to:
- Egor Zhgun for creating Utya Duck (Used for all Duke and User images)
