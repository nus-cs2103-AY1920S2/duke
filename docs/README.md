# User Guide

## 1 What is Duke?

Duke is a personal assistant chatbot that helps to keep track of various tasks. This user guide provides detailed instructions and examples on how you can use Duke to manage your tasks.

## 2 How to use Duke?

Duke can keep track of various tasks for you. In order to use Duke, you have to key in specific commands that it can understand.

### 2.1 Adding a task

There are three types of tasks you can add:

- Todo: a task with a description
- Deadline: a task with a description and deadline
- Event: a task with a description and time

#### 2.1.1 `todo` - Adds a todo task

Usage:  
`todo [description]`

Example:  
`todo read book`

Expected outcome:  
`Got it. I've added this task:`  
&#8203;`    [T][✗] read book`  
`There is now 1 task in the list.`

#### 2.1.2 `deadline` - Adds a deadline task

Usage:  
`deadline [description] /by [date in yyyy-mm-dd format]`

Example:  
`deadline do homework /by 2020-06-30`

Expected outcome:  
`Got it. I've added this task:`  
&#8203;`    [D][✗] do homework (by: 30 Jun 2020)`  
`There are now 2 tasks in the list.`

#### 2.1.3 `event` - Adds an event task

Usage:  
`event [description] /at [time]`

Example:  
`event project meeting /at Monday 3pm`

Expected outcome:  
`Got it. I've added this task:`  
&#8203;`    [E][✗] project meeting (by: Monday 3pm)`  
`There are now 3 tasks in the list.`

### 2.2 Listing tasks

Usage:  
`list`

Example:  
`list`

Expected outcome: 

### 2.3 Finding tasks

Usage:  
`find [search term]`

Example:  
`find book`

Expected outcome:  

### 2.4 Marking a task as done

Usage:  
`done [task number]`

Example:  
`done 3`

Expected outcome:  
`Nice! I've marked this task as done:`  
&#8203;`    [E][✗] project meeting (by: Monday 3pm)`

### 2.5 Deleting a task

Usage:  
`delete [task number]`

Example:  
`delete 1`

Expected outcome:  
`Noted. I've removed this task:`  
&#8203;`    [T][✗] read book`

## Usage

### `Keyword` - Describe action

Describe action and its outcome.

Example of usage:

`keyword (optional arguments)`

Expected outcome:

`outcome`
