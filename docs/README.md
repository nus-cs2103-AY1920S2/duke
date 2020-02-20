# User Guide

## Features 

### Feature 1 
list

## Usage
Lists out the tasks currently contained in duke

### `Keyword` - Describe action

Example of usage: 

`list`

Expected outcome:

`Here are the tasks in your list: {Lists the tasks}`

or

`There are no tasks in your list.`

### Feature 2
delete

## Usage
Removes an existing task from the ones currently in Duke.

### `Keyword` - Describe action

Example of usage: 

`delete {index}`

Expected outcome:

`Noted. I've removed this task`

### Feature 3
find

## Usage
Finds an existing task from the ones currently in Duke.

### `Keyword` - Describe action

Example of usage: 

`find {query}`

Expected outcome:

`Here are the matching tasks in your list: {List of matching tasks}`

or

`No result found for this query.`

### Feature 4
done

## Usage
Completes an existing task from the ones currently in Duke.

### `Keyword` - Describe action

Example of usage: 

`done {index}`, where index is the index in the task from `list`

Expected outcome:

`Nice! I've marked this task as done: {task}`

### Feature 5
todo

## Usage
Adds a new [Todo] task to Duke

### `Keyword` - Describe action

Example of usage: 

`todo {desc}`

Expected outcome:

`Got it. I've added this task: {task}`

### Feature 6
deadline

## Usage
Adds a new [Deadline] task to Duke

### `Keyword` - Describe action

Example of usage: 

`deadline {desc} /by {date} {time}`, where date is in the yyyy-MM-dd format and time is in the HHmm 24hr format

Expected outcome:

`Got it. I've added this task: {task}`

### Feature 3
event

## Usage
Adds a new [Event] task to Duke

### `Keyword` - Describe action

Example of usage: 

`deadline {desc} /at {date} {time}`, where date is in the yyyy-MM-dd format and time is in the HHmm 24hr format

Expected outcome:

`Got it. I've added this task: {task}`