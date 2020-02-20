# User Guide

## Features 
Summary of the features:
* `list`
* `deadline`
* `event`
* `todo`
* `done`
* `find`
* `bye`

### **Feature 1**
`list` : Lists all the tasks that you currently have.

Format: `list`

Example usage: `list`
Possible Expected Outcome:
```
    1. [T][✓] CS2103T Team Project
    2. [D][✘] CS3243 Project (by: 07 Mar 20)
    3. [E][✘] CS3243 Midterm (at: 07 Mar 20)
    4. [E][✘] CS3230 Midterm (at: 07 Mar 20)
```

### **Feature 2**
`deadline` : Adds a deadline task to the application.

Format: `deadline [name] /by YYYY-MM-DD`

Remarks:
* `[name]` can have spaces.

Example usage: `deadline CS2103T homework /by 2020-02-20`

Expected outcome: `I have added that deadline!`

### **Feature 3**
`event` : Adds an event task to the application.

Format: `event [name] /at YYYY-MM-DD`

Remarks:
* `[name]` can have spaces.

Example usage: `event CS3243 Midterm /at 2020-03-07`

Expected outcome: `I have added that event!`

### **Feature 4**
`todo` : Adds a todo task to the application.

Format: `todo [name]`

Remarks:
* `[name]` can have spaces.

Example usage: `todo Laundry`

Expected outcome: `I have added that todo!`

### **Feature 5**
`done`: Indicates that a task is done

Format: `done [index]`

Remarks:
* `[index]` is as per the index of the item when `list` is used.

Example usage: `done 1`

Expected outcome: `I have marked that task as done!`

When using `list`, this will change from: 
```
    1. [T][✘] CS2103T Team Project
```
to:
```
    1. [T][✓] CS2103T Team Project
```

### Feature 6
`find`: Finds the tasks which matches they keyword supplied to the command.

Format: `find [keywords]`

Remarks:
* `[keywords]` is case sensitive. For example, `CS2103T` will match `CS2103T`, but `cs2103t` will not match `CS2103T`.
* `[keywords]` can have multiple words.
* `[keywords]` does not have to be whole words. For example, finding `CS` will return `CS2103T` and `CS3243`.

Example usage: `find CS`

Possible expected outcome:
```
    1. [T][✓] CS2103T Team Project
    2. [D][✘] CS3243 Project (by: 07 Mar 20)
    3. [E][✘] CS3243 Midterm (at: 07 Mar 20)
    4. [E][✘] CS3230 Midterm (at: 07 Mar 20)
    5. [D][✘] CS2103T homework (by: 20 Feb 20)
```

### Feature 8
`bye`: Exits the application.

Format: `bye`

Example usage: `bye`

Expected outcome: `bye!`
