<!-- omit in toc -->
# Duke - A User Guide

- [1. Introduction](#1-introduction)
- [2. Quick Start](#2-quick-start)
- [3. Features](#3-features)
- [4. Usage](#4-usage)
  - [4.1. `todo` Command](#41-todo-command)
  - [4.2. `event` Command](#42-event-command)
  - [4.3. `deadline` Command](#43-deadline-command)
  - [4.4. `list` Command](#44-list-command)
  - [4.5. `done` Command](#45-done-command)
  - [4.6. `delete` Command](#46-delete-command)
  - [4.7. `find` Command](#47-find-command)

## 1. Introduction

This program is really just a Todo list. 🤗

## 2. Quick Start

1. Ensure that you have Java 11 or above installed on your computer
2. Download the [latest release](https://github.com/AaronCQL/duke/releases)
3. Enter the command `java -jar duke-0.2.0.jar` and the GUI should appear within a few seconds

## 3. Features

Here is a list of the available commands that this program understands:

1. `todo`
2. `event`
3. `deadline`
4. `list`
5. `done`
6. `delete`
7. `find`

> Note that this program **is case-sensitive** (ie. `done` is interpreted differently to `Done` or `DONE`), and all **commands are lower cased**.

## 4. Usage

Commands (listed in section 3 above) must always come first for this program to correctly understand the command.

The keyword within the angle brackets indicate what you should replace them with. For example, when you see `done <number>`, replace the `<number>` with a suitable number like `done 2`.

### 4.1. `todo` Command

| Description |        Format        |
| :---------: | :------------------: |
| Adds a todo | `todo <description>` |

**Examples**

- `todo Do some work`
- `todo Really go do some work`

### 4.2. `event` Command

|  Description  |               Format                |
| :-----------: | :---------------------------------: |
| Adds an event | `deadline <description> /at <date>` |

> `<date>` must be of the form `YYYY-MM-DD`

**Examples**

- `event Piano exam /at 2020-10-20`
- `event Mum's birthday /at 2020-04-23`

### 4.3. `deadline` Command

|   Description   |               Format                |
| :-------------: | :---------------------------------: |
| Adds a deadline | `deadline <description> /by <date>` |

> `<date>` must be of the form `YYYY-MM-DD`

**Examples**

- `deadline Finish IP /by 2020-10-20`
- `deadline Procrastinate even more /by 1997-01-01`

### 4.4. `list` Command

|      Description       | Format |
| :--------------------: | :----: |
| Lists all stored tasks | `list` |

### 4.5. `done` Command

|     Description     |     Format      |
| :-----------------: | :-------------: |
| Mark a task as done | `done <number>` |

> Hint: the task number can easily be found by using the `list` command.

**Examples**

- `done 2`
- `done 13`

**Expected Outcome**

The program will confirm the marking of the correct task, if it exists.

### 4.6. `delete` Command

|  Description   |      Format       |
| :------------: | :---------------: |
| Deletes a task | `delete <number>` |

> Hint: the task number can easily be found by using the `list` command.

**Examples**

- `delete 2`
- `delete 13`

**Expected Outcome**

The program will confirm the deletion of the task, if it exists.

### 4.7. `find` Command

|                      Description                       |        Format        |
| :----------------------------------------------------: | :------------------: |
| Searches and lists all tasks similar to `<description>` | `find <description>` |

- `<description>` is case-sensitive
- Tasks are only searched and matched using the stored tasks description
  - ie. The `<date>` field stored in deadlines and events are not searched

**Examples**

- `find D`
  - Asks the program to search for all tasks whose description matches with `D`
- `find Get A+ for CS2103T`
  - Asks the program to search for all tasks whose description matches with `Get A+ for CS2103T`

**Expected Outcome**

The program will reply with the correct tasks, if any.
