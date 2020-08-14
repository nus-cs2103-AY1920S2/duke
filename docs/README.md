# User Guide for Duke Chatbot

## TABLE OF CONTENTS
1. [Introduction](#1-introduction)
2. [Interacting with Duke](#2-interacting-with-duke)  
    2.1. [General Guideline](#21-general-guideline)  
    2.2. [Images](#22-images)  
    2.3. [Sample Interaction with the Duke Application](#23-sample-interaction-with-the-duke-application)  
3. [Features](#3-features)  
     3.1. [Add a todo task](#31-add-a-todo-task-todo)  
     3.2. [Add an event task](#32-add-an-event-task-event)  
     3.3. [Add a deadline task](#33-add-a-deadline-task-deadline)  
     3.4. [List all tasks](#34-list-all-tasks-list)  
     3.5. [Mark task as done](#35-mark-task-as-done-done)  
     3.6. [Delete task](#36-delete-task-delete)  
     3.7. [Find tasks using a keyword](#37-find-tasks-using-a-keyword-find)  
     3.8. [Reschedule a deadline or event](#38-reschedule-a-deadline-or-event-snooze)  
     3.9. [Exit Duke](#39-exit-duke-bye)  
     3.10. [Storing Data](#310-storing-data)  
4. [Command Overview](#4-command-overview)  
5. [Acknowledgements](#5-acknowledgements)


## 1. Introduction
Duke is a chatbot which assists in tracking tasks.

## 2. Interacting with Duke

### 2.1 General Guideline
When started up, Duke will introduce itself.  


After the introduction, the User may input any command, which Duke will try to execute.  
Duke will then inform the User if their command was successfully executed.  
Depending on whether or not the command was executed successfully, Duke will react differently.


After informing the User if their command was successfully executed, Duke will wait for the User's next command.  


Duke's image and dialog box are always on the left, while the User's image and dialog box are always on the right.

### 2.2 Images
As mentioned in Section 2.1, Duke will react differently based on whether or not the User's command was successfully executed. This is done by having a different image correspond to the various case.  


Below are a list of possible images used in the Duke Application.

#### Images used for the User:

There is only one image used to represent the User.  
The image looks like this:  

<img src = "NewUser.png" width = "100">


#### Images used for the Duke:
There are three possible images used to represent the Duke.


Firstly, there is a unique image that is only used during the first interaction with the User. It is used to introduce the Duke.  
The image looks like this:  

<img src = "IntroDuke.png" width = "100">


Next, there is the image used when the Duke successfully carries out a command from the User.  
The image looks like this:


<img src = "NewDuke.png" width = "100">

Lastly, there is the image used when the Duke is unable to carry out a command from the User.  
The image looks like this:


<img src = "Fail.png" width = "100">

### 2.3 Sample Interaction with the Duke Application
Below is a sample interaction with the Duke Application.  


<img src = "Ui.png" width = "350">


## 3. Features

### 3.1 Add a todo task: `todo`
Adds a todo task to Duke.

Format: `todo DESCRIPTION`

- `DESCRIPTION`: any sequence of ASCII printable characters (for reference, click [here](https://en.wikipedia.org/wiki/ASCII#Printable_characters))

**Example of usage:** 

`todo CS2103 Quiz`

**Expected outcome:**

```
Got it, I've added this task:
[T][N] CS2103 Quiz
Now you have 1 task in the list.  
```

### 3.2 Add an event task: `event`
Adds an event task to Duke.

Format: `event DESCRIPTION /at DETAILS`

- `DESCRIPTION`: any sequence of ASCII printable characters (for reference, click [here](https://en.wikipedia.org/wiki/ASCII#Printable_characters))
- `DETAILS`: any sequence of ASCII printable characters (for reference, click [here](https://en.wikipedia.org/wiki/ASCII#Printable_characters))

**Example of usage:** 

`event Steamboat dinner /at Bugis, 15 Mar 2020 8PM`

**Expected outcome:**

```
Got it, I've added this task:
[E][N] Steamboat dinner (at: Bugis, 15 Mar 2020 8PM)
Now you have 2 tasks in the list.
```


### 3.3 Add a deadline task: `deadline`
Adds a deadline task to Duke.

Format: `deadline DESCRIPTION /by DATE TIME`

- `DESCRIPTION`: any sequence of ASCII printable characters (for reference, click [here](https://en.wikipedia.org/wiki/ASCII#Printable_characters))
- `DATE`: yyyy-MM-dd
- `TIME`: HHmm (Time is optional. If no time is provided, it is assumed to be 0000)

**Example of usage:** 

`deadline CS3243 Assigment /by 2020-02-19`

**Expected outcome:**

```
Got it, I've added this task:
[D][N] CS3243 Assignment (by: 19 Feb 2020 0000)
Now you have 3 tasks in the list.
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
2. [E][N] Steamboat dinner (at: Bugis, 15 Mar 2020 8PM)
3. [D][N] CS3243 Assignment (by: 19 Feb 2020 0000)
```


### 3.5 Mark task as done: `done`
Marks a saved task as done. 

Format: `done INDEX`

- `INDEX`: positive number corresponding to the desired task (can be obtained from the `list` command as seen [here](#34-list-all-tasks-list))

**Example of usage:** 

`done 1`

**Expected outcome:**

```
Nice, I've marked this task as done:
[T][Y] CS2103 Quiz
```


### 3.6 Delete task: `delete`
Deletes saved task from Duke. 

Format: `delete INDEX`

- `INDEX`: positive number corresponding to the desired task (can be obtained from the `list` command as seen [here](#34-list-all-tasks-list))

**Example of usage:** 

`delete 1`

**Expected outcome:**


```
Noted. I've removed this task:
[T][Y] CS2103 Quiz
Now you have 2 tasks in the list.
```



### 3.7 Find tasks using a keyword: `find`
Lists out all tasks that contain a specific keyword.  
Note: keyword is case specific.

Format: `find KEYWORD`

- `KEYWORD`: any sequence of ASCII printable characters (for reference, click [here](https://en.wikipedia.org/wiki/ASCII#Printable_characters))

**Example of usage:** 

`find Assignment`

**Expected outcome:**


```
Here are the matching tasks in your list:
1. [D][N] CS3243 Assignment (by: 19 Feb 2020 0000) 
```


### 3.8 Reschedule a deadline or event: `snooze`
Changes the date and timing for a deadline or the details for an event.  
Note: todo tasks cannot be snoozed.

Format: `snooze INDEX /to TASK_FORMAT`

- `INDEX`: positive number corresponding to the desired task (can be obtained from the `list` command as seen [here](#34-list-all-tasks-list))
- The format for `TASK_FORMAT` must be appropriate for the type of task being rescheduled.
  - Case 1: Rescheduled task is a deadline. 
Format: `snooze INDEX /to DATE TIME`
(format details can be found [here](#32-add-an-event-task-event))
    

  - Case 2: Rescheduled task is an event. 
Format: `snooze INDEX /to DETAILS` 
(format details can be found [here](#33-add-a-deadline-task-deadline))

**Example of usage:** 

`snooze 2 /to 2020-03-16`

**Expected outcome:**

```
Got it, the updated deadline looks like:
[D][N] CS3243 Assignment (by: 16 Mar 2020 0000)
```

### 3.9 Exit Duke: `bye`
Exits the Duke Application.  
The Duke Application will close shortly after the command is entered.  
The buffer betweeen the entering of the command and the closing of the program is approximately 1 second to ensure the User can see Duke's response.

Format: `bye`

**Example of usage:**  
`bye`

**Expected outcome:**
```
Bye. Hope to see you again soon!
```


### 3.10 Storing Data
Data in duke is automatically saved onto the hard disk after the use of any command.  
(Even when the command cannot be executed!) 



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
