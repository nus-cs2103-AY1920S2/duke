# User Guide

## Features 

### Add `todo`
Adds a `todo` item to the list

### Usage

#### `todo <desc>`

Example of usage:

`todo read book`

---

### Add `deadline`
Adds a `deadline` to the list with its associated deadline

(Optional: deadline time)

### Usage

#### 1. `deadline <task> /by <YYYY-MM-DD of deadline>`
#### 2. `deadline <task> /by <YYYY-MM-DD of deadline> <HH:mm>`
###### Note: Input time in 24h format

Example of usage:

1. `deadline finish homework /by 2020-02-20`

2. `deadline finish homework /by 2020-02-20 18:05`

---

### Add `event`
Adds an `event` to the list with its associated event date

(Optional: event start time and/or event end time)

### Usage

#### 1. `event <desc> /at <YYYY-MM-DD of event>`
#### 2. `event <desc> /at <YYYY-MM-DD of event> <start HH:mm>`
#### 3. `event <desc> /at <YYYY-MM-DD of event> <start HH:mm> to <end HH:mm>`
###### Note: Input time in 24h format

Example of usage:

1. `event friend's birthday /at 2020-02-20`

2. `event friend's birthday /at 2020-02-20 16:00`

3. `event friend's birthday /at 2020-02-20 16:00 to 20:00`

---

### Mark as `done`
Marks item(s) on the list as `done`

### Usage

#### `done <indices of item(s) to mark as done>`

Example of usage:

1. `done 2` (marks the 2nd item as done)

2. `done 1 3 4` (marks the 1st, 3rd, and 4th items as done)

---

### `delete` item
Deletes item(s) from the list

### Usage

#### `delete <indices of item(s) to delete>`

Example of usage:

1. `delete 2` (deletes the 2nd item)

2. `delete 1 3 4` (deletes the 1st, 3rd, and 4th items)

---

### Display `list`
Displays the list of `todo`, `deadline`, and `event` items

(Optional: /showtimer or /hidetimer option to toggle
 countdown timer to the deadline or event)

### Usage

#### 1. `list`

#### 2. `list /showtimer`

#### 3. `list /hidetimer`

---

### `find` an item
Searches for an item on the list

### Usage

#### `find <keyword(s)>`

Example of usage:

1. `find play`

2. `find read book`

---

### Call for `/help`
Displays the list of commands

### Usage

#### `/help`