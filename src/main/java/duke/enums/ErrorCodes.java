package duke.enums;

/**
 * Types of known error codes for DukeException.
 *
 * @author Firzan Armani
 */
public enum ErrorCodes {
    UNKNOWN_COMMAND,
    MISSING_TASK_NAME,
    MISSING_EVENT_DATE,
    MISSING_DEADLINE_DATE,
    INVALID_TASK_INDEX,
    INVALID_DATE_FORMAT,
    FILE_NOT_FOUND,
    LOADING_ERROR,
    SAVING_ERROR,
    INVALID_TAG,
    LIST_ONE_TAG
}