package duke.exceptions;

public enum DukeError {
    NUMBER, // error in accessing list, no such task exists
    INSUFFICIENT, // error in creation of tasks, insufficient commands
    COMMAND;
}
