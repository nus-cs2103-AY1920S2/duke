# User Guide
1. Introduction
2. Features

## Introduction
![Image of ChuChu Bot](https://raw.githubusercontent.com/XinPei-ng/duke/master/docs/Ui.png)

ChuChu Bot is a task management bot built on Duke. It is 
capable of managing different kinds of task and also has various
features such as add and delete.

## Features 

### 1. Add new task

 **Duplicate task will not be added**
- There are 3 kinds of task:
   1. todo: task with no date and time attached
   2. deadline: task with an end date and time
   3. event: tasks with a start and end date and time
   

- *todo* - add todo task

      todo [task description]

- *deadline* - add deadline task

      deadline [task description] /by [d/MM/yyyy HH:mm]
      **avoid** using /by in task description
      
      
- *event* - add event task
     
      event [task description] /at [d/MM/yyyy HH:mm to d/MM/yyyy HH:mm]
      **avoid** using /at in task description
  
### 2. Delete task

- Deletes task at the specified index
 
      delete [index of task]
    
### 3. Find task
   
- Finds all task with matching keyword
   
      find [keyword]

### 4. List task

- Lists all tasks that the bot is managing

      list
    
### 5. Mark task as done

- Updates the status of a task at specified index from undone to done

      done [index of task]

### 6. Exit

- Terminates the application

      bye 
  
     


