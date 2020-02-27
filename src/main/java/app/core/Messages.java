package app.core;

public class Messages {
    public static final String WELCOME_MESSAGE = "Hey, I'm Janet!\nHow may I help you, Chidi?";
    public static final String GOODBYE_MESSAGE = "Bye Chidi!\nEnjoy your stay in The Bad Place! (hehe)";
    public static final String UNEXPECTED_ERROR_MESSAGE = "Oh No! sOmetHinG bAD is hAppeNing!!!\nCALL MICHAEL (or any developer)!";
    
    // CommandManager
    public static final String EMPTY_COMMAND_MESSAGE = "The command should not be empty";
    public static final String UNSUPPORTED_COMMAND_MESSAGE = "The command '%s' is not supported";
    
    public static final String DEADLINE_WRONG_FORMAT_MESSAGE = "Usage: deadline <description> /by <deadline>";
    public static final String DELETE_WRONG_FORMAT_MESSAGE = "Usage: delete <task_index>";
    public static final String DELETE_INVALID_TASK_INDEX_MESSAGE = "Invalid task index. Please refer to the 'list' command for available indices.";
    public static final String DONE_WRONG_FORMAT_MESSAGE = "Usage: done <task_index>";
    public static final String DONE_INVALID_TASK_INDEX_MESSAGE = "Invalid task index. Please refer to the 'list' command for available indices.";
    
    public static final String EVENT_WRONG_FORMAT_MESSAGE = "Usage: event <description> /at <when>";
    public static final String FIND_WRONG_FORMAT_MESSAGE = "Usage: find <string_to_match>";
    public static final String TODO_WRONG_FORMAT_MESSAGE = "Usage: todo <description>";
    
    
    public static final String ADD_DUPLICATED_TASK_MESSAGE = "This task is duplicated!";
    public static final String ADD_TASK_SUCCESS_MESSAGE = "Got it. I've added this task:\n"
    + "  %s\n"
    + "Now you have %d tasks in the list.\n";
    
    public static final String SET_TASK_DONE_SUCCESS_MESSAGE = "Nice! I've marked this task as done: \n%s";
    public static final String DELETE_TASK_SUCCESS_MESSAGE = "Noted. I've removed this task: \n"
        + "  %s\n"
        + "Now you have %d tasks in the list.\n";
    public static final String FILTER_TASK_NO_TASKS_MESSAGE = "There are no matching tasks";
    public static final String FILTER_TASK_SUCCESS_MESSAGE = "Here are the matching tasks:\n%s";
    
    public static final String LIST_NO_TASK_MESSAGE = "You have no tasks";
    
    public static final String STORAGE_SAVE_ERROR_MESSAGE = "An error was found while writing to the storage file!\n"
        + "The file may be corrupted. "
        + "Please check the file at %s";
}