# User Guide for Edith

## Brief Description
Edith is a Personal Assistant Chatbot that helps a user keep track of various tasks.

## Usage

### Key Commands for Edith: 

1. **Add** a task

2. **Delete** a task

3. **Done** mark a task as completed

4. **Find** tasks with that given keyword

5. **List** out all tasks

6. **High Priority** set a task as high priority

7. **List urgent task** lists all the high priority tasks

8. **Bye** exits jarvis


###How to executes those commands ? 

 - `Add` <br>
 There are 3 types of tasks that you can add. 
    - todo <br>
    `todo [description]` <br>
     Example: `todo borrow book`
     Expected Output: ![](todo_success_image.png)
                      Picture: todo Success Image
     
    - deadline <br>
    `deadline [description] /by [YYYY-MM-DD]` <br>
    Example: `deadline read book /by 2020-04-11`
    Expected Output: ![](deadline_success_image.png)
                     Picture: deadline Success Image
    
    - event <br>
    `event [description] /at [a location or time]` <br>
    Example: `event return book /at library` or <br>
    `event return book /at 2020-04-20`
    Expected Output: ![](event_success_image.png)
                     Picture: event Success Image
    
- `delete` <br>
    Removes a task by providing the task's index.
    Task number starts from 1. <br>
    Example: `delete 1` removes the task at index 1
    

- `done` <br>
    Mark a task as completed by providing the task's index.
    Task number starts from 1. <br>
    Example: `done 2` mark task at index 2 as completed
    
- `find` <br>
    Allows you to find all tasks with that given keyword in your listsOfTasks. <br>
    Example: `find book` returns you only tasks with "book" in its description
    
- `list` <br>
    Lists all your tasks.
    Example: `list` returns you all the tasks in your listOfTasks
    
- `high priorty` <br>
    Sets the task of this index as high priority so you will be able to view it easily.
    <br>
    Example: `highpriority 4` sets the task at index 4 as high priority. 
    
    
- `list urgent task` <br>
    Lists **only** tasks that are marked as high priority <br>
    Example: `list urgent task` returns a list containing of tasks that are high priority.
  
- `bye` <br>
    Terminates the application <br>
    Example: `bye` exits the program 


