package app.core;

public class Messages {
    public static final String WELCOME_MESSAGE = "Hey, I'm Janet! "
            + "How may I help you, Chidi?";
    public static final String GOODBYE_MESSAGE = "Bye Chidi! "
            + "Enjoy your stay in The Bad Place! (hehe)";
    public static final String UNEXPECTED_ERROR_MESSAGE = "Oh No! sOmetHinG bAD is hAppeNing!!! "
            + "CALL MICHAEL (or any developer)!";
    
    // Command Manager
    public static final String EMPTY_COMMAND_MESSAGE = "Oh I'm sorry Chidi! You have to give me a command!";
    public static final String UNSUPPORTED_COMMAND_MESSAGE = "Woopsies! I don't understand '%s'";
    
    // Commands
    public static final String BYE_WRONG_FORMAT_MESSAGE = "Usage: bye";

    public static final String LIST_WRONG_FORMAT_MESSAGE = "Usage: list";
    public static final String LIST_NO_TASK_MESSAGE = "You have no tasks";
    public static final String LIST_SUCCESS_MESSAGE = "Okay Chidi, listen properly...\n"
            + "These are your tasks:\n%s";
    
    // Adding Tasks
    public static final String TODO_WRONG_FORMAT_MESSAGE = "Usage: todo <description>";
    public static final String DEADLINE_WRONG_FORMAT_MESSAGE = "Usage: deadline <description> /by <deadline>";
    public static final String EVENT_WRONG_FORMAT_MESSAGE = "Usage: event <description> /at <when>";
    public static final String ADD_DUPLICATED_TASK_MESSAGE = "This task is duplicated!";
    public static final String ADD_TASK_SUCCESS_MESSAGE = "Okie! I've added this task:\n"
            + "    %s\nYou have %d tasks in the list!\n";

    // Deleting Tasks
    public static final String DELETE_WRONG_FORMAT_MESSAGE = "Usage: delete <task_index>";
    public static final String DELETE_INVALID_TASK_INDEX_MESSAGE = "Oops Chidi! "
            + "I think you gave me the wrong task index!\n"
            + "Please refer to the 'list' command for available indices.";
    public static final String DELETE_TASK_SUCCESS_MESSAGE = "Noted. I've removed this task:\n"
            + "    %s\nNow you have %d tasks in the list.\n";
    
    // Set Task Done
    public static final String DONE_WRONG_FORMAT_MESSAGE = "Usage: done <task_index>";
    public static final String DONE_INVALID_TASK_INDEX_MESSAGE = "Oops Chidi! "
            + "I think you gave me the wrong task index!\n"
            + "Please refer to the 'list' command for available indices.";
    public static final String DONE_SUCCESS_MESSAGE = "Well done Chidi! I've marked this task as done:\n"
            + "    %s\nGive yourself a pat on the back!";

    // Find Tasks
    public static final String FIND_WRONG_FORMAT_MESSAGE = "Usage: find <string_to_match>";
    public static final String FIND_NO_TASKS_MESSAGE = "Ah dang it! There are no matching tasks ):";
    public static final String FIND_SUCCESS_MESSAGE = "Alrighty, I found some tasks! Here they are:\n%s";

    // Others
    public static final String STORAGE_SAVE_ERROR_MESSAGE = "Oh no! An error happened "
            + "while I was writing to the storage file!\n"
            + "The file may be corrupted. Please check the file at %s!";
    
    public static final String INVALID_DATE_FORMAT_MESSAGE = "Ah! I can only understand this format: %s";
    public static final String INVALID_CHARACTER_MESSAGE = "Hey... "
            + "I think you used an invalid character (%s) in your input...";
}