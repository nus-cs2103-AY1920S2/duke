# User Guide

## 1. Features 

### 1.1. View tasks
View a list of all saved tasks.

## Usage

### `list`
Format: `list`

Example of usage: 

`list`

Expected outcome:

```
Here are your tasks:
1.[T][✓] homework #school
2.[D][✘] tasks (by: 24 Feb 2020) #cs2109
3.[E][✘] holiday (at: 1 Jun 2020) #money
```

---

### 1.2. Add todo task
Add a todo task to the saved tasks list.

## Usage

### `todo`
Format: `todo <description>`

Example of usage:

`todo homework`

Expected outcome:

```
Alright! The following task has been added:
    [T][✘] homework
You now have 1 task in the list.
```

---

### 1.3. Add event task
Add an event task to the saved tasks list.

## Usage

### `event`
Format: `event <description> /at <YYYY-MM-DD>`

Example of usage:

`event holiday /at 2020-06-01`

Expected outcome:

```
Alright! The following task has been added:
    [E][✘] holiday (at: 1 Jun 2020)
You now have 1 task in the list.
```

---

### 1.4. Add deadline task
Add a deadline task to the saved tasks list.

## Usage

### `deadline`
Format: `deadline <description> /by <YYYY-MM-DD>`

Example of usage:

`deadline assignment /by 2020-03-04`

Expected outcome:

```
Alright! The following task has been added:
    [D][✘] assignment (by: 4 Mar 2020)
You now have 1 task in the list.
```

---

### 1.5. Set task as done
Set the status of a task in the saved tasks list to done.

## Usage

### `done`
Format: `done <task number in list>`

Example of usage:

`done 1`

Expected outcome:

```
Noted. I have marked this task as done:
    [D][✓] assignment (by: 4 Mar 2020)
```

---

### 1.6. Delete task
Delete a task from the saved tasks list.

## Usage

### `delete`
Format: `delete <task number in list>`

Example of usage:

`delete 1`

Expected outcome:

```
Noted. I have removed this task:
    [D][✓] assignment (by: 4 Mar 2020)
You now have 0 tasks in the list.
```

---

### 1.7. Find tasks
Find tasks in the saved tasks list with descriptions containing the provided search term.

## Usage

### `find`
Format: `find <search term>`

Example of usage:

`search holiday`

Expected outcome:

```
These are the matching tasks in your list:
1.[E][✘] holiday (at: 1 Jun 2020)
```

---

### 1.8. Tag task
Tag a task in the saved tasks list with the provided tag.

## Usage

### `tag`
Format: `tag <task number in list> <tag>`

Example of usage:

`tag 1 money`

Expected outcome:

```
Added tag to this task:
    [E][✘] holiday (at: 1 Jun 2020) #money
```
