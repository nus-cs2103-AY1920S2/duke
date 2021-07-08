package duke.exceptions;

/**
 * An enums class to represent possible causes of exceptions in the Duke application
 */
public enum DukeError {
    NUMBER, // error in accessing list, no such task exists
    INSUFFICIENT, // error in creation of tasks, insufficient commands
    COMMAND, // error in command - no task of given type exists
    DATEFORMAT, // error in date given by the user - unable to parse
    KEYWORDS, // error when user providing keywords - too many keywords
    FILEPARSE, // error when parser is trying to read task from file
    TASKPARSE, // error when parser is trying to read task into a string
    UPDATE; // error when user is trying to update - eg. gives updating date
}
