# User Guide
This guide covers the features of **DUKE** chatbot
## Overview 
1. Introduction

2. Features

3. Usage
    1. Adding a task
    
    2. Marking a task as done
    
    3. Displaying the task list
    
    4. Deleting a task
    
    5. Finding a task
    
    6. Tagging a task

### Introduction 
**DUKE** is a chatbot made for CS2103-T individual project. Its purpose is to help manage
daily tasks by maintaining a checklist of finished and unfinished task.

### Features
1. **Text input**: **DUKE** supports text input from the user. Simply type your command 
into the textbox and start communicating with **DUKE**.

1. **Tasks**: **DUKE** supports 3 different types of tasks:
    1. **ToDo**: These tasks do not have a date associated to them.

    2. **Deadline**: These tasks have a date associated to them.

    3. **Event**: These tasks have a date associated to them.
    
1. **Data saving**: In order to save data when booting, Duke will save a list 
of current tasks in the list in `duke.txt`.

1. **Tagging**: Duke supports tagging of the tasks in the list.
### Usage
1. Adding a task
    * `todo` - Add a **ToDo** task to the task list.
    
        * Format: `todo` `TASK_DESCRIPTION`
    
        * Example: `todo party` - Adds a `party` **ToDo** task to the task list.
    
    * `deadline` - Add a **Deadline** task to the task list.
     
        * Format: `deadline` `TASK_DESCRIPTION /by TASK_DATE`
  
        * Example: `deadline individual project` `/by` `2020-03-02` - Adds a 
        `individual project` **Deadline** task by **March 2, 2020** to the task list.
      
    *  `event` - Add a **Event** task to the task list.
     
        * Format: `event` `TASK_DESCRIPTION /at TASK_DATE`
        
        * Example: `event concert` `/by` `2020-03-28` - Adds a 
        `concert` **Event** task by **March 28, 2020** to the task list.

    **Note**: Please specify all `TASK_DATE` in the following format: **YYYY-MM-DD**.

2. Marking a task as done 
   * `done` - Mark a task in the task list as done.
   
        * Format: `done` `TASK_INDEX`
        
        * Example: `done` `3` - Marks task with index **3** in the task list as done.
        
   **Note**: `TASK_INDEX` starts from 1.

3. Displaying the task list
    * `list` - Display the current tasks in the task list.
    
        * Format `list`
        
    **Note**: This command would also show the status of the tasks in the task list
    (e.g. description, date, tag, status).
    
4. Deleting a task
   * `delete` - Delete a task in the task list.
   
        * Format: `delete` `TASK_INDEX`
        
        * Example: `delete` `6` - Deletes task with index **6** in the task list.
        
   **Note**: `TASK_INDEX` starts from 1.   

5. Finding a task
    * `find` - Finds a task with the specified description.
    
        * Format: `find` `TASK_DESCRIPTION`
        
        * Example: `find` `book` - Finds all tasks in the task list that contains
        **book** in the description.
        
    **Note**: `find` accepts partial `TASK_DESCRIPTION` in the input command, so 
    to find **homework**, you can type **home** instead.
    
6. Tagging a task
    * `tag` - Tags a task in the task list.
    
        * Format: `tag` `TAG_DESCRIPTION /at TASK_INDEX`
        
        * Example: `tag` `not happening /at 12` - Tags the tag at index 12 in the
        task list with **#nothappening**.
        
    **Note**: `tag` automatically removes the spaces in between the `TAG_DESCRIPTION`
    to match with the tagging system of popular social media platform (e.g. Facebook,
     Instagram, etc.)