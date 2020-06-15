# User Guide
This is a brief introduction on how to use "Toki Toki Task Bot".


## What is Duke?
Duke is a task bot built to keep track of all your task and schedules.
Users can add and delete their tasks and Duke will keep track of all your task for you.

## Who is Yuri?
![Image of Yuki](https://github.com/Deunitato/duke/blob/master/src/main/resources/images/meanGirl.jpeg?raw=true)


Yuri is personality built on top of Duke, the original bot. Yuri aims to ensure that you as a user understand her pain of keeping track of all your task. 
Even though she is supposed to track the task for you, she will not hesitate to express her displeasure at your laziness and irresponsibility in handling your own to do list.

## Installing the Doki Doki Task Bot
Navigate to [github Releases](https://github.com/Deunitato/duke/releases) and download the latest release. Place the jar file into a new folder.
Opening the Jar file will show a welcome text from YURI.

![Image of startup](https://i.imgur.com/WZ1yERF.png)

## Features 

#### Create your own tasks
    1. Create todo tasks
    2. Create event tasks
    3. Create deadline tasks
#### Organising your task
    1. Listing
    2. Mark your tasks as done
    3. Find tasks
    4. Delete tasks

#### List your own tasks
Ask Yuri to display the task that you have to do

#### Check your statistics
Yuri will also keep track of things that you did within the day and week.

    1. Check the tasks done today/this week
    2. Check number of types done
    3. Check the task that expired

#### Tsundere Personality
Yuri will "hate" your laziness but still feel compelled to serve you as your personal secretary.


## Usage

Basic commands that Yuri will respond to.

### Creating your own tasks

#### `TODO` - Creates an todo task

Adds a TODO Tasks into your list

Example of usage: 

`Todo Read Book`

Expected outcome:

`sighssss...am I your slave again?`

#### `Event` - Creates an event task

Creates a event and adds it into your list
Yuri also requires the date of the event as denoted by /at

Example of usage: 

`event Anime Matsuri /at 12/01/2020`

`event Anime Matsuri /at 12/01/2020  1200`

`event Anime Matsuri /at 1200`

Expected outcome:

`sighsssss...am I your slave again?`

#### `Deadline` - Creates an deadline

Creates a deadline and adds it into your list
Yuri also requires the date of the event as denoted by /by

Example of usage: 

`event Homework /at 12/01/2020`

`event Homework /at 1200`

`event Homework /at 12/01/2020 1200`

Expected outcome:

`sighsssss...am I your slave again?`

### Organising your tasks

#### `list` - Shows your list bucket

Displays the list of task that you current have.

Example of usage: 

`list`


#### `Done` - Marks task as done

Marks the task signified by ID as done

Example of usage: 

`done 1`

Expected outcome:

`Okay whatever.. so you have completed this item.. so what? Marking item 1 as done `


#### `Find` - Searches for a task

Searches for tasks in your list

Example of usage: 

`find home`

Expected outcome:

`Found this task in total:`

` 1. [T][✘] Homework`

`Found in total 1 tasks`

#### `Delete` - Deletes your task

Totally remove all records of your tasks

Example of usage: 

`delete 1`

Expected outcome:

`Erasing your tracks are you?`

### Checking your statistics

### `Stats` - List out all the stats command

Shows the list of stats command you could use.
Examples of commands are
  1. `Expired` - list all the expired tasks
  2. `Week` - List the tasks you have completed in the week
  
Example of usage: 

`stats`

# Sample Screenshots of Yuri

## Adding Tasks

![Image of Add](https://i.imgur.com/4kyDYJv.png)

## Finding of Tasks
![Image of find](https://i.imgur.com/EsYhahP.png)

## Statistics 
![Image of Stats](https://i.imgur.com/8Je97lt.png)