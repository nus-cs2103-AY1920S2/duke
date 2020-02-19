# User Guide for Edith

## Brief Description
Edith is a Personal Assistant Chatbot that helps a user keep track of various tasks.

![](edith.jpg)

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


<h3>How to executes those commands? </h3>

 - `Add` <br>
 There are 3 types of tasks that you can add. 
    - todo <br>
    `todo [description]` <br>
     Example: `todo borrow book`<br>
     Expected Output: 
     ![](todo_success_image.png)
                     
     <br>
     
    - deadline <br>
    `deadline [description] /by [YYYY-MM-DD]` <br>
    Example: `deadline return book /by 2020-02-19` <br>
    Expected Output: 
    ![](deadline_success_image.png)
                
    <br>
    
    - event <br>
    `event [description] /at [a location or time]` <br>
    Example: `event Endgame Movie /at Vivocity` or <br>
    `event return book /at 2020-04-20` <br>
    Expected Output: 
    ![](event_success_image.png)
                 
<br>

- `delete` <br>
    Removes a task by providing the task's index.
    Task number starts from 1. <br>
    Example: `delete 1` removes the task at index 1 <br>
    Expected Output: 
    ![](delete_success_image.png)
                   
    
<br> 

- `done` <br>
    Mark a task as completed by providing the task's index.
    Task number starts from 1. <br>
    Example: `done 2` mark task at index 2 as completed <br>
    Expected Output: 
    ![](done_success_image.png)
                

<br>

- `find` <br>
    Allows you to find all tasks with that given keyword in your listsOfTasks. <br>
    Example: `find book` returns you only tasks with "book" in its description <br>
    Expected Output: 
    ![](find_success_image.png)
                  

<br>

- `list` <br>
    Lists all your tasks.
    Example: `list` returns you all the tasks in your listOfTask <br>
    Expected Output: 
    ![](list_success_image.png)
          

<br>

- `high priorty` <br>
    Sets the task of this index as high priority so you will be able to view it easily.
    <br>
    Example: `highpriority 4` sets the task at index 4 as high priority. <br>
    Expected Output: 
    ![](highpriority_success_image.png)

    
    
<br>
 
- `list urgent task` <br>
    Lists **only** tasks that are marked as high priority <br>
    Example: `list urgent task` returns a list containing of tasks that are high priority.
    <br>
    Expected Output: 
    ![](listurgenttask_success_image.png)
     
                     
<br>    

- `bye` <br>
    Terminates the application <br>
    Example: `bye` exits the program 
    
    
    
## Acknowledgements
Credit to Jeffry Lum for the fxml and java files for JAVAFX GUI -  [JavaFx TutorialPart 4](https://github.com/nus-cs2103-AY1920S2/duke/blob/master/tutorials/javaFxTutorialPart4.md).  
Acknowledgements FastReader.java was adapted from https://www.javatips.net/api/AlgoDS-master/src/timus/FastReader.java. However, I tweaked some components to cater to my own needs.

Thanos Image for GUI is from https://www.theverge.com/2018/4/16/17243794/avengers-infinity-wars-thanos-origin-story-marvel

Tony Stark Image is from https://pngimage.net/tony-stark-png-5/

Edith Image is from https://www.amazon.com/Glasses-Sticker-Trucks-Laptop-NOK163/dp/B07VS9NPPL

