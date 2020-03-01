# User Guide: DDDuke

## 1. Introduction

## DDDuke is an application for task management targeting at students and workers. 

## 2. Features  

- Quickly viewing all DDDuke's function
- Easily adding tasks into the task list
  - Task types: Todo, Deadline and Event
- Easily managing existing tasks 
  - Marking one task as done
  - Deleting one task
- Easily adding tags to tasks
- Quickly viewing all current tasks
- Smartly searching for keyword-contained tasks

## 3. Quick start

1. Ensure you have installed  [Java 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) 
2. Download the latest jar file to your local computer
3. Run the application in your terminal by command: `java -jar PATH_OF_DDDUKE`.
4. Or you can run the application by double click on the jar file.

## 4. Usage

#### 4.1 View all functions

`help`

- Show all the functions of DDDuke and how to construct a corresponding command
- Example of usage: `help`

#### 4.2 List all tasks

`list`

- List  all of tasks in the task list.
- Example of usage: `list`

#### 4.3 Add a todo task

`todo [task description]`

- A todo task will be added to the user's task list. Duke will respond with successful adding message.
- Example of usage: `add todo play with DDDuke`

#### 4.4 Add a deadline task

`deadline [task description] /by [yyyy-mm-dd]`

- A deadline task will be added to the user's task list. Duke will respond with successful adding message.
- Example of usage: `add deadline share DDDuke /by 2020-02-20`

#### 4.5 Add a event task

`event [task description] /at [yyyy-mm-dd]`

- An event task will be added to the user's task list. Duke will respond with successful adding message.
- Example of usage: `add event discussion about DDDuke /at 2020-02-21`

#### 4.6 Mark a task as done

`done [task number]`

- Mark an incomplete task as done. Duke will inform the user the change of status of that task.
- Example of usage: `done 1`

#### 4.7 Tag a task

`tag [task number] [tag]`

- A task will be tagged by the tags. Duke will inform the user the change of tags of that task.
- Example of usage: `tag 1 interesting`

#### 4.8 Delete a task

`delete [task number]`

- Delete a task from the task list. Duke will inform the user that if deletion is successful.
- Example of usage: ` delete 3`

#### 4.9 Find a task with a keyword

`find [keywords]`

- Duke will show the user the filtered tasks which contain the keyword.
- Example of usage: `find share`

#### 4.10 Exit the application

`bye`

- Duke will say goodbye to the user and exit the application.
- Example of usage: `bye`