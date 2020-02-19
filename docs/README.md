# Duke User Guide

### Introduction 
Duke is a personalized chatbot that enables user to store tasks in the form of todo, event and deadline tasks. This enables the user to organize his or her tasks in a neat way.

### Commands
* `todo` or `t` - Creates a todo task.
    
    Example: `todo read book`
    
* `event` or `e` - Creates an event task.

    Example: `event read book /by Sunday` or `event read book /by 2020-02-25`
    
    Note: `event` accepts date format in `YYYY-MM-DD`, and can convert the date to `DD MMM YYYY`.
    
* `deadline` or `dl` - Creates a deadline task.

    Example: `deadline read book /by Sunday` or `deadline read book /by 2020-02-25`
    
    Note: `event` accepts date format in `YYYY-MM-DD`, and can convert the date to `DD MMM YYYY`.
    
* `list` or `l` - Show tasks.

* `done` or `d` - Mark a task as done.

* `delete` or `del` - Delete a task.

* `find` or `f` - Find a keyword in your task list.

* `bye` `goodbye` - Saves task and exit application.

### Exceptions
If Duke cannot understand your message, Duke will reply the respective message to your command.

### Acknowledgement
Special thanks to @nus-cs2103-AY1920S2 for creating JavaFX tutorial!