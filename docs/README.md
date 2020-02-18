# Duke User Guide

## 1. Features

### 1.1. View all tasks
View a list of all saved tasks.

## Usage

### `list`
Format: `list`

Example of usage:

`list`

Expected outcome:

```
Duke: Here's what I've written down, boss.
      1. [T][✓] Todo 1
      2. [D][✓] Deadline 1 (by: Feb 3 2020)
      3. [E][✗] Event 1 (at: May 1 2020)
```

---

### 1.2. Add a Todo
Add a todo task to the list of saved tasks.

## Usage

### `todo`
Format: `todo <task description>`

Example of usage:

`todo homework`

Expected outcome:

```
Duke: Got it, boss. I'll write this one down

      [T][✗] homework

      Now you have 1 task in the list
```

---

### 1.3. Add an Event
Add an event task with event date to the list of saved tasks.

## Usage

### `event`
Format: `event <description> /at <Event date in YYYY-MM-DD>`

Example of usage:

`event My birthday /at 2020-02-26`

Expected outcome:

```
Duke: A special event I see. Don't worry boss, I'll remind you

      [E][✗] My birthday (at: Feb 26 2020)

      Now you have 2 tasks in the list
```

---

### 1.4. Add a Deadline
Add a deadline task to the list of saved tasks.

## Usage

### `deadline`
Format: `deadline <description> /by <Deadline in YYYY-MM-DD format>`

Example of usage:

`deadline Group project /by 2020-02-25`

Expected outcome:

```
Duke: Oooh, important deadline eh, boss? Don't worry, I got it

      [D][✗] Group project (by: Feb 25 2020)

      Now you have 3 tasks in the list
```

---

### 1.5. Delete task
Delete a task from the list of saved tasks.

## Usage

### `delete`
Format: `delete <Task number>`

Example of usage:

`delete 1`

Expected outcome:

```
Duke: Aaaaand deleted! Don't kill me if it's the wrong one, boss

      [T][✗] homework

      Now you have 2 tasks in the list
```

---

### 1.6. Mark task as done
Set a specified task in the list of saved tasks, to done.

## Usage

### `done`
Format: `done <Task number>`

Example of usage:

`done 1`

Expected outcome:

```
Duke: Got it boss! Just to confirm, this is the one I marked as done

      [D][✓] Group project (by: Feb 25 2020)

      Now you have 2 tasks in the list
```

---

### 1.7. Find tasks
Find tasks with descriptions that contain the specified keyword, in the list of saved tasks.

## Usage

### `find`
Format: `find <Keyword to find>`

Example of usage:

`search group`

Expected outcome:

```
Duke: I found these tasks that match what you might be looking for, boss!

      1. [D][✓] Group project (by: Feb 25 2020)
```
