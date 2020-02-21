# Eevee User Guide
Eevee is your personal task manager, specially catered towards busy SoC students who need to record down their busy schedules.
![Image of Product](./Ui.png)

## Features 

* ### Adding of Tasks 
  Users are able to add tasks via specific commands as described below in *Usage*.

* ### Listing of Tasks
  Users are able to view the list of tasks in the storage as well as their respective status.

* ### Searching of Tasks
  Users are able to search for tasks in the storage and Eevee is able to find all tasks with partial matching keywords.
  
* ### Changing the Status of Tasks
  Users are able to mark tasks as done.

## Usage

* ### `list` - lists the tasks in the storage :page_facing_up:

  The list command provides users with a list of the tasks in the storage and their status.

  Example of usage:

  `list`

  Expected outcome:

  `Here are the tasks in your list:`<br />
  `1.[T][✗] read documentation`<br />
  
* ### `todo [name of task]` - adds a todo :closed_book:

  A ToDo is a task that does not have any date or time arguments. It is a simple and straightforward way to record down what you need to do.

  Example of usage: 

  `todo clean my room`

  Expected outcome:

  `Got it. I've added this task:`<br />
  `[T][✗] clean my room`<br />
  `Now you have 2 tasks in the list.`<br />

* ### `deadline [name of task] /by [YYYY-MM-DD HHMM]` - adds a deadline :green_book:

  A Deadline is a task that has a date and time argument. Users can record down tasks with deadlines.

  Example of usage: 

  `deadline return book /by 2020-03-12 1800`

  Expected outcome:

  `Got it. I've added this task:`<br />
  `[D][✗] return book (by: Mar 12 2020 1800)`<br />
  `Now you have 3 tasks in the list.`<br />

* ### `event [name of task] /by [YYYY-MM-DD HHMM-HHMM]` - adds an event :orange_book:

  An Event is a task that has a date, start time and end time argument. Users can record down tasks that will happen on a certain day as well as the duration of the event.

  Example of usage: 

  `event cs2103 finals /by 2020-04-25 1300-1500`

  Expected outcome:

  `Got it. I've added this task:`<br />
  `[E][✗] cs2103 finals (at: Apr 25 2020 1300 till Apr 25 2020 1500`<br />
  `Now you have 4 tasks in the list.`<br />

* ### `done [index]` - marks a task as done :ballot_box_with_check:

  Each task has a status assigned to it. When users are done with a task, they can choose to mark it as done.

  Example of usage: 

  `done 1`

  Expected outcome:

  `Nice! I've marked this task as done:`<br />
  `[T][✓] read documentation`<br />

* ### `delete [index]` - deletes a task :x:

  After users are done with a task, they can delete it to free up storage space.

  Example of usage: 

  `delete 1`

  Expected outcome:

  `Noted. I've removed this task:`<br />
  `[T][✓] read documentation`<br />
  `Now you have 3 tasks in the list.`<br />

* ### `find [keyword(s)]` - searches for matching tasks :mag:

  Users can filter out tasks according to the keywords.

  Example of usage: 

  `find study`

  Expected outcome:

  `Here are the matching tasks in your list:`<br />
  `[T][✗] study cs2103`<br />
  `[T][✗] study cs2106`<br />

## Acknowledgements
- Some parts of this sample application were inspired by the excellent [Java FX tutorial](http://code.makery.ch/library/javafx-8-tutorial/) by
  _Marco Jakob_.
- Libraries used: [JavaFX](https://openjfx.io/), [JUnit5](https://github.com/junit-team/junit5)
