# User Guide 📖

## Table of Content
- [1. Introduction](#1-introduction)
- [2. Quick Start](#2-quick-start)
- [3. Features](#3-features)
  - [3.1 Create Todo](#31-create-todo)
  - [3.2 Create Event](#32-create-event)
  - [3.3 Create Deadline](#33-create-deadline)
  - [3.4 List](#34-list)
  - [3.5 Tags](#35-tags)
  - [3.6 Find Tasks](#36-find-tasks)
  - [3.7 Done](#37-done)
  - [3.8 Delete](#38-delete)
  - [3.9 Help Center](#39-help-center)
  - [3.10 Exit](#310-exit)
  
  
  
  

## 1. Introduction
Duke is a chatbot that deals with personal tasks management. With Duke, you are able to quickly create new and specific tasks just with your keyboard. If you are a fast typer, Duke is for you!

## 2. Quick Start
### Windows
If you are a Windows user, you may just double-click the .jar file, and fire away! 
### MacOS
If you are a MacOS user, head over to the Terminal, and change directory to the directory with the .jar file. Next, type in the terminal java `-jar duke-1.0.0.jar`. The program will start, and you can start chatting with Duke!

If you are unsure of the commands available for Duke, you can type and enter `help`.

## 3. Features 

### 3.1 Create `Todo`
Create a simple `todo` task, with its description.

```bash
todo read CS2103T textbook
```

### 3.2 Create `Event`
Create an `event`, where you should have a description, start time and date of event. Take note of the time and date format.

Time: `HH:mm`

Date: `dd/MM/yyyy`

```bash
event CS2103T project meeting /at 14:00 17/2/2020
```

### 3.3 Create `Deadline`
Create a `deadline` task, where you should have a description, end time and date of event. Take note of the time and date format.

Time: `HH:mm`

Date: `dd/MM/yyyy`

```bash
deadline CS2103T tutorial /by 23:59 20/2/2020
```

### 3.4 `List`
`List` out all your saved tasks.

```bash
list
```

### 3.5 `Tags`
Add `tags` to your tasks, so that you can categorize them, or search for them easily.

```bash
event CS2103T project meeting /at 14:00 17/2/2020 #tutorial #iP #duke #tP
```

### 3.6 `Find` tasks
`Find` tasks easily by putting in the `search word`, or the `date`.

```bash
find meeting
find 2/2/2020
```

### 3.7 `Done`
Mark specific tasks (indicated by their `index` on the list) as `done`.

```bash
done 3
```

### 3.8 `Delete`
`Delete` specific tasks (indicated by their index on the list) from the list.

```bash
delete 3
```

### 3.9 `Help` Center
Pull up the `help` center if you don't remember the commands.

```bash
help
```

### 3.10 `Exit`
Since its a chatbot, say `bye` to it!

```bash
bye
```
