# User Guide
Shinobu is designed to be your personal assistant, allowing you to plan and track your daily tasks in a simple and intuitive manner!
This is a quick guide on the features that Shinobu has and the respective commands needed to utilize these features. Have fun!

## Features 

### Feature 1 - *Adding To-Do task*
This allows you to add a To-Do task into the list of tasks that Shinobu is tracking for you! 
A To-Do task does not require an accompanying timing for the task to be done by.

### Usage

#### *todo eventDescription*

Add a To-Do tasks that is to be tracked into an existing list of tasks. 
Output should be a confirmation message of a successful addition, followed by the
total number of tasks in your current list.

Example of usage: 

 `todo Hunt For Demons`

Expected outcome:

`Got it! I've added this task:`

`Hunt for Demons`

`Now you have 1 task in the list`

### Feature 1 - *Adding Deadline task*
This allows you to add a deadline into the list of tasks that Shinobu is tracking for you! 
A Deadline task requires an accompanying timing for the task to be done by, else the task will not be added.

### Usage

#### *deadline eventDescription /by eventTiming*

Add a deadline tasks that is to be tracked into an existing list of tasks. 

*eventTiming* needs to be in YYYY-MM-DD format, else Shinobu will not accept the entry!

Output should be a confirmation message of a successful addition, followed by the
total number of tasks in your current list.

Example of usage: 

 `deadline Hunt For Demons /by 2020-12-30`

Expected outcome:

`Got it! I've added this task:`

`Hunt for Demons`

`Now you have 1 task in the list`