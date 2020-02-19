# 1. Duke

**Introduction**

Duke is a Pyramid 480 Medical Class Operator aboard the TranStar research space station Talos I. As a TranStar researcher, your schedule is packed with continuous activities. Duke is one easy way for you to get your work activities organized and sorted!

**Quick Start**

![UI logo](/Ui.png)

1. Ensure you have Java 11 or later installed in your computer.
2. Download the latest `duke-2.0.jar` version of Duke
3. Double click the `jar` file to start Duke.
4. Type all commands in the text box and press enter for Duke to process them.

# 2. Features
## 2.1 List
Lists out all tasks.
Format: `list`

##2.2 Done
Marks a specified task as completed. 
Format: `list INDEX`

- Index is the specific index of each task, as shown when you `list` the tasks out using the `list` function above.  

##2.3 Delete
Deletes a specified task.
Format: `delete INDEX`

- Index is the specified index of each task, as shown when you `list` the tasks out using the `list` function above.

## 2.4 Find
Finds tasks based on certain keywords in their description.
Format: `find k/KEYWORD`

- Keyword must be 1 single word.

##2.5 Todo
Creates a new Todo task. 
Format: `todo d/DESCRIPTION`

- `Description` can be any number of words used to describe the `Todo` task.

##2.6 Deadline
Creates a new Deadline task.
Format: `deadline d/DESCRIPTION t/DATE`

- `Description` can be any number of words used to describe the `Deadline` task.
- `Date` must be in a `YYYY-MM-DD` format. 

##2.7 Event
Creates a new Event task. 
Format: `event d/DESCRIPTION t/DATE`

- `Description` can be any number of words used to describe the `Event` task.
- `Date` must be in a `YYYY-MM-DD` format. 

##2.8 Notes
Welcome to the TranScribe function! The TranScribe is a portable communication device developed by TranStar. It is capable of helping you busy scientists with note-taking.

###2.8.1 Documentation
Allows you to view help for the notes function. 
Format: `notes documentation`

###2.8.2 Viewing all notes
Allows you to view all notes taken down.
Format: `notes view`

###2.8.3 Displaying a note
Allows you to view a single note. This displays information such as the date of conception of the note, number of modifications since the date of inception and the last date and time of modification.
Format: `notes display INDEX`

- `INDEX` is the index of the specified note as shown in the `display` function when you `display` all notes.

###2.8.4 Modifying a note
Modifies the message of a single note. 
Format: `notes modify INDEX n/NEW_MESSAGE`

- `INDEX` is the index of the specified note as shown in the `display` function when you `display` all notes.
- `NEW_MESSAGE` is the new message which you want to change to.

###2.8.5 Adding a note
Adds a note.
Format: `notes add m/MESSAGE`

###2.8.6 Deleting a note
Deletes a note.
Format: `notes delete INDEX`

- `INDEX` is the index of the specified note as shown in the `display` function when you `display` all notes.

###2.8.7 Changing note title
Change the note title of the note.
Format: `notes INDEX n/NEW_NOTE_TITLE`

- `INDEX` is the index of the specified note as shown in the `display` function when you `display` all notes.
- `NEW_NOTE_TITLE` is the new title of the note.

##2.9 Bye
Exits Duke and closes the window.
Format: `bye` 

# Feedback, Bug Reports

* If you have feedback or bug reports, please post in [se-edu/duke issue tracker](https://github.com/se-edu/duke/issues).
* We welcome pull requests too.