# EXE User Guide
By: [Kenny Ho](https://github.com/khsc96) Since: Jan 2020

##1. Introduction
EXE is your very own personal assistant chatbot. EXE will always be there for you if you need someone to voluntarily 
record and remind you on your own personal tasks. 
<br>
<br>
Tasks can be categorised as Todo task, event task, or deadline
tasks. You can add, delete, mark as done and list down all your tasks. Want to further know what EXE can do for you?
Press [here](#features)
##2. Features 
**Command format**
<br>
<div style="background-color: lightskyblue">
    <ul>
        <li>
            Words in <span style="background-color: lightgray">UPPER_CASE</span> 
            are the parameters to be supplied by the user. e.g in todo 
            <span style="background-color: lightgray">TASK_DESCRIPTION</span>, 
            <span style="background-color: lightgray">TASK_DESCRIPTION</span> is a parameter you need to type in. 
            Example is given below.
        </li>
        <li>
            Words in <span style="background-color: lightgray">lower_case</span> 
            are parameters that are optional, keying it will result in more specific command being 
            executed.
        </li>
        <li>
            Parameters must be in order e.g, if the command feature specifies 
            <span style="background-color: lightgray">command TASK_DESCRIPTION /at TIME</span> such order must be 
            maintained for EXE to recognised command.
        </li>
    </ul>
</div>

### 2.1. Adding Todo task 
This feature adds a given task that is categorised as todo into the list of your existing task.
<br>
> Format: todo TASK_DESCRIPTION
> <br>
> e.g: todo My CS2103T iP

For visual example click [here](3.1 Add todo task usage).
 

### 2.2 Adding Event task
This feature adds a given task that is categorised as event into the list of your existing task.
<br>
> Format: event TASK_DESCRIPTION /at YYYY-MM-DD
> <br>
> e.g: event attend CS2103T lecture /at 2020-02-02

For visual example click [here](3.2 Add event task usage).

### 2.3 Adding Deadline task
This feature adds a given task that is categorised as deadline into the list of your existing task.
<br>
> Format: deadline TASK_DESCRIPTION /by YYYY-MM-DD
> <br>
> e.g: deadline finish my CS2103T iP /by 2020-02-02

For visual example click [here](3.3 Add deadline task usage).
### 2.4 Deleting existing task
This feature deletes one of your existing task from your list of tasks.  
> Format: delete TASK_NUMBER
> <br>
> e.g: delete 3

For visual example click [here](3.4 Deleting tasks).
### 2.5 Exiting the application
This feature give you an option to quit the application and enjoy the pretty goodbye message.
> Format: bye

To see the pretty goodbye message please click [here](3.5 Exiting application). 

### 2.6 List out all existing tasks
This feature allows you to see all your current existing tasks and will tell you how many tasks you have currently.
> Format: list

For visual example of how list command will look like click [here](3.6 Listing out all tasks).

### 2.7 Marking task as done
This feature allows you to change the status of a task from undone to done. 
Done tasks are denoted by a smiley face `:)`. While tasks that is undone are denoted by a 
sad face `:(`.

>Format: done TASK_NUMBER>
><br>
>e.g. done 3

For visual help please click [here](#3.7 Marking task as done)
### 2.8 Viewing help menu
This feature shows you all command EXE can execute. Also, it can show you specific command format.
>Format: help [COMMAND_NAME]

For a better understanding of how help command works please click [here](3.8 Help menu)


## 3. Usage
Below are pictures depicting all the scenarios that you should encounter when using EXE.
### 3.1 Add todo task usage
*input:* `todo I want to run`
<br><br>
EXE will add `I want to run` as a todo task
<br><br>
*Expected outcome:*

![Image of todo task expected output](images/todo_image.PNG)

### 3.2 Add event task usage
*input:* `event attend CS2103T lecture /at 2020-02-02`
<br><br>
EXE will add `attend CS2103T lecture` as an event task
<br><br>
*Expected outcome:*

![Image of event task expected output](images/event_image.PNG)
### 3.3 Add deadline task usage
*input:* `deadline finish my CS2103T iP /by 2020-02-02`
<br><br>
EXE will add `finish my CS2103T iP` as an deadline task
<br><br>
*Expected outcome:*

![Image of deadline task expected output](images/deadline_image.PNG)

### 3.4 Deleting tasks
*input:* `delete 2`
<br><br>
EXE will delete task number 2 in the task list which is `project meeting by: Feb 02 2020`.
<br><br>
*Expected outcome:*

![Image of delete task expected output](images/delete_image.PNG)
### 3.5 Exiting application
*input:* `bye`
<br><br>
EXE will exit the application while showing the goodbye message.
<br><br>
*Expected outcome:*

![Image of exit expected output](images/bye_image.PNG)

### 3.6 Listing out all tasks
*input:* `list`
<br><br>
EXE will list out all the existing tasks you have.
<br><br>
*Expected outcome:*

![Image of list expected output](images/list_image.PNG)

### 3.7 Marking task as done
*input:* `done 3`
<br><br>
EXE will mark task 3 in the list to done, which is from a `:(` to a `:)`.
<br><br>
*Expected outcome:*

![Image of done expected output](images/done_image.PNG)
### 3.8 Help menu
**Example 1** 
<br><br>
*input:* `help`
<br><br>
EXE will list out all commands it can execute to help you better communicate with it.
<br><br>
*Expected outcome:*

![Image of help expected output](images/help_image.PNG)

**Example 2**
<br><br>
*input:* `help event`
<br><br>
EXE will show the format of what event command need to be in.
<br><br>
*Expected outcome:*

![Image of help expected output](images/help_2_image.PNG)

### Acknowledgements 


