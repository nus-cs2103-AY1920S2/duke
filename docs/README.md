# User Guide of Duke Chatbot Made by Agnes Natasya
Duke is a desktop chatbot that can keep track of your tasks, including its status and time, and eventually storing it in your computer. It is designed to be compatible for all Operating Systems.

## Features 

### Duke GUI 
The chatbot has an aesthetic and user-friendly GUI. It will greet you when you first start it. For every command that you put in the text field of the chat, you can either just 'Enter' or click the 'Send' button and Duke will process the input from the user.
<br>
<img src="Ui.png" width=30% display=block>
<br>
There is a scrollbar on the right side of the window. When the chat gets long and oy exceeds the window size, you can use the scrollbar to see the previous chat that you and Duke has had. 

### Storing Task 
When you first used Duke, there is no data of tasks that Duke has stored yet. Duke will create one for you in your own laptop, it will create a file that keeps track of the data of your tasks even after you close Duke itself.

When you are using Duke and you create some changes to your tasks, for example adding a new task, updating the status of the old task, Duke will update the date of your tasks.

If it is not your first time to run Duke, Duke will store the updated data of your tasks to the data file that has been created previously. 

### Loading Task 
When you first used Duke, Duke will load blank data from a blank file that has just been created (i.e. there is no data of your tasks yet). 

If it is not your first time to run Duke, Duke will load all the the data of your tasks from your computer. Thus, thedata of your tasks still includes those that you have stored in your previous sessions with Duke.

### Listing Task 
You can ask Duke to _list_, it will list down all the current tasks that you have.

### Finding Task 
You can ask Duke to _find_ a certain _keyword_, it will list down all the tasks that have this _keyword_ inside it.

### Schedule Task 
You can ask Duke to list down the _schedule_ for a certain _day_, it will list down all the event and deadline tasks that you have this on that _day_.

### Adding Tasks (Todo, Event, Deadline)
You can ask Duke to _add_ 3 different types of tasks, **todo**, **event**, and **deadline**.
When you add an **event**, and **deadline** tasks, you need to specify when will this event happen and when is the deadline of the event, respectively.  

### Updating Task (Changing it to Done) 
You can ask Duke to update a certain task to _done_. It will mark this task as being completed.

### Updating Task (Deleting it)
You can ask Duke to _delete_ a certain task, it will list delete this task from the current tasks list.

### Error Task 
When you ask duke to do things that are out of his intelligence, it will say that it doesn't understand this command.

## Usage

### Use the keyword `list` for listing task
 
Example of usage: 

`list`

It will list down all of the tasks that are currently in your tasks list.

Example of outcome:

`Here are the tasks in your list:
 1.[D][x] return book (by: Dec 2 2019 06:00 PM)
 2.[T][x] borrow pencil
`

### Use the keyword `find` for finding task
 
Example of usage: 

`find book`

It will list down all of the tasks that has _book_ keyword inside it.

Example of outcome:

`Here are the matching tasks in your list:
 1.[D][x] return book (by: Dec 2 2019 06:00 PM)
`


### Use the keyword `schedule` for listing task at a certain date
 
Example of usage: 

`schedule /at 2/12/2019`

It will list down all of the tasks that are currently in your tasks list.

Example of outcome:

`Here are the tasks in your list:
 1.[D][x] return book (by: Dec 2 2019 06:00 PM)
`

### Use the keyword `todo` for adding a todo task
 
Example of usage: 

`todo borrow book`

It will give a confirmation message that it has added this task to your list.

Example of outcome:

`Got it. I've added this task:
 [T][x] borrow book
`

### Use the keyword `deadline` for adding a deadline task
 
Example of usage: 

`deadline return book /by 2/12/2019 1800`

It will give a confirmation message that it has added this task to your list.

Example of outcome:

`Got it. I've added this task:
 [D][x] return book (by: Dec 2 2019)
`

### Use the keyword `event` for adding a deadline task
 
Example of usage: 

`event meeting /at 2/12/2019 1800`

It will give a confirmation message that it has added this task to your list.

Example of outcome:

`Got it. I've added this task:
 [E][x] meeting (at: Dec 2 2019)
`

### Use the keyword `done` for marking a task as done
 
Example of usage: 

`done 2`

It will give a confirmation message that it has updated the status of task number 2.

Example of outcome:

`Nice! I've marked this task as done:
 [T][x] read book
`

### Use the keyword `delete` for delete a task from the list
 
Example of usage: 

`delete 2`

It will give a confirmation message that it has updated the status of task number 2.

Example of outcome:

`Noted. I've removed this task:
 [T][x] read book
 Now you have 5 tasks in the list.
`

