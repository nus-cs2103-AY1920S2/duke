package duke;

/**
 * Represents all the foreseen type of error Chatbot EXE will face.
 *
 * @author Kenny Ho
 */

public enum DukeErrorType {
    /**
     * Given command is invalid.
     */
    INVALID_COMMAND,
    /**
     * Given command abbreviation is invalid.
     */
    INVALID_ABBREVIATION,
    /**
     * File content format invalid
     */
    INVALID_FILE_CONTENT,
    /**
     * duke.task.Task do not have an description.
     */
    EMPTY_DESCRIPTION,
    /**
     * Given wrong date format.
     */
    EMPTY_TIME,
    /**
     * User give no input.
     */
    EMPTY_COMMAND;
}