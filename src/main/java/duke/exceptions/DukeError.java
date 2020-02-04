package duke.exceptions;

/**
 * An enums class to represent possible causes of exceptions in the Duke application
 */
public enum DukeError {
    NUMBER, // error in accessing list, no such task exists
    INSUFFICIENT, // error in creation of tasks, insufficient commands
    COMMAND, // error in command - no task of given type exists
    DATEFORMAT, // error in date given by the user - unable to parse
    KEYWORDS // error when user providing keywords - too many keywords
}
