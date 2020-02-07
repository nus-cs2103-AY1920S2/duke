package dukebot.ui;

/**
 * Name of lines to say, to be used with ui.sayLineWithTask.
 * The first word is usually the command in which the command appears in.
 * The second is usually the context in which the line is called.
 */
public enum LineNameWithTask {
    DELETE_SUCCESS,
    DONE_SUCCESS,
    ERROR_PLACEHOLDER,
    NEW_TASK_SUCCESS,
    RESCHEDULE_BAD_TASK,
    RESCHEDULE_SUCCESS;
}
