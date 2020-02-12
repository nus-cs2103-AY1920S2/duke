package duke.util;

/**
 * The {@code Constants} class contains all magic constants.
 */
public class MagicStrings {
    public static final String BLANK = "";

    // Date time helper strings.
    public static final String DATE_TIME_OVERDUE = " [OVERDUE]";
    public static final String DATE_TIME_TODAY = "Today ";
    public static final String DATE_TIME_TOMORROW = "Tomorrow ";
    public static final String DATE_TIME_YESTERDAY = "Yesterday ";

    // Error messages
    public static final String ERROR_DEADLINE_MISSING_CONTENT = "Your deadline content cannot be empty! "
            + "Type help if you need help.";
    public static final String ERROR_DEADLINE_MISSING_DEADLINE = "I don't know when your deadline is! "
            + "Please use /by [deadline here].";
    public static final String ERROR_EVENT_MISSING_CONTENT = "Your event content cannot be empty! "
            + "Type help if you need help.";
    public static final String ERROR_EVENT_MISSING_TIME_FRAME = "I don't know when is your event! "
            + "Please use /at [time here].";
    public static final String ERROR_INDEX_OUT_OF_BOUNDS = "You're referring to a task which does not exist!";
    public static final String ERROR_INVALID_COMMAND = "I don't see what I can do with what you just told me.";
    //    public static final String ERROR_INVALID_FILE_PATH = "Your file path is invalid. A new JSON file will "
    //            + "be used for saving.";
    public static final String ERROR_FAIL_TO_LOAD = "Failed to load save file! Creating new save file.";
    public static final String ERROR_FAIL_TO_LOAD_AND_SAVE = "Failed to load save file! "
            + "You will also not be able to save.";
    public static final String ERROR_FAIL_TO_SAVE = "Facing difficulties saving your tasks right now.";
    public static final String ERROR_TASK_ALREADY_COMPLETED = "You have already completed this task!";
    public static final String ERROR_TODO_MISSING_CONTENT = "Your todo content cannot be empty! "
            + "Type help if you need help.";
    public static final String ERROR_WRONG_DATE_FORMAT = "Your input is of the wrong format.\n"
            + "\tType help to view the accepted formats.";

    // GSON helper strings.
    public static final String GSON_ATTR_DEADLINE = "deadline";
    public static final String GSON_ATTR_DESCRIPTION = "description";
    public static final String GSON_ATTR_IS_COMPLETED = "isCompleted";
    public static final String GSON_ATTR_TIME_FRAME = "timeFrame";
}
