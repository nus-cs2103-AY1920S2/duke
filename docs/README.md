# Hooman 
# User Guide
You're a cat and this is your *hooman* who will serve your every needs possible.  Here are some basic features this hooman can help you with.
## Features 

### Adding To-do Task 
Adds a to-do task. 
### Adding Task with a Deadline 
Adds a to-do task that has a deadline.
### Adding Event 
Adds an event with a span of timing.
### Showing a List of your tasks
Shows your entire tasklist.
### Completing Task
Completes a task.
### Deleting Task
Removes a task from your tasklist.
### Finding Task
Search for all task relating to a keyword.
### Terminate Program
Goodbye.

## Usage

### `todo` 
Key in `todo` and then whatever ridiculous task you desire.
E.g. 
```
todo meow
```
Expected outcome:
```
added: meow
You have 1 task on your list.
```

### `deadline`
Key in `deadline`, the task, and then your deadline in the format `YYYY-MM-DD`.
E.g. 
```
deadline buy cat food /by 2020-03-04
```
Expected outcome:
```
added: buy cat food
You have 1 task in your list.
```

### `event` 
Key in `event`, the event description and the timespan. 
E.g. 
```
event play with me /from 4-6pm
```
Expected outcome:
```
added: play with me
You have 1 task in your list.
```

### `list`
Type `list` & your tasklist will show up.
E.g.
```
todo eat
deadline feed me /by 2020-01-01
event play with me /from 4-6pm
list
```
Expected outcome:
```
1.[T][Incomplete] eat
2.[D][Incomplete] feed me (by: 01 01 2020)
3.[E][Incomplete] play with me(from: 4-6pm)
```

### `done`
Type `done` followed by a task number to complete that specific task.
E.g.
```
todo eat
deadline feed me /by 2020-01-01
todo sleep
done 2
list
```
Expected outcome:
```
//"Feed me" task completed
1.[T][Incomplete] eat
2.[D][Finished] feed me (by: 01 01 2020)
3.[T][Incomplete] sleep
```

### `delete` 
Type `delete` followed by a task number to delete that task.
E.g.
```
event hunt for birds /from 8-9am
deadline feed me /by 2020-01-01
todo sleep
delete 1
list
```
Expected outcome:
```
//"hunt for birds" event removed
1.[D][Finished] feed me (by: 01 01 2020)
2.[T][Incomplete] sleep
```

### Finding Task
Key in `find` and a keyword of what you wish to find. A list of task based on the keyword will be printed.
```
todo eat catfood
event lepak \from 12-8am
event eat fur \from 10am - 12pm
todo scratch post
todo chew & scratch on hooman's couch
todo eat mice
done 3
find eat
```
Expected outcome:
```
1.[T][Incomplete] eat catfood
2.[E][Finished] eat fur (from: 10am - 12pm)
3.[T][Incomplete] eat mice

```

### Terminate Program
Type `bye` to terminate your program.
