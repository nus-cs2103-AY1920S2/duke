# User Guide
1. Introduction
2. Quick Start
3. Features.    
3.1 Listing all tasks: list.    
3.2 Clearing all tasks: clear list.       
3.3 Marking a task as done: done.  
3.4 Finding a task through keyword: find.  
3.5 Deleting a task: delete.  
3.6 Adding a todo task: todo.  
3.7 Adding a deadline task: deadline.  
3.8 Adding an event task: event.  
3.9. Saving the data: exit.  
4. FAQ.  
5. Command Summary.  

## 1. Introduction
Duke is for those who prefer to use a desktop app for managing tasks. More importantly, Duke is optimized for those who prefer to work with a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Duke can help manage your busy schedule better than traditional GUI apps. Interested? Jump to the Section 2, “Quick Start” to get started. Enjoy!

## 2. Quick Start 
1) Ensure you have Java 11 or above installed in your Computer.

2) Download the latest duke.jar from the build/libs folder.

3) Copy the file to the folder you want to use as the home folder for your Duke app.

4) Double-click the file to start the app. The GUI should appear in a few seconds.

(https://github.com/SHAUNGOH97/duke/blob/master/docs/Ui.png?raw=true)

5) Type the command in the command box and press Enter to execute it.
e.g. typing help and pressing Enter will open the help window.

6) Some example commands you can try:

-list : lists all tasks

-todo play basketball: adds a todo task named play basketball.

-delete 3 : deletes the 3rd task shown in the current list

7) Refer to Section 3, “Features” for details of each command.


## 3. Features

# 3.1 Listing all tasks: list.  
Format: list.  

# 3.2 Clearing all tasks: clear list.  
Format: clear list.  

-All tasks, whether done or not done will be removed from list. 

-Spacing is required.  

# 3.3 Marking a task as done: done.  
Format: done NUMBER.  

-NUMBER must be a valid task number within the list (Must be a poistive integer). 

-Tasks that are already done will be left unchanged.  

# 3.4 Finding a task through keyword: find.  
Format: find KEYWORD.  

-Returns all tasks in the list that contains KEYWORD.

-KEYWORD can be an letter, word or integer.  

-KEYWORD search does not include status of tasks.  


# 3.5 Deleting a task: delete.  
Format: delete NUMBER.  

-Removes task NUMBER from the list, task numbers higher than NUMBER will be decremented by 1.  

eg. If task 1 is deleted, task 2 becomes task 1.  

-NUMBER must be a valid task number within the list (Must be a poistive integer).  

# 3.6 Adding a todo task: todo
Format: todo TASKNAME

-TASKNAME can be a letter, word or integer. Spaces are allowed within TASKNAME

eg. Playing basketball.  

# 3.7 Adding a deadline task: deadline.  
Format: deadline TASKNAME/DEADLINE.  

-TASKNAME can be a letter, word or integer. Spaces are allowed within TASKNAME. 

-'/' is required to separate task and deadline.  

-DEADLINE must be in yyyy-mm-dd format.  

# 3.8 Adding an event task: event.  
Format: event TASKNAME/DATE(T)TIME1-TIME2.   

-TASKNAME can be a letter, word or integer. Spaces are allowed within TASKNAME. 

-'/' is required to separate task and event date.  

-DATE must be in yyyy-mm-dd format.  

-'(' and ')' not included, 'T' is used to separate task date and time.  

-'-' is used to separate start time and end time.  

-TIME1: start time, TIME2: end time.  

-Format for TIME1-TIME2: hh:mm-hh:mm.  

# 3.9 Saving Data.  
Duke data are saved in the hard disk automatically after pressing the exit button.
There is no need to save manually.

## 4. FAQ
Q: How do I transfer my data to another Computer?   
A: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Duke folder.

## 5. Command Summary
-Listing all tasks: list. 

-Clearing all tasks: clear list.  

-Marking a task as done: done NUMBER. 
eg. done 2.  

-Finding a task through keyword: find KEYWORD.  
eg. find ball.  

-Deleting a task: delete NUMBER.  
eg. delete 3.  

-Adding a todo task: todo TASKNAME.  
eg. todo play basketball.  

-Adding a deadline task: deadline TASKNAME/DEADLINE.  
eg. deadline homework/2020-02-02.  

-Adding an event task: event TASKNAME/DATE(T)TIME1-TIME2.  
eg. event concert/2020-02-02T20:00-21:00.  




