![Duke UI](Ui.png)

# Duke User Guide

## Contents
1. Overview

2. Starting Duke

3. Features Overview

4. Usage

    4.1. Add a Task

        4.1.1. todo - Add a todo task
        
        4.1.2. deadline - Add a deadline task
        
        4.1.3. event - Add a event task
        
    4.2. Mark a Task as Done
    
    4.3. Delete a Task
    
    4.4. List all Tasks
    
    4.5. Find by Keyword
    
    4.6. Add Tag to a Task
    
    4.7. Exit Duke

## 1. Overview
Duke is a personal chatbot, meant to keep track of *your* personal tasks! For this particular chatbot, the overall themeing is a nod to Transistor by Supergiant Games. Images are taken from official in-game and promotional artworks

## 2. Starting Duke
1. Download and locate the Duke program's jar file in File Explorer.
2. Open command line (via typing "cmd" in Windows search). Alternatively, run Terminal if you are on MacOS.
3. Locate the jar file in command line or Terminal, and run the following command:
    > java -jar duke-0.2.0.jar

## 3. Features Overview
1. Add a Task
2. Mark a Task as Done
3. Delete a Task
4. List all Tasks
5. Find by Keyword
6. Add Tag to a Task
7. Exit Duke
 
## 4. Usage
### 4.1.  Add a Task
There are three types of tasks available, with the following commands:
1. todo
2. deadline
3. event

### 4.1.1. `todo` - Add a todo task
A to-do task is one with only task details.

Format: `todo <description>`

Example of usage:`todo retrieve the Transistor`

Expected outcome: ![Todo UI](Todo.png)

### 4.1.2. `deadline` - Add a deadline task
A deadline task is one with task details as well as a "do by" date. Note that the backslash "/" is required.

Format: `deadline <details> </yyyy-mm-dd>`

Example of usage: `deadline Climb Bracket Towers /2020-05-20`

Expected outcome: ![Deadline UI](Deadline.png)

### 4.1.3. `event` - Add an event task
An event task is one with task details as well as a "do at" date. Note that the backslash "/" is required.

Format: `event <details> </yyyy-mm-dd>`

Example of usage: `event Performance at the Empty Set/2020-05-18`
Expected outcome: ![Event UI](Event.png)

### 4.2. Mark a Task as Done
By providing an index, the task with that index will be marked as done, indicated with a tick symbol.

Format: `done <index>`

Example of usage: `done 2`

Expected outcome: ![Done UI](Done.png)

### 4.3. Delete a Task
By providing an index, the task with that index in the task list will be deleted.

Format: `delete <index>`

Example: `delete 2`

Expected outcome: ![Delete UI](Delete.png)

### 4.4. List All Tasks
Lists out all tasks currently in Duke, along with a reference index.

Format: `list`

Expected outcome: ![List UI](List.png)

### 4.5. Find by Keyword
By providing a keyword, all tasks containing that keyword will be found and listed out.

Format: `find <description>`

Example: `find Towers`

Expected outcome: ![Find UI](Find.png)

### 4.6. Add Tag to a Task
By providing an index and a phrase, the task with that index will be tagged with the phrase.

Format: `tag <index> <tag details>`

Example: `tag 4 sea monster`

Expected outcome: ![Tag UI](Tag.png)

### 4.7. Exit Duke
Once you're done, you can exit Duke. Any task data in Duke will be saved, so next time you run Duke you can continue where you left off!

Format: `bye`

Expected outcome: ![Bye UI](Bye.png)



