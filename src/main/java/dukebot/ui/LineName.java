package dukebot.ui;

/**
 * Name of lines to say with Ui.sayLine.
 * The first word is usually the command in which the command appears in.
 * The second is usually the context in which the line is called.
 */
public enum LineName {
    ALIAS_ALREADY_EXISTS,
    ALIAS_COMMAND_FAIL,
    ALIAS_DOES_NOT_EXIST,
    DATE_TIME_PARSE_FAIL,
    DEADLINE_BY_MISSING,
    DEADLINE_EMPTY,
    DEFAULT_OUT_OF_INDEX,
    DELETE_EMPTY,
    DELETE_OUT_OF_INDEX,
    DONE_ALREADY,
    DONE_EMPTY,
    ERROR_PLACEHOLDER,
    EVENT_AT_MISSING,
    EVENT_EMPTY,
    EXIT,
    FIND_EMPTY,
    FIND_FAIL,
    FIND_SUCCESS,
    HELP,
    INVALID_COMMAND,
    LIST_EMPTY,
    LIST_EXISTS,
    LOAD_FAIL,
    NOT_A_NUMBER,
    NO_INPUT,
    RESCHEDULE_EMPTY,
    RESET_STORAGE_INIT,
    RESET_STORAGE_SUCCESS,
    RESET_STORAGE_FAIL,
    SAVE_FAIL,
    SAY_DUKE,
    TODO_EMPTY;
}